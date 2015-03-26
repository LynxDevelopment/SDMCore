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
import com.lynxspa.entities.securities.managers.SPManager;
import com.lynxspa.entities.securities.managers.auxs.SPManagerAux;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
import com.lynxspa.hbt.utils.HibernateUtils;

/**
 * 
 * @author joseluis.llorente
 *
 * @param <T>
 */
@NodeBeautifier(description="Load Managers", category="CorporateActionsCore", largeIcon="../../../../lynxit/fpm/nodes/icons/db_input_32.gif", smallIcon="../../../../lynxit/fpm/nodes/icons/db_input_16.gif")
public class LoadManagers <T extends ScheduledEvent> extends InternalNodeSupport <T>{

	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	private int pageSize = 100;

	private static final Logger logger = Logger.getLogger(LoadManagers.class);
	
	@Override
	@SuppressWarnings("unchecked")
	protected void processMessage(T _message) throws FPMException {
		SDMConfigManager configManager=null;
		StatelessSession statelessSession=null;
		Query loadManagersQuery = null;
		int rowsMarkedForLoad=0, errors=0;
		int rowsDeleted=0;
		List<SPManagerAux> auxManagersList = null;
		SPManager manager = null;
		long totalRows = 0l;
		long totalPages = 0l;
		
		try {
			configManager=SDMConfigManager.getInstance();
			statelessSession=this.resourceStateless.getNewInstance();
			
			loadManagersQuery = statelessSession.createQuery("update SPManagerAux auxManager set auxManager.deleted=:deleted where auxManager.deleted=:deletedFalse");
			loadManagersQuery.setParameter("deleted", true);
			loadManagersQuery.setParameter("deletedFalse", false);
			rowsMarkedForLoad=loadManagersQuery.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
			
						
			if (rowsMarkedForLoad>0){
				LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_BEGIN, new Object[]{"SPManagerAux",rowsMarkedForLoad},null);
				
				//Paged query
				loadManagersQuery=statelessSession.createQuery("select count(*) from SPManagerAux auxManagers where auxManagers.deleted=:deleted");
				loadManagersQuery.setParameter("deleted",true);
				
				totalRows = (Long)loadManagersQuery.uniqueResult();
				totalPages = calculateTotalPages(totalRows);
				
				for (int pageNumber=0;pageNumber<totalPages;pageNumber++){
					HibernateUtils.beguinTransaction(statelessSession);
					loadManagersQuery=statelessSession.createQuery("select auxManagers from SPManagerAux auxManagers where auxManagers.deleted=:deleted order by auxManagers.id asc");
					loadManagersQuery.setParameter("deleted",true);
					loadManagersQuery.setMaxResults(pageSize);
					auxManagersList = loadManagersQuery.list();
				
					for (SPManagerAux auxManagers:auxManagersList){
						try{
							loadManagersQuery=statelessSession.createQuery("select manager from SPManager manager where manager.customerId=:managerId");
							loadManagersQuery.setParameter("managerId",auxManagers.getCustomerId().trim());
							loadManagersQuery.setMaxResults(1);
							manager = (SPManager)loadManagersQuery.uniqueResult();
							if (manager==null){
								manager = new SPManager(auxManagers.getAuditorId(),auxManagers.getName(),auxManagers.getCustomerId().trim());
								manager.getAuditor().setDeleted(auxManagers.getState()!=null && auxManagers.getState().equals("A"));
								manager.setEmail(auxManagers.getEmail()!=null?auxManagers.getEmail().trim():"");
			
								HibernateUtils.customSave(statelessSession, manager, auxManagers.getAuditorId());
							}else{
								manager.setEmail(auxManagers.getEmail()!=null?auxManagers.getEmail().trim():"");
								manager.setCustomerId(auxManagers.getCustomerId().trim());
								manager.setName(auxManagers.getName());
								manager.getAuditor().setDeleted(auxManagers.getState()!=null && auxManagers.getState().equals("A"));
								HibernateUtils.customUpdate(statelessSession, manager,  auxManagers.getAuditorId());
							}
							
							loadManagersQuery=statelessSession.createQuery("delete SPManagerAux as auxManagers where auxManagers.deleted=:deleted and auxManagers.id=:auxManagerId");
							loadManagersQuery.setParameter("deleted", true);
							loadManagersQuery.setLong("auxManagerId", auxManagers.getId());
							rowsDeleted+=loadManagersQuery.executeUpdate();
							
							
						}catch(Exception e){
							LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogErrorDict.AUX_TABLE_LOAD_ERROR, new Object[]{"AUX_TB_SP_MANAGER",auxManagers.getId()}, e);
							auxManagers.setDeleted(false);
							HibernateUtils.customUpdate(statelessSession, auxManagers,  auxManagers.getAuditorId());
						}
					}					
					HibernateUtils.commitTransaction(statelessSession);
				}
				loadManagersQuery = statelessSession.createQuery("update SPManagerAux auxManager set auxManager.deleted=:deleted where auxManager.deleted=:deletedFalse");
				loadManagersQuery.setParameter("deleted", true);
				loadManagersQuery.setParameter("deletedFalse", false);
				errors=loadManagersQuery.executeUpdate();
				HibernateUtils.commitTransaction(statelessSession);
				LogUtils.createLog(statelessSession, this.user, configManager.getBundleName(), new Locale(this.locale), configManager.getApplication(statelessSession), LogInfoDict.AUX_TABLE_LOAD_END, new Object[]{"AUX_TB_SP_CUSTOMER",rowsDeleted, errors},null);
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
