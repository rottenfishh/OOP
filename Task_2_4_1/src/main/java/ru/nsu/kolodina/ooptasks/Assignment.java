package ru.nsu.kolodina.ooptasks;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Assignment class with student: their tasks structure.
 */
@RequiredArgsConstructor
public class Assignment {
    @NonNull
    String studentName;
    @NonNull
    List<String> tasksNames;
    @Setter
    Group.Student student;
    @Getter
    @Setter
    List<Task> tasks;
}
