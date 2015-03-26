package com.lynxspa.coac.importers.securities.ofival.beans;

import java.io.Serializable;

public class OfivalSecurityBean implements Serializable{

	private static final long serialVersionUID = -6669149493193378525L;
	
	
	private String file=null;
	private int position=0;
	private String name=null;
	private String codigoEmisora=null;
	private String isin=null;
	
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCodigoEmisora() {
		return codigoEmisora;
	}
	public void setCodigoEmisora(String codigoEmisora) {
		this.codigoEmisora = codigoEmisora;
	}
	
	public String getIsin() {
		return isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
}
