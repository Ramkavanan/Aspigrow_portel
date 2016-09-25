/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package com.aspigrow.portel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.aspigrow.persistence.dao.AccountDao;
import com.aspigrow.persistence.dao.OpportunityDao;
import com.aspigrow.persistence.dao.QuestionriesProposalHeaderDao;
import com.aspigrow.persistence.dao.QuestionriesProposalLineItemDao;
import com.aspigrow.persistence.dao.UserDao;
import com.aspigrow.persistence.entities.sObject.Account;
import com.aspigrow.persistence.entities.sObject.Contact;
import com.aspigrow.persistence.entities.sObject.Opportunity;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalHeader;
import com.aspigrow.persistence.entities.sObject.QuestionriesProposalLineItem;
import com.aspigrow.persistence.entities.user.User;
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
	
	private UserDao userDao;
	
	private OpportunityDao opportunityDao;
	
	private QuestionriesProposalHeaderDao quesProplHeaderDao;
	
	private MailSender mailSender;
	
	private AccountModelConvertor accountConvertor;
	
	private ContactModelConvertor contactConvertor;
	
	private OpportunityModelConvertor opptConvertor;
	
	private QuesProcessHeaderModelConvertor quesHeaderConvertor;
	
	private QuesProcessLineItemModelConvertor quesLineItemConvertor;
	
	@Autowired
	public SalesforceServiceImpl(AccountDao accountDao, UserDao userDao, OpportunityDao opportunityDao, 
			QuestionriesProposalHeaderDao quesProplHeaderDao, MailSender mailSender, 
			AccountModelConvertor accountConvertor, ContactModelConvertor contactConvertor, OpportunityModelConvertor opptConvertor, 
			QuesProcessHeaderModelConvertor quesHeaderConvertor, QuesProcessLineItemModelConvertor quesLineItemConvertor) {
		this.accountDao = accountDao;
		this.userDao = userDao;
		this.opportunityDao = opportunityDao;
		this.quesProplHeaderDao = quesProplHeaderDao;
		this.mailSender = mailSender;
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
			Set<QuestionriesProposalLineItem> lineItemSet = getQuesLineItemSet(qphm.getQuestProcessLineItems(), quesHeader);
			if(lineItemSet != null)
				quesHeader.setQuesProcessLineItems(lineItemSet);
			quesHeader = quesProplHeaderDao.save(quesHeader);
			oppt.setQuesProcessHeader(quesHeader);
			oppt = opportunityDao.save(oppt);
			Set<Contact> contactSet = getContactSet(contactModels, acct);
			if(contactSet != null)
				acct.setContacts(contactSet);
			acct.setOpportunity(oppt);
			acct = accountDao.save(acct);
			if(!insertUser(acct.getContacts()))
				return false;
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private boolean insertUser(Set<Contact> contacts) throws Exception {
		Random randomGenerator = new Random();
		List<User> users = new ArrayList<User>();
		try{
			for(Contact cont : contacts) {
				System.out.println("Contacts Phone number and other infor ---- ");
				System.out.println("Contact Phone : "+cont.getPhone()+" ee "+cont.getEmail());
				User user = new User(cont.getPhone(), cont.getEmail());
				//user.setPhone(cont.getPhone());
				//user.setEmail(cont.getEmail());
				//user.setUserName(cont.getEmail());
				int random = randomGenerator .nextInt(4);
				String password = cont.getEmail().trim()+""+random;
				user.setPassword(password);
				user.setContact(cont);
				user = userDao.save(user);
				users.add(user);
				SimpleMailMessage sm = new SimpleMailMessage();
	        	sm.setTo(user.getEmail());
	        	sm.setFrom("support@aspigrow.com");
	        	sm.setSubject("Hi");
	        	sm.setText("Please use this credential to login our service portel <br/>"
	        			+ "<b> User Name </b> :  "+user.getEmail()+"<br/> Password : "
	        			+password+" <br/> Thank you so much for interested in our portel");
	        	mailSender.send(sm);
			}
			System.out.println("Inserted users list --- "+users.toString());
			return true;
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private Set<Contact> getContactSet(ContactModel[] contacts, Account acct) throws Exception {
		try{
			Set<Contact> contactSet = new HashSet<Contact>();
			for(ContactModel contact : contacts) {
				 Contact cont = contactConvertor.convertToEntity(contact);
				 cont.setArchived(false);
				 cont.setDeleted(false);
				 cont.setCreatedAt(new Date());
				 cont.setExternalId(UUID.randomUUID().toString());
				 cont.setModifiedAt(new Date());
				 cont.setAccount(acct);
				 contactSet.add(cont);
			}
			return contactSet;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	private Set<QuestionriesProposalLineItem> getQuesLineItemSet(QuesProcessLineItemModel[] lineItems, QuestionriesProposalHeader header) throws Exception {
		try{
			Set<QuestionriesProposalLineItem> lineItemSet = new HashSet<QuestionriesProposalLineItem>();
			for(QuesProcessLineItemModel lineItem : lineItems) {
				QuestionriesProposalLineItem quesProplLineItem = quesLineItemConvertor.convertToEntity(lineItem);
				 quesProplLineItem.setArchived(false);
		         quesProplLineItem.setDeleted(false);
		         quesProplLineItem.setCreatedAt(new Date());
		         quesProplLineItem.setExternalId(UUID.randomUUID().toString());
		         quesProplLineItem.setModifiedAt(new Date());
		         quesProplLineItem.setQuesProcessHeader(header);
				lineItemSet.add(quesProplLineItem);
			}
			return lineItemSet;
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
 