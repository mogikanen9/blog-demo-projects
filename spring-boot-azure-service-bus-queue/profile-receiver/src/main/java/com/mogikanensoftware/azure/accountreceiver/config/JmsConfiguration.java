
package com.mogikanensoftware.azure.accountreceiver.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.util.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JmsConfiguration {

    @Bean
    public JmsListenerContainerFactory myJmsListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setSessionAcknowledgeMode(javax.jms.Session.CLIENT_ACKNOWLEDGE);

        // anonymous class
        jmsListenerContainerFactory.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void handleError(Throwable t) {
                       log.error("An error has occurred in the transaction");
                    }
                });

        // lambda function
        jmsListenerContainerFactory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));
        return jmsListenerContainerFactory;
    }

}