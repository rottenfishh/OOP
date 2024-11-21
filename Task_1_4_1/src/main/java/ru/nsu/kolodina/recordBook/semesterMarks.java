package ru.nsu.kolodina.recordBook;

import java.util.ArrayList;
import java.util.List;

public class semesterMarks {
    public int semester;
    public List<List<Score>> finalScores; // exam, diff pass, pass scores
    public List<Score> examScores;
    public List<Score> diffScores;
    public List<Score> passScores;
    public List<Score> marks; // usual marks. tasks, tests, colloqiums, practice
    public List<List<Score>> allScores;

    public semesterMarks(int semester) { // create semester grades book
        this.semester = semester;
        int numExams = 0;
        int numDiffs = 0;
        int numPasses = 0;
        int numMarks = 0;
        switch (semester) {
            case 1:
                numExams = numDiffs = numPasses = 3;
                numMarks = 6;
                break;
            case 2:
                numExams = numDiffs = 3;
                numPasses = 2;
                numMarks = 6;
                break;
            case 3:
                numExams = 2;
                numDiffs = 6;
                numMarks = 5;
                break;
            case 4:
                numExams = numDiffs = 5;
                numMarks = 3;
                break;
            case 5:
                numExams = 3;
                numDiffs = 4;
                numMarks = 4;
                break;
            case 6:
                numExams = 2;
                numDiffs = 6;
                numMarks = 4;
                break;
            case 7:
                numExams = 1;
                numDiffs = 4;
                numMarks = 2;
                break;
            case 8:
                numDiffs = 4;
                numPasses = 1;
                numMarks = 2;
                break;
        }
        examScores = new ArrayList<>(numExams);
        diffScores = new ArrayList<>(numDiffs);
        passScores = new ArrayList<>(numPasses);
        marks = new ArrayList<>(numMarks);
        finalScores = new ArrayList<>(numExams + numDiffs);
        allScores = new ArrayList<>(numMarks + numDiffs + numExams + numPasses);
        finalScores.add(examScores);
        finalScores.add(diffScores);
        allScores.add(examScores);
        allScores.add(diffScores);
        allScores.add(passScores);
        allScores.add(marks);
    }
}
