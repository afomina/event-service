package domain.repository;

import domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, BigInteger> {
    BigInteger countEventsByDateAfter(LocalDateTime date);
}
