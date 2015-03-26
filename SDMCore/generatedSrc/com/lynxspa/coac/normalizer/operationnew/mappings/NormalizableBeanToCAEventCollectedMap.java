// $-- src/com/lynxspa/coac/normalizer/operationnew/mappings/NormalizableBeanToCAEventCollected.fpmmap

package com.lynxspa.coac.normalizer.operationnew.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/normalizer/operationnew/mappings/NormalizableBeanToCAEventCollected.fpmmap")
public class NormalizableBeanToCAEventCollectedMap
		extends
		MappingNode<com.lynxspa.coac.normalizer.adapters.NormalizableAdapter, com.lynxspa.sdm.entities.events.CAEventCollected> {

	// function libraries declarations
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
	}

	public com.lynxspa.sdm.entities.events.CAEventCollected perform(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input)
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

		output = new com.lynxspa.sdm.entities.events.CAEventCollected();

		//setting field auditor

		// $-- /mn:mapping-element/field[1]
		com.lynxspa.entities.VersionAuditor outputAuditor;
		if (output.getAuditor() != null) {
			outputAuditor = output.getAuditor();
		} else
			outputAuditor = new com.lynxspa.entities.VersionAuditor();

		//setting field creationDate

		// $-- /mn:mapping-element/field[1]/field[1]
		java.util.Date outputAuditorCreationDate;

		// $-- /mn:mapping-element/field[1]/field[1]/inputField
		outputAuditorCreationDate = in_eventDetail_auditor_creationDate(input);
		outputAuditor.setCreationDate(outputAuditorCreationDate);

		//setting field creationUser

		// $-- /mn:mapping-element/field[1]/field[2]
		java.lang.String outputAuditorCreationUser;

		// $-- /mn:mapping-element/field[1]/field[2]/inputField
		outputAuditorCreationUser = in_eventDetail_auditor_creationUser(input);
		outputAuditor.setCreationUser(outputAuditorCreationUser);
		output.setAuditor(outputAuditor);

		//setting field complete

		// $-- /mn:mapping-element/field[2]
		java.lang.Boolean outputComplete;

		// $-- /mn:mapping-element/field[2]/constant
		outputComplete = false;
		output.setComplete(outputComplete);

		//setting field eventDetail

		// $-- /mn:mapping-element/field[3]
		com.lynxspa.sdm.entities.events.details.adapters.CAEventDetailAdapter outputEventDetail;

		// $-- /mn:mapping-element/field[3]/inputField
		outputEventDetail = in_eventDetail(input);
		output.setEventDetail(outputEventDetail);

		//setting field eventProvider

		// $-- /mn:mapping-element/field[4]
		com.lynxspa.sdm.entities.events.providers.CAEventProvider outputEventProvider;

		// $-- /mn:mapping-element/field[4]/inputField
		outputEventProvider = in_message_eventProvider(input);
		output.setEventProvider(outputEventProvider);

		//setting field eventType

		// $-- /mn:mapping-element/field[5]
		com.lynxspa.sdm.entities.events.types.CAEventType outputEventType;

		// $-- /mn:mapping-element/field[5]/inputField
		outputEventType = in_message_normalizedEventType(input);
		output.setEventType(outputEventType);

		//setting field executionDate

		// $-- /mn:mapping-element/field[6]
		java.util.Date outputExecutionDate;

		// $-- /mn:mapping-element/field[6]/inputField
		outputExecutionDate = in_eventDetail_executionDate(input);
		output.setExecutionDate(outputExecutionDate);

		//setting field expirationDate

		// $-- /mn:mapping-element/field[7]
		java.util.Date outputExpirationDate;

		// $-- /mn:mapping-element/field[7]/inputField
		outputExpirationDate = in_eventDetail_expirationDate(input);
		output.setExpirationDate(outputExpirationDate);

		//setting field operationStatus

		// $-- /mn:mapping-element/field[8]
		com.lynxspa.entities.application.flow.operations.OperationStatus outputOperationStatus;
		if (output.getOperationStatus() != null) {
			outputOperationStatus = output.getOperationStatus();
		} else
			outputOperationStatus = new com.lynxspa.entities.application.flow.operations.OperationStatus();

		//setting field ended

		// $-- /mn:mapping-element/field[8]/field[1]
		java.lang.Boolean outputOperationStatusEnded;

		// $-- /mn:mapping-element/field[8]/field[1]/constant
		outputOperationStatusEnded = false;
		outputOperationStatus.setEnded(outputOperationStatusEnded);

		//setting field state

		// $-- /mn:mapping-element/field[8]/field[2]
		com.lynxspa.entities.application.flow.State outputOperationStatusState;

		// $-- /mn:mapping-element/field[8]/field[2]/function
		outputOperationStatusState =
		// $-- /mn:mapping-element/field[8]/field[2]/function
		coacFunctions
				.getEventCollectedInitialState(
						"HibernateResource",
						com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTCOLLECTEDFlow.UNMC);
		outputOperationStatus.setState(outputOperationStatusState);
		output.setOperationStatus(outputOperationStatus);

		//setting field operationalDate

		// $-- /mn:mapping-element/field[9]
		java.util.Date outputOperationalDate;

		// $-- /mn:mapping-element/field[9]/inputField
		outputOperationalDate = in_eventDetail_operationalDate(input);
		output.setOperationalDate(outputOperationalDate);

		//setting field security

		// $-- /mn:mapping-element/field[10]
		com.lynxspa.entities.securities.SPSecurity outputSecurity;
		if (output.getSecurity() != null) {
			outputSecurity = output.getSecurity();
		} else
			outputSecurity = new com.lynxspa.entities.securities.SPSecurity();

		//setting field id

		// $-- /mn:mapping-element/field[10]/field
		java.lang.Long outputSecurityId;

		// $-- /mn:mapping-element/field[10]/field/inputField
		outputSecurityId = in_message_normalizedSecurity_id(input);
		outputSecurity.setId(outputSecurityId);
		output.setSecurity(outputSecurity);

		//setting field subscriptionDate

		// $-- /mn:mapping-element/field[11]
		java.util.Date outputSubscriptionDate;

		// $-- /mn:mapping-element/field[11]/inputField
		outputSubscriptionDate = in_eventDetail_subscriptionDate(input);
		output.setSubscriptionDate(outputSubscriptionDate);

		return output;
	}

	private com.lynxspa.sdm.entities.events.types.CAEventType in_message_normalizedEventType(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage().getNormalizedEventType();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_expirationDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getExpirationDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_operationalDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getOperationalDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_eventDetail_auditor_creationUser(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getAuditor().getCreationUser();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_executionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getExecutionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.sdm.entities.events.providers.CAExternalEventProvider in_message_eventProvider(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage().getEventProvider();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_auditor_creationDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getAuditor().getCreationDate();
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

	private java.lang.Long in_message_normalizedSecurity_id(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getMessage().getNormalizedSecurity().getId();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.Date in_eventDetail_subscriptionDate(
			com.lynxspa.coac.normalizer.adapters.NormalizableAdapter input) {
		try {
			return input.getEventDetail().getSubscriptionDate();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}