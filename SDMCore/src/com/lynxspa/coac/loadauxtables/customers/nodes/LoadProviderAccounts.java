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
import com.lynxspa.sdm.entities.events.providers.CAEventProvider;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.providers.SPProviderAccount;
import com.lynxspa.entities.securities.providers.auxs.SPProviderAccountAux;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;
/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="Load Account Providers", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadProviderAccounts <T extends ScheduledEvent> extends InternalNodeSupport <T>{

	private static final Logger logger = Logger.getLogger(LoadProviderAccounts.class);

	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager manager=null;
		StatelessSession statelessSession=null;
		Query loadProviderAccountQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPProviderAccountAux> auxProvAccountList = null;
		CAEventProvider provider = null;
		SPProviderAccount providerAccount=null;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			manager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadProviderAccountQuery = statelessSession.createQuery("update SPProviderAccountAux auxProviderAccount set auxProviderAccount.deleted=:deleted where auxProviderAccount.deleted=:deletedFalse");
			loadProviderAccountQuery.setParameter("deleted", true);
			loadProviderAccountQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadProviderAccountQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"SPProviderAccountAux",rowsMarkedForLoad},null);
				
				//Paged query
				loadProviderAccountQuery=statelessSession.createQuery("select count(*) from SPProviderAccountAux auxProviderAccount where auxProviderAccount.deleted=:deleted");
				loadProviderAccountQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadProviderAccountQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadProviderAccountQuery=statelessSession.createQuery("select auxProviderAccount from SPProviderAccountAux auxProviderAccount where auxProviderAccount.deleted=:deleted order by auxProviderAccount.id asc");
					loadProviderAccountQuery.setParameter("deleted",true);
					loadProviderAccountQuery.setMaxResults(pageSize);
					auxProvAccountList = loadProviderAccountQuery.list();
					
					for (SPProviderAccountAux auxProvAccounts:auxProvAccountList){
						try{
							//loadProviderAccountQuery = statelessSession.createQuery("select customer from SPCustomer customer where customer.customerId=:auxCustomerId");
							//customer = (SPCustomer)(statelessSession.get(SPCustomer.class, auxCustomers.getCustomerId()));
							loadProviderAccountQuery=statelessSession.createQuery(""+
									"select providerAccount from SPProviderAccount providerAccount where providerAccount.accountNumber=:providerAccountNumber and providerAccount.eventProvider.id=:providerId");
							loadProviderAccountQuery.setParameter("providerAccountNumber",auxProvAccounts.getAccountNumber().trim());
							loadProviderAccountQuery.setParameter("providerId", auxProvAccounts.getEventProviderId().trim());
							loadProviderAccountQuery.setMaxResults(1);
							providerAccount = (SPProviderAccount)loadProviderAccountQuery.uniqueResult();
							
							loadProviderAccountQuery=statelessSession.createQuery(""+
									"select provider from CAEventProvider provider where provider.id=:providerId");
							loadProviderAccountQuery.setParameter("providerId",auxProvAccounts.getEventProviderId().trim());
							loadProviderAccountQuery.setMaxResults(1);
							provider = (CAEventProvider)loadProviderAccountQuery.uniqueResult();
					
							//custManager = (SPManager)(statelessSession.get(SPManager.class, 0l));
							if (providerAccount==null){
								providerAccount = new SPProviderAccount(auxProvAccounts.getAuditorId(),auxProvAccounts.getName(),auxProvAccounts.getAccountNumber(),auxProvAccounts.isOwnAccount());
								providerAccount.setEventProvider(provider);
								providerAccount.getAuditor().setDeleted(auxProvAccounts.getState()!=null && auxProvAccounts.getState().equals("A"));
								HibernateUtils.customSave(statelessSession, providerAccount, auxProvAccounts.getAuditorId());
							}else{
								providerAccount.setName(auxProvAccounts.getName().trim());
								providerAccount.setOwnAccount(auxProvAccounts.isOwnAccount());
								providerAccount.getAuditor().setDeleted(auxProvAccounts.getState()!=null && auxProvAccounts.getState().equals("A"));
								HibernateUtils.customUpdate(statelessSession, providerAccount,  auxProvAccounts.getAuditorId());
							}
							
							loadProviderAccountQuery=statelessSession.createQuery("delete SPProviderAccountAux as auxProviderAccount where auxProviderAccount.deleted=:deleted and auxProviderAccount.id=:auxProvId");
							loadProviderAccountQuery.setParameter("deleted", true);
							loadProviderAccountQuery.setLong("auxProvId", auxProvAccounts.getId());
							rowsDeleted+=loadProviderAccountQuery.executeUpdate();
						}catch(Exception e){
							LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_CUSTODIAN_ACCOUNTS",auxProvAccounts.getId()}, e);
							auxProvAccounts.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxProvAccounts,  auxProvAccounts.getAuditorId());
							
						}
					}
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadProviderAccountQuery = statelessSession.createQuery("update SPProviderAccountAux auxProviderAccount set auxProviderAccount.deleted=:deleted where auxProviderAccount.deleted=:deletedFalse");
				loadProviderAccountQuery.setParameter("deleted", true);
				loadProviderAccountQuery.setParameter("deletedFalse", false);
				errors=loadProviderAccountQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_CUSTODIAN_ACCOUNTS",rowsDeleted,errors},null);
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
