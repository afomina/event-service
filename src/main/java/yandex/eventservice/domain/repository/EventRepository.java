package yandex.eventservice.domain.repository;

import org.springframework.stereotype.Component;
import yandex.eventservice.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Repository
@Component
public interface EventRepository extends JpaRepository<Event, BigInteger> {
    BigInteger countEventsByDateAfter(LocalDateTime date);
}
