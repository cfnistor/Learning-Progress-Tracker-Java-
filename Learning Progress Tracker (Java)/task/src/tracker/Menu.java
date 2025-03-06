package tracker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = ScannerUtil.getScanner();

    public static void run() {
        while (true) {
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("No input.");
            } else if ("exit".equalsIgnoreCase(input)) {
                break;
            } else {
                Command command = Command.fromString(input);
                if (command == Command.ADD_POINTS) {
                    System.out.println("Enter an id and points or 'back' to return");
                    String result;
                    do {
                        result = addPoints(scanner);
                        if (!"back".equalsIgnoreCase(input)) {
                            System.out.println(result);
                        }
                    } while (!"back".equalsIgnoreCase(result));
                } else if (command == Command.FIND) {
                    System.out.println("Enter an id or 'back' to return");
                    String result;
                    do {
                        result = find(scanner);
                        if (!"back".equalsIgnoreCase(input)) {
                            System.out.println(result);
                        }
                    } while (!"back".equalsIgnoreCase(result));
                } else if(command == Command.STATISTICS) {
                    System.out.println("Type the name of a course to see details or 'back' to quit:");
                    Statistics statistics = new Statistics();
                    for (String line : statistics.listOverall()) {
                        System.out.println(line);
                    }

                    List<String> result;
                    do {
                        result = statistics(scanner);
                        if (!"back".equalsIgnoreCase(input)) {
                            for (String line : result) {
                                if (line.equalsIgnoreCase("back")) {
                                    break;
                                }

                                Course course = Course.fromString(line);
                                if (course == null) {
                                    System.out.println("Unknown course.");
                                    break;
                                }

                                System.out.println(line.substring(0, 1).toUpperCase() + line.substring(1));

                                for (String courseStatsLine : Statistics.getTopStudents(course)) {
                                    System.out.println(courseStatsLine);
                                }
                            }
                        }
                    } while (!"back".equalsIgnoreCase(result.getFirst()));
                } else {
                    command.execute();
                }

            }

        }
        scanner.close();
    }

    public static void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        String input;
        do {
            input = scanner.nextLine();
            if (!"back".equalsIgnoreCase(input)) {
                Student.addStudent(input);
            }
        } while (!"back".equalsIgnoreCase(input));
        System.out.printf("Total %d students have been added.%n", Student.getStudents().size());
    }

    public static void listStudents() {
        if (Student.getStudents().isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.println("Students:");
        for (Integer key : Student.getStudents().keySet()) {
            System.out.println(key);
        }
    }

    public static String addPoints(Scanner inputScanner) {
        String defaultErrorMessage = "Incorrect points format.";
        String input = inputScanner.nextLine().trim();
        if ("back".equalsIgnoreCase(input)) {
            return "back";
        }

        String[] parts = input.split("\\s+");
        if (parts.length != 5) {
            return defaultErrorMessage;
        }

        try {
            String inputId = parts[0].trim();
            int javaPoints = Integer.parseInt(parts[1]);
            int dsaPoints = Integer.parseInt(parts[2]);
            int dbPoints = Integer.parseInt(parts[3]);
            int springPoints = Integer.parseInt(parts[4]);

            if (javaPoints < 0 || dsaPoints < 0 || dbPoints < 0 || springPoints < 0) {
                return defaultErrorMessage;
            }

            if (!Student.updatePoints(inputId, javaPoints, dsaPoints, dbPoints, springPoints)) {
                return String.format("No student is found for id=%s.", inputId);
            }

        } catch (NumberFormatException e) {
            return defaultErrorMessage;
        }

        return "Points updated.";
    }

    public static String find(Scanner inputScanner) {
        String input = inputScanner.nextLine().trim();

        if ("back".equalsIgnoreCase(input)) {
            return "back";
        }

        int id = Integer.parseInt(input);

        Student student = Student.getStudents().get(id);
        if (student == null) {
            return String.format("No student is found for id=%d", id);
        }

        return student.printDetails();
    }

    public static List<String> statistics(Scanner scanner) {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();

            if ("back".equalsIgnoreCase(input)) {
                System.out.println("back");
            }


            return Statistics.getCourseStats(input);
        }

        return Collections.emptyList();
    }
}
