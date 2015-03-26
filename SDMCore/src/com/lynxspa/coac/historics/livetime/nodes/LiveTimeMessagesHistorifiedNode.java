package com.lynxspa.coac.historics.livetime.nodes;

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
@NodeBeautifier(description="LivetimeMessagesHistorifiedNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LiveTimeMessagesHistorifiedNode<T extends ScheduledEvent> extends InternalNodeSupport<T>{
	private static final Logger logger = Logger.getLogger(LiveTimeMessagesHistorifiedNode.class);
	
	private static final long DEFAULT_DAYS_TO_DELETE = 30;
	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		Query livetimeMessageHistorifiedQuery = null;
		long daysToDeleteHistorified = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		int deletedMessageFieldsHistorified= 0;
		int deletedMessagesHistorified= 0;
		try {
			manager=SDMConfigManager.getInstance();
			statefullSession=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			processingTime=System.currentTimeMillis();
			daysToDeleteHistorified = getDaysToDeleteHistorify(statefullSession);
			
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.START_LIVETIME_MESSAGES_HISTORIFIED, new Object[]{daysToDeleteHistorified},null);
			historificableDate=Calendar.getInstance();
			historificableDate.add(Calendar.DAY_OF_YEAR,(int)(daysToDeleteHistorified*(-1)));
			
			logger.info("Deleting CAEventMessageHistoric older than "+String.valueOf(historificableDate)+" to delete status");
			
			livetimeMessageHistorifiedQuery=statelessSession.createQuery("delete CAEventMessageFieldHistoric where  message.id in (select id from CAEventMessageHistoric where auditor.updateDate<=:calculatedDate)");
			livetimeMessageHistorifiedQuery.setParameter("calculatedDate",historificableDate.getTime());
			deletedMessageFieldsHistorified=livetimeMessageHistorifiedQuery.executeUpdate();
			
			
			livetimeMessageHistorifiedQuery=statelessSession.createQuery("delete CAEventMessageHistoric where auditor.updateDate<=:calculatedDate");
			livetimeMessageHistorifiedQuery.setParameter("calculatedDate",historificableDate.getTime());
			deletedMessagesHistorified=livetimeMessageHistorifiedQuery.executeUpdate();
			
			HibernateUtils.commitTransaction(statelessSession);
		
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.END_LIVETIME_MESSAGES_HISTORIFIED, new Object[]{deletedMessagesHistorified, deletedMessageFieldsHistorified,processingTime},null);
		} catch (Exception e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}finally{
			if(statelessSession!=null)
				statelessSession.close();
		}
		
	}
	
	private long getDaysToDeleteHistorify (Session _session) throws Exception{
		
		Long reply= null;
		SDMConfigManager manager=null;
		
		try{
			manager=SDMConfigManager.getInstance();
			reply=(Long)manager.getConfiguration(_session,CAConfiguration.HISTORIFICSMESSAGESLIVETIME);
		}catch (Exception e){
			LogUtils.createLog(_session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(_session), LogErrorDict.DAYS_TO_HISTORIFY_EVENTS_FAIL, new Object[]{e.getMessage(),DEFAULT_DAYS_TO_DELETE}, e);
			reply=DEFAULT_DAYS_TO_DELETE;
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
	public String getLocale() {
		return locale;
	}
	public void setLocale(String _locale) {
		this.locale = _locale;
	}
}