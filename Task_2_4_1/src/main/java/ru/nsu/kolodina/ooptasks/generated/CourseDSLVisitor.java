// Generated from CourseDSL.g4 by ANTLR 4.13.1

package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CourseDSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CourseDSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CourseDSLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#importStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportStmt(CourseDSLParser.ImportStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CourseDSLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#taskBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskBlock(CourseDSLParser.TaskBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#taskDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskDecl(CourseDSLParser.TaskDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#taskBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaskBody(CourseDSLParser.TaskBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#groupBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupBlock(CourseDSLParser.GroupBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#groupDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupDecl(CourseDSLParser.GroupDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#studentDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStudentDecl(CourseDSLParser.StudentDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#studentBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStudentBody(CourseDSLParser.StudentBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#projectDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProjectDecl(CourseDSLParser.ProjectDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#assignmentBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentBlock(CourseDSLParser.AssignmentBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#assignmentDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentDecl(CourseDSLParser.AssignmentDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#assignedTask}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignedTask(CourseDSLParser.AssignedTaskContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#bonusStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBonusStmt(CourseDSLParser.BonusStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#checkpointBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckpointBlock(CourseDSLParser.CheckpointBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#checkpointDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckpointDecl(CourseDSLParser.CheckpointDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#gradingDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradingDecl(CourseDSLParser.GradingDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link CourseDSLParser#gradingRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGradingRule(CourseDSLParser.GradingRuleContext ctx);
}