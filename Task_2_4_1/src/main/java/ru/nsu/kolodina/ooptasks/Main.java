package ru.nsu.kolodina.ooptasks;

import ru.nsu.kolodina.ooptasks.shit.Conditions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Git git = new Git();
        Conditions conditions = new Conditions();
        //BuildTool check = new BuildTool();
        String link = "https://github.com/rottenfishh/OOP.git";
        //git.runGitClone(link);
        String repoName = new Git().extractRepoName(link);
        String taskName = "Task_1_4_1";
        git.getLastCommitDate(repoName, taskName);
        List<String> cmd = new ArrayList<String>();
        //check.runTests(repoName, taskName);
    }
}
