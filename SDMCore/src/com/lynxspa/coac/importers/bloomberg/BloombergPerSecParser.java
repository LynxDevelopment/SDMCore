package com.lynxspa.coac.importers.bloomberg;

import java.text.ParseException;
import java.util.List;

import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;

public class BloombergPerSecParser implements ImportParserAdapter {

	public static final String HEADER_IDENTIFIER="SECURITIES";
	public static final String NOT_AVALIABLE_DATA="N.A.";
	public static final String NOT_DOWNLOADABLE_DATA="N.D.";
	public static final String NOT_SUSCRIBED_DATA="N.S.";
	public static final String FIELD_UNKNOWN="FLD UNKNOWN";
	public static final String EVENT_TYPE_CUPON="CPN";
	public static final String OPERATION_REPEAT="R";
	
	
	private List<String> messageFieldNames=null;
	
	
	public BloombergPerSecParser(List<String> _messageFieldNames){
		this.messageFieldNames=_messageFieldNames;
	}
	
	protected String cleanBloombergValue(final String _value){
	
		String reply=_value;
		
		if(reply!=null){
			reply=reply.trim();
			reply=(((NOT_AVALIABLE_DATA.equals(reply))||(NOT_DOWNLOADABLE_DATA.equals(reply))||(NOT_SUSCRIBED_DATA.equals(reply))||(FIELD_UNKNOWN.equals(reply)))? "" : reply);
		}else{
			reply="";
		}
		
		return reply;
	}
	
	protected int parseHeaderFields(EventMessageImportBean _importedMessage,String[] _splittedFields,int _offset) throws ParseException{
		
		int reply=0;
		
		for(int ic1=0;ic1<3;ic1++){
			_importedMessage.addField("HDR:"+ic1, _splittedFields[ic1]);
		}
		if((_splittedFields[1]).matches("^\\d*$"))
			reply=Integer.valueOf(_splittedFields[1]);
		
		return reply;
	}

	protected String parseStandardFields(EventMessageImportBean _importedMessage,String[] _splittedFields,int _offset) throws ParseException{
	
		String reply=null;
		int it1=0;
		
		if((it1=this.messageFieldNames.indexOf("CPN_TYP"))>0){
			final String cuponTypeValue=cleanBloombergValue(_splittedFields[it1]);
			if(cuponTypeValue.length()>0)
				reply=EVENT_TYPE_CUPON;
		}
		if(reply==null)
			throw new ParseException("Unknown event type",_offset);
		_importedMessage.addField("STD:0",reply);
		_importedMessage.addField("STD:1",OPERATION_REPEAT);

		return reply;
	}

	protected void parseBodyFields(EventMessageImportBean _importedMessage,String _eventType,String[] _splittedFields,int _offset) throws ParseException{
		
		String value=null;
		
		for(int ic1=3;ic1<_splittedFields.length;ic1++){
			value=cleanBloombergValue(_splittedFields[ic1]);
			if(value.length()>0){
				final String fieldName="BDY:"+_eventType+":"+this.messageFieldNames.get(ic1);
				if("BDY:CPN:BH_MULTI_CPN_SCHEDULE".equals(fieldName)){
					parseBodyBulkHeader(_importedMessage,_eventType,fieldName,value);
				}else if("BDY:CPN:MULTI_CPN_SCHEDULE".equals(fieldName)){
					parseBodyBulkField(_importedMessage,_eventType,fieldName,value);
				}else if("BDY:CPN:BH_DES_CASH_FLOW".equals(fieldName)){
					parseBodyBulkHeader(_importedMessage,_eventType,fieldName,value);
				}else if("BDY:CPN:DES_CASH_FLOW".equals(fieldName)){
					parseBodyBulkField(_importedMessage,_eventType,fieldName,value);
				}else{
					_importedMessage.addField(fieldName,value);
				}
			}
		}
	}
	
	protected void parseBodyBulkHeader(EventMessageImportBean _importedMessage,String _eventType,String _fieldName,String _fieldValue) throws ParseException{
		
		final String[] splittedSubFields=_fieldValue.split(String.valueOf(_fieldValue.charAt(0)));
		final int rows=Integer.parseInt(splittedSubFields[2]);
		final int columns=Integer.parseInt(splittedSubFields[3]);
		final int columnSize=columns*2;
		for(int ic1=0;ic1<rows;ic1++){
			final int rowOffset=ic1*columnSize;
			for(int ic2=0;ic2<columns;ic2++){
				final int columnOffset=rowOffset+ic2*2;
				_importedMessage.addField(_fieldName+"("+ic2+")",splittedSubFields[5+columnOffset]);
			}
		}
	}
	
	protected void parseBodyBulkField(EventMessageImportBean _importedMessage,String _eventType,String _fieldName,String _fieldValue) throws ParseException{
		
		final String[] splittedSubFields=_fieldValue.split(String.valueOf(_fieldValue.charAt(0)));
		final int rows=Integer.parseInt(splittedSubFields[2]);
		final int columns=Integer.parseInt(splittedSubFields[3]);
		final int columnSize=columns*2;
		for(int ic1=0;ic1<rows;ic1++){
			final int rowOffset=ic1*columnSize;
			for(int ic2=0;ic2<columns;ic2++){
				final int columnOffset=rowOffset+ic2*2;
				_importedMessage.addField(_fieldName+"("+ic1+"):TYPE("+ic2+")",splittedSubFields[4+columnOffset]);
				_importedMessage.addField(_fieldName+"("+ic1+"):VALUE("+ic2+")",splittedSubFields[5+columnOffset]);
			}
		}
	}
	
	protected void recoverFieldNames(EventMessageImportBean _importedMessage,String[] _splittedFields,int _offset) throws ParseException{
		
		for(int ic1=0;ic1<3;ic1++)
			_importedMessage.addField("HDR:"+ic1, _splittedFields[ic1]);
		for(int ic1=3;ic1<_splittedFields.length;ic1++)
			_importedMessage.addField("BDY:"+(ic1-3), _splittedFields[ic1]);
	}

	public boolean parse(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException{
		
		boolean reply=false;
		String[] splittedFields=null;
		int returnCode=0;
		String eventType=null;
		
		if (!_message.startsWith("#")){
			splittedFields=_message.split("\\|");
			if(_message.startsWith(HEADER_IDENTIFIER)){
				recoverFieldNames(_importedMessage,splittedFields,_offset);
			}else{
				returnCode=parseHeaderFields(_importedMessage, splittedFields,_offset);
				if(returnCode==0){
					reply=true;
					eventType=parseStandardFields(_importedMessage, splittedFields,_offset);
					parseBodyFields(_importedMessage,eventType,splittedFields,_offset);
				}
			}
		}
		
		return reply;
	}
}
