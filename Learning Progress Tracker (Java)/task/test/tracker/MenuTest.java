package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @BeforeEach
    void setUp() {
        Student.getStudents().clear();
        Student.getEmails().clear();
        Student.resetIdCounter();

        Student.addStudent("John Doe johnd@email.net");
        Student.addStudent("Jane Spark jspark@yahoo.com");

        Student.updatePoints("10000", 8, 7, 7, 5);
        Student.updatePoints("10000", 7, 6, 9, 7);
        Student.updatePoints("10000", 6, 5, 5, 0);
        Student.updatePoints("10001", 8, 0, 8, 6);
        Student.updatePoints("10001", 7, 0, 0, 0);
        Student.updatePoints("10001", 9, 0, 0, 5);
    }

    @Test
    void testAddPointsValidInput() {
        String input = "10000 10 20 30 40\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("Points updated.", result);
    }

    @Test
    void testAddPointsReturnBackCommand() {
        String input = "back\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("back", result);
    }

    @Test
    void testAddPointIncorrectFormat() {
        String input = "10000 10 20 30\n"; // Missing one number
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("Incorrect points format.", result);
    }

    @Test
    void testAddPointsNegativePoints() {
        String input = "10000 10 -20 30 40\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("Incorrect points format.", result);
    }

    @Test
    void testAddPointsInvalidId() {
        String input = "999 10 20 30 40\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("No student is found for id=999.", result);
    }

    @Test
    void testAddPointsStringId() {
        String input = "imsurethereisnosuchstudentid 1 1 1 1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
        String result = Menu.addPoints(scanner);
        assertEquals("No student is found for id=imsurethereisnosuchstudentid.", result);
    }

    @Test
    void testFindUser1000Valid() {

        String inputId = "10000\n";
        Scanner scannerId = new Scanner(new ByteArrayInputStream(inputId.getBytes()));

        String result = Menu.find(scannerId);
        assertEquals("10000 points: Java=21; DSA=18; Databases=21; Spring=12", result);
    }

    @Test
    void testFindUser10001Valid() {

        String inputId = "10001\n";
        Scanner scannerId = new Scanner(new ByteArrayInputStream(inputId.getBytes()));

        String result = Menu.find(scannerId);
        assertEquals("10001 points: Java=24; DSA=0; Databases=8; Spring=11", result);
    }

    @Test
    void testStatisticsValidV1() {

        Statistics statistics = new Statistics();
        List<String> result = statistics.listOverall();

        assertEquals(6, result.size());

        assertTrue(result.getFirst().startsWith("Most popular:"));
        assertEquals("Most popular: Java, Databases, Spring", result.getFirst());

        assertTrue(result.get(1).startsWith("Least popular:"));
        assertEquals("Least popular: DSA", result.get(1));

        assertTrue(result.get(2).startsWith("Highest activity:"));
        assertEquals("Highest activity: Java", result.get(2));

        assertTrue(result.get(3).startsWith("Lowest activity:"));
        assertEquals("Lowest activity: DSA", result.get(3));

        assertTrue(result.get(4).startsWith("Easiest course:"));
        assertEquals("Easiest course: Java", result.get(4));

        assertTrue(result.get(5).startsWith("Hardest course:"));
        assertEquals("Hardest course: Spring", result.get(5));
    }

    @Test
    void testStatisticsValidV2() {
        Student.getStudents().clear();
        Student.getEmails().clear();
        Student.resetIdCounter();

        Student.addStudent("Alexina Belcher address1@mail.com");
        Student.addStudent("Carmella Campman address2@mail.com");
        Student.addStudent("Olga Rosanne address3@mail.com");
        Student.addStudent("Nonna Miko address4@mail.com");

        Student.updatePoints("10000", 5, 4, 3, 1);
        Student.updatePoints("10001", 5, 4, 3, 1);
        Student.updatePoints("10002", 5, 4, 3, 1);
        Student.updatePoints("10003", 5, 4, 3, 1);

        Statistics statistics = new Statistics();
        List<String> result = statistics.listOverall();

        assertEquals(6, result.size());

        assertTrue(result.getFirst().startsWith("Most popular:"));
        assertEquals("Most popular: Java, DSA, Databases, Spring", result.getFirst());

        assertTrue(result.get(1).startsWith("Least popular:"));
        assertEquals("Least popular: n/a", result.get(1));

        assertTrue(result.get(2).startsWith("Highest activity:"));
        assertEquals("Highest activity: Java, DSA, Databases, Spring", result.get(2));

        assertTrue(result.get(3).startsWith("Lowest activity:"));
        assertEquals("Lowest activity: n/a", result.get(3));

        assertTrue(result.get(4).startsWith("Easiest course:"));
        assertEquals("Easiest course: Java", result.get(4));

        assertTrue(result.get(5).startsWith("Hardest course:"));
        assertEquals("Hardest course: Spring", result.get(5));
    }

    @Test
    void testStatsCourseJava() {
        String inputCourse = "java";

        List<String> results = Statistics.getTopStudents(Course.fromString(inputCourse));

        assertEquals(3, results.size());

        assertEquals("id    points    completed", results.getFirst());
        assertEquals("10001 24        4.0%", results.get(1));
        assertEquals("10000 21        3.5%", results.get(2));
    }

    @Test
    void testNotifyStudents() {
        Student.getStudents().clear();
        Student.getEmails().clear();
        Student.resetIdCounter();

        Student.addStudent("John Doe johnd@email.net");
        Student.addStudent("Jane Spark jspark@yahoo.com");


        Student.updatePoints("10000", 600, 400, 0, 0);

        List<String> results = Statistics.getCompletedCourses();

        assertEquals(1, results.size());
        assertEquals("Total 1 students have been notified.", results.getFirst());
    }
}