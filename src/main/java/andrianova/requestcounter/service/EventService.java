package andrianova.requestcounter.service;

import andrianova.requestcounter.domain.Event;
import andrianova.requestcounter.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EventService {
    
    @Autowired
    EventRepository repository;

    public void save(Event event) {
        repository.save(event);
    }

    public Long countLast(Duration period, String ip) {
        return repository.countEventsByIpAndDateAfter(ip, LocalDateTime.now().minus(period));
    }

    public boolean isRequestAllowed(String ipAddress, Duration period, Integer maxRequests) {
        return countLast(period, ipAddress) < maxRequests;
    }

    public LocalDateTime getLastEventTime(String userIp) {
        return repository.findMaxDateByIp(userIp);
    }
}
