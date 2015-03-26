// $-- src/com/lynxspa/coac/normalizer/nodes/NotNormalizableReason.fpmswitch

package com.lynxspa.coac.normalizer.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/nodes/NotNormalizableReason.fpmswitch")
public class NotNormalizableReasonSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.sdm.entities.events.messages.CAEventMessage> {
	private Logger logger_ = Logger
			.getLogger(NotNormalizableReasonSwitch.class);

	/**
	 * Is virtual security
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesVirtualSecurityCondition(
			com.lynxspa.sdm.entities.events.messages.CAEventMessage message)
			throws Exception {
		boolean reply = false;

		reply = (!message.isInCustomerPortfolio())
				&& (!(message.getNormalizedSecurity() instanceof com.lynxspa.entities.securities.SPSecurity));

		return reply;
	}

	private OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToVirtualSecurity_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> getNodeConnectedToVirtualSecurity() {
		return nodeConnectedToVirtualSecurity_;
	}

	public void connectNodeToVirtualSecurity(
			OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToVirtualSecurity) {
		nodeConnectedToVirtualSecurity_ = nodeConnectedToVirtualSecurity;
	}

	/**
	 * Security isn't in customer portfolio
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesNotInCustomerPortfolioCondition(
			com.lynxspa.sdm.entities.events.messages.CAEventMessage message)
			throws Exception {
		boolean reply = false;

		reply = (!message.isInCustomerPortfolio())
				&& ((message.getNormalizedSecurity() instanceof com.lynxspa.entities.securities.SPSecurity));

		return reply;
	}

	private OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToNotInCustomerPortfolio_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> getNodeConnectedToNotInCustomerPortfolio() {
		return nodeConnectedToNotInCustomerPortfolio_;
	}

	public void connectNodeToNotInCustomerPortfolio(
			OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToNotInCustomerPortfolio) {
		nodeConnectedToNotInCustomerPortfolio_ = nodeConnectedToNotInCustomerPortfolio;
	}

	private OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToDefault_;

	@NodeConnection(order = 2)
	public OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.sdm.entities.events.messages.CAEventMessage> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public NotNormalizableReasonSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.sdm.entities.events.messages.CAEventMessage message)
			throws Exception {
		if (evaluatesVirtualSecurityCondition(message))
			getNodeConnectedToVirtualSecurity().process(message);
		else if (evaluatesNotInCustomerPortfolioCondition(message))
			getNodeConnectedToNotInCustomerPortfolio().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}