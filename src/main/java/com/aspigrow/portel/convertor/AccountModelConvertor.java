/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.convertor;

import org.springframework.stereotype.Component;

import com.aspigrow.persistence.entities.sObject.Account;
import com.aspigrow.portel.model.AccountModel;

/**
 * This convertor used to convert the UI model object to Database entity object
 * and vice versa.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Component("accountModelConvertor")
public class AccountModelConvertor implements Convertor<AccountModel, Account> {
	
    @Override
    public  Account convertToEntity(AccountModel model) {
        if (model == null) {
            return null;
        }
        Account account = new Account();
        account.setAcctNumber(model.getAccNumber());
        account.setName(model.getAccName());
        account.setSite(model.getAccSite());
        account.setExternalId(model.getAccId());
        return account;
    }

    @Override
    public AccountModel convertToModel(Account entity) {
        if (entity == null) {
            return null;
        }
        AccountModel account = new AccountModel();
        account.setAccId(entity.getExternalId());
        account.setAccName(entity.getSite());
        account.setAccNumber(entity.getAcctNumber());
        return account;
    }

    @Override
    public void updateEntityWithModel(Account toUpdate, AccountModel model) {
    	
    }
}
