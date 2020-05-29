
package com.mogikanensoftware.azure.accountreceiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountConsumer {

    private static final String QUEUE_NAME = "accountqueue";

    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String msg) {
        log.info("Received message: {}", msg);
    }
}