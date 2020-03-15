package statement.expression;

import java.util.List;
import java.util.Map;

/*
 * Parantheses Class that represents parantheses expression
 */
public class Parantheses extends Expression {
	private Expression expr;

	/*
	 * Constructor
	 * 
	 * @param expr the underlying expression
	 */
	public Parantheses(Expression expr) {
		this.expr = expr;
	}

	/*
	 * retrieve the underlying expression
	 * 
	 * @return the underlying expression (without the parantheses)
	 */
	public Expression getExpression() {
		return this.expr;
	}

	/*
	 * Finds a witness that evaluates the current object to the expected value
	 * 
	 * @param expected the boolean value of the witness
	 * 
	 * @return List of maps where each map maps the ID of the current object to
	 * its value in order to evaluate the current expression to the expected
	 * value
	 */
	@Override
	public List<Map<String, Boolean>> witness(boolean expected) {
		return expr.witness(expected);
	}

	/*
	 * creates a string representation of the current object
	 * 
	 * @return string that represents the value of the current object
	 */
	@Override
	public String toString() {
		return this.expr.toString();
	}
}