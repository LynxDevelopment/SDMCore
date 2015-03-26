package com.lynxspa.sdm.importers.bloomberg.fields.nodes;

import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynspa.sdm.jobs.bloomberg.load.fields.ImportBlooomberFieldsJob;
import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.events.fileevents.FileCreatedEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;


/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="SDMNormalizer", category="SDM", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class SDMFieldsBloombergImporter<T> extends InternalNodeSupport<T>{
	
	protected Resource<Session> resource=null;
	protected Resource<StatelessSession> statelessResource=null;
	private String user=null;
	private String locale=null;


	public SDMFieldsBloombergImporter() {
	}

	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws Exception {
		
		Session session=null;
		StatelessSession statelessSession=null;
		session=resource.getNewInstance();
		statelessSession = session.getSessionFactory().openStatelessSession();
		final ImportBlooomberFieldsJob importFields = new ImportBlooomberFieldsJob();
		importFields.importBloombergFields(session, statelessSession, ((FileCreatedEvent)_message).getFile(), this.user, this.locale);
		
	}

	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

	
	@ConfigParam(required = true, description = "StatelssSession",group="config")
	public Resource<StatelessSession> getStatelessResource() {
		return statelessResource;
	}
	public void setStatelessResource(Resource<StatelessSession> statelessResource) {
		this.statelessResource = statelessResource;
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

