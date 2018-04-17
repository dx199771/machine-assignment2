
/**
 * RunRuleNet.java
 * create a rule network & make deductions with it
 * @author Phil Green
 * First Version 23/1/08
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class RunRuleNet{
 public static void main(String[] arg) {
   // create object for output
   EasyWriter scr = new EasyWriter();
   
   //make some rules
   
   //grandfather
   ArrayList<String> gfantes= new ArrayList<String>();
   gfantes.add("?gf father of ?p");
   gfantes.add("?p parent of ?c");
   RuleNode gfrule = new RuleNode ("grandfather rule",gfantes, "?gf grandfather of ?c");
   ArrayList<RuleNode> gfsuccs = new ArrayList<RuleNode>();
   gfrule.setSuccessors(gfsuccs);
   
   //father-is-parent
   ArrayList<String> fpantes = new ArrayList<String>();
   fpantes.add("?f father of ?c");
   RuleNode fprule = new RuleNode ("father-is-parent rule", fpantes, "?f parent of ?c");
   ArrayList<RuleNode> fpsuccs = new ArrayList<RuleNode>();
   fpsuccs.add(gfrule);
   fprule.setSuccessors(fpsuccs);
   
   // the set of rulenodes
   
   ArrayList<RuleNode> rset = new ArrayList<RuleNode>();
   rset.add(fprule);
   rset.add(gfrule);
   
   // make the rule net
   RuleNet rs = new RuleNet(rset);
   //initialise it - set up initial tokens
   rs.initialise(); 
   
   //add facts
   ArrayList<String> facts = new ArrayList<String>();
   rs.addFact("H7 father of H8");
   rs.addFact("H8 father of E");
   
  
  }
}