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
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * 
 * 
 * @author albert.farre
 *
 */
@NodeBeautifier(description="Format validator", category="CorporateActionsCore", smallIcon = "../../../lynxit/fpm/nodes/icons/decision_16.gif", largeIcon = "../../../lynxit/fpm/nodes/icons/decision_32.gif")
public class FormatValidatorNode<T> extends InternalNodeSupport<T> {

	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private CAFormat requiredFormat=null;
	private Resource<Session> resource=null;


	public FormatValidatorNode() {
		super();
	}

	
    @Override
    protected void processMessage(T message) throws Exception{

    	if(isFormatInstalled())
    		getNodeConnectedToOut().process(message);
    }
    
	protected boolean isFormatInstalled() throws Exception{

		boolean reply=false;
		
    	try{
    		SDMConfigManager.getInstance().getMessageFormat(resource.getCurrentInstance(),this.requiredFormat.getCode());
    		reply=true;
    	}catch(Exception e){}
    	
    	return reply;
	}
    
    
    @ConfigParam(required = true, description = "Required format",group="config")
	public CAFormat getRrequiredFormat() {
		return requiredFormat;
	}
	public void setRrequiredFormat(CAFormat requiredFormat) {
		this.requiredFormat = requiredFormat;
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
