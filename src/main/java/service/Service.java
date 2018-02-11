package service;

@org.springframework.stereotype.Service
public abstract class Service<T> {
    public abstract T save(T object);
}
