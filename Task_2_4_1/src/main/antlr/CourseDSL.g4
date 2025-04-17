grammar CourseDSL;

@header {
package ru.nsu.kolodina.ooptasks;
}

program: (importStmt | statement)* EOF;

importStmt: 'import' STRING;
statement
    : importStmt
    | taskBlock
    | groupBlock
    | assignmentBlock
    | bonusStmt
    | checkpointBlock
    | gradingDecl
    | buildtoolBlock
    ;

taskBlock: 'tasks' '{' taskDecl* '}';
taskDecl: 'task id' STRING 'name' STRING '{' taskBody+ '}';
taskBody: 'maxScore' STRING
        | 'softDeadline' STRING
        | 'hardDeadline' STRING;

groupBlock: 'groups' '{' groupDecl* '}';
groupDecl: 'group' STRING '{' studentDecl+ '}';
studentDecl: 'student' '{' studentBody+ '}';
studentBody: 'name' STRING
           | 'githubNickName' STRING
           | 'repository' STRING;

projectDecl: '{' taskDecl '}' '{' studentDecl '}';

assignmentBlock: 'assignments' '{' assignmentDecl* '}';
assignmentDecl: 'student' STRING '{' assignedTask+ '}';
assignedTask: 'task' STRING;

bonusStmt: 'bonus' STRING 'for' STRING '+' INT;

checkpointBlock: 'checkpoints' '{' checkpointDecl* '}';
checkpointDecl: 'checkpoint' STRING '{' 'date' STRING '}';

gradingDecl: 'grading' '{' gradingRule+ '}';
gradingRule: '3' INT
           | '4' INT
           | '5' INT;
buildtoolBlock: 'build tool' STRING '{' buildRules+ '}';
buildRules: 'build' STRING
          | 'test' STRING
          | 'checkstyle' STRING
          | 'docgeneration' STRING;
STRING : '"' .*? '"';
INT    : [0-9]+;
WS     : [ \t\r\n]+ -> skip;
