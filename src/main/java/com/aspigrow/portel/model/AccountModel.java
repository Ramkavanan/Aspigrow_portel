/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class used to expose this attribute with UI 
 * and make getting UI input as below Model format to perform
 * any kind of Comment related operation
 * 
 * The input format must have be an XML/JSON format. Most of this 
 * model format belonging to JSON format . And we can use @JSONInclude properties
 * and @JSONIgnore properties to make visible or hide this properties in expose
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public class AccountModel {
   
	@JsonProperty("AccName")
	private String AccName;
	
	@JsonProperty("AccNumber")
	private String AccNumber;
	
	@JsonProperty("AccSite")
	private String AccSite;
	
	@JsonProperty("AccId")
	private String externalId;
	
	@JsonProperty("Contact")
	private ContactModel[] Contact;
	
	@JsonProperty("Opportunity")
	private OpportunityModel Opportunity;
	
    public AccountModel() {}
    
	public ContactModel[] getContact() {
		return Contact;
	}

	public void setContact(ContactModel[] contact) {
		Contact = contact;
	}

	public OpportunityModel getOpportunity() {
		return Opportunity;
	}

	public void setOpportunity(OpportunityModel opportunity) {
		Opportunity = opportunity;
	}

	public String getAccId() {
		return externalId;
	}

	public void setAccId(String accId) {
		externalId = accId;
	}

	public String getAccName() {
		return AccName;
	}

	public void setAccName(String accName) {
		AccName = accName;
	}

	public String getAccNumber() {
		return AccNumber;
	}

	public void setAccNumber(String accNumber) {
		AccNumber = accNumber;
	}

	public String getAccSite() {
		return AccSite;
	}

	public void setAccSite(String accSite) {
		AccSite = accSite;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
