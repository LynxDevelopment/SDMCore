package com.lynxspa.coac.normalizer.operationrepeat.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationrepeat/businessprocess/RepeatEvent.fpmprocess")
public class RepeatEventProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */
	private String locale_;

	@ConfigParam(description = "param2", required = true, dynamic = false, name = "locale")
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

	public RepeatEventProcess(String id) {
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
								"(message.getPreviousEventCollected()==null)",
								java.lang.Boolean.class));
		// $-- /business process/DecisionNode/

		// $-- /business process/FindPreviousEvent
		// instantiate and configure 'FindPreviousEvent' node
		com.lynxspa.coac.normalizer.nodes.FindPreviousEventNode findPreviousEventNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.nodes.FindPreviousEventNode.class);
		findPreviousEventNode.setId("FindPreviousEvent");
		addNode(findPreviousEventNode);
		// $-- /business process/FindPreviousEvent/
		findPreviousEventNode.setOnNotFoundFail(false);
		// $-- /business process/FindPreviousEvent/
		findPreviousEventNode.setResource(getStatefullSession());
		// $-- /business process/FindPreviousEvent/

		// $-- /business process/Promote to Updater
		// instantiate and configure 'Promote to Updater' node
		com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap promoteToUpdaterNode = Interceptors
				.createNode(com.lynxspa.coac.normalizer.mappings.PromoteToNormalizationUpdaterAdapterMap.class);
		promoteToUpdaterNode.setId("Promote to Updater");
		addNode(promoteToUpdaterNode);
		// $-- /business process/Promote to Updater/

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
		processAsNewMessage.setUser("COACNORMALIZERREPEATNEW");

		processAsNewMessage.initialize();
		// $-- /business process/Process as update message
		// instantiate and configure 'Process as update message' subprocess
		com.lynxspa.coac.normalizer.operationupdate.businessprocess.UpdateEventProcess processAsUpdateMessage = Interceptors
				.createSubProcess(
						com.lynxspa.coac.normalizer.operationupdate.businessprocess.UpdateEventProcess.class,
						"Process as update message");
		addSubProcess(processAsUpdateMessage);
		// $-- /business process/Process as update message/
		processAsUpdateMessage.setStatefullSession(getStatefullSession());
		// $-- /business process/Process as update message/
		processAsUpdateMessage.setLocale(getLocale());
		// $-- /business process/Process as update message/
		// $-- /business process/Process as update message/
		processAsUpdateMessage.setUser("COACNORMALIZERREPEATUPDATE");

		processAsUpdateMessage.initialize();

		// link nodes
		// $-- /business process/DecisionNode/false/business process/Process as update message/normalizableBean
		decisionNodeNode.connectNodeToFalse(processAsUpdateMessage
				.asNormalizableBeanInput());
		// $-- /business process/DecisionNode/true/business process/Process as new message/normalizableBean
		decisionNodeNode.connectNodeToTrue(processAsNewMessage
				.asNormalizableBeanInput());
		decisionNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/FindPreviousEvent/out/business process/DecisionNode/in
		findPreviousEventNode.connectNodeToOut(decisionNodeNode);
		findPreviousEventNode
				.connectNodeToException(getDefaultExceptionHandlerNode());
		// $-- /business process/Promote to Updater/out/business process/FindPreviousEvent/in
		promoteToUpdaterNode.connectNodeToOut(findPreviousEventNode);
		promoteToUpdaterNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses
		// $-- /business process/Process as new message/normalizableOutputBean/business process/normalizableOutputBean
		processAsNewMessage.asNormalizableOutputBeanOutput().connect(
				normalizableOutputBean);
		processAsNewMessage.asExcOutput().connect(getDefaultStopperNode());
		// $-- /business process/Process as update message/normalizableOutputBean/business process/normalizableOutputBean
		processAsUpdateMessage.asNormalizableOutputBeanOutput().connect(
				normalizableOutputBean);
		processAsUpdateMessage.asExcOutput().connect(getDefaultStopperNode());

		// link inputs
		// $-- /business process/normalizableBean/business process/Promote to Updater/in
		normalizableBean.connectNodeToOut(promoteToUpdaterNode);

		decisionNodeNode.init();
		findPreviousEventNode.init();
		promoteToUpdaterNode.init();
	}

}