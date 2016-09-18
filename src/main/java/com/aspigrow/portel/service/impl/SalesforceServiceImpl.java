/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.AccountDao;
import com.aspigrow.persistence.dao.ContactDao;
import com.aspigrow.persistence.dao.OpportunityDao;
import com.aspigrow.persistence.dao.QuestionriesProposalHeaderDao;
import com.aspigrow.persistence.dao.QuestionriesProposalLineItemDao;
import com.aspigrow.persistence.entities.sObject.Account;
import com.aspigrow.persistence.entities.sObject.Contact;
import com.aspigrow.persistence.entities.sObject.Opportunity;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;
import com.aspigrow.portel.convertor.AccountModelConvertor;
import com.aspigrow.portel.convertor.ContactModelConvertor;
import com.aspigrow.portel.convertor.OpportunityModelConvertor;
import com.aspigrow.portel.convertor.QuesProcessHeaderModelConvertor;
import com.aspigrow.portel.convertor.QuesProcessLineItemModelConvertor;
import com.aspigrow.portel.model.AccountModel;
import com.aspigrow.portel.model.ContactModel;
import com.aspigrow.portel.model.OpportunityModel;
import com.aspigrow.portel.model.QuesProcessHeaderModel;
import com.aspigrow.portel.model.QuesProcessLineItemModel;
import com.aspigrow.portel.service.SalesforceService;

/**
 * Implementation of account service functionality
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Service("salesforceService")
public class SalesforceServiceImpl implements SalesforceService {
	
	private AccountDao accountDao;
	
	private ContactDao contactDao;
	
	private OpportunityDao opportunityDao;
	
	private QuestionriesProposalHeaderDao quesProplHeaderDao;
	
	private QuestionriesProposalLineItemDao quesProplLineItemDao;
	
	private AccountModelConvertor accountConvertor;
	
	private ContactModelConvertor contactConvertor;
	
	private OpportunityModelConvertor opptConvertor;
	
	private QuesProcessHeaderModelConvertor quesHeaderConvertor;
	
	private QuesProcessLineItemModelConvertor quesLineItemConvertor;
	
	@Autowired
	public SalesforceServiceImpl(AccountDao accountDao, ContactDao contactDao, OpportunityDao opportunityDao, 
			QuestionriesProposalHeaderDao quesProplHeaderDao, QuestionriesProposalLineItemDao quesProplLineItemDao, 
			AccountModelConvertor accountConvertor, ContactModelConvertor contactConvertor, OpportunityModelConvertor opptConvertor, 
			QuesProcessHeaderModelConvertor quesHeaderConvertor, QuesProcessLineItemModelConvertor quesLineItemConvertor) {
		this.accountDao = accountDao;
		this.contactDao = contactDao;
		this.opportunityDao = opportunityDao;
		this.quesProplHeaderDao = quesProplHeaderDao;
		this.quesProplLineItemDao = quesProplLineItemDao;
		this.accountConvertor = accountConvertor;
		this.contactConvertor = contactConvertor;
		this.opptConvertor = opptConvertor;
		this.quesHeaderConvertor = quesHeaderConvertor;
		this.quesLineItemConvertor = quesLineItemConvertor;
	}

	@Override
	public boolean importSalesforceData(AccountModel account) throws Exception {
		try{
			Account acct = accountConvertor.convertToEntity(account);
			OpportunityModel optModel = account.getOpportunity();
			Opportunity oppt = opptConvertor.convertToEntity(optModel);
			ContactModel[] contactModels = account.getContact();
			QuesProcessHeaderModel qphm = optModel.getQuestProcessHeader();
			QuestionriesProposalHeader quesHeader = quesHeaderConvertor.convertToEntity(qphm);
			Set<QuestionriesProposalLineItem> lineItemSet = getQuesLineItemSet(qphm.getQuestProcessLineItems());
			if(lineItemSet != null)
				quesHeader.setQuesProcessLineItems(lineItemSet);
			quesHeader = quesProplHeaderDao.save(quesHeader);
			oppt.setQuesProcessHeader(quesHeader);
			oppt = opportunityDao.save(oppt);
			Set<Contact> contactSet = getContactSet(contactModels);
			if(contactSet != null)
				acct.setContacts(contactSet);
			acct.setOpportunity(oppt);
			acct = accountDao.save(acct);
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private Set<Contact> getContactSet(ContactModel[] contacts) throws Exception {
		try{
			Set<Contact> contactSet = new HashSet<Contact>();
			for(ContactModel contact : contacts) {
				 Contact cont = contactConvertor.convertToEntity(contact);
				 cont.setArchived(false);
				 cont.setDeleted(false);
				 cont.setCreatedAt(new Date());
				 cont.setExternalId(UUID.randomUUID().toString());
				 cont.setModifiedAt(new Date());
				 contactSet.add(cont);
			}
			return contactSet;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private Set<QuestionriesProposalLineItem> getQuesLineItemSet(QuesProcessLineItemModel[] lineItems) throws Exception {
		try{
			Set<QuestionriesProposalLineItem> lineItemSet = new HashSet<QuestionriesProposalLineItem>();
			for(QuesProcessLineItemModel lineItem : lineItems) {
				QuestionriesProposalLineItem quesProplLineItem = quesLineItemConvertor.convertToEntity(lineItem);
				 quesProplLineItem.setArchived(false);
		         quesProplLineItem.setDeleted(false);
		         quesProplLineItem.setCreatedAt(new Date());
		         quesProplLineItem.setExternalId(UUID.randomUUID().toString());
		         quesProplLineItem.setModifiedAt(new Date());
				lineItemSet.add(quesProplLineItem);
			}
			return lineItemSet;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
 