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
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.entities.securities.auxs.SPSecurityAux;
import com.lynxspa.entities.securities.markets.SPMarket;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="Load Securities", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadSecurities <T extends ScheduledEvent> extends InternalNodeSupport <T>{

	private static final Logger logger = Logger.getLogger(LoadSecurities.class);

	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager manager=null;
		StatelessSession statelessSession=null;
		Query loadSecuritiesQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPSecurityAux> auxSecurityList = null;
		SPSecurity security = null;
		SPMarket market = null;
		SPVirtualSecurity virtualSecurity = null;
		int affectedRows=0;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			manager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadSecuritiesQuery = statelessSession.createQuery("update SPSecurityAux auxSecurity set auxSecurity.deleted=:deleted where auxSecurity.deleted=:deletedFalse");
			loadSecuritiesQuery.setParameter("deleted", true);
			loadSecuritiesQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadSecuritiesQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"AUX_TB_SP_SECURITIES",rowsMarkedForLoad},null);
				
				//Paged query
				loadSecuritiesQuery=statelessSession.createQuery("select count(*) from SPSecurityAux auxSecurities where auxSecurities.deleted=:deleted");
				loadSecuritiesQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadSecuritiesQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadSecuritiesQuery=statelessSession.createQuery("select auxSecurities from SPSecurityAux auxSecurities where auxSecurities.deleted=:deleted order by auxSecurities.id asc");
					loadSecuritiesQuery.setParameter("deleted",true);
					loadSecuritiesQuery.setMaxResults(pageSize);
					auxSecurityList = loadSecuritiesQuery.list();
					
					for (SPSecurityAux auxSecurities:auxSecurityList){
						try {
							loadSecuritiesQuery=statelessSession.createQuery("select security from SPSecurity security where security.customerId=:customerId");
							loadSecuritiesQuery.setParameter("customerId",auxSecurities.getCustomerId().trim());
							loadSecuritiesQuery.setMaxResults(1);
							security = (SPSecurity)loadSecuritiesQuery.uniqueResult();
							
							loadSecuritiesQuery=statelessSession.createQuery("select market from SPMarket market where market.customerId=:customerMarketId");
							loadSecuritiesQuery.setParameter("customerMarketId",auxSecurities.getMarket().trim());
							loadSecuritiesQuery.setMaxResults(1);
							market = (SPMarket)loadSecuritiesQuery.uniqueResult();
					
							//custManager = (SPManager)(statelessSession.get(SPManager.class, 0l));
							if (security==null){
								
								loadSecuritiesQuery=statelessSession.createQuery("select virtualSecurity from SPVirtualSecurity virtualSecurity where virtualSecurity.auditor.deleted=:deleted and (virtualSecurity.isin=:customerSecurityIsin or virtualSecurity.isin=:customerID)");
								loadSecuritiesQuery.setParameter("deleted",false);
								loadSecuritiesQuery.setParameter("customerSecurityIsin",auxSecurities.getIsin());
								loadSecuritiesQuery.setParameter("customerID",auxSecurities.getCustomerId());
								loadSecuritiesQuery.setMaxResults(1);
								virtualSecurity = (SPVirtualSecurity)loadSecuritiesQuery.uniqueResult();
		
								security = new SPSecurity(auxSecurities.getAuditorId());
								security.setCountry(auxSecurities.getCountry());
								security.setCurrency(auxSecurities.getCurrency());
								security.setCusip(auxSecurities.getCusip());
								security.setCustomerId(auxSecurities.getCustomerId().trim());
								security.setExpirationDate(auxSecurities.getExpirationDate());
								security.setInCustomerPortfolio(true);
								security.setIndGroup(auxSecurities.getIndGrup());
								security.setIndSector(auxSecurities.getIndSector());
								security.setIndSubGroup(auxSecurities.getIndSubgrup());
								security.setIsin(auxSecurities.getIsin());
								security.setMarket(market);
								security.setName(auxSecurities.getName());
								security.setPlanification(null);
								security.setTicker(auxSecurities.getTicker());
								security.setSedol(auxSecurities.getSedol());
								security.setSecurityType(auxSecurities.getValueType());
								security.setSecurityDetail(null);
								security.setSecFinancialAssets(null);//TODO ver si es necesario
								security.setRelIndex(auxSecurities.getRelatedIndex());
								security.getAuditor().setDeleted(auxSecurities.getState()!=null && auxSecurities.getState().equals("A"));
								HibernateUtils.customSave(statelessSession, security, auxSecurities.getAuditorId());
								
								if (virtualSecurity!=null){
									//Update associated messages
									loadSecuritiesQuery=statelessSession.createQuery("update CAEventMessage set normalizedSecurity.id=:securityId,operationStatus.state.id.code=:newState where normalizedSecurity.id=:virtualSecurityId");
									loadSecuritiesQuery.setLong("securityId", security.getId());
									loadSecuritiesQuery.setString("newState", CAStatesEVENTMESSAGEFlow.PRSD.getId());
									loadSecuritiesQuery.setLong("virtualSecurityId", virtualSecurity.getId());
									affectedRows=loadSecuritiesQuery.executeUpdate();
									
									loadSecuritiesQuery=statelessSession.createQuery("update SPSecurityPortfolio set security.id=:securityId where security.id=:virtualSecurityId");
									loadSecuritiesQuery.setLong("securityId", security.getId());
									loadSecuritiesQuery.setLong("virtualSecurityId", virtualSecurity.getId());
									affectedRows=loadSecuritiesQuery.executeUpdate();
									
									//delete virtual security
									HibernateUtils.customDelete(statelessSession, virtualSecurity, auxSecurities.getCustomerId().trim());								
									logger.debug("LoadSecurities Procces - Security "+ security.getId() +" has created from virtual security "+ auxSecurities.getAuditorId().trim() +" modifying "+ affectedRows +" event messages.");
								}
							}else{
								security.setCountry(auxSecurities.getCountry());
								security.setCurrency(auxSecurities.getCurrency());
								security.setCusip(auxSecurities.getCusip());
								security.setCustomerId(auxSecurities.getCustomerId().trim());
								security.setExpirationDate(auxSecurities.getExpirationDate());
								security.setInCustomerPortfolio(true);
								security.setIndGroup(auxSecurities.getIndGrup());
								security.setIndSector(auxSecurities.getIndSector());
								security.setIndSubGroup(auxSecurities.getIndSubgrup());
								security.setIsin(auxSecurities.getIsin());
								security.setMarket(market);
								security.setName(auxSecurities.getName());
								security.setPlanification(null);
								security.setTicker(auxSecurities.getTicker());
								security.setSedol(auxSecurities.getSedol());
								security.setSecurityType(auxSecurities.getValueType());
								security.setSecurityDetail(null);
								security.setSecFinancialAssets(null);//TODO ver si es necesario
								security.setRelIndex(auxSecurities.getRelatedIndex());
								security.getAuditor().setDeleted(auxSecurities.getState()!=null && auxSecurities.getState().equals("A"));
								HibernateUtils.customUpdate(statelessSession, security,  auxSecurities.getAuditorId());
	
							}
							
							loadSecuritiesQuery=statelessSession.createQuery("delete SPSecurityAux as auxSecs where auxSecs.deleted=:deleted and auxSecs.id=:auxSecsId");
							loadSecuritiesQuery.setParameter("deleted", true);
							loadSecuritiesQuery.setLong("auxSecsId", auxSecurities.getId());
							rowsDeleted+=loadSecuritiesQuery.executeUpdate();
						} catch (Exception e) {
							LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_SECURITIES",auxSecurities.getCustomerId().trim()}, e);
							auxSecurities.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxSecurities,  auxSecurities.getAuditorId());
						}
						
						
					}
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadSecuritiesQuery = statelessSession.createQuery("update SPSecurityAux auxSecurity set auxSecurity.deleted=:deleted where auxSecurity.deleted=:deletedFalse");
				loadSecuritiesQuery.setParameter("deleted", true);
				loadSecuritiesQuery.setParameter("deletedFalse", false);
				errors=loadSecuritiesQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_SECURITIES",rowsDeleted, errors},null);
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
