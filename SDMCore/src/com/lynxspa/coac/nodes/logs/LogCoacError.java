/**
 * 
 */
package com.lynxspa.coac.nodes.logs;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.fpm.nodes.logs.LogNodeTemplate;
import com.lynxspa.managers.ConfigManager;

/**
 * 
 * 
 * @author albert.farre
 *
 */
@NodeBeautifier(description="LogDB Error", category="Log", smallIcon = "../../../../lynxit/fpm/nodes/icons/exception_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/exception_32.gif")
public class LogCoacError<T> extends LogNodeTemplate<T> {

	protected LogErrorDict log=null;

	@Override
	protected ConfigManager getConfigManager() throws Exception {

		return SDMConfigManager.getInstance();
	}

	/* (non-Javadoc)
	 * @see com.lynxspa.fpm.nodes.logs.LogNodeTemplate#getInternalLog()
	 */
	@Override
	protected LogDictAdapter getInternalLog() {
		return this.log;
	}

	@ConfigParam(required=true, group="log", description="log")
	public LogErrorDict getLog() {
		return log;
	}
	public void setLog(LogErrorDict log) {
		this.log = log;
	}
}
