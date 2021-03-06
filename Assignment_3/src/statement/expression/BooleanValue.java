package statement.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * BooleanValue Class that represents boolean values
 */
public class BooleanValue extends Expression {
	private boolean value; // Attribute to hold the value of the current object
							

	/*
	 * Constructor
	 * 
	 * @param value boolean value of the object
	 */
	public BooleanValue(boolean value) {
		this.value = value;
	}

	/*
	 * getter for the value of the current object
	 * 
	 * @return boolean that represents the value of the current obj
	 */
	public boolean getValue() {
		return value;
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
		if (this.value == expected) {
			Map<String, Boolean> map = new HashMap<>();
			map.put("", expected);
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
		return Boolean.toString(this.value);
	}
}
