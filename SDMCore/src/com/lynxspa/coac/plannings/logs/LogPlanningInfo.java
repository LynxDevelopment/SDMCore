package com.lynxspa.coac.plannings.logs;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public enum LogPlanningInfo implements LogDictAdapter {

	PROCESS_START("info.planning.start","Process {0} with id {1} starts (manually: {2}) at {3}."),
	PROCESS_END("info.planning.end","Process {0} executed in {1,time,mm:ss.SSS}."),
	EXECUTE_SHELL_START("info.planning.shell.start","Executing shell file {0}."),
	EXECUTE_SHELL_END("info.planning.shell.end","shell file {0} executde correctly. Execution Result: {1}"),
	EXECUTE_SHELL_PERMITS_ASSIGNED("info.planning.shell.permits","Reading permits assigned to file {0}"),
	EXECUTE_SHELL_EXCUTED("info.planning.shell.executed","shell file {0} executed."),
	EXECUTE_SHELL_FILE_DELETED("info.planning.shell.deleted","shell file {0} deleted."),
	UPDATED_PLANNING("info.planning.updated","planning {0} with id {1} updated."),
	PLANNINGS_GET_ISINES("info.planning.isines","Found {0} isines for planning with id {1}."),
	PLANNINGS_TEMPLATE_GENERATED("info.planning.template","Template {0} generated correctly."),
	PLANNINGS_FILE_FROM_TEMPLATE("info.planning.file.template","Generating File {0} with template {1}."),
	PLANNINGS_FILE_FROM_TEMPLATE_GENERATED("info.planning.file.template.generated","File {0} generated correctly."),
	PROCESS_FTP_GET("info.planning.ftp.importprocess","Transferring file {0} from server {1} with user {2} to local path {3}."),
	PROCESS_FTP_PUT("info.planning.ftp.exportprocess","Transferring local file {0} to path {3} of the server {1} with user {2}."),
	PROCESS_FTP_GET_CORRECT("info.planning.ftp.importprocess.correct","File {0}{1} received correctly."),
	PROCESS_FTP_PUT_CORRECT("info.planning.ftp.exportprocess","File {0} transferred correctly");
	
	private String messageKey=null;
	private String defaultMessage=null;
	
	LogPlanningInfo(String _key, String _defaultMessage){
		this.messageKey=_key;
		this.defaultMessage=_defaultMessage;
	}
	
	public String getMessageKey() {
		return this.messageKey;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public LogLevel getLevel() {
		return LogLevel.INFO;
	}

}
