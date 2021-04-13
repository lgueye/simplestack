package io.agileinfra.simplestack.producer.server;

import io.agileinfra.simplestack.messaging.api.SimpleStackEventProducerApi;
import io.agileinfra.simplestack.model.EventDto;
import io.agileinfra.simplestack.producer.api.SimpleStackProducerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventResource implements SimpleStackProducerApi {
    private final SimpleStackEventProducerApi simpleStackEventProducerApi;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void create(@RequestBody EventDto event) {
        simpleStackEventProducerApi.createEvent(event);
    }
}
