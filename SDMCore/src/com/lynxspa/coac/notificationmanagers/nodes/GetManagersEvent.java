package com.lynxspa.coac.notificationmanagers.nodes;

import java.util.Iterator;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean;
import com.lynxspa.exception.FPMException;
import com.lynxspa.exception.dict.BasicErrorDict;
/**
 * 
 * 
 * 
 * @author Jose Luis
 *
 */
@NodeBeautifier(description="Get Managers Event", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GetManagersEvent extends SingleExitInternalNodeSupport <NotificationEventBean,NotificationEventBean>{
	private Resource<StatelessSession> resourceStateless;
	private String user;
	private String locale=null;
	
	private static final Logger logger = Logger.getLogger(GetManagersEvent.class);
	
	@Override
	protected NotificationEventBean perform(NotificationEventBean message) throws Exception {
		NotificationEventBean reply=null;
		List<String> emails = null;
		Iterator<String> emailsIte=null;
		InternetAddress [] emailAddresses=null; 
		int numEmail = 0;
		try{
			reply = message;
			final StatelessSession session=resourceStateless.getCurrentInstance();
	    	final Query query=session.createQuery("select distinct(customer.manager.email) from SPSecurityPortfolio where auditor.deleted=:deleted and security.id=:securityId");
	    	query.setParameter("deleted", false);
	    	query.setLong("securityId", reply.getEventGroup().getSecurity().getId());
	    	query.setReadOnly(true);
	
	    	emails = query.list();
	    	emailAddresses = new InternetAddress[emails.size()];
	    	emailsIte = emails.iterator();
	    	while (emailsIte.hasNext()){
	    		emailAddresses[numEmail]=new InternetAddress(emailsIte.next());
	    		numEmail++;
	    	}
	    	
	    	reply.setEmailAddresses(emailAddresses);
		} catch (FPMException e) {
			logger.error("GetManagersEvent - FPMException getting emails: "+e);
			throw e;
		} catch (Exception e) {
			logger.error("GetManagersEvent - Exception getting emails: "+e);
			throw new FPMException(BasicErrorDict.UNEXPECTED_ERROR,e);
		}
		return reply;
	}

	@ConfigParam(required=true)
	public String getUser() {
		return user;
	}
	public void setUser(String _user) {
		this.user = _user;
	}
	
	@ConfigParam(required=true)
	public Resource<StatelessSession> getResourceStateless() {
		return resourceStateless;
	}
	public void setResourceStateless(Resource<StatelessSession> _resourceStateless) {
		this.resourceStateless = _resourceStateless;
	}

	@ConfigParam(required=true)
	public String getLocale() {
		return locale;
	}
	public void setLocale(String _locale) {
		this.locale = _locale;
	}
}
