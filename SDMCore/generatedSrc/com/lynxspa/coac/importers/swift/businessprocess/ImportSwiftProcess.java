package com.lynxspa.coac.importers.swift.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/swift/businessprocess/ImportSwift.fpmprocess")
public class ImportSwiftProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer inputSwiftCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputSwiftCommitSize")
	public Integer getInputSwiftCommitSize() {
		return inputSwiftCommitSize_;
	}

	public void setInputSwiftCommitSize(Integer value) {
		this.inputSwiftCommitSize_ = value;
	}

	private java.io.File inputSwiftErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputSwiftErrorDirectory")
	public java.io.File getInputSwiftErrorDirectory() {
		return inputSwiftErrorDirectory_;
	}

	public void setInputSwiftErrorDirectory(java.io.File value) {
		this.inputSwiftErrorDirectory_ = value;
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

	// input 'SwiftFileCreated'
	// $-- /business process/SwiftFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> swiftFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asSwiftFileCreatedInput() {
		return swiftFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportSwiftProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'SwiftFileCreated' input
		swiftFileCreated = addInput("SwiftFileCreated");

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
		commitNode.setCommitEvery(getInputSwiftCommitSize());
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
		filterMessagesNode.setAcceptedMessages(new java.lang.String[] { "564",
				"565", "566", "567", "568" });

		// $-- /business process/FormatValidatorNode
		// instantiate and configure 'FormatValidatorNode' node
		com.lynxspa.coac.nodes.FormatValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> formatValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.FormatValidatorNode.class);
		formatValidatorNodeNode.setId("FormatValidatorNode");
		addNode(formatValidatorNodeNode);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.SWIFT);
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

		// $-- /business process/Log Swift Import exception
		// instantiate and configure 'Log Swift Import exception' node
		com.lynxspa.coac.nodes.logs.LogCoacException logSwiftImportExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logSwiftImportExceptionNode.setId("Log Swift Import exception");
		addNode(logSwiftImportExceptionNode);
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/Log Swift Import exception/
		((DynaParamsNode) logSwiftImportExceptionNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.ELHelper
								.buildConstantExpression("Swift"),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"fileName\")",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"ParserNode.position\");",
										java.lang.Object.class));
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode.setCommitInNewTransaction(false);
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode.setLocale(getLocale());
		// $-- /business process/Log Swift Import exception/
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode.setUser(getUser());
		// $-- /business process/Log Swift Import exception/
		logSwiftImportExceptionNode
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

		// $-- /business process/Preprocess564
		// instantiate and configure 'Preprocess564' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwift564Map preprocess564Node = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwift564Map.class);
		preprocess564Node.setId("Preprocess564");
		addNode(preprocess564Node);
		// $-- /business process/Preprocess564/

		// $-- /business process/Preprocess565
		// instantiate and configure 'Preprocess565' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwift565Map preprocess565Node = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwift565Map.class);
		preprocess565Node.setId("Preprocess565");
		addNode(preprocess565Node);
		// $-- /business process/Preprocess565/

		// $-- /business process/Preprocess566
		// instantiate and configure 'Preprocess566' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwift566Map preprocess566Node = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwift566Map.class);
		preprocess566Node.setId("Preprocess566");
		addNode(preprocess566Node);
		// $-- /business process/Preprocess566/

		// $-- /business process/Preprocess567
		// instantiate and configure 'Preprocess567' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwift567Map preprocess567Node = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwift567Map.class);
		preprocess567Node.setId("Preprocess567");
		addNode(preprocess567Node);
		// $-- /business process/Preprocess567/

		// $-- /business process/Preprocess568
		// instantiate and configure 'Preprocess568' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwift568Map preprocess568Node = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwift568Map.class);
		preprocess568Node.setId("Preprocess568");
		addNode(preprocess568Node);
		// $-- /business process/Preprocess568/

		// $-- /business process/PreprocessSwift
		// instantiate and configure 'PreprocessSwift' node
		com.lynxspa.coac.importers.swift.mappings.PreprocessSwiftMap preprocessSwiftNode = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.mappings.PreprocessSwiftMap.class);
		preprocessSwiftNode.setId("PreprocessSwift");
		addNode(preprocessSwiftNode);
		// $-- /business process/PreprocessSwift/

		// $-- /business process/SwiftParserNode
		// instantiate and configure 'SwiftParserNode' node
		com.lynxspa.coac.importers.swift.nodes.SwiftParserNode swiftParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.nodes.SwiftParserNode.class);
		swiftParserNodeNode.setId("SwiftParserNode");
		addNode(swiftParserNodeNode);
		// $-- /business process/SwiftParserNode/
		// $-- /business process/SwiftParserNode/
		swiftParserNodeNode.setLocale(getLocale());
		// $-- /business process/SwiftParserNode/
		swiftParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/SwiftParserNode/
		// $-- /business process/SwiftParserNode/
		swiftParserNodeNode.setUser(getUser());
		// $-- /business process/SwiftParserNode/
		swiftParserNodeNode.setErrorPath(getInputSwiftErrorDirectory());

		// $-- /business process/SwiftSwitchNode
		// instantiate and configure 'SwiftSwitchNode' node
		com.lynxspa.coac.importers.swift.nodes.SwiftSwitchNode<com.lynxspa.coac.importers.EventMessageImportBean> swiftSwitchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.swift.nodes.SwiftSwitchNode.class);
		swiftSwitchNodeNode.setId("SwiftSwitchNode");
		addNode(swiftSwitchNodeNode);
		// $-- /business process/SwiftSwitchNode/

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
		// $-- /business process/FilterMessages/out/business process/PreprocessSwift/in
		filterMessagesNode.connectNodeToOut(preprocessSwiftNode);
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
		// $-- /business process/OpenFile/out/business process/SwiftParserNode/in
		openFileNode.connectNodeToOut(swiftParserNodeNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess564/out/business process/DuplicateImportsFilter/in
		preprocess564Node.connectNodeToOut(duplicateImportsFilterNode);
		preprocess564Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess565/out/business process/DuplicateImportsFilter/in
		preprocess565Node.connectNodeToOut(duplicateImportsFilterNode);
		preprocess565Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess566/out/business process/DuplicateImportsFilter/in
		preprocess566Node.connectNodeToOut(duplicateImportsFilterNode);
		preprocess566Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess567/out/business process/DuplicateImportsFilter/in
		preprocess567Node.connectNodeToOut(duplicateImportsFilterNode);
		preprocess567Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Preprocess568/out/business process/DuplicateImportsFilter/in
		preprocess568Node.connectNodeToOut(duplicateImportsFilterNode);
		preprocess568Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessSwift/out/business process/SwiftSwitchNode/in
		preprocessSwiftNode.connectNodeToOut(swiftSwitchNodeNode);
		preprocessSwiftNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SwiftParserNode/out/business process/FilterMessages/in
		swiftParserNodeNode.connectNodeToOut(filterMessagesNode);
		// $-- /business process/SwiftParserNode/exception/business process/Log Swift Import exception/exc
		swiftParserNodeNode.connectNodeToException(logSwiftImportExceptionNode);
		// $-- /business process/SwiftSwitchNode/default/business process/DuplicateImportsFilter/in
		swiftSwitchNodeNode.connectNodeToDefault(duplicateImportsFilterNode);
		// $-- /business process/SwiftSwitchNode/mT564/business process/Preprocess564/in
		swiftSwitchNodeNode.connectNodeToMT564(preprocess564Node);
		// $-- /business process/SwiftSwitchNode/mT565/business process/Preprocess565/in
		swiftSwitchNodeNode.connectNodeToMT565(preprocess565Node);
		// $-- /business process/SwiftSwitchNode/mT566/business process/Preprocess566/in
		swiftSwitchNodeNode.connectNodeToMT566(preprocess566Node);
		// $-- /business process/SwiftSwitchNode/mT567/business process/Preprocess567/in
		swiftSwitchNodeNode.connectNodeToMT567(preprocess567Node);
		// $-- /business process/SwiftSwitchNode/mT568/business process/Preprocess568/in
		swiftSwitchNodeNode.connectNodeToMT568(preprocess568Node);
		swiftSwitchNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/SaveImportedMessageProcess/importedMessage/business process/Commit/in
		saveImportedMessageProcess.asImportedMessageOutput()
				.connect(commitNode);
		// $-- /business process/SaveImportedMessageProcess/exc/business process/ExceptionRollbackNode/exc
		saveImportedMessageProcess.asExcOutput().connect(
				exceptionRollbackNodeNode);

		// link inputs
		// $-- /business process/SwiftFileCreated/business process/LiveCycleValidatorNode/in
		swiftFileCreated.connectNodeToOut(liveCycleValidatorNodeNode);

		commitNode.init();
		duplicateImportsFilterNode.init();
		exceptionRollbackNodeNode.init();
		filterMessagesNode.init();
		formatValidatorNodeNode.init();
		liveCycleValidatorNodeNode.init();
		logSwiftImportExceptionNode.init();
		openFileNode.init();
		preprocess564Node.init();
		preprocess565Node.init();
		preprocess566Node.init();
		preprocess567Node.init();
		preprocess568Node.init();
		preprocessSwiftNode.init();
		swiftParserNodeNode.init();
		swiftSwitchNodeNode.init();
	}

}