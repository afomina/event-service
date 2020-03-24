package andrianova.requestcounter.domain.repository;

import andrianova.requestcounter.domain.Event;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Repository
@Component
public interface EventRepository extends JpaRepository<Event, BigInteger> {
    @Cacheable("eventCount")
    Long countEventsByIpAndDateAfter(String ip, LocalDateTime date);

    @Query("select max(date) from Event where ip=?1")
    LocalDateTime findMaxDateByIp(String ip);
}
