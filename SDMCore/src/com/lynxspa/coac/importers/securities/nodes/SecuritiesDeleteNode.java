package com.lynxspa.coac.importers.securities.nodes;


import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.coac.importers.securities.SecuritySearchMode;
import com.lynxspa.sdm.importers.securities.beans.SecurityBean;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.extensions.StandardStatelessSession;
import com.lynxspa.hbt.utils.HibernateUtils;


/**
 * @author xavier.sarda
 */
@NodeBeautifier(description="Securities Delete", category="CorporateActionsCore")
public class SecuritiesDeleteNode extends InternalNodeSupport<SecurityBean> {

	private String user=null;
	private Resource<StandardStatelessSession> resource=null;
	private Resource<Session> stateFullResource=null;
	private String locale=null;
	private SecuritySearchMode searchMode=null;

	public SecuritiesDeleteNode() {
	}

	private SPSecurity getSecuritySearch(StandardStatelessSession _statelessSession,Session _statefullSession,SecuritySearchMode _mode,SecurityBean _securityBean) throws FPMException{
		
		SPSecurity reply=null;
		Query query=null;
		String fieldName=null;
		Object value=null;
		SDMConfigManager manager=null;
		
		if(SecuritySearchMode.ISIN.equals(_mode)){
			fieldName="isin";
			value=_securityBean.getSecurity().getIsin();
		}else if(SecuritySearchMode.CUSIP.equals(_mode)){
			fieldName="cusip";
			value=_securityBean.getSecurity().getIsin();
		}else if(SecuritySearchMode.SEDOL.equals(_mode)){
			fieldName="sedol";
			value=_securityBean.getSecurity().getIsin();
		}else{
			manager=SDMConfigManager.getInstance();
			Integer idProvider = manager.getSecurityProviderIdField(_statefullSession,_securityBean.getFormat());
			fieldName="infoProviderId"+idProvider;
			value=_securityBean.getSecurity().getInfoProviderId1();
		}
		query = _statelessSession.createQuery("from SPSecurity where auditor.deleted=:deleted and "+fieldName+"=:value");
		query.setParameter("value",value);
		query.setParameter("deleted", false);
		query.setFetchSize(1);
		query.setMaxResults(1);
		reply = (SPSecurity) query.uniqueResult();
		
		return reply;
	}

	@Override
	protected void processMessage(SecurityBean _securityBean) throws Exception {

		StandardStatelessSession session=null;
		Session stateFullSession=null;
		SPSecurity securityToDelete = null;
		
		try{
			session=this.resource.getCurrentInstance();
			stateFullSession=this.stateFullResource.getCurrentInstance();
			securityToDelete=getSecuritySearch(session,stateFullSession,this.searchMode,_securityBean);
			if(securityToDelete != null){
				HibernateUtils.customDelete(session, securityToDelete, user);
			}
		}catch(FPMException e){
			throw e;
		}catch(Throwable e){
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

    @ConfigParam(required=true, group="config", description="Name of the datasource Config Manager will be retrieved with")
	public Resource<Session> getStateFullResource() {
		return stateFullResource;
	}
	public void setStateFullResource(Resource<Session> stateFullResource) {
		this.stateFullResource = stateFullResource;
	}

	@ConfigParam(required=true, group="config", description="Name of the datasource messages will be saved to")
    public Resource<StandardStatelessSession> getResource(){
    	return resource;
    }
    public void setResource(Resource<StandardStatelessSession> _Resource){
    	this.resource = _Resource;
    }

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}

    @ConfigParam(required=true, defaultValue="true", group="config", description="Defines the search mode to find security.")
	public SecuritySearchMode getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(SecuritySearchMode searchMode) {
		this.searchMode = searchMode;
	}
}
