/**
 * Rule.java
 * A rule for forward/backward chaining
 * Many antecedants, 1 consequent
 */

 import java.util.*;
 import java.io.*;

 public class Rule {

 	private String[] antes;
 	private String conseq;

 	public Rule (String[] a, String c){
 		antes = a;
 		conseq=c;}


    public String[] getAntes() {return antes;}
    public String getConseq() {return conseq;}

    /*
     * antes2String
     * eturn the antes as a single string
     */

    public String antes2String(){
    	String res ="";
    		for (String a: antes){res=res+"\n"+a;}
    		return res;
    }
}

