package com.lynxspa.coac.importers.swift;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.ImportParserAdapter;
import com.lynxspa.utils.StringUtils;


public class SwiftParser implements ImportParserAdapter{

	protected int position=0;
	protected Scanner scanner=null;

	
	public SwiftParser() {
		super();
	}
	
	
	protected void parseBlock1(EventMessageImportBean _message,String _block) throws ParseException{

		_message.addField("1:APPID:",String.valueOf(_block.charAt(0)));
		_message.addField("1:SRVID:",_block.substring(1,3));
		_message.addField("1:TERM:",_block.substring(3,15));
		_message.addField("1:SESSN:",_block.substring(15,19));
		_message.addField("1:SEQN:",_block.substring(19,25));
	}

	protected void parseBlock2(EventMessageImportBean _message,String _block) throws ParseException{

		_message.addField("2:IO:",_block.substring(0,1));
		_message.addField("2:MSGT:",_block.substring(1,4));
		switch(_block.charAt(0)){
			case 'I':	_message.addField("2:I:ADD:",_block.substring(4,16));
						_message.addField("2:I:MSGP:",_block.substring(16,17));
						_message.addField("2:I:DMF:",_block.length()>17?_block.substring(17,18):"");//optional field
						_message.addField("2:I:OBP:",_block.length()>18?_block.substring(18,20):"");//optional field
						break;
			case 'O':	_message.addField("2:O:MIR:TIME",_block.substring(4,8));
						_message.addField("2:O:MIR:DATE",_block.substring(8,14));
						_message.addField("2:O:MIR:ADD:",_block.substring(14,26));
						_message.addField("2:O:MIR:SESSN:",_block.substring(26,30));
						_message.addField("2:O:MIR:SEQN:",_block.substring(30,36));
						_message.addField("2:O:DATE:",_block.substring(36,42));
						_message.addField("2:O:TIME:",_block.substring(42,46));
						_message.addField("2:O:MSGP:",_block.substring(46,47));
						break;
			default: throw new ParseException("Unknown application header block type ["+_block+"]",this.position+2);
		}
	}

	protected void parseBlock3(EventMessageImportBean _message,String _block) throws ParseException{

		Scanner blockScanner=null;
		String subBlock=null;

		blockScanner=new Scanner(_block);
		blockScanner.useDelimiter(Pattern.compile("\\{(?=\\d{3}:)|\\}[^}]*\\{(?=\\d{3}:)|}",Pattern.MULTILINE));
		while(blockScanner.hasNext()){
			subBlock=blockScanner.next();
			_message.addField("3:"+subBlock.substring(0,4),subBlock.substring(4));
		}
		blockScanner.close();
	}
	
	protected void parseBlock4Subblocks(EventMessageImportBean _message,String _block) throws ParseException{

		Scanner blockScanner=null;
		String subBlock=null;

		blockScanner=new Scanner(_block);
		blockScanner.useDelimiter(Pattern.compile("\\{(?=\\D{3}:)|\\}[^}]*\\{(?=\\D{3}:)|}",Pattern.MULTILINE));
		while(blockScanner.hasNext()){
			subBlock=blockScanner.next();
			_message.addField("4:"+subBlock.substring(0,4),subBlock.substring(4));
		}
		blockScanner.close();
	}
	
	protected void appendField(EventMessageImportBean _message,String _path,String _value) throws ParseException{
		
		String actualValue=null;
		
		if((actualValue=_message.getField(_path))==null){
			_message.addField(_path, _value);
		}else{
			if(actualValue.endsWith("],")){
				_message.replaceField(_path,"["+_value+"],");
			}else{
				_message.replaceField(_path,"["+actualValue+"],["+_value+"],");
			}
		}
	}
	
	protected void parseBlock4Fin(EventMessageImportBean _message,String _block) throws ParseException{

		Scanner fieldScanner=null;
		String fullField=null;
		StringBuffer path=null;
		String field="";
		String value="";
		String actualPath="";
		HashMap<String, Integer> pathCounter=null;
		int counter=0;

		path=new StringBuffer();
		pathCounter=new HashMap<String, Integer>();
		fieldScanner=new Scanner(_block);
		
		fieldScanner.useDelimiter(Pattern.compile("(\\r\\n|\\n)\\:(?=\\d{2}\\D{1}?\\:)",Pattern.MULTILINE));
		path.append("4:");
		while(fieldScanner.hasNext()){
			fullField=StringUtils.checkNotExist(fieldScanner.next(),"").trim();
			if(fullField.length()>0){
				int position=fullField.indexOf(':');
				field=fullField.substring(0,position);
				value=fullField.substring(position+1,fullField.length());
				if("16R".equals(field)){
					actualPath=path.toString()+fullField+':';
					counter=((pathCounter.get(actualPath)==null)? -1 : pathCounter.get(actualPath));
					counter++;
					path.append(fullField);
					path.append("("+counter+")");
					path.append(':');
					field=null;
					pathCounter.put(actualPath, counter);
				}
				if((field!=null)&&("16S".equals(field))){
					counter=path.length()-path.toString().lastIndexOf('(');
					path.delete(path.length()-(fullField.length()+counter),path.length());
					field=null;
				}
				if(field!=null){
					field=field.substring(0,2)+'('+field.charAt(2)+')';
					if(value.charAt(0)==':'){
						position=value.indexOf('/');
						if(value.charAt(position+1)=='/'){
							appendField(_message,path.toString()+field+':'+value.substring(0,position+2),value.substring(position+2));
						}else{
							appendField(_message,path.toString()+field+':'+value.substring(0,position+1),value.substring(position+1));
						}
					}else{
						appendField(_message,path.toString()+field+':',value);
					}
				}
			}
		}
		fieldScanner.close();
	}

	protected void parseBlock4(EventMessageImportBean _message,String _block) throws ParseException{

		if(_block.charAt(0)=='{'){
			parseBlock4Subblocks(_message,_block);
		}else{
			parseBlock4Fin(_message,_block);
		}
	}

	protected void parseBlock5(EventMessageImportBean _message,String _block) throws ParseException{

		Scanner blockScanner=null;
		String subBlock=null;

		blockScanner=new Scanner(_block);
		blockScanner.useDelimiter(Pattern.compile("\\{(?=\\D{3}:)|\\}[^}]*\\{(?=\\D{3}:)|}",Pattern.MULTILINE));
		while(blockScanner.hasNext()){
			subBlock=blockScanner.next();
			_message.addField("5:"+subBlock.substring(0,4),subBlock.substring(4));
		}
		blockScanner.close();
	}

	protected void parseBlockS(EventMessageImportBean _message,String _block) throws ParseException{

		Scanner blockScanner=null;
		String subBlock=null;

		blockScanner=new Scanner(_block);
		blockScanner.useDelimiter(Pattern.compile("\\{(?=\\D{3}:)|\\}[^}]*\\{(?=\\D{3}:)|}",Pattern.MULTILINE));
		while(blockScanner.hasNext()){
			subBlock=blockScanner.next();
			_message.addField("S:"+subBlock.substring(0,4),subBlock.substring(4));
		}
		blockScanner.close();
	}

	protected void parseBlock(EventMessageImportBean _message,String _block) throws ParseException{
		
		if((_block!=null)&&(_block.length()>0)){
			switch(_block.charAt(0)){
				case '1':	parseBlock1(_message,_block.substring(2));
							break;
				case '2':	parseBlock2(_message,_block.substring(2));
							break;
				case '3':	parseBlock3(_message,_block.substring(2));
							break;
				case '4':	parseBlock4(_message,_block.substring(2));
							break;
				case '5':	parseBlock5(_message,_block.substring(2));
							break;
				case 'S':	parseBlockS(_message,_block.substring(2));
							break;
				default: throw new ParseException("Unknown swift block ["+_block+"]",this.position);
			}
		}
	}
	
	public boolean parse(EventMessageImportBean _importedMessage,String _message,int _offset) throws ParseException{
		
		boolean reply=true;
		String block;
		
		this.position=0;
		this.scanner=new Scanner(_message);
		this.scanner.useDelimiter(Pattern.compile("\\{(?=\\w:)|\\}[^{]*\\{(?=\\w:)|\\-\\}[^{]*\\{(?=\\w:)|(?<=\\})}",Pattern.MULTILINE));
		while(this.scanner.hasNext()){
			block=this.scanner.next();
			this.position+=block.length();
			if(Character.isLetterOrDigit(block.charAt(0))){
				parseBlock(_importedMessage,block);
			}
		}
		this.scanner.close();
		
		return reply;
	}
}
