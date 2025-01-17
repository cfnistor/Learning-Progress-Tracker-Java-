import java.util.ArrayList;
import java.util.LinkedList;

class ListOperations {
    public static void transferAllElements(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            String temp = arrayList.get(i);
            arrayList.set(i, linkedList.get(i));
            linkedList.set(i, temp);
        }
    }
}