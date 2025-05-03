package ru.nsu.kolodina.ooptasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command class for building and running system commands.
 */
public class Command {

    /**
     * Builds a list of arguments from a variable number of string inputs.
     *
     * @param strings command arguments
     * @return list of arguments
     */
    public List<String> buildArgs(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }

    /**
     * Runs a system command with the given arguments.
     *
     * @param dir      working directory, can be null
     * @param args     list of command arguments
     * @param result   list to collect command output lines, can be null
     * @param noOutput if true, output is inherited directly; if false, captured in result
     * @return exit code of the process
     */
    public int runCommand(String dir, List<String> args, List<String> result, boolean noOutput) {
        try {
            Process p;
            if (dir != null) {
                File folder = new File(dir);
                if (noOutput) {
                    p = new ProcessBuilder(args).directory(folder).inheritIO().start();
                } else {
                    p = new ProcessBuilder(args).directory(folder).start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (result != null) {
                            result.add(line);
                        }
                    }
                }
            } else {
                if (noOutput) {
                    p = new ProcessBuilder(args).inheritIO().start();
                } else {
                    p = new ProcessBuilder(args).start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (result != null) {
                            result.add(line);
                        }
                    }
                }
            }
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println(args.get(0) + " " + args.get(1) + " fail!");
                return exitCode;
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
