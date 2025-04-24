package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleBuild implements Build{

    String gradleName = "";
    Command cmd = new Command();

    GradleBuild() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            gradleName = "gradlew.bat";
        } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            gradleName = "./gradlew";
        }
    }
    @Override
    public int compile(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs(gradleName, "compileJava", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int test(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs(gradleName, "test",  "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int docGen(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs(gradleName, "javadoc", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int checkstyle(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> res = new ArrayList<String>();
        List<String> args = cmd.buildArgs("java", "-jar", "checkstyle.jar", "-c", "google_checks.xml", dir);
        int exitCode = cmd.runCommand(null, args, res, false);
        if (res.size() > 10) {
            return 1;
        }
        return exitCode;
    }
}
