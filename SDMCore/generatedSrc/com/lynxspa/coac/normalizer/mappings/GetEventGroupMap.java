// $-- src/com/lynxspa/coac/normalizer/mappings/GetEventGroup.fpmmap

package com.lynxspa.coac.normalizer.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/mappings/GetEventGroup.fpmmap")
public class GetEventGroupMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter, com.lynxspa.sdm.entities.events.CAEventGroup> {

	// function libraries declarations
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
	}

	public com.lynxspa.sdm.entities.events.CAEventGroup perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input)
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

		// $-- /mn:mapping-element/function
		output =
		// $-- /mn:mapping-element/function
		coacFunctions
				.toCAEventGroup(in_previousEventCollected_eventGroup(input));

		return output;
	}

	private com.lynxspa.sdm.entities.events.adapters.CAEventGroupAdapter in_previousEventCollected_eventGroup(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventGroup();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}