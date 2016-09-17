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

public class QuesProcessLineItemModel {
   
	private String externalId;
	
	@JsonProperty("Comments")
	private String Comments;
	
	@JsonProperty("Answer")
	private String Answer;
	
	@JsonProperty("PicklistOtions")
	private String PicklistOtions;
	
	@JsonProperty("HelpText")
	private String HelpText;
	
	@JsonProperty("Type")
	private String Type;
	
	@JsonProperty("Question")
	private String Question;
	
	@JsonProperty("QuestProcessHeader")
	private String QuestProcessHeader;
	
	@JsonProperty("QuestItemId")
	private String QuestItemId;
	
	private QuesProcessHeaderModel CF_Questionnaire_Header__c;

    public QuesProcessLineItemModel() {}
    
	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getPicklistOtions() {
		return PicklistOtions;
	}

	public void setPicklistOtions(String picklistOtions) {
		PicklistOtions = picklistOtions;
	}

	public String getHelpText() {
		return HelpText;
	}

	public void setHelpText(String helpText) {
		HelpText = helpText;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getQuestProcessHeader() {
		return QuestProcessHeader;
	}

	public void setQuestProcessHeader(String questProcessHeader) {
		QuestProcessHeader = questProcessHeader;
	}

	public String getQuestItemId() {
		return QuestItemId;
	}

	public void setQuestItemId(String questItemId) {
		QuestItemId = questItemId;
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
