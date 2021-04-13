package io.agileinfra.simplestack.consumer.client;

import io.agileinfra.simplestack.consumer.api.SimpleStackConsumerApi;
import io.agileinfra.simplestack.model.EventDto;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
public class SimpleStackConsumerApiImpl implements SimpleStackConsumerApi {
    private final String apiUrl;
    private final RestTemplate restTemplate;

    @Override
    public List<EventDto> findAll() {
        final URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl).path("/api/v1/events").build().toUri();
        final HttpHeaders headers = new HttpHeaders();
        final ResponseEntity<List<EventDto>> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            new ParameterizedTypeReference<>() {}
        );
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        return responseEntity.getBody();
    }
}
