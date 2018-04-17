
/**
 *  MString.java
 *  String as Matching pattern
 *  Can have variables - starting with ? - in either p or d
 * Created: Jan 2013
 *
 * @author phil green
 * @version 3
 */

package pmatch;

import java.util.*;
import java.io.*;



public class MString extends Object {
  private String str; //the string to be matched against
  public String getStr() {return str;}; //accessor
  private HashMap context; //the matching context
  public HashMap getContext() {return context;} //accessor

  //forward & backward contexts for bchain
  //used in match_2_way

  private HashMap pCon;
  private HashMap dCon;

  public HashMap getpCon(){return pCon;}
  public HashMap getdCon() {return dCon;}

  /**
  * constructor
  * @param String s
  */

  public MString(String s){
    str=s;
  }

  /**
  * match
  * match against given string
  * @param String d
  */

  public boolean match(String d){
    String[] pTok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dTok= d.split("\\s"); //& the string it will match against
    boolean result;
    if (pTok.length != dTok.length)//unequal number of words
      result=false;
    else {
      result=true;
      context=new HashMap();
      int j=0;
      while (j<pTok.length && result){
        String nextP=pTok[j];
        String nextD=dTok[j];
        j=j+1;

        if (!nextP.equals(nextD)){
          if (pVar(nextP))
            context.put(nextP,nextD);
          else {
            if (pVar(nextD))
              context.put(nextD,nextP);
            else result=false;
          }
        }
      }
    }

    return result;
  }

  /**
  * match
  * match against given string in a given context
  * @param String d
  * @param HashMap con - the context
  */

  public boolean match(String d, HashMap con){
    context=(HashMap)con.clone(); //context for matching is COPY of context passed in
    String[] pTok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dTok= d.split("\\s"); //& the string it will match against
    boolean result;

    if (pTok.length != dTok.length)
      result=false;
    else {
      result=true;
      int j=0;
      while (j<pTok.length&& result){
        String nextP=pTok[j];
        String nextD=dTok[j];
        j=j+1; // this was missing too

        if (!nextP.equals(nextD)){
          if (pVar(nextP)){ //got an mvar in p
            String res=(String)context.get(nextP); //look it up in context
            if (res==null){ //no entry, so add new binding
              context.put(nextP,nextD);
            }
            else{  //does bound value match nextD?
              result=res.equals(nextD);
            }
          }
          else
            if (pVar(nextD)){ //got an mvar in d
              String res=(String)context.get(nextD); //look it up in context
              if (res==null){ //no entry, so add new binding
                context.put(nextD,nextP);
              }
              else{  //does bound value match nextD?
                result=res.equals(nextP);
              }
            }
            else  //neither is mvar
              result=false;
        }
      }
    }
    return result;
  }

  /**
  * match_2_way
  * match creating 2 contexts
  * pCon for matching vars in p (pTok)
  * dCon for matching vars in d
  * used in bchain
  * @param String d
  */

   public boolean match2Way(String d){
    String[] pTok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dTok= d.split("\\s"); //& the string it will match against
    boolean result;

    if (pTok.length != dTok.length)
      result=false;
    else {
      result=true;
      pCon=new HashMap();
      dCon=new HashMap();
      int j=0;
      while (j<pTok.length && result){
        String nextP=pTok[j];
        String nextD=dTok[j];
        j=j+1;

        if (!nextP.equals(nextD)){
          if (pVar(nextP)){pCon.put(nextP,nextD);} //mvar in p
          else {
          	if (pVar(nextD)) {dCon.put(nextD,nextP);} //mvar in d
          	else {result=false;}
          }
        } //end of outer if
      }//end of while
    }//end of else
     return result;
  }



   //is a string a wildcard?

  private boolean pVar(String v){
    return(v.startsWith("?"));
  }

  /**
  * mSubst
  * substitute back in patt from given context
  * creates new patt & returns it
  * @param HashMap c - the context
  */

  public String mSubst(HashMap c){
    context=c;
    String newPatt=new String();
    String[] pTok = str.split("\\s");
    int j=0;
    while (j<pTok.length){
      String np=pTok[j];
      String res= (String) c.get(np);
      if (res==null) newPatt=newPatt.concat(np);
      else newPatt=newPatt.concat(res);
      j=j+1; // forgot this too
      if (j<pTok.length){
        newPatt=newPatt.concat(" ");
      } //no final space

    }
    return newPatt;
  }
}




