// $-- src/com/lynxspa/coac/importers/securities/bloomberg/mappings/BloombergSecurityBeanToSecurityBean.fpmmap

package com.lynxspa.coac.importers.securities.bloomberg.mappings;

import java.util.*;
import com.lynxit.utils.beans.IteratorUtils;

import com.lynxit.fpm.admin.config.annotations.GeneratedType;
import com.lynxit.fpm.admin.config.ConfigurationException;
import com.lynxit.fpm.nodes.internal.MappingNode;

@GeneratedType(definitionFile = "src/com/lynxspa/coac/importers/securities/bloomberg/mappings/BloombergSecurityBeanToSecurityBean.fpmmap")
public class BloombergSecurityBeanToSecurityBeanMap
		extends
		MappingNode<com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean, com.lynxspa.sdm.importers.securities.beans.SecurityBean> {

	// function libraries declarations
	com.lynxit.fpm.functionlibraries.StringFunctions stringFunctions;

	@Override
	public void init() throws Exception {
		super.init();

		// initialize libs
		stringFunctions = new com.lynxit.fpm.functionlibraries.StringFunctions();
		stringFunctions.setNode(this);
	}

	public com.lynxspa.sdm.importers.securities.beans.SecurityBean perform(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input)
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
		outputAction = com.lynxspa.sdm.importers.securities.beans.SecurityBean.ActionsToDo.INSERT_OR_UPDATE;
		output.setAction(outputAction);

		//setting field format

		// $-- /mn:mapping-element/field[2]
		java.lang.String outputFormat;

		// $-- /mn:mapping-element/field[2]/constant
		outputFormat = "BLOOMBERG";
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

		//setting field country

		// $-- /mn:mapping-element/field[5]/field[1]
		java.lang.String outputSecurityCountry;

		// $-- /mn:mapping-element/field[5]/field[1]/inputField
		outputSecurityCountry = in_country(input);
		outputSecurity.setCountry(outputSecurityCountry);

		//setting field currency

		// $-- /mn:mapping-element/field[5]/field[2]
		java.lang.String outputSecurityCurrency;

		// $-- /mn:mapping-element/field[5]/field[2]/inputField
		outputSecurityCurrency = in_crncy(input);
		outputSecurity.setCurrency(outputSecurityCurrency);

		//setting field cusip

		// $-- /mn:mapping-element/field[5]/field[3]
		java.lang.String outputSecurityCusip;

		// $-- /mn:mapping-element/field[5]/field[3]/inputField
		outputSecurityCusip = in_idCusip(input);
		outputSecurity.setCusip(outputSecurityCusip);

		//setting field indGroup

		// $-- /mn:mapping-element/field[5]/field[4]
		java.lang.String outputSecurityIndGroup;

		// $-- /mn:mapping-element/field[5]/field[4]/inputField
		outputSecurityIndGroup = in_industryGroup(input);
		outputSecurity.setIndGroup(outputSecurityIndGroup);

		//setting field indSector

		// $-- /mn:mapping-element/field[5]/field[5]
		java.lang.String outputSecurityIndSector;

		// $-- /mn:mapping-element/field[5]/field[5]/inputField
		outputSecurityIndSector = in_industrySector(input);
		outputSecurity.setIndSector(outputSecurityIndSector);

		//setting field indSubGroup

		// $-- /mn:mapping-element/field[5]/field[6]
		java.lang.String outputSecurityIndSubGroup;

		// $-- /mn:mapping-element/field[5]/field[6]/inputField
		outputSecurityIndSubGroup = in_industrySubGroup(input);
		outputSecurity.setIndSubGroup(outputSecurityIndSubGroup);

		//setting field infoProviderId1

		// $-- /mn:mapping-element/field[5]/field[7]
		java.lang.String outputSecurityInfoProviderId1;

		// $-- /mn:mapping-element/field[5]/field[7]/inputField
		outputSecurityInfoProviderId1 = in_bloombergId(input);
		outputSecurity.setInfoProviderId1(outputSecurityInfoProviderId1);

		//setting field isin

		// $-- /mn:mapping-element/field[5]/field[8]
		java.lang.String outputSecurityIsin;

		// $-- /mn:mapping-element/field[5]/field[8]/inputField
		outputSecurityIsin = in_idIsin(input);
		outputSecurity.setIsin(outputSecurityIsin);

		//setting field market

		// $-- /mn:mapping-element/field[5]/field[9]
		com.lynxspa.entities.securities.markets.SPMarket outputSecurityMarket;
		if (outputSecurity.getMarket() != null) {
			outputSecurityMarket = outputSecurity.getMarket();
		} else
			outputSecurityMarket = new com.lynxspa.entities.securities.markets.SPMarket();

		//setting field mic

		// $-- /mn:mapping-element/field[5]/field[9]/field[1]
		java.lang.String outputSecurityMarketMic;

		// $-- /mn:mapping-element/field[5]/field[9]/field[1]/inputField
		outputSecurityMarketMic = in_idPrimaryExchange(input);
		outputSecurityMarket.setMic(outputSecurityMarketMic);

		//setting field name

		// $-- /mn:mapping-element/field[5]/field[9]/field[2]
		java.lang.String outputSecurityMarketName;

		// $-- /mn:mapping-element/field[5]/field[9]/field[2]/inputField
		outputSecurityMarketName = in_namePrimaryExchange(input);
		outputSecurityMarket.setName(outputSecurityMarketName);

		//setting field ticker

		// $-- /mn:mapping-element/field[5]/field[9]/field[3]
		java.lang.String outputSecurityMarketTicker;

		// $-- /mn:mapping-element/field[5]/field[9]/field[3]/inputField
		outputSecurityMarketTicker = in_idPrimaryExchange(input);
		outputSecurityMarket.setTicker(outputSecurityMarketTicker);
		outputSecurity.setMarket(outputSecurityMarket);

		//setting field name

		// $-- /mn:mapping-element/field[5]/field[10]
		java.lang.String outputSecurityName;

		// $-- /mn:mapping-element/field[5]/field[10]/inputField
		outputSecurityName = in_name(input);
		outputSecurity.setName(outputSecurityName);

		//setting field relIndex

		// $-- /mn:mapping-element/field[5]/field[11]
		java.lang.String outputSecurityRelIndex;

		// $-- /mn:mapping-element/field[5]/field[11]/inputField
		outputSecurityRelIndex = in_relIndex(input);
		outputSecurity.setRelIndex(outputSecurityRelIndex);

		//setting field secFinancialAssets

		// $-- /mn:mapping-element/field[5]/field[12]
		com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssets outputSecuritySecFinancialAssets;

		// $-- /mn:mapping-element/field[5]/field[12]/inputField
		outputSecuritySecFinancialAssets = in_financialAsset(input);
		outputSecurity.setSecFinancialAssets(outputSecuritySecFinancialAssets);

		//setting field securityDetail

		// $-- /mn:mapping-element/field[5]/field[13]
		com.lynxspa.entities.securities.details.SPSecurityDetail outputSecuritySecurityDetail;
		if (outputSecurity.getSecurityDetail() != null) {
			outputSecuritySecurityDetail = outputSecurity.getSecurityDetail();
		} else
			outputSecuritySecurityDetail = new com.lynxspa.entities.securities.details.SPSecurityDetail();

		//setting field dynamicTable

		// $-- /mn:mapping-element/field[5]/field[13]/field
		java.util.HashMap<java.lang.String, java.lang.String> outputSecuritySecurityDetailDynamicTable;

		// $-- /mn:mapping-element/field[5]/field[13]/field/inputField
		outputSecuritySecurityDetailDynamicTable = in_optionalValues(input);
		outputSecuritySecurityDetail
				.setDynamicTable(outputSecuritySecurityDetailDynamicTable);
		outputSecurity.setSecurityDetail(outputSecuritySecurityDetail);

		//setting field securityType

		// $-- /mn:mapping-element/field[5]/field[14]
		java.lang.String outputSecuritySecurityType;

		// $-- /mn:mapping-element/field[5]/field[14]/inputField
		outputSecuritySecurityType = in_securityType(input);
		outputSecurity.setSecurityType(outputSecuritySecurityType);

		//setting field sedol

		// $-- /mn:mapping-element/field[5]/field[15]
		java.lang.String outputSecuritySedol;

		// $-- /mn:mapping-element/field[5]/field[15]/inputField
		outputSecuritySedol = in_idSedol1(input);
		outputSecurity.setSedol(outputSecuritySedol);

		//setting field ticker

		// $-- /mn:mapping-element/field[5]/field[16]
		java.lang.String outputSecurityTicker;

		// $-- /mn:mapping-element/field[5]/field[16]/function
		outputSecurityTicker =
		// $-- /mn:mapping-element/field[5]/field[16]/function
		stringFunctions.concat(in_ticker(input), in_exchName(input));
		outputSecurity.setTicker(outputSecurityTicker);
		output.setSecurity(outputSecurity);

		return output;
	}

	private java.lang.String in_securityType(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getSecurityType();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_industrySector(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIndustrySector();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_idIsin(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIdIsin();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_ticker(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getTicker();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_relIndex(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getRelIndex();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.Integer in_position(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getPosition();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_industrySubGroup(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIndustrySubGroup();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_idCusip(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIdCusip();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssets in_financialAsset(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getFinancialAsset();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_exchName(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getExchName();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_idPrimaryExchange(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIdPrimaryExchange();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_idSedol1(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIdSedol1();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_namePrimaryExchange(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getNamePrimaryExchange();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_file(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getFile();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.util.HashMap in_optionalValues(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getOptionalValues();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_crncy(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getCrncy();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_country(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getCountry();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_name(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getName();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_bloombergId(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getBloombergId();
		} catch (NullPointerException exc) {
			return null;
		}
	}

	private java.lang.String in_industryGroup(
			com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean input) {
		try {
			return input.getIndustryGroup();
		} catch (NullPointerException exc) {
			return null;
		}
	}

}