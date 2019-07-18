package com.peter.n_way_cache;

import java.util.Locale;

public class Entry<K, V> {
    private final K key;
    private final V value;
    private final int hash;

    private long timestamp;

    Entry(K keyInput, V value, int hash) {
        this(keyInput, value, Utility.currentTimestamp(), hash);
    }

    private Entry(K key, V value, long timestamp, int hash) {
    	this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.hash = hash;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    int getHash() {
        return hash;
    }

    long getTimestamp() {
        return timestamp;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), Utility.FORMAT_TO_STRING,
                Utility.objString(key), Utility.objString(value),
                hash, Utility.objString(timestamp));
    }
}
