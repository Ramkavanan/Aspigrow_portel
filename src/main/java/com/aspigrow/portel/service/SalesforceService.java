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

public interface SalesforceService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate Salesforce Dao and save account info into the database
	 * 
	 * @param account
	 * @return
	 */
    boolean importSalesforceData(AccountModel account) throws Exception;

   }
