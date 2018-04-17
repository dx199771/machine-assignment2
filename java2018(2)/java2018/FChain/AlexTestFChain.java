
/**
 * testFChain.java
 * 2013 version
 */

import sheffield.*;
import java.util.*;

//import com.sun.javafx.css.Rule;

import pmatch.*;


public class AlexTestFChain{
 public static void main(String[] arg) { 
	long startTime = System.currentTimeMillis();
	 
   // create objects for input and output
   EasyWriter scr = new EasyWriter();


   //make some rules
   String[] gfantes={"?gf father of ?p","?p parent of ?c"};
   String[] fpantes={"?f father of ?c"};
   String[] fmantes={"?f father of ?c"};
   String[] mpantes={"?m mother of ?c"};
   String[] bsantes={"?b brother of ?s"};
   String[] bmantes={"?b brother of ?s"};
   String[] ssantes={"?s sister of ?x"};
   String[] aantes={"?a sister of ?p","?p parent of ?x"};
   String[] uantes={"?u brother of ?p","?p parent of ?x"};
   String[] nantes={"?s sibling of ?p","?p parent of ?n","?n is male"};

   Rule gfrule = new Rule(gfantes, "?gf grandfather of ?c");
   Rule fprule = new Rule(fpantes, "?f parent of ?c");
   Rule fmrule = new Rule(fmantes, "?f is male");
   Rule mprule = new Rule(mpantes, "?m parent of ?c");
   Rule bsrule = new Rule(bsantes, "?b sibling of ?s");
   Rule bmrule = new Rule(bmantes, "?b is male");
   Rule ssrule = new Rule(ssantes, "?s sibling of ?x");
   Rule arule = new Rule(aantes, "?a aunt of ?x");
   Rule urule = new Rule(uantes, "?u uncle of ?x");
   Rule nrule = new Rule(nantes, "?n nephew of ?s");
   
   //make the ruleset
   ArrayList<Rule> rset = new ArrayList<Rule>();
   rset.add(fprule);
   rset.add(gfrule);
   rset.add(fmrule);
   rset.add(mprule);
   rset.add(bsrule);
   rset.add(bmrule);
   rset.add(ssrule);
   rset.add(arule);
   rset.add(urule);
   rset.add(nrule);
   
   RuleSet rs = new RuleSet(rset); //maybe not needed

   //make a fchain engine
   FChain fc=new FChain(rset);

   //initial facts
   ArrayList<String> facts = new ArrayList<String>();
   facts.add("H7 father of H8");
   facts.add("H8 father of E");
   facts.add("Jill mother of David");
   facts.add("Jill mother of Shula");
   facts.add("David father of Pip");
   facts.add("Shula mother of Daniel");

   ArrayList<String> res=fc.runFC(facts);
   scr.println(res);

   long runTime = System.currentTimeMillis() - startTime;
   System.out.println("Total run time was: " + runTime);
   
  /* String[] ss = {"qqq","www"};
   scr.println(ss[0]);
   scr.println(ss.length);

   //make some rules
   ArrayList<String> gfantes= new ArrayList<String>();
   gfantes.add("?gf father of ?p");
   gfantes.add("?p parent of ?c");
   Rule gfrule = new Rule (gfantes, "?gf grandfather of ?c");

   ArrayList<String> fpantes = new ArrayList<String>();
   fpantes.add("?f father of ?c");
   Rule fprule = new Rule (fpantes, "?f parent of ?c");

   ArrayList<Rule> rset = new ArrayList<Rule>();
   rset.add(fprule);
   rset.add(gfrule);

   RuleSet rs = new RuleSet(rset); //maybe not needed

   //make a fchain engine
   FChain fc=new FChain(rset);

   //initial facts

   ArrayList<String> facts = new ArrayList<String>();
   facts.add("H7 father of H8");
   facts.add("H8 father of E");

   Vector res=fc.runFC(facts);
   scr.println(res);
   */
  }
}