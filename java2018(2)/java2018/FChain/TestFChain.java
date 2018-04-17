
/**
 * testFChain.java
 * 2013 version
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class TestFChain{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();


   //make some rules
   String[]gfantes={"?gf father of ?p","?p parent of ?c"};
   String[]fpantes={"?f father of ?c"};

   Rule gfrule = new Rule (gfantes, "?gf grandfather of ?c");
   Rule fprule = new Rule (fpantes, "?f parent of ?c");
   //make the ruleset
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

   ArrayList<String> res=fc.runFC(facts);
   scr.println(res);

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