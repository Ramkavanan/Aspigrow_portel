/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.AccountDao;
import com.aspigrow.portel.convertor.AccountModelConvertor;
import com.aspigrow.portel.model.AccountModel;
import com.aspigrow.portel.service.AccountService;

/**
 * Implementation of account service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;
	
	private AccountModelConvertor convertor;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao, AccountModelConvertor convertor) {
        this.accountDao = accountDao;
        this.convertor = convertor;
    }

    @Override
    public AccountModel save(AccountModel model) throws Exception {
    	try{
    		if (model == null)
    			return null;
    		model = convertor.convertToModel(accountDao.saveAccount(convertor.convertToEntity(model)));
    		return model;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		System.out.println(ex.getMessage());
    	}
	
    	return null;
    }
	
	@Override
	public AccountModel update(AccountModel model) throws Exception {
		try{
			if(model.getExternalId() == null)
			 	throw new Exception("Prohibitted operation");
			model = convertor.convertToModel(accountDao.updateAccount(convertor.convertToEntity(model)));
			return model;
			
		} catch(Exception ex) {
			return null;		
		}	
	}

	@Override
	public boolean delete(AccountModel account) {
		try{
			if(account == null || account.getExternalId() == null)
				return false;
			return 	accountDao.deleteAccount(Long.valueOf(account.getExternalId()));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean delete(String accountId) {
		try {
			if(accountId == null) 
				return false;
			return 	accountDao.deleteAccount(Long.valueOf(accountId));	
		} catch(Exception ex) {
			return false;
		}
	}

	@Override
	public AccountModel getAccountById(String accountId) {
		try{
			if(accountId == null || accountId.length() <= 0)
				return null;
			return 	convertor.convertToModel(accountDao.getAccount(accountId));	
		} catch(Exception ex) {
			return null;
		}
	}
}
