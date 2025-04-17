package ru.nsu.kolodina.ooptasks;


import java.io.File;
import java.io.IOException;

public class Git {

    public String extractRepoName(String repoUrl) {
        String[] parts = repoUrl.split("/");
        String repoName = parts[parts.length - 1];
        if (repoName.endsWith(".git")) {
            repoName = repoName.substring(0, repoName.length() - 4);
        }
        return repoName;
    }

    public void runGitClone(String command, String arg) {
        try {
            Process p = new ProcessBuilder("git", command, arg).inheritIO().start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Command git clone fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void runGitCheckout(String repoName, String branch){
        File repoDir = new File(repoName);
        System.out.println(repoDir.getAbsolutePath());
        try {
            Process p = new ProcessBuilder("git", "checkout", branch).directory(repoDir).inheritIO().start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Command git checkout fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkFolder(String repoDir, String folder) {
        File srcFolder = new File(repoDir, "TEST");
        if (srcFolder.exists()) {
            System.out.println("Found source folder: " + srcFolder.getAbsolutePath());
        } else {
            System.out.println("No src folder found.");
        }
    }

    public void getLastCommitDate(String repoName, String folder) {
        File repoDir = new File(repoName);
        System.out.println(repoDir.getAbsolutePath());
        try {
            Process p = new ProcessBuilder("git", "log", "-1", "--format=%cd", "--" , folder).directory(repoDir).inheritIO().start();
            int exitCode = p.waitFor();
            if (exitCode != 0) {
                System.err.println("Command git checkout fail!");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
