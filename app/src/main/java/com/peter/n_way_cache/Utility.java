package com.peter.n_way_cache;

import com.peter.n_way_cache.interfaces.Cache;
import com.peter.n_way_cache.interfaces.Policy;

public class Utility {
    static final String FORMAT_TO_STRING = "Key:%s, Value:%s, Tag:%d, Timestamp:%s";

    private static final String NULL_STR = "null";
    private static long timeStamp = 0;

    public static <K, V> Cache<K, V> newCache(int associativity, int numSets, Policy<K, V> evictionPolicy) {
        return new NWayCache<>(associativity, numSets, evictionPolicy);
    }
    
    static int getStartIndex(int hashKey, int numSets, int associativity) {
        return (Math.abs(hashKey) % numSets) * associativity;
    }

    static int getEndIndex(int startIndex, int associativity) {
        return startIndex + associativity - 1;
    }

    static int hash(Object key) {
        return (key.hashCode() * 37 + 17);
    }
    
    static <K> boolean sameKey(K key1, K key2) {
        if (key1 == key2) return true;
        if (key1 == null || key2 == null) return false;
        return key1.equals(key2);
    }
    
    synchronized static long currentTimestamp() {
        return timeStamp++;
    }

    static String objString(Object obj) {
        return obj == null ? Utility.NULL_STR : obj.toString();
    }
}
