package ru.nsu.kolodina.recordbook;

/**
 * class implementing logic of scores.
 */
public class Score {
    public final Double score;
    public final Type type;
    public final Name name;

    /**
     * constructor for scores.
     *
     * @param score double value of score
     * @param type type of score
     * @param name name of score
     */
    public Score(Double score, Type type, Name name) {
        this.score = score;
        this.type = type;
        this.name = name;
    }

    /**
     * method for getScore to use in streams.
     *
     * @return double value of score
     */
    public double getScore() {
        return score;
    }

    public enum Type {
        FINALS,
        MARKS,
        DIPLOMA

    }

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