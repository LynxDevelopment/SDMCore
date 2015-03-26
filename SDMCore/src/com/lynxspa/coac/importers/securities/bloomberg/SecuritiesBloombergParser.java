package com.lynxspa.coac.importers.securities.bloomberg;

import java.text.ParseException;

import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;

public class SecuritiesBloombergParser  implements ImportParserAdapter {

	public int parseHeaderFields(EventMessageImportBean _importedMessage,String[] _splittedFields) throws ParseException{
		
		int reply=0;
		
		for(int ic1=0;ic1<4;ic1++){
			_importedMessage.addField("HDR:"+ic1, _splittedFields[ic1]);
		}
		reply=Integer.valueOf(_splittedFields[3]);
		
		return reply;
	}

	public String parseStandardFields(EventMessageImportBean _importedMessage,String[] _splittedFields) throws ParseException{
	
		String reply="";
		
		for(int ic1=4;ic1<17;ic1++){
			_importedMessage.addField("STD:"+(ic1-4), _splittedFields[ic1]);
		}
		reply=_importedMessage.getField("STD:1");
		
		return reply;
	}

	public void parseBodyFields(EventMessageImportBean _importedMessage,String _eventType,String[] _splittedFields) throws ParseException{
		
		int fieldsNumber=0;

		for(int ic1=0;ic1<fieldsNumber;ic1++){
			_importedMessage.addField("BDY:"+_eventType+":"+_splittedFields[17+(ic1*2)],_splittedFields[17+(ic1*2)+1]);
		}
	}
	
	public boolean parse(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException{
		
		boolean reply=false;
		String[] splittedFields=null;
		int returnCode=0;
		String eventType=null;
		
		splittedFields=_message.split("\\|");
		returnCode=parseHeaderFields(_importedMessage, splittedFields);
		if(returnCode!=300){
			reply=true;
			eventType=parseStandardFields(_importedMessage, splittedFields);
			parseBodyFields(_importedMessage,eventType,splittedFields);
		}
		
		return reply;
	}
}
