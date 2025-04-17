import "task-library"

tasks {
    task id "task1" name "Intro" {
        maxScore 100
        softDeadline "2025-05-01"
        hardDeadline "2025-05-10"
    }
    task id "task2" name "Advanced" {
        maxScore 200
        softDeadline "2025-06-01"
        hardDeadline "2025-06-10"
    }
}

groups {
    group "G1" {
        student {
            name "Alice"
            githubNickName "aliceGH"
            repository "https://github.com/alice/repo"
        }
        student {
            name "Bob"
            githubNickName "bobGH"
            repository "https://github.com/bob/repo"
        }
    }
}

assignments {
    student "aliceGH" {
        task "task1"
        task "task2"
    }
    student "bobGH" {
        task "task2"
    }
}

bonus "Extra Credit" for "task2" + 50

checkpoints {
    checkpoint "midterm" {
        date "2025-05-15"
    }
    checkpoint "final" {
        date "2025-06-15"
    }
}

grading {
    3 60
    4 80
    5 100
}
