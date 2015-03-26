// $-- src/com/lynxspa/coac/normalizer/operationcancel/nodes/EventGroupCancelationEffects.fpmswitch

package com.lynxspa.coac.normalizer.operationcancel.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationcancel/nodes/EventGroupCancelationEffects.fpmswitch")
public class EventGroupCancelationEffectsSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> {
	private Logger logger_ = Logger
			.getLogger(EventGroupCancelationEffectsSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesMASTERRECORDCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter message)
			throws Exception {
		return ((message.getPreviousEventCollected().getEventGroup() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent().getId() == message
						.getPreviousEventCollected().getId()) && ("NAUT"
					.equals(message.getPreviousEventCollected().getEventGroup()
							.getOperationStatus().getState().getId().getCode())));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToMASTERRECORD_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> getNodeConnectedToMASTERRECORD() {
		return nodeConnectedToMASTERRECORD_;
	}

	public void connectNodeToMASTERRECORD(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToMASTERRECORD) {
		nodeConnectedToMASTERRECORD_ = nodeConnectedToMASTERRECORD;
	}

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesAUTHORIZEDCondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter message)
			throws Exception {
		return ((message.getPreviousEventCollected().getEventGroup() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent().getId() == message
						.getPreviousEventCollected().getId()) && ("AUTH"
					.equals(message.getPreviousEventCollected().getEventGroup()
							.getOperationStatus().getState().getId().getCode())));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToAUTHORIZED_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> getNodeConnectedToAUTHORIZED() {
		return nodeConnectedToAUTHORIZED_;
	}

	public void connectNodeToAUTHORIZED(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToAUTHORIZED) {
		nodeConnectedToAUTHORIZED_ = nodeConnectedToAUTHORIZED;
	}

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesSENDTOBACKOFFICECondition(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter message)
			throws Exception {
		return ((message.getPreviousEventCollected().getEventGroup() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent() != null)
				&& (message.getPreviousEventCollected().getEventGroup()
						.getMasterEvent().getId() == message
						.getPreviousEventCollected().getId()) && (("SBKO"
				.equals(message.getPreviousEventCollected().getEventGroup()
						.getOperationStatus().getState().getId().getCode()))
				|| ("TBKU".equals(message.getPreviousEventCollected()
						.getEventGroup().getOperationStatus().getState()
						.getId().getCode())) || ("TBKC".equals(message
				.getPreviousEventCollected().getEventGroup()
				.getOperationStatus().getState().getId().getCode()))));
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToSENDTOBACKOFFICE_;

	@NodeConnection(order = 2)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> getNodeConnectedToSENDTOBACKOFFICE() {
		return nodeConnectedToSENDTOBACKOFFICE_;
	}

	public void connectNodeToSENDTOBACKOFFICE(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToSENDTOBACKOFFICE) {
		nodeConnectedToSENDTOBACKOFFICE_ = nodeConnectedToSENDTOBACKOFFICE;
	}

	private OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToDefault_;

	@NodeConnection(order = 3)
	public OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public EventGroupCancelationEffectsSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter message)
			throws Exception {
		if (evaluatesMASTERRECORDCondition(message))
			getNodeConnectedToMASTERRECORD().process(message);
		else if (evaluatesAUTHORIZEDCondition(message))
			getNodeConnectedToAUTHORIZED().process(message);
		else if (evaluatesSENDTOBACKOFFICECondition(message))
			getNodeConnectedToSENDTOBACKOFFICE().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}