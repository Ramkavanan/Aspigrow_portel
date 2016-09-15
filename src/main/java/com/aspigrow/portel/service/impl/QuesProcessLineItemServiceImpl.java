/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.QuestionriesProposalLineItemDao;
import com.aspigrow.portel.convertor.QuesProcessLineItemModelConvertor;
import com.aspigrow.portel.model.QuesProcessLineItemModel;
import com.aspigrow.portel.service.QuesProcessLineItemService;

/**
 * Implementation of quesLineItem service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("quesLineItemService")
public class QuesProcessLineItemServiceImpl implements QuesProcessLineItemService {

	private QuestionriesProposalLineItemDao quesLineItemDao;
	
	private QuesProcessLineItemModelConvertor convertor;

    @Autowired
    public QuesProcessLineItemServiceImpl(QuestionriesProposalLineItemDao quesLineItemDao, QuesProcessLineItemModelConvertor convertor) {
        this.quesLineItemDao = quesLineItemDao;
        this.convertor = convertor;
    }

    @Override
    public QuesProcessLineItemModel save(QuesProcessLineItemModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(quesLineItemDao.saveQuestionriesProposalLineItem(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public QuesProcessLineItemModel update(QuesProcessLineItemModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(quesLineItemDao.updateQuestionriesProposalLineItem(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(QuesProcessLineItemModel quesLineItem) {
		try{
			if(quesLineItem == null || quesLineItem.getExternalId() == null)
				return false;
			return 	quesLineItemDao.deleteQuestionriesProposalLineItem(Long.valueOf(quesLineItem.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String quesLineItemId) {
		try {
			if(quesLineItemId == null) 
				return false;
			return 	quesLineItemDao.deleteQuestionriesProposalLineItem(Long.valueOf(quesLineItemId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public QuesProcessLineItemModel getQuesProcessLineItemById(String quesLineItemId) {
		try{
			if(quesLineItemId == null || quesLineItemId.length() <= 0)
				return null;
			return 	convertor.convertToModel(quesLineItemDao.getQuestionriesProposalLineItem(quesLineItemId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
