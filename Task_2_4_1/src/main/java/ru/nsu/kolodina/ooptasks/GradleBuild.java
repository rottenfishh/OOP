package ru.nsu.kolodina.ooptasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GradleBuild handles compilation, testing, documentation generation,
 * and checkstyle checks for Java projects using Gradle.
 */
public class GradleBuild implements Build {

    String gradleName = "";
    Command cmd = new Command();

    /**
     * Constructs a GradleBuild instance and determines the appropriate Gradle wrapper
     * depending on the operating system.
     */
    GradleBuild() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            gradleName = "gradlew.bat";
        } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            gradleName = "./gradlew";
        }
    }

    /**
     * Compiles the Java project using Gradle.
     *
     * @param repo the repository path
     * @param task the task directory
     * @return the command's exit code
     */
    @Override
    public int compile(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = null;
        if (gradleName.equals("./gradlew")) {
            args = cmd.buildArgs("sh", "-c", "cat", gradleName, "|", "tr", "-d", "'\r'", ">", "temp", "&&", "mv", "temp", gradleName);
            cmd.runCommand(dir, args, null, true);
        }
        args = cmd.buildArgs(gradleName, "compileJava", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    /**
     * Runs tests using Gradle and parses test result files.
     *
     * @param repo  the repository path
     * @param task  the task directory
     * @param tests a list to store test statistics: [total, skipped, failed]
     * @return the command's exit code
     */
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

    /**
     * Reads and extracts test result statistics from the given XML report file.
     *
     * @param file the test result XML file
     * @return a list containing [total, skipped, failed] counts
     */
    private List<Integer> readTestResults(File file) {
        BufferedReader reader = null;
        String currentLine;
        int skipped = 0;
        int failed = 0;
        int all = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains("<testsuite")) {
                    all = extractNumber(currentLine, "tests=");
                    skipped = extractNumber(currentLine, "skipped=");
                    failed = extractNumber(currentLine, "failures=");
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

    /**
     * Extracts an integer value of an attribute from a line of text.
     *
     * @param line      the line containing the attribute
     * @param attribute the attribute name (e.g., "tests=")
     * @return the extracted integer value
     */
    private int extractNumber(String line, String attribute) {
        int idx = line.indexOf(attribute);
        idx += attribute.length() + 1;
        StringBuilder sb = new StringBuilder();
        while (line.charAt(idx) != '"') {
            sb.append(line.charAt(idx));
            idx++;
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * Generates JavaDoc documentation using Gradle.
     *
     * @param repo the repository path
     * @param task the task directory
     * @return the command's exit code
     */
    @Override
    public int docGen(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> args = cmd.buildArgs(gradleName, "javadoc", "--quiet");
        return cmd.runCommand(dir, args, null, true);
    }

    /**
     * Runs Checkstyle using the Google style configuration.
     *
     * @param repo the repository path
     * @param task the task directory
     * @return 1 if violations are found (>10 lines), otherwise the command's exit code
     */
    @Override
    public int checkstyle(String repo, String task) {
        String dir = repo + File.separator + task;
        List<String> res = new ArrayList<>();
        List<String> args = cmd.buildArgs("java", "-jar", "checkstyle.jar", "-c", "google_checks.xml", dir);
        int exitCode = cmd.runCommand(null, args, res, false);
        if (res.size() > 10) {
            return 1;
        }
        return exitCode;
    }

    /**
     * Parses test result summary from Gradle test output lines.
     *
     * @param output the list of Gradle output lines
     * @return a list containing [total, failed, skipped] test counts or null if not found
     */
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
