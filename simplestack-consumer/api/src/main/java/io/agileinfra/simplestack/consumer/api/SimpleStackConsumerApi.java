package io.agileinfra.simplestack.consumer.api;

import io.agileinfra.simplestack.model.EventDto;
import java.util.List;

public interface SimpleStackConsumerApi {
    List<EventDto> findAll();
}
