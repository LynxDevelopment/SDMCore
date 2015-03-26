/**
 *
 * This is a node class for the save securities with data provided by Bloomberg.
 *
 * 
 * @author marco.bonin
 * 
 */
package com.lynxspa.coac.importers.securities.nodes;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.coac.importers.securities.SecuritySearchMode;
import com.lynxspa.sdm.importers.securities.beans.SecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.details.SPSecurityDetail;
import com.lynxspa.entities.securities.markets.SPMarket;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.extensions.StandardStatelessSession;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.utils.StringUtils;


@NodeBeautifier(description="Securities Save", category="CorporateActionsCore")
public class SecuritiesSaveNode extends InternalNodeSupport<SecurityBean> {

	private String user=null;
	private Resource<StandardStatelessSession> resource=null;
	private Resource<Session> stateFullResource=null;
	private String locale=null;
	private boolean marketAddable=true;
	private SecuritySearchMode searchMode=null;
	Map<String,SPSecurity> securitiesInserted = null;// new HashMap<String, SPSecurity>();
	int inserted = 0;
	
	public SecuritiesSaveNode() {
		this.securitiesInserted = new HashMap<String, SPSecurity>();
	}

	protected SPSecurity setSecurityData(StandardStatelessSession _statelessSession,Session _statefullSession,SPSecurity _actualSecurity,SecurityBean _securityBean) throws FPMException {
		
		SPSecurity reply=null;
		SPSecurity securityUpdater=null;
		SPMarket market=null;
		SPSecurityDetail details=null;
		
		try{
			reply=_actualSecurity;
			if(reply!=null){
				securityUpdater=_securityBean.getSecurity();
				reply.setIsin(StringUtils.getSecureSizeValue(securityUpdater.getIsin(),12, reply.getIsin()));
				reply.setSedol(StringUtils.getSecureSizeValue(securityUpdater.getSedol(),7,reply.getSedol()));
				reply.setCusip(StringUtils.getSecureSizeValue(securityUpdater.getCusip(),9,reply.getCusip()));
				reply.setName(StringUtils.getSecureSizeValue(securityUpdater.getName(),64,reply.getName()));
				reply.setCurrency(StringUtils.getSecureSizeValue(securityUpdater.getCurrency(),3,reply.getCurrency()));
				SDMConfigManager manager=SDMConfigManager.getInstance();
				Integer idProvider = manager.getSecurityProviderIdField(_statefullSession, _securityBean.getFormat());
				if(idProvider!=null){
					switch(idProvider){
						case 1:	reply.setInfoProviderId1(StringUtils.getSecureSizeValue(securityUpdater.getInfoProviderId1(),16,reply.getInfoProviderId1()));
								break;
						case 2:	reply.setInfoProviderId2(StringUtils.getSecureSizeValue(securityUpdater.getInfoProviderId2(),16,reply.getInfoProviderId2()));
								break;
						case 3:	reply.setInfoProviderId3(StringUtils.getSecureSizeValue(securityUpdater.getInfoProviderId3(),16,reply.getInfoProviderId3()));
								break;
					}
				}
				reply.setSecurityType(StringUtils.getSecureSizeValue(securityUpdater.getSecurityType(),32,reply.getSecurityType()));
				reply.setIndSector(StringUtils.getSecureSizeValue(securityUpdater.getIndSector(),32,reply.getIndSector()));
				reply.setIndGroup(StringUtils.getSecureSizeValue(securityUpdater.getIndGroup(),32,reply.getIndGroup()));
				reply.setIndSubGroup(StringUtils.getSecureSizeValue(securityUpdater.getIndSubGroup(),32,reply.getIndSubGroup()));
				reply.setRelIndex(StringUtils.getSecureSizeValue(securityUpdater.getRelIndex(),8,reply.getRelIndex()));
				reply.setCountry(StringUtils.getSecureSizeValue(securityUpdater.getCountry(),2,reply.getCountry()));
				reply.setTicker(StringUtils.getSecureSizeValue(securityUpdater.getTicker(),32,reply.getTicker()));
				if((market=getMarket(_statelessSession,reply,securityUpdater.getMarket()))!=null)
					reply.setMarket(market);
				if ((details=setDetails(_statelessSession,_statefullSession, reply,securityUpdater.getSecurityDetail()))!=null){
					reply.setSecurityDetail(details);
				}
				reply.setSecFinancialAssets(securityUpdater.getSecFinancialAssets());
			}
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		
		return reply;
	}
	
	protected SPSecurityDetail setDetails(StandardStatelessSession _statelessSession, Session _statefullSession,SPSecurity _security, SPSecurityDetail _secdetailsUpdate) throws FPMException {
		
		SPSecurityDetail reply= null;
		HashMap<String,String> _details=null;
		
		try{
			if (_secdetailsUpdate!=null){
				_details = _secdetailsUpdate.getDynamicTable();
				if (_security.getSecurityDetail()==null){
					reply = new SPSecurityDetail(this.user);
					reply.setDynamicTable(_details);
					HibernateUtils.customSave(_statelessSession, reply, this.user);
				}else{
					reply = _security.getSecurityDetail();
					reply.setDynamicTable(_details);
					HibernateUtils.customUpdate(_statelessSession, reply, this.user);
				}
			}
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		
		return reply;
	}
	
	protected void insertSecurity(StandardStatelessSession _statelessSession,Session _statefullSession,SecurityBean _securityBean) throws FPMException {
		
		SPSecurity actualSecurity = null;
		String key = null;
		
		try{
			actualSecurity = new SPSecurity(this.user);
			actualSecurity.setTicker(_securityBean.getSecurity().getTicker());
			actualSecurity=setSecurityData(_statelessSession,_statefullSession,actualSecurity,_securityBean);
			
			if(actualSecurity.getIsin()!=null){
				key= actualSecurity.getIsin();
			}
			if (key!=null){
				if (this.securitiesInserted.size()>=1000){//TODO EStablecer el valor del parametro securitiesUploadBloombergCommitSize 
					this.securitiesInserted.clear();
				}
				this.securitiesInserted.put(key, actualSecurity);
			}
			HibernateUtils.customSave(_statelessSession, actualSecurity, this.user);
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
	}

	protected void updateSecurity(StandardStatelessSession _statelessSession,Session _statefullSession,SPSecurity _actualSecurity,SecurityBean _securityBean) throws FPMException {
		
		SPSecurity actualSecurity = null;
		String key = null;
		try{
			actualSecurity=setSecurityData(_statelessSession,_statefullSession,_actualSecurity,_securityBean);
			if(actualSecurity.getIsin()!=null){
				key= actualSecurity.getIsin();
			}
			if (key!=null){
				if (this.securitiesInserted.size()>=1000){//TODO EStablecer el valor del parametro securitiesUploadBloombergCommitSize 
					this.securitiesInserted.clear();
				}
				this.securitiesInserted.put(key, actualSecurity);
			}
			HibernateUtils.customUpdate(_statelessSession,actualSecurity, this.user);
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
	}
	
	protected SPMarket getMarket(StatelessSession _session,SPSecurity _actualSecurity,SPMarket _updateMarket) throws FPMException{
		
		SPMarket reply=null;
		Query query=null;
		
		if((_updateMarket!=null)&&(_updateMarket.getMic()!=null)){
			if(!(("XXXX".equals(_updateMarket.getMic()))&&(_actualSecurity.getMarket()!=null)&&(!"XXXX".equals(_actualSecurity.getMarket().getMic())))){
				query = _session.createQuery("from SPMarket where auditor.deleted=:deleted and mic=:mic");
				query.setFetchSize(1);
				query.setMaxResults(1);
				query.setReadOnly(true);
				query.setParameter("deleted",false);
				query.setParameter("mic",_updateMarket.getMic());
				reply = (SPMarket) query.uniqueResult();
				if((reply==null)&&(this.marketAddable)){
					reply=new SPMarket(this.user,StringUtils.getSecureSizeValue(_updateMarket.getMic(),4,null),StringUtils.getSecureSizeValue(_updateMarket.getName(),128,null));
					reply.setCity(StringUtils.getSecureSizeValue(_updateMarket.getCity(),32,null));
					reply.setCountry(StringUtils.getSecureSizeValue(_updateMarket.getCountry(),2,null));
					reply.setCustomerId(StringUtils.getSecureSizeValue(_updateMarket.getCustomerId(),64,null));
					reply.setTicker(StringUtils.getSecureSizeValue(_updateMarket.getTicker(),32,null));
					HibernateUtils.customSave(_session,reply, this.user);
				}
			}
		}
		
		return reply;
	}
	
	private SPSecurity getSecuritySearch(StandardStatelessSession _statelessSession,Session _statefullSession,SecuritySearchMode _mode,SecurityBean _securityBean) throws FPMException{
		SPSecurity reply=null;
		Query query=null;		
		Object isinValue=null;

		isinValue=_securityBean.getSecurity().getIsin();

		query = _statelessSession.createQuery("from SPSecurity where auditor.deleted=:deleted and isin=:isinValue");

		query.setParameter("deleted", false);
		query.setParameter("isinValue",isinValue);
		query.setFetchSize(1);
		query.setMaxResults(1);
		reply = (SPSecurity) query.uniqueResult();
		
		return reply;
	}
	
	@Override
	protected void processMessage(SecurityBean _securityBean) throws FPMException {
		
		SPSecurity security = null;
		StandardStatelessSession session=null;
		Session stateFullSession=null; 
		String key = null;
		
		try{
			session=this.resource.getCurrentInstance();
			stateFullSession=this.stateFullResource.getCurrentInstance();
			security=getSecuritySearch(session,stateFullSession,this.searchMode,_securityBean);
			if(_securityBean.getSecurity().getIsin()!=null){
				key= _securityBean.getSecurity().getIsin();
			}
			if(security!=null || this.securitiesInserted.containsKey(key)){
				if (security==null){
					security = this.securitiesInserted.get(key);
					this.inserted ++;
				}
				updateSecurity(session,stateFullSession,security,_securityBean);
			}else{
				if(SecurityBean.ActionsToDo.INSERT_OR_UPDATE.equals(_securityBean.getAction())){
					insertSecurity(session,stateFullSession,_securityBean);
				}
			}
		}catch (FPMException e) {
			throw e;
		}catch (Throwable e) {
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
	}

	@ConfigParam(required=true, group="config", description="User ho store the entity")
    public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true, group="config", description="Name of the datasource messages will be saved to")
    public Resource<StandardStatelessSession> getResource(){
    	return resource;
    }
    public void setResource(Resource<StandardStatelessSession> _Resource){
    	this.resource = _Resource;
    }

    @ConfigParam(required=true, group="config", description="Name of the datasource Config Manager will be retrieved with")
	public Resource<Session> getStateFullResource() {
		return stateFullResource;
	}
	public void setStateFullResource(Resource<Session> stateFullResource) {
		this.stateFullResource = stateFullResource;
	}

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

    @ConfigParam(required=true, defaultValue="true", group="advanced", description="If enable, add a new market if not exist, if disable doent add market and doesnt add the security related.")
	public boolean isAddableMarket() {
		return marketAddable;
	}
	public void setAddableMarket(boolean addable) {
		this.marketAddable = addable;
	}

    @ConfigParam(required=true, defaultValue="true", group="config", description="Defines the search mode to find security.")
	public SecuritySearchMode getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(SecuritySearchMode searchMode) {
		this.searchMode = searchMode;
	}
}
