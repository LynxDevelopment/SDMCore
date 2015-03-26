package com.lynxspa.coac.normalizer.nodes;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.CAOperation;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.coac.normalizer.adapters.NormalizableAdapter;
import com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter;
import com.lynxspa.exception.FPMException;

/**
 * Find previous event
 * @author albert.farre
 */
@NodeBeautifier(description="Find Previous Event", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/db_input_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/db_input_32.gif")
public class FindPreviousEventNode extends SingleExitInternalNodeSupport<NormalizableAdapter,NormalizableUpdateAdapter> {

	private boolean onNotFoundFail=false;
	private Resource<Session> resource=null;
	
	
	@Override
	protected NormalizableUpdateAdapter perform(NormalizableAdapter _message) throws Exception {
		
		NormalizableUpdateAdapter reply=null;
		Session session=null;
		CAEventMessage previousMessage=null;
		CAEventCollected eventCollected=null;
		
		try{
			session=this.resource.getCurrentInstance();
			reply=(NormalizableUpdateAdapter)_message;
			//Recover Previous message
			if(_message.getMessage().isExtension()){
				previousMessage=getExtensionReferenceEventMessage(_message.getMessage());
			}else{
				previousMessage=getPreviousNewEventMessage(_message.getMessage());
			}
			//Recover previous event
			if((eventCollected=getPreviousEvent(session,_message,previousMessage))!=null){
				reply.setPreviousEventCollected(eventCollected);
				reply.setEventCollected(eventCollected);
				session.refresh(eventCollected.getEventDetail());
			}
		}catch(FPMException e){
			throw e;
		}catch(Exception e){
			throw new FPMException(LogErrorDict.NORMALIZATION_FAIL,e);
		}
		
		return reply;
	}
	
	protected CAEventCollected getPreviousEvent(final Session _session,final NormalizableAdapter _message,final CAEventMessage _previousMessage) throws FPMException{
		
		CAEventCollected reply=null;
		
		if((this.onNotFoundFail)&&(_previousMessage==null))
			throw new FPMException(LogErrorDict.PREVIOUS_NEW_MESSAGE_NOT_FOUND,new Object[]{_message.getMessage().getId()});
		if(_previousMessage!=null){
			reply=(CAEventCollected)_session.get(CAEventCollected.class,_previousMessage.getNormalizedEvent(),LockMode.READ);
			if((this.onNotFoundFail)&&(reply==null))
				throw new FPMException(LogErrorDict.PREVIOUS_NEW_MESSAGE_NOT_NORMALIZED,new Object[]{_message.getMessage().getId(),_previousMessage.getId()});
		}
		
		return reply;
	}
	
	protected CAEventMessage getPreviousNewEventMessage(final CAEventMessage _message) throws FPMException{
		
		CAEventMessage reply=null;
		Session session=null;
		Query query=null;

		try{
			session=this.resource.getCurrentInstance();
			query=session.createQuery("from CAEventMessage where operationStatus.state.id.code=:state and normalizedSecurity=:security and normalizedEventType=:eventType and normalizedOperation in (:operations) and messageType.id.format=:format and messageType.id.code=:messageType and eventReference=:eventReference and auditor.deleted=:isDeleted order by normalizedOperation asc");
			query.setString("state",CAStatesEVENTMESSAGEFlow.NORM.getId());
			query.setEntity("security",_message.getNormalizedSecurity());
			query.setEntity("eventType",_message.getNormalizedEventType());
			query.setParameterList("operations", new Object[]{CAOperation.NEW.getCode(),CAOperation.REPEAT.getCode(),CAOperation.UPDATE.getCode()});
			query.setEntity("format",_message.getMessageType().getId().getFormat());
			query.setString("messageType",_message.getMessageType().getId().getCode());
			query.setString("eventReference",_message.getEventReference());
			query.setBoolean("isDeleted",false);
			query.setFetchSize(1);
			query.setMaxResults(1);
			reply=(CAEventMessage)query.uniqueResult();
		}catch(Exception e){
			throw new FPMException(LogErrorDict.NORMALIZATION_FAIL,e);
		}
		
		return reply;
	}
	
	protected CAEventMessage getExtensionReferenceEventMessage(CAEventMessage _message) throws FPMException{
		
		CAEventMessage reply=null;
		Session session=null;
		Query query=null;

		try{
			session=this.resource.getCurrentInstance();
			query=session.createQuery("" +
					"from CAEventMessage " +
					"	where " +
					"		operationStatus.state.id.code=:state " +
					"		and normalizedSecurity=:security " +
					"		and normalizedEventType=:eventType " +
					"		and normalizedOperation in (:operations) " +
					"		and messageType.id.format=:format " +
					"		and extensionReference=:previousEventMessageReference " +
					"		and auditor.deleted=:isDeleted " +
					"		and extension=:isExtension " +
					"order by normalizedOperation asc");
			query.setString("state",CAStatesEVENTMESSAGEFlow.NORM.getId());
			query.setEntity("security",_message.getNormalizedSecurity());
			query.setEntity("eventType",_message.getNormalizedEventType());
			query.setParameterList("operations", new Object[]{CAOperation.NEW.getCode(),CAOperation.REPEAT.getCode(),CAOperation.UPDATE.getCode()});
			query.setEntity("format",_message.getMessageType().getId().getFormat());
			query.setString("previousEventMessageReference",_message.getPreviousEventMessageReference());
			query.setBoolean("isDeleted",false);
			query.setBoolean("isExtension",false);
			query.setFetchSize(1);
			query.setMaxResults(1);
			reply=(CAEventMessage)query.uniqueResult();
		}catch(Exception e){
			throw new FPMException(LogErrorDict.NORMALIZATION_FAIL,e);
		}
		
		return reply;
	}
	
	
	@ConfigParam(required=true, group="config",defaultValueMode=ValueMode.REFERENCE)
	public Resource<Session> getResource(){
		return resource;
	}
	public void setResource(Resource<Session> _resource){
		this.resource = _resource;
	}

	@ConfigParam(required=true, group="config", defaultValue="false",defaultValueMode=ValueMode.FIXED)
	public boolean isOnNotFoundFail() {
		return onNotFoundFail;
	}
	public void setOnNotFoundFail(boolean onNotFoundFail) {
		this.onNotFoundFail = onNotFoundFail;
	}
}
