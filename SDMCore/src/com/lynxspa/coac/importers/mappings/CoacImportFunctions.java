package com.lynxspa.coac.importers.mappings;

import java.text.DecimalFormat;

import org.hibernate.Query;
import org.hibernate.Session;

import com.lynxit.fpm.functionlibraries.FunctionLibrarySupport;
import com.lynxit.fpm.resources.Resource;
import com.lynxit.fpm.resources.ResourceId;
import com.lynxit.fpm.resources.ResourcesManager;
import com.lynxspa.sdm.dictionaries.CASecurityTypes;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.messages.formats.CAMessageType;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.sdm.managers.SDMConfigManager;


public class CoacImportFunctions extends FunctionLibrarySupport{

	public CAEventMessage getEventMessageGroup(String _resourceName,CAEventMessage _message) throws Exception{
		
		CAEventMessage reply=null;
		Session session=null;
		Resource<Session> resource=null;
		Query query=null;
		
		resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
		session=resource.getCurrentInstance();
		query=session.createQuery("from CAEventMessage where auditor.deleted=:deleted and messageGroup is null and eventReference=:eventReference order by id");
		query.setParameter("deleted",false);
		query.setParameter("eventReference", _message.getEventReference());
		query.setMaxResults(1);
		reply=(CAEventMessage)query.uniqueResult();
		
		return reply;
	}

	public CAEventMessage getPreviousEventMessage(String _resourceName,CAEventMessage _message) throws Exception{
		
		CAEventMessage reply=null;
		Session session=null;
		Resource<Session> resource=null;
		Query query=null;
		
		resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
		session=resource.getCurrentInstance();
		query=session.createQuery("from CAEventMessage where auditor.deleted=:deleted and messageGroup is null and eventMessageReference=:previousReference order by id");
		query.setParameter("deleted",false);
		query.setParameter("previousReference", _message.getPreviousEventMessageReference());
		query.setMaxResults(1);
		reply=(CAEventMessage)query.uniqueResult();
		
		return reply;
	}

	public String getImportMessageField(EventMessageImportBean importedMessage,String _fieldName){
		
		String reply=null;
		
		reply=importedMessage.getField(_fieldName);
		
		return reply;
	}

	public boolean isStandardSecurityType(String _securityType) throws Exception{
		
		boolean reply=false;
		
		try{
			if(_securityType!=null){
				CASecurityTypes.valueOf(_securityType.trim().toUpperCase());
				reply=true;
			}
		}catch(Throwable e){}
		
		return reply;
	}
	
	public String getSecurityProviderIdField(String _resourceName,String _provider) throws Exception{
		
		String reply=null;
		Session session=null;
		Resource<Session> resource=null;
		
		try{
			resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
			session=resource.getCurrentInstance();
			reply=String.valueOf(SDMConfigManager.getInstance().getSecurityProviderIdField(session,_provider));
		}catch(Exception e){
			throw e;
		}
		
		return reply;
	}

	public CAMessageType getMessageType(String _resourceName,CAFormat _format,String _type) throws Exception{
		
		CAMessageType reply=null;
		Session session=null;
		Resource<Session> resource=null;
		
		try{
			resource=ResourcesManager.getInstance().getResource(new ResourceId<Session>(_resourceName));
			session=resource.getCurrentInstance();
			reply=SDMConfigManager.getInstance().getMessageType(session,_format.getCode(),_type);
		}catch(Exception e){
			throw e;
		}
		
		return reply;
	}

	public String getMessageFormatId(CAMessageType _messageType) throws Exception{
		
		String reply=null;
		
		try{
			reply=_messageType.getId().getFormat().getId();
		}catch(Exception e){
			throw e;
		}
		
		return reply;
	}
	
	public Integer firstBlank(final String _value){
		
		return _value.indexOf(' ');
	}
	
	public String paddDouble(Double value, String pattern){
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String reply = decimalFormat.format(value);
		return reply;
	}
	
	public String paddString(String value, int length){
		String reply = String.format("%1$#" + length + "s", value);
		return reply.substring(0, length);
	}

}
