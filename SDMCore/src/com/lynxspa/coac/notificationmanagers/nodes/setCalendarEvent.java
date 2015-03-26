package com.lynxspa.coac.notificationmanagers.nodes;

import java.util.GregorianCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.admin.config.annotations.ConfigParam;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean;
import com.lynxspa.coac.notificationmanagers.utils.iCalUtils;
import com.lynxspa.exception.FPMException;

/**
 * 
 * 
 * 
 * @author Jose Luis
 *
 */
@NodeBeautifier(description="Set Calendar Event", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class setCalendarEvent extends SingleExitInternalNodeSupport <NotificationEventBean,NotificationEventBean>{
	private String timeZone=null;
	private int hourToBeginEven=0;//Hora en la que comenzara el evento
	private int evenDuration=0;//Duración del evento
	private int previosHoursForAlarm=0;//Cuantas horas antes del evento se quiere lanzar el la alarma.
	@Override
	protected NotificationEventBean perform(NotificationEventBean message) throws Exception {
		NotificationEventBean reply = null;
		String asunto = null;
		java.util.Calendar eventDate=null;
		
		reply = message;
		eventDate = java.util.Calendar.getInstance();
		eventDate.setTime(message.getEventDate());
		
        asunto = message.getEventGroup().getMasterEvent().getEventType().getName()+ " "+ 
        	message.getEventGroup().getMasterEvent().getSecurity().getName();
        
        reply.setSubject(asunto);
        reply.setEventCalendar(generateEventCalendar(eventDate,asunto));
        
		return reply;
	}

	

	private Calendar generateEventCalendar(java.util.Calendar _eventDate, String _asunto) throws Exception{
		Calendar reply = null;

	    UidGenerator ug = new UidGenerator("uidGen");
	    
		TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
		TimeZone icalTimeZone = registry.getTimeZone(this.timeZone);
		VTimeZone tz = icalTimeZone.getVTimeZone();
		
		java.util.Calendar startDate = new GregorianCalendar();
		startDate.setTimeZone(icalTimeZone);
		startDate.set(java.util.Calendar.MONTH, _eventDate.get(java.util.Calendar.MONTH));
		startDate.set(java.util.Calendar.DAY_OF_MONTH,_eventDate.get(java.util.Calendar.DAY_OF_MONTH));
		startDate.set(java.util.Calendar.YEAR,_eventDate.get(java.util.Calendar.YEAR));
		startDate.set(java.util.Calendar.HOUR_OF_DAY,this.hourToBeginEven);
		startDate.set(java.util.Calendar.MINUTE,0);
		
		/*startDate.set(java.util.Calendar.MONTH, java.util.Calendar.SEPTEMBER);
		startDate.set(java.util.Calendar.DAY_OF_MONTH,21);
		startDate.set(java.util.Calendar.YEAR,2010);
		startDate.set(java.util.Calendar.HOUR_OF_DAY,8);
		startDate.set(java.util.Calendar.MINUTE,0);*/
		
		VEvent reminder = new VEvent(new DateTime(startDate.getTime()),setDuration(0,this.evenDuration,0,0),_asunto);
		iCalUtils.addProperty(reminder,tz.getTimeZoneId());
		iCalUtils.addProperty(reminder,ug.generateUid());
		if (this.previosHoursForAlarm!=0)
			reminder.getAlarms().add(setAlarm(reminder, _asunto, icalTimeZone));
			
		reply = new Calendar();
		reply.getProperties().add(new ProdId("-//LYNX//FPM iCalendar 1.0//ES"));
		reply.getProperties().add(Version.VERSION_2_0);
		reply.getProperties().add(CalScale.GREGORIAN);
		reply.getProperties().add(Method.REQUEST);
		reply.getProperties().add(ug.generateUid());
		iCalUtils.addComponent(reply, reminder);
		//reply.getComponents().add(setAlarm(reminder, _asunto, icalTimeZone));
		
		return reply;
	 }
	private VAlarm setAlarm(final VEvent ev, String _summary, TimeZone _iCalTimeZone) throws FPMException {

		VAlarm alarm = new VAlarm(setDuration(0,this.previosHoursForAlarm,0,0));
		
		iCalUtils.addProperty(alarm,Action.DISPLAY);
		iCalUtils.addProperty(alarm, new Summary(ev.getSummary().toString()));
		
		return alarm;
	}

	
	/*private RRule setRepeat(VEvent event, TimeZone _iCalTimeZone ) throws Exception{
		RRule reply=null;
		Recur recur = null;

		java.util.Calendar endDate = new GregorianCalendar();
		endDate.setTimeZone(_iCalTimeZone);
		endDate.set(java.util.Calendar.MONTH, java.util.Calendar.SEPTEMBER);
		endDate.set(java.util.Calendar.DAY_OF_MONTH,20);
		endDate.set(java.util.Calendar.YEAR,2010);
		endDate.set(java.util.Calendar.HOUR_OF_DAY,12);
		endDate.set(java.util.Calendar.MINUTE,54);
		
		recur = new Recur(Recur.MINUTELY,new Date(endDate.getTime()));
		
		reply = new RRule(recur);
		
		return reply;
	}*/
	
	
	private Dur setDuration (int days, int hours, int minutes, int seconds){
		Dur reply = null;
		reply = new Dur (days,hours,minutes,seconds);
		
		return reply;
	}
	@ConfigParam(group="Config",required=true,defaultValue="Europe/Madrid",description="Zona Horaria")
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@ConfigParam(group="Config",name="HourToBeginEven", required=true,defaultValue="8",description="Hour when the even begins")
	public int getHourToBeginEven() {
		return hourToBeginEven;
	}
	public void setHourToBeginEven(int hourToBeginEven) {
		this.hourToBeginEven = hourToBeginEven;
	}

	@ConfigParam(group="Config",name="EvenDuration", required=true,defaultValue="12",description="Duration of the even")
	public int getEvenDuration() {
		return evenDuration;
	}
	public void setEvenDuration(int evenDuration) {
		this.evenDuration = evenDuration;
	}

	@ConfigParam(group="Config",name="AlarmStart", required=true,defaultValue="-18",description="Hours before the begining of the even where alarm starts")
	public int getPreviosHoursForAlarm() {
		return previosHoursForAlarm;
	}
	public void setPreviosHoursForAlarm(int previosHoursForAlarm) {
		this.previosHoursForAlarm = previosHoursForAlarm;
	}
	
	
}
