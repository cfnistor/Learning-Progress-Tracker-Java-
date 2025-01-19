package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {


    @BeforeEach
    void setUp() {
        Student.getStudents().clear();
        Student.getEmails().clear();
        Student.resetIdCounter();

        Student.addStudent("John Doe john.doe@gmail.com");
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
    void testFindValid() {
        String inputAddPoints = "10000 12 13 14 15";
        Scanner scannerAddPoints = new Scanner(new ByteArrayInputStream(inputAddPoints.getBytes()));
        Menu.addPoints(scannerAddPoints);

        String inputId = "10000\n";
        Scanner scannerId = new Scanner(new ByteArrayInputStream(inputId.getBytes()));

        String result = Menu.find(scannerId);
        assertEquals("10000 points: Java=12; DSA=13; Databases=14; Spring=15", result);
    }
}