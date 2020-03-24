package andrianova.requestcounter.service;

import andrianova.requestcounter.domain.AccessHistoryItem;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Aspect for {@link LimitRequests} annotation
 */
@Aspect
@Component
public class LimitRequestsAspect {

    @Autowired
    private AccessHistoryService eventService;

    /**
     * Counts number of requests with request ip address.
     * If request limit exceeded, returns Http response 429, otherwise proceed with the method.
     */
    @Around("@annotation(LimitRequests)")
    public Object limitRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        LimitRequests limitSettings = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LimitRequests.class);
        Duration period = Duration.parse(limitSettings.period());
        String userIp = ((HttpServletRequest) joinPoint.getArgs()[0]).getRemoteAddr();

        if (eventService.isRequestAllowed(userIp, period, limitSettings.requests())) {
            eventService.save(new AccessHistoryItem(LocalDateTime.now(), userIp));
            return joinPoint.proceed();
        }

        Duration nextAttemptDuration = period.minus
                (Duration.between(eventService.getLastEventTime(userIp), LocalDateTime.now()));
        return new ResponseEntity<>("Rate limit exceeded. Try again in " + nextAttemptDuration.getSeconds() + " seconds",
                HttpStatus.TOO_MANY_REQUESTS);
    }

}
