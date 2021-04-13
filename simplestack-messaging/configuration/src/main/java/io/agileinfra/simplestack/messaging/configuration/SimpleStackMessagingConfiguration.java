package io.agileinfra.simplestack.messaging.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.simplestack.shared.configuration.SimpleStackSharedConfiguration;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Import(SimpleStackSharedConfiguration.class)
@Configuration
public class SimpleStackMessagingConfiguration {
    private static final String MESSAGING_SERVER_URL_KEY = "messaging.server.url";

    @Bean
    public ConnectionFactory connectionFactory(@Value("${" + MESSAGING_SERVER_URL_KEY + "}") String brokerUrl) {
        return new ActiveMQConnectionFactory(brokerUrl);
    }

    @Bean
    public MessageConverter converter(final ObjectMapper objectMapper) {
        final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
