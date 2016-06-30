package com.dfpray.data;

import java.util.UUID;

public class BusinessCard implements Comparable<BusinessCard> {
	
	//String to be parsed
	private String data;
    private Contacts contacts;
    private Representative rep;
    private Company company;
    private CFInfo cfInfo;
    private Misc misc;
    private UUID id;
	
	//Cannot be empty, throw incomplete card exception
	// private String companyName, faxNumber , emailAddress, csiCodes, companyFunction;		             

	/**
	 * Constructor for BC, takes in unsanitized info
	 * @param info String containing BC info with "" delimeters
	 */
	public BusinessCard(String info) {
		data = info;
		processData();
		id = UUID.randomUUID();
	}
	
	public BusinessCard(){
		data = " ";
		id = UUID.randomUUID();
		contacts = new Contacts();
		rep = new Representative();
		company = new Company("*New Company*", " "," "," "," "," "," "," ");
		cfInfo = new CFInfo();
		misc = new Misc();		
	}
	
	/**
	 * Creates a Business Card from an array of info
	 * @param i Array of info
	 */
	public BusinessCard(String[] i) {
		arrayToInfo(i);
		id = UUID.randomUUID();
	}
    
    public Misc getMisc(){
    	return this.misc;
    }
    
    public CFInfo getCFInfo(){
    	return this.cfInfo;
    }
    
    public Company getCompany(){
    	return this.company;
    }
    
    public Representative getRep(){
    	return this.rep;
    }
	
    public Contacts getContacts(){
    	return this.contacts;
    }
	
	/**
	 * Updates cards info dataS
	 * @param c Contacts data
	 * @param r Representative data
	 * @param com Company data 
	 * @param cf CFInfo data
	 * @param m Misc data
	 * @param info Info about company
	 */
	public void updateCard(Contacts c, Representative r, Company com, CFInfo cf, Misc m){
		contacts = c;
		rep = r;
		company = com;
		cfInfo = cf;
		misc = m;
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
				case 10: company.setCity(phrase);	break;		    //city
				case 11: company.setState(phrase); break;           //state
				case 12: company.setZipcode(phrase); break;			//zip code
				case 13: company.setCountry(phrase);   break;		//country
				case 21: contacts.setPhoneNum(phrase); break;		//phone
				case 22: contacts.setFaxNumber(phrase); break;  	//fax
				case 23: rep.setMobilePhone(phrase); break;         //Mobile 
				case 35: contacts.setEmailAddress(phrase); break;	//email
				case 36: contacts.setWebsite(phrase); break;		//webpage
				case 50: misc.setCompanyNotes(phrase);break;
				//case 50: this.info = phrase;           break;		//other		
			}
			count++;			
			oldPos = pos + 2;
		}
	}
	/**
	 * Takes an array of Info and assigns the info 
	 * to the respectful data
	 * @param bData Array of Strings of info of a B.C
	 */
	private void arrayToInfo(String[] bData){
		company.setCompanyName(bData[0]);
		rep.setContactFirstName(bData[1]);
		rep.setContactLastName(bData[2]);
		rep.setTitle(bData[3]);
		company.setStreetAdd(bData[4]);
		company.setSuitPOBox(bData[5]);
		company.setCity(bData[6]);
		company.setState(bData[7]);
		company.setZipcode(bData[8]);
		company.setCountry(bData[9]);
		contacts.setPhoneNum(bData[10]);
		contacts.setExt(bData[11]);
		rep.setMobilePhone(bData[12]);
		contacts.setFaxNumber(bData[13]);
		contacts.setEmailAddress(bData[14]);
		contacts.setWebsite(bData[15]);
		misc.setCsiCodes(bData[16]);
		company.setCompanyFunction(bData[17]);
		misc.setMbeaffiliations(bData[18]);
		misc.setLabor(bData[19]);
		misc.setServiceArea(bData[20]);														
		misc.setCompanyNotes(bData[21]);
		contacts.setContactLists(bData[22]);
		cfInfo.setCf_AltEmail(bData[23]);
		cfInfo.setCf_DNU(bData[24]);
		cfInfo.setCf_SupplierManf(bData[25]);
		cfInfo.setCf_Trade(bData[26]);
		cfInfo.setCf_UnionValue(bData[27]);
		cfInfo.setCf_UnlicensedStates(bData[28]);
		cfInfo.setCf_WillNotBid(bData[29]);
		data = bData[30];
	}
	
	/**
	 * Generates an Array of strings composed of all B.C data
	 * @return
	 */
	public String[] infoToArray() {
		String[] bData = new String[32];
		bData[0] = company.getCompanyName();
		bData[1] = rep.getContactFirstName();
		bData[2] = rep.getContactLastName();
		bData[3] = rep.getTitle();
		bData[4] = company.getStreetAdd();
		bData[5] = company.getSuitPOBox();
		bData[6] = company.getCity();
		bData[7] = company.getState();
		bData[8] = company.getZipcode();
		bData[9] = company.getCountry();
		bData[10] = contacts.getPhoneNum();
		bData[11] = contacts.getExt();
		bData[12] = rep.getMobilePhone();
		bData[13] = contacts.getFaxNumber();
		bData[14] = contacts.getEmailAddress();
		bData[15] = contacts.getWebsite();
		bData[16] = misc.getCsiCodes();
		bData[17] = company.getCompanyFunction();
		bData[18] = misc.getMbeaffiliations();
		bData[19] = misc.getLabor();
		bData[20] =	misc.getServiceArea();														
		bData[21] = misc.getCompanyNotes(); //should i use info for company notes?
		bData[22] = contacts.getContactLists();
		bData[23] = cfInfo.getCf_AltEmail();
		bData[24] = cfInfo.getCf_DNU();
		bData[25] = cfInfo.getCf_SupplierManf();
		bData[26] = cfInfo.getCf_Trade();
		bData[27] = cfInfo.getCf_UnionValue();
		bData[28] = cfInfo.getCf_UnlicensedStates();
		bData[29] =	cfInfo.getCf_WillNotBid();
		bData[30] = data;
		return bData;
	}
	
	public UUID getUI(){
		return id;
	}
	
	public void setUI(UUID id){
		this.id = id;
	}
	
	@Override
	public String toString(){
		String word = company.getCompanyName();
		//String word = rep.toString() + company.toString() + contacts.toString() + cfInfo.toString() + misc.toString() + "\n" + "Info: " + info;
		return word;
	}
	
	/** 
	 * Finds the next " in the string from the starting index. 
	 * @param i Current index in word
	 * @param string String to transverse through
	 * @return index of " or if there was a problem -1. *Change to exception?*
	 */
	private int getNextQ(int i, String string) {
		for(int j = i + 1; j < string.length(); j++){
			if(string.charAt(j) == '"'){
				return j;
			}
		}
		return -1;
	}
	
	/**
	 * Checks if Card has the mandatory information filled out to be exported
	 * @return Boolean indicating whether it has mandatory information
	 */
	public boolean hasMand(){
		if(company.getCompanyName().trim().length() > 0){
			if(contacts.getFaxNumber().trim().length() > 6)
				if(contacts.getEmailAddress().contains("@")){
					if(misc.getCsiCodes().trim().length() > 0){
						if(company.getCompanyFunction().trim().length() > 0){
							return true;
						}
					}
				}
		}
		return false;
	}
	
	
	public int compareTo(BusinessCard otherCard){
		return company.getCompanyName().compareTo(otherCard.getCompany().getCompanyName());
	}

}
