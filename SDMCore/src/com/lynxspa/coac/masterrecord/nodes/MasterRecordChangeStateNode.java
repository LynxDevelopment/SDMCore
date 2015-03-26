package com.lynxspa.coac.masterrecord.nodes;

import java.util.ArrayList;
import java.util.List;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.flow.adapters.StatesDictAdapter;
import com.lynxspa.entities.application.flow.operations.OperableAdapter;
import com.lynxspa.entities.application.logs.utils.LogBean;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.exception.FPMException;
import com.lynxspa.fpm.nodes.workflow.ChangeStateNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="MasterRecord Change State", category="CorporateActions Workflow", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class MasterRecordChangeStateNode extends ChangeStateNodeTemplate {

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
	
	@Override
	protected OperableAdapter perform(OperableAdapter message) throws FPMException {

		LogBean alertMessage=null;
		List<Object> parameters=null;
		
		//Manually set parameters
		alertMessage=(LogBean)this.getBusinessProcess().getContextAttribute(CallMasterRecordProcessor.MASTERRECORD_ALERT_MESSAGE);
		if(alertMessage!=null){
			setActiveMessageTransition(true);
			parameters=new ArrayList<Object>(alertMessage.getParameters().length);
			for(Object ob1:alertMessage.getParameters())
				parameters.add(ob1);
			setMessageParameters(parameters);
		}else{
			setActiveMessageTransition(false);
		}
		
		return super.perform(message);
	}

	@Override
	protected LogDictAdapter getLog() throws Exception {
		
		LogDictAdapter reply=null;
		LogBean alertMessage=null;
		
		alertMessage=(LogBean)this.getBusinessProcess().getContextAttribute(CallMasterRecordProcessor.MASTERRECORD_ALERT_MESSAGE);
		if(alertMessage!=null){
			reply=alertMessage.getMessage();
		}
		
		return reply;
	}
}
