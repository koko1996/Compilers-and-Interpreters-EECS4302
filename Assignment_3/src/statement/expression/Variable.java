package statement.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Variable Class that represents single variable expression
 */
public class Variable extends Expression {
	private String id;
	private Boolean value;

	/*
	 * Constructor
	 * 
	 * @param id the id of the variable in the expression
	 * 
	 * @param valye the assigned value of the variable in the expression
	 */
	public Variable(String id, Boolean value) {
		this.id = id;
		this.value = value;
	}

	/*
	 * retrieve the id of the variable expression
	 * 
	 * @return retrieve the id of the variable expression
	 */
	public String getID() {
		return this.id;
	}

	/*
	 * retrieve the value of the variable expression
	 * 
	 * @return retrieve the value of the variable expression
	 */
	public Boolean getValue() {
		return this.value;
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
		if (this.value == null || this.value == expected) {
			Map<String, Boolean> map = new HashMap<>();
			map.put(this.id, expected);
			result.add(map);
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
		return this.id;
	}

}
