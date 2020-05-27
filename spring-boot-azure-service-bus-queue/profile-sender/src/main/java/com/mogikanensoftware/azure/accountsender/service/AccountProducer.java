
package com.mogikanensoftware.azure.accountsender.service;

import com.mogikanensoftware.azure.accountsender.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountProducer {

    private JmsTemplate jmsTemplate;

    private ObjectMapper mapper;

    public AccountProducer(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.mapper = new ObjectMapper();
    }

    public void produce(Account account) throws Exception {

        String msg = this.mapper.writeValueAsString(account);
        jmsTemplate.convertAndSend("profilequeu", msg);
        log.info("message sent {}", msg);
    }

}