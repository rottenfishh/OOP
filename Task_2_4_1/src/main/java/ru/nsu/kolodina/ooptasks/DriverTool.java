package ru.nsu.kolodina.ooptasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DriverTool is the main controller class responsible for parsing DSL files,
 * managing student repositories, running criteria and grading checks,
 * and generating HTML reports.
 */
public class DriverTool {
    public Git git = new Git();
    public Map<String, String> pathToClasses = new HashMap<>();
    public List<CheckPoint> checkPointsList = new ArrayList<>();
    String cwd = System.getProperty("user.dir");
    List<Group> groupList = new ArrayList<>();
    List<Task> tasksList = new ArrayList<>();
    @Getter
    List<Assignment> assignmentList = new ArrayList<>();

    /**
     * Constructs a DriverTool and extracts data from the given DSL file.
     *
     * @param path path to the DSL file
     */
    public DriverTool(String path) {
        extractData(path);
    }

    /**
     * Extracts data from the DSL file and matches students with tasks.
     *
     * @param path path to the DSL file
     * @return list of assignments
     */
    public List<Assignment> extractData(String path) {
        System.out.println(path);
        DSLParser dslParser = new DSLParser();
        dslParser.extractData(path, groupList, tasksList, assignmentList, pathToClasses, checkPointsList);
        dslParser.matchStudentsAndTasks(groupList, tasksList, assignmentList);
        return assignmentList;
    }

    /**
     * Clones or pulls the student's repository and returns the local path.
     *
     * @param assignment the assignment of the student
     * @return local path to the student's repository
     */
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

    /**
     * Runs private checks on a student's repository against a checkpoint.
     *
     * @param assignment the assignment to check
     * @param checkPoint the checkpoint to evaluate
     * @return the student's total score
     */
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
        }
        gradingCheck.calculateCheckPoint(student, checkPoint);
        git.gitClean(repository);
        return student.getScore();
    }

    /**
     * Retrieves an assignment by student name.
     *
     * @param studentName the name of the student
     * @return the matching Assignment or null if not found
     */
    private Assignment getAssignment(String studentName) {
        for (Assignment a : assignmentList) {
            if (a.studentName.equals(studentName)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Retrieves a checkpoint by name.
     *
     * @param checkPointName the name of the checkpoint
     * @return the matching CheckPoint or null if not found
     */
    private CheckPoint getCheckPoint(String checkPointName) {
        for (CheckPoint c : checkPointsList) {
            if (c.name.equals(checkPointName)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Runs checks on a single student and generates an HTML report.
     *
     * @param studentName  the name of the student
     * @param checkPointName the name of the checkpoint
     * @param htmlPath     the path to save the HTML report
     * @return the student's final mark
     */
    public int checkStudent(String studentName, String checkPointName, String htmlPath) {
        Assignment assignment = getAssignment(studentName);
        CheckPoint checkPoint = getCheckPoint(checkPointName);
        checkStudentPrivate(assignment, checkPoint);
        List<Assignment> assignmentsTemp = new ArrayList<>();
        assignmentsTemp.add(assignment);
        HTMLGeneration.generateHTML(assignmentsTemp, htmlPath);
        return assignment.student.mark;
    }

    /**
     * Runs checks on all students and generates a consolidated HTML report.
     *
     * @param checkpointName the name of the checkpoint
     * @param htmlPath       the path to save the HTML report
     */
    public void runAllChecks(String checkpointName, String htmlPath) {
        CheckPoint checkPoint = getCheckPoint(checkpointName);
        for (Assignment assignment : assignmentList) {
            checkStudentPrivate(assignment, checkPoint);
        }
        HTMLGeneration.generateHTML(assignmentList, htmlPath);
    }
}
