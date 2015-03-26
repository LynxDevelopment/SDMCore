// $-- src/com/lynxspa/coac/importers/securities/nodes/OperationDecision.fpmswitch

package com.lynxspa.coac.importers.securities.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/nodes/OperationDecision.fpmswitch")
public class OperationDecisionSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.sdm.importers.securities.beans.SecurityBean> {
	private Logger logger_ = Logger.getLogger(OperationDecisionSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesInsertOrUpdateCondition(
			com.lynxspa.sdm.importers.securities.beans.SecurityBean message)
			throws Exception {
		return ((com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.INSERT_OR_UPDATE
				.equals(message.getAction())) || (com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.UPDATE
				.equals(message.getAction())));
	}

	private OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> nodeConnectedToInsertOrUpdate_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> getNodeConnectedToInsertOrUpdate() {
		return nodeConnectedToInsertOrUpdate_;
	}

	public void connectNodeToInsertOrUpdate(
			OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> nodeConnectedToInsertOrUpdate) {
		nodeConnectedToInsertOrUpdate_ = nodeConnectedToInsertOrUpdate;
	}

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesDeleteCondition(
			com.lynxspa.sdm.importers.securities.beans.SecurityBean message)
			throws Exception {
		return com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.DELETE
				.equals(message.getAction());
	}

	private OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> nodeConnectedToDelete_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> getNodeConnectedToDelete() {
		return nodeConnectedToDelete_;
	}

	public void connectNodeToDelete(
			OutputConnectable<? super com.lynxspa.sdm.importers.securities.beans.SecurityBean> nodeConnectedToDelete) {
		nodeConnectedToDelete_ = nodeConnectedToDelete;
	}

	public OperationDecisionSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.sdm.importers.securities.beans.SecurityBean message)
			throws Exception {
		if (evaluatesInsertOrUpdateCondition(message))
			getNodeConnectedToInsertOrUpdate().process(message);
		else if (evaluatesDeleteCondition(message))
			getNodeConnectedToDelete().process(message);
		else
			getNodeConnectedToException().processException(
					new SwitchException("No condition has been satisfied"),
					message);
	}
}