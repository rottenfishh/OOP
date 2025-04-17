package ru.nsu.kolodina.ooptasks;

import org.antlr.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Git git = new Git();
        Conditions conditions = new Conditions();
        RunChecks check = new RunChecks(conditions);
        String link = "https://github.com/rottenfishh/OOP.git";
        String repoName = new Git().extractRepoName(link);
        String taskName = "Task_1_4_1";
        git.getLastCommitDate(repoName, taskName);
        List<String> cmd = new ArrayList<String>();
        cmd.add("gradlew.bat");
        cmd.add("test");
        check.runCommand(repoName, taskName, cmd);
    }
}
