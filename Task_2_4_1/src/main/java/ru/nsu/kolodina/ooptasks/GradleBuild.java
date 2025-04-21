package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GradleBuild implements Build{

    Command cmd = new Command();

    @Override
    public int compile(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "compileJava", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int test(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "test",  "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int docGen(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs("gradlew.bat", "javadoc", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }
    
    @Override
    public int checkstyle(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> res = new ArrayList<String>();
        List<String> args = cmd.buildArgs("java", "-jar", "checkstyle.jar", "-c", "google_checks.xml", dir);
        int exitCode = cmd.runCommand(null, args, res, false);
        System.out.println("aa");
        System.out.println(res);
        if (res.size() > 10) {
            return 1;
        }
        return exitCode;
    }
}
