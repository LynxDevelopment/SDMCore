// $-- src/com/lynxspa/coac/normalizer/mappings/PromoteToNormalizationUpdaterAdapter.fpmmap

package com.lynxspa.coac.normalizer.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/mappings/PromoteToNormalizationUpdaterAdapter.fpmmap")
public class PromoteToNormalizationUpdaterAdapterMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.coac.normalizer.beans.MessageNormalizeUpdaterBean> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.coac.normalizer.beans.MessageNormalizeUpdaterBean perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.normalizer.beans.MessageNormalizeUpdaterBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.coac.normalizer.beans.MessageNormalizeUpdaterBean();

		//setting field eventCollected

		// $-- /mn:mapping-element/field[1]
		com.lynxspa.sdm.entities.events.CAEventCollected outputEventCollected;

		// $-- /mn:mapping-element/field[1]/inputField
		outputEventCollected = in_eventCollected(input);
		output.setEventCollected(outputEventCollected);

		//setting field eventDetail

		// $-- /mn:mapping-element/field[2]
		com.lynxspa.sdm.entities.events.details.CAEventDetail outputEventDetail;

		// $-- /mn:mapping-element/field[2]/inputField
		outputEventDetail = in_eventDetail(input);
		output.setEventDetail(outputEventDetail);

		//setting field eventDetailExtended

		// $-- /mn:mapping-element/field[3]
		java.util.List<com.lynxspa.sdm.entities.events.details.CAEventDetailExtended> outputEventDetailExtended;

		// $-- /mn:mapping-element/field[3]/inputField
		outputEventDetailExtended = in_eventDetailExtended(input);
		output.setEventDetailExtended(outputEventDetailExtended);

		//setting field message

		// $-- /mn:mapping-element/field[4]
		com.lynxspa.sdm.entities.events.messages.CAEventMessage outputMessage;

		// $-- /mn:mapping-element/field[4]/inputField
		outputMessage = in_message(input);
		output.setMessage(outputMessage);

		return output;
	}

	private java.util.List in_eventDetailExtended(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetailExtended();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.messages.CAEventMessage in_message(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.details.CAEventDetail in_eventDetail(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail();
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