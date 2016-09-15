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

public class QuesProcessLineItemModel {
   
	private String externalId;
	
	private String Name;
	
	private String CF_Answer__c;
	
	private String CF_Comments__c;
	
	private String CF_Help_Text__c;
	
	private String CF_Picklist_Options__c;
	
	private String CF_Question__c;
	
	private String CF_Type__c;
	
	private QuesProcessHeaderModel CF_Questionnaire_Header__c;

    public QuesProcessLineItemModel() {}
    
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCF_Answer__c() {
		return CF_Answer__c;
	}

	public void setCF_Answer__c(String cF_Answer__c) {
		CF_Answer__c = cF_Answer__c;
	}

	public String getCF_Comments__c() {
		return CF_Comments__c;
	}

	public void setCF_Comments__c(String cF_Comments__c) {
		CF_Comments__c = cF_Comments__c;
	}

	public String getCF_Help_Text__c() {
		return CF_Help_Text__c;
	}

	public void setCF_Help_Text__c(String cF_Help_Text__c) {
		CF_Help_Text__c = cF_Help_Text__c;
	}

	public String getCF_Picklist_Options__c() {
		return CF_Picklist_Options__c;
	}

	public void setCF_Picklist_Options__c(String cF_Picklist_Options__c) {
		CF_Picklist_Options__c = cF_Picklist_Options__c;
	}

	public String getCF_Question__c() {
		return CF_Question__c;
	}

	public void setCF_Question__c(String cF_Question__c) {
		CF_Question__c = cF_Question__c;
	}

	public String getCF_Type__c() {
		return CF_Type__c;
	}

	public void setCF_Type__c(String cF_Type__c) {
		CF_Type__c = cF_Type__c;
	}

	public QuesProcessHeaderModel getCF_Questionnaire_Header__c() {
		return CF_Questionnaire_Header__c;
	}

	public void setCF_Questionnaire_Header__c(
			QuesProcessHeaderModel cF_Questionnaire_Header__c) {
		CF_Questionnaire_Header__c = cF_Questionnaire_Header__c;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
