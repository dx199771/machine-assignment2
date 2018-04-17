/**
* BChainState
* State in backward chaining
* 2013 version
*/

import java.util.*;
import sheffield.*;
import pmatch.*;

public class BChainState extends SearchState{

  private ArrayList<String> gl; //the list of goals
  private HashMap avl; //the ans-var-list


  // constructor supplying each element in row order
  public BChainState(ArrayList<String> glis, HashMap avlis){
  	gl=glis;
  	avl=avlis;
  }

  //accessors
  public ArrayList<String> getGoals(){return gl;}
  public HashMap getAvl(){return avl;}


  // goalP
  // a state is a goal if there are no remaining goals
  // and there are no nulls in avl

  public boolean goalP(Search searcher) {
    if (gl.isEmpty()&&!avl.containsValue(null)) {return true;}
       else {return false;}
      }

  /**
  * Get_Successors
  * for each goal g in the state's goal list
  *  for each rule r in the ruleset
  *    if g matches r's conseq,
  *         giving pcon of form {<cm,gm>} -vars in conseq
  *         and    dcon of form {<gm,cm>} -vars in goal
  *      form up new goals
  *        new goal list=remaining goals from gl+ r's antecedants
  *        substitute for both pcon & dcon in each goal
  *        update avl - add value for any gm in dcon
  */


  public ArrayList<SearchState> getSuccessors (Search searcher) {
    BChainSearch bcsearcher = (BChainSearch) searcher;
    EasyWriter scr = new EasyWriter();
    RuleSet rs=bcsearcher.getRules();
    ArrayList<Rule> rules=rs.getRules();

    ArrayList<SearchState> slis=new ArrayList<SearchState>();

    for (String g:gl){  //g is the goal to try
     //MString mg = new MString(g); //as an MString
     ArrayList<String>rem_gl= (ArrayList<String>)gl.clone();
     rem_gl.remove(g); //remaining goals

     for (Rule r: rules){
     	String conseq = r.getConseq(); //consequent of the rule
    	MString mc= new MString(conseq);
    	//scr.println("    matching conseq "+conseq+" against goal "+g);
    	boolean mres = mc.match2Way(g); //match against the goal
    	//scr.println("    returns "+mres);
    	if (mres){ // match
    		scr.println("    match for rule with conseq "+conseq);
    		HashMap pcon = mc.getpCon();
    		HashMap dcon = mc.getdCon();
    		scr.println("      pcon= "+pcon);
    		scr.println("      dcon= "+dcon);
    		String[] anteslis=r.getAntes(); //antecedants of the rule
    		ArrayList<String>antes=new ArrayList<String>();
    		for (String as: anteslis){antes.add(as);}
    		rem_gl.addAll(antes);

    		ArrayList<String> newGl = new ArrayList<String>(); //the new goal list
    		HashMap newAvl=(HashMap)avl.clone();

    		for (String k: rem_gl) {//obtained by substituting from pcon
    			MString ante=new MString(k);
    			MString antep=new MString(ante.mSubst(pcon));
    			newGl.add(antep.mSubst(dcon)); //substitute for both pcon & dcon
    		}//end of for
    	    //update the avl

    	    for (Map.Entry e: (Set<Map.Entry>)avl.entrySet()){
    	     if (dcon.containsKey(e.getKey())) {//there's a value in dcon for this av
    			newAvl.put(e.getKey(),dcon.get(e.getKey()));
    		    } //end of it
    	    }//end of for

    	    //make the new state
    	    BChainState s=new BChainState(newGl,newAvl);
    	    scr.println("         new state "+s.toString());
    	    slis.add((SearchState)s); //add it to successors
            }//end of if mres
     }//end of for
    }//end of while
    return slis;
  }

  // sameState - must have same gl & avl?

  public boolean sameState (SearchState s2) {
    BChainState bs2= (BChainState) s2;
    return(gl.equals(bs2.getGoals())&&avl.equals(bs2.getAvl()));
  }


// toString
  public String toString () {
    return("\n  Backward Chaining State:\n   Goals  "+gl.toString()+"\n   AVL  "+avl.toString());
    }

}



