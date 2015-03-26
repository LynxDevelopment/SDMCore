package com.lynxspa.coac.normalizer.operationupdate.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationupdate/businessprocess/UpdateEvent.fpmprocess")
public class UpdateEventProcess extends BusinessProcess {
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

	// input 'normalizableBean'
	// $-- /business process/normalizableBean
	private com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> normalizableBean;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> asNormalizableBeanInput() {
		return normalizableBean;
	}

	/*
	 * outputs declarations
	 */

	// output 'normalizableOutputBean'
	// $-- /business process/normalizableOutputBean
	private com.lynxit.fpm.BusinessProcessOutput<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> normalizableOutputBean;

	public com.lynxit.fpm.BusinessProcessOutput<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> asNormalizableOutputBeanOutput() {
		return normalizableOutputBean;
	}

	public UpdateEventProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'normalizableBean' input
		normalizableBean = addInput("normalizableBean");

		// instantiate and configure outputs
		// instantiate and configure 'normalizableOutputBean' output
		normalizableOutputBean = addOutput("normalizableOutputBean");

		// instantiate and configure nodes
		// $-- /business process/CallCompleteAnalyzerProcessor
		// instantiate and configure 'CallCompleteAnalyzerProcessor' node
		com.lynxspa.coac.normalizer.nodes.CallCompleteAnalyzerProcessor callCompleteAnalyzerProcessorNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.CallCompleteAnalyzerProcessor.class);
		callCompleteAnalyzerProcessorNode
				.setId("CallCompleteAnalyzerProcessor");
		addNode(callCompleteAnalyzerProcessorNode);
		// $-- /business process/CallCompleteAnalyzerProcessor/
		callCompleteAnalyzerProcessorNode.setLocale(getLocale());
		// $-- /business process/CallCompleteAnalyzerProcessor/
		callCompleteAnalyzerProcessorNode.setResource(getStatefullSession());
		// $-- /business process/CallCompleteAnalyzerProcessor/
		// $-- /business process/CallCompleteAnalyzerProcessor/
		callCompleteAnalyzerProcessorNode.setUser(getUser());

		// $-- /business process/CallNormalizeProcessor
		// instantiate and configure 'CallNormalizeProcessor' node
		com.lynxspa.coac.normalizer.nodes.CallNormalizeProcessor callNormalizeProcessorNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.CallNormalizeProcessor.class);
		callNormalizeProcessorNode.setId("CallNormalizeProcessor");
		addNode(callNormalizeProcessorNode);
		// $-- /business process/CallNormalizeProcessor/
		callNormalizeProcessorNode.setLocale(getLocale());
		// $-- /business process/CallNormalizeProcessor/
		callNormalizeProcessorNode.setResource(getStatefullSession());
		// $-- /business process/CallNormalizeProcessor/
		// $-- /business process/CallNormalizeProcessor/
		callNormalizeProcessorNode.setUser(getUser());

		// $-- /business process/CastNode
		// instantiate and configure 'CastNode' node
		com.lynxit.fpm.nodes.internal.CastNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> castNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.CastNode.class);
		castNodeNode.setId("CastNode");
		addNode(castNodeNode);
		// $-- /business process/CastNode/

		// $-- /business process/DecisionNode
		// instantiate and configure 'DecisionNode' node
		com.lynxit.fpm.nodes.internal.DecisionNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> decisionNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.DecisionNode.class);
		decisionNodeNode.setId("DecisionNode");
		addNode(decisionNodeNode);
		// $-- /business process/DecisionNode/
		((DynaParamsNode) decisionNodeNode).setDynamicParam("condition",
				com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression(
								"(message.getPreviousEventCollected()!=null)",
								java.lang.Boolean.class));
		// $-- /business process/DecisionNode/

		// $-- /business process/FindPreviousEventNode
		// instantiate and configure 'FindPreviousEventNode' node
		com.lynxspa.coac.normalizer.operationupdate.nodes.UpdateFindPreviousEventNode findPreviousEventNodeNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.nodes.UpdateFindPreviousEventNode.class);
		findPreviousEventNodeNode.setId("FindPreviousEventNode");
		addNode(findPreviousEventNodeNode);
		// $-- /business process/FindPreviousEventNode/
		findPreviousEventNodeNode.setOnNotFoundFail(true);
		// $-- /business process/FindPreviousEventNode/
		findPreviousEventNodeNode.setResource(getStatefullSession());
		// $-- /business process/FindPreviousEventNode/

		// $-- /business process/ForkNode
		// instantiate and configure 'ForkNode' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> forkNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNodeNode.setId("ForkNode");
		addNode(forkNodeNode);
		// $-- /business process/ForkNode/
		// $-- /business process/ForkNode/
		forkNodeNode.setCloneMessage(false);

		// $-- /business process/ForkNode1
		// instantiate and configure 'ForkNode1' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> forkNode1Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode1Node.setId("ForkNode1");
		addNode(forkNode1Node);
		// $-- /business process/ForkNode1/
		// $-- /business process/ForkNode1/
		forkNode1Node.setCloneMessage(false);

		// $-- /business process/ForkNode2
		// instantiate and configure 'ForkNode2' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> forkNode2Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode2Node.setId("ForkNode2");
		addNode(forkNode2Node);
		// $-- /business process/ForkNode2/
		// $-- /business process/ForkNode2/
		forkNode2Node.setCloneMessage(false);

		// $-- /business process/ForkNode3
		// instantiate and configure 'ForkNode3' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> forkNode3Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode3Node.setId("ForkNode3");
		addNode(forkNode3Node);
		// $-- /business process/ForkNode3/
		// $-- /business process/ForkNode3/
		forkNode3Node.setCloneMessage(false);

		// $-- /business process/Get Authorized Group
		// instantiate and configure 'Get Authorized Group' node
		com.lynxspa.coac.normalizer.mappings.GetEventGroupMap getAuthorizedGroupNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.GetEventGroupMap.class);
		getAuthorizedGroupNode.setId("Get Authorized Group");
		addNode(getAuthorizedGroupNode);
		// $-- /business process/Get Authorized Group/

		// $-- /business process/Get MR Group
		// instantiate and configure 'Get MR Group' node
		com.lynxspa.coac.normalizer.mappings.GetEventGroupMap getMRGroupNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.GetEventGroupMap.class);
		getMRGroupNode.setId("Get MR Group");
		addNode(getMRGroupNode);
		// $-- /business process/Get MR Group/

		// $-- /business process/Get Sent Event
		// instantiate and configure 'Get Sent Event' node
		com.lynxspa.coac.normalizer.mappings.GetEventGroupMap getSentEventNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.GetEventGroupMap.class);
		getSentEventNode.setId("Get Sent Event");
		addNode(getSentEventNode);
		// $-- /business process/Get Sent Event/

		// $-- /business process/ManualMapDetailExtensionUpdateToCAEventDetailExtended
		// instantiate and configure 'ManualMapDetailExtensionUpdateToCAEventDetailExtended' node
		com.lynxspa.coac.normalizer.nodes.ManualMapDetailExtensionUpdateToCAEventDetailExtended manualMapDetailExtensionUpdateToCAEventDetailExtendedNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.ManualMapDetailExtensionUpdateToCAEventDetailExtended.class);
		manualMapDetailExtensionUpdateToCAEventDetailExtendedNode
				.setId("ManualMapDetailExtensionUpdateToCAEventDetailExtended");
		addNode(manualMapDetailExtensionUpdateToCAEventDetailExtendedNode);
		// $-- /business process/ManualMapDetailExtensionUpdateToCAEventDetailExtended/

		// $-- /business process/ManualMapNormalizableUpdateAdapterToCAEventDetail
		// instantiate and configure 'ManualMapNormalizableUpdateAdapterToCAEventDetail' node
		com.lynxspa.coac.normalizer.nodes.ManualMapNormalizableUpdateAdapterToCAEventDetail manualMapNormalizableUpdateAdapterToCAEventDetailNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.ManualMapNormalizableUpdateAdapterToCAEventDetail.class);
		manualMapNormalizableUpdateAdapterToCAEventDetailNode
				.setId("ManualMapNormalizableUpdateAdapterToCAEventDetail");
		addNode(manualMapNormalizableUpdateAdapterToCAEventDetailNode);
		// $-- /business process/ManualMapNormalizableUpdateAdapterToCAEventDetail/

		// $-- /business process/NormalizationUpdateExtensionsIteratorNode
		// instantiate and configure 'NormalizationUpdateExtensionsIteratorNode' node
		com.lynxspa.coac.normalizer.operationupdate.nodes.NormalizationUpdateExtensionsIteratorNode normalizationUpdateExtensionsIteratorNodeNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.nodes.NormalizationUpdateExtensionsIteratorNode.class);
		normalizationUpdateExtensionsIteratorNodeNode
				.setId("NormalizationUpdateExtensionsIteratorNode");
		addNode(normalizationUpdateExtensionsIteratorNodeNode);
		// $-- /business process/NormalizationUpdateExtensionsIteratorNode/
		normalizationUpdateExtensionsIteratorNodeNode
				.setResource(getStatefullSession());
		// $-- /business process/NormalizationUpdateExtensionsIteratorNode/

		// $-- /business process/Promote to Updater
		// instantiate and configure 'Promote to Updater' node
		com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap promoteToUpdaterNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap.class);
		promoteToUpdaterNode.setId("Promote to Updater");
		addNode(promoteToUpdaterNode);
		// $-- /business process/Promote to Updater/

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

		// $-- /business process/SwitchNode
		// instantiate and configure 'SwitchNode' node
		com.lynxspa.coac.normalizer.operationupdate.nodes.IsNormalizableUpdateAdapterSwitch switchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.nodes.IsNormalizableUpdateAdapterSwitch.class);
		switchNodeNode.setId("SwitchNode");
		addNode(switchNodeNode);
		// $-- /business process/SwitchNode/

		// $-- /business process/To RepeatSendAsCancel
		// instantiate and configure 'To RepeatSendAsCancel' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode toRepeatSendAsCancelNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		toRepeatSendAsCancelNode.setId("To RepeatSendAsCancel");
		addNode(toRepeatSendAsCancelNode);
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setActiveMessageTransition(true);
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setLocale(getLocale());
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.TBKC);
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setResource(getStatefullSession());
		// $-- /business process/To RepeatSendAsCancel/
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.UPDATE_OVER_SENTEVENT);
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setCreateLog(false);
		// $-- /business process/To RepeatSendAsCancel/
		// $-- /business process/To RepeatSendAsCancel/
		// $-- /business process/To RepeatSendAsCancel/
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setUser(getUser());

		// $-- /business process/To RollbackAuthorization
		// instantiate and configure 'To RollbackAuthorization' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode toRollbackAuthorizationNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		toRollbackAuthorizationNode.setId("To RollbackAuthorization");
		addNode(toRollbackAuthorizationNode);
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setActiveMessageTransition(true);
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setLocale(getLocale());
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.PMRS);
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setResource(getStatefullSession());
		// $-- /business process/To RollbackAuthorization/
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.UPDATE_OVER_AUTHORIZED);
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setCreateLog(false);
		// $-- /business process/To RollbackAuthorization/
		// $-- /business process/To RollbackAuthorization/
		// $-- /business process/To RollbackAuthorization/
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setUser(getUser());

		// $-- /business process/To RollbackMasterRecord
		// instantiate and configure 'To RollbackMasterRecord' node
		com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode toRollbackMasterRecordNode = Interceptors
				.createNode(com.lynxspa.coac.nodes.workflow.EventGroupChangeStateNode.class);
		toRollbackMasterRecordNode.setId("To RollbackMasterRecord");
		addNode(toRollbackMasterRecordNode);
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setActiveMessageTransition(true);
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setLocale(getLocale());
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode
				.setState(com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTGROUPFlow.PMRS);
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setResource(getStatefullSession());
		// $-- /business process/To RollbackMasterRecord/
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.UPDATE_OVER_MASTERRECORD);
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setCreateLog(false);
		// $-- /business process/To RollbackMasterRecord/
		// $-- /business process/To RollbackMasterRecord/
		// $-- /business process/To RollbackMasterRecord/
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setUser(getUser());

		// $-- /business process/Update
		// instantiate and configure 'Update' node
		com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate updateNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.hibernate.HibernateStandardUpdate.class);
		updateNode.setId("Update");
		addNode(updateNode);
		// $-- /business process/Update/
		updateNode.setDataSource(getStatefullSession());
		// $-- /business process/Update/
		updateNode.setFlush(true);
		// $-- /business process/Update/
		// $-- /business process/Update/
		updateNode.setUser(getUser());

		// $-- /business process/Update EventCollected
		// instantiate and configure 'Update EventCollected' node
		com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventCollectedMap updateEventCollectedNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventCollectedMap.class);
		updateEventCollectedNode.setId("Update EventCollected");
		addNode(updateEventCollectedNode);
		// $-- /business process/Update EventCollected/

		// $-- /business process/Update EventDetail
		// instantiate and configure 'Update EventDetail' node
		com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventDetailsMap updateEventDetailNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventDetailsMap.class);
		updateEventDetailNode.setId("Update EventDetail");
		addNode(updateEventDetailNode);
		// $-- /business process/Update EventDetail/

		// $-- /business process/Update EventDetailExtensions
		// instantiate and configure 'Update EventDetailExtensions' node
		com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventDetailsExtensionMap updateEventDetailExtensionsNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.mappings.UpdateEventDetailsExtensionMap.class);
		updateEventDetailExtensionsNode.setId("Update EventDetailExtensions");
		addNode(updateEventDetailExtensionsNode);
		// $-- /business process/Update EventDetailExtensions/

		// $-- /business process/UpdateEffects
		// instantiate and configure 'UpdateEffects' node
		com.lynxspa.coac.normalizer.operationupdate.nodes.EventGroupUpdateEffectsSwitch updateEffectsNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationupdate.nodes.EventGroupUpdateEffectsSwitch.class);
		updateEffectsNode.setId("UpdateEffects");
		addNode(updateEffectsNode);
		// $-- /business process/UpdateEffects/

		// instantiate and configure subprocesses
		// $-- /business process/Process as new message
		// instantiate and configure 'Process as new message' subprocess
		com.lynxspa.coac.normalizer.operationnew.businessprocess.NewEventProcess processAsNewMessage = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationnew.businessprocess.NewEventProcess.class,
						"Process as new message");
		addSubProcess(processAsNewMessage);
		// $-- /business process/Process as new message/
		processAsNewMessage.setStatefullSession(getStatefullSession());
		// $-- /business process/Process as new message/
		processAsNewMessage.setLocale(getLocale());
		// $-- /business process/Process as new message/
		// $-- /business process/Process as new message/
		processAsNewMessage.setUser("COACNORMALIZERUPDATENEW");

		processAsNewMessage.initialize();

		// link nodes
		// $-- /business process/CallCompleteAnalyzerProcessor/out/business process/Update/in
		callCompleteAnalyzerProcessorNode.connectNodeToOut(updateNode);
		callCompleteAnalyzerProcessorNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CallNormalizeProcessor/out/business process/CastNode/in
		callNormalizeProcessorNode.connectNodeToOut(castNodeNode);
		callNormalizeProcessorNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CastNode/out/business process/ForkNode/in
		castNodeNode.connectNodeToOut(forkNodeNode);
		castNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/DecisionNode/false/business process/Process as new message/normalizableBean
		decisionNodeNode.connectNodeToFalse(processAsNewMessage
				.asNormalizableBeanInput());
		// $-- /business process/DecisionNode/true/business process/CallNormalizeProcessor/in
		decisionNodeNode.connectNodeToTrue(callNormalizeProcessorNode);
		decisionNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FindPreviousEventNode/out/business process/DecisionNode/in
		findPreviousEventNodeNode.connectNodeToOut(decisionNodeNode);
		findPreviousEventNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode/two/business process/ForkNode1/in
		forkNodeNode.connectNodeToTwo(forkNode1Node);
		// $-- /business process/ForkNode/one/business process/ManualMapNormalizableUpdateAdapterToCAEventDetail/in
		forkNodeNode
				.connectNodeToOne(manualMapNormalizableUpdateAdapterToCAEventDetailNode);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/ForkNode2/in
		forkNode1Node.connectNodeToTwo(forkNode2Node);
		// $-- /business process/ForkNode1/one/business process/NormalizationUpdateExtensionsIteratorNode/in
		forkNode1Node
				.connectNodeToOne(normalizationUpdateExtensionsIteratorNodeNode);
		forkNode1Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode2/two/business process/normalizableOutputBean
		forkNode2Node.connectNodeToTwo(normalizableOutputBean);
		// $-- /business process/ForkNode2/one/business process/ForkNode3/in
		forkNode2Node.connectNodeToOne(forkNode3Node);
		forkNode2Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode3/two/business process/UpdateEffects/in
		forkNode3Node.connectNodeToTwo(updateEffectsNode);
		// $-- /business process/ForkNode3/one/business process/Update EventCollected/in
		forkNode3Node.connectNodeToOne(updateEventCollectedNode);
		forkNode3Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Get Authorized Group/out/business process/To RollbackAuthorization/in
		getAuthorizedGroupNode.connectNodeToOut(toRollbackAuthorizationNode);
		getAuthorizedGroupNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Get MR Group/out/business process/To RollbackMasterRecord/in
		getMRGroupNode.connectNodeToOut(toRollbackMasterRecordNode);
		getMRGroupNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Get Sent Event/out/business process/To RepeatSendAsCancel/in
		getSentEventNode.connectNodeToOut(toRepeatSendAsCancelNode);
		getSentEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ManualMapDetailExtensionUpdateToCAEventDetailExtended/out/business process/Update/in
		manualMapDetailExtensionUpdateToCAEventDetailExtendedNode
				.connectNodeToOut(updateNode);
		manualMapDetailExtensionUpdateToCAEventDetailExtendedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ManualMapNormalizableUpdateAdapterToCAEventDetail/out/business process/Update/in
		manualMapNormalizableUpdateAdapterToCAEventDetailNode
				.connectNodeToOut(updateNode);
		manualMapNormalizableUpdateAdapterToCAEventDetailNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/NormalizationUpdateExtensionsIteratorNode/existentExtension/business process/ManualMapDetailExtensionUpdateToCAEventDetailExtended/in
		normalizationUpdateExtensionsIteratorNodeNode
				.connectNodeToExistentExtension(manualMapDetailExtensionUpdateToCAEventDetailExtendedNode);
		// $-- /business process/NormalizationUpdateExtensionsIteratorNode/newExtension/business process/Save/in
		normalizationUpdateExtensionsIteratorNodeNode
				.connectNodeToNewExtension(saveNode);
		normalizationUpdateExtensionsIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Promote to Updater/out/business process/FindPreviousEventNode/in
		promoteToUpdaterNode.connectNodeToOut(findPreviousEventNodeNode);
		promoteToUpdaterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		saveNode.connectNodeToOut(getDefaultStopperNode());
		saveNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SwitchNode/yes/business process/CallNormalizeProcessor/in
		switchNodeNode.connectNodeToYes(callNormalizeProcessorNode);
		// $-- /business process/SwitchNode/no/business process/Promote to Updater/in
		switchNodeNode.connectNodeToNo(promoteToUpdaterNode);
		switchNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/To RepeatSendAsCancel/out/business process/Update/in
		toRepeatSendAsCancelNode.connectNodeToOut(updateNode);
		toRepeatSendAsCancelNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/To RollbackAuthorization/out/business process/Update/in
		toRollbackAuthorizationNode.connectNodeToOut(updateNode);
		toRollbackAuthorizationNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/To RollbackMasterRecord/out/business process/Update/in
		toRollbackMasterRecordNode.connectNodeToOut(updateNode);
		toRollbackMasterRecordNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		updateNode.connectNodeToOut(getDefaultStopperNode());
		updateNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Update EventCollected/out/business process/CallCompleteAnalyzerProcessor/in
		updateEventCollectedNode
				.connectNodeToOut(callCompleteAnalyzerProcessorNode);
		updateEventCollectedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		updateEventDetailNode.connectNodeToOut(getDefaultStopperNode());
		updateEventDetailNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		updateEventDetailExtensionsNode
				.connectNodeToOut(getDefaultStopperNode());
		updateEventDetailExtensionsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/UpdateEffects/sENDTOBACKOFFICE/business process/Get Sent Event/in
		updateEffectsNode.connectNodeToSENDTOBACKOFFICE(getSentEventNode);
		updateEffectsNode.connectNodeToDefault(getDefaultStopperNode());
		// $-- /business process/UpdateEffects/mASTERRECORD/business process/Get MR Group/in
		updateEffectsNode.connectNodeToMASTERRECORD(getMRGroupNode);
		// $-- /business process/UpdateEffects/aUTHORIZED/business process/Get Authorized Group/in
		updateEffectsNode.connectNodeToAUTHORIZED(getAuthorizedGroupNode);
		updateEffectsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/Process as new message/normalizableOutputBean/business process/normalizableOutputBean
		processAsNewMessage.asNormalizableOutputBeanOutput().connect(
				normalizableOutputBean);
		processAsNewMessage.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/normalizableBean/business process/SwitchNode/in
		normalizableBean.connectNodeToOut(switchNodeNode);

		callCompleteAnalyzerProcessorNode.init();
		callNormalizeProcessorNode.init();
		castNodeNode.init();
		decisionNodeNode.init();
		findPreviousEventNodeNode.init();
		forkNodeNode.init();
		forkNode1Node.init();
		forkNode2Node.init();
		forkNode3Node.init();
		getAuthorizedGroupNode.init();
		getMRGroupNode.init();
		getSentEventNode.init();
		manualMapDetailExtensionUpdateToCAEventDetailExtendedNode.init();
		manualMapNormalizableUpdateAdapterToCAEventDetailNode.init();
		normalizationUpdateExtensionsIteratorNodeNode.init();
		promoteToUpdaterNode.init();
		saveNode.init();
		switchNodeNode.init();
		toRepeatSendAsCancelNode.init();
		toRollbackAuthorizationNode.init();
		toRollbackMasterRecordNode.init();
		updateNode.init();
		updateEventCollectedNode.init();
		updateEventDetailNode.init();
		updateEventDetailExtensionsNode.init();
		updateEffectsNode.init();
	}

}