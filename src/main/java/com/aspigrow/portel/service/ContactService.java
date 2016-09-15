/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import com.aspigrow.portel.model.ContactModel;

/**
 * This interface provide the CURD operation to contact object through DAO layer.  
 * it provide the service functionalities to handle contact logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface ContactService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate Contact Dao and save contact info into the database
	 * 
	 * @param contact
	 * @return
	 */
    ContactModel save(ContactModel contact) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate Contact Dao and update contact info into the database
	 *  
     * @param contact
     * @return
     */
    ContactModel update(ContactModel contact) throws Exception;
    
    /**
     * Method to delete the contact info from database using the contact object
     * 
     * @param contact
     * @return
     */
    boolean delete(ContactModel contact) throws Exception;
    
    /**
     * Perform delete operation based on contact id.
     * 
     * @param contactId
     * @return
     */
    boolean delete(String contactId) throws Exception;
    
    /**
     * Pulling the contact information based on the contact id.
     * this service provide the bridge to pulling data from Contact Dao
     * 
     * @param contactId
     * @return
     */
	ContactModel getContactById(String contactId) throws Exception;
}
