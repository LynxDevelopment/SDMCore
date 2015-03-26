/**
 *
 * This is a node class for the parsing of Bloomberg Securities files (usually .*.out).
 *
 * 
 * @author marco.bonin
 * 
 */

package com.lynxspa.coac.importers.securities.bloomberg.nodes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.dictionaries.securities.securityfinancialassets.CASecurityFinancialAssets;
import com.lynxspa.coac.importers.ImportParserAdapter;
import com.lynxspa.coac.importers.bloomberg.nodes.BloombergParserNode;
import com.lynxspa.coac.importers.securities.bloomberg.SecuritiesBloombergParser;
import com.lynxspa.coac.importers.securities.bloomberg.beans.BloombergSecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssets;
import com.lynxspa.entities.securities.financialassets.SPSecurityFinancialAssetsDetails;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

@NodeBeautifier(description="Securities Bloomberg Parser", category="CorporateActionsCore", smallIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class SecuritiesBloombergParserNode extends InternalNodeSupport<InputStream>  {
	
	public static final String DEFAULTDATEFORMAT="yyyyMMdd";
	private int messagesProcessed=0;
	
	@SuppressWarnings("unused")
	private String dateFormat=null;
	protected CAFormat format=null;
	protected ImportParserAdapter parser=null;
	protected Resource<Session> resource=null;
	protected Resource<StatelessSession> resourceStateless=null;
	protected int bufferSize=8192;
	protected String user=null;	
	protected String locale=null;
	protected OutputConnectable<? super BloombergSecurityBean> nodeConnectedToOut_;
	protected CASecurityFinancialAssets financialAsset;
	
	public SecuritiesBloombergParserNode(){
		super();
		this.dateFormat=BloombergParserNode.DEFAULTDATEFORMAT;
		this.format=CAFormat.BLOOMBERG;
		this.parser=new SecuritiesBloombergParser();
	}
	
	@Override
	protected final void processMessage(InputStream _message) throws FPMException{
		
		BufferedReader reader=null;
		Session session=null;
		StatelessSession statelessSession=null;
		SDMConfigManager manager=null;
		long processingTime=0l;
		
		try{
			financialAsset = (CASecurityFinancialAssets)this.getBusinessProcess().getParentProcess().getContextAttribute("financialAsset");
			
			session=this.resource.getCurrentInstance();
			statelessSession=this.resourceStateless.getCurrentInstance();
			manager=SDMConfigManager.getInstance();
			this.messagesProcessed=0;
			reader=new BufferedReader(new InputStreamReader(_message),this.bufferSize);
			processingTime=System.currentTimeMillis();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.START_SECURITY_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName")},null);
			
			processFile(session,manager,reader,statelessSession);
			reader.close();
			processingTime=System.currentTimeMillis()-processingTime;
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogInfoDict.END_SECURITY_PARSE, new Object[]{this.format.getCode(),this.getBusinessProcess().getContextAttribute("fileName"),processingTime,this.messagesProcessed},null);
		}catch(FPMException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new FPMException(LogErrorDict.SECURITY_IMPORT_FAIL,new Object[]{"Bloomberg",String.valueOf(this.getBusinessProcess().getContextAttribute("fileName"))},e);
		}
	}
	
	protected void processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader, StatelessSession _statelessSession) throws FPMException{
		
		String readedString=null;
		int processingLine=0;
		HashMap<String,String> optionalSecurityValues = null;
		ArrayList<String> fieldList = null;
		
		try{
			fieldList = new ArrayList<String>();
			optionalSecurityValues = getOptionalSecurityValues(_session);
			readedString=_reader.readLine();
			processingLine++;
			while((readedString!=null)&&(!readedString.startsWith("START-OF-FIELDS"))){
				if(readedString.startsWith("DATEFORMAT"))
					this.dateFormat=readedString.substring(11).trim().replaceAll("mm","MM");
				readedString=_reader.readLine();
				processingLine++;
			}
			while((readedString!=null)&&(!"END-OF-FIELDS".equals(readedString.trim()))){
				readedString=_reader.readLine();
				if((readedString.length() == 0) || (readedString.startsWith("#") || "END-OF-FIELDS".equals(readedString.trim())) ){
					// NOTHING TO DO IN THIS CASE. FAKE LINES AND ANYTHING LIKE "# Security description"...
				}else{
					// POPULATE A SIMPLE LIST WITH NAME OF THE FIELDS
					fieldList.add(readedString);
					processingLine++;	
				}
			}		
			while((readedString!=null)&&(!readedString.startsWith("START-OF-DATA"))){
				readedString=_reader.readLine();
				processingLine++;
			}		
			while((readedString!=null)&&(!"END-OF-DATA".equals(readedString.trim()))){
				readedString=_reader.readLine();
				if((readedString.length() == 0) || (readedString.startsWith("#") || "END-OF-DATA".equals(readedString.trim())) ){
					// NOTHING TO DO IN THIS CASE. FAKE LINES AND ANYTHING LIKE "# Security description"...
				}else{
					processBloombergSecurityMessage(_session,_manager,readedString,fieldList,"|",processingLine, optionalSecurityValues);
					processingLine++;	
				}
			}	
		}catch(FPMException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new FPMException(LogErrorDict.SECURITY_IMPORT_FAIL,new Object[]{"Bloomberg",String.valueOf(this.getBusinessProcess().getContextAttribute("fileName"))},e);
		}
	}
	
	protected void processBloombergSecurityMessage (Session _session,SDMConfigManager _manager,String _readedString, List<String> _fieldList, String _tokenSeparator,int _processingLine, HashMap<String,String> _optionalSecurityValues) throws FPMException{
		String masterString=null;
		BloombergSecurityBean security=null;
		StringTokenizer tokenizer=null;
		HashMap<String,String> valuesToStore = null;
		SPSecurityFinancialAssets secFinancialAsset=null;
		try{
			valuesToStore = new HashMap<String,String>();
			secFinancialAsset = (SPSecurityFinancialAssets) _session.load(SPSecurityFinancialAssets.class, this.financialAsset.getId());
			
			security = new BloombergSecurityBean();
			security.setFile(String.valueOf(this.getBusinessProcess().getContextAttribute("fileName")));
			security.setPosition(_processingLine);
			security.setFinancialAsset(secFinancialAsset);
			masterString = _readedString;
			// FIRST WE NEED TO CUT THE INITIAL PART OF 3 | (PIPES) AND GET RCODE
			for(int i=0;i<3;i++){
				if(i==1)
					security.setRCode(masterString.substring(0,masterString.indexOf("|"))); 
				masterString = masterString.substring(masterString.indexOf("|")+1);
			}
			// SECOND WE SPLIT THE LIST
			tokenizer=new StringTokenizer(masterString,_tokenSeparator);
			if (!financialAsset.equals(CASecurityFinancialAssets.DEBT)){
				valuesToStore = getSecurityValues(tokenizer, security, _fieldList, _optionalSecurityValues);
			}else{
				valuesToStore = getSecurityDEBTValues(tokenizer, security, _fieldList, _optionalSecurityValues);
			}
	        
	        security.setOptionalValues(valuesToStore);
			this.messagesProcessed++;
			// PASS VALUE TO THE NEXT FILTER
			getNodeConnectedToOut().process(security);
		}catch (FPMException e) {
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new FPMException(LogErrorDict.PARSING_EXCEPTION,new Object[]{"Bloomberg",_processingLine,String.valueOf(this.getBusinessProcess().getContextAttribute("fileName"))},e);
		}
	}	

	@SuppressWarnings("unchecked")
	protected HashMap<String,String> getOptionalSecurityValues (Session _session) throws FPMException{
		List<SPSecurityFinancialAssetsDetails> securityFADetails=null;
		HashMap<String,String> reply = null;
		Query query=null;
		try{
			reply = new HashMap<String,String>();
			query=_session.createQuery("select secFADetail from SPSecurityFinancialAssetsDetails as secFADetail "+
					" where secFADetail.auditor.deleted=:isDeleted "+
					" and secFADetail.store=:isStore and secFADetail.securityType.id=:securityFinancialAssetsId");
			query.setParameter("isStore",true);
			query.setParameter("isDeleted",false);
			query.setParameter("securityFinancialAssetsId",this.financialAsset.getId());
			securityFADetails=query.list();

			for (SPSecurityFinancialAssetsDetails fadetails:securityFADetails){
				reply.put(fadetails.getName(), fadetails.getFieldPath());
			}
		}catch (Exception e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		
		return reply;
	}
	
	protected HashMap<String,String> getSecurityValues (StringTokenizer _tokenizer, BloombergSecurityBean security, List<String> _fieldList, HashMap<String,String> _optionalSecurityValues){
		HashMap<String,String> reply= null;
		String actualToken=null;
		int fieldPointer = 0;
		String key=null;
		
		reply = new HashMap<String,String>();
		while (_tokenizer.hasMoreTokens()) {
    		actualToken=(String)_tokenizer.nextElement();
    		// THIRD, HAVING THE TOKEN AND THE FIELD WE CREATE AND SET THE BEAN
    		if ("TICKER".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setTicker(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_SECURITY_TICKER".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setTickerToFilter(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_EXCH_SHRT".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setExchangeShrt(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_SECURITY_PRIM_EXCH".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setExchangeShrtToFilter(filterNullBloombergValue(actualToken));
    		if ("EXCH_CODE".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setExchName(filterNullBloombergValue(actualToken));
    		if ("NAME".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setName(filterNullBloombergValue(actualToken));    		
    		if ("COUNTRY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setCountry(filterNullBloombergValue(actualToken));   
    		if ("CRNCY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setCrncy(filterNullBloombergValue(actualToken));       		
    		if ("ID_SEDOL1".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdSedol1(filterNullBloombergValue(actualToken));   
    		if ("ID_SEDOL2".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdSedol2(filterNullBloombergValue(actualToken));
    		if ("ID_ISIN".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdIsin(filterNullBloombergValue(actualToken));
    		if ("ID_CUSIP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdCusip(filterNullBloombergValue(actualToken));
    		if ("ID_MIC_PRIM_EXCH".equalsIgnoreCase(_fieldList.get(fieldPointer))){
    			security.setIdPrimaryExchange(filterNullBloombergValue(actualToken));
    			if(security.getIdPrimaryExchange()==null)    				
    				security.setIdPrimaryExchange("XXXX");
    		}
    		if ("ID_MIC1".equalsIgnoreCase(_fieldList.get(fieldPointer)) && financialAsset.getId().equals(CASecurityFinancialAssets.DEBT.getId())){
    			security.setIdPrimaryExchange(filterNullBloombergValue(actualToken));
    			if(security.getIdPrimaryExchange()==null)    				
    				security.setIdPrimaryExchange("XXXX");
    		}
    		if ("SECURITY_TYP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setSecurityType(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_GROUP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustryGroup(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_SUBGROUP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustrySubGroup(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_SECTOR".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustrySector(filterNullBloombergValue(actualToken));
    		if ("ID_BB_COMPANY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setBloombergId(filterNullBloombergValue(actualToken));
    		if ("REL_INDEX".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setRelIndex(filterNullBloombergValue(actualToken));
    		
    		if(_optionalSecurityValues.containsKey(_fieldList.get(fieldPointer).toLowerCase())){
    			key= _optionalSecurityValues.get(_fieldList.get(fieldPointer).toLowerCase());
    			reply.put(key.replaceAll(":",""),filterNullBloombergValue(actualToken));
    		}
    		fieldPointer++;
        }
		return reply;
	}
	
	protected HashMap<String,String> getSecurityDEBTValues (StringTokenizer _tokenizer, BloombergSecurityBean security, List<String> _fieldList, HashMap<String,String> _optionalSecurityValues){
		HashMap<String,String> reply= null;
		String actualToken=null;
		int fieldPointer = 0;
		String key=null;
		
		reply = new HashMap<String,String>();
		while (_tokenizer.hasMoreTokens()) {
    		actualToken=(String)_tokenizer.nextElement();
    		// THIRD, HAVING THE TOKEN AND THE FIELD WE CREATE AND SET THE BEAN
    		if ("TICKER".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setTicker(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_SECURITY_TICKER".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setTickerToFilter(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_EXCH_SHRT".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setExchangeShrt(filterNullBloombergValue(actualToken));
    		if ("EQY_PRIM_SECURITY_PRIM_EXCH".equalsIgnoreCase(_fieldList.get(fieldPointer)))  			
    			security.setExchangeShrtToFilter(filterNullBloombergValue(actualToken));
    		if ("EXCH_CODE".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setExchName(filterNullBloombergValue(actualToken));
    		if ("SECURITY_DES".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setName(filterNullBloombergValue(actualToken));    		
    		if ("COUNTRY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setCountry(filterNullBloombergValue(actualToken));   
    		if ("CRNCY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setCrncy(filterNullBloombergValue(actualToken));       		
    		if ("ID_SEDOL1".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdSedol1(filterNullBloombergValue(actualToken));   
    		if ("ID_SEDOL2".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdSedol2(filterNullBloombergValue(actualToken));
    		if ("ID_ISIN".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdIsin(filterNullBloombergValue(actualToken));
    		if ("ID_CUSIP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIdCusip(filterNullBloombergValue(actualToken));
    		if ("ID_MIC_PRIM_EXCH".equalsIgnoreCase(_fieldList.get(fieldPointer))){
    			security.setIdPrimaryExchange(filterNullBloombergValue(actualToken));
    			if(security.getIdPrimaryExchange()==null)    				
    				security.setIdPrimaryExchange("XXXX");
    		}
    		if ("ID_MIC1".equalsIgnoreCase(_fieldList.get(fieldPointer)) && financialAsset.getId().equals(CASecurityFinancialAssets.DEBT.getId())){
    			security.setIdPrimaryExchange(filterNullBloombergValue(actualToken));
    			if(security.getIdPrimaryExchange()==null)    				
    				security.setIdPrimaryExchange("XXXX");
    		}
    		if ("SECURITY_TYP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setSecurityType(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_GROUP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustryGroup(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_SUBGROUP".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustrySubGroup(filterNullBloombergValue(actualToken));
    		if ("INDUSTRY_SECTOR".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setIndustrySector(filterNullBloombergValue(actualToken));
    		if ("ID_BB_COMPANY".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setBloombergId(filterNullBloombergValue(actualToken));
    		if ("REL_INDEX".equalsIgnoreCase(_fieldList.get(fieldPointer)))
    			security.setRelIndex(filterNullBloombergValue(actualToken));
    		
    		if(_optionalSecurityValues.containsKey(_fieldList.get(fieldPointer).toLowerCase())){
    			key= _optionalSecurityValues.get(_fieldList.get(fieldPointer).toLowerCase());
    			reply.put(key.replaceAll(":",""),filterNullBloombergValue(actualToken));
    		}
    		fieldPointer++;
        }
		return reply;
	}
	
	protected String filterNullBloombergValue(String _bloombergValue){
		
		return ((_bloombergValue==null)||(("".equals(_bloombergValue.trim()))||("N.A.".equals(_bloombergValue.trim())))? null : _bloombergValue.trim());
	}
	
	@ConfigParam(required = true, description = "DefaultUser")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@ConfigParam(required = true, description = "Session",group="config")
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void connectNodeToOut(OutputConnectable< ? super BloombergSecurityBean> node){
        nodeConnectedToOut_ = node;
    }
    public OutputConnectable< ? super BloombergSecurityBean> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }

    @ConfigParam(required=true,description="StatelessSession",group="config")
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}

	public void setResourceStateless(Resource<StatelessSession> resourceStateless) {
		this.resourceStateless = resourceStateless;
	}

}

