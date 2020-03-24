package andrianova.requestcounter.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Stores information about each access of specified user to api method
 */
@Table
@Entity
public class AccessHistoryItem implements Comparable<AccessHistoryItem> {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Access date and time
     */
    private LocalDateTime date;

    /**
     * User ip address
     */
    private String ip;

    public AccessHistoryItem() {
    }

    public AccessHistoryItem(LocalDateTime date, String ip) {
        this.date = date;
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public int compareTo(AccessHistoryItem o) {
        return date.compareTo(o.date);
    }
}
