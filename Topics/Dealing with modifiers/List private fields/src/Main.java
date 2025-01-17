import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 Get sorted list of private fields the object declares (inherited fields should be skipped).
 */
class FieldGetter {

    public List<String> getPrivateFields(Object object) {
        Class<?> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        List<String> privateFields = new ArrayList<>();
        for (Field field : fields) {
            int modifier = field.getModifiers();
            if (Modifier.isPrivate(modifier)) {
                privateFields.add(field.getName());
            }
        }
        privateFields.sort(Comparator.naturalOrder());
        return privateFields;
    }

}