package com.lynxspa.coac.historics.events.nodes;

import java.util.Calendar;
import java.util.Iterator;
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
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventCollectedHistoric;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
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
@NodeBeautifier(description="EventsHistorificationNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class EventsHistorificationNode<T extends ScheduledEvent> extends InternalNodeSupport<T>{
	
    private static final Logger logger = Logger.getLogger(EventsHistorificationNode.class);

	private static final long DEFAULT_DAYS_TO_HISTORIFY = 30;

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private Integer historificationCommitSize=0;
	private String locale=null;

	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		
		Query eventQuery = null;
		long daysTohistorify = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		int rowsMarkedForHistorificationEvent=0;
		int totalRowsMarkedForHistorification=0;
		int eventsHistorified=0;
		int detailsHistorified=0;
		int detailsExtendedHistorified=0;
		int rowsMarkedForDetailHistorification = 0;
		int rowsMarkedForExtDetailHistorification= 0;
			
		try {
			manager=SDMConfigManager.getInstance();
			statefullSession=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			processingTime=System.currentTimeMillis();
			daysTohistorify=getDaysToHistorify(statefullSession);
			
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.START_HISTORIFYEVENTS, new Object[]{daysTohistorify},null);

			historificableDate=Calendar.getInstance();
			historificableDate.add(Calendar.DAY_OF_YEAR,(int)(daysTohistorify*(-1)));
			
			logger.info("Historifying Events older than "+String.valueOf(historificableDate)+"");
			
			eventQuery=statelessSession.createQuery(""+
					"update CAEvent event set event.auditor.deleted=:deleted where " +
					"event.auditor.creationDate<=:calculatedDate "+
					"and (event.id not in "+
						"(select distinct(eventw.eventGroup.id) from CAEvent eventw where eventw.auditor.creationDate>:calculatedDate "+
								"and eventw.eventGroup.id is not null)) "+
					"and (id not in "+
						"(select distinct(id) from CAEvent "+
						"where auditor.creationDate>:calculatedDate and id is not null))"+
					"and (event.id not in "+
						"(select eventG.id from CAEvent eventG where eventG.id is not null and eventG.id in( "+
				        " select distinct(auditor.refId) from CAEvent where auditor.creationDate>:calculatedDate))) "+
					"and (event.masterEvent.id=null or event.masterEvent.id not in ( "+
					    "select eventCol.id from CAEventCollected as eventCol "+
					    "where auditor.creationDate>:calculatedDate and eventCol.id in( "+
					    "select gr.masterEvent.id from CAEventGroup gr where auditor.creationDate<:calculatedDate "+
					    "and gr.masterEvent.id is not null)))"+
					"and (id not in "+
						"(select distinct(event.masterEvent.id) from CAEvent event join event.masterEvent as masterRecord with "+ 
						"masterRecord.auditor.deleted=0 where event.auditor.creationDate>:calculatedDate))");
			
			eventQuery.setParameter("deleted", true);
			eventQuery.setParameter("calculatedDate",historificableDate.getTime());
			
			rowsMarkedForHistorificationEvent = eventQuery.executeUpdate();

			logger.info("Total Events to historify "+rowsMarkedForHistorificationEvent);
			
			eventQuery = statelessSession.createQuery(""+
					"update CAEventDetail as eDetail set eDetail.auditor.deleted=:deleted where eDetail.id in ( "+
						"select distinct eventDetail.id from CAEvent where auditor.deleted=:deleted and eventDetail.id is not null) "+
					"and eDetail.id not in ( "+
						"select distinct eventDetail.id from CAEvent where auditor.deleted=:deleted2 and eventDetail.id is not null) ");
			eventQuery.setParameter("deleted",true);
			eventQuery.setParameter("deleted2",false);
			
			rowsMarkedForDetailHistorification=eventQuery.executeUpdate();
			logger.info("Total EventDetails to historify "+rowsMarkedForDetailHistorification);
			
			
			
			eventQuery = statelessSession.createQuery(""+
					"update CAEventDetailExtended extDetail set extDetail.auditor.deleted=:deleted "+
					"where extDetail.mainDetail.id in ("+
						"select distinct id from CAEventDetail where auditor.deleted=:deleted)");
			eventQuery.setParameter("deleted",true);
			
			rowsMarkedForExtDetailHistorification=eventQuery.executeUpdate();
			logger.info("Total EventExtDetails to historify "+rowsMarkedForExtDetailHistorification);
			
			
			HibernateUtils.commitTransaction(statelessSession);
			
			
			HibernateUtils.beguinTransaction(statelessSession);
			try{
				
				detailsHistorified = rowsMarkedForDetailHistorification>0?
						fillEventDetailHistoric(statelessSession, eventQuery):rowsMarkedForDetailHistorification;
				logger.info("EventDetails Historified: "+detailsHistorified);
				
				detailsExtendedHistorified = rowsMarkedForExtDetailHistorification>0
						?fillEventDetailExtendedHistoric(statelessSession, statefullSession, eventQuery)
						:rowsMarkedForExtDetailHistorification;
				logger.info("EventExtededDetails Historified: "+detailsExtendedHistorified);
				
				eventsHistorified = fillEventHistoric(statelessSession, statefullSession, eventQuery);
				logger.info("Events Historified: "+eventsHistorified);
				
				deleteHistorified(statelessSession, eventQuery);
				logger.info("Old Events deleted correctly");
				
				updateHistorics(statelessSession, eventQuery);
				logger.info("New Events updated correctly");
				
				HibernateUtils.commitTransaction(statelessSession);

			}catch (Exception e) {
				logger.error("Error Historifying: ",e);
				HibernateUtils.rollbackTransaction(statelessSession);
				
				HibernateUtils.beguinTransaction(statelessSession);
				
				eventQuery = statelessSession.createQuery(""+
						"update CAEventDetailExtended extDetail set extDetail.auditor.deleted=:deleted "+
						"where extDetail.auditor.deleted=:deletedtrue");
				eventQuery.setParameter("deleted",false);
				eventQuery.setParameter("deletedtrue",true);
				
				eventQuery.executeUpdate();
				
				eventQuery = statelessSession.createQuery(""+
						"update CAEventDetail as eDetail set eDetail.auditor.deleted=:deleted where  eDetail.auditor.deleted=:deletedtrue");
				eventQuery.setParameter("deleted",false);
				eventQuery.setParameter("deletedtrue",true);
				
				eventQuery.executeUpdate();
				
				eventQuery = statelessSession.createQuery(""+
						"update CAEvent as event set event.auditor.deleted=:deleted where event.auditor.deleted=:deletedtrue");
				eventQuery.setParameter("deleted",false);
				eventQuery.setParameter("deletedtrue",true);
				
				eventQuery.executeUpdate();
		
				eventQuery = statelessSession.createQuery(""+
						"update CAEventCollected as evencol set evencol.auditor.deleted=:deleted where evencol.auditor.deleted=:deletedtrue");
				eventQuery.setParameter("deleted",false);
				eventQuery.setParameter("deletedtrue",true);
				
				eventQuery.executeUpdate();
				
				HibernateUtils.commitTransaction(statelessSession);
				
				LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogErrorDict.HISTORIFICATION_EVENTS_FAIL, new Object[]{}, e);
			}
			processingTime=System.currentTimeMillis()-processingTime;
			logger.info("End Historifying Events spending "+processingTime+" to execute process");
			LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.END_HISTORIFYEVENTS, new Object[]{processingTime,totalRowsMarkedForHistorification,eventsHistorified,detailsHistorified, detailsExtendedHistorified},null);
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

	private int fillEventDetailHistoric(StatelessSession statelessSession, Query eventQuery) throws Exception{		
		int reply=0;

		eventQuery=statelessSession.createQuery(""+
				"insert into CAEventDetailHistoric " +
				"(id,version,auditor, subscriptionDate, executionDate, "+
				"expirationDate, operationalDate, dynamicTable) " +
				"select " +
					"eventDetail.id, eventDetail.version, eventDetail.auditor, "+
					"eventDetail.subscriptionDate, eventDetail.executionDate, "+  
					"eventDetail.expirationDate, eventDetail.operationalDate, eventDetail.dynamicTable "+
				"from CAEventDetail as eventDetail " +
				"where eventDetail.auditor.deleted=:deleted");
		
		eventQuery.setParameter("deleted",true);
		reply = eventQuery.executeUpdate();

		return reply;
	}
	
	@SuppressWarnings("unchecked")
	private int fillEventDetailExtendedHistoric(StatelessSession statelessSession, Session statefullSession, Query eventQuery) throws Exception{
		int reply=0;
		
		eventQuery=statefullSession.createQuery(""+
			"select detExt from CAEventDetailExtended detExt where "+
			"detExt.auditor.deleted=:deleted");
		eventQuery.setParameter("deleted",true);
		List<CAEventDetailExtended> evenDetExt = eventQuery.list();

		for (CAEventDetailExtended  evendetext:evenDetExt){
			eventQuery=statelessSession.createQuery(""+
				"insert into CAEventDetailExtendedHistoric " +
				"(id, mainDetail,extensionId, dynamicTable, narrative, "+
				"version, auditor) " +
				"select " +
					"eventDetailExtended.id, "+
					"(select detailHistoric from CAEventDetailHistoric detailHistoric where detailHistoric.id=:mainDetailId), "+
					"eventDetailExtended.extensionId ,eventDetailExtended.dynamicTable, "+
					"eventDetailExtended.narrative, eventDetailExtended.version, "+  
					"eventDetailExtended.auditor "+
				"from CAEventDetailExtended as eventDetailExtended " +
				"where eventDetailExtended.auditor.deleted=:deleted and eventDetailExtended.id=:eventDetailExtId");
			eventQuery.setParameter("deleted",true);
			eventQuery.setParameter("mainDetailId",evendetext.getMainDetail().getId());
			eventQuery.setParameter("eventDetailExtId",evendetext.getId());
			eventQuery.executeUpdate();
			reply++;
		}

		return reply;
	}
	
	@SuppressWarnings("unchecked")
	private int fillEventHistoric(StatelessSession _statelessSession, Session _statefullSession, Query _eventQuery) throws Exception{
		int reply=0;
		List<CAEventCollected> evenColList = null;
		Iterator evenColHistIte = null;
		CAEventCollectedHistoric evenColHist = null;
		
		_eventQuery=_statelessSession.createQuery("" +
				"insert into CAEventGroupHistoric " +
					"(" +
					"	id," +
					"	eventType," +
					"	security," +
					"	subscriptionDate," +
					"	executionDate," +
					"	expirationDate," +
					"	operationalDate," +
					"	providerUpdated," +
					"	providerCancelled," +
					"	version," +
					"	auditor," +
					"	operationStatus," +
					"	mandatory," +
					"	entityDeadLine) " +
				"select " +
					" eventGroup.id," +
					" eventGroup.eventType," +
					" eventGroup.security," +
					" eventGroup.subscriptionDate," +
					" eventGroup.executionDate," +
					" eventGroup.expirationDate," +
					" eventGroup.operationalDate," +
					" eventGroup.providerUpdated," +
					" eventGroup.providerCancelled," +
					" eventGroup.version," +
					" eventGroup.auditor," +
					" eventGroup.operationStatus,"+
					" eventGroup.mandatory,"+
					" eventGroup.entityDeadLine "+
				"from CAEventGroup as eventGroup " +
				"where eventGroup.auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted",true);
		reply=_eventQuery.executeUpdate();

		_eventQuery=_statefullSession.createQuery(""+
				"select even from CAEventCollected even where even.auditor.deleted=:deleted order by even.id asc");
		_eventQuery.setParameter("deleted",true);
		evenColList = _eventQuery.list();

		for (CAEventCollected  evencol:evenColList){
			_eventQuery= _statelessSession.createQuery("" +
				"insert into CAEventCollectedHistoric " +
					"(" +
					"	id," +
					"	eventType," +
					"	security," +
					"	subscriptionDate," +
					"	executionDate," +
					"	expirationDate," +
					"	operationalDate," +
					"	providerUpdated," +
					"	providerCancelled," +
					"	version," +
					"	auditor," +
					"	eventProvider, eventGroup, eventDetail, complete, operationStatus, mandatory) " +
				"select " +
					"	event.id," +
					"	event.eventType," +
					"	event.security," +
					"	event.subscriptionDate," +
					"	event.executionDate," +
					"	event.expirationDate," +
					"	event.operationalDate," +
					"	event.providerUpdated," +
					"	event.providerCancelled," +
					"	event.version," +
					"	event.auditor," +
					"	event.eventProvider, "+
					"	(select groupHistoric from CAEventGroupHistoric groupHistoric where groupHistoric.id=:groupHistoricId), "+
					" 	(select detailHistoric from CAEventDetailHistoric detailHistoric where detailHistoric.id=:eventDetailId), "+
					" 	event.complete, event.operationStatus, "+
					" 	event.mandatory "+
				"from CAEventCollected as event " +
				"where event.auditor.deleted=:deleted and event.id=:eventId");
			
			_eventQuery.setParameter("deleted",true);
			_eventQuery.setParameter("groupHistoricId",evencol.getEventGroup()!=null?evencol.getEventGroup().getId():null);
			_eventQuery.setParameter("eventDetailId",evencol.getEventDetail()!=null?evencol.getEventDetail().getId():null);
			_eventQuery.setParameter("eventId",evencol.getId());
			_eventQuery.executeUpdate();
			reply++;
		}
		
		_eventQuery=_statelessSession.createQuery(""+
					"select gr.id, gr.masterEvent.id from CAEventGroup as gr "+
					"where gr.masterEvent is not null and gr.auditor.deleted=:deleted) ");
		_eventQuery.setParameter("deleted",true);
		evenColHistIte = _eventQuery.list().iterator();

		while ( evenColHistIte.hasNext() ) {
			Object[] tuple = (Object[]) evenColHistIte.next();
			Long id = (Long) tuple[0];
			Long idMasterEvent = (Long) tuple[1];
			
			_eventQuery= _statelessSession.createQuery("" +
					"select col from CAEventCollectedHistoric as col where col.id=:idMasterEvent");
			_eventQuery.setParameter("idMasterEvent",idMasterEvent);
			_eventQuery.setMaxResults(1);
			evenColHist= (CAEventCollectedHistoric)_eventQuery.uniqueResult();
				
			_eventQuery= _statelessSession.createQuery("" +
			"update CAEventGroupHistoric groupHist set groupHist.masterEvent=:eventHist where groupHist.id=:idEvent");
			_eventQuery.setParameter("eventHist",evenColHist);
			_eventQuery.setParameter("idEvent",id);
			_eventQuery.executeUpdate();

		}
		return reply;
	}
	
	private void deleteHistorified(StatelessSession _statelessSession, Query _eventQuery) throws Exception{
		
		logger.info("Updating CAEvent");
		_eventQuery=_statelessSession.createQuery("update CAEventGroup as event set "+
				"event.masterEvent=null where "+
				"event.masterEvent is not null and event.auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();

		
		_eventQuery=_statelessSession.createQuery("update CAEvent as event set "+
				"event.auditor.refId=0 where "+
				"event.auditor.refId is not null and event.auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();

		
		logger.info("Deleting CAEventCollected's associated CAEventHoldingAnswer");
		_eventQuery=_statelessSession.createQuery("delete CAEventHoldingAnswer as answers where answers.question in (from CAQuestions as question where question.event in (from CAEventCollected as event where event.auditor.deleted=:deleted))");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();
		
		logger.info("Deleting CAEventCollected's associated CAQuestions");
		//FIXME this query doesn't work properly, generates an erroneus SQL query: 'delete from COAC.TB_CA_QUESTIONS, COAC.TB_CA_EVENTS caeventcol1_ where ISDELETED=?'
		//_eventQuery=_statelessSession.createQuery("delete CAQuestions as question where question.event.auditor.deleted=:deleted");
		_eventQuery=_statelessSession.createQuery("delete CAQuestions as question where question.event in (from CAEventCollected as event where event.auditor.deleted=:deleted)");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();
		
		logger.info("Deleting CAEventCollected");
		_eventQuery=_statelessSession.createQuery("delete CAEventCollected as event where event.auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();
		
		logger.info("Deleting CAEventGroup");
		_eventQuery=_statelessSession.createQuery("delete CAEventGroup as event where event.auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();
		
		logger.info("Deleting CAEventDetailExtended");
		_eventQuery=_statelessSession.createQuery("delete CAEventDetailExtended where auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();
		
		logger.info("Deleting CAEventDetail");
		_eventQuery=_statelessSession.createQuery("delete CAEventDetail where auditor.deleted=:deleted");
		_eventQuery.setParameter("deleted", true);
		_eventQuery.executeUpdate();

	}
	
	private void updateHistorics(StatelessSession statelessSession, Query eventQuery) throws Exception{
		
		logger.info("Updating CAEventHistoric, setting delete to false");
		eventQuery=statelessSession.createQuery("update CAEventHistoric event set event.auditor.deleted=:deletedFalse where event.auditor.deleted=:deletedTrue");
		eventQuery.setParameter("deletedFalse",false);
		eventQuery.setParameter("deletedTrue",true);
		eventQuery.executeUpdate();
		
		logger.info("Updating CAEventDetailExtendedHistoric, setting delete to false");
		eventQuery=statelessSession.createQuery("update CAEventDetailExtendedHistoric set auditor.deleted=:deletedFalse where auditor.deleted=:deletedTrue");
		eventQuery.setParameter("deletedFalse",false);
		eventQuery.setParameter("deletedTrue",true);
		eventQuery.executeUpdate();
		
		logger.info("Updating CAEventDetailHistoric, setting delete to false");
		eventQuery=statelessSession.createQuery("update CAEventDetailHistoric set auditor.deleted=:deletedFalse where auditor.deleted=:deletedTrue");
		eventQuery.setParameter("deletedFalse",false);
		eventQuery.setParameter("deletedTrue",true);
		eventQuery.executeUpdate();
		
	}
	
	private long getDaysToHistorify (Session _session) throws Exception{
		
		Long reply= null;
		SDMConfigManager manager=null;
		
		try{
			manager=SDMConfigManager.getInstance();
			reply=(Long)manager.getConfiguration(_session,CAConfiguration.TIMETOHISTORIFICATIONEVENTS);
		}catch (Exception e){
			LogUtils.createLog(_session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(_session), LogErrorDict.DAYS_TO_HISTORIFY_EVENTS_FAIL, new Object[]{e.getMessage(),DEFAULT_DAYS_TO_HISTORIFY}, e);
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
}