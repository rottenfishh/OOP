package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@AllArgsConstructor
public class OOPCriteria implements Criteries {

    public boolean meetsCriteria(String repo, Task task) {
        return task.buildOk;
    }

    public static String convertDate(String gitDate) {
        DateTimeFormatter gitFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy Z", Locale.ENGLISH);
        DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ZonedDateTime dateTime = ZonedDateTime.parse(gitDate, gitFormat);
        String formatted = dateTime.format(desiredFormat);
        System.out.println(formatted);
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
        if (dateFirst.isBefore(softDeadline)) {
            return true;
        }
        return false;
    }
    public boolean hardDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        List<String> res = new ArrayList<>();
        git.getLastCommitDate(repo, task.id, res);
        String dateLastCommit = convertDate(res.get(0));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateLast = LocalDate.parse(dateLastCommit, formatter);
        LocalDate hardHeadline = LocalDate.parse(task.hardDeadline, formatter);
        if (dateLast.isBefore(hardHeadline)) {
            return true;
        }
        System.out.println("hard " + hardHeadline + " " + dateLast);
        return false;
    }
}
