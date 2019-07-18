package com.peter.n_way_cache;

import com.peter.n_way_cache.interfaces.Policy;

public class MRUPolicy<K, V> implements Policy<K, V> {

    @Override
    public int evictEntryIndex(Entry<K, V>[] entries, int startIndex, int endIndex) {
        int index = startIndex;
        long latestTime = entries[startIndex].getTimestamp();
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (entries[i] == null) continue;
            if (entries[i].getTimestamp() > latestTime) {
                latestTime = entries[i].getTimestamp();
                index = i;
            }
        }
        return index;
    }
}
