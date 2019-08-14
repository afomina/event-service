package yandex.eventservice.service;

import yandex.eventservice.cache.EventCache;
import yandex.eventservice.domain.Event;
import yandex.eventservice.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class EventService extends Service<Event> {
    
    @Autowired
    EventCache cache;

    @Override
    public void save(Event event) {
        cache.store(event);
    }

    public Long countLast(Integer minutes) {
        return cache.count(Timestamp.valueOf(LocalDateTime.now().minusMinutes(minutes)).getTime());
    }

}
