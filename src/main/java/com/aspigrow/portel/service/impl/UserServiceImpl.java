package com.aspigrow.portel.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.aspigrow.portel.convertor.AddressConvertor;
import com.aspigrow.portel.convertor.ContactModelConvertor;
import com.aspigrow.portel.convertor.UserModelConvertor;
import com.aspigrow.portel.exception.CommonRuntimeException;
import com.aspigrow.portel.model.UserModel;
import com.aspigrow.portel.service.UserService;
import com.aspigrow.persistence.dao.AddressDao;
import com.aspigrow.persistence.dao.UserDao;
import com.aspigrow.persistence.entities.user.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    
    private UserDao userDao;
    
    private MailSender mailSender;
    
    private UserModelConvertor userConvertor;
    
    private AddressConvertor addressConvertor;
    
    private AddressDao addressDao;
    
    private ContactModelConvertor contactConvertor;

    @Autowired
    public UserServiceImpl(UserDao userDao, MailSender mailSender, UserModelConvertor userConvertor
    		, AddressConvertor addressConvertor, AddressDao addressDao, ContactModelConvertor contactConvertor) {
        this.userDao = userDao;
        this.mailSender = mailSender;
        this.userConvertor = userConvertor;
        this.addressConvertor = addressConvertor;
        this.addressDao = addressDao;
        this.contactConvertor = contactConvertor;
    }
    
    @Override
    public UserModel login(UserModel user) throws Exception{
        /**Customer customerEntity = customerDao.findByEmail(customer.getEmailId());
        if (customerEntity != null) {
            return convertToModel(customerEntity);
        }*/
    	
    //Check Maill sending 
    	
    	UserModelConvertor convertor = new UserModelConvertor();
    	User userInfo =  userDao.loadUserByUsername(user.getEmailId());
    	System.out.println("Log in user Info "+userInfo);
   		if(userInfo == null)
   			return null;
   		user = convertor.convertToModel(userInfo);
   		user.setContact(contactConvertor.convertToModel(userInfo.getContact()));
   		return user;
    }
    
    @Override
    public boolean sendNotificationMail(String mailId, String code) {
    	try{
    		if(code == null || code.length()<=0) {
    			Random rand = new Random();
       			int randNum = rand.nextInt(6)+3;
       			code = randNum+"";
    		}
    			
        	SimpleMailMessage sm = new SimpleMailMessage();
        	sm.setTo(mailId);
        	sm.setFrom("ramakavanan@gmail.com");
        	sm.setSubject("Hi");
        	sm.setText("Please use this code and finis your signup process : "+code);
        	mailSender.send(sm);
        	return true;
        } catch(Exception ex) {
        	ex.printStackTrace();
        	System.out.println("Exception --- "+ex.getMessage());
        }
    	return false;
    }

    @Override
    public UserModel save(UserModel user) {
   		User entity = userDao.findByEmail(user.getEmailId());
   		if(entity != null) 
   			throw new IllegalStateException("Email already exists");
   		try{
   			Random rand = new Random();
   			int randNum = rand.nextInt(6)+3;
   			User usr = userConvertor.convertToEntity(user);
   			usr.setEmailCode(randNum+"");
   			user = userConvertor.convertToModel(userDao.saveUser(usr));
   			if(user.getExternalId() == null)
   	   			throw new CommonRuntimeException("failed to save record");
   			if(!sendNotificationMail(user.getEmailId(), randNum+""))
   				System.out.println("Failed to send mail -- See the log");
   		} catch(Exception ex) {
   			ex.printStackTrace();
   			throw new CommonRuntimeException(ex.getMessage());
   		}
   		return user;
    }
    
    @Override
    public UserModel update(UserModel user) {
    	try{
    		if(user.getExternalId() == null) 
    			return null;
    		user = userConvertor.convertToModel(userDao.update(userConvertor.convertToEntity(user)));
    		return user;
    	} catch(Exception ex) {
    		System.out.println("Exception occured");
    		return null;
    	}
    }
    
    @Override
    public boolean verifyEmailCode(String code, String userId)  throws Exception{
    	User entity = userDao.get(Long.valueOf(userId));
    	if(entity == null)
    		throw new IllegalStateException("User Not found");
    	if(entity.getEmailVerified() == true || entity.getEmailCode().isEmpty())
    		return false;
    	if(entity.getEmailCode().equalsIgnoreCase(code)) {
    		entity.setEmailVerified(true);
    		entity = userDao.update(entity);
    		return true;
    	}
    	return false;
    }
    
    @Override
    public boolean reSendCodeToEmail(String userId) throws Exception {
    	User entity = userDao.get(Long.valueOf(userId));
    	if(entity == null || entity.getEmail().isEmpty())
    		throw new IllegalStateException("User Not found");
    	if(entity.getEmailCode().isEmpty()) {
    		Random rand = new Random();
   			int randNum = rand.nextInt(6)+3;
   			entity.setEmailCode(randNum+"");
   			entity = userDao.update(entity);
   			if(!sendNotificationMail(entity.getEmail(), randNum+""))
   				return false;
   			return true;
    	} else {
    		if(!sendNotificationMail(entity.getEmail(), entity.getEmailCode()))
    			return false;
    		return true;
    	}
		
    }

}
