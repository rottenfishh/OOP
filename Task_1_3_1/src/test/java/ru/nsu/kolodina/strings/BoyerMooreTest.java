package ru.nsu.kolodina.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing algorithm.
 */
public class BoyerMooreTest {
    BoyerMoore boyerMoore;

    @BeforeEach
    void setUp() {
        boyerMoore = new BoyerMoore();
    }

    @Test
    public void testSmall() throws IOException {
        File newFile = new File("file1.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        String string = new String("абракадабра".getBytes(), StandardCharsets.UTF_8);
        fileWriter.write(string);
        fileWriter.close();
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file1.txt", "бра", false);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1);
        excepted.add(8);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    public void testBig() throws IOException {
        File newFile = new File("file2.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        char[] chunk = new char[20000];
        Arrays.fill(chunk, 'a');
        fileWriter.write(chunk);
        fileWriter.write("hello");
        Arrays.fill(chunk, 'l');
        fileWriter.write(chunk);
        fileWriter.close();
        String pattern = "hello";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file2.txt", pattern, false);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(20000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyBig() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file3.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);

        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'h');
        fileWriter.write(chunk);
        fileWriter.write("hello");
        Arrays.fill(chunk, 'l');
        fileWriter.write(chunk);
        fileWriter.close();
        String pattern = "hello";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file3.txt", pattern, false);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(100000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyyBig() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file4.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);

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
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file4.txt", pattern, false);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1000000000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyyBigRussian() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file5.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        String s = new String("й".getBytes(), StandardCharsets.UTF_8);
        String string = new String(new char[maxSize]).replace("\0", s);
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(string);
        }
        String find = new String("прив".getBytes(), StandardCharsets.UTF_8);
        fileWriter.write(find);

        String s2 = new String("л".getBytes(), StandardCharsets.UTF_8);
        String string2 = new String(new char[maxSize]).replace("\0", s2);
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(string2);
        }
        fileWriter.flush();
        fileWriter.close();

        String pattern = "прив";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file5.txt", pattern, false);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1000000000);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testBook() {
        String pattern = "Romeo";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("romeoAndJuliet.txt", pattern, true);
        int excepted = 151;
        assertEquals(excepted, resultBoyerMoore.size());
    }

    @Test
    void testRussianBook() {
        String pattern = "князь";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("warAndPeace.txt", pattern, true);
        int excepted = 976;
        assertEquals(excepted, resultBoyerMoore.size());
    }

    @Test
    void testChinese() throws IOException {
        File newFile = new File("fileChinese.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        String ch = "在光下，我们一同前行在光下，未来无限在光下，不惧风雨";
        String txt = new String(ch.getBytes(), StandardCharsets.UTF_8);
        fileWriter.write(txt);
        fileWriter.flush();
        fileWriter.close();
        String pattern = "在光下";
        List<Integer> excepted = new ArrayList<>();
        excepted.add(0);
        excepted.add(10);
        excepted.add(18);
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("fileChinese.txt", pattern, true);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testChineseBook() {
        String pattern = "啊";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("chineseWarAndPeace.txt", pattern, true);
        int excepted = 555;
        assertEquals(excepted, resultBoyerMoore.size());
    }

}
