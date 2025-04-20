package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.util.List;

public class GradleBuild implements Build{
    Command cmd = new Command();
    public int compile(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "compileJava");
        return cmd.runCommand(dir, args, null, true);
    }
    public int test(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "test");
        return cmd.runCommand(dir, args, null, true);
    }
    public int docGen(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "javadoc", "--debug");
        return cmd.runCommand(dir, args, null, true);
    }
    public int checkstyle(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "checkstyleMain", "checkstyleTest");
        return cmd.runCommand(dir, args, null, true);
    }
}
