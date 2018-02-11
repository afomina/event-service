package domain;

import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
public class Event extends DomainObject {
    private LocalDateTime date;

    public Event() {}

    public Event(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
