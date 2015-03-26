package com.lynxspa.coac.importers.iberclear.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/iberclear/businessprocess/ImportIberclear.fpmprocess")
public class ImportIberclearProcess extends BusinessProcess {
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

	private Integer inputIberclearCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearCommitSize")
	public Integer getInputIberclearCommitSize() {
		return inputIberclearCommitSize_;
	}

	public void setInputIberclearCommitSize(Integer value) {
		this.inputIberclearCommitSize_ = value;
	}

	private java.io.File inputIberclearErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputIberclearErrorDirectory")
	public java.io.File getInputIberclearErrorDirectory() {
		return inputIberclearErrorDirectory_;
	}

	public void setInputIberclearErrorDirectory(java.io.File value) {
		this.inputIberclearErrorDirectory_ = value;
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

	// input 'IberclearFileCreated'
	// $-- /business process/IberclearFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> iberclearFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asIberclearFileCreatedInput() {
		return iberclearFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportIberclearProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'IberclearFileCreated' input
		iberclearFileCreated = addInput("IberclearFileCreated");

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
		commitNode.setCommitEvery(getInputIberclearCommitSize());
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
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.IBERCLEAR);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode.setResource(getSessionFactory());
		// $-- /business process/FormatValidatorNode/

		// $-- /business process/IberclearParserNode
		// instantiate and configure 'IberclearParserNode' node
		com.lynxspa.coac.importers.iberclear.nodes.IberclearParserNode iberclearParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.iberclear.nodes.IberclearParserNode.class);
		iberclearParserNodeNode.setId("IberclearParserNode");
		addNode(iberclearParserNodeNode);
		// $-- /business process/IberclearParserNode/
		iberclearParserNodeNode.setErrorExtension("error");
		// $-- /business process/IberclearParserNode/
		iberclearParserNodeNode.setLocale(getLocale());
		// $-- /business process/IberclearParserNode/
		iberclearParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/IberclearParserNode/
		// $-- /business process/IberclearParserNode/
		iberclearParserNodeNode.setUser(getUser());
		// $-- /business process/IberclearParserNode/
		iberclearParserNodeNode.setErrorPath(getInputIberclearErrorDirectory());

		// $-- /business process/ImportFilter
		// instantiate and configure 'ImportFilter' node
		com.lynxspa.coac.importers.nodes.ImportFilter<com.lynxspa.coac.importers.EventMessageImportBean> importFilterNode = Interceptors
				.createNode(com.lynxspa.coac.importers.nodes.ImportFilter.class);
		importFilterNode.setId("ImportFilter");
		addNode(importFilterNode);
		// $-- /business process/ImportFilter/
		// $-- /business process/ImportFilter/
		importFilterNode.setAcceptedMessages(new java.lang.String[] { "X02" });

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

		// $-- /business process/Log Iberclear Import exception
		// instantiate and configure 'Log Iberclear Import exception' node
		com.lynxspa.coac.nodes.logs.LogCoacException logIberclearImportExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logIberclearImportExceptionNode.setId("Log Iberclear Import exception");
		addNode(logIberclearImportExceptionNode);
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/Log Iberclear Import exception/
		((DynaParamsNode) logIberclearImportExceptionNode)
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
								.buildConstantExpression("Iberclear"));
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode.setCommitInNewTransaction(false);
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode.setLocale(getLocale());
		// $-- /business process/Log Iberclear Import exception/
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode.setUser(getUser());
		// $-- /business process/Log Iberclear Import exception/
		logIberclearImportExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.IMPORT_FAIL);

		// $-- /business process/MappingNode
		// instantiate and configure 'MappingNode' node
		com.lynxspa.coac.importers.iberclear.mappings.PreprocessIberclearMap mappingNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.iberclear.mappings.PreprocessIberclearMap.class);
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
		// $-- /business process/IberclearParserNode/out/business process/ImportFilter/in
		iberclearParserNodeNode.connectNodeToOut(importFilterNode);
		// $-- /business process/IberclearParserNode/exception/business process/Log Iberclear Import exception/exc
		iberclearParserNodeNode
				.connectNodeToException(logIberclearImportExceptionNode);
		// $-- /business process/ImportFilter/out/business process/MappingNode/in
		importFilterNode.connectNodeToOut(mappingNodeNode);
		importFilterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/FormatValidatorNode/in
		liveCycleValidatorNodeNode.connectNodeToOut(formatValidatorNodeNode);
		liveCycleValidatorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/MappingNode/out/business process/DuplicateImportsFilter/in
		mappingNodeNode.connectNodeToOut(duplicateImportsFilterNode);
		mappingNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/IberclearParserNode/in
		openFileNode.connectNodeToOut(iberclearParserNodeNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/SaveImportedMessageProcess/importedMessage/business process/Commit/in
		saveImportedMessageProcess.asImportedMessageOutput()
				.connect(commitNode);
		// $-- /business process/SaveImportedMessageProcess/exc/business process/ExceptionRollbackNode/exc
		saveImportedMessageProcess.asExcOutput().connect(
				exceptionRollbackNodeNode);

		// link inputs
		// $-- /business process/IberclearFileCreated/business process/LiveCycleValidatorNode/in
		iberclearFileCreated.connectNodeToOut(liveCycleValidatorNodeNode);

		commitNode.init();
		duplicateImportsFilterNode.init();
		exceptionRollbackNodeNode.init();
		formatValidatorNodeNode.init();
		iberclearParserNodeNode.init();
		importFilterNode.init();
		liveCycleValidatorNodeNode.init();
		logIberclearImportExceptionNode.init();
		mappingNodeNode.init();
		openFileNode.init();
	}

}