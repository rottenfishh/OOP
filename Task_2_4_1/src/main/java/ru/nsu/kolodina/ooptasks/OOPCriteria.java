package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Implements the criteria for evaluating tasks in OOP assignments.
 * This class checks build, documentation, checkstyle, and tests as well as
 * evaluating if deadlines are met.
 */
public class OOPCriteria implements Criteries {

    /**
     * Converts a Git date string into a more readable date format.
     *
     * @param gitDate the date in Git's format.
     * @return the formatted date as a String in "dd.MM.yyyy" format.
     */
    public static String convertDate(String gitDate) {
        DateTimeFormatter gitFormat = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss yyyy Z", Locale.ENGLISH);
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ZonedDateTime dateTime = ZonedDateTime.parse(gitDate, gitFormat);
        return dateTime.format(desiredFormat);
    }

    /**
     * Evaluates the criteria for a given task by running build, documentation,
     * checkstyle, and test checks.
     *
     * @param toolPath the path to the build tool.
     * @param repo     the repository path.
     * @param task     the task to evaluate.
     * @return a map containing the status of each evaluation criterion.
     */
    public Map<String, String> meetsCriteria(String tool, String repo, Task task) {
        return runBuildChecks(tool, repo, task);
    }

    /**
     * Runs checks for building, generating documentation, checkstyle, and tests for a task.
     *
     * @param toolPath the path to the build tool.
     * @param repo     the repository path.
     * @param task     the task to evaluate.
     * @return a map containing the status of each evaluation criterion.
     */
    public Map<String, String> runBuildChecks(String toolPath, String repo, Task task) {
        Map<String, String> criteries = new HashMap<>();
        Build tool;
        try {
            tool = Utils.loadClassInstance(toolPath, Build.class);
        } catch (Exception e) {
            throw new RuntimeException("Error loading build tool: " + e.getMessage(), e);
        }

        // Ensure source folder for the task exists
        File srcFolder = new File(repo, task.id);
        if (!srcFolder.exists()) {
            task.buildOk = false;
            task.mark = 0.0;
            return null;
        }

        boolean everythingOk = true;
        int err;

        // Build check
        err = tool.compile(repo, task.id);
        if (err != 0) {
            criteries.put("Build", "-");
            System.err.println("Compilation failed!");
            everythingOk = false;
        } else {
            criteries.put("Build", "+");
        }

        // Documentation generation check
        err = tool.docGen(repo, task.id);
        if (err != 0) {
            criteries.put("Docs", "-");
            System.err.println("Documentation generation failed!");
            everythingOk = false;
        } else {
            criteries.put("Docs", "+");
        }

        // Checkstyle check
        err = tool.checkstyle(repo, task.id);
        if (err != 0) {
            criteries.put("Checkstyle", "-");
            System.err.println("Checkstyle failed!");
            everythingOk = false;
        } else {
            criteries.put("Checkstyle", "+");
        }

        // Test check
        List<Integer> tests = new ArrayList<>();
        err = tool.test(repo, task.id, tests);
        String testsStr = String.format("%d/%d/%d", tests.get(0), tests.get(1), tests.get(2));
        if (err != 0) {
            criteries.put("Test", testsStr);
            System.err.println("Tests failed!");
            everythingOk = false;
        } else {
            criteries.put("Test", testsStr);
        }

        if (everythingOk) {
            task.buildOk = true;
        }
        task.setConditions(criteries);
        return criteries;
    }

    /**
     * Checks if the task's soft deadline has been met based on the first commit date.
     *
     * @param repo the repository path.
     * @param task the task to check.
     * @return true if the task meets the soft deadline, otherwise false.
     */
    public boolean softDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<String> res = new ArrayList<>();
        git.getFirstCommitDate(repo, task.id, res);
        String dateFirstCommit = convertDate(res.get(0));
        LocalDate dateFirst = LocalDate.parse(dateFirstCommit, formatter);
        LocalDate softDeadline = LocalDate.parse(task.softDeadline, formatter);
        return !dateFirst.isAfter(softDeadline);
    }

    /**
     * Checks if the task's hard deadline has been met based on the last commit date.
     *
     * @param repo the repository path.
     * @param task the task to check.
     * @return true if the task meets the hard deadline, otherwise false.
     */
    public boolean hardDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        List<String> res = new ArrayList<>();
        git.getLastCommitDate(repo, task.id, res);
        String dateLastCommit = convertDate(res.get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateLast = LocalDate.parse(dateLastCommit, formatter);
        LocalDate hardHeadline = LocalDate.parse(task.hardDeadline, formatter);
        return !dateLast.isAfter(hardHeadline);
    }
}
