package com.lynxspa.coac.monitor.businessprocess;

import com.lynxit.fpm.BusinessProcess;
import com.lynxit.fpm.BusinessProcessInput;
import com.lynxit.fpm.BusinessProcessOutput;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.events.EventListener;
import com.lynxit.fpm.nodes.DynaParamsNode;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxit.fpm.instrumentation.interceptors.Interceptors;
import com.lynxit.utils.ConfigParams;
import com.lynxit.utils.beans.BeanWithRuntimeConfigurableParams;

import java.util.Properties;

/*
 * process 'business process'
 */
@GeneratedType(definitionFile = "src/com/lynxspa/coac/monitor/businessprocess/CacheCleaner.fpmprocess")
public class CacheCleanerProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String locale_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private com.lynxit.fpm.resources.Resource statefullSession_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statefullSession")
	public com.lynxit.fpm.resources.Resource getStatefullSession() {
		return statefullSession_;
	}

	public void setStatefullSession(com.lynxit.fpm.resources.Resource value) {
		this.statefullSession_ = value;
	}

	private String user_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "user")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
	}

	/*
	 * inputs declarations
	 */

	// input 'input'
	// $-- /business process/input
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.Event> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.Event> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public CacheCleanerProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ClearCache
		// instantiate and configure 'ClearCache' node
		com.lynxspa.coac.monitor.nodes.ClearCacheNode clearCacheNode = Interceptors
				.createNode(com.lynxspa.coac.monitor.nodes.ClearCacheNode.class);
		clearCacheNode.setId("ClearCache");
		addNode(clearCacheNode);
		// $-- /business process/ClearCache/

		// $-- /business process/GetClearCacheFlag
		// instantiate and configure 'GetClearCacheFlag' node
		com.lynxspa.coac.monitor.nodes.GetConfigValueNode getClearCacheFlagNode = Interceptors
				.createNode(com.lynxspa.coac.monitor.nodes.GetConfigValueNode.class);
		getClearCacheFlagNode.setId("GetClearCacheFlag");
		addNode(getClearCacheFlagNode);
		// $-- /business process/GetClearCacheFlag/
		getClearCacheFlagNode.setResource(getStatefullSession());
		// $-- /business process/GetClearCacheFlag/
		// $-- /business process/GetClearCacheFlag/
		getClearCacheFlagNode
				.setConfiguration(com.lynxspa.sdm.dictionaries.config.CAConfiguration.CLEARCACHE);

		// $-- /business process/LogCoacException
		// instantiate and configure 'LogCoacException' node
		com.lynxspa.coac.nodes.logs.LogCoacException logCoacExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logCoacExceptionNode.setId("LogCoacException");
		addNode(logCoacExceptionNode);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setDataSource(getStatefullSession());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setLocale(getLocale());
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setUser(getUser());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.MONITOR_CLEARCACHE_ERROR);

		// $-- /business process/MustBeCleaned
		// instantiate and configure 'MustBeCleaned' node
		com.lynxit.fpm.nodes.internal.DecisionNode<java.lang.Object> mustBeCleanedNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.DecisionNode.class);
		mustBeCleanedNode.setId("MustBeCleaned");
		addNode(mustBeCleanedNode);
		// $-- /business process/MustBeCleaned/
		((DynaParamsNode) mustBeCleanedNode).setDynamicParam("condition",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("java.lang.Boolean.valueOf(message)",
								java.lang.Boolean.class));
		// $-- /business process/MustBeCleaned/

		// $-- /business process/ReportCacheClean
		// instantiate and configure 'ReportCacheClean' node
		com.lynxspa.coac.monitor.nodes.ChangeConfigValueNode reportCacheCleanNode = Interceptors
				.createNode(com.lynxspa.coac.monitor.nodes.ChangeConfigValueNode.class);
		reportCacheCleanNode.setId("ReportCacheClean");
		addNode(reportCacheCleanNode);
		// $-- /business process/ReportCacheClean/
		reportCacheCleanNode.setInNewTransaction(false);
		// $-- /business process/ReportCacheClean/
		reportCacheCleanNode.setResource(getStatefullSession());
		// $-- /business process/ReportCacheClean/
		reportCacheCleanNode.setValue("false");
		// $-- /business process/ReportCacheClean/
		// $-- /business process/ReportCacheClean/
		reportCacheCleanNode
				.setConfiguration(com.lynxspa.sdm.dictionaries.config.CAConfiguration.CLEARCACHE);
		// $-- /business process/ReportCacheClean/
		reportCacheCleanNode.setUser("CORE");

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/ClearCache/out/business process/ReportCacheClean/in
		clearCacheNode.connectNodeToOut(reportCacheCleanNode);
		clearCacheNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetClearCacheFlag/out/business process/MustBeCleaned/in
		getClearCacheFlagNode.connectNodeToOut(mustBeCleanedNode);
		// $-- /business process/GetClearCacheFlag/exception/business process/LogCoacException/exc
		getClearCacheFlagNode.connectNodeToException(logCoacExceptionNode);
		mustBeCleanedNode.connectNodeToFalse(getDefaultStopperNode());
		// $-- /business process/MustBeCleaned/true/business process/ClearCache/in
		mustBeCleanedNode.connectNodeToTrue(clearCacheNode);
		mustBeCleanedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		reportCacheCleanNode.connectNodeToOut(getDefaultStopperNode());
		reportCacheCleanNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/GetClearCacheFlag/in
		input.connectNodeToOut(getClearCacheFlagNode);

		clearCacheNode.init();
		getClearCacheFlagNode.init();
		logCoacExceptionNode.init();
		mustBeCleanedNode.init();
		reportCacheCleanNode.init();
	}

}