package ru.nsu.kolodina.ooptasks;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDSL {
    String path = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "main" + File.separator + "DSL" + File.separator + "course.dsl";
    DriverTool driverTool = new DriverTool(path);

    @Test
    public void testMark() {
        int score = driverTool.checkStudent("Alina", "midterm", "lol2.html");
        assertEquals(5, score);
        System.out.println(score);
    }
}
