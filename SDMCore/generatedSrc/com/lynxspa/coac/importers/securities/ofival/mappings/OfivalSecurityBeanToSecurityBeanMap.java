// $-- src/com/lynxspa/coac/importers/securities/ofival/mappings/OfivalSecurityBeanToSecurityBean.fpmmap

package com.lynxspa.coac.importers.securities.ofival.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/ofival/mappings/OfivalSecurityBeanToSecurityBean.fpmmap")
public class OfivalSecurityBeanToSecurityBeanMap
		extends
		MappingNode<com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean, com.lynxspa.sdm.importers.securities.beans.SecurityBean> {

	// function libraries declarations

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
	}

	public com.lynxspa.sdm.importers.securities.beans.SecurityBean perform(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input)
			throws Exception {
		if (input == null)
			return null;

		/*
		 * declare the output message
		 */
		// $-- /mn:mapping-element/@outputBean
		com.lynxspa.sdm.importers.securities.beans.SecurityBean output;

		/*
		 * transformation
		 */

		output = new com.lynxspa.sdm.importers.securities.beans.SecurityBean();

		//setting field action

		// $-- /mn:mapping-element/field[1]
		com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo outputAction;

		// $-- /mn:mapping-element/field[1]/constant
		outputAction = com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.UPDATE;
		output.setAction(outputAction);

		//setting field format

		// $-- /mn:mapping-element/field[2]
		java.lang.String outputFormat;

		// $-- /mn:mapping-element/field[2]/constant
		outputFormat = "OFIVAL";
		output.setFormat(outputFormat);

		//setting field originName

		// $-- /mn:mapping-element/field[3]
		java.lang.String outputOriginName;

		// $-- /mn:mapping-element/field[3]/inputField
		outputOriginName = in_file(input);
		output.setOriginName(outputOriginName);

		//setting field originPosition

		// $-- /mn:mapping-element/field[4]
		java.lang.Integer outputOriginPosition;

		// $-- /mn:mapping-element/field[4]/inputField
		outputOriginPosition = in_position(input);
		output.setOriginPosition(outputOriginPosition);

		//setting field security

		// $-- /mn:mapping-element/field[5]
		com.lynxspa.entities.securities.SPSecurity outputSecurity;
		if (output.getSecurity() != null) {
			outputSecurity = output.getSecurity();
		} else
			outputSecurity = new com.lynxspa.entities.securities.SPSecurity();

		//setting field infoProviderId1

		// $-- /mn:mapping-element/field[5]/field[1]
		java.lang.String outputSecurityInfoProviderId1;

		// $-- /mn:mapping-element/field[5]/field[1]/inputField
		outputSecurityInfoProviderId1 = in_codigoEmisora(input);
		outputSecurity.setInfoProviderId1(outputSecurityInfoProviderId1);

		//setting field isin

		// $-- /mn:mapping-element/field[5]/field[2]
		java.lang.String outputSecurityIsin;

		// $-- /mn:mapping-element/field[5]/field[2]/inputField
		outputSecurityIsin = in_isin(input);
		outputSecurity.setIsin(outputSecurityIsin);

		//setting field name

		// $-- /mn:mapping-element/field[5]/field[3]
		java.lang.String outputSecurityName;

		// $-- /mn:mapping-element/field[5]/field[3]/inputField
		outputSecurityName = in_name(input);
		outputSecurity.setName(outputSecurityName);
		output.setSecurity(outputSecurity);

		return output;
	}

	private java.lang.String in_name(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input) {
		try {
			return input.getName();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_file(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input) {
		try {
			return input.getFile();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.Integer in_position(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input) {
		try {
			return input.getPosition();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_codigoEmisora(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input) {
		try {
			return input.getCodigoEmisora();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_isin(
			com.lynxspa.coac.importers.securities.ofival.beans.OfivalSecurityBean input) {
		try {
			return input.getIsin();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}