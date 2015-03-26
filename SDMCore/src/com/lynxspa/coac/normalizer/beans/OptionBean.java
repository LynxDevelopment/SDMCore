package com.lynxspa.coac.normalizer.beans;

public class OptionBean {
	private String field;
	private String value;
	
	public OptionBean(){
		
	}
	
	public OptionBean(String _field, String _value){
		this.field = _field;
		this.value = _value;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
