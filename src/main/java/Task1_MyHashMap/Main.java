package Task1_MyHashMap;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        testMyHashMap();

        addSpace();

        Benchmark benchmark = new Benchmark();
        benchmark.runBenchmark();

    }

    public static void testMyHashMap() {
        MyHashMap<String, Integer> map = new MyHashMapImpl<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println("After adding elements: " + map);

        System.out.println("Getting element by key 'two': " + map.get("two"));

        System.out.println("Does the map contain key 'three': " + map.containsKey("three"));
        System.out.println("Does the map contain key 'four': " + map.containsKey("four"));

        System.out.println("Does the map contain value 2: " + map.containsValue(2));
        System.out.println("Does the map contain value 4: " + map.containsValue(4));

        System.out.println("Size of the map: " + map.size());

        System.out.println("Is the map empty: " + map.isEmpty());

        System.out.println("Removing element by key 'two': " + map.remove("two"));
        System.out.println("After removing element: " + map);

        Set<String> keys = map.keySet();
        System.out.println("Set of keys: " + keys);

        Collection<Integer> values = map.values();
        System.out.println("Collection of values: " + values);

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        System.out.println("Set of entries: " + entries);

        Map<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("four", 4);
        anotherMap.put("five", 5);
        map.putAll(anotherMap);
        System.out.println("After adding elements with putAll: " + map);

        map.clear();
        System.out.println("After clearing the map: " + map);

        System.out.println("Is the map empty after clearing: " + map.isEmpty());
    }

    private static void addSpace() {
        System.out.println();
        System.out.println();
        System.out.println("Benchmark Time!");
    }
}
