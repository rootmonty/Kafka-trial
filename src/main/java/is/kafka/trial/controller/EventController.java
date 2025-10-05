package is.kafka.trial.controller;

import is.kafka.trial.model.AnalyticEvent;
import is.kafka.trial.model.EventClass;
import is.kafka.trial.services.IngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private IngestionService ingestionService;


    @PostMapping("/event")
    public Object ingestEvents(@RequestBody EventClass analyticEvent) {
        return ingestionService.addEvent(analyticEvent);
    }

    @PostMapping("/events")
    public Object ingestEvents(@RequestBody List<EventClass> analyticEvent) {
        return ingestionService.addEvents(analyticEvent);
    }

    @GetMapping("/event/all")
    public Object getAllEvents() {
        return ingestionService.getAll();
    }
}
