/**
 * RuleSet.java
 * A rule set for forward/backward chaining
 * Many antecedants, 1 consequent
 */

 import java.util.*;
 import java.io.*;

 public class RuleSet {

 	private ArrayList<Rule> rules;

 	public RuleSet (ArrayList<Rule> a){
 		rules = a;
 		}


    public ArrayList<Rule> getRules() {return rules;}
}
