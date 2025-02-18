package tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private static Map<Integer, Student> students = Student.getStudents();
    private static Map<String, Integer> enrollment = new HashMap<>();
    private static Map<String, Integer> activity = new HashMap<>();
    private static Map<String, Double> averageScore = new HashMap<>();

    public Statistics() {
        calculateStatistics();
    }

    private void calculateStatistics() {

        // Initialize course stats
        for (Course course : Course.values()) {
            enrollment.put(course.getName(), 0);
            activity.put(course.getName(), 0);
            averageScore.put(course.getName(), 0.0);
        }

        // Compute enrollment, activity
        if (students.isEmpty()) {
            return;
        }

        Map<String, Integer> totalScores = new HashMap<>();

        for (Student student : students.values()) {
            Map<Course, Integer> coursePoints = student.getCoursePoints();
            Map<Course, Integer> courseActivity = student.getAssignmentCounter();
            for (Course course : Course.values()) {
                String currentCourse = course.getName();
                int points = coursePoints.get(course);
                int submissions = courseActivity.get(course);
                if (points > 0) {
                    enrollment.put(currentCourse, enrollment.get(currentCourse) + 1);
                    totalScores.put(currentCourse, totalScores.getOrDefault(currentCourse, 0) + points);
                }
                if (submissions > 0) {
                    activity.put(currentCourse, activity.get(currentCourse) + submissions);
                }
            }
        }

        for (Course course : Course.values()) {
            String currentCourse = course.getName();
            averageScore.put(currentCourse, totalScores.get(currentCourse) / (double)activity.get(currentCourse));
        }
    }

    private List<String> getCoursesByStat(Map<String, Integer> stat, boolean findMax) {
        int bestValue = findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        List<String> bestCourses = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : stat.entrySet()) {
            int value = entry.getValue();
            if (value == bestValue) {
                bestCourses.add(entry.getKey());
            } else if (findMax ? value > bestValue : value < bestValue) {
                bestCourses.clear();
                bestCourses.add(entry.getKey());
                bestValue = value;
            }
        }
        return bestCourses.isEmpty() || bestValue == 0 ? List.of() : bestCourses;
    }

    private List<String> getCoursesByAvgScore(boolean findMax) {
        double bestValue = findMax ? Double.MIN_VALUE : Double.MAX_VALUE;
        List<String> bestCourses = new ArrayList<>();

        for (Map.Entry<String, Double> entry : averageScore.entrySet()) {
            double value = entry.getValue();
            if (value == bestValue) {
                bestCourses.add(entry.getKey());
            } else if (findMax ? value > bestValue : value < bestValue) {
                bestCourses.clear();
                bestCourses.add(entry.getKey());
                bestValue = value;
            }
        }

        return bestCourses.isEmpty() || bestValue == 0.0 ? List.of() : bestCourses;
    }

    public List<String> listOverall() {
        List<String> results = new ArrayList<>();
        results.add("Most popular: " + formatCourseList(getCoursesByStat(enrollment, true)));
        results.add("Least popular: " + formatCourseList(getCoursesByStat(enrollment, false)));
        results.add("Highest activity: " + formatCourseList(getCoursesByStat(activity, true)));
        results.add("Lowest activity: " + formatCourseList(getCoursesByStat(activity, false)));
        results.add("Easiest course: " + formatCourseList(getCoursesByAvgScore(true)));
        results.add("Hardest course: " + formatCourseList(getCoursesByAvgScore(false)));

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
}
