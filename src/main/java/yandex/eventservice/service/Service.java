package yandex.eventservice.service;

@org.springframework.stereotype.Service
public abstract class Service<T> {
    public abstract void save(T object);
}
