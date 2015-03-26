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
@NodeBeautifier(description="LivetimeEventsHistorifiedNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LivetimeEventsHistorifiedNode<T extends ScheduledEvent> extends InternalNodeSupport<T>{

    private static final Logger logger = Logger.getLogger(LivetimeEventsHistorifiedNode.class);

	private static final long DEFAULT_DAYS_TO_DELETE = 30;

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale;

	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		Query livetimeEventHistorifiedQuery = null;
		long daysToDeleteHistorified = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		int rowsMarkedForDeleteHistorificationEvent=0;
		int totalRowsMarkedForDeleteHistorification=0;
		int rowsMarkedForDeleteDetailHistorification = 0;
		int rowsMarkedForDeleteExtDetailHistorification= 0;
		
		try{
			manager=SDMConfigManager.getInstance();
			statefullSession=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			processingTime=System.currentTimeMillis();
			daysToDeleteHistorified = getDaysToDeleteHistorify(statefullSession);
			
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.START_LIVETIME_EVENTS_HISTORIFIED, new Object[]{daysToDeleteHistorified},null);
			
			historificableDate=Calendar.getInstance();
			historificableDate.add(Calendar.DAY_OF_YEAR,(int)(daysToDeleteHistorified*(-1)));
			
			logger.info("Start Deleting Historified Events older than "+String.valueOf(historificableDate)+" days.");

			try{
				livetimeEventHistorifiedQuery=statelessSession.createQuery(""+
						"update CAEventHistoric event set event.auditor.deleted=:deleted where " +
						"event.auditor.creationDate<=:calculatedDate "+
						"and (event.id not in "+
							"(select distinct(eventw.eventGroup.id) from CAEventHistoric eventw where eventw.auditor.creationDate>:calculatedDate "+
									"and eventw.eventGroup.id is not null)) "+
						"and (id not in "+
							"(select distinct(id) from CAEventHistoric "+
							"where auditor.creationDate>:calculatedDate and id is not null))"+
						"and (event.id not in "+
							"(select eventG.id from CAEventHistoric eventG where eventG.id is not null and eventG.id in( "+
					        " select distinct(auditor.refId) from CAEventHistoric where auditor.creationDate>:calculatedDate))) "+
						"and (event.masterEvent.id=null or event.masterEvent.id not in ( "+
						    "select eventCol.id from CAEventCollectedHistoric as eventCol "+
						    "where auditor.creationDate>:calculatedDate and eventCol.id in( "+
						    "select gr.masterEvent.id from CAEventGroupHistoric gr where auditor.creationDate<:calculatedDate "+
						    "and gr.masterEvent.id is not null)))"+
						"and (id not in "+
							"(select distinct(event.masterEvent.id) from CAEventHistoric event join event.masterEvent as masterRecord with "+ 
							"masterRecord.auditor.deleted=0 where event.auditor.creationDate>:calculatedDate))");
				
				livetimeEventHistorifiedQuery.setParameter("deleted", true);
				livetimeEventHistorifiedQuery.setParameter("calculatedDate",historificableDate.getTime());
				
				rowsMarkedForDeleteHistorificationEvent=livetimeEventHistorifiedQuery.executeUpdate();

				
				logger.info("Total Events to delete "+totalRowsMarkedForDeleteHistorification);
				if (rowsMarkedForDeleteHistorificationEvent>0){
					livetimeEventHistorifiedQuery = statelessSession.createQuery(""+
							"update CAEventDetailHistoric as e set e.auditor.deleted=:deleted where e.id in ("+
								"select distinct eventDetail.id from CAEventHistoric where auditor.deleted=:deleted and eventDetail.id is not null)"+
							"and e.id not in ("+
								"select distinct(eventDetail.id) from CAEventHistoric where auditor.deleted=:deleted2 and eventDetail.id is not null)");
					livetimeEventHistorifiedQuery.setParameter("deleted",true);
					livetimeEventHistorifiedQuery.setParameter("deleted2",false);
					rowsMarkedForDeleteDetailHistorification=livetimeEventHistorifiedQuery.executeUpdate();
					logger.info("Total EventDetails to delete "+totalRowsMarkedForDeleteHistorification);
					
					livetimeEventHistorifiedQuery = statelessSession.createQuery(""+
							"update CAEventDetailExtendedHistoric extDetail set extDetail.auditor.deleted=:deleted "+
							"where extDetail.mainDetail.id in ("+
								"select distinct id from CAEventDetailHistoric where auditor.deleted=:deleted)");
					livetimeEventHistorifiedQuery.setParameter("deleted",true);
					rowsMarkedForDeleteExtDetailHistorification=livetimeEventHistorifiedQuery.executeUpdate();
					logger.info("Total EventExtDetails to delete "+totalRowsMarkedForDeleteHistorification);
					//if (rowsMarkedForDeleteExtDetailHistorification>0){
					deleteOldHistorifications(statelessSession, livetimeEventHistorifiedQuery);
					//}
					HibernateUtils.commitTransaction(statelessSession);
				}
			}catch (Exception e) {
				logger.error("Error Deleting Historifyed Events: ",e);
				HibernateUtils.rollbackTransaction(statelessSession);
				LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogErrorDict.LIVETIME_EVENTS_HISTORIFIED_FAIL, new Object[]{}, e);
			}
			processingTime=System.currentTimeMillis()-processingTime;
			
			logger.info("End Deleting Historified Events. Events Deleted: "+totalRowsMarkedForDeleteHistorification+", EventsDetail deleted: "+rowsMarkedForDeleteDetailHistorification+", EventsExtDetails deleted: "+rowsMarkedForDeleteExtDetailHistorification);
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.END_LIVETIME_EVENTS_HISTORIFIED, new Object[]{totalRowsMarkedForDeleteHistorification,rowsMarkedForDeleteDetailHistorification,rowsMarkedForDeleteExtDetailHistorification,processingTime},null);
		} catch (FPMException e) {
			throw e;
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
			reply=(Long)manager.getConfiguration(_session,CAConfiguration.HISTORIFICSEVENTSLIVETIME);
		}catch (Exception e){
			LogUtils.createLog(_session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(_session), LogErrorDict.DAYS_TO_HISTORIFY_EVENTS_FAIL, new Object[]{e.getMessage(),DEFAULT_DAYS_TO_DELETE}, e);
			reply=DEFAULT_DAYS_TO_DELETE;
		}
		
		return reply;
	}
	
	private void deleteOldHistorifications(StatelessSession _statelessSession, Query _livetimeEventHistorifiedQuery) throws Exception{
		
		
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("update CAEventGroupHistoric as event set "+
				"event.masterEvent=null where "+
				"event.masterEvent is not null and event.auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();
		
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("update CAEventHistoric as event set "+
				"event.auditor.refId=0 where "+
				"event.auditor.refId is not null and event.auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();

		logger.info("Deleting CAEventCollectedHistoric");
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("delete CAEventCollectedHistoric as event where event.auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();
		
		logger.info("Deleting CAEventGroupHistoric");
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("delete CAEventGroupHistoric as event where event.auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();
		
		logger.info("Deleting CAEventDetailExtendedHistoric");
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("delete CAEventDetailExtendedHistoric where auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();
		
		logger.info("Deleting CAEventDetailHistoric");
		_livetimeEventHistorifiedQuery=_statelessSession.createQuery("delete CAEventDetailHistoric where auditor.deleted=:deleted");
		_livetimeEventHistorifiedQuery.setParameter("deleted", true);
		_livetimeEventHistorifiedQuery.executeUpdate();

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
