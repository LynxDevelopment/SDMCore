package com.lynxspa.coac.importers.securities;


public enum SecuritySearchMode {

	ISIN(0),
	CUSIP(1),
	SEDOL(2),
	PROVIDERID(3);
	
	private int id=0;
	
	SecuritySearchMode(int _id){
		this.id=_id;
	}

	public int getId() {
		return id;
	}
}
