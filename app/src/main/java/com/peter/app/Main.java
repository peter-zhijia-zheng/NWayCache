package com.peter.app;

import com.peter.n_way_cache.LRUPolicy;
import com.peter.n_way_cache.MRUPolicy;
import com.peter.n_way_cache.Utility;
import com.peter.n_way_cache.interfaces.Cache;

public class Main {
	
    public static void main() {
        demoLRU();
        demoMRU();
        demoCustom();
    }
    
    private static void demoLRU() {
        basicLRU();
        moreLRU();
    }
    
    private static void basicLRU() {
        System.out.println("LRU basic");
        Cache<String, String> cacheLRU = Utility.newCache(2, 8, new LRUPolicy<String, String>());
        cacheLRU.put("Lionel", "Messi");
        cacheLRU.put("Christiano", "Ronaldo");
        cacheLRU.show("LRU cache");
        System.out.println("Whether key=Lione1 returns value=Messi:");
        System.out.println(cacheLRU.get("Lionel"));
        System.out.println("Whether key=Christinao returns value=Ronaldo:");
        System.out.println(cacheLRU.get("Christiano"));
    }
    
    private static void moreLRU() {
        System.out.println("LRU more");
        Cache<Integer, String> cacheLRU = Utility.newCache(2, 8, new LRUPolicy<Integer, String>());
        cacheLRU.put(16, "Christiano");
        cacheLRU.put(32, "Messi");
        cacheLRU.put(48, "Ronaldo");
        cacheLRU.show("LRU cache");
        System.out.println("Whether key=16 returns null:");
        System.out.println(cacheLRU.get(16));
        System.out.println("Whether key=32 returns value=Messi:");
        System.out.println(cacheLRU.get(32));
        System.out.println("Whether key=48 returns value=Ronaldo:");
        System.out.println(cacheLRU.get(48));
    }
    
    private static void demoMRU() {
        System.out.println("MRU demo");
        Cache<Integer, String> cacheMRU = Utility.newCache(2, 8, new MRUPolicy<Integer, String>());
        cacheMRU.put(16, "Christiano");
        cacheMRU.put(32, "Messi");
        cacheMRU.put(48, "Ronaldo");
        cacheMRU.show("MRU cache");
        System.out.println("Whether key=16 gets Christiano:");
        System.out.println(cacheMRU.get(16));
        System.out.println("Whether key=32 gets null:");
        System.out.println(cacheMRU.get(32));
        System.out.println("Whether key=48 gets Ronaldo:");
        System.out.println(cacheMRU.get(48));
    }
    
    private static void demoCustom() {
        Cache<String, String> cacheCustom = Utility.newCache(4, 8, new CustomCachePolicy<String, String>());
        cacheCustom.put("A", "AB");
        cacheCustom.put("ABC", "ABCD");
        cacheCustom.get("AB");
        cacheCustom.show("Custom cache");
    }
}
