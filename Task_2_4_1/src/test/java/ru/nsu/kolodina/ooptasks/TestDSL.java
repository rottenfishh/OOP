package ru.nsu.kolodina.ooptasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
public class TestDSL {
    DriverTool driverTool;
    String repository = null;
    String path = "src/main/DSL/course.dsl";
    @BeforeEach
    public void setUp() {
        driverTool = new DriverTool();
    }

    @Test
    public void testExtractData() {
        driverTool.extractData(path);
        CheckPoint firstCheck = driverTool.getCheckPoint("midterm");
        Assignment firstAssignment = driverTool.getAssignment("Alina");
        assertNotNull(firstCheck);
        assertNotNull(firstAssignment);
    }

    @Test
    public void testGetStudentRepo() {
        driverTool.extractData(path);
        Assignment assignment = driverTool.getAssignment("Alina");
        repository = driverTool.getStudentRepo(assignment);
        assertNotNull(repository);
    }
    @Test
    public void testBuild() {
        driverTool.extractData(path);
        CheckPoint firstCheck = driverTool.getCheckPoint("midterm");
        Assignment assignment = driverTool.getAssignment("Alina");
        Group.Student student = assignment.student;
        driverTool.runBuildChecks(student.buildTool,  repository, assignment.tasks.get(0));
    }
    @Test
    public void testMark() {
        driverTool.extractData(path);
        CheckPoint firstCheck = driverTool.getCheckPoint("midterm");
        Assignment alinaAssignment = driverTool.getAssignment("Alina");
        Group.Student student = alinaAssignment.student;
        driverTool.checkStudent(alinaAssignment, firstCheck);
        assertEquals(3, student.getMark());
    }

    @Test
    public void genHTML() {
        List<Assignment> assignments = driverTool.getAssignmentList();
        HTMLGeneration.generateHTML(assignments, "lol.html");
    }

}
