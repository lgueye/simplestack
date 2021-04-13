package io.agileinfra.simplestack.consumer.server;

import io.agileinfra.simplestack.consumer.api.SimpleStackConsumerApi;
import io.agileinfra.simplestack.model.EventDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventResource implements SimpleStackConsumerApi {
    private final EventStore eventStore;

    @GetMapping
    @Override
    public List<EventDto> findAll() {
        return eventStore.findAll();
    }
}
