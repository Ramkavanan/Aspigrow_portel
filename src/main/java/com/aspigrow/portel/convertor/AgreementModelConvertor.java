/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.persistence.entities.sObject.Agreement;
import com.aspigrow.portel.model.AgreementModel;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("agreementModelConvertor")
public class AgreementModelConvertor implements Convertor<AgreementModel, Agreement> {
	
    @Override
    public  Agreement convertToEntity(AgreementModel model) {
        if (model == null) {
            return null;
        }
        Agreement agreement = new Agreement();
        agreement.setExternalId(model.getExternalId());
        agreement.setContractName(model.getCF_Contract_Name__c());
        agreement.setContractNumber(model.getName());
        return agreement;
    }

    @Override
    public AgreementModel convertToModel(Agreement entity) {
        if (entity == null) {
            return null;
        }
        AgreementModel agreement = new AgreementModel();
        agreement.setExternalId(entity.getExternalId());
        return agreement;
    }

    @Override
    public void updateEntityWithModel(Agreement toUpdate, AgreementModel model) {
    	
    }
}
