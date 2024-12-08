package ru.nsu.kolodina.recordbook;

/**
 * class implementing logic of scores.
 */
public class Score {
    String subject;
    public final Double score;
    public final Type type;
    public final Name name;
    public int semester;

    /**
     * constructor for scores.
     *
     * @param score double value of score
     * @param type type of score
     * @param name name of score
     */
    public Score(Double score, Type type, Name name, String subject, int semester) {
        this.score = score;
        this.type = type;
        this.name = name;
        this.subject = subject;
        this.semester = semester;
    }

    /**
     * method for getScore to use in streams.
     *
     * @return double value of score
     */
    public double getScore() {
        return score;
    }

    /**
     * enum for type of mark.
     * FINALS: exams and diffs
     * MARKS: all other marks
     * DIPLOMA: diploma
     */
    public enum Type {
        FINALS,
        MARKS,
        DIPLOMA

    }

    /**
     * enum for names of marks.
     * all names possible
     */
    public enum Name {
        TASK,
        TEST,
        COLLOQ,
        EXAM,
        DIFF_PASS,
        PASS,
        PRACTICE,
        DIPLOMA
    }
}