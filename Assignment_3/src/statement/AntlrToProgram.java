package statement;

import java.util.ArrayList;
import java.util.List;

import antlr.ExprBaseVisitor;
import antlr.ExprParser.ProgramContext;

/*
 * AntlrToProgram class to represent the input file in internal structure
 */
public class AntlrToProgram extends ExprBaseVisitor<Program> {
	public List<String> semanticErrors; // to be accessed by the main
										// application program

	/*
	 * visitor function for ProgramContext
	 * 
	 * @param ctx representing ProgramContext
	 */
	@Override
	public Program visitProgram(ProgramContext ctx) {
		Program prog = new Program();
		this.semanticErrors = new ArrayList<>();
		// a helper visitor for transforming each subtree into an Expression
		// Object
		AntlrToExpression exprVisitor = new AntlrToExpression(semanticErrors);
		prog.setVariables(exprVisitor.getVars());

		for (int i = 0; i < ctx.getChildCount() - 1; i++) {
			prog.addExpression(exprVisitor.visit(ctx.getChild(i)));
		}
		return prog;

	}

}