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
    void setup() {
        List<int[]> numberOfMarks = new ArrayList<>();
        numberOfMarks.add(new int[]{0, 19, 35, 7, 33});
        numberOfMarks.add(new int[]{1, 3, 3, 3, 6});
        numberOfMarks.add(new int[]{2, 3, 3, 2, 6});
        numberOfMarks.add(new int[]{3, 2, 6, 0, 6});
        numberOfMarks.add(new int[]{4, 5, 5, 0, 3});
        numberOfMarks.add(new int[]{5, 3, 4, 0, 4});
        numberOfMarks.add(new int[]{6, 2, 6, 0, 4});
        numberOfMarks.add(new int[]{7, 1, 4, 0, 2});
        numberOfMarks.add(new int[]{8, 0, 4, 1, 2});
        book = new RecordBook(FREE, 3, false, numberOfMarks);
        book.setName("Alina", "Kolodina");
        for (int i = 1; i < book.currSemester; i++) {
            book.addMark(i, MARKS, PASS, 1.0, "English");
            if (i % 2 == 0) {
                book.addMark(i, FINALS, EXAM, 3.0, "Math");
                book.addMark(i, FINALS, DIFF_PASS, 5.0, "Programming");
                book.addMark(i, MARKS, TEST, 4.0, "Math");
                book.addMark(i, MARKS, TEST, 5.0, "Math");
            } else {
                book.addMark(i, FINALS, EXAM, 4.0, "Math");
                book.addMark(i, FINALS, DIFF_PASS, 4.0, "Programming");
                book.addMark(i, MARKS, TEST, 3.0, "Math");
            }
        }
        book2 = new RecordBook(PAID, 8, false, numberOfMarks);
        book2.setName("Pupa", "Pupov");
        for (int i = 1; i <= 8; i++) {
            for (int j = 0; j < 4; j++) {
                book2.addMark(i, FINALS, EXAM, 5.0, "Math");
                book2.addMark(i, FINALS, DIFF_PASS, 5.0, "Programming");
                book2.addMark(i, MARKS, TASK, 5.0, "Math");
            }
        }
    }


    @Test
    void testAvgScore() {
        assertEquals(4.0, book.getAvgScore());
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
        String result = book.getInfo();
        System.out.println(result);
        assertTrue(result.contains("Alina Kolodina"));
        assertTrue(result.contains("Course: 2"));
    }
}
