/**
 Get list of public fields the object declares (inherited fields should be skipped).
 */

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

class FieldGetter {

    public String[] getPublicFields(Object object) {
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