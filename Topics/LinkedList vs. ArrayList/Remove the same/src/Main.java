import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        for(int i = arrayList.size() - 1; i >= 0; i--) {
            if (arrayList.get(i).equalsIgnoreCase(linkedList.get(i))) {
                arrayList.remove(i);
                linkedList.remove(i);
            }
        }
    }
}