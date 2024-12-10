package ru.nsu.kolodina.recordbook;

import java.util.ArrayList;
import java.util.List;

/**
 * class implementing logic of containing records for one given semester.
 */
public class SemesterMarks {
    public int semester;
    public List<List<Score>> finalScores; // exam, diff pass, pass scores
    public List<Score> examScores;
    public List<Score> diffScores;
    public List<Score> passScores;
    public List<Score> marks; // usual marks. tasks, tests, colloqiums, practice
    public List<List<Score>> allScores;
    int numExams;
    int numDiffs;
    int numPasses;
    int numMarks;

    /**
     * constructor for semester grades book.
     *
     * @param semester current semester of education
     */
    public SemesterMarks(int semester, int numExams, int numDiffs, int numPasses, int numMarks) {
        this.semester = semester;
        this.numExams = numExams;
        this.numDiffs = numDiffs;
        this.numPasses = numPasses;
        this.numMarks = numMarks;
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
