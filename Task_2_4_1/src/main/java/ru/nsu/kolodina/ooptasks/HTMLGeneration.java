package ru.nsu.kolodina.ooptasks;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Utility class to generate HTML reports for assignments.
 */
public class HTMLGeneration {

    /**
     * Generates an HTML report for a list of assignments and writes it to the specified file path.
     *
     * @param assignments the list of assignments to include in the report
     * @param filePath the output file path where the HTML report will be written
     */
    public static void generateHTML(List<Assignment> assignments, String filePath) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Assignment Report</title></head><body>");
        for (Assignment assignment : assignments) {
            html.append("<h1> Group: ")
                    .append(assignment.student.groupName)
                    .append(" Student: ")
                    .append(assignment.studentName)
                    .append("</h1>");
            html.append("<h2>Student current score: ")
                    .append(assignment.student.score)
                    .append(" current mark: ")
                    .append(assignment.student.mark)
                    .append("</h2>");
            html.append("<table border='1'>");
            html.append("<tr>")
                    .append("<th>Task ID</th>")
                    .append("<th>Task Name</th>");

            Map<String, String> map = assignment.getTasks().get(0).conditions;
            for (String entry : map.keySet()) {
                html.append("<th>").append(entry).append("</th>");
            }
            html.append("<th>Score</th>")
                    .append("<th>Max Score</th>")
                    .append("</tr>");

            for (Task task : assignment.getTasks()) {
                html.append("<tr>");
                html.append("<td>").append(task.id).append("</td>");
                html.append("<td>").append(task.name).append("</td>");
                Map<String, String> taskMap = task.conditions;
                for (String value : taskMap.values()) {
                    html.append("<td>").append(value).append("</td>");
                }
                html.append("<td>").append(task.getMark()).append("</td>");
                html.append("<td>").append(task.maxScore).append("</td>");
                html.append("</tr>");
            }
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
