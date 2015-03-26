/**
 * 
 */
package com.lynxspa.coac.authorization.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.authorize.AuthorizeProcessorAdapter;


/**
 * Call authorizer Processor <br>
 * if authorizer generates error throws it<br>
 * @author albert
 */
@NodeBeautifier(description="Call Authorization Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class CallAuthorizationProcessor extends InternalNodeSupport<CAEventGroup> {

	private OutputConnectable< ? super CAEventGroup> nodeConnectedToTrue;
	private OutputConnectable< ? super CAEventGroup> nodeConnectedToFalse;
	
	private String user=null;
	private String locale=null;
	private Resource<Session> resource=null;
	
	
	/**
	 * @see com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport#perform(java.lang.Object)
	 */
	@Override
	protected void processMessage(CAEventGroup message) throws Exception {

		AuthorizeProcessorAdapter authorizeProcessor=null;
		SDMConfigManager manager=null;
		Session session=null;

		session=resource.getCurrentInstance();
		manager=SDMConfigManager.getInstance();
		authorizeProcessor=(AuthorizeProcessorAdapter)manager.getProcessor(session,CAConfiguration.AUTHORIZEPROCESSOR);
		if(authorizeProcessor.authorizeMasterRecord(session,message)){
			this.getNodeConnectedToTrue().process(message);
		}else{
			this.getNodeConnectedToFalse().process(message);
		}
	}

	
	@ConfigParam(required=true, group="config")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true, group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	@ConfigParam(required=true, group="config")
	public Resource<Session> getResource(){
		return resource;
	}
	public void setResource(Resource<Session> _resource){
		this.resource = _resource;
	}

	public void connectNodeToTrue(OutputConnectable< ? super CAEventGroup> node){
		nodeConnectedToTrue = node;
    }
    public OutputConnectable< ? super CAEventGroup> getNodeConnectedToTrue(){
        return nodeConnectedToTrue;
    }

    public void connectNodeToFalse(OutputConnectable< ? super CAEventGroup> node){
    	nodeConnectedToFalse = node;
    }
    public OutputConnectable< ? super CAEventGroup> getNodeConnectedToFalse(){
        return nodeConnectedToFalse;
    }
}
