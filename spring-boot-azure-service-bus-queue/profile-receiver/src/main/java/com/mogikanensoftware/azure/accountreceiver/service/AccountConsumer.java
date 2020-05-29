
package com.mogikanensoftware.azure.accountreceiver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogikanensoftware.azure.accountreceiver.dao.AccountRepository;
import com.mogikanensoftware.azure.accountreceiver.entity.Account;
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

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String msg) {
        log.info("Received message: {}", msg);
        AccountMessage am;
        try {
            am = this.objectMapper.readValue(msg, AccountMessage.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Invalid account message");
        }
        Account ae = new Account();
        ae.setBankName(am.getBankName());
        ae.setNumber(am.getNumber());
        ae.setOpenDate(am.getOpenDate());
        this.accountRepository.save(ae);
    }

}