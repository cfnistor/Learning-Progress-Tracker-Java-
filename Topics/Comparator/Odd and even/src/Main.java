import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        ArrayList<Integer> evenNumber = new ArrayList<>();

        for (int n : numbers) {
            if (n % 2 == 0) {
                evenNumber.add(n);
            } else {
                oddNumbers.add(n);
            }
        }
        oddNumbers.sort(Comparator.naturalOrder());
        evenNumber.sort(Comparator.reverseOrder());
        oddNumbers.addAll(evenNumber);

        return oddNumbers;
    }
}