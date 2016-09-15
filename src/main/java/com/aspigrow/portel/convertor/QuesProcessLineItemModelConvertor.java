/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.portel.model.QuesProcessLineItemModel;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;

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
        return quesLineItem;
    }

    @Override
    public QuesProcessLineItemModel convertToModel(QuestionriesProposalLineItem entity) {
        if (entity == null) {
            return null;
        }
        QuesProcessLineItemModel quesLineItem = new QuesProcessLineItemModel();
        quesLineItem.setExternalId(entity.getExternalId());
        return quesLineItem;
    }

    @Override
    public void updateEntityWithModel(QuestionriesProposalLineItem toUpdate, QuesProcessLineItemModel model) {
    	
    }
}
