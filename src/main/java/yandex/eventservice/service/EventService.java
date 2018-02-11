package yandex.eventservice.service;

import yandex.eventservice.domain.Event;
import yandex.eventservice.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class EventService extends Service<Event> {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public BigInteger countLast(Integer minutes) {
        return eventRepository.countEventsByDateAfter(LocalDateTime.now().minusMinutes(minutes));
    }
}
