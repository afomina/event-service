package yandex.eventservice.cache;

public interface ConcurrentCache<T> {
    public static final int MAX_SIZE = 2500;

    public void store(T o);
}
