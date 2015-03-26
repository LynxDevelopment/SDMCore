package com.lynxspa.coac.importers.inversis.nodes;

import java.io.BufferedReader;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.inversis.InversisParser;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.sdm.managers.SDMConfigManager;

/**
 * @author joseluis.llorente
 */
@NodeBeautifier(description="Inversis Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class InversisParserNode extends ParserNode {
private int processingLineNumber=0;

	public InversisParserNode(){
		super();
		this.format=CAFormat.INVERSIS;
		this.parser=new InversisParser();
	}

	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{
/*
		int reply=0;
		String processingLine=null;
		String[] lines=null;
		
		this.processingLineNumber=0;
		processingLine=_reader.readLine();
		while(processingLine!=null){
			lines = readLines (processingLine, _reader);
			if (lines[0]!=null)
				processMessage(_session,_manager,this.processingLineNumber,lines[0]);
			processingLine=_reader.readLine();
			this.processingLineNumber++;
		}
		
		reply=this.processingLineNumber;
		
		return reply;
		*/
		int reply=0;
		String processingLine=null;
		String[] lines=null;
		String register = null;
		
		this.processingLineNumber=0;
		processingLine=_reader.readLine();
		while(processingLine!=null){
			//lines = readLines (processingLine, _reader);
			register = processingLine.substring(22,36).toUpperCase().trim();
			if ("SIMUANUPOS".equals(register))
				processMessage(_session,_manager,this.processingLineNumber,processingLine);
			processingLine=_reader.readLine();
			this.processingLineNumber++;
		}
		
		reply=this.processingLineNumber;
		
		return reply;
		
	}
	
	/**
	 * 
	 * @param _previousReaded
	 * @param _reader
	 * @return
	 * @throws Exception
	 */
	private String[] readLines (String _previousLine, BufferedReader _reader) throws Exception{
		
		String[] reply=null;
		String register=null;
		String ordenDia=null;
		
		reply=new String[1];
		reply[0]=_previousLine;
		register = _previousLine.substring(22,36).toUpperCase().trim();
		//if ("ANUNCIOSOPF".equals(register)){ 
		if ("SIMUANUPOS".equals(register)){
			ordenDia = _reader.readLine();
			this.processingLineNumber++;
			if (ordenDia.length()>46)
				reply[0]+='\n'+ordenDia.substring(46);
		}else{
			reply[0]=null;
		}
		
		return reply;
	}
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {

		boolean reply=true;
		
		_importedMessage.getMessage().setOriginalMessage(_message);
		_importedMessage.getMessage().setOriginPosition(_position);
		_importedMessage.setMessageType("INV");
		
		return reply;
	}
}
