package andrianova.requestcounter.domain;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Dao for {@link AccessHistoryItem}
 */
@Repository
@Component
public interface AccessHistoryRepository extends JpaRepository<AccessHistoryItem, BigInteger> {

    /**
     * Count accessHistoryItems with specified ip and date after specified date
     *
     * @param ip   accessHistoryItem ip
     * @param date start date
     * @return amount of accessHistoryItems
     */
    @Cacheable("eventCount")
    Long countByIpAndDateAfter(String ip, LocalDateTime date);

    /**
     * Returns date of last accessHistoryItem with specified ip
     *
     * @param ip accessHistoryItem ip
     * @return maximum date
     */
    @Query("select max(date) from AccessHistoryItem where ip=?1")
    LocalDateTime findMaxDateByIp(String ip);

}
