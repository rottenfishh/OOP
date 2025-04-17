package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RunChecks {
    Conditions conditions;
    public RunChecks(Conditions conditions) {
        this.conditions = conditions;
    }
    public void compileProgram(String repoDir, String task) {
        File taskFolder = new File(repoDir + "/" + task);
        System.out.println(taskFolder.getAbsolutePath());
        try {
            Process p = new ProcessBuilder("gradlew.bat", "compileJava").inheritIO().directory(taskFolder).start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Gradle fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        conditions.compilationOk = true;
    }

    public void generateJavaDoc(String repoDir, String task) {
        File taskFolder = new File(repoDir + "/" + task);
        try {
            Process p = new ProcessBuilder("gradlew.bat", "javadoc").inheritIO().directory(taskFolder).start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Javadoc fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        conditions.documentationOk = true;
    }

    public void runCheckStyle(String repoDir, String task) {
        File taskFolder = new File(repoDir + "/" + task);
        try {
            Process p = new ProcessBuilder("gradlew.bat", "checkstyleMain", "checkstyleTest").inheritIO().directory(taskFolder).start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("checkStyle fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        conditions.checkStyleOk = true;
    }

    public void runTests(String repoDir, String task) {
        File taskFolder = new File(repoDir + "/" + task);
        try {
            Process p = new ProcessBuilder("gradlew.bat", "test").inheritIO().directory(taskFolder).start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("tests fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        conditions.testsOk = true;
    }
    public void meetsDeadline(String deadline, String date) {

        conditions.deadlineMeet = true;
    }

    public void runCommand(String repoDir, String folder, List<String> args) {
        File taskFolder = new File(repoDir + "/" + folder);
        try {
            Process p = new ProcessBuilder(args).inheritIO().directory(taskFolder).start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println(args.get(0) + " " + args.get(1) + " fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        conditions.testsOk = true;
    }
}
