// You can experiment here, it won’t be checked

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Task {
  public static void main(String[] args) {
    System.out.println("Public methods: " + ReflectionUtils.countPublicMethods(Student.class));
  }
}

class ReflectionUtils {

  public static int countPublicMethods(Class<?> targetClass) {
    Method[] methods = targetClass.getDeclaredMethods();
    int counter = 0;
    for (Method method : methods) {
      int modifier = method.getModifiers();
      if (modifier == 1) {
        counter++;
      }
    }
    return  counter;
  }
}

class Student {
  public void methodA() {
    System.out.println("method A");
  }

  public void methodB() {
    System.out.println("method B");
  }

  private void methodC() {
    System.out.println("method C");
  }

  protected void methodD() {
    System.out.println("method D");
  }
}
