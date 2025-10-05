package is.kafka.trial.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
@Log4j2
public class KafkaConsumerService {

    // Thread-safe queue to temporarily store events
    private final ConcurrentLinkedQueue<ConsumerRecord<String, String>> buffer = new ConcurrentLinkedQueue<>();

   @PostConstruct
   public void init() {
       syncEvents();
   }

    // Consume from all topics dynamically
    @KafkaListener(topicPattern = ".*", groupId = "quickstart-events")
    public void consume(ConsumerRecord<String, String> record) {
        buffer.add(record);
    }

    @Scheduled(fixedRate = 3000)  // every 30s
    public void syncEvents() {
        log.info("Running scheduled sync job...");

        ConsumerRecord<String, String> record;
        while ((record = buffer.poll()) != null) {
           log.info("Synced from topic={}, key={}, value={}",
                    record.topic(), record.key(), record.value());
        }
    }
}
