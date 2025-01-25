import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHtmlTags = scanner.nextLine().trim();

        String regex ="<[^>]+>";

        System.out.println(stringWithHtmlTags.replaceAll(regex, ""));

        scanner.close();
    }
}