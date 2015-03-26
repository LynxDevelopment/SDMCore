package com.lynxspa.coac.authorization.nodes;

import java.util.ArrayList;
import java.util.List;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.coac.authorization.VirtualLogDictBean;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.entities.application.flow.operations.OperableAdapter;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.exception.FPMException;
import com.lynxspa.fpm.nodes.workflow.ChangeStateNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="Authorization Change State", category="CorporateActions Workflow", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class AuthorizationChangeStateNode extends ChangeStateNodeTemplate {

	private CAStatesEVENTGROUPFlow state=null;
	private LogDictAdapter logDict=null;
	
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
	
	@Override
	protected OperableAdapter perform(OperableAdapter message) throws FPMException {

		String[] messageParameters=null;
		List<Object> parameters=null;
		
		//Manually set parameters
		this.logDict=new VirtualLogDictBean(message.getOperationStatus().getTransitionMessageKey());
		messageParameters=message.getOperationStatus().getMessageParameters();
		if (messageParameters!=null){
			parameters=new ArrayList<Object>(messageParameters.length);
			for(Object ob1:messageParameters)
				parameters.add(ob1);
		}
		setActiveMessageTransition(true);
		setMessageParameters(parameters);
		
		return super.perform(message);
	}

	@Override
	protected LogDictAdapter getLog() throws Exception {
		
		return this.logDict;
	}
}
