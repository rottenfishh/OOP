package ru.nsu.kolodina.ooptasks;

import ru.nsu.kolodina.ooptasks.shit.Conditions;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DSLParser dslParser = new DSLParser();
        String link = "https://github.com/rottenfishh/OOP.git";
        String repoName = new Git().extractRepoName(link);
        String taskName = "Task_1_4_1";
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String path = "src/main/DSL/course.dsl";
        List<Group> groupList = new ArrayList<>();
        List<Task> tasksList = new ArrayList<>();
        List<Assignment> assignmentList = new ArrayList<>();
        BuildTool.buildToolCommands buildToolCommands = new BuildTool.buildToolCommands();
        dslParser.extractData(path, groupList, tasksList, assignmentList, buildToolCommands);
        dslParser.matchStudentsAndTasks(groupList, tasksList, assignmentList);
        System.out.println(assignmentList.get(0).studentObj.name);
        System.out.println(buildToolCommands.buildToolName);
        BuildTool check = new BuildTool(buildToolCommands);
        check.runTests(repoName, taskName);
    }
}
