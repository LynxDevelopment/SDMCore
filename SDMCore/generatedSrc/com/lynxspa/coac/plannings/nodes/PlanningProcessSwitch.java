// $-- src/com/lynxspa/coac/plannings/nodes/planningProcess.fpmswitch

package com.lynxspa.coac.plannings.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/nodes/planningProcess.fpmswitch")
public class PlanningProcessSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.plannings.beans.ControlPlanningsBean> {
	private Logger logger_ = Logger.getLogger(PlanningProcessSwitch.class);

	/**
	 * Process to execute a SHELL
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesExecuteShellCondition(
			com.lynxspa.coac.plannings.beans.ControlPlanningsBean message)
			throws Exception {
		return (message.isExecuteShell());
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToExecuteShell_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> getNodeConnectedToExecuteShell() {
		return nodeConnectedToExecuteShell_;
	}

	public void connectNodeToExecuteShell(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToExecuteShell) {
		nodeConnectedToExecuteShell_ = nodeConnectedToExecuteShell;
	}

	/**
	 * Process to execute an FTP PUT
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesFtpPutCondition(
			com.lynxspa.coac.plannings.beans.ControlPlanningsBean message)
			throws Exception {
		return (message.isFTPExport());
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToFtpPut_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> getNodeConnectedToFtpPut() {
		return nodeConnectedToFtpPut_;
	}

	public void connectNodeToFtpPut(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToFtpPut) {
		nodeConnectedToFtpPut_ = nodeConnectedToFtpPut;
	}

	/**
	 * Process to execute an FTP GET
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesFtpGetCondition(
			com.lynxspa.coac.plannings.beans.ControlPlanningsBean message)
			throws Exception {
		return (message.isFTPImport());
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToFtpGet_;

	@NodeConnection(order = 2)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> getNodeConnectedToFtpGet() {
		return nodeConnectedToFtpGet_;
	}

	public void connectNodeToFtpGet(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToFtpGet) {
		nodeConnectedToFtpGet_ = nodeConnectedToFtpGet;
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToDefault_;

	@NodeConnection(order = 3)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.ControlPlanningsBean> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public PlanningProcessSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.plannings.beans.ControlPlanningsBean message)
			throws Exception {
		if (evaluatesExecuteShellCondition(message))
			getNodeConnectedToExecuteShell().process(message);
		else if (evaluatesFtpPutCondition(message))
			getNodeConnectedToFtpPut().process(message);
		else if (evaluatesFtpGetCondition(message))
			getNodeConnectedToFtpGet().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}