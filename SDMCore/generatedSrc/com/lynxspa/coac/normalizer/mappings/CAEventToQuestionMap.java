// $-- src/com/lynxspa/coac/normalizer/mappings/CAEventToQuestion.fpmmap

package com.lynxspa.coac.normalizer.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/mappings/CAEventToQuestion.fpmmap")
public class CAEventToQuestionMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.coac.normalizer.beans.CAQuestionBean> {

	// function libraries declarations
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
	}

	public com.lynxspa.coac.normalizer.beans.CAQuestionBean perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.normalizer.beans.CAQuestionBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.normalizer.beans.CAQuestionBean();

		//setting field event

		// $-- /mn:mapping-element/field[1]
		com.lynxspa.sdm.entities.events.CAEventCollected outputEvent;

		// $-- /mn:mapping-element/field[1]/inputField
		outputEvent = in_eventCollected(input);
		output.setEvent(outputEvent);

		//setting field mandatory

		// $-- /mn:mapping-element/field[2]
		java.lang.Boolean outputMandatory;

		// $-- /mn:mapping-element/field[2]/function
		outputMandatory =
		// $-- /mn:mapping-element/field[2]/function
		coacFunctions.isMandatory(in_eventDetail(input), "BOOLEAN1");
		output.setMandatory(outputMandatory);

		//setting field originalMessage

		// $-- /mn:mapping-element/field[3]
		java.lang.String outputOriginalMessage;

		// $-- /mn:mapping-element/field[3]/inputField
		outputOriginalMessage = in_message_originalMessage(input);
		output.setOriginalMessage(outputOriginalMessage);

		return output;
	}

	private com.lynxspa.sdm.entities.events.details.CAEventDetail in_eventDetail(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_message_originalMessage(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage().getOriginalMessage();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.CAEventCollected in_eventCollected(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventCollected();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}