package yandex.eventservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yandex.eventservice.domain.Event;
import yandex.eventservice.service.EventService;

import java.math.BigInteger;
import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/event")
public class EventController extends RestController {

    @Autowired
    EventService eventService;

    @GetMapping("/register")
    public void registerEvent() {
        eventService.save(new Event(LocalDateTime.now()));
    }

    @GetMapping
    public BigInteger getEvents(@PathVariable Integer minutes) {
        return eventService.countLast(minutes);
    }
}
