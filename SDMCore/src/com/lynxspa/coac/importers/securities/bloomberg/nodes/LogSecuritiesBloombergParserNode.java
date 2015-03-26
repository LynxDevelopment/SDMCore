package com.lynxspa.coac.importers.securities.bloomberg.nodes;

import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;

@NodeBeautifier(description="LogBloombergSecurityBean", category = "Log", smallIcon="../../../../../lynxit/fpm/nodes/icons/log_16.gif", largeIcon="../../../../lynxit/fpm/nodes/icons/log_32.gif")
public class LogSecuritiesBloombergParserNode <T extends BloombergSecurityBean> extends InternalNodeSupport<T>{

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	
	@Override
	protected void processMessage(T message) throws Exception {

		SDMConfigManager manager=null;
		Session statefullSession=null;
		
		manager=SDMConfigManager.getInstance();
		statefullSession=this.resource.getCurrentInstance();
		
		LogUtils.createLog(statefullSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statefullSession), LogInfoDict.SECURITY_DIF_INTERESTING, new Object[]{message.getName(),message.getIdIsin()},null);
	}

	@ConfigParam(required=true)
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> _resource) {
		this.resource = _resource;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}
	public void setUser(String _user) {
		this.user = _user;
	}
	
	@ConfigParam(required=true)
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}
	public void setResourceStateless(Resource<StatelessSession> _resourceStateless) {
		this.resourceStateless = _resourceStateless;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String _locale) {
		this.locale = _locale;
	}
}
