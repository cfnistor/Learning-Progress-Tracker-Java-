import java.util.*;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.charAt(0) == value.charAt(0)) {
                System.out.println(key + " " + value);
            }
        }

    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Integer person1 = 23;
        Integer person2 = 23;
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
    }
}