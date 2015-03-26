// $-- src/com/lynxspa/coac/normalizer/operationnew/mappings/SetEventCollectedToNormalizableAdapter.fpmmap

package com.lynxspa.coac.normalizer.operationnew.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/mappings/SetEventCollectedToNormalizableAdapter.fpmmap")
public class SetEventCollectedToNormalizableAdapterMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.coac.normalizer.adapters.NormalizableAdapter> {

	// function libraries declarations
	com.lynxspa.coac.normalizer.mappings.CoacNormalizerFunctions coacNormalizerFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacNormalizerFunctions = new com.lynxspa.coac.normalizer.mappings.CoacNormalizerFunctions();
		coacNormalizerFunctions.setNode(this);
	}

	public com.lynxspa.coac.normalizer.adapters.NormalizableAdapter perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.normalizer.adapters.NormalizableAdapter output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = input;

		//setting field eventCollected

		// $-- /mn:mapping-element/field
		com.lynxspa.sdm.entities.events.CAEventCollected outputEventCollected;

		// $-- /mn:mapping-element/field/function
		outputEventCollected =
		// $-- /mn:mapping-element/field/function
		coacNormalizerFunctions
				.getEventCollectedFromContext("EventCollectedFound");
		output.setEventCollected(outputEventCollected);

		return output;
	}

}