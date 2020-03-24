package andrianova.requestcounter.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for limiting requests to api method
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequests {

    /**
     * Period of time
     */
    String period();

    /**
     * Amount of requests allowed
     */
    int requests();

}
