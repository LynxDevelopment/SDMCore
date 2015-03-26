package com.lynxspa.coac.plannings.nodes;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.impl.calendar.BaseCalendar;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.OutputConnectable;
import com.lynxit.fpm.nodes.support.InternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.coac.plannings.beans.ControlPlanningsBean;
import com.lynxspa.coac.plannings.dictionaries.LogErrorDict;
import com.lynxspa.coac.plannings.logs.LogPlanningInfo;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.entities.plannings.SPPlanningProcess;
import com.lynxspa.exception.FPMException;


/**
 * 
 * @author joseluis.llorente
 *
 */
@NodeBeautifier(description="Control Execution Process", category="Corporateactionscore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class ControlExecutionPlanning <T extends ControlPlanningsBean> extends InternalNodeSupport <T> {
	
	public static final Logger logger = Logger.getLogger(ControlExecutionPlanning.class.getName());
	private OutputConnectable< ? super T> nodeConnectedToOut_;
	protected String locale=null;
	protected String user=null;
	protected Resource<Session> resource=null;
	private static final int ELAPSED_TIME = 30000;
	@Override
	protected void processMessage(T message) throws Exception{
		String cron = null;
		Date lastExecution=null;
		Date actualDate=null;
		Date nextExecutionDate=null;
		SDMConfigManager manager=null;
		Session session=null;
		long processingTime=0l;
		CronTrigger trigger_ = null;
		
		try {
			session=this.resource.getCurrentInstance();
			manager = SDMConfigManager.getInstance();
			
			trigger_ = new CronTrigger(message.getCronn(), Scheduler.DEFAULT_GROUP);
			cron = message.getCronn();
			trigger_.setCronExpression(cron);
			
			lastExecution = message.getLastExecutionDate();
			actualDate = Calendar.getInstance().getTime();
			if (lastExecution==null){
				lastExecution = actualDate;
			}
			
			trigger_.computeFirstFireTime(new BaseCalendar());
			nextExecutionDate = trigger_.getNextFireTime();
			
			if (message.isManual()){
				message.setLastExecutionDate(actualDate);
			}else{
				message.setLastExecutionDate(nextExecutionDate);
			}
			
			if (message.isManual() || (lastExecution.getTime() < nextExecutionDate.getTime() && (nextExecutionDate.getTime()-actualDate.getTime()<ELAPSED_TIME))){
				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.PROCESS_START, new Object[]{message.getName(), message.getId(), message.isManual(), actualDate},null);
				processingTime=System.currentTimeMillis();
				getNodeConnectedToOut().process(message);
				processingTime=System.currentTimeMillis()-processingTime;
				LogUtils.createLog(session,this.user,manager.getBundleName(),new Locale(this.locale), manager.getApplication(resource.getCurrentInstance()), LogPlanningInfo.PROCESS_END, new Object[]{message.getName(), processingTime},null);
			}
			
		} catch (ParseException pe) {
			logger.error("Cron expression '"+cron+"' not correct for planning "+ message.getName()+": "+pe);
			LogUtils.createLog(session, this.user, manager.getBundleName(), new Locale(this.locale), manager.getApplication(session), LogErrorDict.PLANNING_INCORRECT_CRON, new Object[]{cron,message.getName()},pe);

		}catch (Exception e) {
			throw new FPMException();
		}finally{
			try {
				if (message.isManual()){
					SPPlanningProcess planningProcess  = (SPPlanningProcess) session.load(SPPlanningProcess.class, message.getId());
					planningProcess.setManual(false);
				}
			} catch (Exception e) {
				throw new FPMException();
			}
		}
	}
	
	public void connectNodeToOut(OutputConnectable< ? super T> node){
        nodeConnectedToOut_ = node;
    }

	public OutputConnectable< ? super T> getNodeConnectedToOut(){
        return nodeConnectedToOut_;
    }

	@ConfigParam(required=true,description="locale",group="config")
	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@ConfigParam(required=true,description="User",group="config")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@ConfigParam(required=true,description="Resource",group="config")
	public Resource<Session> getResource() {
		return resource;
	}

	public void setResource(Resource<Session> resource) {
		this.resource = resource;
	}
}
