// $-- src/com/lynxspa/coac/normalizer/nodes/OperationDecision.fpmswitch

package com.lynxspa.coac.normalizer.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/nodes/OperationDecision.fpmswitch")
public class OperationDecisionSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> {
	private Logger logger_ = Logger.getLogger(OperationDecisionSwitch.class);

	/**
	 * New event
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesNewCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return ((com.lynxspa.sdm.dictionaries.CAOperation.NEW.getCode()
				.equals(message.getMessage().getNormalizedOperation())) && (!message
				.getMessage().isExtension()));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToNew_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToNew() {
		return nodeConnectedToNew_;
	}

	public void connectNodeToNew(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToNew) {
		nodeConnectedToNew_ = nodeConnectedToNew;
	}

	/**
	 * Update event
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesUpdateCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return ((com.lynxspa.sdm.dictionaries.CAOperation.UPDATE.getCode()
				.equals(message.getMessage().getNormalizedOperation())) || (message
				.getMessage().isExtension()));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToUpdate_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToUpdate() {
		return nodeConnectedToUpdate_;
	}

	public void connectNodeToUpdate(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToUpdate) {
		nodeConnectedToUpdate_ = nodeConnectedToUpdate;
	}

	/**
	 * Cancel event
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesCancelCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return ((com.lynxspa.sdm.dictionaries.CAOperation.CANCELATION.getCode()
				.equals(message.getMessage().getNormalizedOperation())) && (!message
				.getMessage().isExtension()));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToCancel_;

	@NodeConnection(order = 2)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToCancel() {
		return nodeConnectedToCancel_;
	}

	public void connectNodeToCancel(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToCancel) {
		nodeConnectedToCancel_ = nodeConnectedToCancel;
	}

	/**
	 * Repeat event
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesRepeatCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		return ((com.lynxspa.sdm.dictionaries.CAOperation.REPEAT.getCode()
				.equals(message.getMessage().getNormalizedOperation())) && (!message
				.getMessage().isExtension()));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToRepeat_;

	@NodeConnection(order = 3)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> getNodeConnectedToRepeat() {
		return nodeConnectedToRepeat_;
	}

	public void connectNodeToRepeat(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> nodeConnectedToRepeat) {
		nodeConnectedToRepeat_ = nodeConnectedToRepeat;
	}

	public OperationDecisionSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter message)
			throws Exception {
		if (evaluatesNewCondition(message))
			getNodeConnectedToNew().process(message);
		else if (evaluatesUpdateCondition(message))
			getNodeConnectedToUpdate().process(message);
		else if (evaluatesCancelCondition(message))
			getNodeConnectedToCancel().process(message);
		else if (evaluatesRepeatCondition(message))
			getNodeConnectedToRepeat().process(message);
		else
			getNodeConnectedToException().processException(
					new SwitchException("No condition has been satisfied"),
					message);
	}
}