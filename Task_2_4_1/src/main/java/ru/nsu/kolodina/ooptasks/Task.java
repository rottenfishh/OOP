package ru.nsu.kolodina.ooptasks;

import lombok.*;

import java.util.Map;

@RequiredArgsConstructor
public class Task {
    @Getter
    @Setter
    double mark;
    @NonNull
    String id;
    @NonNull
    String name;
    @NonNull
    double maxScore;
    @NonNull
    String softDeadline;
    @NonNull
    String hardDeadline;
    Boolean buildOk = false;
    @Getter
    @Setter
    Map<String, String> conditions;
}
