package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Command {
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
    }
}
