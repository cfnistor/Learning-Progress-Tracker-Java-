import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfElements = scanner.nextInt();
        List<Integer> elements = new ArrayList<>(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            elements.add(scanner.nextInt());
        }


        for (int i = elements.size() - 1; i >= 0; i--) {
            System.out.println(elements.get(i));
        }
    }
}