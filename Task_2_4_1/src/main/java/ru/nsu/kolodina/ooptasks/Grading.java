package ru.nsu.kolodina.ooptasks;

public interface Grading {
    public double calculateScore(Group.Student student, String repo, Task task);
    public int calculateFinalMark(Group.Student student);
    public int calculateCheckPoint(Group.Student student, int requiredScore);
}
