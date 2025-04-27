package ru.nsu.kolodina.ooptasks;

import java.util.List;

public interface Build {
    public int compile(String repoDir, String task);
    public int test(String repoDir, String task, List<Integer> tests);
    public int docGen(String repoDir, String task);
    public int checkstyle(String repoDir, String task);
}
