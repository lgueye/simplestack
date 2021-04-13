package io.agileinfra.simplestack.consumer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.simplestack.consumer.api.SimpleStackConsumerApi;
import io.agileinfra.simplestack.shared.configuration.SimpleStackSharedConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Import(SimpleStackSharedConfiguration.class)
@Configuration
public class SimpleStackConsumerClientConfiguration {

    @Bean
    public SimpleStackConsumerApi simpleStackConsumerApi(@Value("${consumer.server.url}") final String apiUrl, final ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
            .getMessageConverters()
            .stream()
            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
            .forEach(converter -> ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper));
        return new SimpleStackConsumerApiImpl(apiUrl, restTemplate);
    }
}
