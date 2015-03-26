// $-- src/com/lynxspa/coac/plannings/ftpprocess/nodes/ftpproccess.fpmswitch

package com.lynxspa.coac.plannings.ftpprocess.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/plannings/ftpprocess/nodes/ftpproccess.fpmswitch")
public class FtpproccessSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.plannings.beans.PlanningBean> {
	private Logger logger_ = Logger.getLogger(FtpproccessSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesIsImportFTPCondition(
			com.lynxspa.coac.plannings.beans.PlanningBean message)
			throws Exception {
		return (message.isFTPImport());
	}

	private OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToIsImportFTP_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> getNodeConnectedToIsImportFTP() {
		return nodeConnectedToIsImportFTP_;
	}

	public void connectNodeToIsImportFTP(
			OutputConnectable<? super com.lynxspa.coac.plannings.beans.PlanningBean> nodeConnectedToIsImportFTP) {
		nodeConnectedToIsImportFTP_ = nodeConnectedToIsImportFTP;
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

	public FtpproccessSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.plannings.beans.PlanningBean message)
			throws Exception {
		if (evaluatesIsImportFTPCondition(message))
			getNodeConnectedToIsImportFTP().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}