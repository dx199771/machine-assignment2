/**
 * RuleNode.java
 * A rule for forward chaining in rule nets
 * Many antecedants, 1 consequent
 * propagate updates tokens & builds list of deductions
 * @author Phil Green
 * First Version 23/1/2008
 */
 
 import java.util.*;
 import java.io.*;
 import pmatch.*;
 import sheffield.*;
 
 public class RuleNode {
 	
 	private String rulename; //rule name
 	private ArrayList<String> antes; //antecedants
 	private MString conseq; //consequent, as an MString (pmatch package)
 	private ArrayList<Token> tokens; //tokens 
 	private ArrayList<RuleNode> successors=new ArrayList<RuleNode>(); //list of rulenodes with antecedants matching the consequent
 	private ArrayList<String> deductions; //deductions made when propagate runs
 	private ArrayList<Token> newTokens; //new tokens made when propagate runs
 	EasyWriter scr = new EasyWriter();
 	
 	/**
 	 * constructor is given name, antes and conseq
 	 */
 	 
 	public RuleNode (String n, ArrayList<String> a, String c){
 		rulename=n;
 		antes = a;
 		conseq=new MString(c);}
 
 	/**
 	 * accessors
 	 */	
 	public String getName() {return rulename;}	
    public ArrayList<String> getAntes() {return antes;}
    public MString getConseq() {return conseq;}
    public ArrayList<Token> getTokens() {return tokens;}
    public ArrayList<String> getDeductions(){return deductions;}
    
    /** 
     * accessor and constructors for successors
     */
    public ArrayList<RuleNode> getSuccessors() {return successors;}
    public void setSuccessors(ArrayList<RuleNode> sucs){successors=sucs;}
    public void addSuccessor(RuleNode n){successors.add(n);}
    
    
    
    /**
     * initialise sets single initial token
     * with all antes, empty context
     */
    public void initialise(){ //set single initial token   
     tokens=new ArrayList<Token>();
     Token nt= new Token(antes,new HashMap()); 
     tokens.add(nt); //with all antes, empty context
    }

    /** propagate a fact to this node
     *  returns True if 
     * returns True if deduction(s) made
     * if so, sets deductions to contain all deductions made
     * adds new tokens to node's token list
     */
    
    public boolean propagate(String f){
      deductions = new ArrayList<String>();
      newTokens = new ArrayList<Token>();
      boolean res = false; //the result - set to true if deduction found
      
      for (Token tok: tokens){  //for each token
    	// match f against remaining antecedants
    	// matchFact returns True if successful, 
    	// sets up ArrayList of new tokens and ArrayList of deduction_contexts
    	scr.println(f);
    	if (tok.matchFact(f)) {
    		res=true;
    		newTokens.addAll(tok.getNewTokens());
    		
    		for (HashMap dcon: tok.getDeduction_Contexts()){
    		  String deduction=conseq.msubst(dcon);
    		  deductions.add(deduction); //add it to deductions
              }   
            }
        }
        tokens.addAll(newTokens); //add new tokens to those already there
        return res;
    }
    		  
}
