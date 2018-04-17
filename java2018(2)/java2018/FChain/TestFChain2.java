
/**
 * testFChain.java
 * 2016 version
 * same rule set as in rulenet project
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class TestFChain2{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();


   //ruleset from rulenet
   
   //father is parent 

   String[] fpantes ={"?f father of ?c"};
   Rule fprule = new Rule(fpantes, "?f parent of ?c");
   
   //mother is parent
    
   String[] mpantes ={"?m mother of ?c"};
   Rule mprule = new Rule(mpantes, "?m parent of ?c");
   
   //sibling
   
   String[] sibantes = {"?p parent of ?c1","?p parent of ?c2"};
   Rule sibrule = new Rule(sibantes, "?c1 sibling of ?c2");
   
   //grandparent
   
   String[] gpantes = {"?gf parent of ?p","?p parent of ?c"};
   Rule gprule = new Rule(gpantes, "?gf grandparent of ?c");
   
   //aunt or uncle
   
   String[] aunties = {"?p parent of ?c","?s sibling of ?p"};
   Rule auncrule = new Rule(aunties, "?s aunt-or-uncle of ?c");
   
   //cousin
   
   String[] cousantes = {"?a aunt-or-uncle of ?c1", "?a parent of ?c2"};
   Rule cousrule = new Rule(cousantes, "?c1 cousin of ?c2");
   
   
   //make the ruleset
   ArrayList<Rule> rset = new ArrayList<Rule>();
   rset.add(fprule);
   rset.add(mprule);
   rset.add(sibrule); 
   rset.add(gprule); 
   rset.add(auncrule);
   rset.add(cousrule);
   

   RuleSet rs = new RuleSet(rset); //maybe not needed

   //make a fchain engine
   FChain fc=new FChain(rset);

   //initial facts

   ArrayList<String> facts = new ArrayList<String>();
   facts.add("phil father of david");
   facts.add("phil father of shula");
   facts.add("david father of pip");
   facts.add("shula mother of daniel");

   // note the time
   long startTime = System.currentTimeMillis();

   ArrayList<String> res=fc.runFC(facts);
   scr.println(res);
   long stopTime = System.currentTimeMillis();
   
   scr.println("compute time (ms) " + (stopTime-startTime));
   
   
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