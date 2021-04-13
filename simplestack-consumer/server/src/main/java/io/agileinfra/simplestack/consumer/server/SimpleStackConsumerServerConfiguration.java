package io.agileinfra.simplestack.consumer.server;

import io.agileinfra.simplestack.messaging.api.EventProcessor;
import io.agileinfra.simplestack.messaging.consumer.SimpleStackMessagingConsumerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(SimpleStackMessagingConsumerConfiguration.class)
@Configuration
public class SimpleStackConsumerServerConfiguration {

    @Bean
    public EventStore eventStore() {
        return new EventStore();
    }

    @Bean
    public EventProcessor eventProcessor(final EventStore eventStore) {
        return new EventProcessorImpl(eventStore);
    }
}
