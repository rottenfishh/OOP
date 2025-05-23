package ru.nsu.kolodina.ooptasks;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.nsu.kolodina.ooptasks.CourseDSLLexer;
import ru.nsu.kolodina.ooptasks.CourseDSLParser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CourseDSLBuilder class for visiting and processing a DSL parse tree.
 */
@AllArgsConstructor
public class CourseDSLBuilder extends ru.nsu.kolodina.ooptasks.CourseDSLBaseVisitor<Void> {
    String path;
    public List<Group> groupList;
    public List<Task> tasksList;
    public List<Assignment> assignmentList;
    public Map<String, String> pathToClasses;
    public List<CheckPoint> checkPointList;

    /**
     * Visits an import statement and processes the imported file.
     *
     * @param ctx context of the import statement
     * @return null
     */
    @Override
    public Void visitImportStmt(CourseDSLParser.ImportStmtContext ctx) {
        String[] pathSplit = null;
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            pathSplit = path.split("\\\\");
        } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            pathSplit = path.split(File.separator);
        }
        StringBuilder pathToFolder = new StringBuilder();
        for (int i = 0; i < pathSplit.length - 1; i++) {
            pathToFolder.append(pathSplit[i]).append("/");
        }
        System.out.println(pathToFolder.toString());
        String fileName = ctx.STRING().getText().replace("\"", "");
        try {
            processImportedFile(pathToFolder + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Processes an imported file by parsing its content.
     *
     * @param fileName name of the file to process
     * @throws IOException if reading the file fails
     */
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
        CourseDSLBuilder visitor = new CourseDSLBuilder(path, groupList, tasksList, assignmentList, pathToClasses, checkPointList);
        visitor.visit(tree);
    }

    /**
     * Visits an assignment block and creates assignments.
     *
     * @param ctx context of the assignment block
     * @return null
     */
    @Override
    public Void visitAssignmentBlock(CourseDSLParser.AssignmentBlockContext ctx) {
        List<String> tasks = new ArrayList<>();
        for (CourseDSLParser.AssignmentDeclContext assignmentDecl : ctx.assignmentDecl()) {
            String studentNick = assignmentDecl.STRING().getText().replace("\"", "");

            for (CourseDSLParser.AssignedTaskContext assignedTask : assignmentDecl.assignedTask()) {
                String taskId = assignedTask.STRING().getText().replace("\"", "");
                tasks.add(taskId);
            }
            Assignment assignment = new Assignment(studentNick, tasks);
            assignmentList.add(assignment);
        }
        return null;
    }

    /**
     * Visits a group block and creates groups.
     *
     * @param ctx context of the group block
     * @return null
     */
    @Override
    public Void visitGroupBlock(CourseDSLParser.GroupBlockContext ctx) {
        for (CourseDSLParser.GroupDeclContext groupDecl : ctx.groupDecl()) {
            List<Group.Student> students = new ArrayList<>();
            String groupName = groupDecl.STRING().getText().replace("\"", "");
            for (CourseDSLParser.StudentDeclContext studentDecl : groupDecl.studentDecl()) {
                List<String> args = new ArrayList<>();
                for (CourseDSLParser.StudentBodyContext studentBody : studentDecl.studentBody()) {
                    args.add(studentBody.STRING().getText().replace("\"", ""));
                }
                Group.Student student = new Group.Student(groupName, args.get(0), args.get(1), args.get(2), args.get(3));
                students.add(student);
            }
            Group group = new Group(groupName, students);
            groupList.add(group);
        }
        return null;
    }

    /**
     * Visits a task block and processes all tasks.
     *
     * @param ctx context of the task block
     * @return null
     */
    @Override
    public Void visitTaskBlock(CourseDSLParser.TaskBlockContext ctx) {
        for (CourseDSLParser.TaskDeclContext taskDecl : ctx.taskDecl()) {
            visitTaskDecl(taskDecl);
        }
        return null;
    }

    /**
     * Visits a task declaration and creates a task.
     *
     * @param ctx context of the task declaration
     * @return null
     */
    @Override
    public Void visitTaskDecl(CourseDSLParser.TaskDeclContext ctx) {
        List<String> args = new ArrayList<>();
        String id = ctx.STRING(0).getText().replace("\"", "");
        int maxScore = 0;
        String softDeadline = "";
        String hardDeadline = "";
        String name = ctx.STRING(1).getText().replace("\"", "");
        for (CourseDSLParser.TaskBodyContext body : ctx.taskBody()) {
            String firstToken = body.getStart().getText();
            String arg = body.STRING().getText().replace("\"", "");
            if (firstToken.equals("maxScore")) {
                maxScore = Integer.parseInt(arg);
            }
            if (firstToken.equals("softDeadline")) {
                softDeadline = arg;
            }
            if (firstToken.equals("hardDeadline")) {
                hardDeadline = arg;
            }
            if (firstToken.equals("bonusScore")) {
                maxScore += Integer.parseInt(arg);
            }
            args.add(body.STRING().getText().replace("\"", ""));
        }
        Task task = new Task(id, name, maxScore, softDeadline, hardDeadline);
        tasksList.add(task);
        return null;
    }

    /**
     * Visits a build system declaration and stores the tool path.
     *
     * @param ctx context of the build system declaration
     * @return null
     */
    @Override
    public Void visitBuildSystemDecl(CourseDSLParser.BuildSystemDeclContext ctx) {
        String toolName = ctx.STRING(0).getText().replace("\"", "");
        String toolPath = ctx.STRING(1).getText().replace("\"", "");
        pathToClasses.put(toolName, toolPath);
        return null;
    }

    /**
     * Visits a criteries declaration and stores the path.
     *
     * @param ctx context of the criteries declaration
     * @return null
     */
    @Override
    public Void visitCriteriesDecl(CourseDSLParser.CriteriesDeclContext ctx) {
        String criteriaPath = ctx.STRING().getText().replace("\"", "");
        pathToClasses.put("criteries", criteriaPath);
        return null;
    }

    /**
     * Visits a grading declaration and stores the path.
     *
     * @param ctx context of the grading declaration
     * @return null
     */
    @Override
    public Void visitGradingDecl(CourseDSLParser.GradingDeclContext ctx) {
        String gradingPath = ctx.STRING().getText().replace("\"", "");
        pathToClasses.put("grading", gradingPath);
        return null;
    }

    /**
     * Visits a checkpoint declaration and creates a checkpoint.
     *
     * @param ctx context of the checkpoint declaration
     * @return null
     */
    @Override
    public Void visitCheckpointDecl(CourseDSLParser.CheckpointDeclContext ctx) {
        String name = ctx.STRING(0).getText().replace("\"", "");
        String date = ctx.STRING(1).getText().replace("\"", "");
        int score = Integer.parseInt(ctx.STRING(2).getText().replace("\"", ""));
        CheckPoint checkpoint = new CheckPoint(name, date, score);
        checkPointList.add(checkpoint);
        return null;
    }
}
