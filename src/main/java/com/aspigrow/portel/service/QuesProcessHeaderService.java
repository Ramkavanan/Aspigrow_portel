/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service;

import java.util.List;

import com.aspigrow.portel.model.QuesProcessHeaderModel;

/**
 * This interface provide the CURD operation to quesHeader object through DAO layer.  
 * it provide the service functionalities to handle quesHeader logics
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

public interface QuesProcessHeaderService {

	/**
	 * Method act bridge between the dao and resource layer to perform 
	 * initiate QuesProcessHeader Dao and save quesHeader info into the database
	 * 
	 * @param quesHeader
	 * @return
	 */
    QuesProcessHeaderModel save(QuesProcessHeaderModel quesHeader) throws Exception;

    /**
     * Method act bridge between the dao and resource layer to perform 
	 * initiate QuesProcessHeader Dao and update quesHeader info into the database
	 *  
     * @param quesHeader
     * @return
     */
    QuesProcessHeaderModel update(QuesProcessHeaderModel quesHeader) throws Exception;
    
    /**
     * Method to delete the quesHeader info from database using the quesHeader object
     * 
     * @param quesHeader
     * @return
     */
    boolean delete(QuesProcessHeaderModel quesHeader) throws Exception;
    
    /**
     * Perform delete operation based on quesHeader id.
     * 
     * @param quesHeaderId
     * @return
     */
    boolean delete(String quesHeaderId) throws Exception;
    
    /**
     * Pulling the quesHeader information based on the quesHeader id.
     * this service provide the bridge to pulling data from QuesProcessHeader Dao
     * 
     * @param quesHeaderId
     * @return
     */
	QuesProcessHeaderModel getQuesProcessHeaderById(String quesHeaderId) throws Exception;
	
	/**
	 * Pulling the quesHeader information based on the contact id.
     * this service provide the bridge to pulling data from QuesProcessHeader Dao
     * 
	 * @param contactId
	 * @return
	 * @throws Exception
	 */
	List<QuesProcessHeaderModel> getQuesProcessHeaderByContactId(String contactId) throws Exception;
	
	boolean saveAnswer(QuesProcessHeaderModel quesHeader) throws Exception;
}
