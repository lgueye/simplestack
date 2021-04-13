package io.agileinfra.simplestack.messaging.api;

import io.agileinfra.simplestack.model.EventDto;

public interface SimpleStackEventConsumerApi {
    void onEvent(final EventDto event);
}
