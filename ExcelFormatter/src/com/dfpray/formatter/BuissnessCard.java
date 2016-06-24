package com.dfpray.formatter;

import java.util.UUID;

public class BuissnessCard {
	
	//String to be parsed
	private String data;
    private Contacts contacts;
    private Representative representative;
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
	 * Parses data to get the values for the rest of the empty fields in the object
	 */
	private void processData() {
		int valuesEval = 0;
		int pos;
		
		// format starts with "", so we skip for convention 
		for(int i = 4; i < data.length(); i++) {
			pos = getNextQ(i,data);
			
			//check if we need to record the data
			switch(pos){
				
			 case -1: break;
			}
		}
	}

	
	/** 
	 * Finds the next " in the string from the starting index. ( Not including current index) 
	 * @param i Current index in word
	 * @param string String to transverse through
	 * @return index of " or if there was a problem -1. *Change to exception?*
	 */
	private int getNextQ(int i, String string) {
		// TODO Auto-generated method stub
		return -1;
	}
	
	public void setInfo(int i, String info){
		
	}
	
	public UUID getUI(){
		return id;
	}
	
	public void setUI(UUID id){
		this.id = id;
	}
}
