package statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.BooleanValueContext;
import antlr.ExprParser.ConjunctionContext;
import antlr.ExprParser.DeclarationContext;
import antlr.ExprParser.DisjunctionContext;
import antlr.ExprParser.EquivalenceContext;
import antlr.ExprParser.ImplicationContext;
import antlr.ExprParser.InitializationContext;
import antlr.ExprParser.NegationContext;
import antlr.ExprParser.ParenthesesContext;
import antlr.ExprParser.VariableContext;
import antlr.ExprParser.VerificationContext;
import statement.declaration.VariableDeclaration;
import statement.declaration.VariableInitialization;
import statement.expression.BooleanValue;
import statement.expression.Conjunction;
import statement.expression.Disjunction;
import statement.expression.Equivalence;
import statement.expression.Expression;
import statement.expression.Implication;
import statement.expression.Negation;
import statement.expression.Parantheses;
import statement.expression.Variable;
import statement.verification.Verification;

public class AntlrToExpression extends ExprBaseVisitor<Statement> {
	/*
	 * Given that all visit_* methods are called in a top-down fashion, we can
	 * be sure that the order in which we add declare variables into the list
	 * `vars` is identical to how they are declared in the input program.
	 */
	private List<String> vars; // stores all the variables declared in the
								// program so far
	private Map<String, Boolean> values; // Symbol table for storing values of
											// variables
	private List<String> semanticErrors; // 1. duplicate declaration 2.
											// reference to undeclared variable
	// Note that semantic errors are different from syntax errors.

	/*
	 * Constructor
	 * 
	 * @param semanticErros list of semantic errors observed in the input file
	 */
	public AntlrToExpression(List<String> semanticErrors) {
		vars = new ArrayList<>();
		values = new HashMap<String, Boolean>();
		this.semanticErrors = semanticErrors;
	}

	/*
	 * getter function to retrieve list of variables defined in the input file
	 * 
	 * @param list of strings representing the variable names in the input
	 * program
	 */
	public List<String> getVars() {
		return vars;
	}

	/*
	 * visitor function for DeclarationContext
	 * 
	 * @param ctx representing DeclarationContext
	 */
	@Override
	public Statement visitDeclaration(DeclarationContext ctx) {
		// ID() is a method generated to correspond to the token ID in the
		// source grammar.
		Token idToken = ctx.ID().getSymbol(); // equivalent to:
												// ctx.getChild(0).getSymbol()
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;
		String id = ctx.ID().getText();

		// Maintaining the vars list for semantic error reporting
		if (values.containsKey(id)) {
			semanticErrors.add("Error: variable " + id + " already declared (" + line + ", " + column + ")");
		} else {
			vars.add(id);
			values.put(id, null);
		}

		String type = ctx.getChild(1).getText();
		return new VariableDeclaration(id, type);
	}

	/*
	 * visitor function for DeclarationContext
	 * 
	 * @param ctx representing DeclarationContext
	 */
	@Override
	public Statement visitInitialization(InitializationContext ctx) {
		// ID() is a method generated to correspond to the token ID in the
		// source grammar.
		Token idToken = ctx.ID().getSymbol(); // equivalent to:
												// ctx.getChild(0).getSymbol()
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;
		String id = ctx.ID().getText();

		String type = ctx.VARIABLE().getText();
		boolean value = Boolean.parseBoolean(ctx.BOOL_VALUE().getText());

		// Maintaining the vars list for semantic error reporting
		if (values.containsKey(id)) {
			semanticErrors.add("Error: variable " + id + " already declared (" + line + ", " + column + ")");
		} else {
			vars.add(id);
			values.put(id, value);
		}
		return new VariableInitialization(id, type, value);
	}

	/*
	 * visitor function for DeclarationContext
	 * 
	 * @param ctx representing DeclarationContext
	 */
	@Override
	public Expression visitDisjunction(DisjunctionContext ctx) {
		// it must be an expression which is defined by grammer
		Expression left = (Expression) visit(ctx.getChild(0));
		Expression right = (Expression) visit(ctx.getChild(2));
		return new Disjunction(left, right);
	}

	/*
	 * visitor function for VariableContext
	 * 
	 * @param ctx representing VariableContext
	 */
	@Override
	public Expression visitVariable(VariableContext ctx) {
		Token idToken = ctx.ID().getSymbol();
		int line = idToken.getLine();
		int column = idToken.getCharPositionInLine() + 1;
		String id = ctx.getChild(0).getText();
		Boolean value = null;

		// Maintaining the vars list for semantic error reporting
		if (!values.containsKey(id)) {
			semanticErrors.add("Error: variable " + id + " not declared (" + line + ", " + column + ")");
		} else {
			value = values.get(id);
		}

		return new Variable(id, value);
	}

	/*
	 * visitor function for NegationContext
	 * 
	 * @param ctx representing NegationContext
	 */
	@Override
	public Expression visitNegation(NegationContext ctx) {
		Expression expr = (Expression) visit(ctx.getChild(1));
		return new Negation(expr);
	}

	/*
	 * visitor function for EquivalenceContext
	 * 
	 * @param ctx representing EquivalenceContext
	 */
	@Override
	public Expression visitEquivalence(EquivalenceContext ctx) {
		Expression left = (Expression) visit(ctx.getChild(0));
		Expression right = (Expression) visit(ctx.getChild(2));
		return new Equivalence(left, right);
	}

	/*
	 * visitor function for BooleanValueContext
	 * 
	 * @param ctx representing BooleanValueContext
	 */
	@Override
	public Expression visitBooleanValue(BooleanValueContext ctx) {
		String boolText = ctx.getChild(0).getText();
		boolean val = Boolean.parseBoolean(boolText);
		return new BooleanValue(val);
	}

	/*
	 * visitor function for ConjunctionContext
	 * 
	 * @param ctx representing ConjunctionContext
	 */
	@Override
	public Expression visitConjunction(ConjunctionContext ctx) {
		Expression left = (Expression) visit(ctx.getChild(0));
		Expression right = (Expression) visit(ctx.getChild(2));
		return new Conjunction(left, right);
	}

	/*
	 * visitor function for ImplicationContext
	 * 
	 * @param ctx representing ImplicationContext
	 */
	@Override
	public Expression visitImplication(ImplicationContext ctx) {
		Expression left = (Expression) visit(ctx.getChild(0));
		Expression right = (Expression) visit(ctx.getChild(2));
		return new Implication(left, right);
	}

	/*
	 * visitor function for ParenthesesContext
	 * 
	 * @param ctx representing ParenthesesContext
	 */
	@Override
	public Expression visitParentheses(ParenthesesContext ctx) {
		Expression expr = (Expression) visit(ctx.getChild(1));
		return new Parantheses(expr);
	}

	/*
	 * visitor function for VerificationContext
	 * 
	 * @param ctx representing VerificationContext
	 */
	@Override
	public Statement visitVerification(VerificationContext ctx) {
		Expression expr = (Expression) visit(ctx.getChild(1));
		return new Verification(expr);
	}

}
