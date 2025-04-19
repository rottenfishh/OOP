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
    | buildSystemDecl
    | criteriesDecl
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
           | 'NickName' STRING
           | 'repository' STRING
           | 'buildtool' STRING;

projectDecl: '{' taskDecl '}' '{' studentDecl '}';

assignmentBlock: 'assignments' '{' assignmentDecl* '}';
assignmentDecl: 'student' STRING '{' assignedTask+ '}';
assignedTask: 'task' STRING;

bonusStmt: 'bonus' STRING 'for' STRING '+' INT;

checkpointBlock: 'checkpoints' '{' checkpointDecl* '}';
checkpointDecl: 'checkpoint' STRING '{' 'date' STRING 'score' STRING'}';

buildSystemDecl: 'buildTool' STRING STRING;

criteriesDecl: 'criteries' STRING;

gradingDecl: 'grading' STRING;

STRING : '"' .*? '"';
INT    : [0-9]+;
WS     : [ \t\r\n]+ -> skip;
COMMENTARY : '//' .*? '//';