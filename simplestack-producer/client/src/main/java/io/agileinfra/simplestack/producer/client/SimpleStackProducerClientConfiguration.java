package io.agileinfra.simplestack.producer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.agileinfra.simplestack.producer.api.SimpleStackProducerApi;
import io.agileinfra.simplestack.shared.configuration.SimpleStackSharedConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Import(SimpleStackSharedConfiguration.class)
@Configuration
public class SimpleStackProducerClientConfiguration {

    @Bean
    public SimpleStackProducerApi simpleStackProducerApi(@Value("${producer.server.url}") final String apiUrl, final ObjectMapper objectMapper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
            .getMessageConverters()
            .stream()
            .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
            .forEach(converter -> ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(objectMapper));
        return new SimpleStackProducerApiImpl(apiUrl, restTemplate);
    }
}
