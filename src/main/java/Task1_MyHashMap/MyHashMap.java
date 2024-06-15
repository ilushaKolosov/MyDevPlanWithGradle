package Task1_MyHashMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyHashMap<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    void putAll(Map<? extends K, ? extends V> map);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    Set<Map.Entry<K, V>> entrySet();
}
