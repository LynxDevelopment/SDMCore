// $-- src/com/lynxspa/coac/notificationmanagers/mappings/NotificationEvent.fpmmap

package com.lynxspa.coac.notificationmanagers.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/notificationmanagers/mappings/NotificationEvent.fpmmap")
public class NotificationEventMap
		extends
		MappingNode<com.lynxspa.sdm.entities.events.CAEventGroup, com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean perform(
			com.lynxspa.sdm.entities.events.CAEventGroup input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean();

		//setting field eventDate

		// $-- /mn:mapping-element/field[1]
		java.util.Date outputEventDate;

		// $-- /mn:mapping-element/field[1]/inputField
		outputEventDate = in_entityDeadLine(input);
		output.setEventDate(outputEventDate);

		//setting field eventGroup

		// $-- /mn:mapping-element/field[2]
		com.lynxspa.sdm.entities.events.CAEventGroup outputEventGroup;

		// $-- /mn:mapping-element/field[2]/inputField
		outputEventGroup = input;
		output.setEventGroup(outputEventGroup);

		return output;
	}

	private java.util.Date in_entityDeadLine(
			com.lynxspa.sdm.entities.events.CAEventGroup input) {
		try {
			return input.getEntityDeadLine();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}