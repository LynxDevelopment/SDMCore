package com.lynxspa.coac.importers.bloomberg.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/bloomberg/businessprocess/ImportBloomberg.fpmprocess")
public class ImportBloombergProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer inputBloombergCommitSize_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "inputBloombergCommitSize")
	public Integer getInputBloombergCommitSize() {
		return inputBloombergCommitSize_;
	}

	public void setInputBloombergCommitSize(Integer value) {
		this.inputBloombergCommitSize_ = value;
	}

	private java.io.File inputBloombergErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergErrorDirectory")
	public java.io.File getInputBloombergErrorDirectory() {
		return inputBloombergErrorDirectory_;
	}

	public void setInputBloombergErrorDirectory(java.io.File value) {
		this.inputBloombergErrorDirectory_ = value;
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

	// input 'BloombergFileCreated'
	// $-- /business process/BloombergFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> bloombergFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asBloombergFileCreatedInput() {
		return bloombergFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportBloombergProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'BloombergFileCreated' input
		bloombergFileCreated = addInput("BloombergFileCreated");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/BloombergParserNode
		// instantiate and configure 'BloombergParserNode' node
		com.lynxspa.coac.importers.bloomberg.nodes.BloombergParserNode bloombergParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.nodes.BloombergParserNode.class);
		bloombergParserNodeNode.setId("BloombergParserNode");
		addNode(bloombergParserNodeNode);
		// $-- /business process/BloombergParserNode/
		// $-- /business process/BloombergParserNode/
		bloombergParserNodeNode.setLocale(getLocale());
		// $-- /business process/BloombergParserNode/
		bloombergParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/BloombergParserNode/
		// $-- /business process/BloombergParserNode/
		bloombergParserNodeNode.setUser(getUser());
		// $-- /business process/BloombergParserNode/
		bloombergParserNodeNode.setErrorPath(getInputBloombergErrorDirectory());

		// $-- /business process/Commit
		// instantiate and configure 'Commit' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit commitNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		commitNode.setId("Commit");
		addNode(commitNode);
		// $-- /business process/Commit/
		commitNode.setDataSource(getStatelessSessionFactory());
		// $-- /business process/Commit/
		commitNode.setCommitEvery(getInputBloombergCommitSize());
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

		// $-- /business process/FilterMessages
		// instantiate and configure 'FilterMessages' node
		com.lynxspa.coac.importers.nodes.ImportFilter<com.lynxspa.coac.importers.EventMessageImportBean> filterMessagesNode = Interceptors
				.createNode(com.lynxspa.coac.importers.nodes.ImportFilter.class);
		filterMessagesNode.setId("FilterMessages");
		addNode(filterMessagesNode);
		// $-- /business process/FilterMessages/
		// $-- /business process/FilterMessages/
		filterMessagesNode
				.setAcceptedMessages(new java.lang.String[] { "Corp/Pfd" });

		// $-- /business process/FormatValidatorNode
		// instantiate and configure 'FormatValidatorNode' node
		com.lynxspa.coac.nodes.FormatValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> formatValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.FormatValidatorNode.class);
		formatValidatorNodeNode.setId("FormatValidatorNode");
		addNode(formatValidatorNodeNode);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.BLOOMBERG);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode.setResource(getSessionFactory());
		// $-- /business process/FormatValidatorNode/

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

		// $-- /business process/Log Bloomberg Import exception
		// instantiate and configure 'Log Bloomberg Import exception' node
		com.lynxspa.coac.nodes.logs.LogCoacException logBloombergImportExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logBloombergImportExceptionNode.setId("Log Bloomberg Import exception");
		addNode(logBloombergImportExceptionNode);
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/Log Bloomberg Import exception/
		((DynaParamsNode) logBloombergImportExceptionNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.ELHelper
								.buildConstantExpression("Bloomberg"),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"fileName\")",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"ParserNode.position\");",
										java.lang.Object.class));
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode.setCommitInNewTransaction(false);
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode.setLocale(getLocale());
		// $-- /business process/Log Bloomberg Import exception/
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode.setUser(getUser());
		// $-- /business process/Log Bloomberg Import exception/
		logBloombergImportExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.IMPORT_FAIL);

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

		// $-- /business process/Preprocess
		// instantiate and configure 'Preprocess' node
		com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergMap preprocessNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergMap.class);
		preprocessNode.setId("Preprocess");
		addNode(preprocessNode);
		// $-- /business process/Preprocess/

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
		// $-- /business process/BloombergParserNode/out/business process/FilterMessages/in
		bloombergParserNodeNode.connectNodeToOut(filterMessagesNode);
		// $-- /business process/BloombergParserNode/exception/business process/Log Bloomberg Import exception/exc
		bloombergParserNodeNode
				.connectNodeToException(logBloombergImportExceptionNode);
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/DuplicateImportsFilter/out/business process/SaveImportedMessageProcess/importedMessage
		duplicateImportsFilterNode.connectNodeToOut(saveImportedMessageProcess
				.asImportedMessageInput());
		duplicateImportsFilterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FilterMessages/out/business process/Preprocess/in
		filterMessagesNode.connectNodeToOut(preprocessNode);
		filterMessagesNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FormatValidatorNode/out/business process/OpenFile/in
		formatValidatorNodeNode.connectNodeToOut(openFileNode);
		formatValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/FormatValidatorNode/in
		liveCycleValidatorNodeNode.connectNodeToOut(formatValidatorNodeNode);
		liveCycleValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/BloombergParserNode/in
		openFileNode.connectNodeToOut(bloombergParserNodeNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess/out/business process/DuplicateImportsFilter/in
		preprocessNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/SaveImportedMessageProcess/importedMessage/business process/Commit/in
		saveImportedMessageProcess.asImportedMessageOutput()
				.connect(commitNode);
		// $-- /business process/SaveImportedMessageProcess/exc/business process/ExceptionRollbackNode/exc
		saveImportedMessageProcess.asExcOutput().connect(
				exceptionRollbackNodeNode);

		// link inputs
		// $-- /business process/BloombergFileCreated/business process/LiveCycleValidatorNode/in
		bloombergFileCreated.connectNodeToOut(liveCycleValidatorNodeNode);

		bloombergParserNodeNode.init();
		commitNode.init();
		duplicateImportsFilterNode.init();
		exceptionRollbackNodeNode.init();
		filterMessagesNode.init();
		formatValidatorNodeNode.init();
		liveCycleValidatorNodeNode.init();
		logBloombergImportExceptionNode.init();
		openFileNode.init();
		preprocessNode.init();
	}

}