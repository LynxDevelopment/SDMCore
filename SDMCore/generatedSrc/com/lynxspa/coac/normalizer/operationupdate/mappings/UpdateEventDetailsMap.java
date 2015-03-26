// $-- src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventDetails.fpmmap

package com.lynxspa.coac.normalizer.operationupdate.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationupdate/mappings/UpdateEventDetails.fpmmap")
public class UpdateEventDetailsMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter, com.lynxspa.sdm.entities.events.details.CAEventDetail> {

	// function libraries declarations
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;
	com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions coacNormalizeUpdateFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
		coacNormalizeUpdateFunctions = new com.lynxspa.coac.normalizer.operationupdate.mappings.CoacNormalizeUpdateFunctions();
		coacNormalizeUpdateFunctions.setNode(this);
	}

	public com.lynxspa.sdm.entities.events.details.CAEventDetail perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.entities.events.details.CAEventDetail output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/function
		output =
		// $-- /mn:mapping-element/function
		coacFunctions
				.toCAEventDetail(in_previousEventCollected_eventDetail(input));

		//setting field dynamicTable

		// $-- /mn:mapping-element/field[1]
		java.util.HashMap<java.lang.String, java.lang.Object> outputDynamicTable;

		// $-- /mn:mapping-element/field[1]/function
		outputDynamicTable =
		// $-- /mn:mapping-element/field[1]/function
		coacNormalizeUpdateFunctions.getSecureDynamicTable(
				in_eventDetail_dynamicTable(input),
				in_previousEventCollected_eventDetail_dynamicTable(input));
		output.setDynamicTable(outputDynamicTable);

		//setting field executionDate

		// $-- /mn:mapping-element/field[2]
		java.util.Date outputExecutionDate;

		// $-- /mn:mapping-element/field[2]/function
		outputExecutionDate =
		// $-- /mn:mapping-element/field[2]/function
		coacNormalizeUpdateFunctions.getSecureDate(
				in_eventDetail_executionDate(input),
				in_previousEventCollected_eventDetail_executionDate(input));
		output.setExecutionDate(outputExecutionDate);

		//setting field expirationDate

		// $-- /mn:mapping-element/field[3]
		java.util.Date outputExpirationDate;

		// $-- /mn:mapping-element/field[3]/function
		outputExpirationDate =
		// $-- /mn:mapping-element/field[3]/function
		coacNormalizeUpdateFunctions.getSecureDate(
				in_eventDetail_expirationDate(input),
				in_previousEventCollected_eventDetail_expirationDate(input));
		output.setExpirationDate(outputExpirationDate);

		//setting field operationalDate

		// $-- /mn:mapping-element/field[4]
		java.util.Date outputOperationalDate;

		// $-- /mn:mapping-element/field[4]/function
		outputOperationalDate =
		// $-- /mn:mapping-element/field[4]/function
		coacNormalizeUpdateFunctions.getSecureDate(
				in_eventDetail_operationalDate(input),
				in_previousEventCollected_eventDetail_operationalDate(input));
		output.setOperationalDate(outputOperationalDate);

		//setting field subscriptionDate

		// $-- /mn:mapping-element/field[5]
		java.util.Date outputSubscriptionDate;

		// $-- /mn:mapping-element/field[5]/function
		outputSubscriptionDate =
		// $-- /mn:mapping-element/field[5]/function
		coacNormalizeUpdateFunctions.getSecureDate(
				in_eventDetail_subscriptionDate(input),
				in_previousEventCollected_eventDetail_subscriptionDate(input));
		output.setSubscriptionDate(outputSubscriptionDate);

		return output;
	}

	private java.util.Date in_eventDetail_expirationDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getEventDetail().getExpirationDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.HashMap in_eventDetail_dynamicTable(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getEventDetail().getDynamicTable();
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

	private java.util.Date in_eventDetail_operationalDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getEventDetail().getOperationalDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_executionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getEventDetail().getExecutionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.HashMap in_previousEventCollected_eventDetail_dynamicTable(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getPreviousEventCollected().getEventDetail()
					.getDynamicTable();
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

	private java.util.Date in_eventDetail_subscriptionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableUpdateAdapter input) {
		try {
			return input.getEventDetail().getSubscriptionDate();
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