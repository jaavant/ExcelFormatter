package com.dfpray.data;

public class Representative {
	private String contactFirstName;
	private String contactLastName;
	private String title;
	private String mobilePhone;
	
	public Representative(String contactFirstName, String contactLastName, String title, String mobilePhone) {
		super();
		this.contactFirstName = contactFirstName;
		this.contactLastName = contactLastName;
		this.title = title;
		this.mobilePhone = mobilePhone;
	}

	public Representative() {
		contactFirstName = " ";
		contactLastName = " ";
		title = " ";
		mobilePhone = " ";
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
}
