
/**
 * MStringVector.java
 * a vector of Mstring
 * with facilities for pattern matching
 * Created: Jan 2013
 *
 * @author phil green
 * @version 4
 */

package pmatch;

import java.util.*;



public class MStringVector extends Object{
  private ArrayList<MString> v; //the vector of MStrings
  private String sin; //the input string - | is separator
  private HashMap context;  //context for matching
  private ArrayList<MString> remVec;  //remainder of vector after match
  private ArrayList<MatchDetails> mdList = new ArrayList<MatchDetails>(); //for multiple matches - list of MatchDetails

  // accessors
  public ArrayList<MString> getV(){return v;}
  public void setV(ArrayList<MString> vv){v=vv;}

  public HashMap getContext() {return context;}
  public ArrayList<MString> getRemVec() {return remVec;}
  public ArrayList<MatchDetails> getMatchDetails() {return mdList;}

  //empty constructor - sets the vector v to be empty
  public MStringVector(){v=new ArrayList<MString>();}

  //constructor given a vector

  public MStringVector(ArrayList<MString> vin) {v=vin;}

  //constructor given a string with '|' as separator

  public MStringVector(String s){
    v=new ArrayList<MString>();
    sin=s;
    int i=0;
    int j=sin.indexOf('|');

    while (j != -1){
      v.add(new MString(sin.substring(i,j)));
      i=j+1;
      j=sin.indexOf('|',j+1);
    }
    v.add(new MString(sin.substring(i,sin.length())));
  }

  //constructor given a string array

  public MStringVector(String[] slis){
  	v=new ArrayList<MString>();
    for(int j=0;j<slis.length;j++){v.add(new MString(slis[j]));}
  }

  /*
  //constructor given an arraylist of String (antes of a rule

  public MStringVector(ArrayList<String> slis){
  	v=new ArrayList<MString>();
  	for (String s: slis){v.add(new MString(s));}
  }

 */

  //add an MString to the vector
  //do we need this?

  public void addMString(MString s) {v.add(s);}

  /**
  * match
  * match against a string
  * until a match is found, giving a context, or vector runs out
  * sets remVec to remainder of vector after matching item
  * @param String s
  */


  public boolean match(String s){
    boolean result=false;
    Iterator vit=v.iterator();
    while (vit.hasNext() && !result){
      MString p=(MString)vit.next();
      result=p.match(s);
      if (result) {
       context=p.getContext();
       remVec= new ArrayList<MString>();
      while (vit.hasNext()){
        remVec.add((MString)vit.next());
        }
      }

    }
    return result;
  }

  /**
  * match against a string
  * given an initial context
  * until a match is found, giving a context, or vector runs out
  * sets remVec to remainder of vector after matching item
  * @param String s
  */

  public boolean match(String s, HashMap con){
    boolean result=false;
    Iterator vit=v.iterator();
    while (vit.hasNext() && !result){
      MString p=(MString)vit.next();
      result=p.match(s,con);
      if (result){
        context=p.getContext();
        remVec= new ArrayList<MString>();
        while (vit.hasNext()){
          remVec.add((MString)vit.next());
          }
      }
    }
    return result;
  }

  /**
  * matchAll
  * find all the matches
  * for each, remember context, what matched & what didn't - in MatchDetails instance
  * @param String s
  */

  public boolean matchAll(String s){
  	mdList.clear(); //initialmatchdetails empty
  	boolean result=false; // becomes true if at least 1 match found

    for (MString nextV: v){
      MString p=nextV; //theMstring
      boolean result1=p.match(s);
      if (result1) {
      		result=true;
      		ArrayList<MString>cloneV=(ArrayList<MString>)v.clone();
      		cloneV.remove(p);
      		MatchDetails md = new MatchDetails(p.getContext(), p.getStr(), new MStringVector(cloneV));
      		mdList.add(md);
      		};
      	}
    return result;
  }

  /**
  * matchAll
  * find all the matches
  * given an initial context
  * for each, remember context, what matched & what didn't - in MatchDetails instance
  * @param String s
  * @param HashMap con
  */

  public boolean matchAll(String s, HashMap con){
  	mdList.clear(); //initialmatchdetails empty
  	boolean result=false; // becomes true if at least 1 match found

    for (MString nextV: v){
      MString p=nextV;
      boolean result1=p.match(s,con);
      //System.out.println("matching "+p.getStr()+" against "+s+" in context "+con);
      if (result1) {
      	    //System.out.println("succeeds giving context "+p.getContext());
      		result=true;
      		ArrayList<MString>cloneV = (ArrayList<MString>)v.clone();
      		cloneV.remove(p);
      		MatchDetails md = new MatchDetails(p.getContext(), p.getStr(),new MStringVector(cloneV));
      		mdList.add(md);
      		};
      	}
    return result;
  }

  /**
  * mSubst
  * substitute for given context
  * @param HashMap c
  */

  public ArrayList<String> mSubst(HashMap c){
    ArrayList<String> ans= new ArrayList<String>();
    for (MString vit : v){
      ans.add(vit.mSubst(c));
    }
    return ans;
  }

  /**
   *toString
   *return v as a String
   */

  public String toString(){
  	String res="";
  	for (MString ms:v){res=res+"\n"+ ms.getStr();}
  	return res;
  }

  /**
   * isEmpty
   * is v empty?
   */

  public Boolean isEmpty()
  	{return v.isEmpty();
  	}

  /**
   * pop
   * pops v
   */

  public MString pop(){
  	MString tms=v.get(0);
  	v.remove(0);
  	return tms;

  }

  /**
   * add
   * add a string to v
   */

  public void add(String s){
  	v.add(new MString(s));
  }
}


