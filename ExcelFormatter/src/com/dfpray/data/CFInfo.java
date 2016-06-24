package com.dfpray.data;

public class CFInfo {
	private String cf_AltEmail;
	private String cf_DNU;
	private String cf_SupplierManf;
	private String cf_Trade;
	private String cf_UnionValue;
	private String cf_UnlicensedStates;
	private String cf_WillNotBid;
	
	public CFInfo(String cf_AltEmail, String cf_DNU, String cf_SupplierManf, String cf_Trade, String cf_UnionValue,
			String cf_UnlicensedStates, String cf_WillNotBid) {
		super();
		this.cf_AltEmail = cf_AltEmail;
		this.cf_DNU = cf_DNU;
		this.cf_SupplierManf = cf_SupplierManf;
		this.cf_Trade = cf_Trade;
		this.cf_UnionValue = cf_UnionValue;
		this.cf_UnlicensedStates = cf_UnlicensedStates;
		this.cf_WillNotBid = cf_WillNotBid;
	}
	public CFInfo() {
		cf_AltEmail = " ";
		cf_DNU = " ";
		cf_SupplierManf = " ";
		cf_Trade = " ";
		cf_UnionValue = " ";
		cf_UnlicensedStates = " ";
		cf_WillNotBid = " ";		
	}
	public String getCf_AltEmail() {
		return cf_AltEmail;
	}
	public void setCf_AltEmail(String cf_AltEmail) {
		this.cf_AltEmail = cf_AltEmail;
	}
	public String getCf_DNU() {
		return cf_DNU;
	}
	public void setCf_DNU(String cf_DNU) {
		this.cf_DNU = cf_DNU;
	}
	public String getCf_SupplierManf() {
		return cf_SupplierManf;
	}
	public void setCf_SupplierManf(String cf_SupplierManf) {
		this.cf_SupplierManf = cf_SupplierManf;
	}
	public String getCf_Trade() {
		return cf_Trade;
	}
	public void setCf_Trade(String cd_Trade) {
		this.cf_Trade = cd_Trade;
	}
	public String getCf_UnionValue() {
		return cf_UnionValue;
	}
	public void setCf_UnionValue(String cf_UnionValue) {
		this.cf_UnionValue = cf_UnionValue;
	}
	public String getCf_UnlicensedStates() {
		return cf_UnlicensedStates;
	}
	public void setCf_UnlicensedStates(String cf_UnlincensedStates) {
		this.cf_UnlicensedStates = cf_UnlincensedStates;
	}
	public String getCf_WillNotBid() {
		return cf_WillNotBid;
	}
	public void setCf_WillNotBid(String cf_WillNotBid) {
		this.cf_WillNotBid = cf_WillNotBid;
	}
	
}
