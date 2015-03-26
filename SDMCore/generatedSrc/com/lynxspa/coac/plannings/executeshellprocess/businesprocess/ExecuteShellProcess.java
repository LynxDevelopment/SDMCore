package com.lynxspa.coac.plannings.executeshellprocess.businesprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/executeshellprocess/businesprocess/executeShell.fpmprocess")
public class ExecuteShellProcess extends BusinessProcess {
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

	// input 'input'
	// $-- /business process/input
	private com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> input;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> asInputInput() {
		return input;
	}

	/*
	 * outputs declarations
	 */

	public ExecuteShellProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'input' input
		input = addInput("input");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/ExecuteShellProcessOutputNode
		// instantiate and configure 'ExecuteShellProcessOutputNode' node
		com.lynxspa.coac.plannings.executeshellprocess.nodes.ExecuteShellProcessOutputNode executeShellProcessOutputNodeNode = Interceptors
				.createNode(com.lynxspa.coac.plannings.executeshellprocess.nodes.ExecuteShellProcessOutputNode.class);
		executeShellProcessOutputNodeNode
				.setId("ExecuteShellProcessOutputNode");
		addNode(executeShellProcessOutputNodeNode);
		// $-- /business process/ExecuteShellProcessOutputNode/
		executeShellProcessOutputNodeNode.setLocale(getLocale());
		// $-- /business process/ExecuteShellProcessOutputNode/
		((DynaParamsNode) executeShellProcessOutputNodeNode).setDynamicParam(
				"fileName", com.lynxit.utils.scripting.Language.BEAN_SHELL
						.createExpression("message.getFileName()",
								java.lang.String.class));
		// $-- /business process/ExecuteShellProcessOutputNode/
		executeShellProcessOutputNodeNode.setResource(getStatefullSession());
		// $-- /business process/ExecuteShellProcessOutputNode/
		// $-- /business process/ExecuteShellProcessOutputNode/
		executeShellProcessOutputNodeNode.setUser(getUser());

		// instantiate and configure subprocesses

		// link nodes
		executeShellProcessOutputNodeNode
				.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/input/business process/ExecuteShellProcessOutputNode/in
		input.connectNodeToOut(executeShellProcessOutputNodeNode);

		executeShellProcessOutputNodeNode.init();
	}

}