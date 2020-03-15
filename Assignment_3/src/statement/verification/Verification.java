package statement.verification;

import statement.Statement;
import statement.expression.Expression;

/*
 * Verification Class that represents verification statement
 */
public class Verification extends Statement {
	private Expression expr;
	
	/*
	 * Constructor 
	 * @param expr the underlying expression 
	 */
	public Verification(Expression expr){
		this.expr = expr;
	}
	
	/*
	 * retrieve the underlying expression
	 * @return the underlying expression
	 */
	public Expression getExpression(){
		return this.expr;
	}
}
