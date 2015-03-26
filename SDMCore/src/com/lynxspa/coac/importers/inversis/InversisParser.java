package com.lynxspa.coac.importers.inversis;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import com.ibm.icu.util.StringTokenizer;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;

public class InversisParser implements ImportParserAdapter {

	public boolean parse(EventMessageImportBean _importedMessage, String _message, int _offset) throws ParseException {
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
		StringTokenizer tokenizer=null;
		String parseLine=null;
		try{
			tokenizer=new StringTokenizer(_message,"\n");
			parseLine = tokenizer.nextToken();
			parseInformation(_importedMessage, parseLine);
			if (parseLine.length()>1279){
				InversisParser.setInversisValue(_importedMessage,_message,"INV",1280,4000,"X(2721)","INV:1280-4000:");
			}
			if(tokenizer.hasMoreTokens()){
				parseLine = tokenizer.nextToken();
				parseOrdenDia(_importedMessage, parseLine);
			}

		}catch (ParseException e) {
			throw e;
		}catch (Exception e) {
			throw new ParseException(e.getMessage(),0);
		}
	
		return reply;
	}
	
	protected void parseInformation(EventMessageImportBean _importedMessage, String _message) throws ParseException{
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1,4,"X(04)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",5,14,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",15,22,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",23,37,"X(15)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",38,46,"X(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",47,48,"X(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",49,98,"X(50)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",99,107,"9(9)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",108,119,"X(12)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",120,149,"X(30)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",150,159,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",160,162,"X(03)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",163,172,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",173,184,"X(12)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",185,193,"9(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",194,223,"X(30)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",224,233,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",234,236,"X(03)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",237,246,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",247,274,"X(28)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",275,302,"X(28)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",303,342,"X(40)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",343,364,"9(22)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",365,382,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",383,390,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",391,412,"9(22)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",413,433,"9(21)V9(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",434,436,"X(03)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",437,454,"9(18)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",455,472,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",473,480,"9(8)V9(04)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",481,498,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",499,516,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",517,524,"9(8)V9(04)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",525,542,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",543,560,"9(18)V9(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",561,568,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",569,576,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",577,584,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",585,624,"X(40)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",625,634,"X(10)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",635,649,"X(15)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",650,658,"X(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",659,660,"X(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",661,710,"X(50)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",711,718,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",719,726,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",727,734,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",735,742,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",743,764,"9(22)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",765,786,"9(22)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",787,807,"9(21)V9(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",808,828,"9(21)V9(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",829,849,"9(21)V9(09)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",850,861,"X(12)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",862,863,"X(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",864,885,"9(22)V9(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",886,893,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",894,901,"X(08)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",902,1156,"X(255)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1157,1206,"X(50)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1207,1256,"X(50)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1257,1260,"X(04)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1261,1264,"X(04)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1265,1267,"9(3)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1268,1269,"X(02)",null);
		InversisParser.setInversisValue(_importedMessage,_message,"INV",1270,1278,"9(9)",null);
		if (_message.length()>1278)
			InversisParser.setInversisValue(_importedMessage,_message,"INV",1279,1279,"X(01)",null);
	}
	
	protected void parseOrdenDia(EventMessageImportBean _importedMessage, String _message) throws ParseException{

		_importedMessage.addField("INV:ORDENDIA",_message);

	}
	
	public static String setInversisValue(EventMessageImportBean _importedMessage,String _message,String _key,int _start,int _end,String _format, String _path) throws ParseException{
		
		String reply=null;
		String path=null;
		try{
			reply=getInversisValue(_message,_start,_end,_format);
			if(reply.length()!=0){
				path = (_path==null?_key+":"+_start+"-"+_end+":":_path);
				_importedMessage.addField(path,reply);
			}else{
			}
		}catch(Exception e){
			throw new ParseException("Parsing exception: "+e.getMessage(),_start);
		}
		
		return reply;
	}
	
	public static String getInversisValue(String _message,int _start,int _end,String _format){

		String reply=null;
		int integerEnd=0;
		DecimalFormat decimalFormat=null;
		DecimalFormatSymbols dformater_rules = null;
		int decimalSize=0;
		
		reply=_message.substring(_start-1,_end);
		switch(_format.charAt(0)){
			case 'X':	reply=reply.replace('','\n').trim();
						break;
			case '9':	if((integerEnd=_format.indexOf(')'))<_format.length()-1){
							if (_format.contains("V9")){
								decimalSize = (Integer.parseInt(_format.substring(_format.lastIndexOf(")")-2,_format.lastIndexOf(")"))));
							}
							integerEnd= Integer.parseInt(_format.substring(2,integerEnd)) - decimalSize;
							dformater_rules = new DecimalFormatSymbols ();
							dformater_rules.setDecimalSeparator ('.');
							decimalFormat=new DecimalFormat("##################0.0####################",dformater_rules);
							Double d = Double.parseDouble(reply.substring(0,integerEnd)+'.'+reply.substring(integerEnd+1));
							reply=decimalFormat.format(d);
						}else{
							reply=String.valueOf(Long.parseLong(reply));
						}
						break;
		}
		return reply;
	}
}

