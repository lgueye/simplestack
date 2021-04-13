package io.agileinfra.simplestack.messaging.consumer;

import io.agileinfra.simplestack.messaging.api.EventProcessor;
import io.agileinfra.simplestack.messaging.api.SimpleStackEventConsumerApi;
import io.agileinfra.simplestack.model.EventDto;
import java.time.Clock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@RequiredArgsConstructor
public class SimpleStackEventConsumerApiImpl implements SimpleStackEventConsumerApi {
    private final Clock clock;
    private final EventProcessor processor;

    @JmsListener(destination = "events", containerFactory = "queueListenerContainerFactory")
    @Override
    public void onEvent(@Payload EventDto message) {
        log.info("Incoming message {}", message);
        final EventDto event = message.toBuilder().timestamp(clock.instant()).build();
        processor.process(event);
    }
}
