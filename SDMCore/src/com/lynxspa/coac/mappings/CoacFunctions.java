package com.lynxspa.coac.mappings;

import org.hibernate.Session;

import com.lynxit.fpm.resources.Resource;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxit.fpm.resources.ResourcesManager;
import com.lynxspa.sdm.dictionaries.CALiveCycle;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.flows.CAWorkflow;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.entities.events.adapters.CAEventCollectedAdapter;
import com.lynxspa.sdm.entities.events.adapters.CAEventGroupAdapter;
import com.lynxspa.sdm.entities.events.details.CAEventDetail;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailAdapter;
import com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailExtendedAdapter;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.CAEventMessageField;
import com.lynxspa.sdm.entities.events.messages.adapters.CAEventMessageAdapter;
import com.lynxspa.sdm.entities.events.messages.adapters.CAEventMessageFieldAdapter;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.State;
import com.lynxspa.fpm.functions.FrameworkFunctions;
import com.lynxspa.managers.ConfigManager;


public class CoacFunctions extends FrameworkFunctions{

	public boolean isLiveCycleActive(String _resourceName,CALiveCycle _liveCycle) throws Exception{
		
		Resource<Session> resource=null;

		resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
		return (_liveCycle.getCode()<=((Long)SDMConfigManager.getInstance().getConfiguration(resource.getCurrentInstance(),CAConfiguration.LIVECYCLE)));
	}

	public State getEventMessageInitialState(String _resourceName, CAStatesEVENTMESSAGEFlow _state) throws Exception {
		return super.getInitialState(_resourceName,CAWorkflow.EVENTMESSAGE,_state);
	}
	
	public State getEventCollectedInitialState(String _resourceName, CAStatesEVENTCOLLECTEDFlow _state) throws Exception {
		return super.getInitialState(_resourceName,CAWorkflow.EVENTCOLLECTED,_state);
	}

	public State getEventMasterInitialState(String _resourceName, CAStatesEVENTGROUPFlow _state) throws Exception {
		return super.getInitialState(_resourceName,CAWorkflow.EVENTGROUP,_state);
	}
	
	protected ConfigManager getConfigManager(){
		return SDMConfigManager.getInstance();
	}

	public CAEventMessage toCAEventMessage(CAEventMessageAdapter _message){
		return (CAEventMessage)_message;
	}
	public CAEventMessageField toCAEventMessageField(CAEventMessageFieldAdapter _messageField){
		return (CAEventMessageField)_messageField;
	}
	public CAEventCollected toCAEventCollected(CAEventCollectedAdapter _event){
		return (CAEventCollected)_event;
	}
	public CAEventDetail toCAEventDetail(CAEventDetailAdapter _eventDetail){
		return (CAEventDetail)_eventDetail;
	}
	public CAEventDetailExtended toCAEventDetailExtended(CAEventDetailExtendedAdapter _eventDetail){
		return (CAEventDetailExtended)_eventDetail;
	}
	public CAEventGroup toCAEventGroup(CAEventGroupAdapter _eventGroup){
		return (CAEventGroup)_eventGroup;
	}
	
	public boolean isMandatory(CAEventDetail eventDetail, String field){
		if (eventDetail!=null)
			return eventDetail.get(field)!=null?(Boolean)eventDetail.get(field):true;
		else
			return true;
	}
}

