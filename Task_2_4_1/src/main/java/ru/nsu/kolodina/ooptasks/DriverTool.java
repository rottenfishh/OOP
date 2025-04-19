package ru.nsu.kolodina.ooptasks;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.*;

@RequiredArgsConstructor
public class DriverTool {

    List<Group> groupList = new ArrayList<>();
    List<Task> tasksList = new ArrayList<>();
    List<Assignment> assignmentList = new ArrayList<>();
    public Git git = new Git();
    public Map<String, String> pathToClasses = new HashMap<>();

    public void setUpClasses() {

    }
    public List<Assignment> extractData(String path) {
        DSLParser dslParser = new DSLParser();
        dslParser.extractData(path, groupList, tasksList, assignmentList, pathToClasses);
        dslParser.matchStudentsAndTasks(groupList, tasksList, assignmentList);
        System.out.println(pathToClasses.get("gradlew.bat"));
        return assignmentList;
    }

    public String getStudentRepo(Assignment assignment) {
        String githubLink = assignment.studentObj.githubLink;
        String repository = git.extractRepoName(githubLink);
        git.runGitClone(githubLink);
        git.runGitCheckout(repository, "main");
        return repository;
    }

    public boolean runBuildChecks(String toolName, String repo, Task task) {

        /*Class<?> clazz = null;
        Build tool = null;
        try {
            clazz = Class.forName(pathToClasses.get(toolName));
            if (!Build.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException("Class " + toolName + " does not implement BuildTool");
            }
            tool = (Build) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }*/
        Build tool = null;
        try {
            tool = Utils.loadClassInstance(pathToClasses.get(toolName), Build.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        boolean everythingOk = true;
        int err = 0;
        System.out.println(task.id);
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

    public void checkStudent(Assignment assignment) {
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
        /*BuildInvoker criteriaCheck = new BuildInvoker();
        criteriaCheck.getBuildClass(pathToClasses.get("criteries"));

        BuildInvoker gradingCheck = new BuildInvoker();
        gradingCheck.getBuildClass(pathToClasses.get("grading"));*/

        String repository = getStudentRepo(assignment);
        Group.Student student = assignment.studentObj;
        String toolName = student.buildTool;
        for (Task t : assignment.taskObj) {
            runBuildChecks(toolName, repository, t);
            criteriaCheck.meetsCriteria(repository, t);
            gradingCheck.calculateScore(student, repository, t);
            Double score = t.getMark();
            System.out.println(score);
            student.addScore(score);
        }
        gradingCheck.calculateFinalMark(student);
        System.out.println(student.mark);
        Utils.deleteDirectory(new File(repository));
    }

    public void checkAll() {
        for (Assignment assignment : assignmentList) {
            checkStudent(assignment);
        }
    }
}
