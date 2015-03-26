// $-- src/com/lynxspa/coac/importers/ofival/mappings/preprocessOfivalRFJ.fpmmap

package com.lynxspa.coac.importers.ofival.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/ofival/mappings/preprocessOfivalRFJ.fpmmap")
public class PreprocessOfivalRFJMap
		extends
		MappingNode<com.lynxspa.coac.importers.EventMessageImportBean, com.lynxspa.coac.importers.EventMessageImportBean> {

	// function libraries declarations
	com.lynxspa.coac.importers.mappings.CoacImportFunctions coacImportFunctions;
	com.lynxspa.fpm.functions.ObjectFunctions objectFunctions;
	com.lynxit.fpm.functionlibraries.StringFunctions stringFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacImportFunctions = new com.lynxspa.coac.importers.mappings.CoacImportFunctions();
		coacImportFunctions.setNode(this);
		objectFunctions = new com.lynxspa.fpm.functions.ObjectFunctions();
		objectFunctions.setNode(this);
		stringFunctions = new com.lynxit.fpm.functionlibraries.StringFunctions();
		stringFunctions.setNode(this);
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
						.getImportMessageField(input, "RFJ:183-190:"),
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
						.getImportMessageField(input, "RFJ:339-346:"),
				"yyyyMMdd");
		outputMessage.setEffectiveDate(outputMessageEffectiveDate);

		//setting field eventMessageReference

		// $-- /mn:mapping-element/field/field[3]
		java.lang.String outputMessageEventMessageReference;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageEventMessageReference =
		// $-- /mn:mapping-element/field/field[3]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[3]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[3]/function/function[1]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:183-190:"),
						// $-- /mn:mapping-element/field/field[3]/function/function[1]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:35-46:")),
				// $-- /mn:mapping-element/field/field[3]/function/function[2]
				coacImportFunctions.getImportMessageField(input, ":9-16:"));
		outputMessage
				.setEventMessageReference(outputMessageEventMessageReference);

		//setting field eventReference

		// $-- /mn:mapping-element/field/field[4]
		java.lang.String outputMessageEventReference;

		// $-- /mn:mapping-element/field/field[4]/function
		outputMessageEventReference =
		// $-- /mn:mapping-element/field/field[4]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[4]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[4]/function/function[1]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:183-190:"),
						// $-- /mn:mapping-element/field/field[4]/function/function[1]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:35-46:")),
				// $-- /mn:mapping-element/field/field[4]/function/function[2]
				coacImportFunctions.getImportMessageField(input, ":9-16:"));
		outputMessage.setEventReference(outputMessageEventReference);

		//setting field operation

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageOperation;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageOperation =
		// $-- /mn:mapping-element/field/field[5]/function
		coacImportFunctions.getImportMessageField(input, "RFJ:34-34:");
		outputMessage.setOperation(outputMessageOperation);

		//setting field previousEventMessageReference

		// $-- /mn:mapping-element/field/field[6]
		java.lang.String outputMessagePreviousEventMessageReference;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessagePreviousEventMessageReference =
		// $-- /mn:mapping-element/field/field[6]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[6]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:183-190:"),
						// $-- /mn:mapping-element/field/field[6]/function/function[1]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"RFJ:35-46:")),
				// $-- /mn:mapping-element/field/field[6]/function/function[2]
				coacImportFunctions.getImportMessageField(input, ":9-16:"));
		outputMessage
				.setPreviousEventMessageReference(outputMessagePreviousEventMessageReference);

		//setting field security

		// $-- /mn:mapping-element/field/field[7]
		java.lang.String outputMessageSecurity;

		// $-- /mn:mapping-element/field/field[7]/function
		outputMessageSecurity =
		// $-- /mn:mapping-element/field/field[7]/function
		coacImportFunctions.getImportMessageField(input, "RFJ:35-46:");
		outputMessage.setSecurity(outputMessageSecurity);

		//setting field securityName

		// $-- /mn:mapping-element/field/field[8]
		java.lang.String outputMessageSecurityName;

		// $-- /mn:mapping-element/field/field[8]/function
		outputMessageSecurityName =
		// $-- /mn:mapping-element/field/field[8]/function
		coacImportFunctions.getImportMessageField(input, "RFJ:47-58:");
		outputMessage.setSecurityName(outputMessageSecurityName);

		//setting field securityType

		// $-- /mn:mapping-element/field/field[9]
		java.lang.String outputMessageSecurityType;

		// $-- /mn:mapping-element/field/field[9]/constant
		outputMessageSecurityType = "ISIN";
		outputMessage.setSecurityType(outputMessageSecurityType);
		output.setMessage(outputMessage);

		return output;
	}

}