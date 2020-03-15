package statement.declaration;

import statement.Statement;

/*
 * VariableInitialization Class that represents initialization of a variable
 */
public class VariableInitialization extends Statement {
	private String id;
	private boolean value;
	private String type;

	/*
	 * Constructor
	 * 
	 * @param id the id of the variable
	 * 
	 * @param type the type of the variable (not important in this project)
	 * 
	 * @param value the assigned value of the variable
	 */
	public VariableInitialization(String id, String type, boolean value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}

	/*
	 * retrieve the id of the variable
	 * 
	 * @return retrieve the id of the variable
	 */
	public String getID() {
		return this.id;
	}

	/*
	 * retrieve the type of the variable
	 * 
	 * @return retrieve the type of the variable
	 */
	public String getType() {
		return this.type;
	}

	/*
	 * retrieve the value of the variable
	 * 
	 * @return retrieve the value of the variable
	 */
	public boolean getValue() {
		return this.value;
	}
}
