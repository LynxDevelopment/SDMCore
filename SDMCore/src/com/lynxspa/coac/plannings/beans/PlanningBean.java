package com.lynxspa.coac.plannings.beans;

import java.util.List;

public class PlanningBean {
	private String cronExpression;
	private String outputFile;
	private String outputFileName;
	private byte [] templateFile;
	private List<String> listSecurities;
	private List<String> listSecuritiesAndMarkets;
	private List<String> listTickers;
	private List<String> listContributors;
	private Long id;
	private String actualDate;
	private boolean isFTPExport;
	private boolean isFTPImport;
	private String pathTemplate;
	private String ftpUser;
	private String ftpPassword;
	private String ftpServer;
	private String ftpPath;
	private boolean append;
	private String ftpProxy;
	private String ftpPort;
	private String ftpFile;
	private boolean isFTPBinary;
	private boolean isFTPPassive;
	private boolean overWrite;
	private String ftpProxyUser;
	private String ftpProxyPassword;
	
	public String getPathTemplate() {
		return pathTemplate;
	}
	public void setPathTemplate(String pathTemplate) {
		this.pathTemplate = pathTemplate;
	}

	public String getActualDate() {
		return actualDate;
	}
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String _cronExpression) {
		cronExpression = _cronExpression;
	}


	public String getOutputFile() {
		return outputFile;
	}
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getOutputFileName() {
		return outputFileName;
	}
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public byte [] getTemplateFile() {
		return templateFile;
	}
	public void setTemplateFile(byte [] templateFile) {
		this.templateFile = templateFile;
	}
	
	public Byte[] getObjectTemplate() {
		
		Byte[] reply=null;
		
		if (this.templateFile!=null){
			reply=new Byte[this.templateFile.length];
			for(int ic1=0;ic1<this.templateFile.length;ic1++)
				reply[ic1]=this.templateFile[ic1];
		}
		return reply;
	}
	public void setObjectTemplate(Byte[] template) {
		
		if (template!=null){
			this.templateFile=new byte[template.length];
			for(int ic1=0;ic1<template.length;ic1++)
				this.templateFile[ic1]=template[ic1];
		}
	}

	public List<String> getListSecurities() {
		return listSecurities;
	}
	public void setListSecurities(List<String> listSecurities) {
		this.listSecurities = listSecurities;
	}

	public String getFtpUser() {
		return ftpUser;
	}
	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpServer() {
		return ftpServer;
	}
	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}

	public String getFtpPath() {
		return ftpPath;
	}
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public boolean isFTPExport() {
		return isFTPExport;
	}
	public void setFTPExport(boolean isFTPExport) {
		this.isFTPExport = isFTPExport;
	}

	public boolean isFTPImport() {
		return isFTPImport;
	}
	public void setFTPImport(boolean isFTPImport) {
		this.isFTPImport = isFTPImport;
	}

	public boolean isAppend() {
		return append;
	}
	public void setAppend(boolean append) {
		this.append = append;
	}

	public String getFtpProxy() {
		return ftpProxy;
	}
	public void setFtpProxy(String ftpProxy) {
		this.ftpProxy = ftpProxy;
	}

	public String getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpFile() {
		return ftpFile;
	}
	public void setFtpFile(String ftpFile) {
		this.ftpFile = ftpFile;
	}

	public boolean isFTPBinary() {
		return isFTPBinary;
	}
	public void setFTPBinary(boolean isFTPBinary) {
		this.isFTPBinary = isFTPBinary;
	}

	public boolean isFTPPassive() {
		return isFTPPassive;
	}
	public void setFTPPassive(boolean isFTPPassive) {
		this.isFTPPassive = isFTPPassive;
	}

	public boolean isOverWrite() {
		return overWrite;
	}
	public void setOverWrite(boolean overWrite) {
		this.overWrite = overWrite;
	}

	public String getFtpProxyUser() {
		return ftpProxyUser;
	}
	public void setFtpProxyUser(String ftpProxyUser) {
		this.ftpProxyUser = ftpProxyUser;
	}

	public String getFtpProxyPassword() {
		return ftpProxyPassword;
	}
	public void setFtpProxyPassword(String ftpProxyPassword) {
		this.ftpProxyPassword = ftpProxyPassword;
	}

	public List<String> getListSecuritiesAndMarkets() {
		return listSecuritiesAndMarkets;
	}
	public void setListSecuritiesAndMarkets(List<String> listSecuritiesAndMarkets) {
		this.listSecuritiesAndMarkets = listSecuritiesAndMarkets;
	}

	public List<String> getListTickers() {
		return listTickers;
	}
	public void setListTickers(List<String> listTickers) {
		this.listTickers = listTickers;
	}
	
	public List<String> getListContributors() {
		return listContributors;
	}
	public void setListContributors(List<String> listContributors) {
		this.listContributors = listContributors;
	}
	
	
}
