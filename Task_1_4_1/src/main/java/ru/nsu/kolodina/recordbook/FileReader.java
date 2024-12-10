package ru.nsu.kolodina.recordbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class for reading RecordBook from file (deserializing).
 */
public class FileReader {
    /**
     * read plan of education from file.
     *
     * @param path to plan
     * @return List of int arrays which have number of marks for each semester
     * @throws URISyntaxException if not valid urisyntax
     * @throws FileNotFoundException if file not found
     */
    public List<int[]> readPlan(String path) throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        File file = new File(resource.toURI());
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        List<int[]> plan = new ArrayList<>();
        int[][] marksAll = new int[9][5];
        int i = 0;
        int idx = 0;
        plan.add(marksAll[0]);
        while (scanner.hasNextLine()) {
            idx = 0;
            String[] strings = scanner.nextLine().replaceAll("\\s", "").split(",");
            for (String s : strings) {
                marksAll[i][idx] = Integer.parseInt(s);
                idx++;
            }
            plan.add(marksAll[i]);
            i++;
        }
        return plan;
    }

    /**
     * read student's record book from file.
     *
     * @param path to file
     * @return RecordBook containing all fields read from file
     * @throws URISyntaxException throw
     * @throws FileNotFoundException throw
     */
    public RecordBook readFromFile(String path) throws URISyntaxException, FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        File file = new File(resource.toURI());
        Scanner scanner = new Scanner(file);
        String info = scanner.nextLine();
        String[] studentInfo = info.split(";");
        RecordBook.Basis basis = null;
        if (studentInfo[0].equals("FREE")) {
            basis = RecordBook.Basis.FREE;
        }
        if (studentInfo[0].equals("PAID")) {
            basis = RecordBook.Basis.PAID;
        }
        int currSemester = Integer.parseInt(studentInfo[1]);
        boolean graduated = studentInfo[2].equals("yes");
        String name = studentInfo[3];

        String pathPlan = "Plan";
        List<int[]> plan = readPlan(pathPlan);
        RecordBook book = new RecordBook(basis, currSemester, graduated, plan);
        book.setName(name);
        Score.Type type = null;
        Score.Name nameMark = null;
        while (scanner.hasNextLine()) {
            String string = scanner.nextLine();
            String[] mark = string.split(";");
            int sem = Integer.parseInt(mark[0]);
            try {
                type = Score.Type.valueOf(mark[1]);
            } catch (NullPointerException | IllegalArgumentException e ) {
                System.err.println("Invalid type!");
            }
            try {
                nameMark = Score.Name.valueOf(mark[2]);
            } catch (NullPointerException | IllegalArgumentException e ) {
                System.err.println("Invalid name!");
            }
            double score = Double.parseDouble(mark[3]);
            String subject = mark[4];
            book.addMark(sem, type, nameMark, score, subject, sem);
        }
        return book;
    }
}
