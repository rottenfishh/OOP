package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Group {
    String groupName;
    public List<Student> studentsList = new ArrayList<>();

    @RequiredArgsConstructor
    public static class Student {
        @NonNull
        String groupName;
        @Getter
        double score = 0.0;
        @Getter
        int mark = 0;
        @NonNull
        String name;
        @NonNull
        String githubNickName;
        @NonNull
        String githubLink;
        @NonNull
        String buildTool;
        public void addScore(double newScore) {
            this.score += newScore;
        }
    }

}
