package com.lynxspa.coac.importers.securities.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.fileevents.FileEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.utils.VirtualSecurityUtils;
import com.lynxspa.hbt.extensions.StandardStatelessSession;

@NodeBeautifier(description="VirtualSecurities Replacement", category="CorporateActionsCore")
public class VirtualSecurityReplacementNode extends InternalNodeSupport<FileEvent> {

	private String user=null;
	private Resource<StandardStatelessSession> resource=null;
	private Resource<Session> stateFullResource=null;
	private String locale=null;
	@Override
	protected void processMessage(FileEvent message) throws Exception {
		Session session = null;

		session = stateFullResource.getCurrentInstance();
		VirtualSecurityUtils.replaceVirtualSecurities(session, this.user, this.locale);

	}
	
	@ConfigParam(required=true, group="config", description="User ho store the entity")
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true, group="config", description="Name of the datasource messages will be saved to")
    public Resource<StandardStatelessSession> getResource(){
    	return resource;
    }
    public void setResource(Resource<StandardStatelessSession> _Resource){
    	this.resource = _Resource;
    }

    @ConfigParam(required=true, group="config", description="Name of the datasource Config Manager will be retrieved with")
	public Resource<Session> getStateFullResource() {
		return stateFullResource;
	}
	public void setStateFullResource(Resource<Session> stateFullResource) {
		this.stateFullResource = stateFullResource;
	}

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}


}
