import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        String regex = "[ABCEHKMOPTYX]\\d{3}[ABCEHKMOPTYX]{2}";

        if (regNum.matches(regex)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}