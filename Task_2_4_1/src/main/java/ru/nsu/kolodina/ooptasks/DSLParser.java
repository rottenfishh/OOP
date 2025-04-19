package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.nsu.kolodina.ooptasks.CourseDSLLexer;
import ru.nsu.kolodina.ooptasks.CourseDSLParser;

public class DSLParser {

    public void extractData(String path, List<Group> groupList, List<Task> tasksList, List<Assignment> assignmentList,  Map<String, String> pathToClasses) {
        CharStream input = null;
        try {
            input = CharStreams.fromFileName(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CourseDSLLexer lexer = new CourseDSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CourseDSLParser parser = new CourseDSLParser(tokens);

        ParseTree tree = parser.program();
        CourseDSLBuilder visitor2 = new CourseDSLBuilder(groupList, tasksList, assignmentList, pathToClasses);
        visitor2.visit(tree);
    }

    public void matchStudentsAndTasks(List<Group> students, List<Task> tasks, List<Assignment> assignmentList) {
        for (Assignment assignment : assignmentList) {
            for (Group group : students) {
                for (Group.Student student : group.studentsList) {
                    if (assignment.student.equals(student.name)) {
                        assignment.setStudentObj(student);
                        break;
                    }
                }
            }
            List<Task> matchedTasks = new ArrayList<>();
            for (String task: assignment.tasks) {
                for (Task taskId : tasks) {
                    if (task.equals(taskId.id)) {
                        matchedTasks.add(taskId);
                        break;
                    }
                }
            }
            assignment.setTaskObj(matchedTasks);
        }
    }
}

