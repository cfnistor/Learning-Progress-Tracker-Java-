package tracker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");

        Menu.run();

        System.out.println("Bye!");
        ScannerUtil.closeScanner();
    }

    public static void backupMain() {
    }
}
