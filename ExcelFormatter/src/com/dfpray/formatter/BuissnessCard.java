package com.dfpray.formatter;

public class BuissnessCard {
	//String to be parsed
	private String data;
	
	//Cannot be empty, throw incomplete card exception
	private String companyName, faxNumber, emailAddress, csiCodes, companyFunction;
	
	//Extra info
	private String contactFirstName, contactLastName, title, streetAdd, suitPOBox,
			city, state, zipcode, county, phoneNum, ext, mobilePhone, website,
			mbeaffiliations, labor, serviceArea, companyNotes, contactLists, 
			cf_AltEmail, cf_DNU, cf_SupllierManf, cf_Trade, cf_UnionValue,
			cf_UnlicensedStates, cf_WillNotBid;
	
	//Array of all BC (Business Cards) info 
	private String[] bc = {companyName, contactFirstName, contactLastName, title, streetAdd, suitPOBox, 
			                city, state, zipcode, county, phoneNum, ext, mobilePhone, faxNumber, emailAddress,
			                website, csiCodes, companyFunction,	mbeaffiliations, labor, serviceArea, companyNotes, 
			                contactLists, cf_AltEmail, cf_DNU, cf_SupllierManf, cf_Trade, cf_UnionValue,
							cf_UnlicensedStates, cf_WillNotBid};
			 				
	
	
	/**
	 * Constructor for BC, takes in unsanitized info
	 * @param info String containing BC info with "" delimeters
	 */
	public BuissnessCard(String info) {
		data = info;
		processData();
	}

	
	private void processData() {
		
		
	}
	
	
	
	
}
