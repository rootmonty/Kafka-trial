package is.kafka.trial.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Data
@Getter
@Setter
@Entity(name = "event")
@NoArgsConstructor
public class AnalyticEvent {

    @Id
    String id;
    @Column(name = "name")
    String name;
    @Column(name = "timestamp")
    ZonedDateTime timestamp = ZonedDateTime.now();
}
