package ru.nsu.kolodina.ooptasks;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Git utility class for managing git operations on student repositories.
 */
public class Git {
    Command command = new Command();

    /**
     * Extracts the repository name from a Git URL.
     *
     * @param repoUrl the repository URL
     * @return the repository name without ".git" suffix
     */
    public String extractRepoName(String repoUrl) {
        String[] parts = repoUrl.split("/");
        String repoName = parts[parts.length - 1];
        if (repoName.endsWith(".git")) {
            repoName = repoName.substring(0, repoName.length() - 4);
        }
        return repoName;
    }

    /**
     * Clones a Git repository into the specified directory.
     *
     * @param pathDir the target directory
     * @param repoUrl the repository URL
     * @return the command's exit code
     */
    public int runGitClone(String pathDir, String repoUrl) {
        List<String> args = command.buildArgs("git", "clone", repoUrl);
        boolean res = new File(pathDir).mkdirs();
        return command.runCommand(pathDir, args, null, true);
    }

    /**
     * Checks out a specific branch in a repository.
     *
     * @param repoName the repository path
     * @param branch   the branch name
     * @return the command's exit code
     */
    public int runGitCheckout(String repoName, String branch) {
        List<String> args = command.buildArgs("git", "checkout", branch);
        return command.runCommand(repoName, args, null, true);
    }

    /**
     * Gets the date of the last commit in the specified folder.
     *
     * @param repoName the repository path
     * @param folder   the folder inside the repository
     * @param result   list to store the commit date output
     * @return the command's exit code
     */
    public int getLastCommitDate(String repoName, String folder, List<String> result) {
        List<String> args = command.buildArgs("git", "log", "-1", "--format=%cd", "--", folder);
        return command.runCommand(repoName, args, result, false);
    }

    /**
     * Gets the date of the first commit in the specified folder.
     *
     * @param repoName the repository path
     * @param folder   the folder inside the repository
     * @param result   list to store the commit date output
     * @return the command's exit code
     */
    public int getFirstCommitDate(String repoName, String folder, List<String> result) {
        List<String> temp = new ArrayList<>();
        List<String> args = command.buildArgs("git", "log",
                "--reverse", "--format=%cd", "--", folder);
        int code = command.runCommand(repoName, args, temp, false);
        result.add(temp.get(0));
        return code;
    }

    /**
     * Performs a git pull on the specified repository and branch.
     *
     * @param repoUrl the repository path
     * @param branch  the branch name
     * @return the command's exit code
     */
    public int gitPull(String repoUrl, String branch) {
        List<String> args = command.buildArgs("git", "pull", "origin", branch);
        return command.runCommand(repoUrl, args, null, true);
    }

    /**
     * Cleans untracked files and directories from the repository.
     *
     * @param repoUrl the repository path
     * @return the command's exit code
     */
    public int gitClean(String repoUrl) {
        List<String> args = command.buildArgs("git", "clean", "-d", "-f");
        return command.runCommand(repoUrl, args, null, true);
    }
}
