/**
 * 
 */
package com.lynxspa.coac.normalizer.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.complete.EventCompleteAdapter;


/**
 * Call Normalizer Processor <br>
 * if normalization fails log an error and launch an exception<br>
 * 
 * @author albert
 *
 */
@NodeBeautifier(description="Call Complete Analyzer Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class CallCompleteAnalyzerProcessor extends SingleExitInternalNodeSupport<CAEventCollected, CAEventCollected> {

	private String user=null;
	private String locale=null;
	private Resource<Session> resource=null;
	
	
	/**
	 * @see com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport#perform(java.lang.Object)
	 */
	@Override
	protected CAEventCollected perform(CAEventCollected message) throws Exception {

		CAEventCollected reply=null;
		EventCompleteAdapter completeAnalyzerProcess=null;
		SDMConfigManager manager=null;
		Session session=null;

		reply=message;
		session=resource.getCurrentInstance();
		manager=SDMConfigManager.getInstance();
		completeAnalyzerProcess=(EventCompleteAdapter)manager.getProcessor(session,CAConfiguration.EVENTCOMPLETEPROCESSOR);
		message.setComplete(completeAnalyzerProcess.isEventComplete(session, message));		
		
		return reply;
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
}
