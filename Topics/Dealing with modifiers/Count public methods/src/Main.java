import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class ReflectionUtils {

    public static int countPublicMethods(Class targetClass) {
        Method[] methods = targetClass.getDeclaredMethods();
        int counter = 0;
        for (Method method : methods) {
            int modifier = method.getModifiers();
            if (Modifier.isPublic(modifier)) {
                counter++;
            }
        }
        return counter;
    }
}