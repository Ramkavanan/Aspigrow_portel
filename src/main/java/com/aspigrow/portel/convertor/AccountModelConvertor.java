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
        account.setAcctNumber(model.getAccountNumber());
        account.setName(model.getName());
        account.setSite(model.getSite());
        account.setExternalId(model.getExternalId());
        return account;
    }

    @Override
    public AccountModel convertToModel(Account entity) {
        if (entity == null) {
            return null;
        }
        AccountModel account = new AccountModel();
        account.setExternalId(entity.getExternalId());
        account.setAccountNumber(entity.getAcctNumber());
        account.setName(entity.getName());
        account.setSite(entity.getSite());
        return account;
    }

    @Override
    public void updateEntityWithModel(Account toUpdate, AccountModel model) {
    	
    }
}
