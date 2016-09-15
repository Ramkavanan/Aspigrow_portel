/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.model;

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

public class OpportunityModel {
   
	private String externalId;
	
	private String Name;
	
	private AccountModel Account;
	
    public OpportunityModel() {}
    
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public AccountModel getAccount() {
		return Account;
	}

	public void setAccount(AccountModel account) {
		Account = account;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
