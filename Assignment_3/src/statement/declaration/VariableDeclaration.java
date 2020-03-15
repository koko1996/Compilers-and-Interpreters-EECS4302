package statement.declaration;

import statement.Statement;

/*
 * VariableDeclaration Class that represents declaration of a variable
 */
public class VariableDeclaration extends Statement {
	private String id;
	private String type;

	/*
	 * Constructor
	 * 
	 * @param id the id of the variable
	 * 
	 * @param value the assigned value of the variable
	 */
	public VariableDeclaration(String id, String type) {
		this.id = id;
		this.type = type;
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
	 * retrieve the value of the variable
	 * 
	 * @return retrieve the value of the variable
	 */
	public String getType() {
		return this.type;
	}

}
