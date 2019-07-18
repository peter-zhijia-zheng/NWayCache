package com.peter.n_way_cache;

import com.peter.n_way_cache.interfaces.Policy;

public class LRUPolicy<K, V> implements Policy<K, V> {

    @Override
    public int evictEntryIndex(Entry<K, V>[] entries, int startIndex, int endIndex) {
        int index = startIndex;
        long earliestTime = Utility.currentTimestamp();
        for (int i = startIndex; i <= endIndex; i++) {
            if (entries[i] == null) continue;
            if (entries[i].getTimestamp() < earliestTime) {
                earliestTime = entries[i].getTimestamp();
                index = i;
            }
        }
        return index;
    }
}
