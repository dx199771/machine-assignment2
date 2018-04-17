
/**
 * testBChain.java
 *
 */

import sheffield.*;
import java.util.*;
import pmatch.*;


public class TestBChain{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();

   //make some rules
   String[]gfantes={"?gf father of ?p","?p parent of ?c"};
   String[]fpantes={"?f father of ?c"};

   Rule gfrule = new Rule (gfantes, "?gf grandfather of ?c");
   Rule fprule = new Rule (fpantes, "?f parent of ?c");

   String[] noantes = {};
   Rule H7fH8rule = new Rule(noantes, "H7 father of H8");
   Rule H8fErule = new Rule(noantes,"H8 father of E");

   //make the ruleset
   ArrayList<Rule> rSet = new ArrayList<Rule>();
   rSet.add(fprule);
   rSet.add(gfrule);
   rSet.add(H7fH8rule);
   rSet.add(H8fErule);

   RuleSet rs = new RuleSet(rSet);

   //make the intial state

   ArrayList<String> goals = new ArrayList<String>(); //initial goals
   goals.add("?X grandfather of E");

   HashMap avl = new HashMap(); //initial ans var list
   avl.put("?X",null);


   BChainState bstate = new BChainState(goals, avl);

   //make a bchain engine
   BChainSearch bc=new BChainSearch(rs);

   //call it given initial state

   String res=bc.runSearch(bstate, "depth_first");
   scr.println(res);

  }
}