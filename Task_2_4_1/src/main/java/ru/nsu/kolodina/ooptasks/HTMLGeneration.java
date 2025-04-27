package ru.nsu.kolodina.ooptasks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HTMLGeneration {
    public static void generateHTML(List<Assignment> assignments, String filePath) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Assignment Report</title></head><body>");
        for (Assignment assignment : assignments) {
            html.append("<h1> Group: ").append(assignment.student.groupName).append(" Student: ").append(assignment.studentName).append("</h1>");
            html.append("<h2>").append("Student current score: ").append(assignment.student.score).append(" current mark: ").append(assignment.student.mark).append("</h2>");
            html.append("<table border='1'>");
            html.append("<tr>")
                    .append("<th>Task ID</th>")
                    .append("<th>Task Name</th>");
            Map<String, Boolean> map = assignment.getTasks().get(0).conditions;
            for (String entry: map.keySet()) {
                html.append("<th> " + entry + " </th>");
            }
                html.append("<th>Max Score</th>")
                .append("<th>Mark</th>")
                .append("</tr>");

            for (Task task : assignment.getTasks()) {
                html.append("<tr>");
                html.append("<td>").append(task.id).append("</td>");
                html.append("<td>").append(task.name).append("</td>");
                Map<String, Boolean> taskMap = task.conditions;
                for (boolean value  : taskMap.values()) {
                    html.append("<td> ").append(value ? "+" : "-").append(" </td>");
                }
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
}
