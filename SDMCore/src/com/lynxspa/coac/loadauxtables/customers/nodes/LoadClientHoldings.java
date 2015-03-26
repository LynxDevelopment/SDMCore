package com.lynxspa.coac.loadauxtables.customers.nodes;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.events.scheduler.ScheduledEvent;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.dictionaries.logs.LogErrorDict;
import com.lynxspa.sdm.dictionaries.logs.LogInfoDict;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPSecurityPortfolio;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.entities.securities.auxs.SPSecurityPortfolioAux;
import com.lynxspa.entities.securities.customers.SPCustomer;
import com.lynxspa.entities.securities.providers.SPProviderAccount;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;

@NodeBeautifier(description="Load Client Holdings", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadClientHoldings <T extends ScheduledEvent> extends InternalNodeSupport <T>{

	private static final Logger logger = Logger.getLogger(LoadClientHoldings.class);

	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager manager=null;
		StatelessSession statelessSession=null;
		Query loadClientHoldingsQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPSecurityPortfolioAux> auxSecurityPortfolioList = null;
		SPSecurityPortfolio securityPortfolio=null;
		SPSecurity security=null;
		SPCustomer customer=null;
		SPVirtualSecurity virtualSecurity=null;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			manager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadClientHoldingsQuery = statelessSession.createQuery("update SPSecurityPortfolioAux auxSecurityPortfolio set auxSecurityPortfolio.deleted=:deleted where auxSecurityPortfolio.deleted=:deletedFalse");
			loadClientHoldingsQuery.setParameter("deleted", true);
			loadClientHoldingsQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadClientHoldingsQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"SPProviderAccountAux",rowsMarkedForLoad},null);
				
				//Paged query
				loadClientHoldingsQuery=statelessSession.createQuery("select count(*) from SPSecurityPortfolioAux auxSecurityPortfolio where auxSecurityPortfolio.deleted=:deleted");
				loadClientHoldingsQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadClientHoldingsQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadClientHoldingsQuery=statelessSession.createQuery("select auxSecurityPortfolio from SPSecurityPortfolioAux auxSecurityPortfolio where auxSecurityPortfolio.deleted=:deleted");
					loadClientHoldingsQuery.setParameter("deleted",true);
					loadClientHoldingsQuery.setMaxResults(pageSize);
					auxSecurityPortfolioList = loadClientHoldingsQuery.list();
					
					for (SPSecurityPortfolioAux auxSecurityPortfolio:auxSecurityPortfolioList){
						try{
							loadClientHoldingsQuery=statelessSession.createQuery("from SPSecurity security where security.customerId=:customerId");
							loadClientHoldingsQuery.setParameter("customerId", auxSecurityPortfolio.getSecurity());
							loadClientHoldingsQuery.setMaxResults(1);
							security=(SPSecurity)loadClientHoldingsQuery.uniqueResult();
							
							if(security==null){
								virtualSecurity=new SPVirtualSecurity();
								virtualSecurity.setIsin(auxSecurityPortfolio.getSecurity());
								virtualSecurity.setName(auxSecurityPortfolio.getSecurity());
								HibernateUtils.customSave(statelessSession,virtualSecurity,auxSecurityPortfolio.getAuditorId());
							}
							
							loadClientHoldingsQuery=statelessSession.createQuery("from SPCustomer customer where customer.customerId=:customerId");
							loadClientHoldingsQuery.setParameter("customerId", auxSecurityPortfolio.getCustomer());
							loadClientHoldingsQuery.setMaxResults(1);
							customer=(SPCustomer)loadClientHoldingsQuery.uniqueResult();

							loadClientHoldingsQuery=statelessSession.createQuery("from SPProviderAccount account where account.accountNumber=:accountNumber");
							loadClientHoldingsQuery.setParameter("accountNumber", auxSecurityPortfolio.getProviderAccount());
							loadClientHoldingsQuery.setMaxResults(1);
							SPProviderAccount account=(SPProviderAccount)loadClientHoldingsQuery.uniqueResult();

							if(customer==null || account==null){
								logger.debug("Customer or account didn't find for auxiliar security"+auxSecurityPortfolio.getId()+". Don't deleted from AUX_TB_SPSECURITYPORTFOLIO");
							}else{
								loadClientHoldingsQuery=statelessSession.createQuery("" +
										"	select" +
										"		securityPortfolio" +
										"	from" +
										"		SPSecurityPortfolio securityPortfolio" +
										"	where" +
										"		securityPortfolio.customer.customerId=:customerId and" +
										"		securityPortfolio.emissionDate=:emissionDate and" +
										"		securityPortfolio.security.id=:securityId and" +
										"		securityPortfolio.custodianAccount.accountNumber=:custodianAccount");
								
								loadClientHoldingsQuery.setParameter("customerId", security!=null?security.getCustomerId():virtualSecurity.getIsin());
								loadClientHoldingsQuery.setParameter("emissionDate", auxSecurityPortfolio.getEmissionDate());
								loadClientHoldingsQuery.setParameter("securityId", security!=null?security.getId():virtualSecurity.getId());
								loadClientHoldingsQuery.setParameter("custodianAccount", auxSecurityPortfolio.getProviderAccount());
								loadClientHoldingsQuery.setMaxResults(1);
								securityPortfolio = (SPSecurityPortfolio)loadClientHoldingsQuery.uniqueResult();
		
								if (securityPortfolio==null){
									securityPortfolio = new SPSecurityPortfolio(auxSecurityPortfolio.getAuditorId(), customer, security!=null?security:virtualSecurity, auxSecurityPortfolio.getEmissionDate());
									securityPortfolio.getAuditor().setDeleted(auxSecurityPortfolio.getState()!=null && auxSecurityPortfolio.getState().equals("A"));
									securityPortfolio.setCustodianAccount(account);
									securityPortfolio.setCustomerAmount(auxSecurityPortfolio.getCustomerAmount());
									HibernateUtils.customSave(statelessSession, securityPortfolio, auxSecurityPortfolio.getAuditorId());
								}else{
									securityPortfolio.setCustomerAmount(auxSecurityPortfolio.getCustomerAmount());
// Duda: se sustituye el customerAmount ya existente por el auxiliar o se suma?
//									securityPortfolio.setCustomerAmount(securityPortfolio.getCustomerAmount()+auxSecurityPortfolio.getCustomerAmount());
									securityPortfolio.getAuditor().setDeleted(auxSecurityPortfolio.getState()!=null && auxSecurityPortfolio.getState().equals("A"));
									HibernateUtils.customUpdate(statelessSession, securityPortfolio, auxSecurityPortfolio.getAuditorId());
								}
								
								loadClientHoldingsQuery=statelessSession.createQuery("delete SPSecurityPortfolioAux as auxSecurityPortfolio where auxSecurityPortfolio.deleted=:deleted and auxSecurityPortfolio.id=:auxSecurityPortfolioId");
								loadClientHoldingsQuery.setParameter("deleted", true);
								loadClientHoldingsQuery.setLong("auxSecurityPortfolioId", auxSecurityPortfolio.getId());
								rowsDeleted+=loadClientHoldingsQuery.executeUpdate();
							}
						}catch(Exception e){
							LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_SECURITYPORTFOLIO",auxSecurityPortfolio.getId()}, e);
							auxSecurityPortfolio.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxSecurityPortfolio, auxSecurityPortfolio.getAuditorId());
						}
					}					
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadClientHoldingsQuery = statelessSession.createQuery("update SPSecurityPortfolioAux auxSecurityPortfolio set auxSecurityPortfolio.deleted=:deleted where auxSecurityPortfolio.deleted=:deletedFalse");
				loadClientHoldingsQuery.setParameter("deleted", true);
				loadClientHoldingsQuery.setParameter("deletedFalse", false);
				errors=loadClientHoldingsQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_SECURITYPORTFOLIO",rowsDeleted,errors},null);
			}
		} catch (FPMException e) {
			logger.error("FPMException: "+e);
			throw e;
		} catch (Exception e) {
			logger.error("Exception stablishing events to be deleted: "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}finally{
			if(statelessSession!=null)
				statelessSession.close();
		}
	}
	
	@ConfigParam(required=true)
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}
	public void setResourceStateless(Resource<StatelessSession> resourceStateless) {
		this.resourceStateless = resourceStateless;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@ConfigParam(required=false)
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize > 0)
			this.pageSize = pageSize;
	}
	
	private long calculateTotalPages(long totalElements){
    	long pages = 0l;
    	
    	pages = (totalElements/pageSize);
    	if(totalElements % pageSize != 0){
    		pages++;
    	}
    	
    	return pages;
    }
}