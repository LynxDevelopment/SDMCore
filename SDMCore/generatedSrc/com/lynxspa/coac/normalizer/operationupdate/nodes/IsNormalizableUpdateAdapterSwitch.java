// $-- src/com/lynxspa/coac/normalizer/operationupdate/nodes/IsNormalizableUpdateAdapter.fpmswitch

package com.lynxspa.coac.normalizer.operationupdate.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationupdate/nodes/IsNormalizableUpdateAdapter.fpmswitch")
public class IsNormalizableUpdateAdapterSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> {
	private Logger logger_ = Logger
			.getLogger(IsNormalizableUpdateAdapterSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesYesCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return (message instanceof com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter);
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToYes_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToYes() {
		return nodeConnectedToYes_;
	}

	public void connectNodeToYes(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToYes) {
		nodeConnectedToYes_ = nodeConnectedToYes;
	}

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesNoCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return !(message instanceof com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter);
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToNo_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToNo() {
		return nodeConnectedToNo_;
	}

	public void connectNodeToNo(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToNo) {
		nodeConnectedToNo_ = nodeConnectedToNo;
	}

	public IsNormalizableUpdateAdapterSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		if (evaluatesYesCondition(message))
			getNodeConnectedToYes().process(message);
		else if (evaluatesNoCondition(message))
			getNodeConnectedToNo().process(message);
		else
			getNodeConnectedToException().processException(
					new SwitchException("No condition has been satisfied"),
					message);
	}
}