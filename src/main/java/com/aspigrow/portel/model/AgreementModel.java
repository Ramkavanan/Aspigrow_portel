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

public class AgreementModel {
   
	private String externalId;
	
	private String Name;
	
	private String CF_Contract_Name__c;
	
	private AccountModel CF_Account__c;
	
	private OpportunityModel CF_Opportunity__c;

    public AgreementModel() {}
    
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCF_Contract_Name__c() {
		return CF_Contract_Name__c;
	}

	public void setCF_Contract_Name__c(String cF_Contract_Name__c) {
		CF_Contract_Name__c = cF_Contract_Name__c;
	}

	public AccountModel getCF_Account__c() {
		return CF_Account__c;
	}

	public void setCF_Account__c(AccountModel cF_Account__c) {
		CF_Account__c = cF_Account__c;
	}

	public OpportunityModel getCF_Opportunity__c() {
		return CF_Opportunity__c;
	}

	public void setCF_Opportunity__c(OpportunityModel cF_Opportunity__c) {
		CF_Opportunity__c = cF_Opportunity__c;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
