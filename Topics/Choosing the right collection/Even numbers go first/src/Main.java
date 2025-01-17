import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Deque<Integer> numbers = new ArrayDeque<>();
        for (int i = 0; i < size; i++) {
            int input = scanner.nextInt();
            if (input % 2 != 0) {
                numbers.addLast(input);
            } else {
                numbers.addFirst(input);
            }
        }
        for (int number : numbers) {
            System.out.println(number);
        }
    }
}