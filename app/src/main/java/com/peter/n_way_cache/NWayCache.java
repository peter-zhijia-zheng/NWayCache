package com.peter.n_way_cache;

import android.util.Log;

import com.peter.n_way_cache.interfaces.Cache;
import com.peter.n_way_cache.interfaces.Policy;

import java.lang.reflect.Array;
import java.util.Locale;

public class NWayCache<K, V> implements Cache<K, V> {

    private final Entry<K, V>[] mEntries;
    
    private final int mAssociativity;
    
    private final int mNumSets;
    
    private final Policy<K, V> mReplacementPolicy;

    NWayCache(int associativity, int numSets, Policy<K, V> replacementPolicy) {
        mAssociativity = associativity;
        mNumSets = numSets;
        mEntries = (Entry<K, V>[]) Array.newInstance(Entry.class, associativity * numSets);
        for (int i = 0; i < mEntries.length; i++) {
            mEntries[i] = null;
        }
        mReplacementPolicy = replacementPolicy;
    }

    @Override
    public V get(K key) {
        final int tag = Utility.hash(key);
        final int startIndex = Utility.getStartIndex(tag, mNumSets, mAssociativity);
        final int endIndex = Utility.getEndIndex(startIndex, mAssociativity);

        for (int i = startIndex; i <= endIndex; i++) {
            if (mEntries[i] == null) continue;
            if (mEntries[i].getHash() != tag) continue;
            if (Utility.sameKey(mEntries[i].getKey(), key)) {
                mEntries[i].setTimestamp(Utility.currentTimestamp());
                return mEntries[i].getValue();
            }
        }
        return null;
    }

    @Override
    public void put(K keyInput, V value) {
        final int hash = Utility.hash(keyInput);
        final int startIndex = Utility.getStartIndex(hash, mNumSets, mAssociativity);
        final int endIndex = Utility.getEndIndex(startIndex, mAssociativity);

        final Entry<K, V> newEntry = new Entry<>(keyInput, value, hash);
        
        int found = -1, firstEmpty = -1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (mEntries[i] == null) {
                if (firstEmpty < 0) {
                    firstEmpty = i; // There is a vacant position for the new entry.
                }
                continue;
            }
            // If entry has already existed in the cache,
            if (mEntries[i].getHash() == newEntry.getHash()
                    && Utility.sameKey(mEntries[i].getKey(), newEntry.getKey())) {
                found = i;
                break;
            }
        }
        if (found >= startIndex) {
            mEntries[found] = newEntry;
            return;
        }
        if (firstEmpty >= startIndex) {
            mEntries[firstEmpty] = newEntry;
            return;
        }

        // Eviction is required.
        int evictionIndex = mReplacementPolicy.evictEntryIndex(mEntries, startIndex, endIndex);
        if (evictionIndex >= startIndex && evictionIndex <= endIndex) {
            mEntries[evictionIndex] = newEntry;
        }
    }
    
    @Override
    public void show(String cacheLabel) {
        if (mEntries == null || mEntries.length <= 0) return;
        if (cacheLabel != null) {
            System.out.println(cacheLabel);
        }
        final String entryFormat = "%d, %s";
        for (int i = 0; i < mEntries.length; i++) {
            if (mEntries[i] == null) continue;
            System.out.println(String.format(Locale.getDefault(), entryFormat, i, mEntries[i].toString()));
        }
    }
}
