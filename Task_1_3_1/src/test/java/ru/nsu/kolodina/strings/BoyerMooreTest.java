package ru.nsu.kolodina.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoyerMooreTest {
    BoyerMoore boyerMoore;

    @BeforeEach
    void setUp() {
        boyerMoore = new BoyerMoore();
    }

    /*@Test
    public void testSmall() throws IOException {
        File newFile = new File("file1.txt");
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write("абракадабра");
        fileWriter.close();
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file1.txt", "бра");
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1);
        excepted.add(8);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    public void testBig() throws IOException {
        File newFile = new File("file2.txt");
        FileWriter fileWriter = new FileWriter(newFile);
        char[] chunk = new char[20000];
        Arrays.fill(chunk, 'a');
        fileWriter.write(chunk);
        fileWriter.write("hello");
        Arrays.fill(chunk, 'l');
        fileWriter.write(chunk);
        fileWriter.close();
        String pattern = "hello";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file2.txt", pattern);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(20000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyBig() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file3.txt");
        FileWriter fileWriter = new FileWriter(newFile);

        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'h');
        fileWriter.write(chunk);
        fileWriter.write("hello");
        Arrays.fill(chunk, 'l');
        fileWriter.write(chunk);
        fileWriter.close();
        String pattern = "hello";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file3.txt", pattern);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(100000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyyBig() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file4.txt");
        FileWriter fileWriter = new FileWriter(newFile);

        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'h');

        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }

        fileWriter.write("hello");

        Arrays.fill(chunk, 'l');
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }

        fileWriter.close();

        String pattern = "hello";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file4.txt", pattern);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1000000000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }*/
    @Test
    void testReallyyBigRussian() throws IOException {
        int maxSize = 10000;
        File newFile = new File("file4.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile), StandardCharsets.UTF_8);
        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, "й".charAt(0));

        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }

        fileWriter.write("прив");

        Arrays.fill(chunk, "л".charAt(0));
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }
        fileWriter.flush();
        fileWriter.close();

        String pattern = "прив";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file4.txt", pattern);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(100000000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }
}
