package com.lynxspa.coac.normalizer.operationnew.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/businessprocess/NewEvent.fpmprocess")
public class NewEventProcess extends BusinessProcess {
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

	public NewEventProcess(String id) {
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

		// $-- /business process/CollectionIteratorNode
		// instantiate and configure 'CollectionIteratorNode' node
		com.lynxit.fpm.nodes.internal.CollectionIteratorNode<java.lang.Object> collectionIteratorNodeNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.CollectionIteratorNode.class);
		collectionIteratorNodeNode.setId("CollectionIteratorNode");
		addNode(collectionIteratorNodeNode);
		// $-- /business process/CollectionIteratorNode/

		// $-- /business process/Context CAEventCollected
		// instantiate and configure 'Context CAEventCollected' node
		com.lynxspa.fpm.nodes.SetBussinessProcessContextParams<com.lynxspa.sdm.entities.events.CAEventCollected> contextCAEventCollectedNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.SetBussinessProcessContextParams.class);
		contextCAEventCollectedNode.setId("Context CAEventCollected");
		addNode(contextCAEventCollectedNode);
		// $-- /business process/Context CAEventCollected/
		((DynaParamsNode) contextCAEventCollectedNode).setDynamicParamValues(
				"values", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message", java.lang.Object.class));
		// $-- /business process/Context CAEventCollected/
		contextCAEventCollectedNode
				.setNames(new java.lang.String[] { "EventCollectedFound" });
		// $-- /business process/Context CAEventCollected/

		// $-- /business process/Context CAEventDetails
		// instantiate and configure 'Context CAEventDetails' node
		com.lynxspa.fpm.nodes.SetBussinessProcessContextParams<com.lynxspa.sdm.entities.events.details.CAEventDetail> contextCAEventDetailsNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.SetBussinessProcessContextParams.class);
		contextCAEventDetailsNode.setId("Context CAEventDetails");
		addNode(contextCAEventDetailsNode);
		// $-- /business process/Context CAEventDetails/
		((DynaParamsNode) contextCAEventDetailsNode).setDynamicParamValues(
				"values", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message", java.lang.Object.class));
		// $-- /business process/Context CAEventDetails/
		contextCAEventDetailsNode
				.setNames(new java.lang.String[] { "EventDetailFound" });
		// $-- /business process/Context CAEventDetails/

		// $-- /business process/Foreach extension
		// instantiate and configure 'Foreach extension' node
		com.lynxit.fpm.nodes.internal.CollectionIteratorNode<java.lang.Object> foreachExtensionNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.CollectionIteratorNode.class);
		foreachExtensionNode.setId("Foreach extension");
		addNode(foreachExtensionNode);
		// $-- /business process/Foreach extension/

		// $-- /business process/ForkNode1
		// instantiate and configure 'ForkNode1' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> forkNode1Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode1Node.setId("ForkNode1");
		addNode(forkNode1Node);
		// $-- /business process/ForkNode1/
		// $-- /business process/ForkNode1/
		forkNode1Node.setCloneMessage(false);

		// $-- /business process/ForkNode2
		// instantiate and configure 'ForkNode2' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> forkNode2Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode2Node.setId("ForkNode2");
		addNode(forkNode2Node);
		// $-- /business process/ForkNode2/
		// $-- /business process/ForkNode2/
		forkNode2Node.setCloneMessage(false);

		// $-- /business process/ForkNode21
		// instantiate and configure 'ForkNode21' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.sdm.entities.events.CAEventCollected> forkNode21Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode21Node.setId("ForkNode21");
		addNode(forkNode21Node);
		// $-- /business process/ForkNode21/
		// $-- /business process/ForkNode21/
		forkNode21Node.setCloneMessage(false);

		// $-- /business process/ForkNode3
		// instantiate and configure 'ForkNode3' node
		com.lynxit.fpm.nodes.internal.ForkNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> forkNode3Node = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.ForkNode.class);
		forkNode3Node.setId("ForkNode3");
		addNode(forkNode3Node);
		// $-- /business process/ForkNode3/
		// $-- /business process/ForkNode3/
		forkNode3Node.setCloneMessage(false);

		// $-- /business process/GetEventCollected
		// instantiate and configure 'GetEventCollected' node
		com.lynxspa.coac.normalizer.operationnew.mappings.NormalizableBeanToCAEventCollectedMap getEventCollectedNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationnew.mappings.NormalizableBeanToCAEventCollectedMap.class);
		getEventCollectedNode.setId("GetEventCollected");
		addNode(getEventCollectedNode);
		// $-- /business process/GetEventCollected/

		// $-- /business process/GetEventDetails
		// instantiate and configure 'GetEventDetails' node
		com.lynxspa.coac.normalizer.operationnew.mappings.GetEventDetailsFromNormalizableBeanMap getEventDetailsNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationnew.mappings.GetEventDetailsFromNormalizableBeanMap.class);
		getEventDetailsNode.setId("GetEventDetails");
		addNode(getEventDetailsNode);
		// $-- /business process/GetEventDetails/

		// $-- /business process/GetEventDetailsExtended
		// instantiate and configure 'GetEventDetailsExtended' node
		com.lynxspa.coac.normalizer.operationnew.mappings.GetEventDetailsExtendedFromNormalizableBeanMap getEventDetailsExtendedNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationnew.mappings.GetEventDetailsExtendedFromNormalizableBeanMap.class);
		getEventDetailsExtendedNode.setId("GetEventDetailsExtended");
		addNode(getEventDetailsExtendedNode);
		// $-- /business process/GetEventDetailsExtended/

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

		// $-- /business process/SetEventDetail
		// instantiate and configure 'SetEventDetail' node
		com.lynxspa.coac.normalizer.operationnew.mappings.SetEventDetailsToEventDetailsExtendedMap setEventDetailNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationnew.mappings.SetEventDetailsToEventDetailsExtendedMap.class);
		setEventDetailNode.setId("SetEventDetail");
		addNode(setEventDetailNode);
		// $-- /business process/SetEventDetail/

		// $-- /business process/ToCAEventDetailsExtended
		// instantiate and configure 'ToCAEventDetailsExtended' node
		com.lynxit.fpm.nodes.internal.CastNode<com.lynxspa.sdm.entities.events.details.CAEventDetailExtended> toCAEventDetailsExtendedNode = Interceptors
				.createNode(com.lynxit.fpm.nodes.internal.CastNode.class);
		toCAEventDetailsExtendedNode.setId("ToCAEventDetailsExtended");
		addNode(toCAEventDetailsExtendedNode);
		// $-- /business process/ToCAEventDetailsExtended/

		// $-- /business process/return
		// instantiate and configure 'return' node
		com.lynxspa.coac.normalizer.operationnew.mappings.SetEventCollectedToNormalizableAdapterMap returnNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.operationnew.mappings.SetEventCollectedToNormalizableAdapterMap.class);
		returnNode.setId("return");
		addNode(returnNode);
		// $-- /business process/return/

		// instantiate and configure subprocesses

		// link nodes
		// $-- /business process/CallCompleteAnalyzerProcessor/out/business process/ForkNode21/in
		callCompleteAnalyzerProcessorNode.connectNodeToOut(forkNode21Node);
		callCompleteAnalyzerProcessorNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CallNormalizeProcessor/out/business process/ForkNode1/in
		callNormalizeProcessorNode.connectNodeToOut(forkNode1Node);
		callNormalizeProcessorNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/CollectionIteratorNode/out/business process/ToCAEventDetailsExtended/in
		collectionIteratorNodeNode
				.connectNodeToOut(toCAEventDetailsExtendedNode);
		collectionIteratorNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		contextCAEventCollectedNode.connectNodeToOut(getDefaultStopperNode());
		contextCAEventCollectedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Context CAEventDetails/out/business process/Save/in
		contextCAEventDetailsNode.connectNodeToOut(saveNode);
		contextCAEventDetailsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		foreachExtensionNode.connectNodeToOut(getDefaultStopperNode());
		foreachExtensionNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode1/two/business process/ForkNode2/in
		forkNode1Node.connectNodeToTwo(forkNode2Node);
		// $-- /business process/ForkNode1/one/business process/GetEventDetails/in
		forkNode1Node.connectNodeToOne(getEventDetailsNode);
		forkNode1Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode2/two/business process/ForkNode3/in
		forkNode2Node.connectNodeToTwo(forkNode3Node);
		// $-- /business process/ForkNode2/one/business process/GetEventDetailsExtended/in
		forkNode2Node.connectNodeToOne(getEventDetailsExtendedNode);
		forkNode2Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode21/two/business process/Context CAEventCollected/in
		forkNode21Node.connectNodeToTwo(contextCAEventCollectedNode);
		// $-- /business process/ForkNode21/one/business process/Save/in
		forkNode21Node.connectNodeToOne(saveNode);
		forkNode21Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ForkNode3/two/business process/return/in
		forkNode3Node.connectNodeToTwo(returnNode);
		// $-- /business process/ForkNode3/one/business process/GetEventCollected/in
		forkNode3Node.connectNodeToOne(getEventCollectedNode);
		forkNode3Node.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetEventCollected/out/business process/CallCompleteAnalyzerProcessor/in
		getEventCollectedNode
				.connectNodeToOut(callCompleteAnalyzerProcessorNode);
		getEventCollectedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetEventDetails/out/business process/Context CAEventDetails/in
		getEventDetailsNode.connectNodeToOut(contextCAEventDetailsNode);
		getEventDetailsNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/GetEventDetailsExtended/out/business process/CollectionIteratorNode/in
		getEventDetailsExtendedNode
				.connectNodeToOut(collectionIteratorNodeNode);
		getEventDetailsExtendedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		saveNode.connectNodeToOut(getDefaultStopperNode());
		saveNode.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/SetEventDetail/out/business process/Save/in
		setEventDetailNode.connectNodeToOut(saveNode);
		setEventDetailNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/ToCAEventDetailsExtended/out/business process/SetEventDetail/in
		toCAEventDetailsExtendedNode.connectNodeToOut(setEventDetailNode);
		toCAEventDetailsExtendedNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/return/out/business process/normalizableOutputBean
		returnNode.connectNodeToOut(normalizableOutputBean);
		returnNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/normalizableBean/business process/CallNormalizeProcessor/in
		normalizableBean.connectNodeToOut(callNormalizeProcessorNode);

		callCompleteAnalyzerProcessorNode.init();
		callNormalizeProcessorNode.init();
		collectionIteratorNodeNode.init();
		contextCAEventCollectedNode.init();
		contextCAEventDetailsNode.init();
		foreachExtensionNode.init();
		forkNode1Node.init();
		forkNode2Node.init();
		forkNode21Node.init();
		forkNode3Node.init();
		getEventCollectedNode.init();
		getEventDetailsNode.init();
		getEventDetailsExtendedNode.init();
		saveNode.init();
		setEventDetailNode.init();
		toCAEventDetailsExtendedNode.init();
		returnNode.init();
	}

}