import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        String regex = "password:?\\s*([a-zA-Z0-9]+)";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        boolean passFound = false;
        while (matcher.find()) {
            passFound = true;
            System.out.println(matcher.group(1));
        }

        if (!passFound) {
            System.out.println("No passwords found.");
        }
    }
}