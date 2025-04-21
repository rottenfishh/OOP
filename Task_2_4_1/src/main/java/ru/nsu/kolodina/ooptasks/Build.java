package ru.nsu.kolodina.ooptasks;

public interface Build {
    public int compile(String repoDir, String task);
    public int test(String repoDir, String task);
    public int docGen(String repoDir, String task);
    public int checkstyle(String repoDir, String task);
}
