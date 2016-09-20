/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import com.aspigrow.portel.model.QuesProcessLineItemModel;

/**
 * This interface provide the CURD operation to quesLineItem object through DAO layer.  
 * it provide the service functionalities to handle quesLineItem logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface QuesProcessLineItemService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate QuesProcessLineItem Dao and save quesLineItem info into the database
	 * 
	 * @param quesLineItem
	 * @return
	 */
    QuesProcessLineItemModel save(QuesProcessLineItemModel quesLineItem) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate QuesProcessLineItem Dao and update quesLineItem info into the database
	 *  
     * @param quesLineItem
     * @return
     */
    QuesProcessLineItemModel update(QuesProcessLineItemModel quesLineItem) throws Exception;
    
    /**
     * Method to delete the quesLineItem info from database using the quesLineItem object
     * 
     * @param quesLineItem
     * @return
     */
    boolean delete(QuesProcessLineItemModel quesLineItem) throws Exception;
    
    /**
     * Perform delete operation based on quesLineItem id.
     * 
     * @param quesLineItemId
     * @return
     */
    boolean delete(String quesLineItemId) throws Exception;
    
    /**
     * Pulling the quesLineItem information based on the quesLineItem id.
     * this service provide the bridge to pulling data from QuesProcessLineItem Dao
     * 
     * @param quesLineItemId
     * @return
     */
	QuesProcessLineItemModel getQuesProcessLineItemById(String quesLineItemId) throws Exception;
	
}
