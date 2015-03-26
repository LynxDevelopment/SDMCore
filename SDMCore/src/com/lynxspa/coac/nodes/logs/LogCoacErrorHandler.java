/**
 * 
 */
package com.lynxspa.coac.nodes.logs;

import java.util.Locale;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.processevents.BusinessProcessExceptionEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;

/**
 * 
 * 
 * @author albert.farre
 *
 */
@NodeBeautifier(description="ErrorHandler", category="Log", smallIcon = "../../../../lynxit/fpm/nodes/icons/exception_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/exception_32.gif")
public class LogCoacErrorHandler extends InternalNodeSupport<com.lynxit.fpm.events.processevents.BusinessProcessExceptionEvent> {

	protected String locale=null;
	protected String user=null;
	protected Resource<Session> dataSource=null;

	@Override
	protected void processMessage(BusinessProcessExceptionEvent message) throws Exception {

		SDMConfigManager manager=null;
		Session session=null;
		
		manager=SDMConfigManager.getInstance();
		session=this.dataSource.getCurrentInstance();
		LogUtils.createLog(session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(session), LogErrorDict.UNKOWN_FAIL, new Object[]{message.getEndedProcessId()}, message.getException());
	}

	@ConfigParam(required=true, group="config", description="User ho store the entity")
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true, group="config", description="Name of the datasource messages will be saved to")
    public Resource<Session> getDataSource(){
    	return dataSource;
    }
    public void setDataSource(Resource<Session> _dataSource){
    	this.dataSource = _dataSource;
    }

    @ConfigParam(required=true,group="config", description="locale for the logs")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
}
