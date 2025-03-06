package tracker;

import java.util.Arrays;

public enum Course {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private final String name;
    private final int requiredPointsToCompleteCourse;

    Course(String name, int requiredPointsToCompleteCourse) {
        this.name = name;
        this.requiredPointsToCompleteCourse = requiredPointsToCompleteCourse;
    }

    public String getName() {
        return name;
    }

    public int getRequiredPointsToCompleteCourse() {
        return requiredPointsToCompleteCourse;
    }

    public static Course fromString(String name) {
        return Arrays.stream(Course.values())
                .filter(course -> course.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
