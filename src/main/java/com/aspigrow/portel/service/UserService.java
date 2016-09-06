package com.aspigrow.portel.service;

import com.aspigrow.portel.model.UserModel;

public interface UserService {

    UserModel save(UserModel user);

    UserModel login(UserModel user);
    
    UserModel update(UserModel user);
    
    boolean sendNotificationMail(String  userId, String code);
    
    boolean verifyEmailCode(String code, String userId) throws Exception;
    boolean reSendCodeToEmail(String userId) throws Exception;
}
