/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.persistence.entities.sObject.Contact;
import com.aspigrow.portel.model.ContactModel;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("contactModelConvertor")
public class ContactModelConvertor implements Convertor<ContactModel, Contact> {
	
    @Override
    public  Contact convertToEntity(ContactModel model) {
        if (model == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setExternalId(model.getExternalId());
        return contact;
    }

    @Override
    public ContactModel convertToModel(Contact entity) {
        if (entity == null) {
            return null;
        }
        ContactModel contact = new ContactModel();
        contact.setExternalId(entity.getExternalId());
        return contact;
    }

    @Override
    public void updateEntityWithModel(Contact toUpdate, ContactModel model) {
    	
    }
}
