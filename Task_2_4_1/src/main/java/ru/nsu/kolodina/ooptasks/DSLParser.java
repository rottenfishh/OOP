package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.nsu.kolodina.ooptasks.CourseDSLLexer;
import ru.nsu.kolodina.ooptasks.CourseDSLParser;

/**
 * DSLParser is responsible for parsing the DSL configuration file
 * and linking students to tasks within the parsed data.
 */
public class DSLParser {

    /**
     * Parses the DSL file and populates the provided data structures
     * with groups, tasks, assignments, build systems, and checkpoints.
     *
     * @param path            path to the DSL file
     * @param groupList       list to store parsed groups
     * @param tasksList       list to store parsed tasks
     * @param assignmentList  list to store parsed assignments
     * @param pathToClasses   map to store build tools and class paths
     * @param checkPointsList list to store parsed checkpoints
     */
    public void extractData(String path, List<Group> groupList, List<Task> tasksList,
                            List<Assignment> assignmentList, Map<String, String> pathToClasses,
                            List<CheckPoint> checkPointsList) {
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
        CourseDSLBuilder visitor2 = new CourseDSLBuilder(path, groupList, tasksList, assignmentList, pathToClasses, checkPointsList);
        visitor2.visit(tree);
    }

    /**
     * Matches students and tasks to their corresponding assignments.
     *
     * @param students        list of parsed student groups
     * @param tasks           list of parsed tasks
     * @param assignmentList  list of assignments to populate with student and task objects
     */
    public void matchStudentsAndTasks(List<Group> students, List<Task> tasks, List<Assignment> assignmentList) {
        for (Assignment assignment : assignmentList) {
            for (Group group : students) {
                for (Group.Student student : group.studentsList) {
                    if (assignment.studentName.equals(student.name)) {
                        assignment.setStudent(student);
                        break;
                    }
                }
            }
            List<Task> matchedTasks = new ArrayList<>();
            for (String task : assignment.tasksNames) {
                for (Task taskId : tasks) {
                    if (task.equals(taskId.id)) {
                        matchedTasks.add(taskId);
                        break;
                    }
                }
            }
            assignment.setTasks(matchedTasks);
        }
    }
}
