/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.portel.model.QuesProcessHeaderModel;
import com.aspigrow.portel.model.QuesProcessLineItemModel;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("quesHeaderModelConvertor")
public class QuesProcessHeaderModelConvertor implements Convertor<QuesProcessHeaderModel, QuestionriesProposalHeader> {
	
    @Override
    public  QuestionriesProposalHeader convertToEntity(QuesProcessHeaderModel model) {
        if (model == null) {
            return null;
        }
        QuestionriesProposalHeader quesHeader = new QuestionriesProposalHeader();
        quesHeader.setExternalId(model.getExternalId());
        quesHeader.setName(model.getQuesName());
        quesHeader.setStatus(model.getStatus());
        quesHeader.setQuesName(model.getQuesName());
        quesHeader.setContact(model.getContact());
        return quesHeader;
    }

    @Override
    public QuesProcessHeaderModel convertToModel(QuestionriesProposalHeader entity) {
        if (entity == null) {
            return null;
        }
        QuesProcessHeaderModel quesHeader = new QuesProcessHeaderModel();
        quesHeader.setExternalId(entity.getExternalId());
        quesHeader.setQuesName(entity.getName());
        quesHeader.setStatus(entity.getStatus());
        quesHeader.setContact(entity.getContact());
        quesHeader.setQuesName(entity.getQuesName());
        return quesHeader;
    }

    @Override
    public void updateEntityWithModel(QuestionriesProposalHeader toUpdate, QuesProcessHeaderModel model) {
    	if(model != null) {
    		if(model.getStatus() != null ) {
    			toUpdate.setStatus(model.getStatus());
    		}
    		if(model.getExternalId() != null ) {
    			toUpdate.setExternalId(model.getExternalId());
    		}
    		if(model.getQuesName() != null ) {
    			toUpdate.setQuesName(model.getQuesName());
    		}
    	}
    }
}
