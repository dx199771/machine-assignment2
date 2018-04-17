/**
*	ProdSys.java -
*   class for production system engine
*   2013 Version
*/

import java.util.*;
import sheffield.*;
import pmatch.*;

public class ProdSys {

    private ArrayList<Prodn> prods; // the prodn set
    private ArrayList<String> stm; // the current short term memory

	private EasyWriter scr = new EasyWriter();


    public  ProdSys (ArrayList<Prodn> rl) { //constructor is given the prod set
    	prods=rl;
    	}


	// the prodn system engine
    public ArrayList<String> RunPS (ArrayList<String> s) { //is given the initial stm
    	scr.println("RUNNING PRODUCTION SYSTEM");
    	stm = s;
    	boolean prodFired=true;

    	while (prodFired) { // continue until no production fires
    		scr.println("------------------------------------------------------------------");
    		scr.println("STM= "+stm);
    		scr.println();

    		prodFired=false; //will be reset to true when a prodn is fired

    		MStringVector stmMatcher=new MStringVector(); //stm as an MStringVector
    		for (String ss:stm){stmMatcher.add(ss);}


			Iterator i = prods.iterator();

			while (i.hasNext()&&!prodFired){ //iterate over the prods until one fires or run out
				Prodn r = (Prodn)i.next(); //next prodn
				//scr.println("Trying to fire "+r.getName());
				MStringVector antes = new MStringVector(r.getAntes()); //its antecedants as an MStringVector
				ArrayList<MatchDetails> partials = new ArrayList<MatchDetails>(); // partial matches
				partials.add(new MatchDetails(new HashMap(),"",antes)); // initiallly 1 partial with all the antes & an empty context

				//try to develop partials by matching next ante
				//continue until partials run out or a prod fires

				while (!partials.isEmpty()&&!prodFired){
				/*	scr.println("while partials are");
					 for (Iterator k=partials.iterator();k.hasNext();){
					 MatchDetails pp = (MatchDetails)k.next();
					 scr.println(pp.getRest());
				     }
				*/
					MatchDetails p=(MatchDetails) partials.get(0);
					//next partial
					partials.remove(0); // remove it

					HashMap con=p.getContext(); //its context
					MStringVector rest = p.getRest(); // the remaining antes
				//	scr.println("developing partial with context "+con+" & remaining antes "+rest);
					//if no more antes and the prodn's predicate returns true in the context, fire the prodn
					if (rest.getV().isEmpty()) {
						if (r.pred(con)){
							fire_prodn(r,con);
							prodFired=true;
						}
					}

						else { //there are more antes, find matches for the first one & create new partials
						String next_ante= (rest.getV().get(0)).getStr(); //next ante to try
				//		scr.println("ante "+next_ante);
				/*		scr.println("before remove partials are");
					     for (Iterator k=partials.iterator();k.hasNext();){
					     MatchDetails pp = (MatchDetails)k.next();
					     scr.println(pp.getRest());
					     }
			    */
					    ArrayList<MString>restV=(ArrayList<MString>)rest.getV().clone();
					    restV.remove(0);
					    rest.setV(restV); //remainder after that
				/*        scr.println("after remove partials are");
					     for (Iterator k=partials.iterator();k.hasNext();){
					     MatchDetails pp = (MatchDetails)k.next();
					     scr.println(pp.getRest());
				         }
				*/
						boolean cres=stmMatcher.matchAll(next_ante, con); //match against stm

							 if (cres) { //matches found, each one creates new partial
							 	ArrayList<MatchDetails> cp=stmMatcher.getMatchDetails();
				//			 	scr.println(cp.size()+" matches found");
							 	for (MatchDetails d: cp){
				//			 		scr.println("rest= "+rest);
							 		d.setRest(rest); //in which this ante is now removed
							 		} //these partials have remaining antes

							 	partials.addAll(0,cp); //put them at the front of the partial list
				/*			 	scr.println("partials are");
							 	for (Iterator k=partials.iterator();k.hasNext();){
							 		MatchDetails pp = (MatchDetails)k.next();
							 		scr.println(pp.getRest());
							 		}
				*/
							 } //end of if (cres)
					    } //end of else to if rest.isEmpty
				} //end of while 3
			}//end of while 2
		}//end of while 1
		scr.println("RUN TERMINATED");
		return stm; // return final stm
	}// end of RunPS

	// fire a given prodn, given the context
	private void fire_prodn(Prodn p, HashMap c) {
		HashMap con=p.modifyContext(c); //call the context modifier
		String name = p.getName();
 	    MStringVector addsm = new MStringVector(p.getAdds());//make MStringVectors for the prods adds, dels & remarks
 		MStringVector delsm = new MStringVector(p.getDels());
 		MStringVector remsm = new MStringVector(p.getRemarks());

 		ArrayList<String>adds=addsm.mSubst(c); //use these to substitute for the context
 		ArrayList<String> dels=delsm.mSubst(c);
 		ArrayList<String> rems=remsm.mSubst(c);

 		scr.println("dels ");
 		for (String d:dels) {scr.println(d);}

 		scr.println("Firing "+name);
 		scr.println(" in context "+c.toString());

 		stm.removeAll(dels); //remove the deletions from the stm
 		stm.addAll(adds);    //add the additions
 		scr.println(name+" remarks:");
 		for (String r:rems){scr.println("  "+r);} //make the remarks

 	}

}


