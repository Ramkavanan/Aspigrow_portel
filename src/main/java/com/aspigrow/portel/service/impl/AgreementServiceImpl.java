/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.AgreementDao;
import com.aspigrow.portel.convertor.AgreementModelConvertor;
import com.aspigrow.portel.model.AgreementModel;
import com.aspigrow.portel.service.AgreementService;

/**
 * Implementation of agreement service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("agreementService")
public class AgreementServiceImpl implements AgreementService {

	private AgreementDao agreementDao;
	
	private AgreementModelConvertor convertor;

    @Autowired
    public AgreementServiceImpl(AgreementDao agreementDao, AgreementModelConvertor convertor) {
        this.agreementDao = agreementDao;
        this.convertor = convertor;
    }

    @Override
    public AgreementModel save(AgreementModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(agreementDao.saveAgreement(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public AgreementModel update(AgreementModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(agreementDao.updateAgreement(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(AgreementModel agreement) {
		try{
			if(agreement == null || agreement.getExternalId() == null)
				return false;
			return 	agreementDao.deleteAgreement(Long.valueOf(agreement.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String agreementId) {
		try {
			if(agreementId == null) 
				return false;
			return 	agreementDao.deleteAgreement(Long.valueOf(agreementId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public AgreementModel getAgreementById(String agreementId) {
		try{
			if(agreementId == null || agreementId.length() <= 0)
				return null;
			return 	convertor.convertToModel(agreementDao.getAgreement(agreementId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
