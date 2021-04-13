package io.agileinfra.simplestack.producer.api;

import io.agileinfra.simplestack.model.EventDto;

public interface SimpleStackProducerApi {
    void create(final EventDto event);
}
