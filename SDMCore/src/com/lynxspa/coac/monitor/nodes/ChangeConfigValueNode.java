package com.lynxspa.coac.monitor.nodes;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxspa.sdm.dictionaries.config.CAConfiguration;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.fpm.nodes.configuration.ChangeConfigValueNodeTemplate;
import com.lynxspa.managers.ConfigManager;

@NodeBeautifier(description="Change Config Value", category="CorporateActionsCore")
public class ChangeConfigValueNode extends ChangeConfigValueNodeTemplate {

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
