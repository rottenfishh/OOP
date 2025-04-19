package ru.nsu.kolodina.ooptasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {

    public List<String> buildArgs(String... strings) {
        return new ArrayList<>(Arrays.asList(strings));
    }
    public int runCommand(String dir, List<String> args, List<String> result) {
        try {
            Process p;
            if (dir != null) {
                File folder = new File(dir);
                p = new ProcessBuilder(args).directory(folder).start();
            } else {
                p = new ProcessBuilder(args).start();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (result != null) {
                    result.add(line);
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
