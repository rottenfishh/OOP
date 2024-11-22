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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestRecordBook {
    RecordBook book;
    RecordBook book2;

    @BeforeEach
    void setup() {
        book = new RecordBook(FREE, 3, false);
        book.setName("Alina", "Kolodina");
        for (int i = 1; i < book.currSemester; i++) {
            for (int j = 0; j < 3; j++) {
                book.addMark(i, MARKS, PASS, 1.0);
                if (j % 2 == 0) {
                    book.addMark(i, FINALS, EXAM, 5.0);
                    book.addMark(1, FINALS, DIFF_PASS, 5.0);
                    book.addMark(1, MARKS, TEST, 4.0);
                    book.addMark(1, MARKS, TEST, 5.0);
                } else {
                    book.addMark(i, FINALS, EXAM, 4.0);
                    book.addMark(i, FINALS, DIFF_PASS, 4.0);
                    book.addMark(i, MARKS, TEST, 3.0);
                }
            }
        }
        book2 = new RecordBook(PAID, 8, false);
        book2.setName("Pupa", "Pupov");
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 4; j++) {
                book2.addMark(i, FINALS, EXAM, 5.0);
                book2.addMark(i, FINALS, DIFF_PASS, 5.0);
                book2.addMark(i, MARKS, TASK, 5.0);
            }
        }
    }

    @Test
    void testAvgScore() {
        assertEquals(4.66, book.getAvgScore());
        assertEquals(5, book2.getAvgScore());
    }

    @Test
    void testGetMoreMoney() {
        assertFalse(book.getIncreasedMoney());
        assertFalse(book2.getIncreasedMoney());
    }

    @Test
    void testGetRedDiploma() {
        assertFalse(book.getRedDiploma());
        assertTrue(book2.getRedDiploma());
    }

    @Test
    void testTransferToFree() {
        assertFalse(book.transferToFreeBasis());
        assertTrue(book2.transferToFreeBasis());
    }

    @Test
    void testPrintInfo() {
        book.printInfo();
        book2.printInfo();
    }
}
