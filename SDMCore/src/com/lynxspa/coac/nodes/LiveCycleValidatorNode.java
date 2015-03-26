/**
 * 
 */
package com.lynxspa.coac.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.CALiveCycle;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * Live cycle validator node, if livecycle is not installed stops business process
 * @author albert.farre
 */
@NodeBeautifier(description="LiveCycle validator", category="CorporateActionsCore", smallIcon = "../../../lynxit/fpm/nodes/icons/decision_16.gif", largeIcon = "../../../lynxit/fpm/nodes/icons/decision_32.gif")
public class LiveCycleValidatorNode<T> extends InternalNodeSupport<T> {

	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private CALiveCycle requiredLiveCycle=null;
	private Resource<Session> resource=null;


	public LiveCycleValidatorNode() {
		super();
	}

	
    @Override
    protected void processMessage(T message) throws Exception{

    	if(this.requiredLiveCycle.getCode()<=((Long)SDMConfigManager.getInstance().getConfiguration(resource.getCurrentInstance(),CAConfiguration.LIVECYCLE)))
    		getNodeConnectedToOut().process(message);
    }
    
	
    @ConfigParam(required = true, description = "Required livecycle",group="config")
	public CALiveCycle getRequiredLiveCycle() {
		return requiredLiveCycle;
	}
	public void setRequiredLiveCycle(CALiveCycle requiredLiveCycle) {
		this.requiredLiveCycle = requiredLiveCycle;
	}

	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
	
	
	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}
