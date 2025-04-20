package ru.nsu.kolodina.ooptasks;

import java.io.FileWriter;
import java.io.IOException;

public class HTMLGeneration {
    public static void generateHTML(Assignment assignment, String filePath){
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Assignment Report</title></head><body>");
        html.append("<h1>Student: ").append(assignment.studentName).append("</h1>");

        html.append("<table border='1'>");
        html.append("<tr>")
                .append("<th>Task ID</th>")
                .append("<th>Task Name</th>")
                .append("<th>Criteries met</th>")
                .append("<th>Max Score</th>")
                .append("<th>Mark</th>")
                .append("</tr>");

        for (Task task : assignment.getTasks()) {
            html.append("<tr>");
            html.append("<td>").append(task.id).append("</td>");
            html.append("<td>").append(task.name).append("</td>");
            html.append("<td>").append(task.buildOk != null && task.buildOk ? "Yes" : "No").append("</td>");
            html.append("<td>").append(task.maxScore).append("</td>");
            html.append("<td>").append(task.getMark()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(html.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
