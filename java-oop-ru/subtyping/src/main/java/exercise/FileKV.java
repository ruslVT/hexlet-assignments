package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private Map<String, String> kvStorageMap = new HashMap<>();
    private final String pathToFile;

    public FileKV(String path, Map<String, String> map) {
        this.pathToFile = path;
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

    public void saveKVStorage() {
        String content = Utils.serialize(this.kvStorageMap);
        Utils.writeFile(this.pathToFile, content);
    }

    public void getFileContent() {
        String content = Utils.readFile(this.pathToFile);
        this.kvStorageMap = Utils.unserialize(content);
    }

}
// END
