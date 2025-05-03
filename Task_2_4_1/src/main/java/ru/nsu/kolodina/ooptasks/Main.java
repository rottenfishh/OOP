package ru.nsu.kolodina.ooptasks;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        String studentName = args[1];
        String checkpointName = args[2];
        String htmlPath = args[3];
        DriverTool driverTool = new DriverTool(path);
        driverTool.checkStudent(studentName, checkpointName, htmlPath);
    }
}
