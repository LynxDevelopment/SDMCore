// $-- src/com/lynxspa/coac/notificationmanagers/nodes/IsNotificable.fpmswitch

package com.lynxspa.coac.notificationmanagers.nodes;

import org.apache.log4j.Logger;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.annotations.NodeConnection;
import com.lynxit.fpm.exceptions.SwitchException;
import com.lynxit.fpm.nodes.OutputConnectable;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/notificationmanagers/nodes/IsNotificable.fpmswitch")
public class IsNotificableSwitch
		extends
		com.lynxit.fpm.nodes.internal.SwitchNode<com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> {
	private Logger logger_ = Logger.getLogger(IsNotificableSwitch.class);

	/**
	 * 
	 *
	 * @param message message proceeding from previous node.
	 * @return the evaluation of the code. 
	 */
	private boolean evaluatesTrueCondition(
			com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean message)
			throws Exception {
		boolean reply = false;
		reply = message.getEmailAddresses().length > 0;

		return reply;
	}

	private OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> nodeConnectedToTrue_;

	@NodeConnection(order = 0)
	public OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> getNodeConnectedToTrue() {
		return nodeConnectedToTrue_;
	}

	public void connectNodeToTrue(
			OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> nodeConnectedToTrue) {
		nodeConnectedToTrue_ = nodeConnectedToTrue;
	}

	private OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> nodeConnectedToDefault_;

	@NodeConnection(order = 1)
	public OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> getNodeConnectedToDefault() {
		return nodeConnectedToDefault_;
	}

	public void connectNodeToDefault(
			OutputConnectable<? super com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> nodeConnectedToDefault) {
		nodeConnectedToDefault_ = nodeConnectedToDefault;
	}

	public IsNotificableSwitch() {
		super();
	}

	protected void processMessage(
			com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean message)
			throws Exception {
		if (evaluatesTrueCondition(message))
			getNodeConnectedToTrue().process(message);
		else
			getNodeConnectedToDefault().process(message);
	}
}