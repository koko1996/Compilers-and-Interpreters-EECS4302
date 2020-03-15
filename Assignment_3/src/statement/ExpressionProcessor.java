package statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import statement.verification.Verification;

/*
 * ExpressionProcessor class to process given expression
 */
public class ExpressionProcessor {
	private List<Statement> statements; // list of statements
	private List<String> vars; // list of variables

	/*
	 * Constructor
	 * 
	 * @param left the left expression
	 * 
	 * @param right the right expression
	 */
	public ExpressionProcessor(List<Statement> statements, List<String> vars) {
		this.statements = statements;
		this.vars = vars;

	}

	/*
	 * helper function to print one line of verification output
	 * 
	 * @param map that maps ID of type String to their boolean values
	 */
	private String prettyPrint(Map<String, Boolean> map) {
		StringBuffer sb = new StringBuffer();
		ListIterator<String> itr = this.vars.listIterator();

		// since we only go through the list of variables (and not the map of
		// assignments) we won't add any boolean values that appear in the
		// expression (i.e. ""=true)
		while (itr.hasNext()) {
			String var = itr.next();
			if (map.containsKey(var)) {
				sb.append(var + "=" + Boolean.toString(map.get(var)));
				sb.append(",");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	/*
	 * helper function to print the list of verification output
	 * 
	 * @param list of maps that map ID of type String to their boolean values
	 */
	private List<String> prettyPrint(List<Map<String, Boolean>> list) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String pp = prettyPrint(list.get(i));
			if (!pp.equals("")) {
				result.add(pp);
			}

		}

		Collections.sort(result);
		return result;
	}

	/*
	 * calculates and returns list representing the evaluation results
	 */
	public List<String> getEvaluationResults() {
		List<String> evaluations = new ArrayList<String>();

		for (Statement e : statements) {
			if (e instanceof Verification) {
				Verification ver = (Verification) e;
				List<Map<String, Boolean>> result = ver.getExpression().witness(false);
				if (!result.isEmpty()) {
					evaluations.add("==========");
					evaluations.add(ver.getExpression().toString() + " is not a tautology.");
					List<String> pp = prettyPrint(result);
					if (!pp.isEmpty()) {
						evaluations.addAll(pp);
					}
				} else {
					evaluations.add("==========");
					evaluations.add(ver.getExpression().toString() + " is a tautology.");
				}

			}
		}

		return evaluations;
	}

}
