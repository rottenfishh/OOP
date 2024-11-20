package ru.nsu.kolodina.recordBook;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.nsu.kolodina.recordBook.RecordBook.Basis.FREE;
import static ru.nsu.kolodina.recordBook.Score.Name.DIFF_PASS;
import static ru.nsu.kolodina.recordBook.Score.Name.EXAM;
import static ru.nsu.kolodina.recordBook.Score.Type.FINALS;

public class Main {
    public static void main(String[] args) {
        RecordBook book = new RecordBook(FREE, 2, false);
        book.addMark(1, FINALS, EXAM, 5.0);
        book.addMark(1, FINALS, EXAM, 5.0);
        book.addMark(1, FINALS, EXAM, 5.0);
        book.addMark(1, FINALS, DIFF_PASS, 5.0);
        book.addMark(1, FINALS, DIFF_PASS, 5.0);
        book.addMark(1, FINALS, DIFF_PASS, 4.0);
        System.out.println(book.getAvgScore());
        System.out.println(book.getIncreasedMoney());
        System.out.println(book.getRedDiploma());
        System.out.println(Arrays.toString(book.getFinalScores().stream().mapToDouble(Score::getScore).toArray()));
    }
}