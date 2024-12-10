package ru.nsu.kolodina.recordbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * main class that contains implementation of student's record book.
 */
public class RecordBook implements java.io.Serializable {
    public List<SemesterMarks> gradeBook;
    public int currSemester;
    String name;
    boolean graduated = false;
    double avgScore;
    Score diploma = null;
    Basis basis;

    /**
     * constructor for record book.
     *
     * @param base          - paid or free
     * @param currSemester  - current semester
     * @param graduated     - did student graduate
     * @param numberOfMarks - list of arrays containing number of marks for
     *                      each semester in following structure:
     *                      semester, numExams, numDiffs, numPasses, numMarks.
     */
    public RecordBook(Basis base, int currSemester, boolean graduated, List<int[]> numberOfMarks) {
        this.currSemester = currSemester;
        gradeBook = new ArrayList<>(9);
        List<SemesterMarks> semesters = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            SemesterMarks semester = new SemesterMarks(numberOfMarks.get(i)[0],
                    numberOfMarks.get(i)[1], numberOfMarks.get(i)[2],
                    numberOfMarks.get(i)[3], numberOfMarks.get(i)[4]);
            semesters.add(semester);
        }
        gradeBook.addAll(semesters);
        this.basis = base;
        this.graduated = graduated;
    }

    /**
     * setting student name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * adding mark to the record book.
     *
     * @param semester to add mark to
     * @param type     type of mark
     * @param name     name of mark
     * @param score    double value of mark
     */
    public void addMark(int semester, Score.Type type, Score.Name name,
                        double score, String subject, int sem) {
        Score scr = new Score(score, type, name, subject, sem);
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

    /**
     * get all final scores using streams.
     *
     * @return stream of scores
     */
    public Stream<Score> getFinalScores() {
        Stream<Score> marks = gradeBook.stream()
                .filter(semestermarks -> semestermarks.semester < currSemester)
                .flatMap(semestermarks -> semestermarks.finalScores.stream()).flatMap(List::stream);
        return marks;
    }

    /**
     * get all scores using streams.
     *
     * @return stream of scores
     */
    public Stream<Score> getScores() {
        Stream<Score> marks = gradeBook.stream()
                .filter(semestermarks -> semestermarks.semester < currSemester)
                .flatMap(semestermarks -> semestermarks.allScores.stream()).flatMap(List::stream);
        return marks;
    }

    /**
     * get average score of student.
     *
     * @return double value - average score
     */
    public double getAvgScore() {
        Stream<Score> marks = getFinalScores();
        Stream<Score> stream = marks.filter(element -> element.score != 0);
        long num = stream.count();
        double avgScore = 0.0;
        double sum = getFinalScores().filter(element -> element.score != 0)
                .mapToDouble(Score::getScore).sum();
        if (num != 0) {
            avgScore = sum / num;
        }
        avgScore = Math.floor(avgScore * 100) / 100;
        return avgScore;
    }

    /**
     * check if student can transfer to free basis.
     * analyze student's marks for last 2 sessions using streams
     * if student is already on free, return false
     *
     * @return true or false
     */
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
        boolean hasThrees = marks.stream().filter(element -> element.score != 0)
                .anyMatch(element -> element.score == 3 || element.score == 2);
        return !hasThrees;
    }

    /**
     * check if student can get red diploma.
     * analyze student session scores and diploma score if it exists
     *
     * @return true or false
     */
    public boolean getRedDiploma() {
        Stream<Score> stream = getFinalScores().filter(element -> element.score != 0);
        Stream<Score> stream2 = getFinalScores().filter(element -> element.score == 5);
        boolean hasThrees = getFinalScores().anyMatch(element -> element.score == 3);
        double diplomaScore = 0.0;
        long fivesCount = stream2.count();
        long allMarksCount = stream.count();
        if (diploma != null) {
            diplomaScore = diploma.score;
        }
        if (graduated) {
            return (fivesCount >= allMarksCount * 0.75) && !hasThrees
                    && ((diplomaScore == 5) || (diplomaScore == 0.0));
        }
        return ((allMarksCount - fivesCount) < gradeBook.get(0).numExams * 0.25) && !hasThrees;
    }

    /**
     * check if student can get increased scholarship.
     * if student is on paid basis, return false
     * analyze scores of the last session
     *
     * @return true or false
     */
    public boolean getIncreasedMoney() {
        if (basis == Basis.PAID) {
            System.out.println("You are on paid basis. No money for you:).");
            return false;
        }
        List<Score> marks = new ArrayList<>();
        for (int i = currSemester - 2; i < currSemester; i++) {
            marks.addAll(gradeBook.get(i).examScores);
        }
        Stream<Score> stream = marks.stream().filter(element -> element.score != 0);
        return stream.noneMatch(element -> element.score != 5);
    }

    /**
     * extracts marks as list of double values from stream of scores.
     *
     * @param stream stream of scores
     * @return list of marks as double values
     */
    public List<String> marksList(Stream<Score> stream) {
        List<String> marks = new ArrayList<>();
        stream.forEach(element -> marks.add(element.subject + ": " + element.getScore()));
        return marks;
    }

    /**
     * build a string containing information of record book.
     *
     * @return string
     */
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grade book of student " + name + "\n");
        sb.append("Education basis: " + basis + "\n");
        int course = (int) Math.ceil((double) currSemester / 2);
        sb.append("Course: " + course + "\n");
        sb.append(String.format("                     Average grade book score: %.2f\n\n",
                getAvgScore()));
        for (int i = 1; i <= currSemester; i++) {
            sb.append("Semester " + i + "\nExams marks: "
                    + marksList(gradeBook.get(i).examScores.stream()) + "\n");
            sb.append("Differential pass marks: "
                    + marksList(gradeBook.get(i).diffScores.stream()) + "\n");
            sb.append("Passes: "
                    + marksList(gradeBook.get(i).passScores.stream()) + "\n");
            sb.append("Marks: "
                    + marksList(gradeBook.get(i).marks.stream()) + "\n");
        }
        return sb.toString();
    }

    /**
     * method to write recordBook to file(serialization).
     *
     * @param path to create file in
     * @throws IOException throw exception
     */
    public void writeToFile(String path) throws IOException {
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        String yesOrNo = "";
        if (graduated) {
            yesOrNo = "yes";
        } else {
            yesOrNo = "no";
        }
        sb.append(basis.toString()).append(";").append(currSemester)
                .append(";").append(yesOrNo).append(";").append(name).append('\n');
        Stream<Score> stream = getScores();
        for (Score s : stream.toList()) {
            sb.append(s.semester).append(";").append(s.type)
                    .append(";").append(s.name).append(";")
                    .append(s.getScore()).append(";").append(s.subject).append('\n');
        }
        FileWriter writer = new FileWriter(path);
        writer.write(sb.toString());
        writer.close();
    }

    /**
     * enum for basis of education.
     */
    public enum Basis {
        FREE,
        PAID
    }

}
