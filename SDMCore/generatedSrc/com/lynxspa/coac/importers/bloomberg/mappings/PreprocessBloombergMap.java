// $-- src/com/lynxspa/coac/importers/bloomberg/mappings/preprocessBloomberg.fpmmap

package com.lynxspa.coac.importers.bloomberg.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/bloomberg/mappings/preprocessBloomberg.fpmmap")
public class PreprocessBloombergMap
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
		// $-- /mn:mapping-element/field/field[1]/function/function[1]
				coacImportFunctions.getImportMessageField(input, "STD:9"),
				// $-- /mn:mapping-element/field/field[1]/function/function[2]
				coacImportFunctions.getImportMessageField(input, "FMT:DATE"));
		outputMessage.setAnnounceDate(outputMessageAnnounceDate);

		//setting field effectiveDate

		// $-- /mn:mapping-element/field/field[2]
		java.util.Date outputMessageEffectiveDate;

		// $-- /mn:mapping-element/field/field[2]/function
		outputMessageEffectiveDate =
		// $-- /mn:mapping-element/field/field[2]/function
		objectFunctions.parseSecureDate(
		// $-- /mn:mapping-element/field/field[2]/function/function[1]
				coacImportFunctions.getImportMessageField(input, "STD:10"),
				// $-- /mn:mapping-element/field/field[2]/function/function[2]
				coacImportFunctions.getImportMessageField(input, "FMT:DATE"));
		outputMessage.setEffectiveDate(outputMessageEffectiveDate);

		//setting field eventMessageReference

		// $-- /mn:mapping-element/field/field[3]
		java.lang.String outputMessageEventMessageReference;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageEventMessageReference =
		// $-- /mn:mapping-element/field/field[3]/function
		coacImportFunctions.getImportMessageField(input, "STD:0");
		outputMessage
				.setEventMessageReference(outputMessageEventMessageReference);

		//setting field eventReference

		// $-- /mn:mapping-element/field/field[4]
		java.lang.String outputMessageEventReference;

		// $-- /mn:mapping-element/field/field[4]/function
		outputMessageEventReference =
		// $-- /mn:mapping-element/field/field[4]/function
		coacImportFunctions.getImportMessageField(input, "STD:0");
		outputMessage.setEventReference(outputMessageEventReference);

		//setting field eventType

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageEventType;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageEventType =
		// $-- /mn:mapping-element/field/field[5]/function
		coacImportFunctions.getImportMessageField(input, "STD:1");
		outputMessage.setEventType(outputMessageEventType);

		//setting field messageId

		// $-- /mn:mapping-element/field/field[6]
		java.lang.String outputMessageMessageId;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessageMessageId =
		// $-- /mn:mapping-element/field/field[6]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[6]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[1]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[1]/function[1]
								coacImportFunctions.getImportMessageField(
										input, "HDR:0"),
								// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[1]/function[2]
								coacImportFunctions.getImportMessageField(
										input, "HDR:1")),
						// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"STD:1")),
				// $-- /mn:mapping-element/field/field[6]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"STD:2"),
						// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"STD:10")));
		outputMessage.setMessageId(outputMessageMessageId);

		//setting field messageType

		// $-- /mn:mapping-element/field/field[7]
		com.lynxspa.sdm.entities.events.messages.formats.CAMessageType outputMessageMessageType;

		// $-- /mn:mapping-element/field/field[7]/function
		outputMessageMessageType =
		// $-- /mn:mapping-element/field/field[7]/function
		coacImportFunctions.getMessageType("HibernateResource",
				com.lynxspa.sdm.dictionaries.formats.CAFormat.BLOOMBERG,
				in_messageType(input));
		outputMessage.setMessageType(outputMessageMessageType);

		//setting field operation

		// $-- /mn:mapping-element/field/field[8]
		java.lang.String outputMessageOperation;

		// $-- /mn:mapping-element/field/field[8]/function
		outputMessageOperation =
		// $-- /mn:mapping-element/field/field[8]/function
		coacImportFunctions.getImportMessageField(input, "STD:2");
		outputMessage.setOperation(outputMessageOperation);

		//setting field operationStatus

		// $-- /mn:mapping-element/field/field[9]
		com.lynxspa.entities.application.flow.operations.OperationStatus outputMessageOperationStatus;
		if (outputMessage.getOperationStatus() != null) {
			outputMessageOperationStatus = outputMessage.getOperationStatus();
		} else
			outputMessageOperationStatus = new com.lynxspa.entities.application.flow.operations.OperationStatus();

		//setting field state

		// $-- /mn:mapping-element/field/field[9]/field
		com.lynxspa.entities.application.flow.State outputMessageOperationStatusState;

		// $-- /mn:mapping-element/field/field[9]/field/function
		outputMessageOperationStatusState =
		// $-- /mn:mapping-element/field/field[9]/field/function
		coacFunctions
				.getEventMessageInitialState(
						"HibernateResource",
						com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.PRSD);
		outputMessageOperationStatus
				.setState(outputMessageOperationStatusState);
		outputMessage.setOperationStatus(outputMessageOperationStatus);

		//setting field originId

		// $-- /mn:mapping-element/field/field[10]
		java.lang.String outputMessageOriginId;

		// $-- /mn:mapping-element/field/field[10]/function
		outputMessageOriginId =
		// $-- /mn:mapping-element/field/field[10]/function
		objectFunctions.getHash(
		// $-- /mn:mapping-element/field/field[10]/function/function
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[10]/function/function/function[1]
						fPMFunctions.getContextStringParam("fileName"),
						// $-- /mn:mapping-element/field/field[10]/function/function/function[2]
						objectFunctions.stringValueOf(
						// $-- /mn:mapping-element/field/field[10]/function/function/function[2]/function
								timeFunctions.now())));
		outputMessage.setOriginId(outputMessageOriginId);

		//setting field originName

		// $-- /mn:mapping-element/field/field[11]
		java.lang.String outputMessageOriginName;

		// $-- /mn:mapping-element/field/field[11]/function
		outputMessageOriginName =
		// $-- /mn:mapping-element/field/field[11]/function
		fPMFunctions.getContextStringParam("fileName");
		outputMessage.setOriginName(outputMessageOriginName);

		//setting field originType

		// $-- /mn:mapping-element/field/field[12]
		java.lang.String outputMessageOriginType;

		// $-- /mn:mapping-element/field/field[12]/constant
		outputMessageOriginType = "File";
		outputMessage.setOriginType(outputMessageOriginType);

		//setting field previousEventMessageReference

		// $-- /mn:mapping-element/field/field[13]
		java.lang.String outputMessagePreviousEventMessageReference;

		// $-- /mn:mapping-element/field/field[13]/function
		outputMessagePreviousEventMessageReference =
		// $-- /mn:mapping-element/field/field[13]/function
		coacImportFunctions.getImportMessageField(input, "STD:0");
		outputMessage
				.setPreviousEventMessageReference(outputMessagePreviousEventMessageReference);

		//setting field security

		// $-- /mn:mapping-element/field/field[14]
		java.lang.String outputMessageSecurity;

		// $-- /mn:mapping-element/field/field[14]/function
		outputMessageSecurity =
		// $-- /mn:mapping-element/field/field[14]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field/field[14]/function/function
				objectFunctions.condition(
						// $-- /mn:mapping-element/field/field[14]/function/function/function[1]
						objectFunctions.or(
						// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[1]
								objectFunctions.equals(
								// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[1]/function[1]
										objectFunctions.trim(
										// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[1]/function[1]/function
												objectFunctions
														.stringValueOf(0)),
										// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[1]/function[2]
										objectFunctions.trim(
										// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[1]/function[2]/function
										coacImportFunctions
												.getImportMessageField(input,
														"HDR:2"))),
								// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[2]
								objectFunctions.not(
								// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[2]/function
								coacImportFunctions.isStandardSecurityType(
								// $-- /mn:mapping-element/field/field[14]/function/function/function[1]/function[2]/function/function
										coacImportFunctions
												.getImportMessageField(input,
														"STD:4")))),
						// $-- /mn:mapping-element/field/field[14]/function/function/function[2]
						coacImportFunctions.getImportMessageField(input,
								"HDR:1"),
						// $-- /mn:mapping-element/field/field[14]/function/function/function[3]
						coacImportFunctions.getImportMessageField(input,
								"STD:5")));
		outputMessage.setSecurity(outputMessageSecurity);

		//setting field securityName

		// $-- /mn:mapping-element/field/field[15]
		java.lang.String outputMessageSecurityName;

		// $-- /mn:mapping-element/field/field[15]/function
		outputMessageSecurityName =
		// $-- /mn:mapping-element/field/field[15]/function
		coacImportFunctions.getImportMessageField(input, "STD:3");
		outputMessage.setSecurityName(outputMessageSecurityName);

		//setting field securityType

		// $-- /mn:mapping-element/field/field[16]
		java.lang.String outputMessageSecurityType;

		// $-- /mn:mapping-element/field/field[16]/function
		outputMessageSecurityType =
		// $-- /mn:mapping-element/field/field[16]/function
		objectFunctions.stringValueOf(
		// $-- /mn:mapping-element/field/field[16]/function/function
				objectFunctions.condition(
						// $-- /mn:mapping-element/field/field[16]/function/function/function[1]
						objectFunctions.or(
						// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[1]
								objectFunctions.equals(
								// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[1]/function[1]
										objectFunctions.trim(
										// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[1]/function[1]/function
												objectFunctions
														.stringValueOf(0)),
										// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[1]/function[2]
										objectFunctions.trim(
										// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[1]/function[2]/function
										coacImportFunctions
												.getImportMessageField(input,
														"HDR:2"))),
								// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[2]
								objectFunctions.not(
								// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[2]/function
								coacImportFunctions.isStandardSecurityType(
								// $-- /mn:mapping-element/field/field[16]/function/function/function[1]/function[2]/function/function
										coacImportFunctions
												.getImportMessageField(input,
														"STD:4")))),
						// $-- /mn:mapping-element/field/field[16]/function/function/function[2]
						stringFunctions.concat("IPRVID",
						// $-- /mn:mapping-element/field/field[16]/function/function/function[2]/function
								coacImportFunctions.getSecurityProviderIdField(
										"HibernateResource", "BLOOMBERG")),
						// $-- /mn:mapping-element/field/field[16]/function/function/function[3]
						coacImportFunctions.getImportMessageField(input,
								"STD:4")));
		outputMessage.setSecurityType(outputMessageSecurityType);

		//setting field sender

		// $-- /mn:mapping-element/field/field[17]
		java.lang.String outputMessageSender;

		// $-- /mn:mapping-element/field/field[17]/function
		outputMessageSender =
		// $-- /mn:mapping-element/field/field[17]/function
		coacImportFunctions
				.getMessageFormatId(
				// $-- /mn:mapping-element/field/field[17]/function/function
				coacImportFunctions
						.getMessageType(
								"HibernateResource",
								com.lynxspa.sdm.dictionaries.formats.CAFormat.BLOOMBERG,
								in_messageType(input)));
		outputMessage.setSender(outputMessageSender);

		//setting field senderTimestamp

		// $-- /mn:mapping-element/field/field[18]
		java.util.Date outputMessageSenderTimestamp;

		// $-- /mn:mapping-element/field/field[18]/function
		outputMessageSenderTimestamp =
		// $-- /mn:mapping-element/field/field[18]/function
		objectFunctions.parseSecureLocalizedDate(
				// $-- /mn:mapping-element/field/field[18]/function/function
				coacImportFunctions.getImportMessageField(input,
						"MSG:STARTTIMESTAMP"), "EEE MMM dd HH:mm:ss zzz yyyy",
				"en");
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