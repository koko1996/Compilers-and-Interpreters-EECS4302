package statement.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Equivalence Class that represents equivalence expression
 */
public class Equivalence extends Composite {
	/*
	 * Constructor
	 * 
	 * @param left the left expression
	 * 
	 * @param right the right expression
	 */
	public Equivalence(Expression left, Expression right) {
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
		if (expected == true) {
			// left = true && right == true
			leftList = this.left.witness(expected);
			rightList = this.right.witness(expected);
			crossAppend(result, leftList, rightList);

			// left = false && right == false
			leftList = this.left.witness(!expected);
			rightList = this.right.witness(!expected);
			crossAppend(result, leftList, rightList);
		} else {
			// expected == false
			// left = false && right == true
			leftList = this.left.witness(expected);
			rightList = this.right.witness(!expected);
			crossAppend(result, leftList, rightList);

			// left = true && right == false
			leftList = this.left.witness(!expected);
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
		return "(" + this.left.toString() + ") <=> (" + this.right.toString() + ")";
	}

}
