package com.lynxspa.coac.historics.securityportfolio.nodes;

import java.util.Calendar;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.scheduler.ScheduledEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="SecurityPortfolioHistorificationNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class SecurityPortfolioHistorificationNode <T extends ScheduledEvent> extends InternalNodeSupport<T>{

	private static final Logger logger = Logger.getLogger(SecurityPortfolioHistorificationNode.class);
	
	private static final long DEFAULT_DAYS_TO_HISTORIFY = 30;
	
	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private Integer historificationCommitSize=0;
	private String locale=null;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		Query securityPortfolioQuery = null;
		
		long daysTohistorify = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		long proccessedCorrectly=0;
		long proccessedWrong=0;
		int rowsMarkedForHistorification=0;
		int securityPortfolioHistorified=0;
		int securityPortfolioDeleted=0;
		int historifiedSecurityPortfolioUpdated=0;
		long initialId=0l;
		long lastId=0l;
		
		try {
			manager=SDMConfigManager.getInstance();
			statefullSession=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			processingTime=System.currentTimeMillis();
			daysTohistorify=getDaysToHistorify(statefullSession);
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.START_HISTORIFY, new Object[]{daysTohistorify},null);
			historificableDate=Calendar.getInstance();
			historificableDate.add(Calendar.DAY_OF_YEAR,(int)(daysTohistorify*(-1)));
			logger.info("Setting CAEventMessage older than "+String.valueOf(historificableDate)+" to delete status");
			
			securityPortfolioQuery=statelessSession.createQuery("update SPSecurityPortfolio set auditor.deleted=:deleted where auditor.creationDate<=:calculatedDate");
			securityPortfolioQuery.setParameter("deleted", true);
			securityPortfolioQuery.setParameter("calculatedDate",historificableDate.getTime());
			rowsMarkedForHistorification=securityPortfolioQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			
			if(rowsMarkedForHistorification>0){
				HibernateUtils.beguinTransaction(statelessSession);
				
				logger.info("Recovering CAEventMessage first id");
				securityPortfolioQuery=statelessSession.createQuery("select id from SPSecurityPortfolio where auditor.deleted=:deleted order by id asc");
				securityPortfolioQuery.setParameter("deleted",true);
				securityPortfolioQuery.setFetchSize(1);
				securityPortfolioQuery.setMaxResults(1);
				initialId=(Long)securityPortfolioQuery.uniqueResult();
				logger.info("First id: "+initialId);
				
				logger.info("Recovering CAEventMessage last id");
				securityPortfolioQuery=statelessSession.createQuery("select id from SPSecurityPortfolio where auditor.deleted=:deleted order by id desc");
				securityPortfolioQuery.setParameter("deleted",true);
				securityPortfolioQuery.setFetchSize(1);
				securityPortfolioQuery.setMaxResults(1);
				lastId=(Long)securityPortfolioQuery.uniqueResult();
				logger.info("Last id: "+lastId);
				HibernateUtils.commitTransaction(statelessSession);
				
				for(long maxId=initialId+this.historificationCommitSize;initialId<=lastId;maxId+=this.historificationCommitSize,initialId+=this.historificationCommitSize){
					logger.info("Historifing from "+initialId+" to "+maxId);
					
					HibernateUtils.beguinTransaction(statelessSession);
					securityPortfolioHistorified=0;
					securityPortfolioDeleted=0;
					historifiedSecurityPortfolioUpdated=0;

					try{
					
						logger.info("	Saving CAEventMessageHistoric from CAEventMessage");
						securityPortfolioQuery=statelessSession.createQuery("" +
								"insert into SPHistoricSecurityPortfolio " +
									"(id,customer,security,customerAmount,custodianAmount,"+
									"version,auditor,emissionDate) " +
								"select " +
									"securityPortfolio.id,securityPortfolio.customer,securityPortfolio.security, "+
									"securityPortfolio.customerAmount, securityPortfolio.custodianAmount, "+
									"securityPortfolio.version, securityPortfolio.auditor, securityPortfolio.emissionDate "+
								"from SPSecurityPortfolio as securityPortfolio " +
								"where securityPortfolio.auditor.deleted=:deleted and securityPortfolio.id>=:initialId and securityPortfolio.id<:endId " +
								"and securityPortfolio.custodianAmount=1l");
						
						securityPortfolioQuery.setParameter("deleted",true);
						securityPortfolioQuery.setParameter("initialId",initialId);
						securityPortfolioQuery.setParameter("endId",maxId);
						securityPortfolioHistorified=securityPortfolioQuery.executeUpdate();
						
						
						securityPortfolioQuery=statelessSession.createQuery("delete SPSecurityPortfolio as securityPortfolio where securityPortfolio.auditor.deleted=:deleted and securityPortfolio.id>=:initialId and securityPortfolio.id<:endId");
						securityPortfolioQuery.setParameter("deleted", true);
						securityPortfolioQuery.setParameter("initialId",initialId);
						securityPortfolioQuery.setParameter("endId",maxId);
						securityPortfolioDeleted=securityPortfolioQuery.executeUpdate();
						
						securityPortfolioQuery=statelessSession.createQuery("update SPHistoricSecurityPortfolio securityPortfolioHist set securityPortfolioHist.auditor.deleted=:deletedFalse where securityPortfolioHist.auditor.deleted=:deletedTrue and securityPortfolioHist.id>=:initialId and securityPortfolioHist.id<:endId");
						securityPortfolioQuery.setParameter("deletedFalse",false);
						securityPortfolioQuery.setParameter("deletedTrue",true);
						securityPortfolioQuery.setParameter("initialId",initialId);
						securityPortfolioQuery.setParameter("endId",maxId);
						historifiedSecurityPortfolioUpdated=securityPortfolioQuery.executeUpdate();
						
						HibernateUtils.commitTransaction(statelessSession);
						proccessedCorrectly+=securityPortfolioHistorified;
						
					}catch (Exception e) {
						logger.error("Historification from "+initialId+" to "+maxId+" fail",e);
						HibernateUtils.rollbackTransaction(statelessSession);
						LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogErrorDict.HISTORIFICATION_BLOCK_FAIL, new Object[]{initialId,maxId,securityPortfolioHistorified,historifiedSecurityPortfolioUpdated,securityPortfolioDeleted,historifiedSecurityPortfolioUpdated}, e);
						proccessedWrong+=securityPortfolioHistorified;
					}
				}
			}
			logger.info("Affected rows: "+rowsMarkedForHistorification);
			
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.END_HISTORIFY, new Object[]{processingTime,rowsMarkedForHistorification,proccessedCorrectly,proccessedWrong},null);
		} catch (FPMException e) {
			throw e;
		} catch (Exception e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}finally{
			if(statelessSession!=null)
				statelessSession.close();
		}
		
	}

	private long getDaysToHistorify (Session _session) throws Exception{
		
		Long reply= null;
		SDMConfigManager manager=null;
		
		try{
			manager=SDMConfigManager.getInstance();
			reply=(Long)manager.getConfiguration(_session,CAConfiguration.TIMETOHISTORIFICATIONMESSAGES);
		}catch (Exception e){
			LogUtils.createLog(_session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(_session), LogErrorDict.DAYS_TO_HISTORIFY_FAIL, new Object[]{e.getMessage(),DEFAULT_DAYS_TO_HISTORIFY}, e);
			reply=DEFAULT_DAYS_TO_HISTORIFY;
		}
		
		return reply;
	}
	
	@ConfigParam(required=true)
	public Resource<Session> getResource() {
		return resource;
	}

	public void setResource(Resource<Session> resource) {
		this.resource = resource;
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
	public Integer getHistorificationCommitSize() {
		return historificationCommitSize;
	}
	public void setHistorificationCommitSize(Integer historificationCommitSize) {
		this.historificationCommitSize = historificationCommitSize;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	
}
