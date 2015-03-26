package com.lynxspa.coac.importers.bloomberg.nodes;


import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.bloomberg.BloombergPerSecParser;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Bloomberg Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class BloombergPerSecParserNode extends ParserNode {
	
	public static final String DEFAULTDATEFORMAT="yyyyMMdd";
	
	private enum ProcessBlock{

		FILE("START-OF-FILE","END-OF-FILE"),
		DICTIONARY("START-OF-FIELDS","END-OF-FIELDS"),
		DATA("START-OF-DATA","END-OF-DATA");
		
		private String startBlock=null;
		private String endBlock=null;
	
		ProcessBlock(String _startBlock,String _endBlock){
			this.startBlock=_startBlock;
			this.endBlock=_endBlock;
		}
		public String getStartBlock(){
			return startBlock;
		}
		public String getEndBlock(){
			return endBlock;
		}
	}
	
	
	private String messageType=null;
	private List<String> messageFieldNames=null;
	private Map<String,String> messageFields=null;

	
	public BloombergPerSecParserNode(){
		super();
		newMessage();
	}
	
	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{
		
		int reply=0;
		String readedString=null;
		int processingLine=0;
		ProcessBlock block=ProcessBlock.FILE;
		
		
		readedString=_reader.readLine();
		readedString=(readedString!=null)? readedString.trim() : readedString;
		processingLine++;
		this.messageType="Corp/Psc";
		newMessage();
		while((readedString!=null)&&(!ProcessBlock.FILE.getEndBlock().equals(readedString))){
			if((readedString.length()>0)&&(!readedString.startsWith("#"))){
				if(ProcessBlock.DICTIONARY==block){
					if(!ProcessBlock.DICTIONARY.getEndBlock().equals(readedString)){
						this.messageFieldNames.add(readedString);
					}else block=ProcessBlock.FILE;
				}else if(ProcessBlock.DATA==block){
					if(!ProcessBlock.DATA.getEndBlock().equals(readedString)){
						processMessage(_session,_manager,processingLine,readedString);
						reply++;
					}else block=ProcessBlock.FILE;
				}else{
					if(ProcessBlock.DICTIONARY.getStartBlock().equals(readedString)){
						block=ProcessBlock.DICTIONARY;
					}else if(ProcessBlock.DATA.getStartBlock().equals(readedString)){
						block=ProcessBlock.DATA;
					}else if(!ProcessBlock.FILE.getStartBlock().equals(readedString)){
						final String[] splittedField=readedString.split("=");
						if(splittedField[0].equals("DATEFORMAT")){
							this.messageFields.put(splittedField[0],splittedField[1].replaceAll("mm","MM"));
						}else{
							this.messageFields.put(splittedField[0],splittedField[1]);
						}
					}
				}
			}
			readedString=_reader.readLine();
			readedString=(readedString!=null)? readedString.trim() : readedString;
			processingLine++;
		}
		
		return reply;
	}
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {
		
		boolean reply=false;
		
		if("0".equals(_importedMessage.getField("HDR:1").trim())){
			for(Entry<String,String> entry:messageFields.entrySet()){
				_importedMessage.addField("MSG:"+entry.getKey(),entry.getValue());
			}
			_importedMessage.getMessage().setOriginalMessage(_message);
			_importedMessage.getMessage().setOriginPosition(_position);
			_importedMessage.setMessageType(this.messageType);
			reply=true;
		}else if((this.messageFieldNames.size()==3)&&("ERROR CODE".equals(_importedMessage.getField("HDR:1").trim()))){
			for(int ic1=3;ic1<_importedMessage.getFields().size();ic1++){
				this.messageFieldNames.add(_importedMessage.getFields().get(ic1).getValue());
			}
		}
		
		return reply;
	}
	
	private void newMessage (){
		this.messageFieldNames=new ArrayList<String>();
		this.messageFieldNames.add("0");
		this.messageFieldNames.add("1");
		this.messageFieldNames.add("2");
		this.messageFields=new HashMap<String,String>();
		this.messageFields.put("DATEFORMAT", BloombergPerSecParserNode.DEFAULTDATEFORMAT);
		this.format=CAFormat.BLOOMBERG;
		this.parser=new BloombergPerSecParser(this.messageFieldNames);
	}
}
