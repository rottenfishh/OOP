package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import ru.nsu.kolodina.ooptasks.CourseDSLParser;
import ru.nsu.kolodina.ooptasks.CourseDSLLexer;

@AllArgsConstructor
public class CourseDSLBuilder extends ru.nsu.kolodina.ooptasks.CourseDSLBaseVisitor<Void> {
    public List<Group> groupList;
    public List<Task> tasksList;

    @Override
    public Void visitImportStmt(CourseDSLParser.ImportStmtContext ctx) {
        String fileName = ctx.STRING().getText().replace("\"", ""); // Remove the surrounding quotes
        try {
            processImportedFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void processImportedFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("File not found: " + fileName);
            return;
        }

        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        ANTLRInputStream inputStream = new ANTLRInputStream(content);
        CourseDSLLexer lexer = new ru.nsu.kolodina.ooptasks.CourseDSLLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CourseDSLParser parser = new CourseDSLParser(tokens);

        ParseTree tree = parser.program();
        CourseDSLPrinter visitor = new CourseDSLPrinter();
        visitor.visit(tree);
    }

    @Override
    public Void visitAssignmentBlock(CourseDSLParser.AssignmentBlockContext ctx) {
        for (CourseDSLParser.AssignmentDeclContext assignmentDecl : ctx.assignmentDecl()) {
            String studentNick = assignmentDecl.STRING().getText();

            for (CourseDSLParser.AssignedTaskContext assignedTask : assignmentDecl.assignedTask()) {
                String taskId = assignedTask.STRING().getText();
                System.out.println("Assign " + taskId + " to " + studentNick);
            }
        }
        return null;
    }

    @Override
    public Void visitGroupBlock(CourseDSLParser.GroupBlockContext ctx) {
        for (CourseDSLParser.GroupDeclContext groupDecl : ctx.groupDecl()) { // parse group list
            List<Group.Student> students = new ArrayList<>();
            String groupName = groupDecl.STRING().getText();
            for (CourseDSLParser.StudentDeclContext studentDecl : groupDecl.studentDecl()) { //parse students list
                List<String> args = new ArrayList<>();
                for (CourseDSLParser.StudentBodyContext studentBody : studentDecl.studentBody()) { // parse one student
                    args.add(studentBody.STRING().getText());
                }
                Group.Student student = new Group.Student(args.get(0), args.get(1), args.get(2));
                students.add(student);
                System.out.println("Name " + args.get(0) + ", " + args.get(1) + ", " + args.get(2));
            }
            Group group = new Group(groupName, students);
            groupList.add(group);
            System.out.println("group name " + groupName);
        }
        return null;
    }

    @Override
    public Void visitTaskBlock(CourseDSLParser.TaskBlockContext ctx) {
        for (CourseDSLParser.TaskDeclContext taskDecl : ctx.taskDecl()) {
            visitTaskDecl(taskDecl);
        }
        return null;
    }

    @Override
    public Void visitTaskDecl(CourseDSLParser.TaskDeclContext ctx) {
        List<String> args = new ArrayList<>();
        String id = ctx.STRING(0).getText();
        String name = ctx.STRING(1).getText();
        System.out.println("Task ID: " + id + ", Name: " + name);
        for (CourseDSLParser.TaskBodyContext body : ctx.taskBody()) {
            args.add(body.STRING().getText());
        }
        Task task = new Task(id, name, Integer.parseInt(args.get(0)), args.get(1), args.get(2));
        tasksList.add(task);
        System.out.println("Max Score: " + args.get(0) + ", SoftDeadline: " + args.get(1) + ", HardDeadline: " + args.get(2));
        return null;
    }
}
