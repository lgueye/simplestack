package io.agileinfra.simplestack.model;

import java.time.Instant;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class EventDto {
    private String id;
    private String value;
    private Instant timestamp;
}
