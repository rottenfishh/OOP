package ru.nsu.kolodina.strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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
        String string = new String("абракадабра".getBytes(), StandardCharsets.UTF_8);
        List<Long> resultBoyerMoore = boyerMoore.search(string, "бра", 0);
        List<Long> excepted = List.of(1L, 8L);
        assertEquals(excepted, resultBoyerMoore);
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
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file2.txt", pattern, false);
        List<Long> excepted = List.of(20000L);
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
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file3.txt", pattern, false);
        List<Long> excepted = List.of(100000L);
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
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file4.txt", pattern, false);
        List<Long> excepted = List.of(1000000000L);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testReallyyBigRussian() throws IOException {
        int maxSize = 100000;
        File newFile = new File("file5.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'й');
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }
        String find = "прив";
        fileWriter.write(find);

        Arrays.fill(chunk, 'л');
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(chunk);
        }
        fileWriter.flush();
        fileWriter.close();

        String pattern = "прив";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file5.txt", pattern, false);
        List<Long> excepted = List.of(1000000000L);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testBook() {
        String pattern = "Romeo";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("romeoAndJuliet.txt", pattern, true);
        int excepted = 151;
        assertEquals(excepted, resultBoyerMoore.size());
    }

    @Test
    void testRussianBook() {
        String pattern = "князь";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("warAndPeace.txt", pattern, true);
        int excepted = 976;
        assertEquals(excepted, resultBoyerMoore.size());
    }

    @Test
    void testChinese() {
        String ch = "在光下，我们一同前行在光下，未来无限在光下，不惧风雨";
        String txt = new String(ch.getBytes(), StandardCharsets.UTF_8);
        String pattern = "在光下";
        List<Long> excepted = List.of(0L, 10L, 18L);
        List<Long> resultBoyerMoore = boyerMoore.search(txt, pattern, 0);
        assertEquals(excepted, resultBoyerMoore);
    }

    @Test
    void testChineseBook() {
        String pattern = "啊";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("chineseWarAndPeace.txt",
                pattern, true);
        int excepted = 555;
        assertEquals(excepted, resultBoyerMoore.size());
    }

    @Test
    void testMixed() {
        String txt = new String("абраkadaбра".getBytes(), StandardCharsets.UTF_8);
        String pattern = "бра";
        List<Long> resultBoyerMoore = boyerMoore.search(txt,
                pattern, 0);
        List<Long> excepted = List.of(1L, 8L);
        assertEquals(excepted, resultBoyerMoore);
    }

    @Test
    void testBorder() throws IOException {
        int maxSize = 50000 - 2;
        File newFile = new File("file6.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'a');
        fileWriter.write(chunk);
        String find = "hello";
        fileWriter.write(find);

        Arrays.fill(chunk, 'b');
        fileWriter.write(chunk);
        fileWriter.flush();
        fileWriter.close();

        String pattern = "hello";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file6.txt", pattern, false);
        List<Long> excepted = List.of(50000L - 2L);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }

    @Test
    void testGiganticFile() throws IOException {
        int maxSize = 20000000;
        File newFile = new File("file7.txt");
        OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(newFile),
                StandardCharsets.UTF_8);
        char[] chunk = new char[maxSize];
        Arrays.fill(chunk, 'a');
        for (int i = 0; i < 1000; i++) {
            fileWriter.write(chunk);
        }
        String find = "hello";
        fileWriter.write(find);
        fileWriter.write("something");
        fileWriter.flush();
        fileWriter.close();

        String pattern = "hello";
        List<Long> resultBoyerMoore = boyerMoore.findInFile("file7.txt", pattern, false);
        List<Long> excepted = List.of(20000000000L);
        assertEquals(excepted, resultBoyerMoore);
        newFile.delete();
    }
}
