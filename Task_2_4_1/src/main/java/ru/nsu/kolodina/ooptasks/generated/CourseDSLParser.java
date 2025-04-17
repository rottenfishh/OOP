// Generated from CourseDSL.g4 by ANTLR 4.13.1

package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CourseDSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, STRING=27, INT=28, WS=29;
	public static final int
		RULE_program = 0, RULE_importStmt = 1, RULE_statement = 2, RULE_taskBlock = 3, 
		RULE_taskDecl = 4, RULE_taskBody = 5, RULE_groupBlock = 6, RULE_groupDecl = 7, 
		RULE_studentDecl = 8, RULE_studentBody = 9, RULE_projectDecl = 10, RULE_assignmentBlock = 11, 
		RULE_assignmentDecl = 12, RULE_assignedTask = 13, RULE_bonusStmt = 14, 
		RULE_checkpointBlock = 15, RULE_checkpointDecl = 16, RULE_gradingDecl = 17, 
		RULE_gradingRule = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "importStmt", "statement", "taskBlock", "taskDecl", "taskBody", 
			"groupBlock", "groupDecl", "studentDecl", "studentBody", "projectDecl", 
			"assignmentBlock", "assignmentDecl", "assignedTask", "bonusStmt", "checkpointBlock", 
			"checkpointDecl", "gradingDecl", "gradingRule"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'tasks'", "'{'", "'}'", "'task id'", "'name'", "'maxScore'", 
			"'softDeadline'", "'hardDeadline'", "'groups'", "'group'", "'student'", 
			"'githubNickName'", "'repository'", "'assignments'", "'task'", "'bonus'", 
			"'for'", "'+'", "'checkpoints'", "'checkpoint'", "'date'", "'grading'", 
			"'3'", "'4'", "'5'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "STRING", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CourseDSL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CourseDSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CourseDSLParser.EOF, 0); }
		public List<ImportStmtContext> importStmt() {
			return getRuleContexts(ImportStmtContext.class);
		}
		public ImportStmtContext importStmt(int i) {
			return getRuleContext(ImportStmtContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9602054L) != 0)) {
				{
				setState(40);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(38);
					importStmt();
					}
					break;
				case 2:
					{
					setState(39);
					statement();
					}
					break;
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportStmtContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public ImportStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitImportStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStmtContext importStmt() throws RecognitionException {
		ImportStmtContext _localctx = new ImportStmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__0);
			setState(48);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public ImportStmtContext importStmt() {
			return getRuleContext(ImportStmtContext.class,0);
		}
		public TaskBlockContext taskBlock() {
			return getRuleContext(TaskBlockContext.class,0);
		}
		public GroupBlockContext groupBlock() {
			return getRuleContext(GroupBlockContext.class,0);
		}
		public AssignmentBlockContext assignmentBlock() {
			return getRuleContext(AssignmentBlockContext.class,0);
		}
		public BonusStmtContext bonusStmt() {
			return getRuleContext(BonusStmtContext.class,0);
		}
		public CheckpointBlockContext checkpointBlock() {
			return getRuleContext(CheckpointBlockContext.class,0);
		}
		public GradingDeclContext gradingDecl() {
			return getRuleContext(GradingDeclContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				importStmt();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				taskBlock();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				groupBlock();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(53);
				assignmentBlock();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 5);
				{
				setState(54);
				bonusStmt();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 6);
				{
				setState(55);
				checkpointBlock();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 7);
				{
				setState(56);
				gradingDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TaskBlockContext extends ParserRuleContext {
		public List<TaskDeclContext> taskDecl() {
			return getRuleContexts(TaskDeclContext.class);
		}
		public TaskDeclContext taskDecl(int i) {
			return getRuleContext(TaskDeclContext.class,i);
		}
		public TaskBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taskBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitTaskBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaskBlockContext taskBlock() throws RecognitionException {
		TaskBlockContext _localctx = new TaskBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_taskBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__1);
			setState(60);
			match(T__2);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(61);
				taskDecl();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TaskDeclContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(CourseDSLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CourseDSLParser.STRING, i);
		}
		public List<TaskBodyContext> taskBody() {
			return getRuleContexts(TaskBodyContext.class);
		}
		public TaskBodyContext taskBody(int i) {
			return getRuleContext(TaskBodyContext.class,i);
		}
		public TaskDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taskDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitTaskDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaskDeclContext taskDecl() throws RecognitionException {
		TaskDeclContext _localctx = new TaskDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_taskDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__4);
			setState(70);
			match(STRING);
			setState(71);
			match(T__5);
			setState(72);
			match(STRING);
			setState(73);
			match(T__2);
			setState(75); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				taskBody();
				}
				}
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 896L) != 0) );
			setState(79);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TaskBodyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public TaskBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_taskBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitTaskBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TaskBodyContext taskBody() throws RecognitionException {
		TaskBodyContext _localctx = new TaskBodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_taskBody);
		try {
			setState(87);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(T__6);
				setState(82);
				match(STRING);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				match(T__7);
				setState(84);
				match(STRING);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(85);
				match(T__8);
				setState(86);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupBlockContext extends ParserRuleContext {
		public List<GroupDeclContext> groupDecl() {
			return getRuleContexts(GroupDeclContext.class);
		}
		public GroupDeclContext groupDecl(int i) {
			return getRuleContext(GroupDeclContext.class,i);
		}
		public GroupBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitGroupBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupBlockContext groupBlock() throws RecognitionException {
		GroupBlockContext _localctx = new GroupBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_groupBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__9);
			setState(90);
			match(T__2);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(91);
				groupDecl();
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupDeclContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public List<StudentDeclContext> studentDecl() {
			return getRuleContexts(StudentDeclContext.class);
		}
		public StudentDeclContext studentDecl(int i) {
			return getRuleContext(StudentDeclContext.class,i);
		}
		public GroupDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitGroupDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupDeclContext groupDecl() throws RecognitionException {
		GroupDeclContext _localctx = new GroupDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_groupDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(T__10);
			setState(100);
			match(STRING);
			setState(101);
			match(T__2);
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102);
				studentDecl();
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 );
			setState(107);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StudentDeclContext extends ParserRuleContext {
		public List<StudentBodyContext> studentBody() {
			return getRuleContexts(StudentBodyContext.class);
		}
		public StudentBodyContext studentBody(int i) {
			return getRuleContext(StudentBodyContext.class,i);
		}
		public StudentDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_studentDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitStudentDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StudentDeclContext studentDecl() throws RecognitionException {
		StudentDeclContext _localctx = new StudentDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_studentDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__11);
			setState(110);
			match(T__2);
			setState(112); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(111);
				studentBody();
				}
				}
				setState(114); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 24640L) != 0) );
			setState(116);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StudentBodyContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public StudentBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_studentBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitStudentBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StudentBodyContext studentBody() throws RecognitionException {
		StudentBodyContext _localctx = new StudentBodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_studentBody);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__5);
				setState(119);
				match(STRING);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				match(T__12);
				setState(121);
				match(STRING);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				match(T__13);
				setState(123);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProjectDeclContext extends ParserRuleContext {
		public TaskDeclContext taskDecl() {
			return getRuleContext(TaskDeclContext.class,0);
		}
		public StudentDeclContext studentDecl() {
			return getRuleContext(StudentDeclContext.class,0);
		}
		public ProjectDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitProjectDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectDeclContext projectDecl() throws RecognitionException {
		ProjectDeclContext _localctx = new ProjectDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_projectDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__2);
			setState(127);
			taskDecl();
			setState(128);
			match(T__3);
			setState(129);
			match(T__2);
			setState(130);
			studentDecl();
			setState(131);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentBlockContext extends ParserRuleContext {
		public List<AssignmentDeclContext> assignmentDecl() {
			return getRuleContexts(AssignmentDeclContext.class);
		}
		public AssignmentDeclContext assignmentDecl(int i) {
			return getRuleContext(AssignmentDeclContext.class,i);
		}
		public AssignmentBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitAssignmentBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentBlockContext assignmentBlock() throws RecognitionException {
		AssignmentBlockContext _localctx = new AssignmentBlockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_assignmentBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(T__14);
			setState(134);
			match(T__2);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(135);
				assignmentDecl();
				}
				}
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(141);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentDeclContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public List<AssignedTaskContext> assignedTask() {
			return getRuleContexts(AssignedTaskContext.class);
		}
		public AssignedTaskContext assignedTask(int i) {
			return getRuleContext(AssignedTaskContext.class,i);
		}
		public AssignmentDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitAssignmentDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentDeclContext assignmentDecl() throws RecognitionException {
		AssignmentDeclContext _localctx = new AssignmentDeclContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignmentDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__11);
			setState(144);
			match(STRING);
			setState(145);
			match(T__2);
			setState(147); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(146);
				assignedTask();
				}
				}
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__15 );
			setState(151);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignedTaskContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CourseDSLParser.STRING, 0); }
		public AssignedTaskContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignedTask; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitAssignedTask(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignedTaskContext assignedTask() throws RecognitionException {
		AssignedTaskContext _localctx = new AssignedTaskContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignedTask);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(T__15);
			setState(154);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BonusStmtContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(CourseDSLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CourseDSLParser.STRING, i);
		}
		public TerminalNode INT() { return getToken(CourseDSLParser.INT, 0); }
		public BonusStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bonusStmt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitBonusStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BonusStmtContext bonusStmt() throws RecognitionException {
		BonusStmtContext _localctx = new BonusStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bonusStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__16);
			setState(157);
			match(STRING);
			setState(158);
			match(T__17);
			setState(159);
			match(STRING);
			setState(160);
			match(T__18);
			setState(161);
			match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CheckpointBlockContext extends ParserRuleContext {
		public List<CheckpointDeclContext> checkpointDecl() {
			return getRuleContexts(CheckpointDeclContext.class);
		}
		public CheckpointDeclContext checkpointDecl(int i) {
			return getRuleContext(CheckpointDeclContext.class,i);
		}
		public CheckpointBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkpointBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitCheckpointBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckpointBlockContext checkpointBlock() throws RecognitionException {
		CheckpointBlockContext _localctx = new CheckpointBlockContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_checkpointBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__19);
			setState(164);
			match(T__2);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__20) {
				{
				{
				setState(165);
				checkpointDecl();
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CheckpointDeclContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(CourseDSLParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(CourseDSLParser.STRING, i);
		}
		public CheckpointDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkpointDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitCheckpointDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CheckpointDeclContext checkpointDecl() throws RecognitionException {
		CheckpointDeclContext _localctx = new CheckpointDeclContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_checkpointDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__20);
			setState(174);
			match(STRING);
			setState(175);
			match(T__2);
			setState(176);
			match(T__21);
			setState(177);
			match(STRING);
			setState(178);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GradingDeclContext extends ParserRuleContext {
		public List<GradingRuleContext> gradingRule() {
			return getRuleContexts(GradingRuleContext.class);
		}
		public GradingRuleContext gradingRule(int i) {
			return getRuleContext(GradingRuleContext.class,i);
		}
		public GradingDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradingDecl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitGradingDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradingDeclContext gradingDecl() throws RecognitionException {
		GradingDeclContext _localctx = new GradingDeclContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_gradingDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__22);
			setState(181);
			match(T__2);
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				gradingRule();
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 117440512L) != 0) );
			setState(187);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GradingRuleContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CourseDSLParser.INT, 0); }
		public GradingRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gradingRule; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CourseDSLVisitor ) return ((CourseDSLVisitor<? extends T>)visitor).visitGradingRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradingRuleContext gradingRule() throws RecognitionException {
		GradingRuleContext _localctx = new GradingRuleContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_gradingRule);
		try {
			setState(195);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(T__23);
				setState(190);
				match(INT);
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(T__24);
				setState(192);
				match(INT);
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				match(T__25);
				setState(194);
				match(INT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001d\u00c6\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0001\u0000\u0001\u0000\u0005\u0000)\b\u0000\n\u0000\f\u0000,\t"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002:\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005"+
		"\u0003?\b\u0003\n\u0003\f\u0003B\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004"+
		"\u0004L\b\u0004\u000b\u0004\f\u0004M\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005"+
		"X\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006]\b\u0006\n\u0006"+
		"\f\u0006`\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0004\u0007h\b\u0007\u000b\u0007\f\u0007i\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0004\bq\b\b\u000b\b\f\br\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t}\b\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u0089\b\u000b\n\u000b\f\u000b\u008c\t\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0004\f\u0094"+
		"\b\f\u000b\f\f\f\u0095\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00a7\b\u000f\n\u000f"+
		"\f\u000f\u00aa\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u00b8\b\u0011\u000b\u0011\f\u0011"+
		"\u00b9\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00c4\b\u0012\u0001\u0012\u0000"+
		"\u0000\u0013\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$\u0000\u0000\u00c9\u0000*\u0001\u0000\u0000"+
		"\u0000\u0002/\u0001\u0000\u0000\u0000\u00049\u0001\u0000\u0000\u0000\u0006"+
		";\u0001\u0000\u0000\u0000\bE\u0001\u0000\u0000\u0000\nW\u0001\u0000\u0000"+
		"\u0000\fY\u0001\u0000\u0000\u0000\u000ec\u0001\u0000\u0000\u0000\u0010"+
		"m\u0001\u0000\u0000\u0000\u0012|\u0001\u0000\u0000\u0000\u0014~\u0001"+
		"\u0000\u0000\u0000\u0016\u0085\u0001\u0000\u0000\u0000\u0018\u008f\u0001"+
		"\u0000\u0000\u0000\u001a\u0099\u0001\u0000\u0000\u0000\u001c\u009c\u0001"+
		"\u0000\u0000\u0000\u001e\u00a3\u0001\u0000\u0000\u0000 \u00ad\u0001\u0000"+
		"\u0000\u0000\"\u00b4\u0001\u0000\u0000\u0000$\u00c3\u0001\u0000\u0000"+
		"\u0000&)\u0003\u0002\u0001\u0000\')\u0003\u0004\u0002\u0000(&\u0001\u0000"+
		"\u0000\u0000(\'\u0001\u0000\u0000\u0000),\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000*+\u0001\u0000\u0000\u0000+-\u0001\u0000\u0000\u0000"+
		",*\u0001\u0000\u0000\u0000-.\u0005\u0000\u0000\u0001.\u0001\u0001\u0000"+
		"\u0000\u0000/0\u0005\u0001\u0000\u000001\u0005\u001b\u0000\u00001\u0003"+
		"\u0001\u0000\u0000\u00002:\u0003\u0002\u0001\u00003:\u0003\u0006\u0003"+
		"\u00004:\u0003\f\u0006\u00005:\u0003\u0016\u000b\u00006:\u0003\u001c\u000e"+
		"\u00007:\u0003\u001e\u000f\u00008:\u0003\"\u0011\u000092\u0001\u0000\u0000"+
		"\u000093\u0001\u0000\u0000\u000094\u0001\u0000\u0000\u000095\u0001\u0000"+
		"\u0000\u000096\u0001\u0000\u0000\u000097\u0001\u0000\u0000\u000098\u0001"+
		"\u0000\u0000\u0000:\u0005\u0001\u0000\u0000\u0000;<\u0005\u0002\u0000"+
		"\u0000<@\u0005\u0003\u0000\u0000=?\u0003\b\u0004\u0000>=\u0001\u0000\u0000"+
		"\u0000?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000"+
		"\u0000\u0000AC\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0005"+
		"\u0004\u0000\u0000D\u0007\u0001\u0000\u0000\u0000EF\u0005\u0005\u0000"+
		"\u0000FG\u0005\u001b\u0000\u0000GH\u0005\u0006\u0000\u0000HI\u0005\u001b"+
		"\u0000\u0000IK\u0005\u0003\u0000\u0000JL\u0003\n\u0005\u0000KJ\u0001\u0000"+
		"\u0000\u0000LM\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OP\u0005\u0004\u0000\u0000"+
		"P\t\u0001\u0000\u0000\u0000QR\u0005\u0007\u0000\u0000RX\u0005\u001b\u0000"+
		"\u0000ST\u0005\b\u0000\u0000TX\u0005\u001b\u0000\u0000UV\u0005\t\u0000"+
		"\u0000VX\u0005\u001b\u0000\u0000WQ\u0001\u0000\u0000\u0000WS\u0001\u0000"+
		"\u0000\u0000WU\u0001\u0000\u0000\u0000X\u000b\u0001\u0000\u0000\u0000"+
		"YZ\u0005\n\u0000\u0000Z^\u0005\u0003\u0000\u0000[]\u0003\u000e\u0007\u0000"+
		"\\[\u0001\u0000\u0000\u0000]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000"+
		"\u0000^_\u0001\u0000\u0000\u0000_a\u0001\u0000\u0000\u0000`^\u0001\u0000"+
		"\u0000\u0000ab\u0005\u0004\u0000\u0000b\r\u0001\u0000\u0000\u0000cd\u0005"+
		"\u000b\u0000\u0000de\u0005\u001b\u0000\u0000eg\u0005\u0003\u0000\u0000"+
		"fh\u0003\u0010\b\u0000gf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000"+
		"ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000kl\u0005\u0004\u0000\u0000l\u000f\u0001\u0000\u0000\u0000mn\u0005"+
		"\f\u0000\u0000np\u0005\u0003\u0000\u0000oq\u0003\u0012\t\u0000po\u0001"+
		"\u0000\u0000\u0000qr\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000"+
		"rs\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0005\u0004\u0000"+
		"\u0000u\u0011\u0001\u0000\u0000\u0000vw\u0005\u0006\u0000\u0000w}\u0005"+
		"\u001b\u0000\u0000xy\u0005\r\u0000\u0000y}\u0005\u001b\u0000\u0000z{\u0005"+
		"\u000e\u0000\u0000{}\u0005\u001b\u0000\u0000|v\u0001\u0000\u0000\u0000"+
		"|x\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000}\u0013\u0001\u0000"+
		"\u0000\u0000~\u007f\u0005\u0003\u0000\u0000\u007f\u0080\u0003\b\u0004"+
		"\u0000\u0080\u0081\u0005\u0004\u0000\u0000\u0081\u0082\u0005\u0003\u0000"+
		"\u0000\u0082\u0083\u0003\u0010\b\u0000\u0083\u0084\u0005\u0004\u0000\u0000"+
		"\u0084\u0015\u0001\u0000\u0000\u0000\u0085\u0086\u0005\u000f\u0000\u0000"+
		"\u0086\u008a\u0005\u0003\u0000\u0000\u0087\u0089\u0003\u0018\f\u0000\u0088"+
		"\u0087\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000\u0000\u0000\u008a"+
		"\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b"+
		"\u008d\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008d"+
		"\u008e\u0005\u0004\u0000\u0000\u008e\u0017\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0005\f\u0000\u0000\u0090\u0091\u0005\u001b\u0000\u0000\u0091\u0093"+
		"\u0005\u0003\u0000\u0000\u0092\u0094\u0003\u001a\r\u0000\u0093\u0092\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0005\u0004\u0000\u0000\u0098\u0019\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0005\u0010\u0000\u0000\u009a\u009b\u0005"+
		"\u001b\u0000\u0000\u009b\u001b\u0001\u0000\u0000\u0000\u009c\u009d\u0005"+
		"\u0011\u0000\u0000\u009d\u009e\u0005\u001b\u0000\u0000\u009e\u009f\u0005"+
		"\u0012\u0000\u0000\u009f\u00a0\u0005\u001b\u0000\u0000\u00a0\u00a1\u0005"+
		"\u0013\u0000\u0000\u00a1\u00a2\u0005\u001c\u0000\u0000\u00a2\u001d\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0005\u0014\u0000\u0000\u00a4\u00a8\u0005"+
		"\u0003\u0000\u0000\u00a5\u00a7\u0003 \u0010\u0000\u00a6\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a7\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ab\u0001\u0000"+
		"\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005\u0004"+
		"\u0000\u0000\u00ac\u001f\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005\u0015"+
		"\u0000\u0000\u00ae\u00af\u0005\u001b\u0000\u0000\u00af\u00b0\u0005\u0003"+
		"\u0000\u0000\u00b0\u00b1\u0005\u0016\u0000\u0000\u00b1\u00b2\u0005\u001b"+
		"\u0000\u0000\u00b2\u00b3\u0005\u0004\u0000\u0000\u00b3!\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b5\u0005\u0017\u0000\u0000\u00b5\u00b7\u0005\u0003\u0000"+
		"\u0000\u00b6\u00b8\u0003$\u0012\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005\u0004\u0000\u0000\u00bc#\u0001\u0000\u0000\u0000\u00bd"+
		"\u00be\u0005\u0018\u0000\u0000\u00be\u00c4\u0005\u001c\u0000\u0000\u00bf"+
		"\u00c0\u0005\u0019\u0000\u0000\u00c0\u00c4\u0005\u001c\u0000\u0000\u00c1"+
		"\u00c2\u0005\u001a\u0000\u0000\u00c2\u00c4\u0005\u001c\u0000\u0000\u00c3"+
		"\u00bd\u0001\u0000\u0000\u0000\u00c3\u00bf\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c4%\u0001\u0000\u0000\u0000\u000f(*"+
		"9@MW^ir|\u008a\u0095\u00a8\u00b9\u00c3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}