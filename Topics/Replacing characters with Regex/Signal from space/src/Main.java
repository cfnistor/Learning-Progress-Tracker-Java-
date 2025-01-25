import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String[] decipherCosmicSignal(String[] spaceSignalArray) {
        String[] newArray = new String[spaceSignalArray.length];
        String regex = "~";
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i < newArray.length; i++) {
                Matcher matcher = pattern.matcher(spaceSignalArray[i]);
                newArray[i] = matcher.replaceAll("");
        }
        return newArray;
    }    

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] encryptedSpaceSignalArray = scanner.nextLine().split("\\s");
        String[] spaceSignalArray = decipherCosmicSignal(encryptedSpaceSignalArray);
        StringBuilder spaceMessage = new StringBuilder();
        for (String signal : spaceSignalArray) {
            spaceMessage.append(signal).append("\s");
        }
        System.out.println(spaceMessage.toString().trim());
    }
}