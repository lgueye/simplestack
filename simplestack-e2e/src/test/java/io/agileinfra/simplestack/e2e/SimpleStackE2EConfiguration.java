package io.agileinfra.simplestack.e2e;

import io.agileinfra.simplestack.consumer.client.SimpleStackConsumerClientConfiguration;
import io.agileinfra.simplestack.producer.client.SimpleStackProducerClientConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author louis.gueye@gmail.com
 */
@Import({ SimpleStackProducerClientConfiguration.class, SimpleStackConsumerClientConfiguration.class })
@Configuration
public class SimpleStackE2EConfiguration {}
