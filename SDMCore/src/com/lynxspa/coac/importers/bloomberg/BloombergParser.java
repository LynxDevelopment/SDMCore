package com.lynxspa.coac.importers.bloomberg;

import java.text.ParseException;

import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;

public class BloombergParser implements ImportParserAdapter {

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
		String value=null;

		for(int ic1=4;ic1<17;ic1++){
			value=_splittedFields[ic1];
			value=("N.A.".equals(value.trim()))? "" : value.trim();
			_importedMessage.addField("STD:"+(ic1-4),value);
		}
		reply=_importedMessage.getField("STD:1");
		
		return reply;
	}

	public void parseBodyFields(EventMessageImportBean _importedMessage,String _eventType,String[] _splittedFields) throws ParseException{
		
		int fieldsNumber=0;
		String value=null;
		
		fieldsNumber=Integer.valueOf(_importedMessage.getField("STD:12"));
		for(int ic1=0;ic1<fieldsNumber;ic1++){
			value=_splittedFields[17+(ic1*2)+1];
			value=("N.A.".equals(value.trim()))? "" : value.trim();
			_importedMessage.addField("BDY:"+_eventType+":"+_splittedFields[17+(ic1*2)],value);
		}
	}
	
	public boolean parse(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException{
		
		boolean reply=false;
		String[] splittedFields=null;
		int returnCode=0;
		String eventType=null;
		
		splittedFields=_message.split("\\|");
		if (!splittedFields[0].startsWith("#")){
			returnCode=parseHeaderFields(_importedMessage, splittedFields);
			if(returnCode==0){
				reply=true;
				eventType=parseStandardFields(_importedMessage, splittedFields);
				parseBodyFields(_importedMessage,eventType,splittedFields);
			}
		}
		return reply;
	}
}
