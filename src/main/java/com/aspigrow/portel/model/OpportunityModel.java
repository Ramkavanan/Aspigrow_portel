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

public class OpportunityModel {
   
	private String externalId;
	
	@JsonProperty("QuesId")
	private String QuesId;
	
	@JsonProperty("oppId")
	private String oppId;
	
	@JsonProperty("OppName")
	private String OppName;
	
	@JsonProperty("Account")
	private String Account;
	
	@JsonProperty("QuestProcessHeader")
	private QuesProcessHeaderModel QuestProcessHeader;
	
    public OpportunityModel() {}
    
	public String getQuesId() {
		return QuesId;
	}

	public void setQuesId(String quesId) {
		QuesId = quesId;
	}

	public String getOppId() {
		return oppId;
	}

	public void setOppId(String oppId) {
		this.oppId = oppId;
	}

	public String getOppName() {
		return OppName;
	}

	public void setOppName(String oppName) {
		OppName = oppName;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public QuesProcessHeaderModel getQuestProcessHeader() {
		return QuestProcessHeader;
	}

	public void setQuestProcessHeader(QuesProcessHeaderModel questProcessHeader) {
		QuestProcessHeader = questProcessHeader;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	
}
