package ru.nsu.kolodina.recordbook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RecordBook {
    public List<SemesterMarks> gradeBook;
    public int currSemester;
    String name;
    String lastName;
    boolean graduated = false;
    double avgScore;
    Score diploma = null;
    Basis basis;

    public RecordBook(Basis base, int currSemester, boolean graduated) {
        this.currSemester = currSemester;
        gradeBook = new ArrayList<>(9);
        for (int i = 0; i <= currSemester; i++) {
            SemesterMarks semester = new SemesterMarks(i);
            gradeBook.add(semester);
        }
        this.basis = base;
        this.graduated = graduated;
    }

    public void setName(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void addMark(int semester, Score.Type type, Score.Name name, double score) {
        Score scr = new Score(score, type, name);
        switch (type) {
            case FINALS:
                switch (name) {
                    case EXAM -> gradeBook.get(semester).examScores.add(scr);
                    case DIFF_PASS -> gradeBook.get(semester).diffScores.add(scr);
                    case PASS -> gradeBook.get(semester).passScores.add(scr);
                    default -> System.err.println("Wrong name of mark!");
                }
                break;
            case MARKS:
                switch (name) {
                    case PASS -> gradeBook.get(semester).passScores.add(scr);
                    default -> gradeBook.get(semester).marks.add(scr);
                }
                break;
            case DIPLOMA:
                this.diploma = scr;
                break;
            default:
                System.err.println("Invalid type name!");
        }
    }

    public Stream<Score> getFinalScores() {
        Stream<Score> marks = gradeBook.stream().filter(SemesterMarks -> SemesterMarks.semester < currSemester).
                flatMap(SemesterMarks -> SemesterMarks.finalScores.stream()).flatMap(List::stream);
        return marks;
    }

    public Stream<Score> getScores() {
        Stream<Score> marks = gradeBook.stream().filter(SemesterMarks -> SemesterMarks.semester < currSemester).
                flatMap(SemesterMarks -> SemesterMarks.allScores.stream()).flatMap(List::stream);
        return marks;
    }

    public double getAvgScore() {
        Stream<Score> marks = getFinalScores();
        Stream<Score> stream = marks.filter(element -> element.score != 0);
        long num = stream.count();
        double avgScore = 0.0;
        double sum = getFinalScores().filter(element -> element.score != 0).mapToDouble(Score::getScore).sum();
        if (num != 0) {
            avgScore = sum / num;
        }
        avgScore = Math.floor(avgScore * 100) / 100;
        return avgScore;
    }

    public boolean transferToFreeBasis() {
        if (this.basis == Basis.FREE) {
            System.out.println("Already on free basis!");
            return false;
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

    public boolean getRedDiploma() {
        Stream<Score> stream = getFinalScores().filter(element -> element.score != 0);
        Stream<Score> stream2 = getFinalScores().filter(element -> element.score == 5);
        boolean hasThrees = getFinalScores().anyMatch(element -> element.score == 3);
        double diplomaScore = 0.0;
        if (diploma != null) {
            diplomaScore = diploma.score;
        }
        return (stream2.count() >= stream.count() * 0.75) && !hasThrees && ((diplomaScore == 5) || (diplomaScore == 0.0));
    }

    public boolean getIncreasedMoney() {
        if (basis == Basis.PAID) {
            System.out.println("You are on paid basis. No money for you:).");
            return false;
        }
        Stream<Score> stream = getFinalScores().filter(element -> element.score != 0);
        return stream.noneMatch(element -> element.score != 5);
    }

    public List<Double> numberMarks(Stream<Score> list) {
        List<Double> marks = new ArrayList<>();
        list.forEach(element -> marks.add(element.getScore()));
        return marks;
    }

    public void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grade book of student " + name + " " + lastName + "\n");
        sb.append("Education basis: " + basis + "\n");
        int course = (int) Math.ceil((double) currSemester / 2);
        sb.append("Course: " + course + "\n");
        sb.append(String.format("                     Average grade book score: %.2f\n\n", getAvgScore()));
        for (int i = 1; i < currSemester; i++) {
            sb.append("Semester " + i + "\nExams marks: " + numberMarks(gradeBook.get(i).examScores.stream()) + "\n");
            sb.append("Differential pass marks: " + numberMarks(gradeBook.get(i).diffScores.stream()) + "\n");
            sb.append("Passes: " + numberMarks(gradeBook.get(i).passScores.stream()) + "\n");
            sb.append("Marks: " + numberMarks(gradeBook.get(i).marks.stream()) + "\n");
        }
        System.out.println(sb);
    }

    public enum Basis {
        FREE,
        PAID
    }
}
