package ru.nsu.kolodina.ooptasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DriverTool {

    List<Group> groupList = new ArrayList<>();
    List<Task> tasksList = new ArrayList<>();
    @Getter
    List<Assignment> assignmentList = new ArrayList<>();
    public Git git = new Git();
    public Map<String, String> pathToClasses = new HashMap<>();
    public List<CheckPoint> checkPointsList = new ArrayList<>();

    public List<Assignment> extractData(String path) {
        DSLParser dslParser = new DSLParser();
        dslParser.extractData(path, groupList, tasksList, assignmentList, pathToClasses, checkPointsList);
        dslParser.matchStudentsAndTasks(groupList, tasksList, assignmentList);
        return assignmentList;
    }

    public String getStudentRepo(Assignment assignment) {
        String githubLink = assignment.student.githubLink;
        String repository = git.extractRepoName(githubLink);
        git.runGitClone(githubLink);
        git.runGitCheckout(repository, "main");
        return repository;
    }

    public boolean runBuildChecks(String toolName, String repo, Task task) {
        Build tool = null;
        try {
            tool = Utils.loadClassInstance(pathToClasses.get(toolName), Build.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        File srcFolder = new File(repo, task.id);
        if (!srcFolder.exists()) {
            task.buildOk = false;
            task.mark = 0.0;
            return false;
        }
        boolean everythingOk = true;
        int err = 0;
        err = tool.compile(repo, task.id);
        if (err != 0) {
            System.err.println("Compilation failed!");
            everythingOk = false;
        }
        err = tool.docGen(repo, task.id);
        if (err != 0) {
            System.err.println("Documentation generation failed!");
            everythingOk = false;
        }
        err = tool.checkstyle(repo, task.id);
        if (err != 0) {
            System.err.println("Checkstyle failed!");
            everythingOk = false;
        }
        err = tool.test(repo, task.id);
        if (err != 0) {
            System.err.println("Tests failed!");
            everythingOk = false;
        }
        if (everythingOk) {
            task.buildOk = true;
        }
        return everythingOk;
    }

    public void checkStudent(Assignment assignment, CheckPoint checkPoint) {
        Criteries criteriaCheck = null;
        try {
            criteriaCheck = Utils.loadClassInstance(pathToClasses.get("criteries"), Criteries.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Grading gradingCheck = null;
        try {
            gradingCheck = Utils.loadClassInstance(pathToClasses.get("grading"), Grading.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String repository = getStudentRepo(assignment);
        Group.Student student = assignment.student;
        String toolName = student.buildTool;
        for (Task t : assignment.tasks) {
            runBuildChecks(toolName, repository, t);
            criteriaCheck.meetsCriteria(repository, t);
            gradingCheck.calculateScore(student, repository, t);
            Double score = t.getMark();
            student.addScore(score);
        }
        gradingCheck.calculateCheckPoint(student, checkPoint);
        Utils.deleteDirectory(new File(repository));
    }

    public Assignment getAssignment(String studentName) {
        for (Assignment a : assignmentList) {
            if (a.studentName.equals(studentName)) {
                return a;
            }
        }
        return null;
    }

    public CheckPoint getCheckPoint(String checkPointName) {
        for (CheckPoint c: checkPointsList) {
            if (c.name.equals(checkPointName)) {
                return c;
            }
        }
        return null;
    }
}
