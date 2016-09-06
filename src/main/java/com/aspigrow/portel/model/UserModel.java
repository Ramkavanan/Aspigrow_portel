package com.aspigrow.portel.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * User entity act inbetween the user persistence and the api model view
 */

@ApiModel("User")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel implements Serializable {

    @ApiModelProperty(value = "User's email ID", required = true)
    private String emailId;
    @ApiModelProperty(value = "User's Phone Number", required = true)
    private String phone;
    private String firstName;
    private String middleName;
    private String lastName;
    private String imageUrl;
    @JsonProperty("userId")
    private String externalId;
    private boolean phoneVerified;
    @JsonProperty("password")
    private String password;
    private boolean isEnabled;
    private AddressModel address;

    public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public UserModel() {}
    
   /* public UserEntity(User customerEntity) {
        if (customerEntity != null) {
            this.phone = customerEntity.getPhone();
            this.emailId = customerEntity.getEmail();
            this.firstName = customerEntity.getFirstName();
            this.middleName = customerEntity.getMiddleName();
            this.lastName = customerEntity.getLastName();
            this.externalId = customerEntity.getExternalId();
            this.phoneVerified = customerEntity.getPhoneVerified();
            this.imageUrl = customerEntity.getImageUrl();
            this.maritalstatus = customerEntity.getMaritalstatus();
            this.loanStatus = customerEntity.getLoanStatus();
            this.appStatus = customerEntity.getAppStatus();
        }
    }*/
    
    public AddressModel getAddress() {
		return address;
	}


	public void setAddress(AddressModel address) {
		this.address = address;
	}


	public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Boolean getPhoneVerified(){
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified){
        this.phoneVerified = phoneVerified;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserModel{");
        sb.append("emailId='").append(emailId).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", externalId='").append(externalId).append('\'');
        sb.append(", phoneVerified=").append(phoneVerified);
        sb.append('}');
        return sb.toString();
    }
}
