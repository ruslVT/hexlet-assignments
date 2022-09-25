package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            try {
                field.setAccessible(true);
                if (notNull != null && field.get(address) == null) {
                    notValidFields.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new LinkedHashMap<>();

        for (Field field : address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            MinLength minLength = field.getAnnotation(MinLength.class);
            try {
                field.setAccessible(true);
                if (notNull != null && field.get(address) == null) {
                    notValidFields.put(field.getName(), List.of("can not be null"));
                } else if (minLength != null && field.get(address).toString().length() < 4) {
                    notValidFields.put(field.getName(), List.of("length less than 4"));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return notValidFields;
    }
}
// END
