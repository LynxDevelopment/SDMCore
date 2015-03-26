package com.lynxspa.coac.importers.ofival.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/ofival/businessprocess/ImportOfival.fpmprocess")
public class ImportOfivalProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private Integer inputOfivalCommitSize_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalCommitSize")
	public Integer getInputOfivalCommitSize() {
		return inputOfivalCommitSize_;
	}

	public void setInputOfivalCommitSize(Integer value) {
		this.inputOfivalCommitSize_ = value;
	}

	private java.io.File inputOfivalErrorDirectory_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "inputOfivalErrorDirectory")
	public java.io.File getInputOfivalErrorDirectory() {
		return inputOfivalErrorDirectory_;
	}

	public void setInputOfivalErrorDirectory(java.io.File value) {
		this.inputOfivalErrorDirectory_ = value;
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

	// input 'OfivalFileCreated'
	// $-- /business process/OfivalFileCreated
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> ofivalFileCreated;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.fileevents.FileEvent> asOfivalFileCreatedInput() {
		return ofivalFileCreated;
	}

	/*
	 * outputs declarations
	 */

	public ImportOfivalProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'OfivalFileCreated' input
		ofivalFileCreated = addInput("OfivalFileCreated");

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
		commitNode.setCommitEvery(getInputOfivalCommitSize());
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
		filterMessagesNode.setAcceptedMessages(new java.lang.String[] { "CUP",
				"AMS", "CVC", "THR", "FUS", "FVL", "RFJ", "OPV", "AMP", "DAC",
				"DEV", "SPL", "OPA" });

		// $-- /business process/FormatValidatorNode
		// instantiate and configure 'FormatValidatorNode' node
		com.lynxspa.coac.nodes.FormatValidatorNode<com.lynxit.fpm.events.fileevents.FileEvent> formatValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.FormatValidatorNode.class);
		formatValidatorNodeNode.setId("FormatValidatorNode");
		addNode(formatValidatorNodeNode);
		// $-- /business process/FormatValidatorNode/
		formatValidatorNodeNode
				.setRrequiredFormat(com.lynxspa.sdm.dictionaries.formats.CAFormat.OFIVAL);
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

		// $-- /business process/Log Ofival Import exception
		// instantiate and configure 'Log Ofival Import exception' node
		com.lynxspa.coac.nodes.logs.LogCoacException logOfivalImportExceptionNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.logs.LogCoacException.class);
		logOfivalImportExceptionNode.setId("Log Ofival Import exception");
		addNode(logOfivalImportExceptionNode);
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode.setDataSource(getSessionFactory());
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode.setStopExceptionHandling(true);
		// $-- /business process/Log Ofival Import exception/
		((DynaParamsNode) logOfivalImportExceptionNode)
				.setDynamicParamValues(
						"arguments",
						com.lynxit.utils.scripting.ELHelper
								.buildConstantExpression("Ofival"),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"fileName\")",
										java.lang.Object.class),
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"ParserNode.position\");",
										java.lang.Object.class));
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode.setCommitInNewTransaction(false);
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode.setLocale(getLocale());
		// $-- /business process/Log Ofival Import exception/
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode.setUser(getUser());
		// $-- /business process/Log Ofival Import exception/
		logOfivalImportExceptionNode
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.IMPORT_FAIL);

		// $-- /business process/OfivalParserNode
		// instantiate and configure 'OfivalParserNode' node
		com.lynxspa.coac.importers.ofival.nodes.OfivalParserNode ofivalParserNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.nodes.OfivalParserNode.class);
		ofivalParserNodeNode.setId("OfivalParserNode");
		addNode(ofivalParserNodeNode);
		// $-- /business process/OfivalParserNode/
		// $-- /business process/OfivalParserNode/
		ofivalParserNodeNode.setLocale(getLocale());
		// $-- /business process/OfivalParserNode/
		ofivalParserNodeNode.setResource(getSessionFactory());
		// $-- /business process/OfivalParserNode/
		// $-- /business process/OfivalParserNode/
		ofivalParserNodeNode.setUser(getUser());
		// $-- /business process/OfivalParserNode/
		ofivalParserNodeNode.setErrorPath(getInputOfivalErrorDirectory());

		// $-- /business process/OfivalSwitchNode
		// instantiate and configure 'OfivalSwitchNode' node
		com.lynxspa.coac.importers.ofival.nodes.OfivalSwitchNode<com.lynxspa.coac.importers.EventMessageImportBean> ofivalSwitchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.nodes.OfivalSwitchNode.class);
		ofivalSwitchNodeNode.setId("OfivalSwitchNode");
		addNode(ofivalSwitchNodeNode);
		// $-- /business process/OfivalSwitchNode/

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

		// $-- /business process/PreprocessAMP
		// instantiate and configure 'PreprocessAMP' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalAMPMap preprocessAMPNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalAMPMap.class);
		preprocessAMPNode.setId("PreprocessAMP");
		addNode(preprocessAMPNode);
		// $-- /business process/PreprocessAMP/

		// $-- /business process/PreprocessAMS
		// instantiate and configure 'PreprocessAMS' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalAMSMap preprocessAMSNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalAMSMap.class);
		preprocessAMSNode.setId("PreprocessAMS");
		addNode(preprocessAMSNode);
		// $-- /business process/PreprocessAMS/

		// $-- /business process/PreprocessCUP
		// instantiate and configure 'PreprocessCUP' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalCUPMap preprocessCUPNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalCUPMap.class);
		preprocessCUPNode.setId("PreprocessCUP");
		addNode(preprocessCUPNode);
		// $-- /business process/PreprocessCUP/

		// $-- /business process/PreprocessCVC
		// instantiate and configure 'PreprocessCVC' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalCVCMap preprocessCVCNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalCVCMap.class);
		preprocessCVCNode.setId("PreprocessCVC");
		addNode(preprocessCVCNode);
		// $-- /business process/PreprocessCVC/

		// $-- /business process/PreprocessDAC
		// instantiate and configure 'PreprocessDAC' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalDACMap preprocessDACNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalDACMap.class);
		preprocessDACNode.setId("PreprocessDAC");
		addNode(preprocessDACNode);
		// $-- /business process/PreprocessDAC/

		// $-- /business process/PreprocessDEV
		// instantiate and configure 'PreprocessDEV' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalDEVMap preprocessDEVNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalDEVMap.class);
		preprocessDEVNode.setId("PreprocessDEV");
		addNode(preprocessDEVNode);
		// $-- /business process/PreprocessDEV/

		// $-- /business process/PreprocessFUS
		// instantiate and configure 'PreprocessFUS' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalFUSMap preprocessFUSNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalFUSMap.class);
		preprocessFUSNode.setId("PreprocessFUS");
		addNode(preprocessFUSNode);
		// $-- /business process/PreprocessFUS/

		// $-- /business process/PreprocessFVL
		// instantiate and configure 'PreprocessFVL' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalFVLMap preprocessFVLNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalFVLMap.class);
		preprocessFVLNode.setId("PreprocessFVL");
		addNode(preprocessFVLNode);
		// $-- /business process/PreprocessFVL/

		// $-- /business process/PreprocessOPA
		// instantiate and configure 'PreprocessOPA' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalOPAMap preprocessOPANode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalOPAMap.class);
		preprocessOPANode.setId("PreprocessOPA");
		addNode(preprocessOPANode);
		// $-- /business process/PreprocessOPA/

		// $-- /business process/PreprocessOPV
		// instantiate and configure 'PreprocessOPV' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalOPVMap preprocessOPVNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalOPVMap.class);
		preprocessOPVNode.setId("PreprocessOPV");
		addNode(preprocessOPVNode);
		// $-- /business process/PreprocessOPV/

		// $-- /business process/PreprocessOfival
		// instantiate and configure 'PreprocessOfival' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalMap preprocessOfivalNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalMap.class);
		preprocessOfivalNode.setId("PreprocessOfival");
		addNode(preprocessOfivalNode);
		// $-- /business process/PreprocessOfival/

		// $-- /business process/PreprocessRFJ
		// instantiate and configure 'PreprocessRFJ' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalRFJMap preprocessRFJNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalRFJMap.class);
		preprocessRFJNode.setId("PreprocessRFJ");
		addNode(preprocessRFJNode);
		// $-- /business process/PreprocessRFJ/

		// $-- /business process/PreprocessSPL
		// instantiate and configure 'PreprocessSPL' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalSPLMap preprocessSPLNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalSPLMap.class);
		preprocessSPLNode.setId("PreprocessSPL");
		addNode(preprocessSPLNode);
		// $-- /business process/PreprocessSPL/

		// $-- /business process/PreprocessTHR
		// instantiate and configure 'PreprocessTHR' node
		com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalTHRMap preprocessTHRNode = Interceptors
				.createNode(com.lynxspa.coac.importers.ofival.mappings.PreprocessOfivalTHRMap.class);
		preprocessTHRNode.setId("PreprocessTHR");
		addNode(preprocessTHRNode);
		// $-- /business process/PreprocessTHR/

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
		// $-- /business process/FilterMessages/out/business process/PreprocessOfival/in
		filterMessagesNode.connectNodeToOut(preprocessOfivalNode);
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
		// $-- /business process/OfivalParserNode/out/business process/FilterMessages/in
		ofivalParserNodeNode.connectNodeToOut(filterMessagesNode);
		// $-- /business process/OfivalParserNode/exception/business process/Log Ofival Import exception/exc
		ofivalParserNodeNode
				.connectNodeToException(logOfivalImportExceptionNode);
		// $-- /business process/OfivalSwitchNode/cup/business process/PreprocessCUP/in
		ofivalSwitchNodeNode.connectNodeToCup(preprocessCUPNode);
		// $-- /business process/OfivalSwitchNode/dac/business process/PreprocessDAC/in
		ofivalSwitchNodeNode.connectNodeToDac(preprocessDACNode);
		// $-- /business process/OfivalSwitchNode/thr/business process/PreprocessTHR/in
		ofivalSwitchNodeNode.connectNodeToThr(preprocessTHRNode);
		// $-- /business process/OfivalSwitchNode/dev/business process/PreprocessDEV/in
		ofivalSwitchNodeNode.connectNodeToDev(preprocessDEVNode);
		// $-- /business process/OfivalSwitchNode/fvl/business process/PreprocessFVL/in
		ofivalSwitchNodeNode.connectNodeToFvl(preprocessFVLNode);
		// $-- /business process/OfivalSwitchNode/cvc/business process/PreprocessCVC/in
		ofivalSwitchNodeNode.connectNodeToCvc(preprocessCVCNode);
		// $-- /business process/OfivalSwitchNode/opa/business process/PreprocessOPA/in
		ofivalSwitchNodeNode.connectNodeToOpa(preprocessOPANode);
		// $-- /business process/OfivalSwitchNode/default/business process/DuplicateImportsFilter/in
		ofivalSwitchNodeNode.connectNodeToDefault(duplicateImportsFilterNode);
		// $-- /business process/OfivalSwitchNode/spl/business process/PreprocessSPL/in
		ofivalSwitchNodeNode.connectNodeToSpl(preprocessSPLNode);
		// $-- /business process/OfivalSwitchNode/rfj/business process/PreprocessRFJ/in
		ofivalSwitchNodeNode.connectNodeToRfj(preprocessRFJNode);
		// $-- /business process/OfivalSwitchNode/opv/business process/PreprocessOPV/in
		ofivalSwitchNodeNode.connectNodeToOpv(preprocessOPVNode);
		// $-- /business process/OfivalSwitchNode/amp/business process/PreprocessAMP/in
		ofivalSwitchNodeNode.connectNodeToAmp(preprocessAMPNode);
		// $-- /business process/OfivalSwitchNode/ams/business process/PreprocessAMS/in
		ofivalSwitchNodeNode.connectNodeToAms(preprocessAMSNode);
		// $-- /business process/OfivalSwitchNode/fus/business process/PreprocessFUS/in
		ofivalSwitchNodeNode.connectNodeToFus(preprocessFUSNode);
		ofivalSwitchNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/OpenFile/out/business process/OfivalParserNode/in
		openFileNode.connectNodeToOut(ofivalParserNodeNode);
		openFileNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessAMP/out/business process/DuplicateImportsFilter/in
		preprocessAMPNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessAMPNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessAMS/out/business process/DuplicateImportsFilter/in
		preprocessAMSNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessAMSNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessCUP/out/business process/DuplicateImportsFilter/in
		preprocessCUPNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessCUPNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessCVC/out/business process/DuplicateImportsFilter/in
		preprocessCVCNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessCVCNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessDAC/out/business process/DuplicateImportsFilter/in
		preprocessDACNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessDACNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessDEV/out/business process/DuplicateImportsFilter/in
		preprocessDEVNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessDEVNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessFUS/out/business process/DuplicateImportsFilter/in
		preprocessFUSNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessFUSNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessFVL/out/business process/DuplicateImportsFilter/in
		preprocessFVLNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessFVLNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessOPA/out/business process/DuplicateImportsFilter/in
		preprocessOPANode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessOPANode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessOPV/out/business process/DuplicateImportsFilter/in
		preprocessOPVNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessOPVNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessOfival/out/business process/OfivalSwitchNode/in
		preprocessOfivalNode.connectNodeToOut(ofivalSwitchNodeNode);
		preprocessOfivalNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessRFJ/out/business process/DuplicateImportsFilter/in
		preprocessRFJNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessRFJNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessSPL/out/business process/DuplicateImportsFilter/in
		preprocessSPLNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessSPLNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PreprocessTHR/out/business process/DuplicateImportsFilter/in
		preprocessTHRNode.connectNodeToOut(duplicateImportsFilterNode);
		preprocessTHRNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/SaveImportedMessageProcess/importedMessage/business process/Commit/in
		saveImportedMessageProcess.asImportedMessageOutput()
				.connect(commitNode);
		// $-- /business process/SaveImportedMessageProcess/exc/business process/ExceptionRollbackNode/exc
		saveImportedMessageProcess.asExcOutput().connect(
				exceptionRollbackNodeNode);

		// link inputs
		// $-- /business process/OfivalFileCreated/business process/LiveCycleValidatorNode/in
		ofivalFileCreated.connectNodeToOut(liveCycleValidatorNodeNode);

		commitNode.init();
		duplicateImportsFilterNode.init();
		exceptionRollbackNodeNode.init();
		filterMessagesNode.init();
		formatValidatorNodeNode.init();
		liveCycleValidatorNodeNode.init();
		logOfivalImportExceptionNode.init();
		ofivalParserNodeNode.init();
		ofivalSwitchNodeNode.init();
		openFileNode.init();
		preprocessAMPNode.init();
		preprocessAMSNode.init();
		preprocessCUPNode.init();
		preprocessCVCNode.init();
		preprocessDACNode.init();
		preprocessDEVNode.init();
		preprocessFUSNode.init();
		preprocessFVLNode.init();
		preprocessOPANode.init();
		preprocessOPVNode.init();
		preprocessOfivalNode.init();
		preprocessRFJNode.init();
		preprocessSPLNode.init();
		preprocessTHRNode.init();
	}

}