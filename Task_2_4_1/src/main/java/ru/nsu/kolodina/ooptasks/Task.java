package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Task {
    String id;
    String name;
    Integer maxScore;
    String softDeadline;
    String hardDeadline;
}
