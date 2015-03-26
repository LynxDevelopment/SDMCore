package com.lynxspa.coac.normalizer.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/businessprocess/Normalizer.fpmprocess")
public class NormalizerProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String locale_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "locale")
	public String getLocale() {
		return locale_;
	}

	public void setLocale(String value) {
		this.locale_ = value;
	}

	private Integer maxResultsToLoad_;

	@ConfigParam(description = "param1", required = true, dynamic = false, name = "maxResultsToLoad")
	public Integer getMaxResultsToLoad() {
		return maxResultsToLoad_;
	}

	public void setMaxResultsToLoad(Integer value) {
		this.maxResultsToLoad_ = value;
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

	public NormalizerProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/Add EventCollected to Message
		// instantiate and configure 'Add EventCollected to Message' node
		com.lynxspa.coac.normalizer.mappings.GetCAEventMessageFromNormalizableBeanMap addEventCollectedToMessageNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.GetCAEventMessageFromNormalizableBeanMap.class);
		addEventCollectedToMessageNode.setId("Add EventCollected to Message");
		addNode(addEventCollectedToMessageNode);
		// $-- /business process/Add EventCollected to Message/

		// $-- /business process/CAEventToQuestionMap
		// instantiate and configure 'CAEventToQuestionMap' node
		com.lynxspa.coac.normalizer.mappings.CAEventToQuestionMap cAEventToQuestionMapNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.CAEventToQuestionMap.class);
		cAEventToQuestionMapNode.setId("CAEventToQuestionMap");
		addNode(cAEventToQuestionMapNode);
		// $-- /business process/CAEventToQuestionMap/

		// $-- /business process/Commit
		// instantiate and configure 'Commit' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit commitNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		commitNode.setId("Commit");
		addNode(commitNode);
		// $-- /business process/Commit/
		commitNode.setDataSource(getStatefullSession());
		// $-- /business process/Commit/
		commitNode.setCommitEvery(1);
		// $-- /business process/Commit/

		// $-- /business process/CommitNotNormalizable
		// instantiate and configure 'CommitNotNormalizable' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit commitNotNormalizableNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardCommit.class);
		commitNotNormalizableNode.setId("CommitNotNormalizable");
		addNode(commitNotNormalizableNode);
		// $-- /business process/CommitNotNormalizable/
		commitNotNormalizableNode.setDataSource(getStatefullSession());
		// $-- /business process/CommitNotNormalizable/
		commitNotNormalizableNode.setCommitEvery(1);
		// $-- /business process/CommitNotNormalizable/

		// $-- /business process/ForkNode1
		// instantiate and configure 'ForkNode1' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> forkNode1Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode1Node.setId("ForkNode1");
		addNode(forkNode1Node);
		// $-- /business process/ForkNode1/
		// $-- /business process/ForkNode1/
		forkNode1Node.setCloneMessage(false);

		// $-- /business process/ForkNode11
		// instantiate and configure 'ForkNode11' node
		com.lynxit.fpm.nodes.internal.ForkNode<java.util.Iterator<com.lynxspa.sdm.entities.events.answers.CAQuestions>> forkNode11Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode11Node.setId("ForkNode11");
		addNode(forkNode11Node);
		// $-- /business process/ForkNode11/
		// $-- /business process/ForkNode11/
		forkNode11Node.setCloneMessage(false);

		// $-- /business process/GenerateDefaultAnswer
		// instantiate and configure 'GenerateDefaultAnswer' node
		com.lynxspa.coac.normalizer.nodes.GenerateDefaultAnswer generateDefaultAnswerNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.GenerateDefaultAnswer.class);
		generateDefaultAnswerNode.setId("GenerateDefaultAnswer");
		addNode(generateDefaultAnswerNode);
		// $-- /business process/GenerateDefaultAnswer/
		generateDefaultAnswerNode.setResource(getStatefullSession());
		// $-- /business process/GenerateDefaultAnswer/
		// $-- /business process/GenerateDefaultAnswer/
		generateDefaultAnswerNode.setUser(getUser());

		// $-- /business process/GenerateQuestionsEventProcessor
		// instantiate and configure 'GenerateQuestionsEventProcessor' node
		com.lynxspa.coac.normalizer.nodes.GenerateQuestionsEventProcessor generateQuestionsEventProcessorNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.GenerateQuestionsEventProcessor.class);
		generateQuestionsEventProcessorNode
				.setId("GenerateQuestionsEventProcessor");
		addNode(generateQuestionsEventProcessorNode);
		// $-- /business process/GenerateQuestionsEventProcessor/
		generateQuestionsEventProcessorNode.setResource(getStatefullSession());
		// $-- /business process/GenerateQuestionsEventProcessor/
		// $-- /business process/GenerateQuestionsEventProcessor/
		generateQuestionsEventProcessorNode.setUser(getUser());

		// $-- /business process/LiveCycleValidatorNode
		// instantiate and configure 'LiveCycleValidatorNode' node
		com.lynxspa.coac.nodes.LiveCycleValidatorNode<java.lang.Object> liveCycleValidatorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.LiveCycleValidatorNode.class);
		liveCycleValidatorNodeNode.setId("LiveCycleValidatorNode");
		addNode(liveCycleValidatorNodeNode);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode
				.setRequiredLiveCycle(com.lynxspa.sdm.dictionaries.CALiveCycle.NORMALIZE_MESSAGES);
		// $-- /business process/LiveCycleValidatorNode/
		liveCycleValidatorNodeNode.setResource(getStatefullSession());
		// $-- /business process/LiveCycleValidatorNode/

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
				.setLog(com.lynxspa.sdm.dictionaries.logs.LogErrorDict.NORMALIZATION_FAIL);

		// $-- /business process/Message to Normalizable
		// instantiate and configure 'Message to Normalizable' node
		com.lynxspa.coac.normalizer.mappings.CAEventMessageToMessageNormalizeBeanMap messageToNormalizableNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.CAEventMessageToMessageNormalizeBeanMap.class);
		messageToNormalizableNode.setId("Message to Normalizable");
		addNode(messageToNormalizableNode);
		// $-- /business process/Message to Normalizable/

		// $-- /business process/OperationDecisionSwitch
		// instantiate and configure 'OperationDecisionSwitch' node
		com.lynxspa.coac.normalizer.nodes.OperationDecisionSwitch operationDecisionSwitchNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.OperationDecisionSwitch.class);
		operationDecisionSwitchNode.setId("OperationDecisionSwitch");
		addNode(operationDecisionSwitchNode);
		// $-- /business process/OperationDecisionSwitch/

		// $-- /business process/PatchedIteratorNode
		// instantiate and configure 'PatchedIteratorNode' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.messages.CAEventMessage> patchedIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNodeNode.setId("PatchedIteratorNode");
		addNode(patchedIteratorNodeNode);
		// $-- /business process/PatchedIteratorNode/

		// $-- /business process/PatchedIteratorNode1
		// instantiate and configure 'PatchedIteratorNode1' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.answers.CAQuestions> patchedIteratorNode1Node = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNode1Node.setId("PatchedIteratorNode1");
		addNode(patchedIteratorNode1Node);
		// $-- /business process/PatchedIteratorNode1/

		// $-- /business process/PatchedIteratorNode11
		// instantiate and configure 'PatchedIteratorNode11' node
		com.lynxspa.fpm.nodes.patch.PatchedIteratorNode<com.lynxspa.sdm.entities.events.answers.CAEventHoldingAnswer> patchedIteratorNode11Node = Interceptors
				.createNode(com.lynxspa.fpm.nodes.patch.PatchedIteratorNode.class);
		patchedIteratorNode11Node.setId("PatchedIteratorNode11");
		addNode(patchedIteratorNode11Node);
		// $-- /business process/PatchedIteratorNode11/

		// $-- /business process/Read
		// instantiate and configure 'Read' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead<java.lang.Object, com.lynxspa.sdm.entities.events.messages.CAEventMessage> readNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.class);
		readNode.setId("Read");
		addNode(readNode);
		// $-- /business process/Read/
		readNode.setReadOnly(false);
		// $-- /business process/Read/
		readNode.setQuery("from CAEventMessage where auditor.deleted=:par0 and operationStatus.state.id.code=:par1 order by senderTimestamp,id asc");
		// $-- /business process/Read/
		readNode.setEntityIdField("id");
		// $-- /business process/Read/
		readNode.setResource(getStatefullSession());
		// $-- /business process/Read/
		readNode.setEntityClass(com.lynxspa.sdm.entities.events.messages.CAEventMessage.class);
		// $-- /business process/Read/
		readNode.setNumResults(getMaxResultsToLoad());
		// $-- /business process/Read/
		readNode.setParameters(com.lynxit.utils.beans.BeanUtils.createList(
				java.lang.Object.class, false, "PNRM"));
		// $-- /business process/Read/
		// $-- /business process/Read/
		readNode.setReadMode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardRead.HibernateReadModes.CURSOR_IDENTIFIER);

		// $-- /business process/Rollback
		// instantiate and configure 'Rollback' node
		com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode rollbackNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.ExceptionRollbackNode.class);
		rollbackNode.setId("Rollback");
		addNode(rollbackNode);
		// $-- /business process/Rollback/
		rollbackNode.setDataSource(getStatefullSession());
		// $-- /business process/Rollback/
		rollbackNode.setStopExceptionHandling(false);
		// $-- /business process/Rollback/

		// $-- /business process/Save
		// instantiate and configure 'Save' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardSave saveNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardSave.class);
		saveNode.setId("Save");
		addNode(saveNode);
		// $-- /business process/Save/
		saveNode.setDataSource(getStatefullSession());
		// $-- /business process/Save/
		saveNode.setFlush(true);
		// $-- /business process/Save/
		// $-- /business process/Save/
		saveNode.setUser(getUser());

		// $-- /business process/To NORMALIZED
		// instantiate and configure 'To NORMALIZED' node
		com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode toNORMALIZEDNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode.class);
		toNORMALIZEDNode.setId("To NORMALIZED");
		addNode(toNORMALIZEDNode);
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode.setActiveMessageTransition(false);
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode.setLocale(getLocale());
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.NORM);
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode.setResource(getStatefullSession());
		// $-- /business process/To NORMALIZED/
		// $-- /business process/To NORMALIZED/
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode.setCreateLog(false);
		// $-- /business process/To NORMALIZED/
		// $-- /business process/To NORMALIZED/
		// $-- /business process/To NORMALIZED/
		// $-- /business process/To NORMALIZED/
		toNORMALIZEDNode.setUser(getUser());

		// $-- /business process/To NORMFAIL
		// instantiate and configure 'To NORMFAIL' node
		com.lynxspa.coac.nodes.workflow.EventMessageChangeStateExceptionNode toNORMFAILNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventMessageChangeStateExceptionNode.class);
		toNORMFAILNode.setId("To NORMFAIL");
		addNode(toNORMFAILNode);
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setCreateLog(true);
		// $-- /business process/To NORMFAIL/
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setStopExceptionHandling(true);
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setCommit(true);
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setLocale(getLocale());
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setResource(getStatefullSession());
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.NRFA);
		// $-- /business process/To NORMFAIL/
		// $-- /business process/To NORMFAIL/
		// $-- /business process/To NORMFAIL/
		toNORMFAILNode.setUser(getUser());

		// $-- /business process/To NOTNORMALIZABLE
		// instantiate and configure 'To NOTNORMALIZABLE' node
		com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode toNOTNORMALIZABLENode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventMessageChangeStateNode.class);
		toNOTNORMALIZABLENode.setId("To NOTNORMALIZABLE");
		addNode(toNOTNORMALIZABLENode);
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode.setActiveMessageTransition(true);
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode.setLocale(getLocale());
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.NNRM);
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode.setResource(getStatefullSession());
		// $-- /business process/To NOTNORMALIZABLE/
		// $-- /business process/To NOTNORMALIZABLE/
		((DynaParamsNode) toNOTNORMALIZABLENode)
				.setDynamicParam(
						"message",
						com.lynxit.utils.scripting.Language.BEAN_SHELL
								.createExpression(
										"process.getContextAttribute(\"not.normalizable.reason\")",
										com.lynxspa.sdm.dictionaries.logs.LogInfoDict.class));
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode.setCreateLog(false);
		// $-- /business process/To NOTNORMALIZABLE/
		// $-- /business process/To NOTNORMALIZABLE/
		// $-- /business process/To NOTNORMALIZABLE/
		// $-- /business process/To NOTNORMALIZABLE/
		toNOTNORMALIZABLENode.setUser(getUser());

		// $-- /business process/UpdateMessage
		// instantiate and configure 'UpdateMessage' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate updateMessageNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate.class);
		updateMessageNode.setId("UpdateMessage");
		addNode(updateMessageNode);
		// $-- /business process/UpdateMessage/
		updateMessageNode.setDataSource(getStatefullSession());
		// $-- /business process/UpdateMessage/
		updateMessageNode.setFlush(true);
		// $-- /business process/UpdateMessage/
		// $-- /business process/UpdateMessage/
		updateMessageNode.setUser(getUser());

		// $-- /business process/is Normalizable
		// instantiate and configure 'is Normalizable' node
		com.lynxspa.coac.normalizer.nodes.NormalizationValidatorNode isNormalizableNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.NormalizationValidatorNode.class);
		isNormalizableNode.setId("is Normalizable");
		addNode(isNormalizableNode);
		// $-- /business process/is Normalizable/
		isNormalizableNode.setResource(getStatefullSession());
		// $-- /business process/is Normalizable/

		// instantiate and configure subprocesses
		// $-- /business process/CancelProcess
		// instantiate and configure 'CancelProcess' subprocess
		com.lynxspa.coac.normalizer.operationcancel.businessprocess.CancelEventProcess cancelProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationcancel.businessprocess.CancelEventProcess.class,
						"CancelProcess");
		addSubProcess(cancelProcess);
		// $-- /business process/CancelProcess/
		cancelProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/CancelProcess/
		cancelProcess.setLocale(getLocale());
		// $-- /business process/CancelProcess/
		// $-- /business process/CancelProcess/
		cancelProcess.setUser("COACNORMALIZERCANCEL");

		cancelProcess.initialize();
		// $-- /business process/NewProcess
		// instantiate and configure 'NewProcess' subprocess
		com.lynxspa.coac.normalizer.operationnew.businessprocess.NewEventProcess newProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationnew.businessprocess.NewEventProcess.class,
						"NewProcess");
		addSubProcess(newProcess);
		// $-- /business process/NewProcess/
		newProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/NewProcess/
		newProcess.setLocale(getLocale());
		// $-- /business process/NewProcess/
		// $-- /business process/NewProcess/
		newProcess.setUser("COACNORMALIZERNEW");

		newProcess.initialize();
		// $-- /business process/RepeatProcess
		// instantiate and configure 'RepeatProcess' subprocess
		com.lynxspa.coac.normalizer.operationrepeat.businessprocess.RepeatEventProcess repeatProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationrepeat.businessprocess.RepeatEventProcess.class,
						"RepeatProcess");
		addSubProcess(repeatProcess);
		// $-- /business process/RepeatProcess/
		repeatProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/RepeatProcess/
		repeatProcess.setLocale(getLocale());
		// $-- /business process/RepeatProcess/
		// $-- /business process/RepeatProcess/
		repeatProcess.setUser("COACNORMALIZERREPEAT");

		repeatProcess.initialize();
		// $-- /business process/UpdateProcess
		// instantiate and configure 'UpdateProcess' subprocess
		com.lynxspa.coac.normalizer.operationupdate.businessprocess.UpdateEventProcess updateProcess = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationupdate.businessprocess.UpdateEventProcess.class,
						"UpdateProcess");
		addSubProcess(updateProcess);
		// $-- /business process/UpdateProcess/
		updateProcess.setStatefullSession(getStatefullSession());
		// $-- /business process/UpdateProcess/
		updateProcess.setLocale(getLocale());
		// $-- /business process/UpdateProcess/
		// $-- /business process/UpdateProcess/
		updateProcess.setUser("COACNORMALIZERUPDATE");

		updateProcess.initialize();

		// link nodes
		// $-- /business process/Add EventCollected to Message/out/business process/To NORMALIZED/in
		addEventCollectedToMessageNode.connectNodeToOut(toNORMALIZEDNode);
		addEventCollectedToMessageNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CAEventToQuestionMap/out/business process/GenerateQuestionsEventProcessor/in
		cAEventToQuestionMapNode
				.connectNodeToOut(generateQuestionsEventProcessorNode);
		cAEventToQuestionMapNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		commitNode.connectNodeToException(getDefaultExceptionHandlerNode());
		commitNotNormalizableNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/CAEventToQuestionMap/in
		forkNode1Node.connectNodeToTwo(cAEventToQuestionMapNode);
		// $-- /business process/ForkNode1/one/business process/Add EventCollected to Message/in
		forkNode1Node.connectNodeToOne(addEventCollectedToMessageNode);
		forkNode1Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode11/two/business process/GenerateDefaultAnswer/in
		forkNode11Node.connectNodeToTwo(generateDefaultAnswerNode);
		// $-- /business process/ForkNode11/one/business process/PatchedIteratorNode1/in
		forkNode11Node.connectNodeToOne(patchedIteratorNode1Node);
		forkNode11Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GenerateDefaultAnswer/out/business process/PatchedIteratorNode11/in
		generateDefaultAnswerNode.connectNodeToOut(patchedIteratorNode11Node);
		generateDefaultAnswerNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GenerateQuestionsEventProcessor/out/business process/ForkNode11/in
		generateQuestionsEventProcessorNode.connectNodeToOut(forkNode11Node);
		generateQuestionsEventProcessorNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/LiveCycleValidatorNode/out/business process/Read/in
		liveCycleValidatorNodeNode.connectNodeToOut(readNode);
		// $-- /business process/LiveCycleValidatorNode/exception/business process/LogCoacException/exc
		liveCycleValidatorNodeNode.connectNodeToException(logCoacExceptionNode);
		// $-- /business process/Message to Normalizable/out/business process/OperationDecisionSwitch/in
		messageToNormalizableNode.connectNodeToOut(operationDecisionSwitchNode);
		// $-- /business process/Message to Normalizable/exception/business process/Rollback/exc
		messageToNormalizableNode.connectNodeToException(rollbackNode);
		// $-- /business process/OperationDecisionSwitch/update/business process/UpdateProcess/normalizableBean
		operationDecisionSwitchNode.connectNodeToUpdate(updateProcess
				.asNormalizableBeanInput());
		// $-- /business process/OperationDecisionSwitch/new/business process/NewProcess/normalizableBean
		operationDecisionSwitchNode.connectNodeToNew(newProcess
				.asNormalizableBeanInput());
		// $-- /business process/OperationDecisionSwitch/cancel/business process/CancelProcess/normalizableBean
		operationDecisionSwitchNode.connectNodeToCancel(cancelProcess
				.asNormalizableBeanInput());
		// $-- /business process/OperationDecisionSwitch/repeat/business process/RepeatProcess/normalizableBean
		operationDecisionSwitchNode.connectNodeToRepeat(repeatProcess
				.asNormalizableBeanInput());
		operationDecisionSwitchNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode/out/business process/is Normalizable/in
		patchedIteratorNodeNode.connectNodeToOut(isNormalizableNode);
		patchedIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode1/out/business process/Save/in
		patchedIteratorNode1Node.connectNodeToOut(saveNode);
		patchedIteratorNode1Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/PatchedIteratorNode11/out/business process/Save/in
		patchedIteratorNode11Node.connectNodeToOut(saveNode);
		patchedIteratorNode11Node
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Read/out/business process/PatchedIteratorNode/in
		readNode.connectNodeToOut(patchedIteratorNodeNode);
		readNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Save/out/business process/Commit/in
		saveNode.connectNodeToOut(commitNode);
		saveNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/To NORMALIZED/out/business process/UpdateMessage/in
		toNORMALIZEDNode.connectNodeToOut(updateMessageNode);
		toNORMALIZEDNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/To NOTNORMALIZABLE/out/business process/CommitNotNormalizable/in
		toNOTNORMALIZABLENode.connectNodeToOut(commitNotNormalizableNode);
		toNOTNORMALIZABLENode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/UpdateMessage/out/business process/Commit/in
		updateMessageNode.connectNodeToOut(commitNode);
		updateMessageNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/is Normalizable/notNormalizable/business process/To NOTNORMALIZABLE/in
		isNormalizableNode.connectNodeToNotNormalizable(toNOTNORMALIZABLENode);
		// $-- /business process/is Normalizable/normalizable/business process/Message to Normalizable/in
		isNormalizableNode.connectNodeToNormalizable(messageToNormalizableNode);
		// $-- /business process/is Normalizable/exception/business process/To NORMFAIL/exc
		isNormalizableNode.connectNodeToException(toNORMFAILNode);

		// link subprocesses
		// $-- /business process/CancelProcess/normalizableOutputBean/business process/ForkNode1/in
		cancelProcess.asNormalizableOutputBeanOutput().connect(forkNode1Node);
		cancelProcess.asExcOutput().connect(getDefaultStopperNode());
		// $-- /business process/NewProcess/normalizableOutputBean/business process/ForkNode1/in
		newProcess.asNormalizableOutputBeanOutput().connect(forkNode1Node);
		newProcess.asExcOutput().connect(getDefaultStopperNode());
		// $-- /business process/RepeatProcess/normalizableOutputBean/business process/ForkNode1/in
		repeatProcess.asNormalizableOutputBeanOutput().connect(forkNode1Node);
		repeatProcess.asExcOutput().connect(getDefaultStopperNode());
		// $-- /business process/UpdateProcess/normalizableOutputBean/business process/ForkNode1/in
		updateProcess.asNormalizableOutputBeanOutput().connect(forkNode1Node);
		updateProcess.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/input/business process/LiveCycleValidatorNode/in
		input.connectNodeToOut(liveCycleValidatorNodeNode);

		addEventCollectedToMessageNode.init();
		cAEventToQuestionMapNode.init();
		commitNode.init();
		commitNotNormalizableNode.init();
		forkNode1Node.init();
		forkNode11Node.init();
		generateDefaultAnswerNode.init();
		generateQuestionsEventProcessorNode.init();
		liveCycleValidatorNodeNode.init();
		logCoacExceptionNode.init();
		messageToNormalizableNode.init();
		operationDecisionSwitchNode.init();
		patchedIteratorNodeNode.init();
		patchedIteratorNode1Node.init();
		patchedIteratorNode11Node.init();
		readNode.init();
		rollbackNode.init();
		saveNode.init();
		toNORMALIZEDNode.init();
		toNORMFAILNode.init();
		toNOTNORMALIZABLENode.init();
		updateMessageNode.init();
		isNormalizableNode.init();
	}

}