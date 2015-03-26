package com.lynxspa.coac.historics.logs.nodes;

import java.util.Calendar;
import java.util.List;
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
@NodeBeautifier(description="HistorificationLogsNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class HistorificationLogsNode<T extends ScheduledEvent> extends InternalNodeSupport<T>{
	
    private static final Logger logger = Logger.getLogger(HistorificationLogsNode.class);

	private static final long DEFAULT_DAYS_TO_HISTORIFY = 30;

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private Integer historificationCommitSize=0;
	private String locale=null;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		
		Query logsQuery = null;
		long daysTohistorify = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		
		List<String> logsIdList = null;
		long totalRows = 0l;
		long totalPages = 0l;
		int rowsDeleted=0;
		try {
			manager=SDMConfigManager.getInstance();
			statefullSession=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			processingTime=System.currentTimeMillis();
			daysTohistorify=getDaysToHistorify(statefullSession);
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.START_LOGS_HISTORIFICATION, new Object[]{daysTohistorify},null);
			historificableDate=Calendar.getInstance();
			historificableDate.add(Calendar.DAY_OF_YEAR,(int)(daysTohistorify*(-1)));
			
			logger.info("Deleting Logs older than "+String.valueOf(historificableDate));
			//Paged query
			logsQuery=statelessSession.createQuery("select count(*) from Log log where auditor.creationDate<=:calculatedDate");
			logsQuery.setParameter("calculatedDate",historificableDate.getTime());
			
			totalRows = (Long)logsQuery.uniqueResult();
			if (totalRows>0){
				totalPages = calculateTotalPages(totalRows);
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					
					HibernateUtils.beguinTransaction(statelessSession);
					logsQuery=statelessSession.createQuery("select log.id from Log log where auditor.creationDate<=:calculatedDate");
					logsQuery.setParameter("calculatedDate",historificableDate.getTime());
					logsQuery.setMaxResults(historificationCommitSize);
					logsIdList= logsQuery.list();

					logsQuery=statelessSession.createQuery("delete Log as log where log.id in(:logIDs)");
					logsQuery.setParameterList("logIDs", logsIdList);

					rowsDeleted+=logsQuery.executeUpdate();
					HibernateUtils.commitTransaction(statelessSession);
					
				}
			}
			
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.END_LOGS_HISTORIFICATION, new Object[]{processingTime,rowsDeleted},null);
		} catch (FPMException e) {
			logger.error("Error FPMException Deleting Logs"+e);
			throw e;
		} catch (Exception e) {
			logger.error("Error Exception Deleting Logs"+e);
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
			reply=(Long)manager.getConfiguration(_session,CAConfiguration.TIMETODELETELOGS);
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
	public void setResource(Resource<Session> _resource) {
		this.resource = _resource;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}
	public void setUser(String _user) {
		this.user = _user;
	}
	
	@ConfigParam(required=true)
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}
	public void setResourceStateless(Resource<StatelessSession> _resourceStateless) {
		this.resourceStateless = _resourceStateless;
	}
	
	@ConfigParam(required=true)
	public Integer getHistorificationCommitSize() {
		return historificationCommitSize;
	}
	public void setHistorificationCommitSize(Integer _historificationCommitSize) {
		this.historificationCommitSize = _historificationCommitSize;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String _locale) {
		this.locale = _locale;
	}
	
	
	private long calculateTotalPages(long totalElements){
    	long pages = 0l;
    	
    	pages = (totalElements/historificationCommitSize);
    	if(totalElements % historificationCommitSize != 0){
    		pages++;
    	}
    	
    	return pages;
    }
}