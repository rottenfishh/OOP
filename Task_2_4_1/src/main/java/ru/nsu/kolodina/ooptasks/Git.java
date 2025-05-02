package ru.nsu.kolodina.ooptasks;


import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Git {
    Command command = new Command();

    public String extractRepoName(String repoUrl) {
        String[] parts = repoUrl.split("/");
        String repoName = parts[parts.length - 1];
        if (repoName.endsWith(".git")) {
            repoName = repoName.substring(0, repoName.length() - 4);
        }
        return repoName;
    }

    public int runGitClone(String pathDir, String repoUrl) {
        List<String> args = command.buildArgs("git", "clone", repoUrl);
        boolean res = new File(pathDir).mkdirs();
        return command.runCommand(pathDir, args, null, true);
    }

    public int runGitCheckout(String repoName, String branch){
        List<String> args = command.buildArgs("git", "checkout", branch);
        return command.runCommand(repoName, args, null, true);
    }

    public int getLastCommitDate(String repoName, String folder, List<String> result) {
        List<String> args = command.buildArgs("git", "log", "-1", "--format=%cd", "--", folder);
        return command.runCommand(repoName, args, result, false);
    }

    public int getFirstCommitDate(String repoName, String folder, List<String> result) {
        List<String> temp = new ArrayList<>();
        List<String> args = command.buildArgs("git", "log", "--reverse",
                "--format=%cd", "--", folder);
        int code = command.runCommand(repoName, args, temp, false);
        result.add(temp.get(0));
        return code;
    }
    public int gitPull(String repoUrl, String branch) {
        List<String> args = command.buildArgs("git", "pull", "origin", branch);
        return command.runCommand(repoUrl, args, null, true);
    }

    public int gitClean(String repoUrl) {
        List<String> args = command.buildArgs("git", "clean", "-d", "-f");
        return command.runCommand(repoUrl, args, null, true);
    }
}
