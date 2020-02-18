package com.mrporter.pomangam._bases.utils.reflection;

import javax.persistence.Id;
import java.lang.reflect.Field;

public class ReflectionUtils {
    public static <T> void oldInstanceByNewInstance(T oldInstance, T newInstance) throws Exception {
        Class<?> newInstanceClass = newInstance.getClass();
        Class<?> oldInstanceClass = oldInstance.getClass();

        if(!newInstanceClass.isAssignableFrom(oldInstanceClass)){
            return;
        }

        for (Field newField : newInstanceClass.getDeclaredFields()) {
            newField.setAccessible(true);
            Object obj;
            try {
                obj = newField.get(newInstance);
            } catch (IllegalAccessException e) {
                throw new ReflectionException();
            }
            Id id = newField.getAnnotation(Id.class);
            PatchIgnore patchIgnore = newField.getAnnotation(PatchIgnore.class);
            if (id == null) {
                if (obj != null && patchIgnore == null) {
                    Field oldField;
                    try {
                        oldField = oldInstanceClass.getDeclaredField(newField.getName());
                        oldField.setAccessible(true);
                        oldField.set(oldInstance, obj);
                    } catch (NoSuchFieldException e) {
                        throw new ReflectionException();
                    } catch (IllegalAccessException e) {
                        throw new ReflectionException();
                    }
                }
            }
        }
    }
}
