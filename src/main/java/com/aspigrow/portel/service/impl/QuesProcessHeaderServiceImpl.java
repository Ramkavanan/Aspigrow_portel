/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.QuestionriesProposalHeaderDao;
import com.aspigrow.portel.convertor.QuesProcessHeaderModelConvertor;
import com.aspigrow.portel.model.QuesProcessHeaderModel;
import com.aspigrow.portel.service.QuesProcessHeaderService;

/**
 * Implementation of quesHeader service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("quesHeaderService")
public class QuesProcessHeaderServiceImpl implements QuesProcessHeaderService {

	private QuestionriesProposalHeaderDao quesHeaderDao;
	
	private QuesProcessHeaderModelConvertor convertor;

    @Autowired
    public QuesProcessHeaderServiceImpl(QuestionriesProposalHeaderDao quesHeaderDao, QuesProcessHeaderModelConvertor convertor) {
        this.quesHeaderDao = quesHeaderDao;
        this.convertor = convertor;
    }

    @Override
    public QuesProcessHeaderModel save(QuesProcessHeaderModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(quesHeaderDao.saveQuestionriesProposalHeader(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public QuesProcessHeaderModel update(QuesProcessHeaderModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(quesHeaderDao.updateQuestionriesProposalHeader(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(QuesProcessHeaderModel quesHeader) {
		try{
			if(quesHeader == null || quesHeader.getExternalId() == null)
				return false;
			return 	quesHeaderDao.deleteQuestionriesProposalHeader(Long.valueOf(quesHeader.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String quesHeaderId) {
		try {
			if(quesHeaderId == null) 
				return false;
			return 	quesHeaderDao.deleteQuestionriesProposalHeader(Long.valueOf(quesHeaderId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public QuesProcessHeaderModel getQuesProcessHeaderById(String quesHeaderId) {
		try{
			if(quesHeaderId == null || quesHeaderId.length() <= 0)
				return null;
			return 	convertor.convertToModel(quesHeaderDao.getQuestionriesProposalHeader(quesHeaderId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
