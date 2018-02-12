package yandex.eventservice.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Table
@Entity
public class Event implements Comparable<Event> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    public Event() {}

    public Event(LocalDateTime date) {
        this.date = date;
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

    @Override
    public int compareTo(Event o) {
        return date.compareTo(o.date);
    }
}
