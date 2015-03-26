// $-- src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventCollected.fpmmap

package com.lynxspa.coac.normalizer.operationupdate.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventCollected.fpmmap")
public class UpdateEventCollectedMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter, com.lynxspa.sdm.entities.events.CAEventCollected> {

	// function libraries declarations
	com.lynxspa.fpm.functions.ObjectFunctions objectFunctions;
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;
	com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions coacNormalizeUpdateFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		objectFunctions = new com.lynxspa.fpm.functions.ObjectFunctions();
		objectFunctions.setNode(this);
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
		coacNormalizeUpdateFunctions = new com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions();
		coacNormalizeUpdateFunctions.setNode(this);
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

		//setting field complete

		// $-- /mn:mapping-element/field[1]
		java.lang.Boolean outputComplete;

		// $-- /mn:mapping-element/field[1]/function
		outputComplete =
		// $-- /mn:mapping-element/field[1]/function
		objectFunctions.or(in_previousEventCollected_complete(input),
		// $-- /mn:mapping-element/field[1]/function/function
				coacNormalizeUpdateFunctions.falseValue());
		output.setComplete(outputComplete);

		//setting field eventDetail

		// $-- /mn:mapping-element/field[2]
		com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailAdapter outputEventDetail;

		// $-- /mn:mapping-element/field[2]/function
		outputEventDetail =
		// $-- /mn:mapping-element/field[2]/function
		coacFunctions
				.toCAEventDetail(in_previousEventCollected_eventDetail(input));
		output.setEventDetail(outputEventDetail);

		//setting field executionDate

		// $-- /mn:mapping-element/field[3]
		java.util.Date outputExecutionDate;

		// $-- /mn:mapping-element/field[3]/inputField
		outputExecutionDate = in_previousEventCollected_eventDetail_executionDate(input);
		output.setExecutionDate(outputExecutionDate);

		//setting field expirationDate

		// $-- /mn:mapping-element/field[4]
		java.util.Date outputExpirationDate;

		// $-- /mn:mapping-element/field[4]/inputField
		outputExpirationDate = in_previousEventCollected_eventDetail_expirationDate(input);
		output.setExpirationDate(outputExpirationDate);

		//setting field operationalDate

		// $-- /mn:mapping-element/field[5]
		java.util.Date outputOperationalDate;

		// $-- /mn:mapping-element/field[5]/inputField
		outputOperationalDate = in_previousEventCollected_eventDetail_operationalDate(input);
		output.setOperationalDate(outputOperationalDate);

		//setting field providerUpdated

		// $-- /mn:mapping-element/field[6]
		java.lang.Boolean outputProviderUpdated;

		// $-- /mn:mapping-element/field[6]/constant
		outputProviderUpdated = true;
		output.setProviderUpdated(outputProviderUpdated);

		//setting field subscriptionDate

		// $-- /mn:mapping-element/field[7]
		java.util.Date outputSubscriptionDate;

		// $-- /mn:mapping-element/field[7]/inputField
		outputSubscriptionDate = in_previousEventCollected_eventDetail_subscriptionDate(input);
		output.setSubscriptionDate(outputSubscriptionDate);

		return output;
	}

	private java.lang.Boolean in_previousEventCollected_complete(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getComplete();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_previousEventCollected_eventDetail_subscriptionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail()
					.getSubscriptionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_previousEventCollected_eventDetail_expirationDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail()
					.getExpirationDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.CAEventCollected in_previousEventCollected(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_previousEventCollected_eventDetail_executionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail()
					.getExecutionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailAdapter in_previousEventCollected_eventDetail(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_previousEventCollected_eventDetail_operationalDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail()
					.getOperationalDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}