package is.kafka.trial.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is.kafka.trial.model.AnalyticEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
    KafkaTemplate<String, Object> template;
    @Autowired
    private ObjectMapper objectMapper;

    public void queueEvent(AnalyticEvent event) {
        try {
            String eventString = objectMapper.writeValueAsString(event);
            template.send("quickstart-events", eventString);
            log.info("producer - sent events ");
        } catch (JsonProcessingException e) {
            log.error("exception - {}", e);
        }
    }

}
