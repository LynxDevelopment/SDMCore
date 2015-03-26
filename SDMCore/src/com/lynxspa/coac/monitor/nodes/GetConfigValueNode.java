package com.lynxspa.coac.monitor.nodes;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.fpm.nodes.configuration.GetConfigValueNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="Get Config Value", category="CorporateActionsCore")
public class GetConfigValueNode extends GetConfigValueNodeTemplate {

	@Override
	protected ConfigManager getConfigManager() throws Exception {
		return SDMConfigManager.getInstance();
	}
	
	@ConfigParam(required = true, description = "Configuration",group="config")
	public CAConfiguration getConfiguration() {
		return (CAConfiguration)configuration;
	}
	public void setConfiguration(CAConfiguration configuration) {
		this.configuration = configuration;
	}
}
