package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.nsu.kolodina.ooptasks.shit.Conditions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuildTool {
    @NoArgsConstructor
    public static class buildToolCommands {
        String buildToolName;
        String compile;
        String test;
        String docGen;
        String checkstyle;
    }
    @NonNull
    buildToolCommands commands;
    Command command = new Command();

    public int compileProgram(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> cmd = List.of(commands.compile.split(" "));
        List<String> args = new ArrayList<>();
        args.add(commands.buildToolName);
        args.addAll(cmd);
        return command.runCommand(dir, args);
    }

    public int generateJavaDoc(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> cmd = List.of(commands.docGen.split(" "));
        List<String> args = new ArrayList<>();
        args.add(commands.buildToolName);
        args.addAll(cmd);
        return command.runCommand(dir, args);
    }

    public int runCheckStyle(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> cmd = List.of(commands.checkstyle.split(" "));
        List<String> args = new ArrayList<>();
        args.add(commands.buildToolName);
        args.addAll(cmd);
        return command.runCommand(dir, args);
    }

    public int runTests(String repoDir, String task) {
        String dir = repoDir + File.separator + task;
        List<String> cmd = List.of(commands.test.split(" "));
        List<String> args = new ArrayList<>();
        args.add(commands.buildToolName);
        args.addAll(cmd);
        return command.runCommand(dir, args);
    }
}
