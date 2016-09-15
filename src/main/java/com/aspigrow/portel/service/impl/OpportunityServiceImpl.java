/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.OpportunityDao;
import com.aspigrow.portel.convertor.OpportunityModelConvertor;
import com.aspigrow.portel.model.OpportunityModel;
import com.aspigrow.portel.service.OpportunityService;

/**
 * Implementation of opportunity service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("opportunityService")
public class OpportunityServiceImpl implements OpportunityService {

	private OpportunityDao opportunityDao;
	
	private OpportunityModelConvertor convertor;

    @Autowired
    public OpportunityServiceImpl(OpportunityDao opportunityDao, OpportunityModelConvertor convertor) {
        this.opportunityDao = opportunityDao;
        this.convertor = convertor;
    }

    @Override
    public OpportunityModel save(OpportunityModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(opportunityDao.saveOpportunity(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public OpportunityModel update(OpportunityModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(opportunityDao.updateOpportunity(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(OpportunityModel opportunity) {
		try{
			if(opportunity == null || opportunity.getExternalId() == null)
				return false;
			return 	opportunityDao.deleteOpportunity(Long.valueOf(opportunity.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String opportunityId) {
		try {
			if(opportunityId == null) 
				return false;
			return 	opportunityDao.deleteOpportunity(Long.valueOf(opportunityId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public OpportunityModel getOpportunityById(String opportunityId) {
		try{
			if(opportunityId == null || opportunityId.length() <= 0)
				return null;
			return 	convertor.convertToModel(opportunityDao.getOpportunity(opportunityId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
