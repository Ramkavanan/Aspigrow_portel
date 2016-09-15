/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import com.aspigrow.portel.model.AgreementModel;

/**
 * This interface provide the CURD operation to agreement object through DAO layer.  
 * it provide the service functionalities to handle agreement logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface AgreementService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate Agreement Dao and save agreement info into the database
	 * 
	 * @param agreement
	 * @return
	 */
    AgreementModel save(AgreementModel agreement) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate Agreement Dao and update agreement info into the database
	 *  
     * @param agreement
     * @return
     */
    AgreementModel update(AgreementModel agreement) throws Exception;
    
    /**
     * Method to delete the agreement info from database using the agreement object
     * 
     * @param agreement
     * @return
     */
    boolean delete(AgreementModel agreement) throws Exception;
    
    /**
     * Perform delete operation based on agreement id.
     * 
     * @param agreementId
     * @return
     */
    boolean delete(String agreementId) throws Exception;
    
    /**
     * Pulling the agreement information based on the agreement id.
     * this service provide the bridge to pulling data from Agreement Dao
     * 
     * @param agreementId
     * @return
     */
	AgreementModel getAgreementById(String agreementId) throws Exception;
}
