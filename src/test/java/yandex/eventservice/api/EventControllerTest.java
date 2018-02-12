package yandex.eventservice.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest {

    private static final Long EVENT_AMOUNT = 100000L;

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    Logger logger = Logger.getAnonymousLogger();

    @Test
    public void registerManyEventsTest() {
        LocalDateTime start = LocalDateTime.now();
        logger.log(Level.WARNING, String.format("Register %d events started at %s", EVENT_AMOUNT, start.toString()));
        for (long i = 0; i < EVENT_AMOUNT; i++) {
            ResponseEntity response = restTemplate.getForEntity("http://localhost:" + port + "/event/register", String.class);
            assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        }
        LocalDateTime end = LocalDateTime.now();
        logger.log(Level.WARNING, String.format("Events registered %s", end.toString()));
        LocalDateTime totalTime = new Timestamp(Timestamp.valueOf(end).getTime() - Timestamp.valueOf(start).getTime()).toLocalDateTime();
        logger.log(Level.WARNING, String.format("Total time %s", totalTime.toString()));
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/event?minutes=" + (totalTime.getMinute() + 1), Long.class), equalTo(EVENT_AMOUNT));
    }
}
