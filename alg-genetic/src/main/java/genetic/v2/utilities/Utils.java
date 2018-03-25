package genetic.v2.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {
    public static <T> T firstOf(List<T> items) {
        return items.get(0);
    }

    public static <T> T lastOf(List<T> items) {
        return items.get(items.size()-1);
    }

    public static  <T> List<T> copyOf(List<T> items) {
        return new ArrayList<>(items);
    }

    public static  <T> List<T> toList(Set<T> items) {
        return new ArrayList<>(items);
    }

    public static <T> List<T> listOf(Class<T> tClass) {
        return new ArrayList<>();
    }
}
