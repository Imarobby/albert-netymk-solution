package jlogic;

public class Block {
	static TheoremProver engine=new TheoremProver(100);
	static Clause clause;
	static Clause goal;

	// ToDo:
	// add assertions re male/female people contained in the family tree
	// rewrite daughter/son clauses to make use of male/female assertions
	// add clauses for mother, father, sister, aunt, grandmother/grandfather
	// add more people to the family tree and check if everything works...

	public static void printResult(Substitution result) {
		if(result==null)
			System.out.println("False.\n"+goal);
		else {
			clause=goal.substitute(result);
			System.out.println("True.\n"+goal);
			// System.out.println("True.\n"+clause);
		}
	}
	public static void main(String[] args) {
	}
}
