package ru.nsu.kolodina.ooptasks;

import java.io.*;
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
        List<String> args = null;
        //sed -i 's/\r$//' filename
        if (gradleName.equals("./gradlew")) {
            args = cmd.buildArgs("sed", "-i", "'s/\\r$//'", gradleName);
            cmd.runCommand(dir, args, null, true);
        }
        args = cmd.buildArgs(gradleName, "compileJava", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    @Override
    public int test(String repo, String task, List<Integer> tests) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs(gradleName, "test");
        int exitCode = cmd.runCommand(dir, args, null, true);
        String testReportDir = repo + File.separator + task + File.separator + "build" + File.separator + "test-results" + File.separator + "test";
        File reportDir = new File(testReportDir);
        File[] files = reportDir.listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".xml")) {
                tests.addAll(readTestResults(file));
                break;
            }
        }
        return exitCode;
    }

    private List<Integer> readTestResults(File file) {
        BufferedReader reader = null;
        String currentLine;
        int skipped = 0;
        int failed = 0;
        int all = 0;
        int success = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("<testsuite")) {
                    all = extractNumber(currentLine, "tests=");
                    skipped = extractNumber(currentLine, "skipped=");
                    failed = extractNumber(currentLine, "failures=");
                    success = all - skipped - failed;
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Integer> tests = new ArrayList<>();
        tests.add(all);
        tests.add(skipped);
        tests.add(failed);
        return tests;
    }

    private int extractNumber(String line, String attribute) {
        int idx = line.indexOf(attribute);
        idx += attribute.length() + 1;
        StringBuilder sb = new StringBuilder();
        while (line.charAt(idx) != '"') {
            sb.append(line.charAt(idx));
            idx++;
        }
        String see = sb.toString();
        return Integer.parseInt(sb.toString());
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

    public List<Integer> parseTestResult(List<String> output) {
        for (String line : output) {
            if (line.contains("tests completed")) {
                line = line.replaceAll("[\\[\\]]", "").trim();

                String[] parts = line.split(",");
                int total = 0;
                int failed = 0;
                int skipped = 0;

                for (String part : parts) {
                    part = part.trim();
                    if (part.endsWith("tests completed")) {
                        total = Integer.parseInt(part.split(" ")[0]);
                    } else if (part.endsWith("failed")) {
                        failed = Integer.parseInt(part.split(" ")[0]);
                    } else if (part.endsWith("skipped")) {
                        skipped = Integer.parseInt(part.split(" ")[0]);
                    }
                }
                List<Integer> tests = new ArrayList<>();
                tests.add(total);
                tests.add(failed);
                tests.add(skipped);
                return tests;
            }
        }
        return null;
    }
}
