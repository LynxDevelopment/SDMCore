// $-- src/com/lynxspa/coac/importers/securities/bloomberg/switches/ActionDecision.fpmswitch

package com.lynxspa.coac.importers.securities.bloomberg.switches;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/bloomberg/switches/ActionDecision.fpmswitch")
public class ActionDecisionSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> {
	private Logger logger_ = Logger.getLogger(ActionDecisionSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesInsertCondition(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean message)
			throws Exception {
		return ((message.getDifInteresting()) && ("0"
				.equals(message.getRCode())));
	}

	private OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToInsert_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> getNodeConnectedToInsert() {
		return nodeConnectedToInsert_;
	}

	public void connectNodeToInsert(
			OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToInsert) {
		nodeConnectedToInsert_ = nodeConnectedToInsert;
	}

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesDeleteCondition(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean message)
			throws Exception {
		return ((message.getDifInteresting()) && ("-1".equals(message
				.getRCode())));
	}

	private OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToDelete_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> getNodeConnectedToDelete() {
		return nodeConnectedToDelete_;
	}

	public void connectNodeToDelete(
			OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToDelete) {
		nodeConnectedToDelete_ = nodeConnectedToDelete;
	}

	private OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToDefault_;

	@NodeConnection(order = 2)
	public OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public ActionDecisionSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean message)
			throws Exception {
		if (evaluatesInsertCondition(message))
			getNodeConnectedToInsert().process(message);
		else if (evaluatesDeleteCondition(message))
			getNodeConnectedToDelete().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}