package com.lynxspa.coac.historics.messages.nodes;

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
import com.lynxspa.sdm.entities.events.messages.CAEventMessageHistoric;
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
@NodeBeautifier(description="HistorificationMessagesNode", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class HistorificationMessagesNode<T extends ScheduledEvent> extends InternalNodeSupport<T>{
	
    private static final Logger logger = Logger.getLogger(HistorificationMessagesNode.class);

	private static final long DEFAULT_DAYS_TO_HISTORIFY = 30;

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private Integer historificationCommitSize=0;
	private String locale=null;

	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		
		Query eventMessageQuery = null;
		CAEventMessageHistoric messageHistoric = null;
		long daysTohistorify = 0l;
		Calendar historificableDate=null;
		SDMConfigManager manager=null;
		Session statefullSession=null;
		StatelessSession statelessSession=null;
		long processingTime=0l;
		long proccessedCorrectly=0;
		long proccessedWrong=0;
		int rowsMarkedForHistorification=0;
		int messagesHistorified=0;
		int fieldMessagesHistorified=0;
		int historifiedMessageFieldsUpdated=0;
		int messagesDeleted=0;
		int messageFieldsDeleted=0;
		int historifiedMessagesUpdated=0;
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
			eventMessageQuery=statelessSession.createQuery("update CAEventMessage set auditor.deleted=:deleted where auditor.creationDate<=:calculatedDate");
			eventMessageQuery.setParameter("deleted", true);
			eventMessageQuery.setParameter("calculatedDate",historificableDate.getTime());
			rowsMarkedForHistorification=eventMessageQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			logger.info("Affected rows: "+rowsMarkedForHistorification);
			if(rowsMarkedForHistorification>0){
				HibernateUtils.beguinTransaction(statelessSession);
				logger.info("Recovering CAEventMessage first id");
				eventMessageQuery=statelessSession.createQuery("select id from CAEventMessage where auditor.deleted=:deleted order by id asc");
				eventMessageQuery.setParameter("deleted",true);
				eventMessageQuery.setFetchSize(1);
				eventMessageQuery.setMaxResults(1);
				initialId=(Long)eventMessageQuery.uniqueResult();
				logger.info("First id: "+initialId);
				logger.info("Recovering CAEventMessage last id");
				eventMessageQuery=statelessSession.createQuery("select id from CAEventMessage where auditor.deleted=:deleted order by id desc");
				eventMessageQuery.setParameter("deleted",true);
				eventMessageQuery.setFetchSize(1);
				eventMessageQuery.setMaxResults(1);
				lastId=(Long)eventMessageQuery.uniqueResult();
				logger.info("Last id: "+lastId);
				HibernateUtils.commitTransaction(statelessSession);
				for(long maxId=initialId+this.historificationCommitSize;initialId<lastId;maxId+=this.historificationCommitSize,initialId+=this.historificationCommitSize){
					logger.info("Historifing from "+initialId+" to "+maxId);
					HibernateUtils.beguinTransaction(statelessSession);
					messagesHistorified=0;
					fieldMessagesHistorified=0;
					historifiedMessageFieldsUpdated=0;
					messagesDeleted=0;
					messageFieldsDeleted=0;
					historifiedMessagesUpdated=0;
					try{
						logger.info("	Saving CAEventMessageHistoric from CAEventMessage");
						eventMessageQuery=statelessSession.createQuery("" +
								"insert into CAEventMessageHistoric " +
									"(" +
									"	id," +
									"	normalizedEvent," +
									"	originId," +
									"	originName," +
									"	originType," +
									"	originPosition," +
									"	messageId," +
									"	messageType," +
									"	eventType," +
									"	operation," +
									"	sender," +
									"	receiver," +
									"	eventReference," +
									"	eventMessageReference," +
									"	previousEventMessageReference," +
									"	securityType," +
									"	security," +
									"	securityName," +
									"	announceDate," +
									"	effectiveDate," +
									"	account," +
									"	amount," +
									"	originalMessage," +
									"	extension," +
									"	extensionReference," +
									"	senderTimestamp," +
									"	mainMarket, " +
									"	inCustomerPortfolio, " +
									"	normalizedEventType," +
									"	normalizedSecurity," +
									"	normalizedOperation," +
									"	eventProvider, " +
									"	version," +
									"	auditor," +
									"	operationStatus) "+
								"select " +
									"	eventMessage.id," +
									"	eventMessage.normalizedEvent," +
									"	eventMessage.originId," +
									"	eventMessage.originName," +
									"	eventMessage.originType," +
									"	eventMessage.originPosition," +
									"	eventMessage.messageId," +
									"	eventMessage.messageType," +
									"	eventMessage.eventType," +
									"	eventMessage.operation," +
									"	eventMessage.sender," +
									"	eventMessage.receiver," +
									"	eventMessage.eventReference," +
									"	eventMessage.eventMessageReference," +
									"	eventMessage.previousEventMessageReference," +
									"	eventMessage.securityType," +
									"	eventMessage.security," +
									"	eventMessage.securityName," +
									"	eventMessage.announceDate," +
									"	eventMessage.effectiveDate," +
									"	eventMessage.account," +
									"	eventMessage.amount," +
									"	eventMessage.originalMessage," +
									"	eventMessage.extension," +
									"	eventMessage.extensionReference," +
									"	eventMessage.senderTimestamp," +
									" 	eventMessage.mainMarket, " +
									" 	eventMessage.inCustomerPortfolio, " +
									"	eventMessage.normalizedEventType," +
									"	eventMessage.normalizedSecurity," +
									"	eventMessage.normalizedOperation," +
									"	eventMessage.eventProvider, "+
									"	eventMessage.version," +
									"	eventMessage.auditor," +
									"	eventMessage.operationStatus "+
								"from CAEventMessage as eventMessage " +
								"where eventMessage.auditor.deleted=:deleted and eventMessage.id>=:initialId and eventMessage.id<:endId");
						eventMessageQuery.setParameter("deleted",true);
						eventMessageQuery.setParameter("initialId",initialId);
						eventMessageQuery.setParameter("endId",maxId);
						messagesHistorified=eventMessageQuery.executeUpdate();
						if (messagesHistorified>0){
							logger.info("	Recovering last CAEventMessageHistoric");
							eventMessageQuery=statelessSession.createQuery("from CAEventMessageHistoric order by id desc");
							eventMessageQuery.setFetchSize(1);
							eventMessageQuery.setMaxResults(1);
							messageHistoric=(CAEventMessageHistoric)eventMessageQuery.uniqueResult();
							logger.info("	Last CAEventMessageHistoric is "+messageHistoric.getId());
							logger.info("	Saving CAEventMessageFieldHistoric from CAEventMessageField");
							eventMessageQuery=statelessSession.createQuery("" +
									"insert into CAEventMessageFieldHistoric " +
										"(id,message,type,path,valueShort,valueLong,valueVeryLong,valueClob,auditor) " +
									"select " +
										"field.id,(select messageHistoric from CAEventMessageHistoric messageHistoric where messageHistoric.id=:lastHistoricMessageId),field.type,field.path,field.valueShort,field.valueLong,field.valueVeryLong,field.valueClob,field.auditor "+
									"from CAEventMessageField as field " +
									"where field.message.auditor.deleted=:deleted and field.message.id>=:initialId and field.message.id<:endId");
							eventMessageQuery.setParameter("lastHistoricMessageId",messageHistoric.getId());
							eventMessageQuery.setParameter("deleted",true);
							eventMessageQuery.setParameter("initialId",initialId);
							eventMessageQuery.setParameter("endId",maxId);
							fieldMessagesHistorified=eventMessageQuery.executeUpdate();
							logger.info("	Updating historified CAEventMessageField with correct message");
							eventMessageQuery=statelessSession.createQuery("update CAEventMessageFieldHistoric eventMessageFieldHistoric set eventMessageFieldHistoric.message.id=(select eventMessageField.message.id from CAEventMessageField eventMessageField where eventMessageField.id=eventMessageFieldHistoric.id) where eventMessageFieldHistoric.message=:lastHistoricMessage");
							eventMessageQuery.setParameter("lastHistoricMessage",messageHistoric);
							historifiedMessageFieldsUpdated=eventMessageQuery.executeUpdate();
							logger.info("	Historified CAEventMessageField updated");
							logger.info("	Deleting historified CAEventMessageField");
							eventMessageQuery=statelessSession.createQuery("delete CAEventMessageField as field where field.message.id in (select eventMessage.id from CAEventMessage as eventMessage where eventMessage.auditor.deleted=:deleted and eventMessage.id>=:initialId and eventMessage.id<:endId)");
							eventMessageQuery.setParameter("deleted", true);
							eventMessageQuery.setParameter("initialId",initialId);
							eventMessageQuery.setParameter("endId",maxId);
							messageFieldsDeleted=eventMessageQuery.executeUpdate();
							logger.info("	Deleting historified CAEventMessage");
							eventMessageQuery=statelessSession.createQuery("delete CAEventMessage as eventMessage where eventMessage.auditor.deleted=:deleted and eventMessage.id>=:initialId and eventMessage.id<:endId");
							eventMessageQuery.setParameter("deleted", true);
							eventMessageQuery.setParameter("initialId",initialId);
							eventMessageQuery.setParameter("endId",maxId);
							messagesDeleted=eventMessageQuery.executeUpdate();
							logger.info("	Updating historified CAEventMessage with setting delete to false");
							eventMessageQuery=statelessSession.createQuery("update CAEventMessageHistoric eventMessage set eventMessage.auditor.deleted=:deletedFalse where eventMessage.auditor.deleted=:deletedTrue and eventMessage.id>=:initialId and eventMessage.id<:endId");
							eventMessageQuery.setParameter("deletedFalse",false);
							eventMessageQuery.setParameter("deletedTrue",true);
							eventMessageQuery.setParameter("initialId",initialId);
							eventMessageQuery.setParameter("endId",maxId);
							historifiedMessagesUpdated=eventMessageQuery.executeUpdate();
							logger.info("	Historified CAEventMessage updated");
							HibernateUtils.commitTransaction(statelessSession);
							logger.info("Historification from "+initialId+" to "+maxId+" ends correctly");
							proccessedCorrectly+=messagesHistorified;
						}else{
							logger.warn("Messages between "+initialId+" and "+maxId+" do not exists. No action to do");
						}
					}catch (Exception e) {
						logger.error("Historification from "+initialId+" to "+maxId+" fail",e);
						HibernateUtils.rollbackTransaction(statelessSession);
						LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogErrorDict.HISTORIFICATION_BLOCK_FAIL, new Object[]{initialId,maxId,messagesHistorified,fieldMessagesHistorified,historifiedMessageFieldsUpdated,messageFieldsDeleted,messagesDeleted,historifiedMessagesUpdated}, e);
						proccessedWrong+=messagesHistorified;
					}
				}
			}
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