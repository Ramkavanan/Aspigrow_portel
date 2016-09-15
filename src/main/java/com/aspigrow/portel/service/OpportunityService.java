/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import com.aspigrow.portel.model.OpportunityModel;

/**
 * This interface provide the CURD operation to opportunity object through DAO layer.  
 * it provide the service functionalities to handle opportunity logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface OpportunityService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate Opportunity Dao and save opportunity info into the database
	 * 
	 * @param opportunity
	 * @return
	 */
    OpportunityModel save(OpportunityModel opportunity) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate Opportunity Dao and update opportunity info into the database
	 *  
     * @param opportunity
     * @return
     */
    OpportunityModel update(OpportunityModel opportunity) throws Exception;
    
    /**
     * Method to delete the opportunity info from database using the opportunity object
     * 
     * @param opportunity
     * @return
     */
    boolean delete(OpportunityModel opportunity) throws Exception;
    
    /**
     * Perform delete operation based on opportunity id.
     * 
     * @param opportunityId
     * @return
     */
    boolean delete(String opportunityId) throws Exception;
    
    /**
     * Pulling the opportunity information based on the opportunity id.
     * this service provide the bridge to pulling data from Opportunity Dao
     * 
     * @param opportunityId
     * @return
     */
	OpportunityModel getOpportunityById(String opportunityId) throws Exception;
}
