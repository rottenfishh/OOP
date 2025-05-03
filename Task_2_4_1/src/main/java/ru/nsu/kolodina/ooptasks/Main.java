package ru.nsu.kolodina.ooptasks;

/**
 * Main class to run the assignment checking tool.
 *
 * Usage:
 * java -jar ooptasks.jar <config_path> <student_name> <checkpoint_name>
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java -jar ooptasks.jar <config_path> <student_name> <checkpoint_name>");
            System.exit(1);
        }

        String path = args[0];
        String studentName = args[1];
        String checkpointName = args[2];

        DriverTool driverTool = new DriverTool(path);
        driverTool.checkStudent(studentName, checkpointName, "report.html");

        System.out.println("Report generated at: " + System.getProperty("user.dir") + "/report.html");
    }
}
