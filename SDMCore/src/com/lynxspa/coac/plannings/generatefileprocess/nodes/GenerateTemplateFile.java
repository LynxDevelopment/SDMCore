package com.lynxspa.coac.plannings.generatefileprocess.nodes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.beans.PlanningBean;
import com.lynxspa.coac.plannings.logs.LogPlanningError;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Generate Template File", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GenerateTemplateFile <T extends PlanningBean> extends InternalNodeSupport <T> {

	public static final Logger logger = Logger.getLogger(GenerateTemplateFile.class.getName());
	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private File templatePathFile = null;
	private String templateExtension = null;
	protected String locale=null;
	protected String user=null;
	protected Resource<Session> resource=null;
	
	@Override
	protected void processMessage(T message) throws Exception{
		String templateName = null;
		FileOutputStream reply=null;
		File templateFile = null;
		SDMConfigManager manager=null;
		Session session=null;
		
		try{
			session=this.resource.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			
			templateName = message.getOutputFileName().substring(0,message.getOutputFileName().lastIndexOf("."));
			logger.debug("Generating template "+templateName+ "."+ templateExtension);
			templateFile =  new File(this.templatePathFile.getPath()+'/'+templateName + "."+ templateExtension);
			reply = new FileOutputStream(templateFile);
			reply.write(message.getTemplateFile()!=null?message.getTemplateFile():new byte[0]);
			reply.close();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(session), LogPlanningInfo.PLANNINGS_TEMPLATE_GENERATED, new Object[]{templateFile.getPath()},null);

			logger.debug("template "+templateFile.getPath()+ " generated.");
			message.setPathTemplate(templateFile.getPath());
			getNodeConnectedToOut().process(message);
			
		}catch(Exception e){
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), 
					manager.getApplication(session), LogPlanningError.PLANNINGS_TEMPLATE_ERROR, 
					new Object[]{(templateName+ "."+ templateExtension)},e);
			logger.error("Error generating template "+templateName+": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
	}

	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }

	public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }

	@ConfigParam(required=true,description="templatePath")
	public File getTemplatePathFile() {
		return templatePathFile;
	}

	public void setTemplatePathFile(File templatePathFile) {
		this.templatePathFile = templatePathFile;
	}

	@ConfigParam(required=true,description="templateExtension")
	public String getTemplateExtension() {
		return templateExtension;
	}

	public void setTemplateExtension(String templateExtension) {
		this.templateExtension = templateExtension;
	}

	@ConfigParam(required=true,description="Locale")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true,description="User")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true,description="Resource")
	public Resource<Session> getResource() {
		return resource;
	}

	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
	
	
}
