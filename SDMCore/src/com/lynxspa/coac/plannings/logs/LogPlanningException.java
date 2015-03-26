package com.lynxspa.coac.plannings.logs;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.dictionaries.LogErrorDict;
import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.fpm.nodes.logs.LogExceptionNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="LogPlanningException", category="Log", smallIcon = "../../../../lynxit/fpm/nodes/icons/exception_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/exception_32.gif")
public class LogPlanningException extends LogExceptionNodeTemplate {

protected LogErrorDict log=null;
	
	@Override
	protected ConfigManager getConfigManager() throws Exception{

		return SDMConfigManager.getInstance();
	}

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
