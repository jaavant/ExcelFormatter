package com.dfpray.data;

import java.io.Serializable;

public class Misc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String csiCodes;
	private String mbeaffiliations;
	private String labor;
	private String serviceArea;
	private String companyNotes;
	
	public Misc(String csiCodes, String mbeaffiliations, String labor, String serviceArea, String companyNotes) {
		super();
		this.csiCodes = csiCodes;
		this.mbeaffiliations = mbeaffiliations;
		this.labor = labor;
		this.serviceArea = serviceArea;
		this.companyNotes = companyNotes;
	}

	public Misc() {
		csiCodes = " ";
		mbeaffiliations = " ";
		labor = " ";
		serviceArea = " ";
		companyNotes = " ";
	}

	public String getCsiCodes() {
		return csiCodes;
	}

	public void setCsiCodes(String csiCodes) {
		this.csiCodes = csiCodes;
	}

	public String getMbeaffiliations() {
		return mbeaffiliations;
	}

	public void setMbeaffiliations(String mbeaffiliations) {
		this.mbeaffiliations = mbeaffiliations;
	}

	public String getLabor() {
		return labor;
	}

	public void setLabor(String labor) {
		this.labor = labor;
	}

	public String getServiceArea() {
		return serviceArea;
	}

	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
	}

	public String getCompanyNotes() {
		return companyNotes;
	}

	public void setCompanyNotes(String companyNotes) {
		this.companyNotes = companyNotes;
	}
	
	public String toString(){
		return csiCodes + "\n" + mbeaffiliations + "\n" + labor + "\n" + serviceArea + "\n" + companyNotes + "\n"; 
	}
}
