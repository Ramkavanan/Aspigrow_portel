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

public class ContactModel {
   
	private String externalId;
	
	private AccountModel Account;
	
	private String First_Name;
	
	private String Last_Name;
	
	private String Email;
	
	private String Phone;

    public ContactModel() {}
    
	public AccountModel getAccount() {
		return Account;
	}

	public void setAccount(AccountModel account) {
		Account = account;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
