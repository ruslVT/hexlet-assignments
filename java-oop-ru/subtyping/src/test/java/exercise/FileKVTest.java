package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    void fileKVTest() {
        FileKV kvStorage = new FileKV(filepath.toString(), new HashMap<>(Map.of("key1", "value1")));

        String actualString1 = kvStorage.get("key1", "default");
        assertThat(actualString1).isEqualTo("value1");

        kvStorage.set("key2", "value2");
        Map<String, String> expectedMap1 = new HashMap<>(Map.of("key1", "value1", "key2", "value2"));
        assertThat(kvStorage.toMap()).isEqualTo(expectedMap1);

        kvStorage.unset("key2");
        Map<String, String> expectedMap2 = new HashMap<>(Map.of("key1", "value1"));
        assertThat(kvStorage.toMap()).isEqualTo(expectedMap2);

        kvStorage.saveKVStorage();
        String actualString2 = Utils.readFile(filepath.toString());
        String expectedString1 = "{\"key1\":\"value1\"}";
        assertThat(actualString2).isEqualTo(expectedString1);

        kvStorage.getFileContent();
        Map<String, String> expectedMap3 = new HashMap<>(Map.of("key1", "value1"));
        assertThat(kvStorage.toMap()).isEqualTo(expectedMap3);
    }
    // END
}
