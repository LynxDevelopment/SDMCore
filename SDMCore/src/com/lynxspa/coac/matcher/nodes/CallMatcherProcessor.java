/**
 * 
 */
package com.lynxspa.coac.matcher.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.entities.events.CAEventCollected;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.matcher.MatcherProcessorAdapter;
import com.lynxspa.exception.FPMException;


/**
 * Call Matcher Processor <br>
 * if matcher fails lauches an exception<br>
 * @author albert
 */
@NodeBeautifier(description="Call Matcher Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class CallMatcherProcessor extends SingleExitInternalNodeSupport<CAEventCollected, CAEventCollected> {

	private String user=null;
	private String locale=null;
	private Resource<Session> resource=null;
	
	
	/**
	 * @see com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport#perform(java.lang.Object)
	 */
	@Override
	protected CAEventCollected perform(CAEventCollected message) throws Exception {

		CAEventCollected reply=null;
		MatcherProcessorAdapter matchProcess=null;
		SDMConfigManager manager=null;
		Session session=null;
		CAEventGroup group=null;

		reply=message;
		session=resource.getCurrentInstance();
		manager=SDMConfigManager.getInstance();
		matchProcess=(MatcherProcessorAdapter)manager.getProcessor(session,CAConfiguration.MATCHERPROCESSOR);
		group=matchProcess.findGroupMember(session,message);
		if(group==null)
			throw new FPMException(LogErrorDict.CANT_GROUP_EVENT,new Object[]{message.getId()});
		message.setEventGroup(group);
		
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
