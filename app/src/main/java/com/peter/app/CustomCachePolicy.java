package com.peter.app;

import com.peter.n_way_cache.Entry;
import com.peter.n_way_cache.interfaces.Policy;

public class CustomCachePolicy<K, V> implements Policy<K, V> {

    @Override
    public int evictEntryIndex(Entry<K, V>[] entries, int startIndex, int endIndex) {
        return (startIndex + (endIndex - startIndex) / 2);
    }

}
