package andrianova.requestcounter.api;

import andrianova.requestcounter.domain.AccessHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test for {@link LimitRequestController}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LimitRequestControllerTest {

    private static final int EVENT_AMOUNT = 100;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private AccessHistoryRepository accessHistoryRepository;

    @Test
    public void limitRequestsTest() {
        accessHistoryRepository.deleteAll();

        String url = "http://localhost:" + port + "/";
        for (long i = 0; i < EVENT_AMOUNT; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            assertThat(response.getStatusCode(), is(HttpStatus.OK));
        }

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.TOO_MANY_REQUESTS));
    }
}
