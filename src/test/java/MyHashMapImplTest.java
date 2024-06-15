import Task1_MyHashMap.MyHashMap;
import Task1_MyHashMap.MyHashMapImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class MyHashMapImplTest {
    private MyHashMap<String, Integer> map;

    @BeforeEach
    public void testMyMap() {
        map = new MyHashMapImpl<>();
    }

    @Test
    public void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
    }

    @Test
    public void testSize() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(2, map.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());

        map.put("one", 1);
        assertFalse(map.isEmpty());
    }

    @Test
    public void testContainsKey() {
        map.put("one", 1);

        assertTrue(map.containsKey("one"));
        assertFalse(map.containsKey("two"));
    }

    @Test
    public void testContainsValue() {
        map.put("one", 1);

        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(2));
    }

    @Test
    public void testRemove() {
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(1, map.remove("one"));
        assertNull(map.remove("three"));
    }

    @Test
    public void testClear() {
        map.put("one", 1);
        map.put("two", 2);
        map.clear();

        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testPutAll() {
        Map<String, Integer> otherMap = new HashMap<>();
        otherMap.put("one", 1);
        otherMap.put("two", 2);

        map.putAll(otherMap);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
    }

    @Test
    public void testEntrySet_EmptyMap() {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        assertTrue(entries.isEmpty(), "Entry set should be empty for an empty map.");
    }

    @Test
    public void testEntrySet_NonEmptyMap() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Set<Map.Entry<String, Integer>> expectedEntries = new HashSet<>();
        expectedEntries.add(new AbstractMap.SimpleEntry<>("one", 1));
        expectedEntries.add(new AbstractMap.SimpleEntry<>("two", 2));
        expectedEntries.add(new AbstractMap.SimpleEntry<>("three", 3));

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        assertEquals(expectedEntries, entries, "Entry set should contain all entries in the map.");
    }

    @Test
    public void testEntrySet_AfterRemoval() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.remove("two");

        Set<Map.Entry<String, Integer>> expectedEntries = new HashSet<>();
        expectedEntries.add(new AbstractMap.SimpleEntry<>("one", 1));
        expectedEntries.add(new AbstractMap.SimpleEntry<>("three", 3));

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        assertEquals(expectedEntries, entries, "Entry set should reflect the map after an entry has been removed.");
    }

    @Test
    public void testEntrySet_DuplicateKeys() {
        map.put("one", 1);
        map.put("one", 2);

        Set<Map.Entry<String, Integer>> expectedEntries = new HashSet<>();
        expectedEntries.add(new AbstractMap.SimpleEntry<>("one", 2));

        Set<Map.Entry<String, Integer>> entries = map.entrySet();

        assertEquals(expectedEntries, entries, "Entry set should contain the latest entry for duplicate keys.");
        assertEquals(1, entries.size(), "Entry set should contain exactly one entry when duplicates are added.");
    }

    @Test
    public void testKeySet_EmptyMap() {
        Set<String> keys = map.keySet();
        assertTrue(keys.isEmpty(), "Key set should be empty for an empty map.");
    }

    @Test
    public void testKeySet_NonEmptyMap() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        Set<String> expectedKeys = new HashSet<>(Set.of("one", "two", "three"));
        Set<String> keys = map.keySet();

        assertEquals(expectedKeys, keys, "Key set should contain all keys in the map.");
    }

    @Test
    public void testKeySet_AfterRemoval() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.remove("two");

        Set<String> expectedKeys = new HashSet<>(Set.of("one", "three"));
        Set<String> keys = map.keySet();

        assertEquals(expectedKeys, keys, "Key set should reflect the map after a key has been removed.");
    }

    @Test
    public void testKeySet_DuplicateKeys() {
        map.put("one", 1);
        map.put("one", 2);

        Set<String> expectedKeys = new HashSet<>(Set.of("one"));
        Set<String> keys = map.keySet();

        assertEquals(expectedKeys, keys, "Key set should not contain duplicate keys.");
        assertEquals(1, keys.size(), "Key set should contain exactly one key when duplicates are added.");
    }
}
