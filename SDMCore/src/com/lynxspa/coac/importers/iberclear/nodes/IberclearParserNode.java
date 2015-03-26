package com.lynxspa.coac.importers.iberclear.nodes;


import java.io.BufferedReader;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.iberclear.IberclearParser;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * @author joseluis.llorente
 */
@NodeBeautifier(description="Iberclear Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class IberclearParserNode extends ParserNode {
	
	
	private int processingLineNumber=0;
	
	public IberclearParserNode(){
		super();
		this.format=CAFormat.IBERCLEAR;
		this.parser=new IberclearParser();
	}
	
	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{

		int reply=0;
		String processingLine=null;

		this.processingLineNumber=0;
		processingLine=_reader.readLine();
		while(processingLine!=null){
			processMessage(_session,_manager,this.processingLineNumber,processingLine);
				processingLine=_reader.readLine();
				this.processingLineNumber++;
		}
		
		reply=this.processingLineNumber;
		
		return reply;
	}
	 
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {

		boolean reply=true;
		
		_importedMessage.getMessage().setOriginalMessage(_message);
		_importedMessage.getMessage().setOriginPosition(_position);
		_importedMessage.setMessageType(_importedMessage.getField("IBC:1-3:"));
		
		return reply;
	}
}
