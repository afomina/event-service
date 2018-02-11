package yandex.eventservice.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Table
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private LocalDateTime date;

    public Event() {}

    public Event(LocalDateTime date) {
        this.date = date;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
