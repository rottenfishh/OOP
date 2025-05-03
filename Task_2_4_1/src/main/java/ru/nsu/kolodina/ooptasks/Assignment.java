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
    /**
     * The name of the student to whom the assignment belongs.
     */
    @NonNull
    String studentName;

    /**
     * The list of task names assigned to the student.
     */
    @NonNull
    List<String> tasksNames;

    /**
     * The student object associated with this assignment.
     */
    @Setter
    Group.Student student;

    /**
     * The list of Task objects linked to this assignment.
     */
    @Getter
    @Setter
    List<Task> tasks;
}
