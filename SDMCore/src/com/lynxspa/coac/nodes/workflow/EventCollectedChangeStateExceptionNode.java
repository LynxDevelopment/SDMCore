package com.lynxspa.coac.nodes.workflow;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.fpm.nodes.workflow.ExceptionChangeStateNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="EventCollected Exception Change State", category="CorporateActions Workflow", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class EventCollectedChangeStateExceptionNode extends ExceptionChangeStateNodeTemplate {

	private CAStatesEVENTCOLLECTEDFlow state=null;
	
	@Override
	protected ConfigManager getConfigManager() throws Exception {
		return SDMConfigManager.getInstance();
	}

	@ConfigParam(required=true,description="new state",group="config")
	public CAStatesEVENTCOLLECTEDFlow getState() {
		return this.state;
	}
	public void setState(CAStatesEVENTCOLLECTEDFlow state) {
		this.state = state;
	}
	@Override
	protected StatesDictAdapter getNewState() throws Exception {
		return this.state;
	}
}
