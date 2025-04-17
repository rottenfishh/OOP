package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    public List<String> buildArgs(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }
    public int runCommand(String dir, List<String> args) {
        try {
            Process p;
            if (dir != null) {
                File folder = new File(dir);
                p = new ProcessBuilder(args).inheritIO().directory(folder).start();
            } else {
                p = new ProcessBuilder(args).inheritIO().start();
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
