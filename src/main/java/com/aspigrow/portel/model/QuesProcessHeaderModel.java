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

public class QuesProcessHeaderModel {
   
	private String externalId;
	
	private String Name;
	
	private AgreementModel CF_Agreement__c;
	
	private OpportunityModel CF_Opportunity__c;
	
	private ContactModel CF_Contact__c;
	
	private String CF_Status__c;

    public QuesProcessHeaderModel() {}
    
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public AgreementModel getCF_Agreement__c() {
		return CF_Agreement__c;
	}

	public void setCF_Agreement__c(AgreementModel cF_Agreement__c) {
		CF_Agreement__c = cF_Agreement__c;
	}

	public OpportunityModel getCF_Opportunity__c() {
		return CF_Opportunity__c;
	}

	public void setCF_Opportunity__c(OpportunityModel cF_Opportunity__c) {
		CF_Opportunity__c = cF_Opportunity__c;
	}

	public ContactModel getCF_Contact__c() {
		return CF_Contact__c;
	}

	public void setCF_Contact__c(ContactModel cF_Contact__c) {
		CF_Contact__c = cF_Contact__c;
	}

	public String getCF_Status__c() {
		return CF_Status__c;
	}

	public void setCF_Status__c(String cF_Status__c) {
		CF_Status__c = cF_Status__c;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
