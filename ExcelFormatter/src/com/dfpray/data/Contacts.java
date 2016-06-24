package com.dfpray.data;

public class Contacts {
	private String phoneNum;
	private String ext;
	private String faxNumber;
	private String website;
	private String contactLists;
	private String emailAddress;
	
	public Contacts(String phone, String ext, String web, String contacts, String email, String fax){
		phoneNum = phone;
		this.ext = ext;
		website = web;
		contactLists = contacts;
		emailAddress = email;
		faxNumber = fax;
	}

	public Contacts() {
		phoneNum = " ";
		ext = " ";
		faxNumber = " ";
		website = " ";
		contactLists = " ";
		emailAddress = " ";
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactLists() {
		return contactLists;
	}

	public void setContactLists(String contactLists) {
		this.contactLists = contactLists;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
