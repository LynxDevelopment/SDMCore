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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/bloomberg/businessprocess/ImportBloombergPerSecurity.fpmprocess")
public class ImportBloombergPerSecurityProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer inputBloombergPerSecCommitSize_;

	@ConfigParam(description = "param1", required = false, dynamic = false, name = "inputBloombergPerSecCommitSize")
	public Integer getInputBloombergPerSecCommitSize() {
		return inputBloombergPerSecCommitSize_;
	}

	public void setInputBloombergPerSecCommitSize(Integer value) {
		this.inputBloombergPerSecCommitSize_ = value;
	}

	private java.io.File inputBloombergPerSecErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputBloombergPerSecErrorDirectory")
	public java.io.File getInputBloombergPerSecErrorDirectory() {
		return inputBloombergPerSecErrorDirectory_;
	}

	public void setInputBloombergPerSecErrorDirectory(java.io.File value) {
		this.inputBloombergPerSecErrorDirectory_ = value;
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

	public ImportBloombergPerSecurityProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'BloombergFileCreated' input
		bloombergFileCreated = addInput("BloombergFileCreated");

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
		commitNode.setCommitEvery(getInputBloombergPerSecCommitSize());
		// $-- /business process/Commit/

		// $-- /business process/DuplicatesFilter
		// instantiate and configure 'DuplicatesFilter' node
		com.lynxspa.coac.importers.nodes.DuplicateImportFilter<com.lynxspa.coac.importers.EventMessageImportBean> duplicatesFilterNode = Interceptors
				.createNode(com.lynxspa.coac.importers.nodes.DuplicateImportFilter.class);
		duplicatesFilterNode.setId("DuplicatesFilter");
		addNode(duplicatesFilterNode);
		// $-- /business process/DuplicatesFilter/
		duplicatesFilterNode.setLocale(getLocale());
		// $-- /business process/DuplicatesFilter/
		duplicatesFilterNode.setResource(getStatelessSessionFactory());
		// $-- /business process/DuplicatesFilter/
		// $-- /business process/DuplicatesFilter/
		duplicatesFilterNode.setUser(getUser());

		// $-- /business process/EventTypeSwitch
		// instantiate and configure 'EventTypeSwitch' node
		com.lynxspa.coac.importers.bloomberg.nodes.BloombergPerSecSwitchNode<com.lynxspa.coac.importers.EventMessageImportBean> eventTypeSwitchNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.nodes.BloombergPerSecSwitchNode.class);
		eventTypeSwitchNode.setId("EventTypeSwitch");
		addNode(eventTypeSwitchNode);
		// $-- /business process/EventTypeSwitch/

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
				.setAcceptedMessages(new java.lang.String[] { "Corp/Psc" });

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

		// $-- /business process/Parse
		// instantiate and configure 'Parse' node
		com.lynxspa.coac.importers.bloomberg.nodes.BloombergPerSecParserNode parseNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.nodes.BloombergPerSecParserNode.class);
		parseNode.setId("Parse");
		addNode(parseNode);
		// $-- /business process/Parse/
		parseNode.setErrorExtension("error");
		// $-- /business process/Parse/
		parseNode.setLocale(getLocale());
		// $-- /business process/Parse/
		parseNode.setResource(getSessionFactory());
		// $-- /business process/Parse/
		// $-- /business process/Parse/
		parseNode.setUser(getUser());
		// $-- /business process/Parse/
		parseNode.setErrorPath(getInputBloombergPerSecErrorDirectory());

		// $-- /business process/Preprocess
		// instantiate and configure 'Preprocess' node
		com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergPerSecurityMap preprocessNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergPerSecurityMap.class);
		preprocessNode.setId("Preprocess");
		addNode(preprocessNode);
		// $-- /business process/Preprocess/

		// $-- /business process/PreprocessCPN
		// instantiate and configure 'PreprocessCPN' node
		com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergPerSecurityCPNMap preprocessCPNNode = Interceptors
				.createNode(com.lynxspa.coac.importers.bloomberg.mappings.PreprocessBloombergPerSecurityCPNMap.class);
		preprocessCPNNode.setId("PreprocessCPN");
		addNode(preprocessCPNNode);
		// $-- /business process/PreprocessCPN/

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
		// $-- /business process/DuplicatesFilter/out/business process/SaveImportedMessageProcess/importedMessage
		duplicatesFilterNode.connectNodeToOut(saveImportedMessageProcess
				.asImportedMessageInput());
		duplicatesFilterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/EventTypeSwitch/default/business process/DuplicatesFilter/in
		eventTypeSwitchNode.connectNodeToDefault(duplicatesFilterNode);
		// $-- /business process/EventTypeSwitch/cPN/business process/PreprocessCPN/in
		eventTypeSwitchNode.connectNodeToCPN(preprocessCPNNode);
		eventTypeSwitchNode
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
		// $-- /business process/OpenFile/out/business process/Parse/in
		openFileNode.connectNodeToOut(parseNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Parse/out/business process/FilterMessages/in
		parseNode.connectNodeToOut(filterMessagesNode);
		// $-- /business process/Parse/exception/business process/Log Bloomberg Import exception/exc
		parseNode.connectNodeToException(logBloombergImportExceptionNode);
		// $-- /business process/Preprocess/out/business process/EventTypeSwitch/in
		preprocessNode.connectNodeToOut(eventTypeSwitchNode);
		preprocessNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessCPN/out/business process/DuplicatesFilter/in
		preprocessCPNNode.connectNodeToOut(duplicatesFilterNode);
		preprocessCPNNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

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

		commitNode.init();
		duplicatesFilterNode.init();
		eventTypeSwitchNode.init();
		exceptionRollbackNodeNode.init();
		filterMessagesNode.init();
		formatValidatorNodeNode.init();
		liveCycleValidatorNodeNode.init();
		logBloombergImportExceptionNode.init();
		openFileNode.init();
		parseNode.init();
		preprocessNode.init();
		preprocessCPNNode.init();
	}

}