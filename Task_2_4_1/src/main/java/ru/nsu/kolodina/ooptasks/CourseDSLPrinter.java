package ru.nsu.kolodina.ooptasks;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.kolodina.ooptasks.CourseDSLParser;
import ru.nsu.kolodina.ooptasks.CourseDSLVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CourseDSLPrinter extends ru.nsu.kolodina.ooptasks.CourseDSLBaseVisitor<Void> {

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
        ru.nsu.kolodina.ooptasks.CourseDSLLexer lexer = new ru.nsu.kolodina.ooptasks.CourseDSLLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CourseDSLParser parser = new CourseDSLParser(tokens);

        ParseTree tree = parser.program();
        CourseDSLPrinter visitor = new CourseDSLPrinter();
        visitor.visit(tree);
    }

    @Override
    public Void visitTaskDecl(CourseDSLParser.TaskDeclContext ctx) {
        List<String> args = new ArrayList<>();
        String id = ctx.STRING(0).getText();
        String name = ctx.STRING(1).getText();
        String maxScore = null;
        String softDeadline = null;
        String hardDeadline = null;
        System.out.println("Task ID: " + id + ", Name: " + name);
        for (CourseDSLParser.TaskBodyContext body : ctx.taskBody()) {
            args.add(body.STRING().getText());
        }
        System.out.println("Max Score: " + args.get(0) + ", SoftDeadline: " + args.get(1) + ", HardDeadline: " + args.get(2));
        return null;
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
        List<Group.Student> students = new ArrayList<>();
        String groupName = null;
        String name = null;
        String studentNick= null;
        String github = null;
        for (CourseDSLParser.GroupDeclContext groupDecl : ctx.groupDecl()) {
            groupName = groupDecl.STRING().getText();
            for (CourseDSLParser.StudentDeclContext studentDecl : groupDecl.studentDecl()) {
                List<String> args = new ArrayList<>();
                for (CourseDSLParser.StudentBodyContext studentBody : studentDecl.studentBody()) {
                    args.add(studentBody.STRING().getText());
                }
                System.out.println("Name " + args.get(0) + ", " + args.get(1) + ", " + args.get(2));
            }
        }
        System.out.println("group name " + groupName);
        return null;
    }

    @Override
    public Void visitTaskBlock(CourseDSLParser.TaskBlockContext ctx) {
        for (CourseDSLParser.TaskDeclContext taskDecl : ctx.taskDecl()) {
            visitTaskDecl(taskDecl);
        }
        return null;
    }
}
