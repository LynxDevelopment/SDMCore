package com.lynxspa.coac.importers.securities.bloomberg.beans;

import java.util.HashMap;

import com.lynxspa.sdm.dictionaries.securities.securityfinancialassets.CASecurityFinancialAssets;
import com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssets;

/**
 * This is a basic rappresentation for data that bloomber give in a .out file for securities
 * 
 * 
 * # Security Description
 * TICKER
 * EXCH_CODE
 * NAME
 * COUNTRY
 * CRNCY
 * SECURITY_TYP
 * PAR_AMT
 * EQY_PRIM_EXCH
 * EQY_PRIM_EXCH_SHRT
 * # Industry Classification
 * EQY_SIC_CODE
 * EQY_SIC_NAME
 * INDUSTRY_GROUP
 * INDUSTRY_SUBGROUP
 * INDUSTRY_SECTOR
 * # Identifiers
 * ID_SEDOL1
 * ID_WERTPAPIER
 * ID_ISIN
 * ID_DUTCH
 * ID_VALOREN
 * ID_FRENCH
 * ID_BELGIUM
 * ID_BB_COMPANY
 * ID_BB_SECURITY
 * ID_CUSIP
 * ID_COMMON
 * # ADRs
 * ADR_UNDL_TICKER
 * ADR_SH_PER_ADR
 * # Dividend Information
 * DVD_CRNCY
 * EQY_DVD_SH_12M_NET
 * EQY_DVD_SH_12M
 * EQY_DVD_SH_LAST
 * EQY_LAST_DPS_GROSS
 * EQY_DVD_PCT_FRANKED
 * EQY_DVD_TYP_LAST
 * EQY_DVD_FREQ
 * DVD_PAY_DT
 * DVD_RECORD_DT
 * DVD_DECLARED_DT
 * EQY_SPLIT_DT
 * EQY_SPLIT_RATIO
 * DVD_EX_DT
 * EQY_DVD_EX_FLAG
 * INDUSTRY_SUBGROUP_NUM
 * CNTRY_ISSUE_ISO
 * MARKET_STATUS
 * ID_BB_PARENT_CO
 * ADR_UNDL_CMPID
 * ADR_UNDL_SECID
 * REL_INDEX
 * PX_TRADE_LOT_SIZE
 * PARENT_COMP_TICKER
 * PARENT_COMP_NAME
 * ID_LOCAL
 * LONG_COMP_NAME
 * PARENT_INDUSTRY_GROUP
 * PARENT_INDUSTRY_SUBGROUP
 * PARENT_INDUSTRY_SECTOR
 * VOTING_RIGHTS
 * ID_BB_PRIM_SECURITY_FLAG
 * PRE_EURO_ID_ISIN
 * PRE_REDENOM_CRNCY
 * PRE_EURO_PAR_AMT
 * POST_REDENOM_CRNCY
 * POST_EURO_PAR_AMT
 * POST_EURO_ID_ISIN
 * REDENOM_DT
 * REDENOM_METHOD
 * REDENOM_ROUND_METHOD
 * PAR_VAL_CRNCY
 * EQY_SH_OUT
 * EQY_SH_OUT_DT
 * ID_BB_UNIQUE
 * MARKET_SECTOR_DES
 * IS_STK_MARGINABLE
 * 144A_FLAG
 * TRANSFER_AGENT
 * EQY_PRIM_SECURITY_TICKER
 * EQY_PRIM_SECURITY_COMP_EXCH
 * IS_SETS
 * WHICH_JAPANESE_SECTION
 * ADR_ADR_PER_SH
 * EQY_PRIM_SECURITY_PRIM_EXCH
 * EQY_FUND_CRNCY
 * WHEN_ISSUED
 * CDR_COUNTRY_CODE
 * CDR_EXCH_CODE
 * CNTRY_OF_INCORPORATION
 * CNTRY_OF_DOMICILE
 * SEC_RESTRICT
 * EQY_SH_OUT_REAL
 * ADR_UNDL_CRNCY
 * MULTIPLE_SHARE
 * PX_QUOTE_LOT_SIZE
 * PX_ROUND_LOT_SIZE
 * ID_SEDOL2
 * SEDOL1_COUNTRY_ISO
 * SEDOL2_COUNTRY_ISO
 * ID_MIC_PRIM_EXCH
 * ID_MIC_LOCAL_EXCH
 * LSE_SECTOR
 * LSE_SEGMENT
 * EQY_SH_OUT_TOT_MULT_SH
 * SECURITY_TYP2
 * ID_BB_PRIM_SECURITY
 * EQY_OPT_AVAIL
 * EQY_FREE_FLOAT_PCT
 * TICKER_AND_EXCH_CODE
 * EQY_INIT_PO_DT
 * EQY_PO_DT
 * EQY_INIT_PO_SH_PX
 * EQY_SPLIT_ADJ_INIT_PO_PX
 * EQY_FUND_TICKER
 * DTC_ELIGIBLE
 * TOTAL_VOTING_SHARES_VALUE

 * @author marco.bonin
 */

public class BloombergSecurityBean {

	private String file;
	private int position;
	private String ticker;				//TICKER
	private String tickerToFilter;		//EQY_PRIM_SEC_TICKER
	private String exchangeShrt;		//EQY_PRIM_EXCH_SHRT
	private String exchangeShrtToFilter;//EQY_PRIM_SECUR_PRIM_EXCH
	private String rCode;				//RCODE
	private String exchName;
	private String name;
	private String country;
	private String crncy;
	private String idSedol1;
	private String idSedol2;	
	private String idIsin;
	private String idCusip;
	private String idPrimaryExchange;
	private String namePrimaryExchange;
	private String securityType;
	private String industrySector;
	private String industryGroup;
	private String industrySubGroup;
	private String bloombergId;
	private String relIndex;
	private HashMap<String,String> optionalValues;
	private SPSecurityFinancialAssets financialAsset;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getIndustrySector() {
		return industrySector;
	}
	public void setIndustrySector(String industrySector) {
		this.industrySector = industrySector;
	}
	public String getIndustryGroup() {
		return industryGroup;
	}
	public void setIndustryGroup(String industryGroup) {
		this.industryGroup = industryGroup;
	}
	public String getIndustrySubGroup() {
		return industrySubGroup;
	}
	public void setIndustrySubGroup(String industrySubGroup) {
		this.industrySubGroup = industrySubGroup;
	}
	public String getBloombergId() {
		return bloombergId;
	}
	public void setBloombergId(String bloomberId) {
		this.bloombergId = bloomberId;
	}
	public String getRelIndex() {
		return relIndex;
	}
	public void setRelIndex(String relIndex) {
		this.relIndex = relIndex;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getExchName() {
		return exchName;
	}
	public void setExchName(String exchName) {
		this.exchName = exchName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCrncy() {
		return crncy;
	}
	public void setCrncy(String crncy) {
		this.crncy = crncy;
	}
	public String getIdSedol1() {
		return idSedol1;
	}
	public void setIdSedol1(String idSedol1) {
		this.idSedol1 = idSedol1;
	}
	public String getIdSedol2() {
		return idSedol2;
	}
	public void setIdSedol2(String idSedol2) {
		this.idSedol2 = idSedol2;
	}
	public String getIdIsin() {
		return idIsin;
	}
	public void setIdIsin(String idIsin) {
		this.idIsin = idIsin;
	}
	public String getIdCusip() {
		return idCusip;
	}
	public void setIdCusip(String idCusip) {
		this.idCusip = idCusip;
	}
	public String getIdPrimaryExchange() {
		return idPrimaryExchange;
	}
	public void setIdPrimaryExchange(String idPrimaryExchange) {
		this.idPrimaryExchange = idPrimaryExchange;
	}
	public String getNamePrimaryExchange() {
		return namePrimaryExchange;
	}
	public void setNamePrimaryExchange(String namePrimaryExchange) {
		this.namePrimaryExchange = namePrimaryExchange;
	}
	public String getTickerToFilter() {
		return tickerToFilter;
	}
	public void setTickerToFilter(String tickerToFilter) {
		this.tickerToFilter = tickerToFilter;
	}
	public String getExchangeShrt() {
		return exchangeShrt;
	}
	public void setExchangeShrt(String exchangeShrt) {
		this.exchangeShrt = exchangeShrt;
	}
	public String getExchangeShrtToFilter() {
		return exchangeShrtToFilter;
	}
	public void setExchangeShrtToFilter(String exchangeShrtToFilter) {
		this.exchangeShrtToFilter = exchangeShrtToFilter;
	}
	public String getRCode() {
		return rCode;
	}
	public void setRCode(String code) {
		rCode = code;
	}
	public boolean getOutInteresting(){
		
		boolean reply=false;
		
		if((exchangeShrt!=null)&&(ticker!=null)&&("0".equals(rCode))&&(exchangeShrt.equals(exchangeShrtToFilter))&&(ticker.equals(tickerToFilter)))
			reply=true;
		
		return reply;
	}
	public boolean getDifInteresting(){
		
		boolean reply=false;

		if (financialAsset.getId().equals(CASecurityFinancialAssets.EQUITY.getId())){
			if((exchangeShrt!=null)&&(ticker!=null)&&(("0".equals(rCode))||("-1".equals(rCode)))&&(exchangeShrt.equals(exchangeShrtToFilter))&&(ticker.equals(tickerToFilter)))
				reply=true;
		}else{
			if("0".equals(rCode)||"-1".equals(rCode))
				reply=true;
		}
		
		return reply;
	}
	
	public HashMap<String, String> getOptionalValues() {
		return optionalValues;
	}
	public void setOptionalValues(HashMap<String, String> _optionalValues) {
		this.optionalValues = _optionalValues;
	}
	public SPSecurityFinancialAssets getFinancialAsset() {
		return financialAsset;
	}
	public void setFinancialAsset(SPSecurityFinancialAssets financialAsset) {
		this.financialAsset = financialAsset;
	}
	
	
	
}
