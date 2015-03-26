// $-- src/com/lynxspa/coac/importers/mappings/GetEventMessageFieldsFromImport.fpmmap

package com.lynxspa.coac.importers.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/mappings/GetEventMessageFieldsFromImport.fpmmap")
public class GetEventMessageFieldsFromImportMap
		extends
		MappingNode<com.lynxspa.coac.importers.EventMessageImportBean, java.util.Collection<java.lang.Object>> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public java.util.Collection<java.lang.Object> perform(
			com.lynxspa.coac.importers.EventMessageImportBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		java.util.Collection<java.lang.Object> output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_fields(input);

		return output;
	}

	private java.util.List in_fields(
			com.lynxspa.coac.importers.EventMessageImportBean input) {
		try {
			return input.getFields();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}