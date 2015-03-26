// $-- src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventDetailsExtension.fpmmap

package com.lynxspa.coac.normalizer.operationupdate.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventDetailsExtension.fpmmap")
public class UpdateEventDetailsExtensionMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean, com.lynxspa.sdm.entities.events.details.CAEventDetailExtended> {

	// function libraries declarations
	com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions coacNormalizeUpdateFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacNormalizeUpdateFunctions = new com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions();
		coacNormalizeUpdateFunctions.setNode(this);
	}

	public com.lynxspa.sdm.entities.events.details.CAEventDetailExtended perform(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.details.CAEventDetailExtended output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_existentVersion(input);

		//setting field dynamicTable

		// $-- /mn:mapping-element/field[1]
		java.util.HashMap<java.lang.String, java.lang.Object> outputDynamicTable;

		// $-- /mn:mapping-element/field[1]/function
		outputDynamicTable =
		// $-- /mn:mapping-element/field[1]/function
		coacNormalizeUpdateFunctions.getSecureDynamicTable(
				in_newVersion_dynamicTable(input),
				in_existentVersion_dynamicTable(input));
		output.setDynamicTable(outputDynamicTable);

		//setting field narrative

		// $-- /mn:mapping-element/field[2]
		java.lang.String outputNarrative;

		// $-- /mn:mapping-element/field[2]/function
		outputNarrative =
		// $-- /mn:mapping-element/field[2]/function
		coacNormalizeUpdateFunctions.getSecureString(
				in_newVersion_narrative(input),
				in_existentVersion_narrative(input));
		output.setNarrative(outputNarrative);

		return output;
	}

	private java.util.HashMap in_newVersion_dynamicTable(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input) {
		try {
			return input.getNewVersion().getDynamicTable();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.HashMap in_existentVersion_dynamicTable(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input) {
		try {
			return input.getExistentVersion().getDynamicTable();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_existentVersion_narrative(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input) {
		try {
			return input.getExistentVersion().getNarrative();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_newVersion_narrative(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input) {
		try {
			return input.getNewVersion().getNarrative();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.details.CAEventDetailExtended in_existentVersion(
			com.lynxspa.coac.normalizer.operationupdate.beans.EventDetailExtensionUpdateBean input) {
		try {
			return input.getExistentVersion();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}