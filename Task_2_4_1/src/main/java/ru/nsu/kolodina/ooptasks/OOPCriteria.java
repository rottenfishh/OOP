package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

import java.io.File;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OOPCriteria implements Criteries {

    public Map<String, Boolean> meetsCriteria(String tool, String repo, Task task) {
        return runBuildChecks(tool, repo, task);
    }

    public Map<String, Boolean> runBuildChecks(String toolPath, String repo, Task task) {
        Map<String, Boolean> criteries = new HashMap<>();
        Build tool = null;
        try {
            tool = Utils.loadClassInstance(toolPath, Build.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        File srcFolder = new File(repo, task.id);
        if (!srcFolder.exists()) {
            task.buildOk = false;
            task.mark = 0.0;
            return null;
        }
        boolean everythingOk = true;
        int err = 0;
        err = tool.compile(repo, task.id);
        if (err != 0) {
            criteries.put("Compilation", false);
            System.err.println("Compilation failed!");
            everythingOk = false;
        } else {
            criteries.put("Compilation", true);
        }
        err = tool.docGen(repo, task.id);
        if (err != 0) {
            criteries.put("Documentation", false);
            System.err.println("Documentation generation failed!");
            everythingOk = false;
        } else {
            criteries.put("Documentation", true);
        }
        err = tool.checkstyle(repo, task.id);
        if (err != 0) {
            criteries.put("Checkstyle", false);
            System.err.println("Checkstyle failed!");
            everythingOk = false;
        } else {
            criteries.put("Checkstyle", true);
        }
        err = tool.test(repo, task.id);
        if (err != 0) {
            criteries.put("Tests", false);
            System.err.println("Tests failed!");
            everythingOk = false;
        } else {
            criteries.put("Tests", true);
        }
        if (everythingOk) {
            task.buildOk = true;
        }
        task.setConditions(criteries);
        return criteries;
    }

    public static String convertDate(String gitDate) {
        DateTimeFormatter gitFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy Z", Locale.ENGLISH);
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ZonedDateTime dateTime = ZonedDateTime.parse(gitDate, gitFormat);
        String formatted = dateTime.format(desiredFormat);
        return formatted;
    }

    public boolean softDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        List<String> res = new ArrayList<>();
        git.getFirstCommitDate(repo, task.id, res);
        String dateFirstCommit = convertDate(res.get(0));
        LocalDate dateFirst = LocalDate.parse(dateFirstCommit, formatter);
        LocalDate softDeadline = LocalDate.parse(task.softDeadline, formatter);
        return dateFirst.isBefore(softDeadline) || dateFirst.isEqual(softDeadline);
    }
    public boolean hardDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        List<String> res = new ArrayList<>();
        git.getLastCommitDate(repo, task.id, res);
        String dateLastCommit = convertDate(res.get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateLast = LocalDate.parse(dateLastCommit, formatter);
        LocalDate hardHeadline = LocalDate.parse(task.hardDeadline, formatter);
        return dateLast.isBefore(hardHeadline) || dateLast.isEqual(hardHeadline);
    }
}
