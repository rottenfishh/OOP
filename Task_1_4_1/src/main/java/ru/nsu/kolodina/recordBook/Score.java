package ru.nsu.kolodina.recordBook;

public class Score {
    public final Double score;
    public final Type type;
    public final Name name;
    public Score(Double score, Type type, Name name) {
        this.score = score;
        this.type = type;
        this.name = name;
    }

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