/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.QuestionriesProposalHeaderDao;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;
import com.aspigrow.portel.convertor.QuesProcessHeaderModelConvertor;
import com.aspigrow.portel.convertor.QuesProcessLineItemModelConvertor;
import com.aspigrow.portel.model.QuesProcessHeaderModel;
import com.aspigrow.portel.model.QuesProcessLineItemModel;
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
	
	private QuesProcessLineItemModelConvertor lineItemConvertor;

    @Autowired
    public QuesProcessHeaderServiceImpl(QuestionriesProposalHeaderDao quesHeaderDao, QuesProcessHeaderModelConvertor convertor,
    		 QuesProcessLineItemModelConvertor lineItemConvertor) {
        this.quesHeaderDao = quesHeaderDao;
        this.convertor = convertor;
        this.lineItemConvertor = lineItemConvertor;
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
	
	public List<QuesProcessHeaderModel> getQuesProcessHeaderByContactId(String contactId) throws Exception {
		List<QuesProcessHeaderModel> modelList = new ArrayList<QuesProcessHeaderModel>();
		try{
			if(contactId == null || contactId.length() <= 0)
				return null;
			List<QuestionriesProposalHeader> headers = quesHeaderDao.getQuestionriesProposalHeaderByContactId(contactId);
			System.out.println("Header Size ---- "+headers.size());
			Set<Integer> headersFilter = new HashSet<Integer>();
			for(QuestionriesProposalHeader header : headers) {
				if(!headersFilter.contains(header.getId())) {
					QuesProcessHeaderModel headerModel = convertor.convertToModel(header);	
					List<QuesProcessLineItemModel> items = new ArrayList<QuesProcessLineItemModel>();
					for(QuestionriesProposalLineItem lineItem : header.getQuesProcessLineItems()) {
						System.out.println("Line item id ---- "+lineItem.getQuestion());
						QuesProcessLineItemModel line = lineItemConvertor.convertToModel(lineItem);
						items.add(line);
					}
					System.out.println("List Size ==== "+items.size());
					QuesProcessLineItemModel[] arrayItem = new QuesProcessLineItemModel[items.size()];
					headerModel.setQuestProcessLineItems(items.toArray(arrayItem)); 
					modelList.add(headerModel);
					headersFilter.add(header.getId());
				}
			}
			System.out.println("Size of the model list ---- "+modelList.size());
			return modelList;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean saveAnswer(QuesProcessHeaderModel model) throws Exception {
		try{
			System.out.println("Implemented to save  ----------------- ");
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			QuestionriesProposalHeader header = quesHeaderDao.getQuestionriesProposalHeader(model.getExternalId());
			header.setStatus("Submitted");
			System.out.println("Header information ---- "+header.getName()+" headr  "+model.getStatus());
			System.out.println("Header Sixe" + header.getQuesProcessLineItems().size());
			//convertor.updateEntityWithModel(header, model);
			Set<QuestionriesProposalLineItem> lineItems = new HashSet<QuestionriesProposalLineItem>();
			QuesProcessLineItemModel[] modelLineItems = model.getQuestProcessLineItems();
			for(QuesProcessLineItemModel modelItem : modelLineItems) {
				for(QuestionriesProposalLineItem lineItem : header.getQuesProcessLineItems()) {
					if(modelItem.getExternalId().equalsIgnoreCase(lineItem.getExternalId())) {
						lineItem.setAnswer(modelItem.getAnswer());
						lineItem.setComment(modelItem.getComments());
						//lineItemConvertor.updateEntityWithModel(lineItem, modelItem);
						System.out.println(modelItem.getAnswer() + " Commnd --- "+modelItem.getComments());
						System.out.println("Linre item ---- "+lineItem.getId()+"    ans "+lineItem.getAnswer());
						lineItems.add(lineItem);
					}
				}
			}
			header.setQuesProcessLineItems(lineItems);
			System.out.println("Model answer  ---- "+header.toString());
			header = quesHeaderDao.updateQuestionriesProposalHeader(header);
		//	model = convertor.convertToModel(quesHeaderDao.updateQuestionriesProposalHeader(convertor.convertToEntity(model)));
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;		
		}	
	}
}
