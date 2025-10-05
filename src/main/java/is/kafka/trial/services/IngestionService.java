package is.kafka.trial.services;

import is.kafka.trial.model.AnalyticEvent;
import is.kafka.trial.model.EventClass;
import is.kafka.trial.repository.EventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class IngestionService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    private static final Logger log = LoggerFactory.getLogger(IngestionService.class);

    public AnalyticEvent addEvent(EventClass analyticEvent) {
        log.info("event {}", analyticEvent);
        AnalyticEvent dataEntry = new AnalyticEvent();
        dataEntry.setId(UUID.randomUUID().toString());
        dataEntry.setName(analyticEvent.getName());
        dataEntry.setTimestamp(ZonedDateTime.now());
        // send to kafka queue
        kafkaProducerService.queueEvent(dataEntry);
       // dataEntry = eventRepository.save(dataEntry);
        return dataEntry;
    }

    public List<AnalyticEvent> addEvents(List<EventClass> analyticEvents) {
        List<AnalyticEvent> savedEntries = new ArrayList<>();
        for (EventClass analyticEvent : analyticEvents) {
            savedEntries.add(this.addEvent(analyticEvent));
        }
        return savedEntries;
    }

    public Object getAll() {
        return eventRepository.findAll();
    }
}
