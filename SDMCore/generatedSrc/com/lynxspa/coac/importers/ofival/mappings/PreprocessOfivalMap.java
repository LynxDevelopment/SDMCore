// $-- src/com/lynxspa/coac/importers/ofival/mappings/preprocessOfival.fpmmap

package com.lynxspa.coac.importers.ofival.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/ofival/mappings/preprocessOfival.fpmmap")
public class PreprocessOfivalMap
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

		//setting field eventType

		// $-- /mn:mapping-element/field/field[1]
		java.lang.String outputMessageEventType;

		// $-- /mn:mapping-element/field/field[1]/function
		outputMessageEventType =
		// $-- /mn:mapping-element/field/field[1]/function
		coacImportFunctions.getImportMessageField(input, ":9-16:");
		outputMessage.setEventType(outputMessageEventType);

		//setting field messageId

		// $-- /mn:mapping-element/field/field[2]
		java.lang.String outputMessageMessageId;

		// $-- /mn:mapping-element/field/field[2]/function
		outputMessageMessageId =
		// $-- /mn:mapping-element/field/field[2]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[2]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[2]/function/function[1]/function[1]
						coacImportFunctions.getImportMessageField(input,
								":1-8:"),
						// $-- /mn:mapping-element/field/field[2]/function/function[1]/function[2]
						coacImportFunctions.getImportMessageField(input,
								":9-16:")),
				// $-- /mn:mapping-element/field/field[2]/function/function[2]
				coacImportFunctions.getImportMessageField(input, ":17-24:"));
		outputMessage.setMessageId(outputMessageMessageId);

		//setting field messageType

		// $-- /mn:mapping-element/field/field[3]
		com.lynxspa.sdm.entities.events.messages.formats.CAMessageType outputMessageMessageType;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageMessageType =
		// $-- /mn:mapping-element/field/field[3]/function
		coacImportFunctions.getMessageType("HibernateResource",
				com.lynxspa.sdm.dictionaries.formats.CAFormat.OFIVAL,
				in_messageType(input));
		outputMessage.setMessageType(outputMessageMessageType);

		//setting field operationStatus

		// $-- /mn:mapping-element/field/field[4]
		com.lynxspa.entities.application.flow.operations.OperationStatus outputMessageOperationStatus;
		if (outputMessage.getOperationStatus() != null) {
			outputMessageOperationStatus = outputMessage.getOperationStatus();
		} else
			outputMessageOperationStatus = new com.lynxspa.entities.application.flow.operations.OperationStatus();

		//setting field state

		// $-- /mn:mapping-element/field/field[4]/field
		com.lynxspa.entities.application.flow.State outputMessageOperationStatusState;

		// $-- /mn:mapping-element/field/field[4]/field/function
		outputMessageOperationStatusState =
		// $-- /mn:mapping-element/field/field[4]/field/function
		coacFunctions
				.getEventMessageInitialState(
						"HibernateResource",
						com.lynxspa.sdm.dictionaries.flows.states.CAStatesEVENTMESSAGEFlow.PRSD);
		outputMessageOperationStatus
				.setState(outputMessageOperationStatusState);
		outputMessage.setOperationStatus(outputMessageOperationStatus);

		//setting field originId

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageOriginId;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageOriginId =
		// $-- /mn:mapping-element/field/field[5]/function
		objectFunctions.getHash(
		// $-- /mn:mapping-element/field/field[5]/function/function
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[5]/function/function/function[1]
						fPMFunctions.getContextStringParam("fileName"),
						// $-- /mn:mapping-element/field/field[5]/function/function/function[2]
						objectFunctions.stringValueOf(
						// $-- /mn:mapping-element/field/field[5]/function/function/function[2]/function
								timeFunctions.now())));
		outputMessage.setOriginId(outputMessageOriginId);

		//setting field originName

		// $-- /mn:mapping-element/field/field[6]
		java.lang.String outputMessageOriginName;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessageOriginName =
		// $-- /mn:mapping-element/field/field[6]/function
		fPMFunctions.getContextStringParam("fileName");
		outputMessage.setOriginName(outputMessageOriginName);

		//setting field originType

		// $-- /mn:mapping-element/field/field[7]
		java.lang.String outputMessageOriginType;

		// $-- /mn:mapping-element/field/field[7]/constant
		outputMessageOriginType = "File";
		outputMessage.setOriginType(outputMessageOriginType);

		//setting field sender

		// $-- /mn:mapping-element/field/field[8]
		java.lang.String outputMessageSender;

		// $-- /mn:mapping-element/field/field[8]/function
		outputMessageSender =
		// $-- /mn:mapping-element/field/field[8]/function
		coacImportFunctions.getMessageFormatId(
		// $-- /mn:mapping-element/field/field[8]/function/function
				coacImportFunctions.getMessageType("HibernateResource",
						com.lynxspa.sdm.dictionaries.formats.CAFormat.OFIVAL,
						in_messageType(input)));
		outputMessage.setSender(outputMessageSender);

		//setting field senderTimestamp

		// $-- /mn:mapping-element/field/field[9]
		java.util.Date outputMessageSenderTimestamp;

		// $-- /mn:mapping-element/field/field[9]/function
		outputMessageSenderTimestamp =
		// $-- /mn:mapping-element/field/field[9]/function
		objectFunctions
				.parseSecureDate(
						// $-- /mn:mapping-element/field/field[9]/function/function
						coacImportFunctions.getImportMessageField(input,
								":1029-1036:"), "yyyyMMdd");
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