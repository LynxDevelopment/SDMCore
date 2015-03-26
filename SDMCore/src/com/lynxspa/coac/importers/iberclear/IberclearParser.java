package com.lynxspa.coac.importers.iberclear;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;

public class IberclearParser implements ImportParserAdapter {
	static enum ServiceBodyParser{
		
		IBCS{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",1,3,"X(03)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",4,7,"X(04)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",8,19,"X(12)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",20,21,"X(02)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",22,25,"X(04)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",26,40,"X(15)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",41,52,"X(12)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",53,65,"9(11)V9(02)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",66,73,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",74,81,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",82,89,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",90,97,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",98,115,"9(10)V9(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",116,122,"9(07)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",123,129,"9(07)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",130,135,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",136,141,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",142,147,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",148,153,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",154,161,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",162,169,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",170,170,"X(01)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",171,171,"X(01)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",172,179,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",180,180,"X(01)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",181,185,"9(05)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",186,189,"X(04)");
			}
		},
		IBCN{
			public void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException{
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",1,3,"X(03)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",4,7,"X(04)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",8,19,"X(12)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",20,21,"X(02)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",22,25,"X(04)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",26,40,"X(15)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",41,52,"X(12)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",53,65,"9(11)V9(02)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",66,73,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",74,81,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",82,89,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",90,97,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",98,115,"9(10)V9(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",116,122,"9(07)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",123,129,"9(07)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",130,135,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",136,141,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",142,147,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",148,153,"X(06)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",154,161,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",162,169,"X(08)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",170,170,"X(01)");
				IberclearParser.setIBCValue(_importedMessage,_message,"IBC",171,171,"X(01)");
			}
		};
		public abstract void parseBody(EventMessageImportBean _importedMessage, String _message) throws ParseException;
	} 

	public boolean parse(EventMessageImportBean _importedMessage, String _message,
			int _offset) throws ParseException {
		boolean reply=true;
		
		try{
			parseLine(_importedMessage,_message,_offset);
		}catch (ParseException e) {
			throw e;
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
		
		return reply;
	}
	
	public boolean parseLine(EventMessageImportBean _importedMessage, String _message,int _offset) throws ParseException {
		
		boolean reply=true;
		ServiceBodyParser bodyParser=null;
		
		try{

			try{			
				bodyParser=ServiceBodyParser.valueOf("IBC".concat(_message.substring(170, 171)));
				bodyParser.parseBody(_importedMessage, _message);
			}catch (Exception e) {
				throw new ParseException("Unknown body parser for iberclear service [IBC]",29);
			}
		}catch (ParseException e) {
			throw e;
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
	
		return reply;
	}
	
	public static String setIBCValue(EventMessageImportBean _importedMessage,String _message,String _key,int _start,int _end,String _format) throws ParseException{
		
		String reply=null;
		String path=null;
		try{
			reply=getIBCValue(_message,_start,_end,_format);
			if(reply.length()!=0){
				path = _key+":"+_start+"-"+_end+":";
				_importedMessage.addField(path,reply);
			}else{
				reply=null;
			}
		}catch(Exception e){
			throw new ParseException("Parsing exception: "+e.getMessage(),_start);
		}
		
		return reply;
	}

	public static String getIBCValue(String _message,int _start,int _end,String _format){

		String reply=null;
		int integerEnd=0;
		DecimalFormat format=null;
		
		reply=_message.substring(_start-1,_end);
		switch(_format.charAt(0)){
			case 'X':	reply=reply.replace('','\n').trim();
						break;
			case '9':	if((integerEnd=_format.indexOf(')'))<_format.length()-1){
							integerEnd=Integer.parseInt(_format.substring(2,integerEnd));
							format=new DecimalFormat("##################0.0####################");
							reply=format.format(Double.parseDouble(reply.substring(0,integerEnd)+'.'+reply.substring(integerEnd)));
						}else{
							reply=String.valueOf(Long.parseLong(reply));
						}
						break;
		}
		return reply;
	}

}
