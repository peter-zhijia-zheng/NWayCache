package com.peter.n_way_cache;

import java.sql.Timestamp;
import java.util.Locale;

public class Entry<K, V> {
    private final K key;
    private final V value;
    private final int hash;

    private Timestamp timestamp;

    public Entry(K keyInput, V value, int hash) {
        this(keyInput, value, Utility.currentTimestamp(), hash);
    }

    private Entry(K key, V value, Timestamp timestamp, int hash) {
    	this.key = key;
        this.value = value;
        this.timestamp = timestamp;
        this.hash = hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public int getHash() {
        return hash;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), Utility.FORMAT_TO_STRING,
                Utility.objString(key), Utility.objString(value),
                hash, Utility.objString(timestamp));
    }
}
