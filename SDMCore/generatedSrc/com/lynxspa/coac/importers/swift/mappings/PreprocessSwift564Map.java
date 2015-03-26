// $-- src/com/lynxspa/coac/importers/swift/mappings/preprocessSwift564.fpmmap

package com.lynxspa.coac.importers.swift.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/swift/mappings/preprocessSwift564.fpmmap")
public class PreprocessSwift564Map
		extends
		MappingNode<com.lynxspa.coac.importers.EventMessageImportBean, com.lynxspa.coac.importers.EventMessageImportBean> {

	// function libraries declarations
	com.lynxspa.coac.importers.mappings.CoacImportFunctions coacImportFunctions;
	com.lynxspa.fpm.functions.ObjectFunctions objectFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		coacImportFunctions = new com.lynxspa.coac.importers.mappings.CoacImportFunctions();
		coacImportFunctions.setNode(this);
		objectFunctions = new com.lynxspa.fpm.functions.ObjectFunctions();
		objectFunctions.setNode(this);
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

		//setting field account

		// $-- /mn:mapping-element/field/field[1]
		java.lang.String outputMessageAccount;

		// $-- /mn:mapping-element/field/field[1]/function
		outputMessageAccount =
		// $-- /mn:mapping-element/field/field[1]/function
		coacImportFunctions.getImportMessageField(input,
				"4:16R:USECU(0):16R:ACCTINFO(0):97(A)::SAFE//");
		outputMessage.setAccount(outputMessageAccount);

		//setting field amount

		// $-- /mn:mapping-element/field/field[2]
		java.lang.Long outputMessageAmount;

		// $-- /mn:mapping-element/field/field[2]/function
		outputMessageAmount =
		// $-- /mn:mapping-element/field/field[2]/function
		objectFunctions.parseSecureLong(
		// $-- /mn:mapping-element/field/field[2]/function/function
				coacImportFunctions.getImportMessageField(input,
						"4:16R:USECU(0):16R:ACCTINFO(0):93(B)::SETT//"));
		outputMessage.setAmount(outputMessageAmount);

		//setting field announceDate

		// $-- /mn:mapping-element/field/field[3]
		java.util.Date outputMessageAnnounceDate;

		// $-- /mn:mapping-element/field/field[3]/function
		outputMessageAnnounceDate =
		// $-- /mn:mapping-element/field/field[3]/function
		objectFunctions.parseSecureDate(
		// $-- /mn:mapping-element/field/field[3]/function/function
				coacImportFunctions.getImportMessageField(input,
						"4:16R:CADETL(0):98(A)::ANOU//"), "yyyyMMdd");
		outputMessage.setAnnounceDate(outputMessageAnnounceDate);

		//setting field effectiveDate

		// $-- /mn:mapping-element/field/field[4]
		java.util.Date outputMessageEffectiveDate;

		// $-- /mn:mapping-element/field/field[4]/function
		outputMessageEffectiveDate =
		// $-- /mn:mapping-element/field/field[4]/function
		objectFunctions.parseSecureDate(
		// $-- /mn:mapping-element/field/field[4]/function/function
				coacImportFunctions.getImportMessageField(input,
						"4:16R:CADETL(0):98(A)::XDTE//"), "yyyyMMdd");
		outputMessage.setEffectiveDate(outputMessageEffectiveDate);

		//setting field eventMessageReference

		// $-- /mn:mapping-element/field/field[5]
		java.lang.String outputMessageEventMessageReference;

		// $-- /mn:mapping-element/field/field[5]/function
		outputMessageEventMessageReference =
		// $-- /mn:mapping-element/field/field[5]/function
		coacImportFunctions.getImportMessageField(input,
				"4:16R:GENL(0):20(C)::SEME//");
		outputMessage
				.setEventMessageReference(outputMessageEventMessageReference);

		//setting field eventReference

		// $-- /mn:mapping-element/field/field[6]
		java.lang.String outputMessageEventReference;

		// $-- /mn:mapping-element/field/field[6]/function
		outputMessageEventReference =
		// $-- /mn:mapping-element/field/field[6]/function
		coacImportFunctions.getImportMessageField(input,
				"4:16R:GENL(0):20(C)::CORP//");
		outputMessage.setEventReference(outputMessageEventReference);

		//setting field eventType

		// $-- /mn:mapping-element/field/field[7]
		java.lang.String outputMessageEventType;

		// $-- /mn:mapping-element/field/field[7]/function
		outputMessageEventType =
		// $-- /mn:mapping-element/field/field[7]/function
		coacImportFunctions.getImportMessageField(input,
				"4:16R:GENL(0):22(F)::CAEV//");
		outputMessage.setEventType(outputMessageEventType);

		//setting field extensionReference

		// $-- /mn:mapping-element/field/field[8]
		java.lang.String outputMessageExtensionReference;

		// $-- /mn:mapping-element/field/field[8]/function
		outputMessageExtensionReference =
		// $-- /mn:mapping-element/field/field[8]/function
		coacImportFunctions.getImportMessageField(input,
				"4:16R:GENL(0):20(C)::SEME//");
		outputMessage.setExtensionReference(outputMessageExtensionReference);

		//setting field operation

		// $-- /mn:mapping-element/field/field[9]
		java.lang.String outputMessageOperation;

		// $-- /mn:mapping-element/field/field[9]/function
		outputMessageOperation =
		// $-- /mn:mapping-element/field/field[9]/function
		coacImportFunctions
				.getImportMessageField(input, "4:16R:GENL(0):23(G):");
		outputMessage.setOperation(outputMessageOperation);

		//setting field originType

		// $-- /mn:mapping-element/field/field[10]
		java.lang.String outputMessageOriginType;

		// $-- /mn:mapping-element/field/field[10]/constant
		outputMessageOriginType = "File";
		outputMessage.setOriginType(outputMessageOriginType);

		//setting field security

		// $-- /mn:mapping-element/field/field[11]
		java.lang.String outputMessageSecurity;

		// $-- /mn:mapping-element/field/field[11]/function
		outputMessageSecurity =
		// $-- /mn:mapping-element/field/field[11]/function
		objectFunctions.secureSubString(
		// $-- /mn:mapping-element/field/field[11]/function/function[1]
				coacImportFunctions.getImportMessageField(input,
						"4:16R:USECU(0):35(B):"),
				// $-- /mn:mapping-element/field/field[11]/function/function[2]
				objectFunctions.parseInt("5"),
				// $-- /mn:mapping-element/field/field[11]/function/function[3]
				objectFunctions.parseInt("17"));
		outputMessage.setSecurity(outputMessageSecurity);

		//setting field securityName

		// $-- /mn:mapping-element/field/field[12]
		java.lang.String outputMessageSecurityName;

		// $-- /mn:mapping-element/field/field[12]/function
		outputMessageSecurityName =
		// $-- /mn:mapping-element/field/field[12]/function
		objectFunctions.secureSubString(
		// $-- /mn:mapping-element/field/field[12]/function/function[1]
				coacImportFunctions.getImportMessageField(input,
						"4:16R:USECU(0):35(B):"),
				// $-- /mn:mapping-element/field/field[12]/function/function[2]
				objectFunctions.parseInt("17"),
				// $-- /mn:mapping-element/field/field[12]/function/function[3]
				objectFunctions.parseInt("-1"));
		outputMessage.setSecurityName(outputMessageSecurityName);

		//setting field securityType

		// $-- /mn:mapping-element/field/field[13]
		java.lang.String outputMessageSecurityType;

		// $-- /mn:mapping-element/field/field[13]/function
		outputMessageSecurityType =
		// $-- /mn:mapping-element/field/field[13]/function
		objectFunctions.secureSubString(
		// $-- /mn:mapping-element/field/field[13]/function/function[1]
				coacImportFunctions.getImportMessageField(input,
						"4:16R:USECU(0):35(B):"),
				// $-- /mn:mapping-element/field/field[13]/function/function[2]
				objectFunctions.parseInt("0"),
				// $-- /mn:mapping-element/field/field[13]/function/function[3]
				objectFunctions.parseInt("4"));
		outputMessage.setSecurityType(outputMessageSecurityType);
		output.setMessage(outputMessage);

		return output;
	}

}