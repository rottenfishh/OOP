package ru.nsu.kolodina.recordBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RecordBook {
    public List<semesterMarks> gradeBook;
    int currSemester;
    boolean graduated = false;
    double avgScore;
    Score diploma = null;
    Basis basis;
    public RecordBook(Basis base, int currSemester, boolean graduated) {
        this.currSemester = currSemester;
        gradeBook = new ArrayList<>(9);
        for (int i = 1; i <= currSemester; i++) {
            semesterMarks semester = new semesterMarks(i);
            gradeBook.add(semester);
        }
        this.basis = base;
        this.graduated = graduated;
    }

    public void addMark(int semester, Score.Type type, Score.Name name, double score) {
        Score scr = new Score(score, type, name);
        switch(type) {
            case FINALS:
                switch (name) {
                    case EXAM -> gradeBook.get(semester).examScores.add(scr);
                    case DIFF_PASS -> gradeBook.get(semester).diffScores.add(scr);
                    case PASS -> gradeBook.get(semester).passScores.add(scr);
                }
                break;
            case MARKS:
                gradeBook.get(semester).marks.add(scr);
                break;
            case DIPLOMA:
                this.diploma = scr;
                break;
        }
    }
    public List<Score> getFinalScores() {
        List<Score> marks = new ArrayList<>();
        for (int i = 1; i < currSemester; i++) {
            semesterMarks sms = gradeBook.get(i);
            for (List<Score> ms : sms.finalScores) {
                marks.addAll(ms);
            }
        }
        return marks;
    }
    double getAvgScore() {
        List<Score> marks = getFinalScores();
        Stream<Score> stream = marks.stream().filter(element -> element.score != 0);
        long num = stream.count();
        Stream<Score> streamSum = marks.stream();
        double sum = streamSum.filter(element -> element.score != 0).mapToDouble(Score::getScore).sum();
        if (num == 0) {
            return 0;
        } else {
            return sum/num;
        }
    }

    boolean transferToFreeBasis() {
        if (this.basis == Basis.FREE) {
            return true;
        }
        if (currSemester < 3) {
            return false;
        }
        List<Score> marks = new ArrayList<>();
        for (int i = currSemester - 2; i < currSemester; i++) {
            marks.addAll(gradeBook.get(i).examScores);
        }
        boolean hasThrees = marks.stream().filter(element -> element.score != 0).anyMatch(element -> element.score == 3 || element.score == 2);
        return !hasThrees;
    }

    boolean getRedDiploma() {
        List<Score> marks = getFinalScores();
        Stream<Score> stream = marks.stream().filter(element -> element.score != 0);
        Stream<Score> stream2 = marks.stream().filter(element -> element.score == 5);
        boolean hasThrees = marks.stream().anyMatch(element -> element.score == 3);
        double diplomaScore = 0.0;
        if (diploma != null) {
            diplomaScore = diploma.score;
        }
        return (stream2.count() >= stream.count() * 0.75) && !hasThrees && ((diplomaScore == 5) || (diplomaScore == 0.0));
    }
    boolean getIncreasedMoney() {
        List<Score> marks = new ArrayList<>();
        for (int i = 1; i < currSemester; i++) {
            semesterMarks sms = gradeBook.get(i);
            for (List<Score> ms : sms.finalScores) {
                marks.addAll(ms);
            }
        }
        Stream<Score> stream = marks.stream().filter(element -> element.score != 0);
        return stream.noneMatch(element -> element.score != 5);
    }

    public enum Basis {
        FREE,
        PAID;
    }
}
