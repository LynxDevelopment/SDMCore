package com.lynxspa.coac.plannings.executeshellprocess.nodes;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.OutputNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.sdm.processors.plannings.ExecuteCommandAdapter;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Execute Shell", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class ExecuteShellProcessOutputNode extends OutputNodeSupport<Object> {
	

	private final Logger logger = Logger.getLogger(ExecuteShellProcessOutputNode.class);
	private String fileName;

	protected String locale=null;
	protected String user=null;
	protected Resource<Session> resource=null;
	
	public void process(Object message) throws Exception {
		SDMConfigManager manager=null;
		Session session=null;
		String ejecutionResult=null;
		ExecuteCommandAdapter commandProcessor = null;
		try{
			session=this.resource.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.EXECUTE_SHELL_START, new Object[]{this.fileName},null);
			commandProcessor=(ExecuteCommandAdapter)manager.getProcessor(session,CAConfiguration.EXECUTECOMMANDPROCESSOR);
			ejecutionResult = commandProcessor.executeCommand(this.fileName, (String)manager.getConfiguration(session,CAConfiguration.SYSTEM));
			
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.EXECUTE_SHELL_END, new Object[]{this.fileName, ejecutionResult},null);
			logger.debug("Resultado ejecucion de Shell: " + ejecutionResult);
			
		}catch(Exception e){
			logger.error("Error ejecutando shell "+fileName+ ": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}

	}

	@ConfigParam(required = true, description="File with shell to execute",dynamic=true)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@ConfigParam(required = true, description="Locale")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required = true, description="User")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required = true, description="Resource")
	public Resource<Session> getResource() {
		return resource;
	}

	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

	
}
