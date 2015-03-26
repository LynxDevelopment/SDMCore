package com.lynxspa.coac.notificationmanagers.beans;

import java.util.Date;

import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;

import net.fortuna.ical4j.model.Calendar;

import com.lynxspa.sdm.entities.events.CAEventGroup;

public class NotificationEventBean {
	CAEventGroup eventGroup=null;
    Date eventDate=null;
    Calendar eventCalendar=null;
    String subject=null;
    Multipart multipart=null;
    Address [] emailAddresses=null; 
                     
	public CAEventGroup getEventGroup() {
		return eventGroup;
	}
	public void setEventGroup(CAEventGroup eventGroup) {
		this.eventGroup = eventGroup;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public Calendar getEventCalendar() {
		return eventCalendar;
	}
	public void setEventCalendar(Calendar eventCalendar) {
		this.eventCalendar = eventCalendar;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Multipart getMultipart() {
		return multipart;
	}
	public void setMultipart(Multipart multipart) {
		this.multipart = multipart;
	}
	public Address[] getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(InternetAddress[] emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	
}
