package tracker;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

    private static final int DEFAULT_ID = 10000;

    private static int idCounter = DEFAULT_ID;
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private Map<Course, Integer> courseActivity = new EnumMap<>(Course.class);
    private Map<Course, Integer> coursePoints = new EnumMap<>(Course.class);
    private static Map<Integer, Student> students = new HashMap<>();
    private static Set<String> emails = new HashSet<>();

    public Student(String firstName, String lastName, String email) {
        id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        // Initialize course points to 0
        for (Course course : Course.values()) {
            coursePoints.put(course, 0);
            courseActivity.put(course, 0);
        }
    }

    public int getId() {
        return id;
    }

    public Map<Course, Integer> getAssignmentCounter() {
        return courseActivity;
    }

    public static void resetIdCounter() {
        idCounter = DEFAULT_ID; // Reset ID counter to its initial state
    }

    public static Map<Integer, Student> getStudents() {
        return students;
    }

    public static Set<String> getEmails() {
        return emails;
    }

    public static boolean isEmailUsed(String email) {
        return emails.contains(email);
    }

    public Map<Course, Integer> getCoursePoints() {
        return coursePoints;
    }

    public void addPoints(Course course, int points) {

        coursePoints.put(course, coursePoints.getOrDefault(course, 0) + points);
        if (points > 0) {
            courseActivity.put(course, courseActivity.getOrDefault(course, 0) + 1);
        }
    }

    public static boolean updatePoints(String inputId, int javaPoints, int dsaPoints, int dbPoints, int springPoints) {
        int id = 0;
        try {
            id = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            return false;
        }

        Student student = students.get(id);
        if (student == null) {
            return false;
        }

        student.addPoints(Course.JAVA, javaPoints);
        student.addPoints(Course.DSA, dsaPoints);
        student.addPoints(Course.DATABASES, dbPoints);
        student.addPoints(Course.SPRING, springPoints);

        return true;
    }

    public static boolean validateLastName(String name) {

        String regex = "^[A-Za-z]+(?:[ '-]?[A-Za-z]+)+$";
        if (name.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect last name.");
        return false;
    }

    public static boolean validateFirstName(String name) {

        String regex = "^[A-Za-z]+(?:[-']?[A-Za-z]+)+$";
        if (name.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect first name.");
        return false;
    }

    public static boolean validateEmail(String email) {

        String regex = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";


        if (email.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect email.");
        return false;
    }

    public static void addStudent(String input) {
        String regex = "^([\\S]+)\\s([\\S\\s]+)\\s([\\S]+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            String email = matcher.group(3);

            if (!isEmailUsed(email)) {
                if (validateFirstName(firstName) && validateLastName(lastName) && validateEmail(email)) {
                    Student student = new Student(firstName, lastName, email);
                    students.put(student.getId(), student);
                    emails.add(email);
                    System.out.println("The student has been added.");
                }
            } else {
                System.out.println("This email is already taken.");
            }
        } else {
            System.out.println("Incorrect credentials.");
        }
    }

    public String printDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d points:", getId()));
        int counter = 1;
        for (Map.Entry<Course, Integer> entry : getCoursePoints().entrySet()) {
            Integer points = entry.getValue();
            Course course = entry.getKey();
            sb.append(String.format(" %s=%d", course.getName(), points));
            if (Course.values().length != counter++) {
                sb.append(";");
            }

        }
        return sb.toString();
    }
}
