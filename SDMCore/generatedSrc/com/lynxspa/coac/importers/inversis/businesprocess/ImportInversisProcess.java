package com.lynxspa.coac.importers.inversis.businesprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/inversis/businesprocess/ImportInversis.fpmprocess")
public class ImportInversisProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String user_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "User")
	public String getUser() {
		return user_;
	}

	public void setUser(String value) {
		this.user_ = value;
	}

	private Integer inputInversisCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisCommitSize")
	public Integer getInputInversisCommitSize() {
		return inputInversisCommitSize_;
	}

	public void setInputInversisCommitSize(Integer value) {
		this.inputInversisCommitSize_ = value;
	}

	private java.io.File inputInversisErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputInversisErrorDirectory")
	public java.io.File getInputInversisErrorDirectory() {
		return inputInversisErrorDirectory_;
	}

	public void setInputInversisErrorDirectory(java.io.File value) {
		this.inputInversisErrorDirectory_ = value;
	}

	private Integer inputTimeOut_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputTimeOut")
	public Integer getInputTimeOut() {
		return inputTimeOut_;
	}

	public void setInputTimeOut(Integer value) {
		this.inputTimeOut_ = value;
	}

	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
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

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "statelessSessionFactory")
	public com.lynxit.fpm.resources.Resource getStatelessSessionFactory() {
		return statelessSessionFactory_;
	}

	public void setStatelessSessionFactory(
			com.lynxit.fpm.resources.Resource value) {
		this.statelessSessionFactory_ = value;
	}

	/*
	 * inputs declarations
	 */

	// input 'InversisFileCreated'
	// $-- /business process/InversisFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> inversisFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asInversisFileCreatedInput() {
		return inversisFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportInversisProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'InversisFileCreated' input
		inversisFileCreated = addInput("InversisFileCreated");

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
		commitNode.setCommitEvery(getInputInversisCommitSize());
		// $-- /business process/Commit/

		// $-- /business process/DuplicateImportsFilter
		// instantiate and configure 'DuplicateImportsFilter' node
		com.lynxspa.coac.importers.nodes.DuplicateImportFilter<com.lynxspa.coac.importers.EventMessageImportBean> duplicateImportsFilterNode = Interceptors
				.createNode(com.lynxspa.coac.importers.nodes.DuplicateImportFilter.class);
		duplicateImportsFilterNode.setId("DuplicateImportsFilter");
		addNode(duplicateImportsFilterNode);
		// $-- /business process/DuplicateImportsFilter/
		duplicateImportsFilterNode.setLocale(getLocale());
		// $-- /business process/DuplicateImportsFilter/
		duplicateImportsFilterNode.setResource(getStatelessSessionFactory());
		// $-- /business process/DuplicateImportsFilter/
		// $-- /business process/DuplicateImportsFilter/
		duplicateImportsFilterNode.setUser(getUser());

		// $-- /business process/ExceptionRollbackNode
		// instantiate and configure 'ExceptionRollbackNode' node
		com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode exceptionRollbackNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode.class);
		exceptionRollbackNodeNode.setId("ExceptionRollbackNode");
		addNode(exceptionRollbackNodeNode);
		// $-- /business process/ExceptionRollbackNode/
		exceptionRollbackNodeNode.setDataSource(getStatelessSessionFactory());
		// $-- /business process/ExceptionRollbackNode/
		exceptionRollbackNodeNode.setStopExceptionHandling(false);
		// $-- /business process/ExceptionRollbackNode/

		// $-- /business process/FormatValidatorNode
		// instantiate and configure 'FormatValidatorNode' node
		com.lynxspa.coac.nodes.FormatValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> formatValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.FormatValidatorNode.class);
		formatValidatorNodeNode.setId("FormatValidatorNode");
		addNode(formatValidatorNodeNode);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.INVERSIS);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode.setResource(getSessionFactory());
		// $-- /business process/FormatValidatorNode/

		// $-- /business process/ImportFilter
		// instantiate and configure 'ImportFilter' node
		com.lynxspa.coac.importers.nodes.ImportFilter<com.lynxspa.coac.importers.EventMessageImportBean> importFilterNode = Interceptors
				.createNode(com.lynxspa.coac.importers.nodes.ImportFilter.class);
		importFilterNode.setId("ImportFilter");
		addNode(importFilterNode);
		// $-- /business process/ImportFilter/
		// $-- /business process/ImportFilter/
		importFilterNode.setAcceptedMessages(new java.lang.String[] { "INV" });

		// $-- /business process/InversisParserNode
		// instantiate and configure 'InversisParserNode' node
		com.lynxspa.coac.importers.inversis.nodes.InversisParserNode inversisParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.inversis.nodes.InversisParserNode.class);
		inversisParserNodeNode.setId("InversisParserNode");
		addNode(inversisParserNodeNode);
		// $-- /business process/InversisParserNode/
		inversisParserNodeNode.setErrorExtension("error");
		// $-- /business process/InversisParserNode/
		inversisParserNodeNode.setLocale(getLocale());
		// $-- /business process/InversisParserNode/
		inversisParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/InversisParserNode/
		// $-- /business process/InversisParserNode/
		inversisParserNodeNode.setUser(getUser());
		// $-- /business process/InversisParserNode/
		inversisParserNodeNode.setErrorPath(getInputInversisErrorDirectory());

		// $-- /business process/LiveCycleValidatorNode
		// instantiate and configure 'LiveCycleValidatorNode' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> liveCycleValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidatorNodeNode.setId("LiveCycleValidatorNode");
		addNode(liveCycleValidatorNodeNode);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.IMPORT_MESSAGES);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode.setResource(getSessionFactory());
		// $-- /business process/LiveCycleValidatorNode/

		// $-- /business process/Log Inversis Import exception
		// instantiate and configure 'Log Inversis Import exception' node
		com.lynxspa.coac.nodes.logs.LogCoacException logInversisImportExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logInversisImportExceptionNode.setId("Log Inversis Import exception");
		addNode(logInversisImportExceptionNode);
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/Log Inversis Import exception/
		((DynaParamsNode) logInversisImportExceptionNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"fileName\")",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"ParserNode.position\");",
										java.lang.Object.class),
						com.lynxit.utils.scripting.ELHelper
								.buildConstantExpression("Inversis"));
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode.setCommitInNewTransaction(false);
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode.setLocale(getLocale());
		// $-- /business process/Log Inversis Import exception/
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode.setUser(getUser());
		// $-- /business process/Log Inversis Import exception/
		logInversisImportExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.IMPORT_FAIL);

		// $-- /business process/MappingNode
		// instantiate and configure 'MappingNode' node
		com.lynxspa.coac.importers.inversis.mappings.PreprocessInversisMap mappingNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.inversis.mappings.PreprocessInversisMap.class);
		mappingNodeNode.setId("MappingNode");
		addNode(mappingNodeNode);
		// $-- /business process/MappingNode/

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
		openFileNode.setRollbackedFilesDirectory(new java.io.File("/error"));
		// $-- /business process/OpenFile/
		openFileNode.setCommittedFilesDirectory(new java.io.File("/done"));
		// $-- /business process/OpenFile/
		openFileNode.setTimeoutOnFileLock(getInputTimeOut());
		// $-- /business process/OpenFile/
		openFileNode.setCreateDestinationFolders(false);
		// $-- /business process/OpenFile/
		openFileNode.setRollbackedFileSuffix("error");
		// $-- /business process/OpenFile/
		// $-- /business process/OpenFile/
		openFileNode.setTemporaryFileSuffix("tmp");

		// instantiate and configure subprocesses
		// $-- /business process/SaveImportedMessageProcess
		// instantiate and configure 'SaveImportedMessageProcess' subprocess
		com.lynxspa.coac.importers.businessprocess.SaveImportedMessageProcess saveImportedMessageProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.importers.businessprocess.SaveImportedMessageProcess.class,
						"SaveImportedMessageProcess");
		addSubProcess(saveImportedMessageProcess);
		// $-- /business process/SaveImportedMessageProcess/
		saveImportedMessageProcess
				.setSessionFactory(getStatelessSessionFactory());
		// $-- /business process/SaveImportedMessageProcess/
		saveImportedMessageProcess.setLocale(getLocale());
		// $-- /business process/SaveImportedMessageProcess/
		// $-- /business process/SaveImportedMessageProcess/
		saveImportedMessageProcess.setUser(getUser());

		saveImportedMessageProcess.initialize();

		// link nodes
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/DuplicateImportsFilter/out/business process/SaveImportedMessageProcess/importedMessage
		duplicateImportsFilterNode.connectNodeToOut(saveImportedMessageProcess
				.asImportedMessageInput());
		duplicateImportsFilterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FormatValidatorNode/out/business process/OpenFile/in
		formatValidatorNodeNode.connectNodeToOut(openFileNode);
		formatValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ImportFilter/out/business process/MappingNode/in
		importFilterNode.connectNodeToOut(mappingNodeNode);
		importFilterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/InversisParserNode/out/business process/ImportFilter/in
		inversisParserNodeNode.connectNodeToOut(importFilterNode);
		// $-- /business process/InversisParserNode/exception/business process/Log Inversis Import exception/exc
		inversisParserNodeNode
				.connectNodeToException(logInversisImportExceptionNode);
		// $-- /business process/LiveCycleValidatorNode/out/business process/FormatValidatorNode/in
		liveCycleValidatorNodeNode.connectNodeToOut(formatValidatorNodeNode);
		liveCycleValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MappingNode/out/business process/DuplicateImportsFilter/in
		mappingNodeNode.connectNodeToOut(duplicateImportsFilterNode);
		mappingNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/InversisParserNode/in
		openFileNode.connectNodeToOut(inversisParserNodeNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/SaveImportedMessageProcess/importedMessage/business process/Commit/in
		saveImportedMessageProcess.asImportedMessageOutput()
				.connect(commitNode);
		// $-- /business process/SaveImportedMessageProcess/exc/business process/ExceptionRollbackNode/exc
		saveImportedMessageProcess.asExcOutput().connect(
				exceptionRollbackNodeNode);

		// link inputs
		// $-- /business process/InversisFileCreated/business process/LiveCycleValidatorNode/in
		inversisFileCreated.connectNodeToOut(liveCycleValidatorNodeNode);

		commitNode.init();
		duplicateImportsFilterNode.init();
		exceptionRollbackNodeNode.init();
		formatValidatorNodeNode.init();
		importFilterNode.init();
		inversisParserNodeNode.init();
		liveCycleValidatorNodeNode.init();
		logInversisImportExceptionNode.init();
		mappingNodeNode.init();
		openFileNode.init();
	}

}