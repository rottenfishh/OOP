package ru.nsu.kolodina.ooptasks;

import java.io.File;

public class Utils {

    public static <T> T loadClassInstance(String classPath, Class<T> type) throws Exception {
        Class<?> clazz = Class.forName(classPath);

        if (!type.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Class " + classPath + " does not implement/extend " + type.getName());
        }

        return type.cast(clazz.getDeclaredConstructor().newInstance());
    }

    public static void deleteDirectory(File file) {
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
            subfile.delete();
        }
        file.delete();
    }

}
