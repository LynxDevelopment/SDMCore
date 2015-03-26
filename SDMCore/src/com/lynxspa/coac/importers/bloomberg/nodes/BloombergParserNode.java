package com.lynxspa.coac.importers.bloomberg.nodes;


import java.io.BufferedReader;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.bloomberg.BloombergParser;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Bloomberg Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class BloombergParserNode extends ParserNode {
	
	public static final String DEFAULTDATEFORMAT="yyyyMMdd";
	
	
	private String messageType=null;
	private String dateFormat=null;
	private String startSenderTimestamp=null;

	
	public BloombergParserNode(){
		super();
		this.dateFormat=BloombergParserNode.DEFAULTDATEFORMAT;
		this.format=CAFormat.BLOOMBERG;
		this.parser=new BloombergParser();
	}
	
	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{
		
		int reply=0;
		String readedString=null;
		int processingLine=0;
		
		readedString=_reader.readLine();
		processingLine++;
		while((readedString!=null)&&(!readedString.startsWith("START-OF-DATA"))){
			if(readedString.startsWith("DATEFORMAT"))
				this.dateFormat=readedString.substring(11).trim().replaceAll("mm","MM");
			if(readedString.startsWith("TIMESTARTED"))
				this.startSenderTimestamp=readedString.substring(12);
			readedString=_reader.readLine();
			processingLine++;
		}
		if((readedString!=null)&&(readedString.length()>10)){
			this.messageType="Corp/Pfd";
			readedString=_reader.readLine();
			reply++;
			processingLine++;
			while((readedString!=null)&&(!"END-OF-DATA".equals(readedString.trim()))){
				processMessage(_session,_manager,processingLine,readedString);
				readedString=_reader.readLine();
				processingLine++;
				reply++;
			}
		}
		
		return reply;
	}
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {
		
		boolean reply=false;
		
		if("0".equals(_importedMessage.getField("HDR:3").trim())){
			_importedMessage.addField("FMT:DATE",this.dateFormat);
			_importedMessage.addField("MSG:STARTTIMESTAMP",this.startSenderTimestamp);
			_importedMessage.getMessage().setOriginalMessage(_message);
			_importedMessage.getMessage().setOriginPosition(_position);
			_importedMessage.setMessageType(this.messageType);
			reply=true;
		}
		
		return reply;
	}
}
