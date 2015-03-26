package com.lynxspa.coac.plannings.generatefileprocess.nodes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.beans.PlanningBean;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;

/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Find Security Event", category="Corporateactionscore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class FindSecurityEventNode<T extends PlanningBean> extends InternalNodeSupport <T> {

	public static final Logger logger = Logger.getLogger(FindSecurityEventNode.class.getName());
	private OutputConnectable< ? super T> nodeConnectedToOut_;
	private Resource<StatelessSession> resource=null;
	protected String locale=null;
	protected String user=null;
	protected Resource<Session> resourceFull=null;
	
	@Override
	protected void processMessage(T message) throws Exception{

		message.setListSecurities(getISINSecurities(message.getId()));
		message.setListSecuritiesAndMarkets(getISINAndMarketsSecurities(message.getId()));
		message.setListTickers(getTICKERSecurities(message.getId()));
		message.setListContributors(getISINContributorsSecurities(message.getId()));
		message.setActualDate(new Date(System.currentTimeMillis()).toString());
		getNodeConnectedToOut().process(message);

	}

	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }

	public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }
	
	
	@SuppressWarnings("unchecked")
	protected List<String> getISINAndMarketsSecurities(Long planificationId) throws FPMException{
		StatelessSession statelessSession=null;
		List<String> reply=null;
		Query query=null;
		SDMConfigManager manager=null;
		Session session=null;
		Iterator iteResults = null;
		String isin = null;
		String market = null;
		Object[] tuple = null;
		logger.debug("Getting Information for planification with id "+planificationId);
		try{
			
			session=this.resourceFull.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			statelessSession=resource.getCurrentInstance();
			
			query=statelessSession.createQuery("select distinct(sec.isin), sec.market.mic from SPSecuritiesPlannings as secplan right outer join secplan.security as sec "+ 
				" where sec.auditor.deleted=:isDeleted and secplan.planning.id=:idPlanification and isin is not null");
			query.setParameter("isDeleted",false);
			query.setParameter("idPlanification",planificationId);
			query.setReadOnly(true);
		
			iteResults=query.list().iterator();
			reply = new ArrayList();
			while ( iteResults.hasNext() ) {
				tuple = (Object[]) iteResults.next();
				isin = (String) tuple[0];
				market = (String) tuple[1];
				
				reply.add(isin+" "+ (market!=null?market:""));
			}
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resourceFull.getCurrentInstance()), LogPlanningInfo.PLANNINGS_GET_ISINES, new Object[]{reply==null?0:reply.size(),planificationId},null);
		}catch(Exception e){
			logger.error("Error getting Information for planification with id "+planificationId+ ": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		logger.debug("Found "+reply==null?0:reply.size()+" securities for planification "+planificationId);
		return reply;
	}

	@SuppressWarnings("unchecked")
	protected List<String> getISINSecurities(Long planificationId) throws FPMException{
		StatelessSession statelessSession=null;
		List<String> reply=null;
		Query query=null;
		SDMConfigManager manager=null;
		Session session=null;
		logger.debug("Getting Information for planification with id "+planificationId);
		try{
			
			session=this.resourceFull.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			statelessSession=resource.getCurrentInstance();
			
			query=statelessSession.createQuery("select distinct(sec.isin) from SPSecuritiesPlannings as secplan right outer join secplan.security as sec "+ 
				" where sec.auditor.deleted=:isDeleted and secplan.planning.id=:idPlanification and isin is not null");
			
			query.setParameter("isDeleted",false);
			query.setParameter("idPlanification",planificationId);
			query.setReadOnly(true);

			reply=query.list();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resourceFull.getCurrentInstance()), LogPlanningInfo.PLANNINGS_GET_ISINES, new Object[]{reply==null?0:reply.size(),planificationId},null);
		}catch(Exception e){
			logger.error("Error getting Information for planification with id "+planificationId+ ": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		logger.debug("Found "+reply==null?0:reply.size()+" securities for planification "+planificationId);
		return reply;
	}
	
	@SuppressWarnings("unchecked")
	protected List<String> getTICKERSecurities(Long planificationId) throws FPMException{
		StatelessSession statelessSession=null;
		List<String> reply=null;
		Query query=null;
		SDMConfigManager manager=null;
		Session session=null;
		logger.debug("Getting Tickers for planification with id "+planificationId);
		try{
			
			session=this.resourceFull.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			statelessSession=resource.getCurrentInstance();

			query=statelessSession.createQuery("select distinct(sec.market.ticker) from SPSecuritiesPlannings as secplan right outer join secplan.security as sec "+ 
				" where sec.auditor.deleted=:isDeleted and secplan.planning.id=:idPlanification and isin is not null");
			
			query.setParameter("isDeleted",false);
			query.setParameter("idPlanification",planificationId);
			query.setReadOnly(true);

			reply=query.list();
			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resourceFull.getCurrentInstance()), LogPlanningInfo.PLANNINGS_GET_ISINES, new Object[]{reply==null?0:reply.size(),planificationId},null);
		}catch(Exception e){
			logger.error("Error getting Information for planification with id "+planificationId+ ": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		logger.debug("Found "+reply==null?0:reply.size()+" securities for planification "+planificationId);
		return reply;
	}
	
	@SuppressWarnings("unchecked")
	protected List<String> getISINContributorsSecurities(Long planificationId) throws FPMException{
		StatelessSession statelessSession=null;
		List<String> reply=null;
		Query query=null;
		SDMConfigManager manager=null;
		Session session=null;
		logger.debug("Getting Contribuitor Information for planification with id "+planificationId);
		try{
			
			session=this.resourceFull.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			statelessSession=resource.getCurrentInstance();
			
			/*query=statelessSession.createQuery("select distinct(sec) from SPSecuritiesPlannings as secplan right outer join secplan.security as sec "+ 
				" where sec.auditor.deleted=:isDeleted and secplan.planning.id=:idPlanification and isin is not null");*/
			
			query=statelessSession.createQuery("select distinct(secplan.security.id) from SPSecuritiesPlannings as secplan "+ 
				" where secplan.security.auditor.deleted=:isDeleted and secplan.planning.id=:idPlanification and isin is not null");
			
			query.setParameter("isDeleted",false);
			query.setParameter("idPlanification",planificationId);
			query.setReadOnly(true);

			List<String> lSecurities= query.list();
			Iterator<String> iteSecs = lSecurities.iterator();
			SPSecurity security=null;
			reply = new ArrayList<String>();
			while (iteSecs.hasNext()){
				security= (SPSecurity)(session.get(SPSecurity.class,iteSecs.next()));
				if (security.getCustomerId()!=null){
					String mf = security.getSecurityDetail().getDynamicTable().get("MIDDLESTRING19");
					reply.add(security.getIsin()+" " + mf + "||1|PRICING_SOURCE|"+security.getCustomerId()+"|");
				}else{
					reply.add(security.getIsin());
				}
			}

			LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resourceFull.getCurrentInstance()), LogPlanningInfo.PLANNINGS_GET_ISINES, new Object[]{reply==null?0:reply.size(),planificationId},null);
		}catch(Exception e){
			logger.error("Error getting Information for planification with id "+planificationId+ ": "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		logger.debug("Found "+reply==null?0:reply.size()+" securities for planification "+planificationId);
		return reply;
	}
	
	@ConfigParam(required=true, group="config", description="StatelessResource")
	public Resource<StatelessSession> getResource(){
		return resource;
	}
	public void setResource(Resource<StatelessSession> _resource){
		this.resource = _resource;
	}

	@ConfigParam(required=true, group="config", description="locale")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true, group="config", description="user")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true, group="config", description="Resource")
	public Resource<Session> getResourceFull() {
		return resourceFull;
	}

	public void setResourceFull(Resource<Session> resourceFull) {
		this.resourceFull = resourceFull;
	}
	
	
}
