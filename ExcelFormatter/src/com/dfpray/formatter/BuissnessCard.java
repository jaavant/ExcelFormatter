package com.dfpray.formatter;

import java.util.UUID;

import com.dfpray.data.CFInfo;
import com.dfpray.data.Company;
import com.dfpray.data.Contacts;
import com.dfpray.data.Misc;
import com.dfpray.data.Representative;

public class BuissnessCard {
	
	//String to be parsed
	private String data;
    private Contacts contacts;
    private Representative rep;
    private Company company;
    private CFInfo cfInfo;
    private Misc misc;
    private String info;
    private UUID id;
	
	//Cannot be empty, throw incomplete card exception
	// private String companyName, faxNumber , emailAddress, csiCodes, companyFunction;		             
	
	
	/**
	 * Constructor for BC, takes in unsanitized info
	 * @param info String containing BC info with "" delimeters
	 */
	public BuissnessCard(String info) {
		data = info;
		processData();
		id = UUID.randomUUID();
	}

	/**
	 * Constructs Contracts, Representatives, Company, CFInfo, Misc, and info and parses data for them
	 */
	private void processData() {
		int count = 0;
		int oldPos = 0;
		int pos;
		String phrase;
		
		//Initialize and Construct Data Objects for Class
		contacts = new Contacts();
		rep = new Representative();
		company = new Company();
		cfInfo = new CFInfo();
		misc = new Misc();
		
		
		
		while(true){
			if((pos = getNextQ(oldPos,data)) == -1) return;
			phrase = data.substring(oldPos + 1, pos); 
			
			//Check if phrase is " which happens when "" is come across
			if(phrase.equals("\"")) phrase = " ";
			
			switch(count){
				case 1:  rep.setContactFirstName(phrase); break; 	//first name 
				case 3:	 rep.setContactLastName(phrase); break;		//last name
				case 5:	 rep.setTitle(phrase);	        break;		//title
				case 6:	 company.setCompanyName(phrase); break;		//Company
				case 7:	 company.setStreetAdd(phrase);	break;		//Address
				case 10: company.setCity(phrase);	break;		    //city, state
				case 11: company.setZipcode(phrase); break;			//zip code
				case 12: company.setCountry(phrase);   break;		//country
				case 20: contacts.setPhoneNum(phrase); break;		//phone
				case 21: contacts.setFaxNumber(phrase); break;  	//fax
				case 34: contacts.setEmailAddress(phrase); break;	//email
				case 35: contacts.setWebsite(phrase); break;		//webpage
				case 49: this.info = phrase;           break;		//other		
			}
			count++;			
			oldPos = pos + 2;
		}
	}

	
	/** 
	 * Finds the next " in the string from the starting index. 
	 * @param i Current index in word
	 * @param string String to transverse through
	 * @return index of " or if there was a problem -1. *Change to exception?*
	 */
	private int getNextQ(int i, String string) {
		// TODO Auto-generated method stub
		for(int j = i + 1; j < string.length(); j++){
			if(string.charAt(j) == '"'){
				return j;
			}
		}
		return -1;
	}
	
	public UUID getUI(){
		return id;
	}
	
	public void setUI(UUID id){
		this.id = id;
	}
}
