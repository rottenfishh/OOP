package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DSLParser {
    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        CharStream input = CharStreams.fromFileName("src/main/DSL/course.dsl");

        ru.nsu.kolodina.ooptasks.CourseDSLLexer lexer = new ru.nsu.kolodina.ooptasks.CourseDSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ru.nsu.kolodina.ooptasks.CourseDSLParser parser = new ru.nsu.kolodina.ooptasks.CourseDSLParser(tokens);

        ParseTree tree = parser.program();
        List<Group> groupList = new ArrayList<>();
        List<Task> tasksList = new ArrayList<>();
        CourseDSLBuilder visitor2 = new CourseDSLBuilder(groupList, tasksList);
        visitor2.visit(tree);
        System.out.println(tree.toStringTree(parser));
        System.out.println("Tasks:                            ");
        System.out.println(tasksList);
        System.out.println("Groups:                            ");
        System.out.println(groupList);
    }
}

