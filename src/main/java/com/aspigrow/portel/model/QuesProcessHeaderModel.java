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

public class QuesProcessHeaderModel {
   
	private String externalId;
	
	@JsonProperty("Opportunity")
	private String Opportunity;
	
	@JsonProperty("QuesName")
	private String QuesName;
	
	@JsonProperty("Contact")
	private String Contact;
	
	@JsonProperty("Status")
	private String Status;
	
	@JsonProperty("CF_Agreement__c")
	private AgreementModel CF_Agreement__c;
	
	@JsonProperty("QuestProcessLineItems")
	private QuesProcessLineItemModel[] QuestProcessLineItems;
	
    public QuesProcessHeaderModel() {}
    
	public QuesProcessLineItemModel[] getQuestProcessLineItems() {
		return QuestProcessLineItems;
	}

	public void setQuestProcessLineItems(
			QuesProcessLineItemModel[] questProcessLineItems) {
		QuestProcessLineItems = questProcessLineItems;
	}

	public AgreementModel getCF_Agreement__c() {
		return CF_Agreement__c;
	}

	public void setCF_Agreement__c(AgreementModel cF_Agreement__c) {
		CF_Agreement__c = cF_Agreement__c;
	}
	
	public String getOpportunity() {
		return Opportunity;
	}

	public void setOpportunity(String opportunity) {
		Opportunity = opportunity;
	}

	public String getQuesName() {
		return QuesName;
	}

	public void setQuesName(String quesName) {
		QuesName = quesName;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
