// $-- src/com/lynxspa/coac/normalizer/mappings/GetCAEventMessageFromNormalizableBean.fpmmap

package com.lynxspa.coac.normalizer.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/mappings/GetCAEventMessageFromNormalizableBean.fpmmap")
public class GetCAEventMessageFromNormalizableBeanMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.sdm.entities.events.messages.CAEventMessage> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.entities.events.messages.CAEventMessage perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
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

		//setting field normalizedEvent

		// $-- /mn:mapping-element/field
		java.lang.Long outputNormalizedEvent;

		// $-- /mn:mapping-element/field/inputField
		outputNormalizedEvent = in_eventCollected_id(input);
		output.setNormalizedEvent(outputNormalizedEvent);

		return output;
	}

	private com.lynxspa.sdm.entities.events.messages.CAEventMessage in_message(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.Long in_eventCollected_id(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventCollected().getId();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}