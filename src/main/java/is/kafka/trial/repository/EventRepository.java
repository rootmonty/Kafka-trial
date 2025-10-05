package is.kafka.trial.repository;

import is.kafka.trial.model.AnalyticEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<AnalyticEvent, String> {
}
