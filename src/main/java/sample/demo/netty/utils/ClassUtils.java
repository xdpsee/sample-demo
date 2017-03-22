package sample.demo.netty.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClassUtils {

    public static Collection<Class<?>> getClasses(String packageName, Class<?> baseClass)
            throws ClassNotFoundException, IOException {

        final List<Class<?>> classes = new ArrayList<>();

        ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());

        ImmutableSet<ClassPath.ClassInfo> classInfos = classPath.getTopLevelClassesRecursive(packageName);
        for (ClassPath.ClassInfo classInfo : classInfos) {
            Class<?> clazz = Class.forName(classInfo.getName());
            if (baseClass.isAssignableFrom(clazz)) {
                classes.add(clazz);
            }
        }

        return classes;
    }

}
