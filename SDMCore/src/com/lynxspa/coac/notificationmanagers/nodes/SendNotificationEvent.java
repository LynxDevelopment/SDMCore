package com.lynxspa.coac.notificationmanagers.nodes;

import java.util.Properties;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxspa.sdm.entities.events.CAEventGroup;
import com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean;
import com.lynxspa.exception.FPMException;

/**
 * 
 * 
 * 
 * @author Jose Luis
 *
 */
@NodeBeautifier(description="Send Notification Event", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class SendNotificationEvent extends SingleExitInternalNodeSupport<NotificationEventBean,CAEventGroup> {

	private String smtp_host=null;
	private String smtp_port=null;
	private String smtp_user=null;
	private String smtp_password=null;
	private String message_from_ad=null;
	private final String  SUBMITTER = "mail.smtp.submitter";
	private final String  AUTH = "mail.smtp.auth";

	@SuppressWarnings("unchecked")
	@Override
	protected CAEventGroup perform(NotificationEventBean _message) throws FPMException {
		CAEventGroup reply = null;
		Authenticator authenticator = null;
		Properties properties = null;
		MimetypesFileTypeMap mimetypes = null;
		MailcapCommandMap mailcap = null;
		MimeMessage message = null;
        try {
        	
	        authenticator = new Authenticator(this.smtp_user, this.smtp_password);
	
	        properties = new Properties();
	        properties.setProperty(SUBMITTER, authenticator.getPasswordAuthentication().getUserName());
	        properties.setProperty(AUTH, "true");
	        properties.setProperty("mail.smtp.host", this.smtp_host);
	        properties.setProperty("mail.smtp.port", this.smtp_port);
	        
	        Session session=javax.mail.Session.getInstance(properties, authenticator);
	        //register the text/calendar mime type
	        mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
	        mimetypes.addMimeTypes("text/calendar ics ICS");
	
	        //register the handling of text/calendar mime type
	        mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
	        mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	
	        message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(this.message_from_ad));
	        message.setSubject(_message.getSubject());
	        message.addRecipients(Message.RecipientType.TO, _message.getEmailAddresses());
	        //Put the multipart in message
	        message.setContent(_message.getMultipart());
	
	        // send the message
	        sendMessage(message,session);
        
		}catch (Throwable e) {
			throw new FPMException(e);
		}
		reply= _message.getEventGroup();
		return reply;
	}

	 private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator(String _username, String _password) {
			String username = _username;
			String password = _password;
			authentication = new PasswordAuthentication(username, password);
		}

        @Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}

    }

	 private void sendMessage (MimeMessage _message, Session session) throws Exception{
		 // send the message
        Transport transport = session.getTransport("smtp");
        transport.connect();
        transport.sendMessage(_message, _message.getAllRecipients());
        transport.close();
	 }
	 
	@ConfigParam(group="SMTP Config", name="SMTP_HOST", required=true,description="SMTP Host")
	public String getSmtp_host() {
		return smtp_host;
	}
	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}

	@ConfigParam(group="SMTP Config",name="SMTP_PORT" ,required=true,description="SMTP Port")
	public String getSmtp_port() {
		return smtp_port;
	}
	public void setSmtp_port(String smtp_port) {
		this.smtp_port = smtp_port;
	}
	
	@ConfigParam(group="SMTP Config", name="SMTP_USER", required=true,description="SMTP User")
	public String getSmtp_user() {
		return smtp_user;
	}
	public void setSmtp_user(String smtp_user) {
		this.smtp_user = smtp_user;
	}

	@ConfigParam(group="SMTP Config", name="SMTP_PASSWORD", required=true,description="SMTP password")
	public String getSmtp_password() {
		return smtp_password;
	}
	public void setSmtp_password(String smtp_password) {
		this.smtp_password = smtp_password;
	}

	@ConfigParam(group="SMTP Config",name="SMTP_FROM", required=true,description="SMTP From")
	public String getMessage_from_ad() {
		return message_from_ad;
	}
	public void setMessage_from_ad(String message_from_ad) {
		this.message_from_ad = message_from_ad;
	}
}
