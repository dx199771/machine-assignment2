
/**
 *  MString.java
 *  String as Matching pattern
 *  Can have variables - starting with ? - in either p or d
 * Created: Sun Jan 2004
 *
 * @author phil green
 * @version 2
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

  private HashMap pcon;
  private HashMap dcon;

  public HashMap getPcon(){return pcon;}
  public HashMap getDcon() {return dcon;}

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
    String[] ptok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dtok= d.split("\\s"); //& the string it will match against
    boolean result;
    if (ptok.length != dtok.length)//unequal number of words
      result=false;
    else {
      result=true;
      context=new HashMap();
      int j=0;
      while (j<ptok.length && result){
        String nextp=ptok[j];
        String nextd=dtok[j];
        j=j+1; //forgot this is previous version

        if (!nextp.equals(nextd)){
          if (pvar(nextp))
            context.put(nextp,nextd);
          else {
            if (pvar(nextd))
              context.put(nextd,nextp);
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
    String[] ptok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dtok= d.split("\\s"); //& the string it will match against
    boolean result;

    if (ptok.length != dtok.length)
      result=false;
    else {
      result=true;
      int j=0;
      while (j<ptok.length&& result){
        String nextp=ptok[j];
        String nextd=dtok[j];
        j=j+1; // this was missing too

        if (!nextp.equals(nextd)){
          if (pvar(nextp)){ //got an mvar in p
            String res=(String)context.get(nextp); //look it up in context
            if (res==null){ //no entry, so add new binding
              context.put(nextp,nextd);
            }
            else{  //does bound value match nextd?
              result=res.equals(nextd);
            }
          }
          else
            if (pvar(nextd)){ //got an mvar in d
              String res=(String)context.get(nextd); //look it up in context
              if (res==null){ //no entry, so add new binding
                context.put(nextd,nextp);
              }
              else{  //does bound value match nextd?
                result=res.equals(nextp);
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
  * pcon for matching vars in p (ptok)
  * dcon for matching vars in d
  * used in bchain
  * @param String d
  */

   public boolean match_2_way(String d){
    String[] ptok = str.split("\\s"); //tokenise the MString - separate into words
    String[] dtok= d.split("\\s"); //& the string it will match against
    boolean result;

    if (ptok.length != dtok.length)
      result=false;
    else {
      result=true;
      pcon=new HashMap();
      dcon=new HashMap();
      int j=0;
      while (j<ptok.length && result){
        String nextp=ptok[j];
        String nextd=dtok[j];
        j=j+1; // and again

        if (!nextp.equals(nextd)){
          if (pvar(nextp)){pcon.put(nextp,nextd);} //mvar in p
          else {
          	if (pvar(nextd)) {dcon.put(nextd,nextp);} //mvar in d
          	else {result=false;}
          }
        } //end of outer if
      }//end of while
    }//end of else
     return result;
  }



   //is a string a wildcard?

  private boolean pvar(String v){
    return(v.startsWith("?"));
  }

  /**
  * Msubst
  * substitute back in patt from given context
  * creates new patt & returns it
  * @param HashMap c - the context
  */

  public String msubst(HashMap c){
    context=c;
    String newpatt=new String();
    String[] ptok = str.split("\\s");
    int j=0;
    while (j<ptok.length){
      String np=ptok[j];
      String res= (String) c.get(np);
      if (res==null) newpatt=newpatt.concat(np);
      else newpatt=newpatt.concat(res);
      j=j+1; // forgot this too
      if (j<ptok.length){
        newpatt=newpatt.concat(" ");
      } //no final space

    }
    return newpatt;
  }
}




