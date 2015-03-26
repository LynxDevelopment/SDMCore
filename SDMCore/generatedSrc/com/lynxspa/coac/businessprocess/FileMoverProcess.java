package com.lynxspa.coac.businessprocess;

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
@GeneratedType(definitionFile = "src/com/lynxspa/coac/businessprocess/fileMover.fpmprocess")
public class FileMoverProcess extends BusinessProcess {
	/*
	 * Parameters declarations
	 */

	/*
	 * inputs declarations
	 */

	// input 'InputEvent'
	// $-- /business process/InputEvent
	private com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> inputEvent;

	public com.lynxit.fpm.BusinessProcessInput<com.lynxit.fpm.events.scheduler.ScheduledEvent> asInputEventInput() {
		return inputEvent;
	}

	/*
	 * outputs declarations
	 */

	public FileMoverProcess(String id) {
		super(id);
	}

	public void init() throws Exception {
		Properties initParams = ConfigParams.getInstance().getProperties();

		// instantiate and configure inputs
		// instantiate and configure 'InputEvent' input
		inputEvent = addInput("InputEvent");

		// instantiate and configure outputs

		// instantiate and configure nodes
		// $-- /business process/MoverNode
		// instantiate and configure 'MoverNode' node
		com.lynxspa.fpm.nodes.MoverNode moverNodeNode = Interceptors
				.createNode(com.lynxspa.fpm.nodes.MoverNode.class);
		moverNodeNode.setId("MoverNode");
		addNode(moverNodeNode);
		// $-- /business process/MoverNode/
		moverNodeNode.setToDirectory("input/swift");
		// $-- /business process/MoverNode/
		// $-- /business process/MoverNode/
		moverNodeNode.setFromDirectory("input/swift/examples");

		// instantiate and configure subprocesses

		// link nodes
		moverNodeNode.connectNodeToException(getDefaultExceptionHandlerNode());

		// link subprocesses

		// link inputs
		// $-- /business process/InputEvent/business process/MoverNode/in
		inputEvent.connectNodeToOut(moverNodeNode);

		moverNodeNode.init();
	}

}