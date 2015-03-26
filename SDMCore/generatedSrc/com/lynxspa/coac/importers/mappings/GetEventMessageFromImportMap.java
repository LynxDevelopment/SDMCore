// $-- src/com/lynxspa/coac/importers/mappings/GetEventMessageFromImport.fpmmap

package com.lynxspa.coac.importers.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/mappings/GetEventMessageFromImport.fpmmap")
public class GetEventMessageFromImportMap
		extends
		MappingNode<com.lynxspa.coac.importers.EventMessageImportBean, com.lynxspa.sdm.entities.events.messages.CAEventMessage> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.entities.events.messages.CAEventMessage perform(
			com.lynxspa.coac.importers.EventMessageImportBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.messages.CAEventMessage output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_message(input);

		return output;
	}

	private com.lynxspa.sdm.entities.events.messages.CAEventMessage in_message(
			com.lynxspa.coac.importers.EventMessageImportBean input) {
		try {
			return input.getMessage();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}