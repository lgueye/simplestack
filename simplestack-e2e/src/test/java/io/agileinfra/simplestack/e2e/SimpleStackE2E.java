package io.agileinfra.simplestack.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.agileinfra.simplestack.consumer.api.SimpleStackConsumerApi;
import io.agileinfra.simplestack.model.EventDto;
import io.agileinfra.simplestack.producer.api.SimpleStackProducerApi;
import java.time.Duration;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.util.Sets;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author louis.gueye@gmail.com
 */
@SpringJUnitConfig(classes = SimpleStackE2EConfiguration.class)
public class SimpleStackE2E {
    private final SimpleStackProducerApi producerApi;
    private final SimpleStackConsumerApi consumerApi;

    @Autowired
    public SimpleStackE2E(final SimpleStackProducerApi producerApi, final SimpleStackConsumerApi consumerApi) {
        this.consumerApi = consumerApi;
        this.producerApi = producerApi;
    }

    @Test
    public void e2e_ok() {
        // Given
        final int eventsCount = 100;

        // When
        final Set<EventDto> events = IntStream
            .range(0, eventsCount)
            .boxed()
            .map(
                index -> {
                    final String id = UUID.randomUUID().toString();
                    final String value = RandomStringUtils.randomAlphanumeric(20);
                    return EventDto.builder().id(id).value(value).build();
                }
            )
            .collect(Collectors.toSet());
        events.forEach(producerApi::create);
        Awaitility.await().atMost(Duration.ofSeconds(2)).until(() -> consumerApi.findAll().size() == eventsCount);

        // Then
        assertEquals(Sets.newHashSet(consumerApi.findAll()), events);
    }
}
