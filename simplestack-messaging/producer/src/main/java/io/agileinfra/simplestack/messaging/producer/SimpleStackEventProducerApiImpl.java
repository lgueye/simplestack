package io.agileinfra.simplestack.messaging.producer;

import io.agileinfra.simplestack.messaging.api.SimpleStackEventProducerApi;
import io.agileinfra.simplestack.model.EventDto;
import javax.jms.Queue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

@Slf4j
@RequiredArgsConstructor
public class SimpleStackEventProducerApiImpl implements SimpleStackEventProducerApi {
    private final JmsTemplate jmsTemplate;

    @Override
    public void createEvent(EventDto message) {
        log.info("Outgoing message {}", message);
        jmsTemplate.convertAndSend((Queue) () -> "events", message);
    }
}
