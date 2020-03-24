package andrianova.requestcounter.api;

import andrianova.requestcounter.conf.LimitSettings;
import andrianova.requestcounter.service.EventService;
import andrianova.requestcounter.service.LimitRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private LimitSettings limitSettings;

    @LimitRequests(requests = 1, period = "PT1M")
    @GetMapping("/register")
    public ResponseEntity<String> registerEvent(HttpServletRequest request) {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
