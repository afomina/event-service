package yandex.eventservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public BigInteger getEvents(@RequestParam(required = false, defaultValue = "0") Integer minutes,
                                @RequestParam(required = false, defaultValue = "0") Integer hours) {
        return eventService.countLast(hours * 60 + minutes);
    }
}
