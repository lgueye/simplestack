package io.agileinfra.simplestack.shared.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class SimpleStackSharedConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return Jackson2ObjectMapperBuilder
            .json() //
            .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // use ISODate and not other cryptic formats
            .featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS) // allow empty json to be produced
            .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // do not fail on unknown properties because they are likely to
            .modules(new JavaTimeModule()) //
            .build();
    }
}
