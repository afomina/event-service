package andrianova.requestcounter.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Configuration
@PropertySource("file:src/main/resources/limits.properties")
@ConfigurationProperties(prefix = "requestcounter")
public class LimitSettings {

    private Duration period;

    private Integer maxRequestCount;

    public Duration getPeriod() {
        return period;
    }

    public Integer getMaxRequestCount() {
        return maxRequestCount;
    }

    public void setPeriod(String period) {
        this.period = Duration.parse(period);
    }

    public void setMaxRequestCount(Integer maxRequestCount) {
        this.maxRequestCount = maxRequestCount;
    }
}
