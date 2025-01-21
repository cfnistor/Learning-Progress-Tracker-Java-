import java.util.*;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "=" + age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Utils {

    public static void sortUsers(List<User> users) {
        Comparator<User> byNameThenByAgeReverseOrder = 
            Comparator.comparing(User::getName).thenComparing(
                User::getAge, 
                Comparator.reverseOrder());
        users.sort(byNameThenByAgeReverseOrder);
    }
}
