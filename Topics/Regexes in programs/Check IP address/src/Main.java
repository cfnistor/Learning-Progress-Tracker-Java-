import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        String regex = "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?|0)\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]?|0)$";
        Scanner scanner = new Scanner(System.in);
        String ipAddress = scanner.nextLine();

        if (ipAddress.matches(regex)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}