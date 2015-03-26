package com.lynxspa.coac.prenormalizer.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.prenormalize.PrenormalizerProcessorAdapter;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * @author xavi
 */
@NodeBeautifier(description="Operation Normalizer", category="CorporateActionsCore")
public class OperationNormalizer extends SingleExitInternalNodeSupport<CAEventMessage, CAEventMessage> {
	
	protected Resource<Session> resource=null;
	private String user=null;
	private String locale=null;

	public OperationNormalizer() {
	}
	
	protected CAEventMessage perform(CAEventMessage message) throws FPMException {
		
		final CAEventMessage reply=message;
		Session session=null;
		String normalizedOperation=null;
		
		try{
			session=resource.getCurrentInstance();
			final PrenormalizerProcessorAdapter prenormalizer=(PrenormalizerProcessorAdapter)SDMConfigManager.getInstance().getProcessor(session,CAConfiguration.PRENORMALIZEPROCESSOR);
			normalizedOperation=prenormalizer.prenormalizeOperation(session,message,this.user,this.locale);
			reply.setNormalizedOperation(normalizedOperation);
		}catch(FPMException e){
			if(!reply.isExtension())
				throw e;
		}catch (Exception e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		
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
