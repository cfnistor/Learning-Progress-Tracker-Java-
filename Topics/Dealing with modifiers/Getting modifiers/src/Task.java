// You can experiment here, it won’t be checked

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
  public static void main(String[] args) {
    Student student = new Student();
    System.out.println(Arrays.toString(FieldGetter.getPublicFields(student)));
  }
}

class FieldGetter {

  public static String[] getPublicFields(Object object) {
    Class<?> objectClass = object.getClass();
    Field[] fields = objectClass.getDeclaredFields();
    List<String> publicFields = new ArrayList<>();

    for (Field field : fields) {
      int modifiers = field.getModifiers();
      if (modifiers == 1) {
        publicFields.add(field.getName());
      }
    }
    return publicFields.toArray(String[]::new);
  }

}

class Student {
  private String firstName;
  public String lastName;
  public String dateOfBirth;
  public int age;
}
