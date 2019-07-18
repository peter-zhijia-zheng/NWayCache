package com.peter.n_way_cache.interfaces;

import com.peter.n_way_cache.Entry;

public interface Policy<K, V> {
    int evictEntryIndex(Entry<K, V>[] entries, int startIndex, int endIndex);
}