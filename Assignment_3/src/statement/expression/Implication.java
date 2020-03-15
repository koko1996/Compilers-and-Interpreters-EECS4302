package statement.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Implication Class that represents implication expression
 */
public class Implication extends Composite {

	/*
	 * Constructor
	 * 
	 * @param left the left expression
	 * 
	 * @param right the left expression
	 */
	public Implication(Expression left, Expression right) {
		super(left, right);
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
		List<Map<String, Boolean>> result = new ArrayList<>();
		List<Map<String, Boolean>> leftList;
		List<Map<String, Boolean>> rightList;
		if (expected == false) {
			// left = true && right == false
			leftList = this.left.witness(!expected);
			rightList = this.right.witness(expected);
			crossAppend(result, leftList, rightList);
		} else {
			// expected == true
			// left = false && right == false
			// Also
			// left = false && right == true
			leftList = this.left.witness(!expected);
			rightList = this.right.witness(!expected);
			rightList.addAll(this.right.witness(expected));
			crossAppend(result, leftList, rightList);

			// left = true && right == true
			leftList = this.left.witness(expected);
			rightList = this.right.witness(expected);
			crossAppend(result, leftList, rightList);
		}
		return result;
	}

	/*
	 * creates a string representation of the current object
	 * 
	 * @return string that represents the value of the current object
	 */
	@Override
	public String toString() {
		return "(" + this.left.toString() + ") => (" + this.right.toString() + ")";
	}

}
