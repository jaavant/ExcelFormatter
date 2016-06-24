package com.dfpray.formatter;

public class Company {
	private String companyName;
	private String streedAdd;
	private String suitPOBox;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String companyFunction;	
	
	public Company(String companyName, String streedAdd, String suitPOBox, String city, String state, String zipcode,
			String country, String companyFunction) {
		super();
		this.companyName = companyName;
		this.streedAdd = streedAdd;
		this.suitPOBox = suitPOBox;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.companyFunction = companyFunction;
	}

	public String getCompanyFunction() {
		return companyFunction;
	}

	public void setCompanyFunction(String companyFunction) {
		this.companyFunction = companyFunction;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStreedAdd() {
		return streedAdd;
	}

	public void setStreedAdd(String streedAdd) {
		this.streedAdd = streedAdd;
	}

	public String getSuitPOBox() {
		return suitPOBox;
	}

	public void setSuitPOBox(String suitPOBox) {
		this.suitPOBox = suitPOBox;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCounty(String county) {
		this.country = county;
	}
	
	
	
	
}
