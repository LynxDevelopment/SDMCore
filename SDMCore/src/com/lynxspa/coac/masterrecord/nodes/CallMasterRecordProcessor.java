/**
 * 
 */
package com.lynxspa.coac.masterrecord.nodes;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.masterrecord.MRProcessorAdapter;
import com.lynxspa.sdm.processors.masterrecord.MasterRecordSelection;
import com.lynxspa.utils.UtilDate;


/**
 * Call Master record Processor <br>
 * if master record generates error throws it<br>
 * @author albert
 */
@NodeBeautifier(description="Call MasterRecord Processor", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class CallMasterRecordProcessor extends SingleExitInternalNodeSupport<CAEventGroup, CAEventGroup> {

	public static String MASTERRECORD_ALERT_MESSAGE="master.record.alert.message";
	
	
	private String user=null;
	private String locale=null;
	private Resource<Session> resource=null;
	private int entityDeadLineDays = 0;
	
	
	/**
	 * @see com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport#perform(java.lang.Object)
	 */
	@Override
	protected CAEventGroup perform(CAEventGroup message) throws Exception {

		CAEventGroup reply=null;
		MRProcessorAdapter masterRecordProcessor=null;
		SDMConfigManager manager=null;
		Session session=null;
		MasterRecordSelection selection=null;

		reply=message;
		session=resource.getCurrentInstance();
		manager=SDMConfigManager.getInstance();
		masterRecordProcessor=(MRProcessorAdapter)manager.getProcessor(session,CAConfiguration.MRPROCESSOR);
		selection=masterRecordProcessor.findMasterRecord(session,message);
		message.setMasterEvent(selection.getMasterRecordSelected());
		if (message.getEntityDeadLine()==null)
			message.setEntityDeadLine(UtilDate.calculateRelativeDate(message.getExpirationDate(),-entityDeadLineDays,0,0));
		
		if((selection.getAlerts()!=null)&&(selection.getAlerts().size()>0)){
			this.getBusinessProcess().setContextAttribute(CallMasterRecordProcessor.MASTERRECORD_ALERT_MESSAGE, selection.getAlerts().get(0));
		}else{
			this.getBusinessProcess().setContextAttribute(CallMasterRecordProcessor.MASTERRECORD_ALERT_MESSAGE,null);
		}
		
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

	@ConfigParam(required=true, group="config",defaultValue="3", description="Days between entity operational date (deadLine) and real operational date")
	public int getEntityDeadLineDays() {
		return entityDeadLineDays;
	}
	public void setEntityDeadLineDays(int entityDeadLineDays) {
		this.entityDeadLineDays = entityDeadLineDays;
	}
	
	
}
