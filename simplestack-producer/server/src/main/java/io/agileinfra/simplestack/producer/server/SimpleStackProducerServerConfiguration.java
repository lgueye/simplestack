package io.agileinfra.simplestack.producer.server;

import io.agileinfra.simplestack.messaging.producer.SimpleStackMessagingProducerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(SimpleStackMessagingProducerConfiguration.class)
@Configuration
public class SimpleStackProducerServerConfiguration {}
