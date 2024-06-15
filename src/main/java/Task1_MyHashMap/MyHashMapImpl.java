package Task1_MyHashMap;

import java.util.*;

public class MyHashMapImpl<K, V> implements MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private List<Entry<K, V>>[] table;
    private int size;

    public MyHashMapImpl() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    private static class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode() % table.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);
        List<Entry<K, V>> bucket = table[hash];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (Objects.equals(entry.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (Objects.equals(entry.value, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        List<Entry<K, V>> bucket = table[hash];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (Objects.equals(entry.key, key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[hash]) {
            if (Objects.equals(entry.key, key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        table[hash].add(new Entry<>(key, value));
        size++;
        if (size > LOAD_FACTOR * table.length) {
            resize();
        }
        return null;
    }

    private void resize() {
        List<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;
        for (List<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        List<Entry<K, V>> bucket = table[hash];
        if (bucket != null) {
            Iterator<Entry<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (Objects.equals(entry.key, key)) {
                    V oldValue = entry.value;
                    iterator.remove();
                    size--;
                    return oldValue;
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        table = new LinkedList[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    keys.add(entry.key);
                }
            }
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    values.add(entry.value);
                }
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entries = new HashSet<>();
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    entries.add(new AbstractMap.SimpleEntry<>(entry.key, entry.value));
                }
            }
        }
        return entries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (List<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(entry.key).append("=").append(entry.value);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
