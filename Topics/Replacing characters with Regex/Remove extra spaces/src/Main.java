import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();
        String regex = "\\s+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println(matcher.replaceAll(" "));
    }
}