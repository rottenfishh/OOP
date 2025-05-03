package ru.nsu.kolodina.ooptasks;

import java.io.File;

/**
 * Utility class that provides helper methods for loading class instances
 * and deleting directories.
 */
public class Utils {

    /**
     * Loads and returns an instance of a class specified by the class path.
     * The class must have a no-argument constructor.
     *
     * @param classPath the fully qualified name of the class to load
     * @param type      the class type to cast the loaded class to
     * @param <T>       the type of the class to be loaded
     * @return an instance of the specified class
     * @throws Exception if the class cannot be loaded or instantiated
     */
    public static <T> T loadClassInstance(String classPath, Class<T> type) throws Exception {
        // Load the class by name
        Class<?> clazz = Class.forName(classPath);

        // Check if the class is assignable to the specified type
        if (!type.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Class " + classPath + " does not implement/extend " + type.getName());
        }

        // Instantiate and return the object
        return type.cast(clazz.getDeclaredConstructor().newInstance());
    }

    /**
     * Recursively deletes a directory and all its contents.
     *
     * @param file the directory to delete
     */
    public static void deleteDirectory(File file) {
        // Iterate through all the files and subdirectories inside the directory
        for (File subfile : file.listFiles()) {
            if (subfile.isDirectory()) {
                deleteDirectory(subfile); // Recursively delete subdirectory
            }
            subfile.delete(); // Delete file or empty directory
        }
        file.delete(); // Delete the parent directory
    }
}
