package ru.nsu.kolodina.ooptasks;


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

    public int runGitClone(String repoUrl) {
        List<String> args = command.buildArgs("git", "clone", repoUrl);
        return command.runCommand(null, args);
    }

    public int runGitCheckout(String repoName, String branch){
        List<String> args = command.buildArgs("git", "checkout", branch);
        return command.runCommand(repoName, args);
    }

    public int getLastCommitDate(String repoName, String folder) {
        List<String> args = command.buildArgs("git", "log", "-1", "--format=%cd", "--", folder);
        return command.runCommand(repoName, args);
    }
}
