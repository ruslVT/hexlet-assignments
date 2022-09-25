package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String tagBody;
    private List<Tag> childList;

    public PairedTag(String nameTag, Map<String, String> tagAttr, String tagBody, List<Tag> childList) {
        super(nameTag, tagAttr);
        this.tagBody = tagBody;
        this.childList = childList;
    }

    @Override
    public String toString() {
        return super.toString() + tagBody + childListToString(childList) + "</" + super.getTagName() + ">";
    }

    public static String childListToString(List<Tag> singleTags) {
        if (singleTags.isEmpty()) return "";

        return singleTags.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
    }
}
// END
