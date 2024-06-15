package Task1_MyHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Benchmark {

    public void runBenchmark() {
        int numElements = 1000000;

        MyHashMapImpl<String, Integer> myMap = new MyHashMapImpl<>();
        Map<String, Long> myMapResults = benchmarkMyHashMap(myMap, numElements);

        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Long> hashMapResults = benchmarkJavaMap(hashMap, numElements);

        System.out.println("Task1_MyHashMap.MyHashMapImpl Insertion Time: " + (myMapResults.get("insert") / 1_000_000.0) + " ms");
        System.out.println("HashMap Insertion Time: " + (hashMapResults.get("insert") / 1_000_000.0) + " ms");
        System.out.println();
        System.out.println("Task1_MyHashMap.MyHashMapImpl Retrieval Time: " + (myMapResults.get("retrieve") / 1_000_000.0) + " ms");
        System.out.println("HashMap Retrieval Time: " + (hashMapResults.get("retrieve") / 1_000_000.0) + " ms");
        System.out.println();
        System.out.println("Task1_MyHashMap.MyHashMapImpl Removal Time: " + (myMapResults.get("remove") / 1_000_000.0) + " ms");
        System.out.println("HashMap Removal Time: " + (hashMapResults.get("remove") / 1_000_000.0) + " ms");
    }

    private <K, V> Map<String, Long> benchmarkMyHashMap(MyHashMap<K, V> map, int numElements) {
        Random random = new Random();
        long insertTime = 0;
        long retrieveTime = 0;
        long removeTime = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.put((K) ("key" + i), (V) (Integer) random.nextInt());
        }
        insertTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.get((K) ("key" + i));
        }
        retrieveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.remove((K) ("key" + i));
        }
        removeTime = System.nanoTime() - startTime;

        Map<String, Long> results = new HashMap<>();
        results.put("insert", insertTime);
        results.put("retrieve", retrieveTime);
        results.put("remove", removeTime);

        return results;
    }

    private <K, V> Map<String, Long> benchmarkJavaMap(Map<K, V> map, int numElements) {
        Random random = new Random();
        long insertTime = 0;
        long retrieveTime = 0;
        long removeTime = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.put((K) ("key" + i), (V) (Integer) random.nextInt());
        }
        insertTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.get((K) ("key" + i));
        }
        retrieveTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            map.remove((K) ("key" + i));
        }
        removeTime = System.nanoTime() - startTime;

        Map<String, Long> results = new HashMap<>();
        results.put("insert", insertTime);
        results.put("retrieve", retrieveTime);
        results.put("remove", removeTime);

        return results;
    }
}
