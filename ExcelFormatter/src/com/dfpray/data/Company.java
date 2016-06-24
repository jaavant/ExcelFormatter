package com.dfpray.data;

public class Company {
	private String companyName;
	private String streetAdd;
	private String suitPOBox;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private String companyFunction;	
	
	public Company(String companyName, String streetAdd, String suitPOBox, String city, String state, String zipcode,
			String country, String companyFunction) {
		super();
		this.companyName = companyName;
		this.streetAdd = streetAdd;
		this.suitPOBox = suitPOBox;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
		this.companyFunction = companyFunction;
	}

	public Company() {
		companyName = " ";
		streetAdd = " ";
		suitPOBox = " ";
		city = " ";
		state = " ";
		zipcode = " ";
		country = " ";
		companyFunction = " ";
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

	public String getStreetAdd() {
		return streetAdd;
	}

	public void setStreetAdd(String streetAdd) {
		this.streetAdd = streetAdd;
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

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString(){
		return  "Company: " + companyName +  "\n StreedAdd: " + streetAdd  + "\n POBox:" + suitPOBox + "\n City: " +city  + 
				 "\n State: " + state  + "\n Zip: " + zipcode + "\n Country: " + country + "\n Function" + companyFunction + "\n";
	}
	
	
}
