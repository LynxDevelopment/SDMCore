package com.lynxspa.coac.prenormalizer.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.prenormalize.PrenormalizerProcessorAdapter;

/**
 * 
 * @author xavi
 *
 */
@NodeBeautifier(description="Normalize Event Type", category="CorporateActionsCore")
public class EventTypeNormalizer extends SingleExitInternalNodeSupport<CAEventMessage, CAEventMessage> {
	
	protected Resource<Session> resource=null;
	private String user=null;
	private String locale=null;


	public EventTypeNormalizer() {
	}

	protected CAEventMessage perform(CAEventMessage message) throws Exception {
		
		final CAEventMessage reply=message;
		Session session=null;
		CAEventType eventType = null;

		session=resource.getCurrentInstance();
		final PrenormalizerProcessorAdapter prenormalizer=(PrenormalizerProcessorAdapter)SDMConfigManager.getInstance().getProcessor(session,CAConfiguration.PRENORMALIZEPROCESSOR);
		eventType=prenormalizer.prenormalizeEventType(session,reply,this.user,this.locale);
		reply.setNormalizedEventType(eventType);
		
		return reply;
	}

	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

	@ConfigParam(required=true,description="locale",group="config",defaultValue="locale",defaultValueMode=ValueMode.REFERENCE)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@ConfigParam(required=true, group="config", description="User who store the entity",defaultValue="user",defaultValueMode=ValueMode.REFERENCE)
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
