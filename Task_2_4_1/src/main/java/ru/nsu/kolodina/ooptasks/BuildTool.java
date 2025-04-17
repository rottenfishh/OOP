package ru.nsu.kolodina.ooptasks;

import ru.nsu.kolodina.ooptasks.shit.Conditions;

import java.io.File;
import java.util.List;

public class BuildTool {
    Command command = new Command();

    public int compileProgram(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> args = command.buildArgs("gradlew.bat", "compileJava");
        return command.runCommand(dir, args);
    }

    public int generateJavaDoc(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> args = command.buildArgs("gradlew.bat", "javadoc");
        return command.runCommand(dir, args);
    }

    public int runCheckStyle(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> args = command.buildArgs("gradlew.bat", "checkstyleMain", "checkstyleTest");
        return command.runCommand(dir, args);
    }

    public int runTests(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> args = command.buildArgs("gradlew.bat", "test");
        return command.runCommand(dir, args);
    }
}
