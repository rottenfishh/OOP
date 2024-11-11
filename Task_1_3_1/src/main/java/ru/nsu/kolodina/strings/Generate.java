package ru.nsu.kolodina.strings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generate {
    public static void main(String[] args) throws IOException {
        try {
            File myObj = new File("file4.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Random rand = new Random();
        String string1;
        List<String> inputs = new ArrayList<>();
        inputs.add("dad");
        inputs.add("daddy");
        inputs.add("mordadd");
        inputs.add("daapres");
        inputs.add("peepoopeepoo");
        inputs.add("hahahasistro");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            int n = rand.nextInt(6);
            sb.append(inputs.get(n));
        }
        try {
            FileWriter myWriter = new FileWriter("file4.txt");
            myWriter.write(sb.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}