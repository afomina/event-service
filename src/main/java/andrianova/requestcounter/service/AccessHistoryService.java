package andrianova.requestcounter.service;

import andrianova.requestcounter.domain.AccessHistoryItem;
import andrianova.requestcounter.domain.AccessHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Service to work with {@link AccessHistoryItem}
 */
@Service
public class AccessHistoryService {

    @Autowired
    private AccessHistoryRepository repository;

    /**
     * Store {@link AccessHistoryItem}
     *
     * @param item accessHistoryItem to store
     */
    public void save(AccessHistoryItem item) {
        repository.save(item);
    }

    /**
     * Count accessHistoryItems with specified ip for the last period of time
     *
     * @param period period of time
     * @param ip     accessHistoryItem ip
     * @return amount of items
     */
    public Long countLast(Duration period, String ip) {
        return repository.countByIpAndDateAfter(ip, LocalDateTime.now().minus(period));
    }

    /**
     * Checks if request is allowed
     *
     * @param ipAddress user ip address
     * @param period time interval
     * @param maxRequests amount of requests allowed to perform
     * @return true if request is allowed, false otherwise
     */
    public boolean isRequestAllowed(String ipAddress, Duration period, Integer maxRequests) {
        return countLast(period, ipAddress) < maxRequests;
    }

    /**
     * Returns date of last accessHistoryItem with specified ip
     *
     * @param userIp user ip
     * @return maximum date
     */
    public LocalDateTime getLastEventTime(String userIp) {
        return repository.findMaxDateByIp(userIp);
    }
}
