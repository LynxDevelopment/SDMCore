package com.lynxspa.coac.plannings.nodes;

import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.beans.ControlPlanningsBean;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Update Execution Date", category="Corporateactionscore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class UpdateExecutionDate extends SingleExitInternalNodeSupport<ControlPlanningsBean,ControlPlanningsBean> {
	
	public static final Logger logger = Logger.getLogger(UpdateExecutionDate.class.getName());
	private Resource<StatelessSession> resource;
	private Resource<Session> resourceFull;
	protected String locale=null;
	protected String user=null;
	
	public UpdateExecutionDate (){
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected ControlPlanningsBean  perform(ControlPlanningsBean _message) throws Exception {
		ControlPlanningsBean reply=null;
		Session stateFullSession=null;
		Date actualDate = null;
		SDMConfigManager manager=null;
		SPPlanningProcess planningProcess  =null;
		try{
			stateFullSession = resourceFull.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			
			actualDate = _message.getLastExecutionDate();
			
			planningProcess  = (SPPlanningProcess) stateFullSession.load(SPPlanningProcess.class, _message.getId());
			planningProcess.setExecutionDate(actualDate);
			planningProcess.setManual(false);

			LogUtils.createLog(stateFullSession,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resourceFull.getCurrentInstance()), LogPlanningInfo.UPDATED_PLANNING, new Object[]{_message.getName(),_message.getId()},null);
			logger.debug("last execution date of planningProcess with id "+_message.getId()+" updated ");
			
		}catch(FPMException e){
				throw e;
		}catch (Exception e) {
			logger.debug("Unexpected error: "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		
		return reply;
	}

	@ConfigParam(required = true, description = "StatelessSession",group="config")
	public Resource<StatelessSession> getResource() {
		return resource;
	}
	public void setResource(Resource<StatelessSession> _resource) {
		this.resource = _resource;
	}

	@ConfigParam(required = true, description = "StatefullSession",group="config")
	public Resource<Session> getResourceFull() {
		return resourceFull;
	}

	public void setResourceFull(Resource<Session> resourceFull) {
		this.resourceFull = resourceFull;
	}

	@ConfigParam(required = true, description = "Locale",group="config")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required = true, description = "User",group="config")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
}
