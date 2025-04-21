package ru.nsu.kolodina.ooptasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
public class TestDSL {
    DriverTool driverTool;
    @BeforeEach
    public void setUp() {
        driverTool = new DriverTool();
    }
    @Test
    public void testMark() {
        String path = "src/main/DSL/course.dsl";
        driverTool.extractData(path);
        CheckPoint firstCheck = driverTool.getCheckPoint("midterm");
        Assignment alinaAssignment = driverTool.getAssignment("Alina");
        Group.Student student = alinaAssignment.student;
        driverTool.checkStudent(alinaAssignment, firstCheck);
        assertEquals(3, student.getMark());
        System.out.println(student.getMark());
        List<Assignment> assignments = driverTool.getAssignmentList();
        HTMLGeneration.generateHTML(assignments, "lol.html");
    }

    @Test
    public void genHTML() {
        List<Assignment> assignments = driverTool.getAssignmentList();
        HTMLGeneration.generateHTML(assignments, "lol.html");
    }

}
