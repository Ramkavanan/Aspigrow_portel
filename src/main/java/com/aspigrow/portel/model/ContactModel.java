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

public class ContactModel {
   
	private String externalId;
	
	@JsonProperty("Account")
	private String Account;
	
	@JsonProperty("FirstName")
	private String FirstName;
	
	@JsonProperty("LastName")
	private String LastName;
	
	@JsonProperty("Email")
	private String Email;
	
	@JsonProperty("Phone")
	private String Phone;
	
	@JsonProperty("ContId")
	private String ContId;

    public ContactModel() {}
    
	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getContId() {
		return ContId;
	}

	public void setContId(String contId) {
		ContId = contId;
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
