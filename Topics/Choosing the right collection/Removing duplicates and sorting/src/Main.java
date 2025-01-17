import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();

        Set<String> sortedStrings = new TreeSet<>();

        for (int i = 0; i < size; i++) {
            sortedStrings.add(scanner.nextLine());
        }

        for (String str : sortedStrings) {
            System.out.println(str);
        }

        scanner.close();
    }
}