package andrianova.requestcounter.api;

import andrianova.requestcounter.service.LimitRequests;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Api controller
 */
@RestController
@RequestMapping("/")
public class LimitRequestController {

    /**
     * Test api method that limits requests from specified ip address
     *
     * @param request http request
     * @return response
     */
    @LimitRequests(requests = 100, period = "PT1H")
    @GetMapping
    public ResponseEntity<String> limitRequests(HttpServletRequest request) {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
