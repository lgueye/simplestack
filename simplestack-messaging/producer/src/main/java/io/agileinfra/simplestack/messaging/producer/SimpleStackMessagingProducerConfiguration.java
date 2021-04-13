package io.agileinfra.simplestack.messaging.producer;

import io.agileinfra.simplestack.messaging.api.SimpleStackEventProducerApi;
import io.agileinfra.simplestack.messaging.configuration.SimpleStackMessagingConfiguration;
import javax.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Import(SimpleStackMessagingConfiguration.class)
@Configuration
public class SimpleStackMessagingProducerConfiguration {

    @Bean
    public JmsTemplate jmsTemplate(final ConnectionFactory connectionFactory, final MessageConverter messageConverter) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(messageConverter);
        return jmsTemplate;
    }

    @Bean
    public SimpleStackEventProducerApi simpleStackEventProducerApi(final JmsTemplate jmsTemplate) {
        return new SimpleStackEventProducerApiImpl(jmsTemplate);
    }
}
