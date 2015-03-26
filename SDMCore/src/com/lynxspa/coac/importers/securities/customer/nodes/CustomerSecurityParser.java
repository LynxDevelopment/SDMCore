package com.lynxspa.coac.importers.securities.customer.nodes;

import java.io.FileInputStream;
import java.util.Locale;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.ValueMode;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.importers.securities.beans.SecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.customer.security.CustomerSecurityParserProcessorAdapter;
import com.lynxspa.sdm.processors.customer.security.CustomerSecurityRegisterCallback;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;

@NodeBeautifier(description="CustomerSecurity Parser", category="CorporateActionsCore", smallIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class CustomerSecurityParser extends InternalNodeSupport<FileInputStream> implements CustomerSecurityRegisterCallback {

	private static final String customerFormat="CUSTOMER";

	private OutputConnectable<? super SecurityBean> nodeConnectedToOut_;
	private Resource<Session> resource=null;
	private String encoding=null;
	private String user=null;
	private String locale=null;


	public CustomerSecurityParser() {
		super();
	}
	
    @Override
    protected void processMessage(FileInputStream _stream) throws FPMException{

    	CustomerSecurityParserProcessorAdapter securityParser=null;
		SDMConfigManager manager=null;
    	Session session=null;
		long processingTime=0l;
		int messagesProcessed=0;
    	
    	try{
	    	session=this.resource.getCurrentInstance();
			manager=SDMConfigManager.getInstance();
	    	securityParser=(CustomerSecurityParserProcessorAdapter)manager.getProcessor(session,CAConfiguration.CUSTOMERSECURITYPARSERPROCESSOR);
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(this.resource.getCurrentInstance()), LogInfoDict.START_SECURITY_PARSE, new Object[]{CustomerSecurityParser.customerFormat,this.getBusinessProcess().getContextAttribute("fileName")},null);
			processingTime=System.currentTimeMillis();
			messagesProcessed=securityParser.processFile(session,_stream,this,this.encoding,String.valueOf(this.getBusinessProcess().getContextAttribute("fileName")));
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(this.resource.getCurrentInstance()), LogInfoDict.END_SECURITY_PARSE, new Object[]{CustomerSecurityParser.customerFormat,this.getBusinessProcess().getContextAttribute("fileName"),processingTime,messagesProcessed},null);
    	}catch(FPMException e){
    		throw e;
    	}catch(Exception e){
			throw new FPMException(e);
		}
    }

	public void processRegister(SecurityBean _bean,int _messagePosition) {
	
		try{
			_bean.setOriginName(String.valueOf(this.getBusinessProcess().getContextAttribute("fileName")));
			_bean.setOriginPosition(_messagePosition);
			getNodeConnectedToOut().process(_bean);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@ConfigParam(name="session", description = "Session", group="config", required = true, defaultValueMode=ValueMode.REFERENCE,dynamic=false)
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
	
	@ConfigParam(name="encoding", description = "Encoding to use on file parsing", group="config", required = true, defaultValue="ISO-8859-1", defaultValueMode=ValueMode.FIXED,dynamic=false)
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	@ConfigParam(name="user", description = "User whose doing parsing", group="config", required = true, defaultValue="CUSTOMERPARSER", defaultValueMode=ValueMode.FIXED,dynamic=false)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(name="locale", description = "Locale to use for logging", group="config", required = true, defaultValue="en", defaultValueMode=ValueMode.FIXED,dynamic=false)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void connectNodeToOut(OutputConnectable<? super SecurityBean> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable<? super SecurityBean> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
}
