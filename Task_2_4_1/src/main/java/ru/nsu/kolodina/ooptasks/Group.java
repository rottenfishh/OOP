package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Group {
    String groupName;
    public List<Student> studentsList = new ArrayList<>();

    @AllArgsConstructor
    public static class Student {
        String name;
        String githubNickName;
        String githubLink;
    }
}
