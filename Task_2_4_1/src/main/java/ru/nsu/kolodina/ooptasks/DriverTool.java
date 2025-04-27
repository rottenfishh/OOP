package ru.nsu.kolodina.ooptasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverTool {
    String cwd = System.getProperty("user.dir");

    List<Group> groupList = new ArrayList<>();
    List<Task> tasksList = new ArrayList<>();
    @Getter
    List<Assignment> assignmentList = new ArrayList<>();
    public Git git = new Git();
    public Map<String, String> pathToClasses = new HashMap<>();
    public List<CheckPoint> checkPointsList = new ArrayList<>();

    public DriverTool(String path) {
        extractData(path);
    }

    public List<Assignment> extractData(String path) {
        System.out.println(path);
        DSLParser dslParser = new DSLParser();
        dslParser.extractData(path, groupList, tasksList, assignmentList, pathToClasses, checkPointsList);
        dslParser.matchStudentsAndTasks(groupList, tasksList, assignmentList);
        return assignmentList;
    }

    public String getStudentRepo(Assignment assignment) {
        String githubLink = assignment.student.githubLink;
        String repository = git.extractRepoName(githubLink);
        String studentPath = cwd + File.separator + assignment.studentName;
        String fullPath = studentPath + File.separator + repository;
        if (Files.exists(new File(fullPath).toPath())) {
            git.gitPull(fullPath, "main");
        } else {
            git.runGitClone(studentPath, githubLink);
        }
        git.runGitCheckout(fullPath, "main");
        return fullPath;
    }

    private double checkStudentPrivate(Assignment assignment, CheckPoint checkPoint) {
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
            criteriaCheck.meetsCriteria(pathToClasses.get(toolName), repository, t);
            gradingCheck.calculateScore(student, repository, t);
            double score = t.getMark();
            student.addScore(score);
            System.out.println(t.conditions.get("Compilation"));
        }
        gradingCheck.calculateCheckPoint(student, checkPoint);
        git.gitClean(repository);
        return student.getScore();
    }

    private Assignment getAssignment(String studentName) {
        for (Assignment a : assignmentList) {
            if (a.studentName.equals(studentName)) {
                return a;
            }
        }
        return null;
    }

    private CheckPoint getCheckPoint(String checkPointName) {
        for (CheckPoint c: checkPointsList) {
            if (c.name.equals(checkPointName)) {
                return c;
            }
        }
        return null;
    }
    public int checkStudent(String studentName, String checkPointName, String htmlPath) {
        Assignment assignment = getAssignment(studentName);
        CheckPoint checkPoint = getCheckPoint(checkPointName);
        checkStudentPrivate(assignment, checkPoint);
        List<Assignment> assignmentsTemp = new ArrayList<>();
        assignmentsTemp.add(assignment);
        HTMLGeneration.generateHTML(assignmentsTemp, htmlPath);
        return assignment.student.mark;
    }
    public void runAllChecks(String checkpointName, String htmlPath) {
        CheckPoint checkPoint = getCheckPoint(checkpointName);
        for (Assignment assignment : assignmentList) {
            checkStudentPrivate(assignment, checkPoint);
        }
        HTMLGeneration.generateHTML(assignmentList, htmlPath);
    }
}
//git log git fetch