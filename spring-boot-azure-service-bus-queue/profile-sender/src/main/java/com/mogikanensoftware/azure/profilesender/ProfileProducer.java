
package com.mogikanensoftware.azure.profilesender;

import java.time.Instant;

import com.microsoft.azure.servicebus.IQueueClient;
import com.microsoft.azure.servicebus.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProfileProducer implements Ordered {

    @Autowired
    private final IQueueClient iQueueClient;

    public ProfileProducer(final IQueueClient iQueueClient) {
        this.iQueueClient = iQueueClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() throws Exception {
        Message msg = new Message("Hello @ " + Instant.now().toString());
        this.iQueueClient.send(msg);
        log.info("message sent {}", msg);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}