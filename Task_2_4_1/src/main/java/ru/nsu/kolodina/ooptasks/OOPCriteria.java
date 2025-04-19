package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

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

    public String convertDate(String gitDate) {
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
        ZonedDateTime dateFirst = ZonedDateTime.parse(dateFirstCommit, formatter);
        ZonedDateTime softDeadline = ZonedDateTime.parse(task.softDeadline, formatter);
        if (dateFirst.isBefore(softDeadline)) {
            return true;
        }
        System.out.println(dateFirstCommit);
        return false;
    }
    public boolean hardDeadlineMeet(String repo, Task task) {
        Git git = new Git();
        List<String> res = new ArrayList<>();
        git.getLastCommitDate(repo, task.id, res);
        String dateLastCommit = convertDate(res.get(1));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        ZonedDateTime dateLast = ZonedDateTime.parse(dateLastCommit, formatter);
        ZonedDateTime hardHeadline = ZonedDateTime.parse(task.hardDeadline, formatter);
        if (hardHeadline.isBefore(dateLast)) {
            return true;
        }
        System.out.println(dateLastCommit);
        return false;
    }
}
