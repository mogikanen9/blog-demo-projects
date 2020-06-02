
package com.mogikanensoftware.azure.accountreceiver.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogikanensoftware.azure.accountreceiver.dao.AccountRepository;
import com.mogikanensoftware.azure.accountreceiver.entity.Account;
import com.mogikanensoftware.azure.accountreceiver.entity.Branch;
import com.mogikanensoftware.azure.accountreceiver.model.AccountMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountConsumer {

    private static final String QUEUE_NAME = "accountqueue";

    private ObjectMapper objectMapper;
    private AccountRepository accountRepository;

    public AccountConsumer() {
        this.objectMapper = new ObjectMapper();
    }

    @Autowired
    public AccountConsumer(AccountRepository accountRepository) {
        this();
        this.accountRepository = accountRepository;
    }

    @JmsListener(destination = QUEUE_NAME, containerFactory = "myJmsListenerContainerFactory")
    public void receiveMessage(Message msg, Session session) {

        log.info("Received message: {}", msg);
        log.info("Received session: {}", session);      
        
        try {

            log.info("session.getAcknowledgeMode(): {}", session.getAcknowledgeMode());

            String body = msg.getBody(String.class);

            AccountMessage am = this.objectMapper.readValue(body, AccountMessage.class);
                if (am.getProfileId().equals("123")) {
                    throw new RuntimeException("Invalid profile");
                }
           
            Account ae = new Account();
            ae.setBankName(am.getBankName());
            ae.setNumber(am.getNumber());
            ae.setOpenDate(am.getOpenDate());

            if (am.getBranch() != null) {
                ae.setBranch(new Branch(am.getBranch().getName(), am.getBranch().getCode(), am.getBranch().getLocation()));
            }

            this.accountRepository.save(ae);

            msg.acknowledge();
        } catch (JsonProcessingException | JMSException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }

    }

}