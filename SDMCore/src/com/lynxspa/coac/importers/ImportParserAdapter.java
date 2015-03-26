package com.lynxspa.coac.importers;

import java.text.ParseException;

public interface ImportParserAdapter {

	/**
	 * Parses the next message
	 * @param _importedMessage: imported message bean
	 * @param _message: message to parse
	 * @param _offset: offset of message 
	 * @return if message should be processed
	 */
	public boolean parse(EventMessageImportBean _importedMessage,String _message,int _offset) throws ParseException;
}
