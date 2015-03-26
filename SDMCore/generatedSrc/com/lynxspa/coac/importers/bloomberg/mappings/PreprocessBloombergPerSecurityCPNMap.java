// $-- src/com/lynxspa/coac/importers/bloomberg/mappings/preprocessBloombergPerSecurityCPN.fpmmap

package com.lynxspa.coac.importers.bloomberg.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/bloomberg/mappings/preprocessBloombergPerSecurityCPN.fpmmap")
public class PreprocessBloombergPerSecurityCPNMap
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
		// $-- /mn:mapping-element/field/field[1]/function/function[1]
				coacImportFunctions.getImportMessageField(input,
						"BDY:CPN:ANNOUNCE_DT"),
				// $-- /mn:mapping-element/field/field[1]/function/function[2]
				coacImportFunctions.getImportMessageField(input,
						"MSG:DATEFORMAT"));
		outputMessage.setAnnounceDate(outputMessageAnnounceDate);

		//setting field effectiveDate

		// $-- /mn:mapping-element/field/field[2]
		java.util.Date outputMessageEffectiveDate;

		// $-- /mn:mapping-element/field/field[2]/function
		outputMessageEffectiveDate =
		// $-- /mn:mapping-element/field/field[2]/function
		objectFunctions.parseSecureDate(
		// $-- /mn:mapping-element/field/field[2]/function/function[1]
				coacImportFunctions.getImportMessageField(input,
						"BDY:CPN:NXT_CPN_DT"),
				// $-- /mn:mapping-element/field/field[2]/function/function[2]
				coacImportFunctions.getImportMessageField(input,
						"MSG:DATEFORMAT"));
		outputMessage.setEffectiveDate(outputMessageEffectiveDate);

		//setting field eventMessageReference

		// $-- /mn:mapping-element/field/field[3]
		java.lang.String outputMessageEventMessageReference;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageEventMessageReference =
		// $-- /mn:mapping-element/field/field[3]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[3]/function/function[1]
				coacImportFunctions.getImportMessageField(input, "STD:0"),
				// $-- /mn:mapping-element/field/field[3]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[3]/function/function[2]/function[1]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[3]/function/function[2]/function[1]/function[1]
								coacImportFunctions.getImportMessageField(
										input, "HDR:0"),
								// $-- /mn:mapping-element/field/field[3]/function/function[2]/function[1]/function[2]
								coacImportFunctions.getImportMessageField(
										input, "BDY:CPN:NXT_CPN_DT")),
						// $-- /mn:mapping-element/field/field[3]/function/function[2]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"BDY:CPN:CPN")));
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
				coacImportFunctions.getImportMessageField(input, "STD:0"),
				// $-- /mn:mapping-element/field/field[4]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[4]/function/function[2]/function[1]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[4]/function/function[2]/function[1]/function[1]
								coacImportFunctions.getImportMessageField(
										input, "HDR:0"),
								// $-- /mn:mapping-element/field/field[4]/function/function[2]/function[1]/function[2]
								coacImportFunctions.getImportMessageField(
										input, "BDY:CPN:NXT_CPN_DT")),
						// $-- /mn:mapping-element/field/field[4]/function/function[2]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"BDY:CPN:CPN")));
		outputMessage.setEventReference(outputMessageEventReference);

		//setting field messageId

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageMessageId;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageMessageId =
		// $-- /mn:mapping-element/field/field[5]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[5]/function/function[1]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"STD:0"),
						// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[2]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[2]/function[1]
								stringFunctions.concat(
								// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[2]/function[1]/function[1]
										coacImportFunctions
												.getImportMessageField(input,
														"HDR:0"),
										// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[2]/function[1]/function[2]
										coacImportFunctions
												.getImportMessageField(input,
														"BDY:CPN:NXT_CPN_DT")),
								// $-- /mn:mapping-element/field/field[5]/function/function[1]/function[2]/function[2]
								coacImportFunctions.getImportMessageField(
										input, "BDY:CPN:CPN"))),
				// $-- /mn:mapping-element/field/field[5]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[5]/function/function[2]/function[1]
						coacImportFunctions.getImportMessageField(input,
								"BDY:CPN:PAR_AMT"),
						// $-- /mn:mapping-element/field/field[5]/function/function[2]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"BDY:CPN:MATURITY")));
		outputMessage.setMessageId(outputMessageMessageId);

		//setting field previousEventMessageReference

		// $-- /mn:mapping-element/field/field[6]
		java.lang.String outputMessagePreviousEventMessageReference;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessagePreviousEventMessageReference =
		// $-- /mn:mapping-element/field/field[6]/function
		stringFunctions.concat(
		// $-- /mn:mapping-element/field/field[6]/function/function[1]
				coacImportFunctions.getImportMessageField(input, "STD:0"),
				// $-- /mn:mapping-element/field/field[6]/function/function[2]
				stringFunctions.concat(
				// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[1]
						stringFunctions.concat(
						// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[1]/function[1]
								coacImportFunctions.getImportMessageField(
										input, "HDR:0"),
								// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[1]/function[2]
								coacImportFunctions.getImportMessageField(
										input, "BDY:CPN:NXT_CPN_DT")),
						// $-- /mn:mapping-element/field/field[6]/function/function[2]/function[2]
						coacImportFunctions.getImportMessageField(input,
								"BDY:CPN:CPN")));
		outputMessage
				.setPreviousEventMessageReference(outputMessagePreviousEventMessageReference);

		//setting field securityName

		// $-- /mn:mapping-element/field/field[7]
		java.lang.String outputMessageSecurityName;

		// $-- /mn:mapping-element/field/field[7]/function
		outputMessageSecurityName =
		// $-- /mn:mapping-element/field/field[7]/function
		coacImportFunctions
				.getImportMessageField(input, "BDY:CPN:SECURITY_DES");
		outputMessage.setSecurityName(outputMessageSecurityName);
		output.setMessage(outputMessage);

		return output;
	}

}