package io.agileinfra.simplestack.messaging.api;

import io.agileinfra.simplestack.model.EventDto;

public interface EventProcessor {
    void process(EventDto event);
}
