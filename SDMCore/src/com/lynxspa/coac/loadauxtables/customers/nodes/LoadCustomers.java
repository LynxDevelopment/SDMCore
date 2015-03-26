package com.lynxspa.coac.loadauxtables.customers.nodes;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
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
import com.lynxspa.entities.securities.customers.SPCustomer;
import com.lynxspa.entities.securities.customers.auxs.SPCustomerAux;
import com.lynxspa.entities.securities.managers.SPManager;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="Load Customers", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadCustomers<T extends ScheduledEvent> extends InternalNodeSupport <T>{

	private static final Logger logger = Logger.getLogger(LoadCustomers.class);

	private Resource<Session> resource;
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager manager=null;
		StatelessSession statelessSession=null;
		Query loadCustomersQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPCustomerAux> auxCustomerList = null;
		SPCustomer customer = null;
		SPManager custManager = null;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			manager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadCustomersQuery = statelessSession.createQuery("update SPCustomerAux auxCustomer set auxCustomer.deleted=:deleted where auxCustomer.deleted=:deletedFalse");
			loadCustomersQuery.setParameter("deleted", true);
			loadCustomersQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadCustomersQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"SPCustomerAux",rowsMarkedForLoad},null);
				
				//Paged query
				loadCustomersQuery=statelessSession.createQuery("select count(*) from SPCustomerAux auxCustomers where auxCustomers.deleted=:deleted");
				loadCustomersQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadCustomersQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadCustomersQuery=statelessSession.createQuery("select auxCustomers from SPCustomerAux auxCustomers where auxCustomers.deleted=:deleted order by auxCustomers.id asc");
					loadCustomersQuery.setParameter("deleted",true);
					loadCustomersQuery.setMaxResults(pageSize);
					auxCustomerList = loadCustomersQuery.list();
					
					for (SPCustomerAux auxCustomers:auxCustomerList){
						try{
							loadCustomersQuery=statelessSession.createQuery("select customer from SPCustomer customer where customer.customerId=:customerId");
							loadCustomersQuery.setParameter("customerId",auxCustomers.getCustomerId());
							loadCustomersQuery.setMaxResults(1);
							customer = (SPCustomer)loadCustomersQuery.uniqueResult();
							
							loadCustomersQuery=statelessSession.createQuery("select manager from SPManager manager where manager.customerId=:managerId");
							loadCustomersQuery.setParameter("managerId",auxCustomers.getManagerId().trim());
							loadCustomersQuery.setMaxResults(1);
							custManager = (SPManager)loadCustomersQuery.uniqueResult();
					
							if (customer==null){
								customer = new SPCustomer(auxCustomers.getAuditorId(),auxCustomers.getName(),auxCustomers.getCustomerId());
								customer.getAuditor().setDeleted(auxCustomers.getState()!=null && auxCustomers.getState().equals("A"));
								customer.setManager(custManager);
								HibernateUtils.customSave(statelessSession, customer, auxCustomers.getAuditorId());
							}else{
								customer.setManager(custManager);
								customer.setName(auxCustomers.getName());
								customer.getAuditor().setDeleted(auxCustomers.getState()!=null && auxCustomers.getState().equals("A"));
								HibernateUtils.customUpdate(statelessSession, customer,  auxCustomers.getAuditorId());
							}

							loadCustomersQuery=statelessSession.createQuery("delete SPCustomerAux as auxCustomers where auxCustomers.deleted=:deleted and auxCustomers.id=:auxCustomerId");
							loadCustomersQuery.setParameter("deleted", true);
							loadCustomersQuery.setLong("auxCustomerId", auxCustomers.getId());
							rowsDeleted+=loadCustomersQuery.executeUpdate();
						}catch(Exception e){
							LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_CUSTOMER",auxCustomers.getId()}, e);
							auxCustomers.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxCustomers,  auxCustomers.getAuditorId());
						}
					}
					
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadCustomersQuery = statelessSession.createQuery("update SPCustomerAux auxCustomer set auxCustomer.deleted=:deleted where auxCustomer.deleted=:deletedFalse");
				loadCustomersQuery.setParameter("deleted", true);
				loadCustomersQuery.setParameter("deletedFalse", false);
				errors=loadCustomersQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_CUSTOMER",rowsDeleted,errors},null);
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
	public Resource<Session> getResource() {
		return resource;
	}
	public void setResource(Resource<Session> resource) {
		this.resource = resource;
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
