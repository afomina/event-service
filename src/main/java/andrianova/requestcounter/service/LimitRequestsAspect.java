package andrianova.requestcounter.service;

import andrianova.requestcounter.domain.Event;
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
 *
 */
@Aspect
@Component
public class LimitRequestsAspect {

    @Autowired
    private EventService eventService;

    @Around("@annotation(LimitRequests)")
    public Object countRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        LimitRequests limitSettings = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LimitRequests.class);
        Duration period = Duration.parse(limitSettings.period());

        String userIp = ((HttpServletRequest) joinPoint.getArgs()[0]).getRemoteAddr();

        if (eventService.isRequestAllowed(userIp, period, limitSettings.requests())) {
            eventService.save(new Event(LocalDateTime.now(), userIp));
            return joinPoint.proceed();
        }

        Duration nextAttemptDuration = period.minus
                (Duration.between(eventService.getLastEventTime(userIp), LocalDateTime.now()));
        return new ResponseEntity<>("Rate limit exceeded. Try again in " + nextAttemptDuration.getSeconds() + " seconds",
                HttpStatus.TOO_MANY_REQUESTS);
    }

}
