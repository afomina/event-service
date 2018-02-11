package api;

import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.EventService;

import java.math.BigInteger;
import java.time.LocalDateTime;

@RequestMapping("/event")
public class EventController extends RestController {

    @Autowired
    EventService eventService;

    @PostMapping("/register")
    public void registerEvent() {
        eventService.save(new Event(LocalDateTime.now()));
    }

    @GetMapping
    public BigInteger getEvents(@PathVariable Integer minutes) {
        return eventService.countLast(minutes);
    }
}
