package ru.nsu.kolodina.recordbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FileReader reader = new FileReader();
        RecordBook book = reader.readFromFile("BookAlina");
        System.out.println(book.gradeBook.get(1).marks.get(0).score);
        book.writeToFile("book2.txt");
    }
}
