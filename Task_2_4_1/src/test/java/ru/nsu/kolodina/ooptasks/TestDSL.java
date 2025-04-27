package ru.nsu.kolodina.ooptasks;

import org.junit.jupiter.api.Test;

public class TestDSL {
    DriverTool driverTool = new DriverTool("src/main/DSL/course.dsl");

    @Test
    public void testMark() {
        String path = "src/main/DSL/course.dsl";
        driverTool.extractData(path);
        driverTool.checkStudent("Alina", "midterm", "lol2.html");

    }
}
