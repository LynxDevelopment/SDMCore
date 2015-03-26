// $-- src/com/lynxspa/coac/importers/inversis/mappings/preprocessInversis.fpmmap

package com.lynxspa.coac.importers.inversis.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/inversis/mappings/preprocessInversis.fpmmap")
public class PreprocessInversisMap
		extends
		MappingNode<com.lynxspa.coac.importers.EventMessageImportBean, com.lynxspa.coac.importers.EventMessageImportBean> {

	// function libraries declarations
	com.lynxspa.coac.importers.mappings.CoacImportFunctions coacImportFunctions;
	com.lynxit.fpm.functionlibraries.StringFunctions stringFunctions;
	com.lynxspa.fpm.functions.FPMFunctions fPMFunctions;
	com.lynxit.fpm.functionlibraries.TimeFunctions timeFunctions;
	com.lynxspa.fpm.functions.ObjectFunctions objectFunctions;
	com.lynxspa.coac.mappings.CoacFunctions coacFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacImportFunctions = new com.lynxspa.coac.importers.mappings.CoacImportFunctions();
		coacImportFunctions.setNode(this);
		stringFunctions = new com.lynxit.fpm.functionlibraries.StringFunctions();
		stringFunctions.setNode(this);
		fPMFunctions = new com.lynxspa.fpm.functions.FPMFunctions();
		fPMFunctions.setNode(this);
		timeFunctions = new com.lynxit.fpm.functionlibraries.TimeFunctions();
		timeFunctions.setNode(this);
		objectFunctions = new com.lynxspa.fpm.functions.ObjectFunctions();
		objectFunctions.setNode(this);
		coacFunctions = new com.lynxspa.coac.mappings.CoacFunctions();
		coacFunctions.setNode(this);
	}

	public com.lynxspa.coac.importers.EventMessageImportBean perform(
			com.lynxspa.coac.importers.EventMessageImportBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.coac.importers.EventMessageImportBean output;

		/*
		 * transformation
		 */

		// $-- /mn:mapping-element/inputField
		output = input;

		//setting field message

		// $-- /mn:mapping-element/field
		com.lynxspa.sdm.entities.events.messages.CAEventMessage outputMessage;
		if (output.getMessage() != null) {
			outputMessage = output.getMessage();
		} else
			outputMessage = new com.lynxspa.sdm.entities.events.messages.CAEventMessage();

		//setting field announceDate

		// $-- /mn:mapping-element/field/field[1]
		java.util.Date outputMessageAnnounceDate;

		// $-- /mn:mapping-element/field/field[1]/function
		outputMessageAnnounceDate =
		// $-- /mn:mapping-element/field/field[1]/function
		objectFunctions.parseSecureDate(
				// $-- /mn:mapping-element/field/field[1]/function/function
				coacImportFunctions
						.getImportMessageField(input, "INV:430-437:"),
				"yyyyMMdd");
		outputMessage.setAnnounceDate(outputMessageAnnounceDate);

		//setting field effectiveDate

		// $-- /mn:mapping-element/field/field[2]
		java.util.Date outputMessageEffectiveDate;

		// $-- /mn:mapping-element/field/field[2]/function
		outputMessageEffectiveDate =
		// $-- /mn:mapping-element/field/field[2]/function
		objectFunctions.parseSecureDate(
				// $-- /mn:mapping-element/field/field[2]/function/function
				coacImportFunctions
						.getImportMessageField(input, "INV:711-718:"),
				"yyyyMMdd");
		outputMessage.setEffectiveDate(outputMessageEffectiveDate);

		//setting field eventReference

		// $-- /mn:mapping-element/field/field[3]
		java.lang.String outputMessageEventReference;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageEventReference =
		// $-- /mn:mapping-element/field/field[3]/function
		coacImportFunctions.getImportMessageField(input, "INV:38-46:");
		outputMessage.setEventReference(outputMessageEventReference);

		//setting field eventType

		// $-- /mn:mapping-element/field/field[4]
		java.lang.String outputMessageEventType;

		// $-- /mn:mapping-element/field/field[4]/function
		outputMessageEventType =
		// $-- /mn:mapping-element/field/field[4]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field/field[4]/function/function
				objectFunctions.condition(
						// $-- /mn:mapping-element/field/field[4]/function/function/function[1]
						objectFunctions.isNull(
						// $-- /mn:mapping-element/field/field[4]/function/function/function[1]/function
								coacImportFunctions.getImportMessageField(
										input, "INV:47-48:")),
						// $-- /mn:mapping-element/field/field[4]/function/function/function[2]
						coacImportFunctions.getImportMessageField(input,
								"INV:38-46:"),
						// $-- /mn:mapping-element/field/field[4]/function/function/function[3]
						coacImportFunctions.getImportMessageField(input,
								"INV:47-48:")));
		outputMessage.setEventType(outputMessageEventType);

		//setting field messageId

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageMessageId;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageMessageId =
		// $-- /mn:mapping-element/field/field[5]/function
		objectFunctions
				.stringValueOf(
				// $-- /mn:mapping-element/field/field[5]/function/function
				objectFunctions.condition(
				// $-- /mn:mapping-element/field/field[5]/function/function/function[1]
						objectFunctions.isNull(
						// $-- /mn:mapping-element/field/field[5]/function/function/function[1]/function
								coacImportFunctions.getImportMessageField(
										input, "INV:47-48:")),
						// $-- /mn:mapping-element/field/field[5]/function/function/function[2]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]
								stringFunctions.concat(
								// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[1]
										coacImportFunctions
												.getImportMessageField(input,
														"INV:108-119:"),
										// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[2]
										objectFunctions.stringValueOf(
										// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[2]/function
										objectFunctions.condition(
												// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[2]/function/function[1]
												objectFunctions
														.isNull(
														// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[2]/function/function[1]/function
														coacImportFunctions
																.getImportMessageField(
																		input,
																		"INV:577-584:")),
												"00000000",
												// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[1]/function[2]/function/function[2]
												coacImportFunctions
														.getImportMessageField(
																input,
																"INV:577-584:")))),
								// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]
								stringFunctions.concat(
								// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]/function[1]
										coacImportFunctions.paddDouble(
												// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]/function[1]/function
												objectFunctions
														.parseDouble(
														// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]/function[1]/function/function
														coacImportFunctions
																.getImportMessageField(
																		input,
																		"INV:437-454:")),
												"0000000000.00000000"),
										// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]/function[2]
										coacImportFunctions.paddString(
												// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function[2]/function[2]/function
												coacImportFunctions
														.getImportMessageField(
																input,
																"INV:247-274:"),
												28))),
						// $-- /mn:mapping-element/field/field[5]/function/function/function[3]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[5]/function/function/function[3]/function[1]
								coacImportFunctions.getImportMessageField(
										input, "INV:108-119:"),
								// $-- /mn:mapping-element/field/field[5]/function/function/function[3]/function[2]
								objectFunctions.getHash(
								// $-- /mn:mapping-element/field/field[5]/function/function/function[3]/function[2]/function
										objectFunctions.stringValueOf(
										// $-- /mn:mapping-element/field/field[5]/function/function/function[3]/function[2]/function/function
												timeFunctions.now())))));
		outputMessage.setMessageId(outputMessageMessageId);

		//setting field messageType

		// $-- /mn:mapping-element/field/field[6]
		com.lynxspa.sdm.entities.events.messages.formats.CAMessageType outputMessageMessageType;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessageMessageType =
		// $-- /mn:mapping-element/field/field[6]/function
		coacImportFunctions.getMessageType("HibernateResource",
				com.lynxspa.sdm.dictionaries.formats.CAFormat.INVERSIS,
				in_messageType(input));
		outputMessage.setMessageType(outputMessageMessageType);

		//setting field operation

		// $-- /mn:mapping-element/field/field[7]
		java.lang.String outputMessageOperation;

		// $-- /mn:mapping-element/field/field[7]/function
		outputMessageOperation =
		// $-- /mn:mapping-element/field/field[7]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field/field[7]/function/function
				objectFunctions.subString(
						// $-- /mn:mapping-element/field/field[7]/function/function/function
						coacImportFunctions.getImportMessageField(input,
								"INV:38-46:"), 0, 1));
		outputMessage.setOperation(outputMessageOperation);

		//setting field operationStatus

		// $-- /mn:mapping-element/field/field[8]
		com.lynxspa.entities.application.flow.operations.OperationStatus outputMessageOperationStatus;
		if (outputMessage.getOperationStatus() != null) {
			outputMessageOperationStatus = outputMessage.getOperationStatus();
		} else
			outputMessageOperationStatus = new com.lynxspa.entities.application.flow.operations.OperationStatus();

		//setting field state

		// $-- /mn:mapping-element/field/field[8]/field
		com.lynxspa.entities.application.flow.State outputMessageOperationStatusState;

		// $-- /mn:mapping-element/field/field[8]/field/function
		outputMessageOperationStatusState =
		// $-- /mn:mapping-element/field/field[8]/field/function
		coacFunctions
				.getEventMessageInitialState(
						"HibernateResource",
						com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.PRSD);
		outputMessageOperationStatus
				.setState(outputMessageOperationStatusState);
		outputMessage.setOperationStatus(outputMessageOperationStatus);

		//setting field originId

		// $-- /mn:mapping-element/field/field[9]
		java.lang.String outputMessageOriginId;

		// $-- /mn:mapping-element/field/field[9]/function
		outputMessageOriginId =
		// $-- /mn:mapping-element/field/field[9]/function
		objectFunctions.getHash(
		// $-- /mn:mapping-element/field/field[9]/function/function
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[9]/function/function/function[1]
						objectFunctions.stringValueOf(
						// $-- /mn:mapping-element/field/field[9]/function/function/function[1]/function
								timeFunctions.now()),
						// $-- /mn:mapping-element/field/field[9]/function/function/function[2]
						fPMFunctions.getContextStringParam("fileName")));
		outputMessage.setOriginId(outputMessageOriginId);

		//setting field originName

		// $-- /mn:mapping-element/field/field[10]
		java.lang.String outputMessageOriginName;

		// $-- /mn:mapping-element/field/field[10]/function
		outputMessageOriginName =
		// $-- /mn:mapping-element/field/field[10]/function
		fPMFunctions.getContextStringParam("fileName");
		outputMessage.setOriginName(outputMessageOriginName);

		//setting field originType

		// $-- /mn:mapping-element/field/field[11]
		java.lang.String outputMessageOriginType;

		// $-- /mn:mapping-element/field/field[11]/constant
		outputMessageOriginType = "File";
		outputMessage.setOriginType(outputMessageOriginType);

		//setting field security

		// $-- /mn:mapping-element/field/field[12]
		java.lang.String outputMessageSecurity;

		// $-- /mn:mapping-element/field/field[12]/function
		outputMessageSecurity =
		// $-- /mn:mapping-element/field/field[12]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field/field[12]/function/function
				objectFunctions.condition(
						// $-- /mn:mapping-element/field/field[12]/function/function/function[1]
						objectFunctions.isNull(
						// $-- /mn:mapping-element/field/field[12]/function/function/function[1]/function
								coacImportFunctions.getImportMessageField(
										input, "INV:108-119:")),
						// $-- /mn:mapping-element/field/field[12]/function/function/function[2]
						coacImportFunctions.getImportMessageField(input,
								"INV:1-4:"),
						// $-- /mn:mapping-element/field/field[12]/function/function/function[3]
						coacImportFunctions.getImportMessageField(input,
								"INV:108-119:")));
		outputMessage.setSecurity(outputMessageSecurity);

		//setting field securityName

		// $-- /mn:mapping-element/field/field[13]
		java.lang.String outputMessageSecurityName;

		// $-- /mn:mapping-element/field/field[13]/constant
		outputMessageSecurityName = "NONAME";
		outputMessage.setSecurityName(outputMessageSecurityName);

		//setting field securityType

		// $-- /mn:mapping-element/field/field[14]
		java.lang.String outputMessageSecurityType;

		// $-- /mn:mapping-element/field/field[14]/constant
		outputMessageSecurityType = "ISIN";
		outputMessage.setSecurityType(outputMessageSecurityType);

		//setting field sender

		// $-- /mn:mapping-element/field/field[15]
		java.lang.String outputMessageSender;

		// $-- /mn:mapping-element/field/field[15]/function
		outputMessageSender =
		// $-- /mn:mapping-element/field/field[15]/function
		coacImportFunctions.getMessageFormatId(
		// $-- /mn:mapping-element/field/field[15]/function/function
				coacImportFunctions.getMessageType("HibernateResource",
						com.lynxspa.sdm.dictionaries.formats.CAFormat.INVERSIS,
						in_messageType(input)));
		outputMessage.setSender(outputMessageSender);

		//setting field senderTimestamp

		// $-- /mn:mapping-element/field/field[16]
		java.util.Date outputMessageSenderTimestamp;

		// $-- /mn:mapping-element/field/field[16]/function
		outputMessageSenderTimestamp =
		// $-- /mn:mapping-element/field/field[16]/function
		objectFunctions.parseSecureDate(
				// $-- /mn:mapping-element/field/field[16]/function/function
				coacImportFunctions
						.getImportMessageField(input, "INV:719-726:"),
				"yyyyMMdd");
		outputMessage.setSenderTimestamp(outputMessageSenderTimestamp);
		output.setMessage(outputMessage);

		return output;
	}

	private java.lang.String in_messageType(
			com.lynxspa.coac.importers.EventMessageImportBean input) {
		try {
			return input.getMessageType();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}