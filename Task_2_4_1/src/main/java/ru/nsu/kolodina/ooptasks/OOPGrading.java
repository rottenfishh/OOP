package ru.nsu.kolodina.ooptasks;

/**
 * This class implements the grading logic for OOP assignments.
 * It calculates the score based on task performance, soft and hard deadlines,
 * and provides methods to calculate final marks and checkpoint marks.
 */
public class OOPGrading implements Grading {

    OOPCriteria criteria = new OOPCriteria();

    /**
     * Calculates the score for a student on a particular task.
     * The score depends on whether the task is built successfully,
     * and penalties are applied if the soft or hard deadlines are missed.
     *
     * @param student the student for whom the score is calculated.
     * @param repo    the repository where the task is located.
     * @param task    the task to be graded.
     * @return the calculated score for the task.
     */
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

    /**
     * Calculates the final mark for a student based on their overall score.
     * The final mark is determined by predefined score ranges.
     *
     * @param student the student for whom the final mark is calculated.
     * @return the final mark for the student (2, 3, 4, or 5).
     */
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

    /**
     * Calculates the mark for a student at a specific checkpoint.
     * The mark depends on how well the student's score compares to the required score at the checkpoint.
     *
     * @param student    the student for whom the checkpoint mark is calculated.
     * @param checkPoint the checkpoint with the required score.
     * @return the calculated mark for the checkpoint (2, 3, 4, or 5).
     */
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

    /**
     * Adds a bonus score to a specific task in an assignment.
     * The bonus score is also added to the student's overall score.
     *
     * @param assignment the assignment containing the task.
     * @param taskId     the ID of the task to which the bonus score will be added.
     * @param score      the bonus score to add.
     */
    public void giveBonusScore(Assignment assignment, String taskId, double score) {
        for (Task t : assignment.tasks) {
            if (taskId.equals(t.id)) {
                t.mark += score;
                break;
            }
        }
        assignment.student.score += score;
    }
}
