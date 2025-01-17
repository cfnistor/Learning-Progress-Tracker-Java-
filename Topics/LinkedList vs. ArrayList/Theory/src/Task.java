// You can experiment here, it won’t be checked

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import List

public class Task {
  public static void main(String[] args) {
    LinkedList<String> linkedList = new LinkedList<>(List.of("a", "b", "c", "d", "e"));
    ArrayList<String> arrayList = new ArrayList<>(List.of("f", "g", "h", "i", "f"));
    LinkedList<String> newLinkedList = mergeLists(linkedList, arrayList);
    System.out.println(newLinkedList);
  }
}
