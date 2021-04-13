package io.agileinfra.simplestack.messaging.api;

import io.agileinfra.simplestack.model.EventDto;

public interface SimpleStackEventProducerApi {
    void createEvent(final EventDto event);
}
