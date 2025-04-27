package ru.nsu.kolodina.ooptasks;

import java.util.Map;

public interface Criteries {
    Map<String, String> meetsCriteria(String tool, String repo, Task task);
}
