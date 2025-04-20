package ru.nsu.kolodina.ooptasks;

public class OOPGrading implements Grading {

    OOPCriteria criteria = new OOPCriteria();

    @Override
    public double calculateScore(Group.Student student, String repo, Task task) {
        if (task.buildOk) {
            task.mark = task.maxScore;
            if (!criteria.softDeadlineMeet(repo, task)) {
                task.mark -= 0.5;
            }
            if (!criteria.hardDeadlineMeet(repo, task)) {
                task.mark -= 0.5;
            }
        } else {
            task.mark = 0.0;
        }
        student.score += task.mark;
        return task.mark;
    }

    @Override
    public int calculateFinalMark(Group.Student student) {
        double score = student.getScore();
        if (score >= 5) {
            student.mark = 5;
        } else if (score >= 3) {
            student.mark = 4;
        } else if (score >= 2) {
            student.mark = 3;
        } else {
            student.mark = 2;
        }
        return student.mark;
    }

    @Override
    public int calculateCheckPoint(Group.Student student, CheckPoint checkPoint) {
        int requiredScore = checkPoint.requiredScore;
        double score = student.getScore();
        if (score >= requiredScore * 0.9) {
            student.mark = 5;
        } else if (score >= requiredScore * 0.75) {
            student.mark = 4;
        } else if (score >= requiredScore * 0.5) {
            student.mark = 3;
        } else {
            student.mark = 2;
        }
        return student.mark;
    }

    public void giveBonusScore(Assignment assignment, String taskId, double score) {
        for (Task t: assignment.tasks) {
            if (taskId.equals(t.id)) {
                t.mark += score;
                break;
            }
        }
        assignment.student.score += score;
    }
}
