package com.lynxspa.coac.plannings.beans;

import java.util.Date;

public class ControlPlanningsBean {
	
	private Long id;
	private String cronn;
	private boolean FTPExport;
	private boolean FTPImport;
	private boolean ExecuteShell;
	private boolean FileToPath;
	private Date lastExecutionDate;
	private String fileName;
	private boolean manual;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCronn() {
		return cronn;
	}
	public void setCronn(String cronn) {
		this.cronn = cronn;
	}
	public boolean isFTPExport() {
		return FTPExport;
	}
	public void setFTPExport(boolean export) {
		FTPExport = export;
	}
	public boolean isFTPImport() {
		return FTPImport;
	}
	public void setFTPImport(boolean import1) {
		FTPImport = import1;
	}
	public boolean isExecuteShell() {
		return ExecuteShell;
	}
	public void setExecuteShell(boolean executeShell) {
		ExecuteShell = executeShell;
	}
	public boolean isFileToPath() {
		return FileToPath;
	}
	public void setFileToPath(boolean fileToPath) {
		FileToPath = fileToPath;
	}
	
	public Date getLastExecutionDate() {
		return lastExecutionDate;
	}
	public void setLastExecutionDate(Date lastExecutionDate) {
		this.lastExecutionDate = lastExecutionDate;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public boolean isManual() {
		return manual;
	}
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
