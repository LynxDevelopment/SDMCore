package com.lynxspa.coac.loadauxtables.customers.nodes;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.scheduler.ScheduledEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.markets.SPMarket;
import com.lynxspa.entities.securities.markets.auxs.SPMarketAux;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="Load Markets", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadMarkets <T extends ScheduledEvent> extends InternalNodeSupport <T>{
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;

	private static final Logger logger = Logger.getLogger(LoadMarkets.class);
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager configManager=null;
		StatelessSession statelessSession=null;
		Query loadMarketsQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPMarketAux> auxMarketsList = null;
		SPMarket market = null;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			configManager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadMarketsQuery = statelessSession.createQuery("update SPMarketAux auxMarket set auxMarket.deleted=:deleted where auxMarket.deleted=:deletedFalse");
			loadMarketsQuery.setParameter("deleted", true);
			loadMarketsQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadMarketsQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"SPMarketAux",rowsMarkedForLoad},null);
				
				//Paged query
				loadMarketsQuery=statelessSession.createQuery("select count(*) from SPMarketAux auxMarket where auxMarket.deleted=:deleted");
				loadMarketsQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadMarketsQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadMarketsQuery=statelessSession.createQuery("select auxMarket from SPMarketAux auxMarket where auxMarket.deleted=:deleted order by auxMarket.id asc");
					loadMarketsQuery.setParameter("deleted",true);
					loadMarketsQuery.setMaxResults(pageSize);
					auxMarketsList = loadMarketsQuery.list();
				
					for (SPMarketAux auxMarkets:auxMarketsList){
						try{
							loadMarketsQuery=statelessSession.createQuery("select market from SPMarket market where market.customerId=:marketId");
							loadMarketsQuery.setParameter("marketId",auxMarkets.getMarketCode().trim());
							loadMarketsQuery.setMaxResults(1);
							market = (SPMarket)loadMarketsQuery.uniqueResult();
							if (market==null){
								market = new SPMarket(auxMarkets.getAuditorId(),null,auxMarkets.getName(),auxMarkets.getCountry(),null,auxMarkets.getMarketCode().trim());
								market.getAuditor().setDeleted(auxMarkets.getState()!=null && auxMarkets.getState().equals("A"));
								market.setMic(auxMarkets.getMic()==null?" ":auxMarkets.getMic());
								HibernateUtils.customSave(statelessSession, market, auxMarkets.getAuditorId());
							}else{
								market.setCustomerId(auxMarkets.getMarketCode().trim());
								market.setName(auxMarkets.getName());
								market.setCity(auxMarkets.getCity());
								market.setMic(auxMarkets.getMic()==null?" ":auxMarkets.getMic());
								market.getAuditor().setDeleted(auxMarkets.getState()!=null && auxMarkets.getState().equals("A"));
								HibernateUtils.customUpdate(statelessSession, market,  auxMarkets.getAuditorId());
							}
							
							loadMarketsQuery=statelessSession.createQuery("delete SPMarketAux as auxMarkets where auxMarkets.deleted=:deleted and auxMarkets.id=:auxMarketId");
							loadMarketsQuery.setParameter("deleted", true);
							loadMarketsQuery.setLong("auxMarketId", auxMarkets.getId());
							rowsDeleted+=loadMarketsQuery.executeUpdate();
						}catch(Exception e){
							LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_MARKETS",auxMarkets.getId()}, e);
							auxMarkets.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxMarkets,  auxMarkets.getAuditorId());
						}
					}
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadMarketsQuery = statelessSession.createQuery("update SPMarketAux auxMarket set auxMarket.deleted=:deleted where auxMarket.deleted=:deletedFalse");
				loadMarketsQuery.setParameter("deleted", true);
				loadMarketsQuery.setParameter("deletedFalse", false);
				errors=loadMarketsQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_MARKETS",rowsDeleted, errors},null);
			}
		} catch (FPMException e) {
			logger.error("FPMException: "+e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception stablishing events to be deleted: "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}finally{
			if(statelessSession!=null)
				statelessSession.close();
		}
	}

	@ConfigParam(required=true)
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}
	public void setResourceStateless(Resource<StatelessSession> resourceStateless) {
		this.resourceStateless = resourceStateless;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@ConfigParam(required=false)
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize > 0)
			this.pageSize = pageSize;
	}
	
	private long calculateTotalPages(long totalElements){
    	long pages = 0l;
    	
    	pages = (totalElements/pageSize);
    	if(totalElements % pageSize != 0){
    		pages++;
    	}
    	
    	return pages;
    }
}
