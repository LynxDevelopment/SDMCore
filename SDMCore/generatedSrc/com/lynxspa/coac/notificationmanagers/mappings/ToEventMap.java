// $-- src/com/lynxspa/coac/notificationmanagers/mappings/ToEvent.fpmmap

package com.lynxspa.coac.notificationmanagers.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/notificationmanagers/mappings/ToEvent.fpmmap")
public class ToEventMap
		extends
		MappingNode<com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean, com.lynxspa.sdm.entities.events.CAEventGroup> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.entities.events.CAEventGroup perform(
			com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.CAEventGroup output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_eventGroup(input);

		return output;
	}

	private com.lynxspa.sdm.entities.events.CAEventGroup in_eventGroup(
			com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean input) {
		try {
			return input.getEventGroup();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}