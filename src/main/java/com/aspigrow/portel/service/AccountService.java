/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import com.aspigrow.portel.model.AccountModel;

/**
 * This interface provide the CURD operation to account object through DAO layer.  
 * it provide the service functionalities to handle account logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface AccountService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate Account Dao and save account info into the database
	 * 
	 * @param account
	 * @return
	 */
    AccountModel save(AccountModel account) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate Account Dao and update account info into the database
	 *  
     * @param account
     * @return
     */
    AccountModel update(AccountModel account) throws Exception;
    
    /**
     * Method to delete the account info from database using the account object
     * 
     * @param account
     * @return
     */
    boolean delete(AccountModel account) throws Exception;
    
    /**
     * Perform delete operation based on account id.
     * 
     * @param accountId
     * @return
     */
    boolean delete(String accountId) throws Exception;
    
    /**
     * Pulling the account information based on the account id.
     * this service provide the bridge to pulling data from Account Dao
     * 
     * @param accountId
     * @return
     */
	AccountModel getAccountById(String accountId) throws Exception;
}
