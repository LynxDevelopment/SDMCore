package com.lynxspa.coac.plannings.dictionaries;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum LogErrorDict implements LogDictAdapter ,FPMExceptionMessage{
	
	PLANNING_EXCEPTION("error.planning.process","Error in the planning process:.\n"),
	EXECUTE_SHELL_EXCEPTION("error.execute.shell.process","Error executing shell.\n"),
	PLANNING_INCORRECT_CRON("error.planning.cron.value","Cron expression {0} not correct for planning {1}.\n");
	
	private LogErrorDict(String messageKey, String defaultMessage){
		this.messageKey = messageKey;
		this.defaultMessage = defaultMessage;
	} 
	
	private String defaultMessage = null;
	private String messageKey = null;
	
	public LogLevel getLevel() {
		return LogLevel.ERROR;
	}

	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	public String getMessageKey() {
		return this.messageKey;
	}
	
	public FPMExceptionType getType() {
		return FPMExceptionType.ERROR;
	}
}
