package com.lynxspa.coac.notificationmanagers.nodes;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxit.fpm.nodes.support.SingleExitInternalNodeSupport;
import com.lynxspa.coac.notificationmanagers.beans.NotificationEventBean;

/**
 * 
 * 
 * 
 * @author Jose Luis
 *
 */
@NodeBeautifier(description="Generate Message Body", category="CorporateActionsCore", smallIcon = "../../../../lynxit/fpm/nodes/icons/certificate_16.gif", largeIcon = "../../../../lynxit/fpm/nodes/icons/certificate_32.gif")
public class GenerateMessageBody extends SingleExitInternalNodeSupport<NotificationEventBean,NotificationEventBean> {

	@Override
	protected NotificationEventBean perform(NotificationEventBean _message) throws Exception {
		Multipart multipart = new MimeMultipart("alternative");
		String messageBody=null;
		NotificationEventBean reply= null;
		
		reply = _message;
        //part 1, html text
		//messageBody=_message.getSubject();
		messageBody="S'ha notificat d'un nou event corporatiu, accedeixi a l'aplicació COAC per consultar-ne tots els detalls.\n\n"+ 
			"Se ha notificado de un nuevo hecho corporativo, accede a la aplicación COAC para consultar todos los detalles.\n\n"+
			"A new corporate event has been announced. Please, check all details in COAC application.\n\n"+
			"En noveau event corporatife a eté announcé. Si-vous-plaît, acceder a l'aplicative COAC pour tous les détails.";

        BodyPart messageBodyPart = buildHtmlTextPart(messageBody);
        multipart.addBodyPart(messageBodyPart);

        // Add part two, the calendar
        BodyPart calendarPart = new MimeBodyPart();
        calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
        //calendarPart.setContent(cal.toString(), "text/calendar;method=CANCEL");
        calendarPart.setContent(reply.getEventCalendar().toString(), "text/calendar;method=REQUEST;charset=\"UTF-8\"");
        multipart.addBodyPart(calendarPart);

        //Put the multipart in message
        reply.setMultipart(multipart);
		return reply;
	}

	private BodyPart buildHtmlTextPart(String messageBody) throws MessagingException {
        MimeBodyPart descriptionPart = new MimeBodyPart();
        //Note: even if the content is spcified as being text/html, outlook won't read correctly tables at all
        // and only some properties from div:s. Thus, try to avoid too fancy content
        String content = messageBody;
        //"<font size='2'>simple meeting Nadal invitation</font>";
        descriptionPart.setContent(content, "text/html; charset=utf-8");
        return descriptionPart;
    }
}
