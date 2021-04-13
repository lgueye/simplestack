package io.agileinfra.simplestack.messaging.consumer;

import io.agileinfra.simplestack.messaging.api.EventProcessor;
import io.agileinfra.simplestack.messaging.api.SimpleStackEventConsumerApi;
import io.agileinfra.simplestack.messaging.configuration.SimpleStackMessagingConfiguration;
import java.time.Clock;
import javax.jms.ConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MessageConverter;

@EnableJms
@Import(SimpleStackMessagingConfiguration.class)
@Configuration
public class SimpleStackMessagingConsumerConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(
        final ConnectionFactory connectionFactory,
        final MessageConverter messageConverter,
        final DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainerFactory(
        final ConnectionFactory connectionFactory,
        final MessageConverter messageConverter,
        final DefaultJmsListenerContainerFactoryConfigurer configurer
    ) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    public SimpleStackEventConsumerApi simpleStackEventConsumerApi(final Clock clock, final EventProcessor processor) {
        return new SimpleStackEventConsumerApiImpl(clock, processor);
    }
}
