package tracker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");

        Menu.run();

        System.out.println("Bye!");
        ScannerUtil.closeScanner();
    }
} 