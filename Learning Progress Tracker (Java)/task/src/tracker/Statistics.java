package tracker;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private static Map<Integer, Student> students = Student.getStudents();
    private static Map<String, Integer> enrollment = new HashMap<>();
    private static Map<String, Integer> activity = new HashMap<>();
    private static Map<String, Double> averageScore = new HashMap<>();

    public Statistics() {
        calculateStatistics();
    }

    private void calculateStatistics() {
        // Initialize maps
        for (Course course : Course.values()) {
            enrollment.put(course.getName(), 0);
            activity.put(course.getName(), 0);
            averageScore.put(course.getName(), 0.0);
        }

        if (students.isEmpty()) return;

        Map<String, Integer> totalScores = new HashMap<>();

        for (Student student : students.values()) {
            for (Course course : Course.values()) {
                String courseName = course.getName();
                int points = student.getCoursePoints().getOrDefault(course, 0);
                int submissions = student.getAssignmentCounter().getOrDefault(course, 0);

                if (points > 0) {
                    enrollment.put(courseName, enrollment.get(courseName) + 1);
                    totalScores.put(courseName, totalScores.getOrDefault(courseName, 0) + points);
                }
                if (submissions > 0) {
                    activity.put(courseName, activity.get(courseName) + submissions);
                }
            }
        }

        for (Course course : Course.values()) {
            String courseName = course.getName();
            if (activity.get(courseName) > 0) {
                averageScore.put(courseName, totalScores.get(courseName) / (double) activity.get(courseName));
            }
        }
    }

    private List<String> getCoursesByStat(Map<String, Integer> stat, boolean findMax, Set<String> excluded) {
        int bestValue = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<String> bestCourses = new ArrayList<>();

        for (var entry : stat.entrySet()) {
            String course = entry.getKey();
            int value = entry.getValue();
            if (excluded.contains(course)) continue;

            if (value == bestValue) {
                bestCourses.add(course);
            } else if (findMax ? value > bestValue : value < bestValue) {
                bestCourses.clear();
                bestCourses.add(course);
                bestValue = value;
            }
        }

        return bestCourses.isEmpty() || bestValue == 0 ? List.of() : bestCourses;
    }

    private List<String> getCoursesByAvgScore(boolean findMax, Set<String> excluded) {
        double bestValue = findMax ? Double.MIN_VALUE : Double.MAX_VALUE;
        List<String> bestCourses = new ArrayList<>();

        for (var entry : averageScore.entrySet()) {
            String course = entry.getKey();
            double value = entry.getValue();
            if (excluded.contains(course)) continue;

            if (value == bestValue) {
                bestCourses.add(course);
            } else if (findMax ? value > bestValue : value < bestValue) {
                bestCourses.clear();
                bestCourses.add(course);
                bestValue = value;
            }
        }

        return bestCourses.isEmpty() || bestValue == 0 ? List.of() : bestCourses;
    }

    public List<String> listOverall() {
        List<String> results = new ArrayList<>();
        Set<String> excludedCourses = new HashSet<>();

        // Most popular
        List<String> mostPopular = getCoursesByStat(enrollment, true, excludedCourses);
        if (mostPopular.isEmpty()) {
            results.add("Most popular: n/a");
        } else {
            results.add("Most popular: " + String.join(", ", mostPopular));
            excludedCourses.addAll(mostPopular);
        }


        List<String> leastPopular = getCoursesByStat(enrollment, false, excludedCourses);
        if (leastPopular.isEmpty()) {
            results.add("Least popular: n/a");
        } else {
            results.add("Least popular: " + String.join(", ", leastPopular));
            excludedCourses.addAll(leastPopular);
        }

        // Reset excluded for Highest activity
        excludedCourses.clear();

        List<String> highestActivity = getCoursesByStat(activity, true, excludedCourses);
        if (highestActivity.isEmpty()) {
            results.add("Highest activity: n/a");
        } else {
            results.add("Highest activity: " + String.join(", ", highestActivity));
            excludedCourses.addAll(highestActivity);
        }


        List<String> lowestActivity = getCoursesByStat(activity, false, excludedCourses);
        if (lowestActivity.isEmpty()) {
            results.add("Lowest activity: n/a");
        } else {
            results.add("Lowest activity: " + String.join(", ", lowestActivity));
            excludedCourses.addAll(lowestActivity);
        }

        // Reset excluded for Easiest course
        excludedCourses.clear();

        List<String> easiestCourses = getCoursesByAvgScore(true, excludedCourses);
        if (easiestCourses.isEmpty()) {
            results.add("Easiest course: n/a");
        } else {
            results.add("Easiest course: " + String.join(", ", easiestCourses));
            excludedCourses.addAll(easiestCourses);
        }

        List<String> hardestCourses = getCoursesByAvgScore(false, excludedCourses);
        if (hardestCourses.isEmpty()) {
            results.add("Hardest course: n/a");
        } else {
            results.add("Hardest course: " + String.join(", ", hardestCourses));
        }

        return results;
    }

    private String formatCourseList(List<String> courses) {
        return courses.isEmpty() ? "n/a" : String.join(", ", courses);
    }

    public static List<String> getCourseStats(String course) {
        List<String> results = new ArrayList<>();
        results.add(course);


        return results;
    }

    public static List<String> getTopStudents(Course course) {
        List<String> result = new ArrayList<>();
        result.add(String.format("%-5s %-9s %s", "id", "points", "completed"));

        students.values().stream()
                .filter(student -> student.getCoursePoints().getOrDefault(course, 0) > 0)
                .sorted(Comparator.comparing((Student s) -> s.getCoursePoints().getOrDefault(course, 0))
                        .reversed()
                        .thenComparing(Student::getId))
                .map(student -> {
                    int points = student.getCoursePoints().getOrDefault(course, 0);
                    double percentage = (points * 100.0) / course.getRequiredPointsToCompleteCourse();
                    return String.format("%-5d %-9d %.1f%%", student.getId(), points, percentage);
                })
                .forEach(result::add);

        return result;
    }

    public static List<String> getCompletedCourses() {
        final List<String> result = new ArrayList<>();
        final Set<Integer> notifiedStudents = new HashSet<>();

        students.values().stream()
                .filter(student -> Arrays.stream(Course.values())
                        .anyMatch(course -> student.getCoursePoints().getOrDefault(course, 0) >= course.getRequiredPointsToCompleteCourse() && !student.isNotificationSent(course)))
                .forEach(student -> {
                    Arrays.stream(Course.values())
                            .filter(course -> student.getCoursePoints().getOrDefault(course, 0) >= course.getRequiredPointsToCompleteCourse() && !student.isNotificationSent(course))
                            .forEach(student::sendNotification);
                    notifiedStudents.add(student.getId());
                });

        result.add(String.format("Total %d students have been notified.", notifiedStudents.size()));
        return result;
    }

}