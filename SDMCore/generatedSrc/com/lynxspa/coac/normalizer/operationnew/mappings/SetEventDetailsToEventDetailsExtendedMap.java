// $-- src/com/lynxspa/coac/normalizer/operationnew/mappings/SetEventDetailsToEventDetailsExtended.fpmmap

package com.lynxspa.coac.normalizer.operationnew.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/mappings/SetEventDetailsToEventDetailsExtended.fpmmap")
public class SetEventDetailsToEventDetailsExtendedMap
		extends
		MappingNode<com.lynxspa.sdm.entities.events.details.CAEventDetailExtended, com.lynxspa.sdm.entities.events.details.CAEventDetailExtended> {

	// function libraries declarations
	com.lynxspa.coac.normalizer.mappings.CoacNormalizerFunctions coacNormalizerFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacNormalizerFunctions = new com.lynxspa.coac.normalizer.mappings.CoacNormalizerFunctions();
		coacNormalizerFunctions.setNode(this);
	}

	public com.lynxspa.sdm.entities.events.details.CAEventDetailExtended perform(
			com.lynxspa.sdm.entities.events.details.CAEventDetailExtended input)
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
		output = input;

		//setting field mainDetail

		// $-- /mn:mapping-element/field
		com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailAdapter outputMainDetail;

		// $-- /mn:mapping-element/field/function
		outputMainDetail =
		// $-- /mn:mapping-element/field/function
		coacNormalizerFunctions.getEventDetailFromContext("EventDetailFound");
		output.setMainDetail(outputMainDetail);

		return output;
	}

}