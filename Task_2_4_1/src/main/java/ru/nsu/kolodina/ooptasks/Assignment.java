package ru.nsu.kolodina.ooptasks;

import lombok.Setter;

import java.util.List;

public class Assignment {
    String studentName;
    List<String> tasksNames;
    @Setter
    Group.Student student;
    @Setter
    List<Task> tasks;

    public Assignment(String studentName, List<String> tasksNames) {
        this.studentName = studentName;
        this.tasksNames = tasksNames;
    }
}
