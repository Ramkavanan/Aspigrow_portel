/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;
import com.aspigrow.portel.model.QuesProcessLineItemModel;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("quesLineItemModelConvertor")
public class QuesProcessLineItemModelConvertor implements Convertor<QuesProcessLineItemModel, QuestionriesProposalLineItem> {
	
    @Override
    public  QuestionriesProposalLineItem convertToEntity(QuesProcessLineItemModel model) {
        if (model == null) {
            return null;
        }
        QuestionriesProposalLineItem quesLineItem = new QuestionriesProposalLineItem();
        quesLineItem.setExternalId(model.getExternalId());
        quesLineItem.setAnswer(model.getAnswer());
        quesLineItem.setComment(model.getComments());
        quesLineItem.setHelpText(model.getHelpText());
      //  quesLineItem.setName(model.get);
        quesLineItem.setPickListOpts(model.getPicklistOtions());
        quesLineItem.setType(model.getType());
        quesLineItem.setQuestion(model.getQuestion());
        
        return quesLineItem;
    }

    @Override
    public QuesProcessLineItemModel convertToModel(QuestionriesProposalLineItem entity) {
        if (entity == null) {
            return null;
        }
        QuesProcessLineItemModel quesLineItem = new QuesProcessLineItemModel();
        quesLineItem.setExternalId(entity.getExternalId());
        quesLineItem.setExternalId(entity.getExternalId());
        quesLineItem.setAnswer(entity.getAnswer());
        quesLineItem.setComments(entity.getComment());
        quesLineItem.setHelpText(entity.getHelpText());
      //  quesLineItem.setName(model.get);
        quesLineItem.setPicklistOtions(entity.getPickListOpts());
        quesLineItem.setType(entity.getType());
        quesLineItem.setQuestion(entity.getQuestion());
        return quesLineItem;
    }

    @Override
    public void updateEntityWithModel(QuestionriesProposalLineItem toUpdate, QuesProcessLineItemModel model) {
    	
    }
}
