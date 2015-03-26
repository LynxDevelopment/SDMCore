package com.lynxspa.coac.importers.securities.customer.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/customer/businessprocess/CustomerSecuritiesUpload.fpmprocess")
public class CustomerSecuritiesUploadProcess extends BusinessProcess {
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputTimeout")
	public Integer getInputTimeout() {
		return inputTimeout_;
	}

	public void setInputTimeout(Integer value) {
		this.inputTimeout_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "sessionFactory")
	public com.lynxit.fpm.resources.Resource getSessionFactory() {
		return sessionFactory_;
	}

	public void setSessionFactory(com.lynxit.fpm.resources.Resource value) {
		this.sessionFactory_ = value;
	}

	private com.lynxit.fpm.resources.Resource statelessSessionFactory_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "statelessSessionFactory")
	public com.lynxit.fpm.resources.Resource getStatelessSessionFactory() {
		return statelessSessionFactory_;
	}

	public void setStatelessSessionFactory(
			com.lynxit.fpm.resources.Resource value) {
		this.statelessSessionFactory_ = value;
	}

	private String user_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "user")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
	}

	/*
	 * inputs declarations
	 */

	// input 'SecuritiesCustomerFileCreated'
	// $-- /business process/SecuritiesCustomerFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> securitiesCustomerFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asSecuritiesCustomerFileCreatedInput() {
		return securitiesCustomerFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public CustomerSecuritiesUploadProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'SecuritiesCustomerFileCreated' input
		securitiesCustomerFileCreated = addInput("SecuritiesCustomerFileCreated");

		// instantiate and configure outputs

		// instantiate and configure nodes
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

		// $-- /business process/CustomerSecurityParser
		// instantiate and configure 'CustomerSecurityParser' node
		com.lynxspa.coac.importers.securities.customer.nodes.CustomerSecurityParser customerSecurityParserNode = Interceptors
				.createNode(com.lynxspa.coac.importers.securities.customer.nodes.CustomerSecurityParser.class);
		customerSecurityParserNode.setId("CustomerSecurityParser");
		addNode(customerSecurityParserNode);
		// $-- /business process/CustomerSecurityParser/
		customerSecurityParserNode.setLocale(getLocale());
		// $-- /business process/CustomerSecurityParser/
		customerSecurityParserNode.setResource(getSessionFactory());
		// $-- /business process/CustomerSecurityParser/
		customerSecurityParserNode.setEncoding("ISO-8859-1");
		// $-- /business process/CustomerSecurityParser/
		// $-- /business process/CustomerSecurityParser/
		customerSecurityParserNode.setUser(getUser());

		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.sdm.importers.securities.beans.SecurityBean> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

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
						.buildConstantExpression("Customer"),
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
				.setSearchMode(com.lynxspa.coac.importers.securities.SecuritySearchMode.ISIN);

		storeSecurityProcess.initialize();

		// link nodes
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CustomerSecurityParser/out/business process/ForkNode/in
		customerSecurityParserNode.connectNodeToOut(forkNodeNode);
		// $-- /business process/CustomerSecurityParser/exception/business process/LogCoacException/exc
		customerSecurityParserNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/ForkNode/two/business process/Commit/in
		forkNodeNode.connectNodeToTwo(commitNode);
		// $-- /business process/ForkNode/one/business process/StoreSecurityProcess/importedBean
		forkNodeNode.connectNodeToOne(storeSecurityProcess
				.asImportedBeanInput());
		// $-- /business process/ForkNode/exception/business process/Rollback/exc
		forkNodeNode.connectNodeToException(rollbackNode);
		// $-- /business process/LiveCycle Validation/out/business process/OpenFile/in
		liveCycleValidationNode.connectNodeToOut(openFileNode);
		liveCycleValidationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/CustomerSecurityParser/in
		openFileNode.connectNodeToOut(customerSecurityParserNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		storeSecurityProcess.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/SecuritiesCustomerFileCreated/business process/LiveCycle Validation/in
		securitiesCustomerFileCreated.connectNodeToOut(liveCycleValidationNode);

		commitNode.init();
		customerSecurityParserNode.init();
		forkNodeNode.init();
		liveCycleValidationNode.init();
		logCoacExceptionNode.init();
		openFileNode.init();
		rollbackNode.init();
	}

}