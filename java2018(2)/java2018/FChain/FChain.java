/**
*	FChain.java -
*   class for forward chaining engine
* 2013 version
*/

import java.util.*;
import sheffield.*;
import pmatch.*;

public class FChain {

    private ArrayList<Rule> rules; // the rule set
    private ArrayList<String> knownFacts; // all facts given & deduced so far
    private MStringVector kfMvec; //the facts as an MStringVector
    private ArrayList<String> toPursue; //facts waiting for expansion
    private String f; //the fact currently being pursued
	private EasyWriter scr = new EasyWriter();


    public  FChain (ArrayList<Rule> rl) {rules=rl;} //constructor is given the rule set

    public ArrayList<String> getKF (){ return knownFacts;} //accessor for knownFacts


	// the forward chaining engine
    public ArrayList<String> runFC (ArrayList<String> kf) { //is given starting facts
    	knownFacts = new ArrayList<String>();
    	toPursue =new ArrayList<String>();
    	knownFacts.addAll(kf);
    	toPursue.addAll(kf);

    	while (!toPursue.isEmpty()) { // continue until nothing left to pursue
    		//scr.println("---------------");
    		//scr.println("kf= "+knownFacts);
    		//scr.println("tp= "+toPursue);
    		f= (String) toPursue.get(0); //explore from first node on toPursue - f
			toPursue.remove(0); //& remove it from toPursue

			scr.println("pursuing "+f);


			for (Rule r:rules){ //iterate over the rules looking for antes matching f

				MStringVector antes = new MStringVector(r.getAntes()); //its antecedants as an MStringVector
				boolean res=antes.matchAll(f); //match for f?


				if (res) { // match, go for the remaining antes
					scr.println("match for rule with antes "+r.antes2String());
					ArrayList<MatchDetails> partials = antes.getMatchDetails(); //matchall returns partial match list ***
					while (!partials.isEmpty()){ //explore each partial
						MatchDetails p = partials.get(0); //pop next partial
						partials.remove(0);
						HashMap con=p.getContext(); //its context
						String matcher = p.getMatcher(); //the ante which matched
						MStringVector rest = p.getRest(); // the rest

						if (rest.isEmpty()) {//no more antecedants - deduction
							MString cm = new MString(r.getConseq()); //consequent of the rule
							String deduction=cm.mSubst(con); //substitute in it
							scr.println("Deduced "+ deduction);
							knownFacts.add(deduction); //to get the deduction - add to knownFacts and toPursue
							toPursue.add(deduction);


							}
							// there are more antes - can we match them against knownFacts?
							else {
							 String nextAnte=rest.pop().getStr();
							 scr.println("matching "+nextAnte);
							 scr.println("in context "+con);

							 kfMvec=new MStringVector(); //set the knownFacts up for matching
							 ArrayList<MString>kfMlis=new ArrayList<MString>();
							 for (String kfs:knownFacts){kfMlis.add(new MString(kfs));}
							 kfMvec.setV(kfMlis);

							 // could be
							 // for (String kfs:knownFacts) {kfMvec.add(kfs);}


							 boolean cres=kfMvec.matchAll(nextAnte, con); //match against known facts
							 scr.println("result = "+cres);
							 if (cres) { //matches found, each one creates new partial
							 	ArrayList<MatchDetails> cp=kfMvec.getMatchDetails();

							 	for (MatchDetails d:cp){
							 		d.setRest(rest); //in which this ante is now removed
							 		} //these partials have remaining antes

							 	partials.addAll(0,cp); //put them at the front of the partial list
							 	} //end of if (cres)
							 }//end of else to if (rest.isEmpty())
				}// end of while (!partials.isEmpty())
			}//end of if (res)
		}//end of for over the rules
	}//end of while something to pursue
	return knownFacts;
	} //end of runFC
} //end of class
