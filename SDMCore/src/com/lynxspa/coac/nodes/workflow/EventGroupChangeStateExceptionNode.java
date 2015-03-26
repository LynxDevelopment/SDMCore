package com.lynxspa.coac.nodes.workflow;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.fpm.nodes.workflow.ExceptionChangeStateNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="EventGroup Exception Change State", category="CorporateActions Workflow", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class EventGroupChangeStateExceptionNode extends ExceptionChangeStateNodeTemplate {

	private CAStatesEVENTGROUPFlow state=null;
	
	@Override
	protected ConfigManager getConfigManager() throws Exception {
		return SDMConfigManager.getInstance();
	}

	@ConfigParam(required=true,description="new state",group="config")
	public CAStatesEVENTGROUPFlow getState() {
		return this.state;
	}
	public void setState(CAStatesEVENTGROUPFlow state) {
		this.state = state;
	}
	@Override
	protected StatesDictAdapter getNewState() throws Exception {
		return this.state;
	}
}
