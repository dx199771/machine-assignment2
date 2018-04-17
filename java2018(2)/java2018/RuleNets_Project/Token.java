/**
 * Token.java
 * token stored in a RuleNode
 * remaining antes, context
 * @author Phil Green
 * First Version 23/1/2008
 */

import pmatch.*;
import java.util.*;

public class Token {
	
		private MStringVector antes; //antecedants remaining in this token
		private HashMap context; //context from antes already matched
		private ArrayList<Token> newTokens; // new tokens after matching
		private ArrayList<HashMap> deduction_contexts; // contexts for deductions after matching
		
		/**
		 * accessors
		 */
		public ArrayList<Token> getNewTokens(){return newTokens;}
		public ArrayList<HashMap> getDeduction_Contexts() {return deduction_contexts;}
		
		/** 
		 * constructor given all the antes - used in RuleNode.initialise
		 */
		public Token(ArrayList<String> alis) { //constructor just given antes as a Vector  
		  antes = new MStringVector(); // set them as an MStringVector
		  for (String s: alis){antes.addMString(new MString(s));}
		  context = new HashMap(); //set empty context
          }
          
		/**
		 * constructor given antes and context - used in RuleNode.propagate
		 */
	    public Token (ArrayList<String> alis, HashMap con){ //constructor given antes & context
	      antes = new MStringVector(); // set them as an MStringVector
		  for (String s: alis){antes.addMString(new MString(s));}
	      context=con;
	     }
	    
	    /** constructor given an MStringVector and a context
	     * used belwo
	     */
	     
	   public Token (MStringVector ants, HashMap con){ 
	   	antes=ants;
	   	context=con;
	   }

        /**
         * matchFact
         * match given fact against remaining antes in existing context
         * return True if match(es) found
         * create list of new tokens and deductions
         */
        public boolean matchFact(String f) { //match fact against all antes
         boolean res=antes.matchall(f,context); //using matchall from pmatch
         if (res) { //success, get matchdetails
         		 newTokens = new ArrayList<Token>();
         		 deduction_contexts = new ArrayList<HashMap>();
                 
                 for (MatchDetails md: antes.getMatchDetails()){ 
                 	//for each item in matchdetails either make new token or make a deduction
                 	MStringVector remAntes = md.getRest();
                 	if (remAntes.isEmpty()) {
                 		deduction_contexts.add(md.getContext());
                 	}
                 	else newTokens.add(new Token(remAntes,md.getContext()));
                 }
         }
         return res;
        }
}
                 
      