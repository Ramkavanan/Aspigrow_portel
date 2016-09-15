/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.persistence.entities.sObject.Opportunity;
import com.aspigrow.portel.model.OpportunityModel;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("opportunityModelConvertor")
public class OpportunityModelConvertor implements Convertor<OpportunityModel, Opportunity> {
	
    @Override
    public  Opportunity convertToEntity(OpportunityModel model) {
        if (model == null) {
            return null;
        }
        Opportunity opportunity = new Opportunity();
        opportunity.setExternalId(model.getExternalId());
        return opportunity;
    }

    @Override
    public OpportunityModel convertToModel(Opportunity entity) {
        if (entity == null) {
            return null;
        }
        OpportunityModel opportunity = new OpportunityModel();
        opportunity.setExternalId(entity.getExternalId());
        return opportunity;
    }

    @Override
    public void updateEntityWithModel(Opportunity toUpdate, OpportunityModel model) {
    	
    }
}
