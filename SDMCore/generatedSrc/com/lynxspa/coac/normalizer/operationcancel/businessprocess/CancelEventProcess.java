package com.lynxspa.coac.normalizer.operationcancel.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationcancel/businessprocess/CancelEvent.fpmprocess")
public class CancelEventProcess extends BusinessProcess {
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

	public CancelEventProcess(String id) {
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
		// $-- /business process/Cancel Event
		// instantiate and configure 'Cancel Event' node
		com.lynxspa.coac.normalizer.operationcancel.mappings.CancelCAEventCollectedMap cancelEventNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationcancel.mappings.CancelCAEventCollectedMap.class);
		cancelEventNode.setId("Cancel Event");
		addNode(cancelEventNode);
		// $-- /business process/Cancel Event/

		// $-- /business process/FindPreviousEvent
		// instantiate and configure 'FindPreviousEvent' node
		com.lynxspa.coac.normalizer.nodes.FindPreviousEventNode findPreviousEventNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.FindPreviousEventNode.class);
		findPreviousEventNode.setId("FindPreviousEvent");
		addNode(findPreviousEventNode);
		// $-- /business process/FindPreviousEvent/
		findPreviousEventNode.setOnNotFoundFail(true);
		// $-- /business process/FindPreviousEvent/
		findPreviousEventNode.setResource(getStatefullSession());
		// $-- /business process/FindPreviousEvent/

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

		// $-- /business process/Promote to Updater
		// instantiate and configure 'Promote to Updater' node
		com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap promoteToUpdaterNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap.class);
		promoteToUpdaterNode.setId("Promote to Updater");
		addNode(promoteToUpdaterNode);
		// $-- /business process/Promote to Updater/

		// $-- /business process/SwitchNode
		// instantiate and configure 'SwitchNode' node
		com.lynxspa.coac.normalizer.operationcancel.nodes.EventGroupCancelationEffectsSwitch switchNodeNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationcancel.nodes.EventGroupCancelationEffectsSwitch.class);
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
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.CANCELATION_OVER_SENTEVENT);
		// $-- /business process/To RepeatSendAsCancel/
		toRepeatSendAsCancelNode.setCreateLog(true);
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
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.CANCELATION_OVER_AUTHORIZED);
		// $-- /business process/To RollbackAuthorization/
		toRollbackAuthorizationNode.setCreateLog(true);
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
				.setMessage(com.lynxspa.sdm.dictionaries.logs.LogInfoDict.CANCELATION_OVER_MASTERRECORD);
		// $-- /business process/To RollbackMasterRecord/
		toRollbackMasterRecordNode.setCreateLog(true);
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

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/Cancel Event/out/business process/Update/in
		cancelEventNode.connectNodeToOut(updateNode);
		cancelEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FindPreviousEvent/out/business process/ForkNode/in
		findPreviousEventNode.connectNodeToOut(forkNodeNode);
		findPreviousEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode/two/business process/normalizableOutputBean
		forkNodeNode.connectNodeToTwo(normalizableOutputBean);
		// $-- /business process/ForkNode/one/business process/ForkNode1/in
		forkNodeNode.connectNodeToOne(forkNode1Node);
		forkNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/SwitchNode/in
		forkNode1Node.connectNodeToTwo(switchNodeNode);
		// $-- /business process/ForkNode1/one/business process/Cancel Event/in
		forkNode1Node.connectNodeToOne(cancelEventNode);
		forkNode1Node.connectNodeToException(getDefaultExceptionHandlerNode());
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
		// $-- /business process/Promote to Updater/out/business process/FindPreviousEvent/in
		promoteToUpdaterNode.connectNodeToOut(findPreviousEventNode);
		promoteToUpdaterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SwitchNode/sENDTOBACKOFFICE/business process/Get Sent Event/in
		switchNodeNode.connectNodeToSENDTOBACKOFFICE(getSentEventNode);
		switchNodeNode.connectNodeToDefault(getDefaultStopperNode());
		// $-- /business process/SwitchNode/mASTERRECORD/business process/Get MR Group/in
		switchNodeNode.connectNodeToMASTERRECORD(getMRGroupNode);
		// $-- /business process/SwitchNode/aUTHORIZED/business process/Get Authorized Group/in
		switchNodeNode.connectNodeToAUTHORIZED(getAuthorizedGroupNode);
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

		// link subprocesses

		// link inputs
		// $-- /business process/normalizableBean/business process/Promote to Updater/in
		normalizableBean.connectNodeToOut(promoteToUpdaterNode);

		cancelEventNode.init();
		findPreviousEventNode.init();
		forkNodeNode.init();
		forkNode1Node.init();
		getAuthorizedGroupNode.init();
		getMRGroupNode.init();
		getSentEventNode.init();
		promoteToUpdaterNode.init();
		switchNodeNode.init();
		toRepeatSendAsCancelNode.init();
		toRollbackAuthorizationNode.init();
		toRollbackMasterRecordNode.init();
		updateNode.init();
	}

}