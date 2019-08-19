package cn.com.agree.aweb.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    private Map<K, V> map;

    private MapBuilder(){
        this.map = new HashMap<>();
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.map.put(k, v);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.map.putAll(map);
        return this;
    }

    public MapBuilder<K, V> putIfAbsent(K k, V v) {
        map.putIfAbsent(k, v);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

    public static <K, V> MapBuilder<K, V> getInstance(Class<K> classK, Class<V> classV) {
        return new MapBuilder<>();
    }
}
