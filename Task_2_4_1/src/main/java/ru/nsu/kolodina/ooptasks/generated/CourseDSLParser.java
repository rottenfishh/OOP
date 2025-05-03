// Generated from CourseDSL.g4 by ANTLR 4.13.1

package ru.nsu.kolodina.ooptasks;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CourseDSLParser extends Parser {
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, STRING = 29, INT = 30, WS = 31, COMMENTARY = 32;
    public static final int
            RULE_program = 0, RULE_importStmt = 1, RULE_statement = 2, RULE_taskBlock = 3,
            RULE_taskDecl = 4, RULE_taskBody = 5, RULE_groupBlock = 6, RULE_groupDecl = 7,
            RULE_studentDecl = 8, RULE_studentBody = 9, RULE_projectDecl = 10, RULE_assignmentBlock = 11,
            RULE_assignmentDecl = 12, RULE_assignedTask = 13, RULE_bonusStmt = 14,
            RULE_checkpointBlock = 15, RULE_checkpointDecl = 16, RULE_buildSystemDecl = 17,
            RULE_criteriesDecl = 18, RULE_gradingDecl = 19;
    public static final String[] ruleNames = makeRuleNames();
    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;
    public static final String _serializedATN =
            "\u0004\u0001 \u00c9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
                    "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
                    "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
                    "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
                    "\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
                    "\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
                    "\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0005\u0000+\b\u0000" +
                    "\n\u0000\f\u0000.\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001" +
                    "\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002" +
                    "\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002>\b\u0002" +
                    "\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003C\b\u0003\n\u0003\f\u0003" +
                    "F\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004" +
                    "\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004P\b\u0004\u000b\u0004" +
                    "\f\u0004Q\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005" +
                    "\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005" +
                    "^\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006c\b\u0006\n\u0006" +
                    "\f\u0006f\t\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001" +
                    "\u0007\u0001\u0007\u0004\u0007n\b\u0007\u000b\u0007\f\u0007o\u0001\u0007" +
                    "\u0001\u0007\u0001\b\u0001\b\u0001\b\u0004\bw\b\b\u000b\b\f\bx\u0001\b" +
                    "\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001" +
                    "\t\u0003\t\u0085\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
                    "\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0091\b\u000b\n\u000b" +
                    "\f\u000b\u0094\t\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f" +
                    "\u0001\f\u0004\f\u009c\b\f\u000b\f\f\f\u009d\u0001\f\u0001\f\u0001\r\u0001" +
                    "\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f" +
                    "\u00af\b\u000f\n\u000f\f\u000f\u00b2\t\u000f\u0001\u000f\u0001\u000f\u0001" +
                    "\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001" +
                    "\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001" +
                    "\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001" +
                    "\u0013\u0001\u0013\u0000\u0000\u0014\u0000\u0002\u0004\u0006\b\n\f\u000e" +
                    "\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&\u0000\u0000\u00cc" +
                    "\u0000,\u0001\u0000\u0000\u0000\u00021\u0001\u0000\u0000\u0000\u0004=" +
                    "\u0001\u0000\u0000\u0000\u0006?\u0001\u0000\u0000\u0000\bI\u0001\u0000" +
                    "\u0000\u0000\n]\u0001\u0000\u0000\u0000\f_\u0001\u0000\u0000\u0000\u000e" +
                    "i\u0001\u0000\u0000\u0000\u0010s\u0001\u0000\u0000\u0000\u0012\u0084\u0001" +
                    "\u0000\u0000\u0000\u0014\u0086\u0001\u0000\u0000\u0000\u0016\u008d\u0001" +
                    "\u0000\u0000\u0000\u0018\u0097\u0001\u0000\u0000\u0000\u001a\u00a1\u0001" +
                    "\u0000\u0000\u0000\u001c\u00a4\u0001\u0000\u0000\u0000\u001e\u00ab\u0001" +
                    "\u0000\u0000\u0000 \u00b5\u0001\u0000\u0000\u0000\"\u00be\u0001\u0000" +
                    "\u0000\u0000$\u00c2\u0001\u0000\u0000\u0000&\u00c5\u0001\u0000\u0000\u0000" +
                    "(+\u0003\u0002\u0001\u0000)+\u0003\u0004\u0002\u0000*(\u0001\u0000\u0000" +
                    "\u0000*)\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000\u0000,*\u0001\u0000" +
                    "\u0000\u0000,-\u0001\u0000\u0000\u0000-/\u0001\u0000\u0000\u0000.,\u0001" +
                    "\u0000\u0000\u0000/0\u0005\u0000\u0000\u00010\u0001\u0001\u0000\u0000" +
                    "\u000012\u0005\u0001\u0000\u000023\u0005\u001d\u0000\u00003\u0003\u0001" +
                    "\u0000\u0000\u00004>\u0003\u0002\u0001\u00005>\u0003\u0006\u0003\u0000" +
                    "6>\u0003\f\u0006\u00007>\u0003\u0016\u000b\u00008>\u0003\u001c\u000e\u0000" +
                    "9>\u0003\u001e\u000f\u0000:>\u0003&\u0013\u0000;>\u0003\"\u0011\u0000" +
                    "<>\u0003$\u0012\u0000=4\u0001\u0000\u0000\u0000=5\u0001\u0000\u0000\u0000" +
                    "=6\u0001\u0000\u0000\u0000=7\u0001\u0000\u0000\u0000=8\u0001\u0000\u0000" +
                    "\u0000=9\u0001\u0000\u0000\u0000=:\u0001\u0000\u0000\u0000=;\u0001\u0000" +
                    "\u0000\u0000=<\u0001\u0000\u0000\u0000>\u0005\u0001\u0000\u0000\u0000" +
                    "?@\u0005\u0002\u0000\u0000@D\u0005\u0003\u0000\u0000AC\u0003\b\u0004\u0000" +
                    "BA\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000" +
                    "\u0000DE\u0001\u0000\u0000\u0000EG\u0001\u0000\u0000\u0000FD\u0001\u0000" +
                    "\u0000\u0000GH\u0005\u0004\u0000\u0000H\u0007\u0001\u0000\u0000\u0000" +
                    "IJ\u0005\u0005\u0000\u0000JK\u0005\u001d\u0000\u0000KL\u0005\u0006\u0000" +
                    "\u0000LM\u0005\u001d\u0000\u0000MO\u0005\u0003\u0000\u0000NP\u0003\n\u0005" +
                    "\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QO\u0001\u0000" +
                    "\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0005" +
                    "\u0004\u0000\u0000T\t\u0001\u0000\u0000\u0000UV\u0005\u0007\u0000\u0000" +
                    "V^\u0005\u001d\u0000\u0000WX\u0005\b\u0000\u0000X^\u0005\u001d\u0000\u0000" +
                    "YZ\u0005\t\u0000\u0000Z^\u0005\u001d\u0000\u0000[\\\u0005\n\u0000\u0000" +
                    "\\^\u0005\u001d\u0000\u0000]U\u0001\u0000\u0000\u0000]W\u0001\u0000\u0000" +
                    "\u0000]Y\u0001\u0000\u0000\u0000][\u0001\u0000\u0000\u0000^\u000b\u0001" +
                    "\u0000\u0000\u0000_`\u0005\u000b\u0000\u0000`d\u0005\u0003\u0000\u0000" +
                    "ac\u0003\u000e\u0007\u0000ba\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000" +
                    "\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000eg\u0001\u0000" +
                    "\u0000\u0000fd\u0001\u0000\u0000\u0000gh\u0005\u0004\u0000\u0000h\r\u0001" +
                    "\u0000\u0000\u0000ij\u0005\f\u0000\u0000jk\u0005\u001d\u0000\u0000km\u0005" +
                    "\u0003\u0000\u0000ln\u0003\u0010\b\u0000ml\u0001\u0000\u0000\u0000no\u0001" +
                    "\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000" +
                    "pq\u0001\u0000\u0000\u0000qr\u0005\u0004\u0000\u0000r\u000f\u0001\u0000" +
                    "\u0000\u0000st\u0005\r\u0000\u0000tv\u0005\u0003\u0000\u0000uw\u0003\u0012" +
                    "\t\u0000vu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xv\u0001\u0000" +
                    "\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0005" +
                    "\u0004\u0000\u0000{\u0011\u0001\u0000\u0000\u0000|}\u0005\u0006\u0000" +
                    "\u0000}\u0085\u0005\u001d\u0000\u0000~\u007f\u0005\u000e\u0000\u0000\u007f" +
                    "\u0085\u0005\u001d\u0000\u0000\u0080\u0081\u0005\u000f\u0000\u0000\u0081" +
                    "\u0085\u0005\u001d\u0000\u0000\u0082\u0083\u0005\u0010\u0000\u0000\u0083" +
                    "\u0085\u0005\u001d\u0000\u0000\u0084|\u0001\u0000\u0000\u0000\u0084~\u0001" +
                    "\u0000\u0000\u0000\u0084\u0080\u0001\u0000\u0000\u0000\u0084\u0082\u0001" +
                    "\u0000\u0000\u0000\u0085\u0013\u0001\u0000\u0000\u0000\u0086\u0087\u0005" +
                    "\u0003\u0000\u0000\u0087\u0088\u0003\b\u0004\u0000\u0088\u0089\u0005\u0004" +
                    "\u0000\u0000\u0089\u008a\u0005\u0003\u0000\u0000\u008a\u008b\u0003\u0010" +
                    "\b\u0000\u008b\u008c\u0005\u0004\u0000\u0000\u008c\u0015\u0001\u0000\u0000" +
                    "\u0000\u008d\u008e\u0005\u0011\u0000\u0000\u008e\u0092\u0005\u0003\u0000" +
                    "\u0000\u008f\u0091\u0003\u0018\f\u0000\u0090\u008f\u0001\u0000\u0000\u0000" +
                    "\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000" +
                    "\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000" +
                    "\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0096\u0005\u0004\u0000\u0000" +
                    "\u0096\u0017\u0001\u0000\u0000\u0000\u0097\u0098\u0005\r\u0000\u0000\u0098" +
                    "\u0099\u0005\u001d\u0000\u0000\u0099\u009b\u0005\u0003\u0000\u0000\u009a" +
                    "\u009c\u0003\u001a\r\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009d" +
                    "\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009d\u009e" +
                    "\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a0" +
                    "\u0005\u0004\u0000\u0000\u00a0\u0019\u0001\u0000\u0000\u0000\u00a1\u00a2" +
                    "\u0005\u0012\u0000\u0000\u00a2\u00a3\u0005\u001d\u0000\u0000\u00a3\u001b" +
                    "\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\u0013\u0000\u0000\u00a5\u00a6" +
                    "\u0005\u001d\u0000\u0000\u00a6\u00a7\u0005\u0014\u0000\u0000\u00a7\u00a8" +
                    "\u0005\u001d\u0000\u0000\u00a8\u00a9\u0005\u0015\u0000\u0000\u00a9\u00aa" +
                    "\u0005\u001e\u0000\u0000\u00aa\u001d\u0001\u0000\u0000\u0000\u00ab\u00ac" +
                    "\u0005\u0016\u0000\u0000\u00ac\u00b0\u0005\u0003\u0000\u0000\u00ad\u00af" +
                    "\u0003 \u0010\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b2\u0001" +
                    "\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001" +
                    "\u0000\u0000\u0000\u00b1\u00b3\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001" +
                    "\u0000\u0000\u0000\u00b3\u00b4\u0005\u0004\u0000\u0000\u00b4\u001f\u0001" +
                    "\u0000\u0000\u0000\u00b5\u00b6\u0005\u0017\u0000\u0000\u00b6\u00b7\u0005" +
                    "\u001d\u0000\u0000\u00b7\u00b8\u0005\u0003\u0000\u0000\u00b8\u00b9\u0005" +
                    "\u0018\u0000\u0000\u00b9\u00ba\u0005\u001d\u0000\u0000\u00ba\u00bb\u0005" +
                    "\u0019\u0000\u0000\u00bb\u00bc\u0005\u001d\u0000\u0000\u00bc\u00bd\u0005" +
                    "\u0004\u0000\u0000\u00bd!\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u001a" +
                    "\u0000\u0000\u00bf\u00c0\u0005\u001d\u0000\u0000\u00c0\u00c1\u0005\u001d" +
                    "\u0000\u0000\u00c1#\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\u001b\u0000" +
                    "\u0000\u00c3\u00c4\u0005\u001d\u0000\u0000\u00c4%\u0001\u0000\u0000\u0000" +
                    "\u00c5\u00c6\u0005\u001c\u0000\u0000\u00c6\u00c7\u0005\u001d\u0000\u0000" +
                    "\u00c7\'\u0001\u0000\u0000\u0000\r*,=DQ]dox\u0084\u0092\u009d\u00b0";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    private static final String[] _LITERAL_NAMES = makeLiteralNames();
    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    static {
        RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
    }

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

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }

    public CourseDSLParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    private static String[] makeRuleNames() {
        return new String[]{
                "program", "importStmt", "statement", "taskBlock", "taskDecl", "taskBody",
                "groupBlock", "groupDecl", "studentDecl", "studentBody", "projectDecl",
                "assignmentBlock", "assignmentDecl", "assignedTask", "bonusStmt", "checkpointBlock",
                "checkpointDecl", "buildSystemDecl", "criteriesDecl", "gradingDecl"
        };
    }

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'import'", "'tasks'", "'{'", "'}'", "'task id'", "'name'", "'maxScore'",
                "'bonusScore'", "'softDeadline'", "'hardDeadline'", "'groups'", "'group'",
                "'student'", "'NickName'", "'repository'", "'buildtool'", "'assignments'",
                "'task'", "'bonus'", "'for'", "'+'", "'checkpoints'", "'checkpoint'",
                "'date'", "'score'", "'buildTool'", "'criteries'", "'grading'"
        };
    }

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, "STRING", "INT", "WS", "COMMENTARY"
        };
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
    public String getGrammarFileName() {
        return "CourseDSL.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public final ProgramContext program() throws RecognitionException {
        ProgramContext _localctx = new ProgramContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_program);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(44);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 474613766L) != 0)) {
                    {
                        setState(42);
                        _errHandler.sync(this);
                        switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                            case 1: {
                                setState(40);
                                importStmt();
                            }
                            break;
                            case 2: {
                                setState(41);
                                statement();
                            }
                            break;
                        }
                    }
                    setState(46);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(47);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ImportStmtContext importStmt() throws RecognitionException {
        ImportStmtContext _localctx = new ImportStmtContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_importStmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(49);
                match(T__0);
                setState(50);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StatementContext statement() throws RecognitionException {
        StatementContext _localctx = new StatementContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_statement);
        try {
            setState(61);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__0:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(52);
                    importStmt();
                }
                break;
                case T__1:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(53);
                    taskBlock();
                }
                break;
                case T__10:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(54);
                    groupBlock();
                }
                break;
                case T__16:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(55);
                    assignmentBlock();
                }
                break;
                case T__18:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(56);
                    bonusStmt();
                }
                break;
                case T__21:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(57);
                    checkpointBlock();
                }
                break;
                case T__27:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(58);
                    gradingDecl();
                }
                break;
                case T__25:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(59);
                    buildSystemDecl();
                }
                break;
                case T__26:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(60);
                    criteriesDecl();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TaskBlockContext taskBlock() throws RecognitionException {
        TaskBlockContext _localctx = new TaskBlockContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_taskBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(63);
                match(T__1);
                setState(64);
                match(T__2);
                setState(68);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__4) {
                    {
                        {
                            setState(65);
                            taskDecl();
                        }
                    }
                    setState(70);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(71);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TaskDeclContext taskDecl() throws RecognitionException {
        TaskDeclContext _localctx = new TaskDeclContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_taskDecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(73);
                match(T__4);
                setState(74);
                match(STRING);
                setState(75);
                match(T__5);
                setState(76);
                match(STRING);
                setState(77);
                match(T__2);
                setState(79);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(78);
                            taskBody();
                        }
                    }
                    setState(81);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0));
                setState(83);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final TaskBodyContext taskBody() throws RecognitionException {
        TaskBodyContext _localctx = new TaskBodyContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_taskBody);
        try {
            setState(93);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__6:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(85);
                    match(T__6);
                    setState(86);
                    match(STRING);
                }
                break;
                case T__7:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(87);
                    match(T__7);
                    setState(88);
                    match(STRING);
                }
                break;
                case T__8:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(89);
                    match(T__8);
                    setState(90);
                    match(STRING);
                }
                break;
                case T__9:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(91);
                    match(T__9);
                    setState(92);
                    match(STRING);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GroupBlockContext groupBlock() throws RecognitionException {
        GroupBlockContext _localctx = new GroupBlockContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_groupBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(95);
                match(T__10);
                setState(96);
                match(T__2);
                setState(100);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__11) {
                    {
                        {
                            setState(97);
                            groupDecl();
                        }
                    }
                    setState(102);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(103);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GroupDeclContext groupDecl() throws RecognitionException {
        GroupDeclContext _localctx = new GroupDeclContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_groupDecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(105);
                match(T__11);
                setState(106);
                match(STRING);
                setState(107);
                match(T__2);
                setState(109);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(108);
                            studentDecl();
                        }
                    }
                    setState(111);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == T__12);
                setState(113);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StudentDeclContext studentDecl() throws RecognitionException {
        StudentDeclContext _localctx = new StudentDeclContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_studentDecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(115);
                match(T__12);
                setState(116);
                match(T__2);
                setState(118);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(117);
                            studentBody();
                        }
                    }
                    setState(120);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 114752L) != 0));
                setState(122);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final StudentBodyContext studentBody() throws RecognitionException {
        StudentBodyContext _localctx = new StudentBodyContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_studentBody);
        try {
            setState(132);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__5:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(124);
                    match(T__5);
                    setState(125);
                    match(STRING);
                }
                break;
                case T__13:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(126);
                    match(T__13);
                    setState(127);
                    match(STRING);
                }
                break;
                case T__14:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(128);
                    match(T__14);
                    setState(129);
                    match(STRING);
                }
                break;
                case T__15:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(130);
                    match(T__15);
                    setState(131);
                    match(STRING);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final ProjectDeclContext projectDecl() throws RecognitionException {
        ProjectDeclContext _localctx = new ProjectDeclContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_projectDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(134);
                match(T__2);
                setState(135);
                taskDecl();
                setState(136);
                match(T__3);
                setState(137);
                match(T__2);
                setState(138);
                studentDecl();
                setState(139);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignmentBlockContext assignmentBlock() throws RecognitionException {
        AssignmentBlockContext _localctx = new AssignmentBlockContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_assignmentBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(141);
                match(T__16);
                setState(142);
                match(T__2);
                setState(146);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__12) {
                    {
                        {
                            setState(143);
                            assignmentDecl();
                        }
                    }
                    setState(148);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(149);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignmentDeclContext assignmentDecl() throws RecognitionException {
        AssignmentDeclContext _localctx = new AssignmentDeclContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_assignmentDecl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(151);
                match(T__12);
                setState(152);
                match(STRING);
                setState(153);
                match(T__2);
                setState(155);
                _errHandler.sync(this);
                _la = _input.LA(1);
                do {
                    {
                        {
                            setState(154);
                            assignedTask();
                        }
                    }
                    setState(157);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                } while (_la == T__17);
                setState(159);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final AssignedTaskContext assignedTask() throws RecognitionException {
        AssignedTaskContext _localctx = new AssignedTaskContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_assignedTask);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(161);
                match(T__17);
                setState(162);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BonusStmtContext bonusStmt() throws RecognitionException {
        BonusStmtContext _localctx = new BonusStmtContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_bonusStmt);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(164);
                match(T__18);
                setState(165);
                match(STRING);
                setState(166);
                match(T__19);
                setState(167);
                match(STRING);
                setState(168);
                match(T__20);
                setState(169);
                match(INT);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CheckpointBlockContext checkpointBlock() throws RecognitionException {
        CheckpointBlockContext _localctx = new CheckpointBlockContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_checkpointBlock);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(171);
                match(T__21);
                setState(172);
                match(T__2);
                setState(176);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__22) {
                    {
                        {
                            setState(173);
                            checkpointDecl();
                        }
                    }
                    setState(178);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(179);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CheckpointDeclContext checkpointDecl() throws RecognitionException {
        CheckpointDeclContext _localctx = new CheckpointDeclContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_checkpointDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(181);
                match(T__22);
                setState(182);
                match(STRING);
                setState(183);
                match(T__2);
                setState(184);
                match(T__23);
                setState(185);
                match(STRING);
                setState(186);
                match(T__24);
                setState(187);
                match(STRING);
                setState(188);
                match(T__3);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final BuildSystemDeclContext buildSystemDecl() throws RecognitionException {
        BuildSystemDeclContext _localctx = new BuildSystemDeclContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_buildSystemDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(190);
                match(T__25);
                setState(191);
                match(STRING);
                setState(192);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final CriteriesDeclContext criteriesDecl() throws RecognitionException {
        CriteriesDeclContext _localctx = new CriteriesDeclContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_criteriesDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(194);
                match(T__26);
                setState(195);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public final GradingDeclContext gradingDecl() throws RecognitionException {
        GradingDeclContext _localctx = new GradingDeclContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_gradingDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(197);
                match(T__27);
                setState(198);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProgramContext extends ParserRuleContext {
        public ProgramContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode EOF() {
            return getToken(CourseDSLParser.EOF, 0);
        }

        public List<ImportStmtContext> importStmt() {
            return getRuleContexts(ImportStmtContext.class);
        }

        public ImportStmtContext importStmt(int i) {
            return getRuleContext(ImportStmtContext.class, i);
        }

        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_program;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitProgram(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ImportStmtContext extends ParserRuleContext {
        public ImportStmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_importStmt;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitImportStmt(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StatementContext extends ParserRuleContext {
        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ImportStmtContext importStmt() {
            return getRuleContext(ImportStmtContext.class, 0);
        }

        public TaskBlockContext taskBlock() {
            return getRuleContext(TaskBlockContext.class, 0);
        }

        public GroupBlockContext groupBlock() {
            return getRuleContext(GroupBlockContext.class, 0);
        }

        public AssignmentBlockContext assignmentBlock() {
            return getRuleContext(AssignmentBlockContext.class, 0);
        }

        public BonusStmtContext bonusStmt() {
            return getRuleContext(BonusStmtContext.class, 0);
        }

        public CheckpointBlockContext checkpointBlock() {
            return getRuleContext(CheckpointBlockContext.class, 0);
        }

        public GradingDeclContext gradingDecl() {
            return getRuleContext(GradingDeclContext.class, 0);
        }

        public BuildSystemDeclContext buildSystemDecl() {
            return getRuleContext(BuildSystemDeclContext.class, 0);
        }

        public CriteriesDeclContext criteriesDecl() {
            return getRuleContext(CriteriesDeclContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitStatement(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TaskBlockContext extends ParserRuleContext {
        public TaskBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TaskDeclContext> taskDecl() {
            return getRuleContexts(TaskDeclContext.class);
        }

        public TaskDeclContext taskDecl(int i) {
            return getRuleContext(TaskDeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_taskBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitTaskBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TaskDeclContext extends ParserRuleContext {
        public TaskDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(CourseDSLParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(CourseDSLParser.STRING, i);
        }

        public List<TaskBodyContext> taskBody() {
            return getRuleContexts(TaskBodyContext.class);
        }

        public TaskBodyContext taskBody(int i) {
            return getRuleContext(TaskBodyContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_taskDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitTaskDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class TaskBodyContext extends ParserRuleContext {
        public TaskBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_taskBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitTaskBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GroupBlockContext extends ParserRuleContext {
        public GroupBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<GroupDeclContext> groupDecl() {
            return getRuleContexts(GroupDeclContext.class);
        }

        public GroupDeclContext groupDecl(int i) {
            return getRuleContext(GroupDeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_groupBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitGroupBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GroupDeclContext extends ParserRuleContext {
        public GroupDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        public List<StudentDeclContext> studentDecl() {
            return getRuleContexts(StudentDeclContext.class);
        }

        public StudentDeclContext studentDecl(int i) {
            return getRuleContext(StudentDeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_groupDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitGroupDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StudentDeclContext extends ParserRuleContext {
        public StudentDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<StudentBodyContext> studentBody() {
            return getRuleContexts(StudentBodyContext.class);
        }

        public StudentBodyContext studentBody(int i) {
            return getRuleContext(StudentBodyContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_studentDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitStudentDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StudentBodyContext extends ParserRuleContext {
        public StudentBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_studentBody;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitStudentBody(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProjectDeclContext extends ParserRuleContext {
        public ProjectDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TaskDeclContext taskDecl() {
            return getRuleContext(TaskDeclContext.class, 0);
        }

        public StudentDeclContext studentDecl() {
            return getRuleContext(StudentDeclContext.class, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_projectDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitProjectDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignmentBlockContext extends ParserRuleContext {
        public AssignmentBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<AssignmentDeclContext> assignmentDecl() {
            return getRuleContexts(AssignmentDeclContext.class);
        }

        public AssignmentDeclContext assignmentDecl(int i) {
            return getRuleContext(AssignmentDeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignmentBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitAssignmentBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignmentDeclContext extends ParserRuleContext {
        public AssignmentDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        public List<AssignedTaskContext> assignedTask() {
            return getRuleContexts(AssignedTaskContext.class);
        }

        public AssignedTaskContext assignedTask(int i) {
            return getRuleContext(AssignedTaskContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignmentDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitAssignmentDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class AssignedTaskContext extends ParserRuleContext {
        public AssignedTaskContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_assignedTask;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitAssignedTask(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BonusStmtContext extends ParserRuleContext {
        public BonusStmtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(CourseDSLParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(CourseDSLParser.STRING, i);
        }

        public TerminalNode INT() {
            return getToken(CourseDSLParser.INT, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bonusStmt;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitBonusStmt(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CheckpointBlockContext extends ParserRuleContext {
        public CheckpointBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<CheckpointDeclContext> checkpointDecl() {
            return getRuleContexts(CheckpointDeclContext.class);
        }

        public CheckpointDeclContext checkpointDecl(int i) {
            return getRuleContext(CheckpointDeclContext.class, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_checkpointBlock;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitCheckpointBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CheckpointDeclContext extends ParserRuleContext {
        public CheckpointDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(CourseDSLParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(CourseDSLParser.STRING, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_checkpointDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitCheckpointDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BuildSystemDeclContext extends ParserRuleContext {
        public BuildSystemDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public List<TerminalNode> STRING() {
            return getTokens(CourseDSLParser.STRING);
        }

        public TerminalNode STRING(int i) {
            return getToken(CourseDSLParser.STRING, i);
        }

        @Override
        public int getRuleIndex() {
            return RULE_buildSystemDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitBuildSystemDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CriteriesDeclContext extends ParserRuleContext {
        public CriteriesDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_criteriesDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitCriteriesDecl(this);
            else return visitor.visitChildren(this);
        }
    }

    @SuppressWarnings("CheckReturnValue")
    public static class GradingDeclContext extends ParserRuleContext {
        public GradingDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public TerminalNode STRING() {
            return getToken(CourseDSLParser.STRING, 0);
        }

        @Override
        public int getRuleIndex() {
            return RULE_gradingDecl;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof CourseDSLVisitor)
                return ((CourseDSLVisitor<? extends T>) visitor).visitGradingDecl(this);
            else return visitor.visitChildren(this);
        }
    }
}