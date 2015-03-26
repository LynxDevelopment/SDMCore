package com.lynxspa.coac.importers.ofival.nodes;


import java.io.BufferedReader;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.coac.importers.ofival.OfivalParser;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Ofival Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class OfivalParserNode extends ParserNode {
	
	
	private int processingLineNumber=0;
	
	public OfivalParserNode(){
		super();
		this.format=CAFormat.OFIVAL;
		this.parser=new OfivalParser();
	}
	
	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{

		int reply=0;
		String processingLine=null;
		String[] readedLines=null;

		this.processingLineNumber=0;
		processingLine=_reader.readLine();
		while(processingLine!=null){
			readedLines=readLine(processingLine,_reader);
			processMessage(_session,_manager,this.processingLineNumber,readedLines[0]);
			if(readedLines[1]!=null){
				processingLine=readedLines[1];
			}else{
				processingLine=_reader.readLine();
				this.processingLineNumber++;
			}
		}
		reply=this.processingLineNumber;
		
		return reply;
	}
	
	private String[] readLine (String _previousReaded,BufferedReader _reader) throws Exception{
		
		String[] reply=null;
		String internalReadLine=null;
		String groupIdentifier=null;
		
		reply=new String[2];
		reply[0]=_previousReaded;
		reply[1]=null;
		if((reply[0]!=null)&&("THR     ".equals(reply[0].substring(8,16)))){
			groupIdentifier=reply[0].substring(34,50)+reply[0].substring(8,16);
			internalReadLine=_reader.readLine();
			this.processingLineNumber++;
			while((internalReadLine!=null)&&("THR     ".equals(internalReadLine.substring(8,16)))&&(groupIdentifier.equals(internalReadLine.substring(34,50)+internalReadLine.substring(8,16)))){
				reply[0]+='\n'+internalReadLine;
				internalReadLine=_reader.readLine();
				this.processingLineNumber++;
			}
			if(internalReadLine!=null)
				reply[1]=internalReadLine;
		}

		return reply;
	}
	
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {

		boolean reply=true;
		
		_importedMessage.getMessage().setOriginalMessage(_message);
		_importedMessage.getMessage().setOriginPosition(_position);
		_importedMessage.setMessageType(_importedMessage.getField(":9-16:"));
		
		return reply;
	}
}
