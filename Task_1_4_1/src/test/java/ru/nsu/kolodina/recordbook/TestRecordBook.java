package ru.nsu.kolodina.recordbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nsu.kolodina.recordbook.RecordBook.Basis.FREE;
import static ru.nsu.kolodina.recordbook.RecordBook.Basis.PAID;
import static ru.nsu.kolodina.recordbook.Score.Name.DIFF_PASS;
import static ru.nsu.kolodina.recordbook.Score.Name.EXAM;
import static ru.nsu.kolodina.recordbook.Score.Name.PASS;
import static ru.nsu.kolodina.recordbook.Score.Name.TASK;
import static ru.nsu.kolodina.recordbook.Score.Name.TEST;
import static ru.nsu.kolodina.recordbook.Score.Type.FINALS;
import static ru.nsu.kolodina.recordbook.Score.Type.MARKS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * class for testing record book.
 */
public class TestRecordBook {
    RecordBook book;
    RecordBook book2;

    @BeforeEach
    void setup() throws FileNotFoundException, URISyntaxException {
        FileReader reader = new FileReader();
        book = reader.readFromFile("BookAlina");
    }


    @Test
    void testAvgScore() {
        assertEquals(4.12, book.getAvgScore());
        //assertEquals(5, book2.getAvgScore());
    }

    @Test
    void testGetMoreMoney() {
        assertFalse(book.getIncreasedMoney());
        //assertFalse(book2.getIncreasedMoney());
    }

    @Test
    void testGetRedDiploma() {
        assertFalse(book.getRedDiploma());
        //assertTrue(book2.getRedDiploma());
    }

    @Test
    void testTransferToFree() {
        assertFalse(book.transferToFreeBasis());
        //assertTrue(book2.transferToFreeBasis());
    }

    @Test
    void testPrintInfo() {
        String result = book.getInfo();
        System.out.println(result);
        assertTrue(result.contains("Alina Kolodina"));
        assertTrue(result.contains("Course: 2"));
    }

}
