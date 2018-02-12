package yandex.eventservice.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import yandex.eventservice.domain.Event;
import yandex.eventservice.domain.repository.EventRepository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventCache implements ConcurrentCache<Event> {
    SortedMap<Long, List<Event>> eventsByTimestamp = new ConcurrentSkipListMap<>();
    AtomicInteger currentSize = new AtomicInteger();

    @Autowired
    EventRepository repository;

    @Override
    @Transactional
    public void store(Event event) {
        if (eventsByTimestamp.size() >= MAX_SIZE) {
            synchronized (eventsByTimestamp) {
                List<List<Event>> toStore = new ArrayList<>(eventsByTimestamp.values());
                eventsByTimestamp.clear();
                for (List<Event> events : toStore) {
                    repository.save(events);
                }
            }
        }
        Long key = Timestamp.valueOf(event.getDate()).getTime();
        if (!eventsByTimestamp.containsKey(key)) {
            eventsByTimestamp.put(key, new ArrayList<>());
        }
        eventsByTimestamp.get(key).add(event);
    }

    public Long count(Long minTime) {
        if (eventsByTimestamp.isEmpty()) {
            return dbCount(minTime);
        }
        Long cachedMinDate = eventsByTimestamp.firstKey();
        if (minTime.equals(cachedMinDate)) {
            return (long) mapSize(eventsByTimestamp);
        }
        if (minTime < cachedMinDate) {
            return dbCount(minTime) + mapSize(eventsByTimestamp);
        }
        if (minTime > cachedMinDate) {
            return (long) mapSize(eventsByTimestamp.tailMap(minTime));
        }
        return 0L;
    }

    int mapSize(Map<Long, List<Event>> map) {
        return map.values().stream().map(List::size).reduce(0, (x, y) -> x + y);
    }

    private Long dbCount(Long minTime) {
        return repository.countEventsByDateAfter(new Timestamp(minTime).toLocalDateTime());
    }
}
