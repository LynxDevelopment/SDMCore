/**
 * 
 */
package com.lynxspa.coac.importers.swift.nodes;


import java.io.BufferedReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Session;

import com.lynxit.fpm.admin.config.NodeBeautifier;
import com.lynxspa.sdm.dictionaries.formats.CAFormat;
import com.lynxspa.coac.importers.EventMessageImportBean;
import com.lynxspa.coac.importers.nodes.ParserNode;
import com.lynxspa.coac.importers.swift.SwiftParser;
import com.lynxspa.sdm.managers.SDMConfigManager;


/**
 * @author albert.farre
 */
@NodeBeautifier(description="Swift Parser", category="CorporateActionsCore", smallIcon = "../../../../../lynxit/fpm/nodes/icons/parser_16.gif", largeIcon = "../../../../../lynxit/fpm/nodes/icons/parser_32.gif")
public class SwiftParserNode extends ParserNode {

	public SwiftParserNode(){
		super();
		this.format=CAFormat.SWIFT;
		this.parser=new SwiftParser();
	}
	
	@Override
	protected int processFile (Session _session,SDMConfigManager _manager,BufferedReader _reader) throws Exception{
		
		int reply=0;
		Scanner scanner=null;
		String message=null;
	
		scanner=new Scanner(_reader);
		scanner.useDelimiter(Pattern.compile("\\{1:",Pattern.MULTILINE));
		while(scanner.hasNext()){
			reply++;
			message="{1:"+scanner.next();
			if((message.length()>2)&&(Character.isLetterOrDigit(message.charAt(3)))){
				processMessage(_session,_manager,reply,message);
			}
		}
		scanner.close();
		
		return reply;
	}
	
	@Override
	protected boolean processInternalMessage(Session _session,SDMConfigManager _manager, int _position, String _message,EventMessageImportBean _importedMessage) throws Exception {

		boolean reply=true;
		
		_importedMessage.getMessage().setOriginalMessage(_message);
		_importedMessage.getMessage().setOriginPosition(_position);
		_importedMessage.setMessageType(("21".equals(_importedMessage.getField("1:SRVID:")))? "RSP" : _importedMessage.getField("2:MSGT:"));
		
		return reply;
	}
}
