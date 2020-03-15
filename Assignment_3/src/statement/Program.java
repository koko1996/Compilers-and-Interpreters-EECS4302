package statement;

import java.util.ArrayList;
import java.util.List;

/*
 * Program class that represents an input program
 */
public class Program {
	private List<Statement> statements; // expressions in the program
	private List<String> variables; // variables in this program in
	// the order it is specified

	/*
	 * Constructor
	 */
	public Program() {
		this.statements = new ArrayList<>();
	}

	/*
	 * Adds a statement to the list of statements
	 * 
	 * @param e Statement to add
	 */
	public void addExpression(Statement e) {
		this.statements.add(e);
	}

	/*
	 * Get the the list of statements
	 */
	public List<Statement> getStatments() {
		return statements;
	}

	/*
	 * Get the the list of variables
	 */
	public List<String> getVariables() {
		return variables;
	}

	/*
	 * Set the the list of variables
	 */
	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
}
