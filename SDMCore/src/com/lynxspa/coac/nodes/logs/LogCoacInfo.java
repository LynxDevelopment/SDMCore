/**
 * 
 */
package com.lynxspa.coac.nodes.logs;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.fpm.nodes.logs.LogNodeTemplate;
import com.lynxspa.managers.ConfigManager;

/**
 * @author albert.farre
 */
@NodeBeautifier(description="LogDB Info", category = "Log", smallIcon="../../../../lynxit/fpm/nodes/icons/log_16.gif", largeIcon="../../../../lynxit/fpm/nodes/icons/log_32.gif")
public class LogCoacInfo extends LogNodeTemplate<Object> {

	protected LogInfoDict log=null;

	@Override
	protected ConfigManager getConfigManager() throws Exception{

		return SDMConfigManager.getInstance();
	}

	@Override
	protected LogDictAdapter getInternalLog() {

		return this.log;
	}

	@ConfigParam(required=true, group="log", description="log")
	public LogInfoDict getLog() {
		return log;
	}
	public void setLog(LogInfoDict log) {
		this.log = log;
	}
}