package io.agileinfra.simplestack.consumer.server;

import io.agileinfra.simplestack.messaging.api.EventProcessor;
import io.agileinfra.simplestack.model.EventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class EventProcessorImpl implements EventProcessor {
    private final EventStore store;

    @Override
    public void process(EventDto event) {
        store.save(event);
    }
}
