// $-- src/com/lynxspa/coac/plannings/ftpprocess/nodes/fileftp.fpmswitch

package com.lynxspa.coac.plannings.ftpprocess.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/ftpprocess/nodes/fileftp.fpmswitch")
public class FileftpSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.plannings.beans.PlanningBean> {
	private Logger logger_ = Logger.getLogger(FileftpSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesIsFTPCondition(
			com.lynxspa.coac.plannings.beans.PlanningBean message)
			throws Exception {
		return (message.isFTPExport());
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToIsFTP_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> getNodeConnectedToIsFTP() {
		return nodeConnectedToIsFTP_;
	}

	public void connectNodeToIsFTP(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToIsFTP) {
		nodeConnectedToIsFTP_ = nodeConnectedToIsFTP;
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToDefault_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public FileftpSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.plannings.beans.PlanningBean message)
			throws Exception {
		if (evaluatesIsFTPCondition(message))
			getNodeConnectedToIsFTP().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}