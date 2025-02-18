import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();

        String dateRegex = "^(?:(?:19\\d{2}|20\\d{2})[-/.](0[1-9]|1[0-2])[-/.](0[1-9]|[12]\\d|3[01])|"
                + "(0[1-9]|[12]\\d|3[01])[-/.](0[1-9]|1[0-2])[-/.](19\\d{2}|20\\d{2}))$";

        Pattern pattern = Pattern.compile(dateRegex);
        Matcher matcher = pattern.matcher(date);

        System.out.println(matcher.matches() ? "Yes" : "No");
    }
}