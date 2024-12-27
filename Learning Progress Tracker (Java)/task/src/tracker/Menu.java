package tracker;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("No input.");
            } else if ("exit".equalsIgnoreCase(input)) {
                break;
            } else {
                command(input);
            }

        }
        scanner.close();
    }

    private static void command(String input) {
        switch (input) {
            case "add students":
                addStudents();
                break;
            case "back":
                System.out.println("Enter 'exit' to exit the program");
                break;
            default:
                System.out.println("Error: unknown command!");
        }
    }

    private static void addStudents() {
        String input = "";
        System.out.println("Enter student credentials or 'back' to return:");
        do {
            input = scanner.nextLine();
            if (!"back".equalsIgnoreCase(input)) {
                Student.addStudent(input);
            }
        } while (!"back".equalsIgnoreCase(input));
        System.out.printf("Total %d students have been added.%n", Student.getStudents().size());
    }
}
