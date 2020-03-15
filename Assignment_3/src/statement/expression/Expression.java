package statement.expression;

import java.util.List;
import java.util.Map;

import statement.Statement;

/* 
 * Expression class representing the logical expressions
 */
public abstract class Expression extends Statement {
	public abstract List<Map<String, Boolean>> witness(boolean expected);
}
