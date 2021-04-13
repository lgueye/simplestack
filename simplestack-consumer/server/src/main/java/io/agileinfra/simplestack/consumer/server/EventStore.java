package io.agileinfra.simplestack.consumer.server;

import io.agileinfra.simplestack.model.EventDto;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventStore {
    private Map<String, EventDto> store = new HashMap<>();

    public void save(EventDto event) {
        store.putIfAbsent(event.getId(), event);
        log.info("Stored {}", event);
    }

    public List<EventDto> findAll() {
        return store.values().stream().sorted(Comparator.comparing(EventDto::getTimestamp)).collect(Collectors.toList());
    }
}
