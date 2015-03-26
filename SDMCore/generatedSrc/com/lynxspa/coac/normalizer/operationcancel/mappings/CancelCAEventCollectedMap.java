// $-- src/com/lynxspa/coac/normalizer/operationcancel/mappings/CancelCAEventCollected.fpmmap

package com.lynxspa.coac.normalizer.operationcancel.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationcancel/mappings/CancelCAEventCollected.fpmmap")
public class CancelCAEventCollectedMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter, com.lynxspa.sdm.entities.events.CAEventCollected> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.entities.events.CAEventCollected perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.CAEventCollected output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = in_previousEventCollected(input);

		//setting field providerCancelled

		// $-- /mn:mapping-element/field
		java.lang.Boolean outputProviderCancelled;

		// $-- /mn:mapping-element/field/constant
		outputProviderCancelled = true;
		output.setProviderCancelled(outputProviderCancelled);

		return output;
	}

	private com.lynxspa.sdm.entities.events.CAEventCollected in_previousEventCollected(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}