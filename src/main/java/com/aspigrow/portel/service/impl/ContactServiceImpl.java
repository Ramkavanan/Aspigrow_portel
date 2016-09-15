/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.ContactDao;
import com.aspigrow.portel.convertor.ContactModelConvertor;
import com.aspigrow.portel.model.ContactModel;
import com.aspigrow.portel.service.ContactService;

/**
 * Implementation of contact service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("contactService")
public class ContactServiceImpl implements ContactService {

	private ContactDao contactDao;
	
	private ContactModelConvertor convertor;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao, ContactModelConvertor convertor) {
        this.contactDao = contactDao;
        this.convertor = convertor;
    }

    @Override
    public ContactModel save(ContactModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(contactDao.saveContact(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public ContactModel update(ContactModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(contactDao.updateContact(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(ContactModel contact) {
		try{
			if(contact == null || contact.getExternalId() == null)
				return false;
			return 	contactDao.deleteContact(Long.valueOf(contact.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String contactId) {
		try {
			if(contactId == null) 
				return false;
			return 	contactDao.deleteContact(Long.valueOf(contactId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public ContactModel getContactById(String contactId) {
		try{
			if(contactId == null || contactId.length() <= 0)
				return null;
			return 	convertor.convertToModel(contactDao.getContact(contactId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
