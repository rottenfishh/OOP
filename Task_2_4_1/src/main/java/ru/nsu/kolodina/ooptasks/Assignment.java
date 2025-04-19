package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

public class Assignment {
    String student;
    List<String> tasks;
    @Setter
    Group.Student studentObj;
    @Setter
    List<Task> taskObj;

    public Assignment(String student, List<String> tasks) {
        this.student = student;
        this.tasks = tasks;
    }
}
