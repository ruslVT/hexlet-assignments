package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private final Map<String, String> kvStorageMap = new HashMap<>();

    public InMemoryKV(Map<String, String> map) {
        this.kvStorageMap.putAll(map);
    }

    @Override
    public void set(String key, String value) {
        this.kvStorageMap.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.kvStorageMap.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.kvStorageMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.kvStorageMap);
    }
}
// END
