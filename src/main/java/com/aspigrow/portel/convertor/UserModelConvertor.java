package com.aspigrow.portel.convertor;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.aspigrow.persistence.entities.user.Address;
import com.aspigrow.persistence.entities.user.User;
import com.aspigrow.portel.model.AddressModel;
import com.aspigrow.portel.model.UserModel;

/**
 * User entity act inbetween the user persistence and the api model view
 */

@Component("userConvertor")
public class UserModelConvertor implements Convertor<UserModel, User> {
	
	@Override
   	public UserModel convertToModel(User entity) {
		try{
			if(entity == null)
				return null;
			UserModel userModel = new UserModel();
			userModel.setEmailId(entity.getEmail());
			userModel.setPhone(entity.getPhone());
			userModel.setFirstName(entity.getFirstName());
			userModel.setPassword(entity.getPassword());
			userModel.setExternalId(entity.getExternalId());
			return userModel;
		} catch(Exception ex) {
			return null;
		}   	
	}

   	@Override
	public User convertToEntity(UserModel model) {
		try{
			if(model == null) 
				return null;
			User user = new User();
			user.setEmail(model.getEmailId());
			user.setPhone(model.getPhone());
			user.setFirstName(model.getFirstName());
			user.setPassword(model.getPassword());
			user.setExternalId(model.getExternalId());
			return user;
		} catch(Exception ex) {
			return null;
		}	
	}
	
	@Override
	public void updateEntityWithModel(User user, UserModel userModel) {
		if(userModel != null) {
			user.setFirstName(userModel.getFirstName());
			user.setLastName(userModel.getLastName());
			user.setImageUrl(userModel.getExternalId());
			user.setphoneVerified((userModel.getPhoneVerified() == null? false:userModel.getPhoneVerified() ));
			user.setPhone(userModel.getPhone());
			user.setPassword(userModel.getPassword());
			user.setEnabled(userModel.isEnabled());
		}
	}
}
