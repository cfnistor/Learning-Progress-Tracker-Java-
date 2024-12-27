package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private String firstName;
    private String lastName;
    private String email;

    private static final Scanner scanner = new Scanner(System.in);
    
    private static List<Student> students = new ArrayList<>();

    public static List<Student> getStudents() {
        return students;
    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

            if (validateFirstName(firstName) && validateLastName(lastName) && validateEmail(email)) {
                Student student = new Student(firstName, lastName, email);
                students.add(student);
                System.out.println("The student has been added.");
            }
        } else {
            System.out.println("Incorrect credentials.");
        }
    }
}
