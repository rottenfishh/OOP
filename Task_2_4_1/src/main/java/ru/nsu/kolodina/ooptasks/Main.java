package ru.nsu.kolodina.ooptasks;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "src/main/DSL/course.dsl";
        DriverTool driverTool = new DriverTool();
        List<Assignment> assignmentList = driverTool.extractData(path);
        Assignment ass = assignmentList.get(0);
        CheckPoint firstCheck = driverTool.getCheckPoint("midterm");
        Group.Student student = ass.student;
        Assignment alinaAssignment = driverTool.getAssignment("Alina");
        driverTool.checkStudent(alinaAssignment, firstCheck);
        System.out.println(student.getMark());
        List<Assignment> assignments = driverTool.getAssignmentList();
        HTMLGeneration.generateHTML(assignments, "lol.html");
    }
}
