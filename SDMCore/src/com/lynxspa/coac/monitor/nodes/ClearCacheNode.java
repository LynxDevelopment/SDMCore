package com.lynxspa.coac.monitor.nodes;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.exception.FPMException;

/**
 * Clear current cache
 * @author albert
 */
@NodeBeautifier(description="Clear Cache", category="CorporateActionsCore")
public class ClearCacheNode extends SingleExitInternalNodeSupport<Object, Object> {
	
	public ClearCacheNode() {
	}
	
	protected Object perform(Object message) throws FPMException {
		
		try{
			SDMConfigManager.getInstance().initialize();
		}catch (Exception e) {
			throw new FPMException(e);
		}
		
		return message;
	}
}
