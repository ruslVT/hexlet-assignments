package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {

    private String tagName;
    private Map<String, String> tagAttr;

    public Tag(String tagName, Map<String, String> tagAttr) {
        this.tagName = tagName;
        this.tagAttr = tagAttr;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagAttr(Map<String, String> tagAttr) {
        this.tagAttr = tagAttr;
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getTagAttr() {
        return tagAttr;
    }

    public String toString() {
        return "<" + this.tagName + attrToString(this.tagAttr) + ">";
    }

    private String attrToString(Map<String, String> tagAttr) {
        if (tagAttr.isEmpty()) return "";

        return tagAttr.keySet().stream()
                .map(key -> " " + key + "=\"" + tagAttr.get(key) + "\"")
                .collect(Collectors.joining());
    }

}
// END
