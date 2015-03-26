/**
 * 
 */
package com.lynxspa.coac.normalizer.nodes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtended;
import com.lynxspa.sdm.entities.events.details.CAEventDetailExtensionId;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.normalizer.adapters.NormalizableAdapter;
import com.lynxspa.sdm.processors.normalize.NormalizeProcessorAdapter;
import com.lynxspa.sdm.processors.normalize.NormalizeResultBean;
import com.lynxspa.exception.FPMException;


/**
 * Call Normalizer Processor <br>
 * if normalization fails log an error and launch an exception<br>
 * 
 * @author albert
 *
 */
@NodeBeautifier(description="Call Normalizer Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class CallNormalizeProcessor extends SingleExitInternalNodeSupport<NormalizableAdapter, NormalizableAdapter> {

	private String user=null;
	private String locale=null;
	private Resource<Session> resource=null;
	
	
	/**
	 * @see com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport#perform(java.lang.Object)
	 */
	@Override
	protected NormalizableAdapter perform(NormalizableAdapter message) throws Exception {

		NormalizableAdapter reply=null;
		NormalizeResultBean result=null;
		NormalizeProcessorAdapter normalizeProcess=null;
		SDMConfigManager manager=null;
		Session session=null;
		List<CAEventDetailExtended> extendedDetails=null;

		reply=message;
		session=resource.getCurrentInstance();
		manager=SDMConfigManager.getInstance();
		normalizeProcess=(NormalizeProcessorAdapter)manager.getProcessor(session,CAConfiguration.NORMALIZEPROCESSOR);
		result=normalizeProcess.normalize(session,message.getMessage());
		if(result==null)
			throw new FPMException(LogErrorDict.NORMALIZATION_RESULT_NULL,new Object[]{message.getMessage().getId()});
		if(result.getException()!=null)
			throw new FPMException(LogErrorDict.NORMALIZATION_SCRIPTING_ERROR,new Object[]{message.getMessage().getId()},result.getException());
		reply.setEventDetail(result.getDetail());
		extendedDetails=new ArrayList<CAEventDetailExtended>();
		for(CAEventDetailExtensionId extensionId:result.getExtendedDetails().keySet()){
			extendedDetails.add(result.getExtendedDetails().get(extensionId));
		}
		reply.setEventDetailExtended(extendedDetails);
		
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
