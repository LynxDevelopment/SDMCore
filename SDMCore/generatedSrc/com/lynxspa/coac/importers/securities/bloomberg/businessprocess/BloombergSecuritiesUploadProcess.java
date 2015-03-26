package com.lynxspa.coac.importers.securities.bloomberg.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/bloomberg/businessprocess/BloombergSecuritiesUpload.fpmprocess")
public class BloombergSecuritiesUploadProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer commitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "commitSize")
	public Integer getCommitSize() {
		return commitSize_;
	}

	public void setCommitSize(Integer value) {
		this.commitSize_ = value;
	}

	private Integer inputTimeout_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "inputTimeout")
	public Integer getInputTimeout() {
		return inputTimeout_;
	}

	public void setInputTimeout(Integer value) {
		this.inputTimeout_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param3", required = true, dynamic = true, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private java.lang.Boolean securitiesUploadForceMarketInsert_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "securitiesUploadForceMarketInsert")
	public java.lang.Boolean getSecuritiesUploadForceMarketInsert() {
		return securitiesUploadForceMarketInsert_;
	}

	public void setSecuritiesUploadForceMarketInsert(java.lang.Boolean value) {
		this.securitiesUploadForceMarketInsert_ = value;
	}

	private com.lynxit.fpm.resources.Resource sessionFactory_;

	@ConfigParam(description = "param4", required = true, dynamic = false, name = "sessionFactory")
	public com.lynxit.fpm.resources.Resource getSessionFactory() {
		return sessionFactory_;
	}

	public void setSessionFactory(com.lynxit.fpm.resources.Resource value) {
		this.sessionFactory_ = value;
	}

	private com.lynxit.fpm.resources.Resource statelessSessionFactory_;

	@ConfigParam(description = "param5", required = true, dynamic = false, name = "statelessSessionFactory")
	public com.lynxit.fpm.resources.Resource getStatelessSessionFactory() {
		return statelessSessionFactory_;
	}

	public void setStatelessSessionFactory(
			com.lynxit.fpm.resources.Resource value) {
		this.statelessSessionFactory_ = value;
	}

	private String user_;

	@ConfigParam(description = "param6", required = true, dynamic = false, name = "user")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
	}

	/*
	 * inputs declarations
	 */

	// input 'SecuritiesBloombergFileCreated'
	// $-- /business process/SecuritiesBloombergFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> securitiesBloombergFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asSecuritiesBloombergFileCreatedInput() {
		return securitiesBloombergFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public BloombergSecuritiesUploadProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'SecuritiesBloombergFileCreated' input
		securitiesBloombergFileCreated = addInput("SecuritiesBloombergFileCreated");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ActionToDo
		// instantiate and configure 'ActionToDo' node
		com.lynxspa.coac.importers.securities.bloomberg.switches.ActionDecisionSwitch actionToDoNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.bloomberg.switches.ActionDecisionSwitch.class);
		actionToDoNode.setId("ActionToDo");
		addNode(actionToDoNode);
		// $-- /business process/ActionToDo/

		// $-- /business process/Commit
		// instantiate and configure 'Commit' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit commitNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		commitNode.setId("Commit");
		addNode(commitNode);
		// $-- /business process/Commit/
		commitNode.setDataSource(getStatelessSessionFactory());
		// $-- /business process/Commit/
		commitNode.setCommitEvery(getCommitSize());
		// $-- /business process/Commit/

		// $-- /business process/DeleteSecurityBean
		// instantiate and configure 'DeleteSecurityBean' node
		com.lynxspa.coac.importers.securities.bloomberg.mappings.BloombergSecurityBeanToSecurityBeanMap deleteSecurityBeanNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.bloomberg.mappings.BloombergSecurityBeanToSecurityBeanMap.class);
		deleteSecurityBeanNode.setId("DeleteSecurityBean");
		addNode(deleteSecurityBeanNode);
		// $-- /business process/DeleteSecurityBean/

		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.sdm.importers.securities.beans.SecurityBean> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/Format Validation
		// instantiate and configure 'Format Validation' node
		com.lynxspa.coac.nodes.FormatValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> formatValidationNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.FormatValidatorNode.class);
		formatValidationNode.setId("Format Validation");
		addNode(formatValidationNode);
		// $-- /business process/Format Validation/
		formatValidationNode
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.BLOOMBERG);
		// $-- /business process/Format Validation/
		formatValidationNode.setResource(getSessionFactory());
		// $-- /business process/Format Validation/

		// $-- /business process/InsertSecurityBean
		// instantiate and configure 'InsertSecurityBean' node
		com.lynxspa.coac.importers.securities.bloomberg.mappings.BloombergSecurityBeanToSecurityBeanMap insertSecurityBeanNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.bloomberg.mappings.BloombergSecurityBeanToSecurityBeanMap.class);
		insertSecurityBeanNode.setId("InsertSecurityBean");
		addNode(insertSecurityBeanNode);
		// $-- /business process/InsertSecurityBean/

		// $-- /business process/LiveCycle Validation
		// instantiate and configure 'LiveCycle Validation' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> liveCycleValidationNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidationNode.setId("LiveCycle Validation");
		addNode(liveCycleValidationNode);
		// $-- /business process/LiveCycle Validation/
		liveCycleValidationNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.PRENORMALIZE_IMPORT);
		// $-- /business process/LiveCycle Validation/
		liveCycleValidationNode.setResource(getSessionFactory());
		// $-- /business process/LiveCycle Validation/

		// $-- /business process/LogCoacException
		// instantiate and configure 'LogCoacException' node
		com.lynxspa.coac.nodes.logs.LogCoacException logCoacExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logCoacExceptionNode.setId("LogCoacException");
		addNode(logCoacExceptionNode);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setDataSource(getStatelessSessionFactory());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setStopExceptionHandling(false);
		// $-- /business process/LogCoacException/
		((DynaParamsNode) logCoacExceptionNode).setDynamicParamValues(
				"arguments", com.lynxit.utils.scripting.ELHelper
						.buildConstantExpression("Bloomberg"),
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression(
								"process.getContextAttribute(\"fileName\")",
								java.lang.Object.class));
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setCommitInNewTransaction(true);
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setLocale(getLocale());
		// $-- /business process/LogCoacException/
		// $-- /business process/LogCoacException/
		logCoacExceptionNode.setUser(getUser());
		// $-- /business process/LogCoacException/
		logCoacExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.SECURITY_IMPORT_FAIL);

		// $-- /business process/LogSecuritiesBloombergParserNode
		// instantiate and configure 'LogSecuritiesBloombergParserNode' node
		com.lynxspa.coac.importers.securities.bloomberg.nodes.LogSecuritiesBloombergParserNode<com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> logSecuritiesBloombergParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.bloomberg.nodes.LogSecuritiesBloombergParserNode.class);
		logSecuritiesBloombergParserNodeNode
				.setId("LogSecuritiesBloombergParserNode");
		addNode(logSecuritiesBloombergParserNodeNode);
		// $-- /business process/LogSecuritiesBloombergParserNode/
		logSecuritiesBloombergParserNodeNode
				.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/LogSecuritiesBloombergParserNode/
		logSecuritiesBloombergParserNodeNode.setLocale(getLocale());
		// $-- /business process/LogSecuritiesBloombergParserNode/
		logSecuritiesBloombergParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/LogSecuritiesBloombergParserNode/
		// $-- /business process/LogSecuritiesBloombergParserNode/
		logSecuritiesBloombergParserNodeNode.setUser(getUser());

		// $-- /business process/OpenFile
		// instantiate and configure 'OpenFile' node
		com.lynxit.fpm.nodes.file.SingleFileEventReaderNode openFileNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.file.SingleFileEventReaderNode.class);
		openFileNode.setId("OpenFile");
		addNode(openFileNode);
		// $-- /business process/OpenFile/
		openFileNode
				.setRollbackAction(com.lynxit.fpm.nodes.input.FileReaderNodeSupport.Action.RENAME);
		// $-- /business process/OpenFile/
		openFileNode
				.setCommitAction(com.lynxit.fpm.nodes.input.FileReaderNodeSupport.Action.RENAME);
		// $-- /business process/OpenFile/
		openFileNode.setCommittedFileSuffix("done");
		// $-- /business process/OpenFile/
		openFileNode.setRollbackedFilesDirectory(new java.io.File("error"));
		// $-- /business process/OpenFile/
		openFileNode.setCommittedFilesDirectory(new java.io.File("done"));
		// $-- /business process/OpenFile/
		openFileNode.setTimeoutOnFileLock(getInputTimeout());
		// $-- /business process/OpenFile/
		openFileNode.setCreateDestinationFolders(false);
		// $-- /business process/OpenFile/
		openFileNode.setRollbackedFileSuffix("error");
		// $-- /business process/OpenFile/
		// $-- /business process/OpenFile/
		openFileNode.setTemporaryFileSuffix("tmp");

		// $-- /business process/Parse File
		// instantiate and configure 'Parse File' node
		com.lynxspa.coac.importers.securities.bloomberg.nodes.SecuritiesBloombergParserNode parseFileNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.bloomberg.nodes.SecuritiesBloombergParserNode.class);
		parseFileNode.setId("Parse File");
		addNode(parseFileNode);
		// $-- /business process/Parse File/
		parseFileNode.setResourceStateless(getStatelessSessionFactory());
		// $-- /business process/Parse File/
		parseFileNode.setLocale(getLocale());
		// $-- /business process/Parse File/
		parseFileNode.setResource(getSessionFactory());
		// $-- /business process/Parse File/
		// $-- /business process/Parse File/
		parseFileNode.setUser(getUser());

		// $-- /business process/Rollback
		// instantiate and configure 'Rollback' node
		com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode rollbackNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode.class);
		rollbackNode.setId("Rollback");
		addNode(rollbackNode);
		// $-- /business process/Rollback/
		rollbackNode.setDataSource(getStatelessSessionFactory());
		// $-- /business process/Rollback/
		rollbackNode.setStopExceptionHandling(true);
		// $-- /business process/Rollback/

		// $-- /business process/Set insertOrUpdate
		// instantiate and configure 'Set insertOrUpdate' node
		com.lynxspa.coac.importers.securities.mappings.SecurityBeanSetActionInsertUpdateMap setInsertOrUpdateNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.mappings.SecurityBeanSetActionInsertUpdateMap.class);
		setInsertOrUpdateNode.setId("Set insertOrUpdate");
		addNode(setInsertOrUpdateNode);
		// $-- /business process/Set insertOrUpdate/

		// $-- /business process/set delete
		// instantiate and configure 'set delete' node
		com.lynxspa.coac.importers.securities.mappings.SecurityBeanSetActionDeleteMap setDeleteNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.mappings.SecurityBeanSetActionDeleteMap.class);
		setDeleteNode.setId("set delete");
		addNode(setDeleteNode);
		// $-- /business process/set delete/

		// instantiate and configure subprocesses
		// $-- /business process/StoreSecurityProcess
		// instantiate and configure 'StoreSecurityProcess' subprocess
		com.lynxspa.coac.importers.securities.businessprocess.StoreSecurityProcess storeSecurityProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.importers.securities.businessprocess.StoreSecurityProcess.class,
						"StoreSecurityProcess");
		addSubProcess(storeSecurityProcess);
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess.setSessionFactory(getStatelessSessionFactory());
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess
				.setSecuritiesUploadForceMarketInsert(getSecuritiesUploadForceMarketInsert());
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess.setLocale(getLocale());
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess.setStateFullSession(getSessionFactory());
		// $-- /business process/StoreSecurityProcess/
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess.setUser(getUser());
		// $-- /business process/StoreSecurityProcess/
		storeSecurityProcess
				.setSearchMode(com.lynxspa.coac.importers.securities.SecuritySearchMode.PROVIDERID);

		storeSecurityProcess.initialize();

		// link nodes
		// $-- /business process/ActionToDo/default/business process/LogSecuritiesBloombergParserNode/in
		actionToDoNode
				.connectNodeToDefault(logSecuritiesBloombergParserNodeNode);
		// $-- /business process/ActionToDo/delete/business process/DeleteSecurityBean/in
		actionToDoNode.connectNodeToDelete(deleteSecurityBeanNode);
		// $-- /business process/ActionToDo/insert/business process/InsertSecurityBean/in
		actionToDoNode.connectNodeToInsert(insertSecurityBeanNode);
		actionToDoNode.connectNodeToException(getDefaultExceptionHandlerNode());
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/DeleteSecurityBean/out/business process/set delete/in
		deleteSecurityBeanNode.connectNodeToOut(setDeleteNode);
		deleteSecurityBeanNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode/two/business process/Commit/in
		forkNodeNode.connectNodeToTwo(commitNode);
		// $-- /business process/ForkNode/one/business process/StoreSecurityProcess/importedBean
		forkNodeNode.connectNodeToOne(storeSecurityProcess
				.asImportedBeanInput());
		// $-- /business process/ForkNode/exception/business process/Rollback/exc
		forkNodeNode.connectNodeToException(rollbackNode);
		// $-- /business process/Format Validation/out/business process/OpenFile/in
		formatValidationNode.connectNodeToOut(openFileNode);
		formatValidationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/InsertSecurityBean/out/business process/Set insertOrUpdate/in
		insertSecurityBeanNode.connectNodeToOut(setInsertOrUpdateNode);
		insertSecurityBeanNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycle Validation/out/business process/Format Validation/in
		liveCycleValidationNode.connectNodeToOut(formatValidationNode);
		liveCycleValidationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		logSecuritiesBloombergParserNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/Parse File/in
		openFileNode.connectNodeToOut(parseFileNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Parse File/out/business process/ActionToDo/in
		parseFileNode.connectNodeToOut(actionToDoNode);
		// $-- /business process/Parse File/exception/business process/LogCoacException/exc
		parseFileNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/Set insertOrUpdate/out/business process/ForkNode/in
		setInsertOrUpdateNode.connectNodeToOut(forkNodeNode);
		setInsertOrUpdateNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/set delete/out/business process/ForkNode/in
		setDeleteNode.connectNodeToOut(forkNodeNode);
		setDeleteNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		storeSecurityProcess.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/SecuritiesBloombergFileCreated/business process/LiveCycle Validation/in
		securitiesBloombergFileCreated
				.connectNodeToOut(liveCycleValidationNode);

		actionToDoNode.init();
		commitNode.init();
		deleteSecurityBeanNode.init();
		forkNodeNode.init();
		formatValidationNode.init();
		insertSecurityBeanNode.init();
		liveCycleValidationNode.init();
		logCoacExceptionNode.init();
		logSecuritiesBloombergParserNodeNode.init();
		openFileNode.init();
		parseFileNode.init();
		rollbackNode.init();
		setInsertOrUpdateNode.init();
		setDeleteNode.init();
	}

}