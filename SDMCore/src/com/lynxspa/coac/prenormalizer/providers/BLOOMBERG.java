package com.lynxspa.coac.prenormalizer.providers;

import org.hibernate.Session;

import com.lynxspa.sdm.dictionaries.CABasicDomains;
import com.lynxspa.sdm.dictionaries.CAType;
import com.lynxspa.sdm.dictionaries.securities.securityfinancialassets.CASecurityFinancialAssets;
import com.lynxspa.sdm.entities.events.messages.CAEventMessage;
import com.lynxspa.sdm.entities.events.types.CAEventType;
import com.lynxspa.sdm.managers.SDMConfigManager;
import com.lynxspa.sdm.processors.prenormalize.plugin.DefaultPrenormalizerPlugin;
import com.lynxspa.entities.securities.SPSecurity;
import com.lynxspa.entities.securities.SPVirtualSecurity;
import com.lynxspa.exception.FPMException;

public class BLOOMBERG extends DefaultPrenormalizerPlugin{

	@Override
	public CAEventType prenormalizeEventType(Session _session,CAEventMessage _message, String _user, String _locale) throws FPMException {

		CAEventType reply = null;
		String eventTypeName=_message.getEventType();

		if(_message.getOriginName().endsWith("rf.cax"))
			eventTypeName+="_RF";		
		final String normalizedEventType=SDMConfigManager.getInstance().getDomainNorm(_session,CABasicDomains.EVENTTYPE.getCode(),_message,eventTypeName);
		if(normalizedEventType!=null){
			reply=(CAEventType)_session.load(CAEventType.class, normalizedEventType);
		}else{
			reply=(CAEventType)_session.load(CAEventType.class, CAType.OTHR.getCode());
		}
		
		return reply;
	}

	@Override
	public boolean isMainMarket(Session _session, CAEventMessage _message,SPVirtualSecurity _security, String _user, String _locale) throws FPMException {
		
		boolean reply=false;
		
		if((_security instanceof SPSecurity)&&(((SPSecurity)_security).getTicker()!=null)){
			if((((SPSecurity)_security).getSecFinancialAssets()!=null)&&(CASecurityFinancialAssets.DEBT.getId().equals(((SPSecurity)_security).getSecFinancialAssets().getId()))){
				reply = true;
			}else{
				String st1=_message.getField("HDR:0");
				if (st1.indexOf(' ')!=-1 && st1.indexOf(' ')>1){//Bulk
					st1=st1.substring(0,8).trim()+st1.substring(8,10);
					reply=((SPSecurity)_security).getTicker().equals(st1);
				}else{//Persecurity
					reply=true;
				}
			}
		}
		
		return reply;
	}
}
