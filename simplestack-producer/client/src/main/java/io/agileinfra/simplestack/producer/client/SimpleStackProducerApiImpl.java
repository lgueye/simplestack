package io.agileinfra.simplestack.producer.client;

import io.agileinfra.simplestack.model.EventDto;
import io.agileinfra.simplestack.producer.api.SimpleStackProducerApi;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class SimpleStackProducerApiImpl implements SimpleStackProducerApi {
    private final String apiUrl;
    private final RestTemplate restTemplate;

    @Override
    public void create(EventDto event) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl).path("/api/v1/events").build().toUri();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final ResponseEntity<Void> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(event, headers), Void.class);
        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }
}
