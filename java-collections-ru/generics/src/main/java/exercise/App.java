package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> resultList = new ArrayList<>();
        Set<Entry<String, String>> whereSet = where.entrySet();

        for (Map<String, String> book : books) {
            Set<Map.Entry<String, String>> bookSet = book.entrySet();
            if (bookSet.containsAll(whereSet)) {
                resultList.add(book);
            }
        }

        return resultList;
    }

}
//END
