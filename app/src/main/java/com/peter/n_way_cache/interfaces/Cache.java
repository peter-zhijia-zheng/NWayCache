package com.peter.n_way_cache.interfaces;

public interface Cache<K, V> {
    V get(K key);

    void put(K key, V value);

    void show(String cacheLabel);
}
