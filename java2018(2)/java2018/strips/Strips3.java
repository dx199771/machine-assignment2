
/**
 * Strips3.java
 * Apply a Strips operator
 *
 * Created: Mon Jan 15 20:13:17 2001
 *
 * @author
 * 2013 Version
 */

//import pmatch.MStringVector;
//import pmatch.MString;
//import pmatch.MatchDetails;
import pmatch.*;
import java.util.*;


public class Strips3 extends StripsFn{
  private StripsOp op;
  private HashMap context;
  private MStringVector preState; //state after preconds met
  private HashMap pCon; //context after preconds met
  private ArrayList<String> prePlan; //plan to achieve preconds
  private MStringVector aState; //state after applying op
  private ArrayList<String> aPlan; //plan to apply op

  //constructor
  public Strips3 (ArrayList<StripsOp> opl,MStringVector is, MStringVector gs, StripsOp o, HashMap con){
    opLis=opl;
    initState=is;
    op=o;
    context=con;

  }

  //run problem solver

  public boolean run(){
    //commentary
    System.out.println("-------------------");
    System.out.println("Strips3");
    System.out.println("current state "+initState);
    System.out.println("attempting to use operator "+op.getActList());
    System.out.println("in context "+context);

    boolean preres = meetPreconditions(); //try to meet preconditions
    if (!preres){
      result=false; //failed
    }
    else{ //OK, apply the op
      applyOp();
      result=true;
      plan=prePlan;
      plan.addAll(aPlan); //plan is plan for meeting preconds + op's act
    }
    return result;
  }

  private boolean meetPreconditions(){
    ArrayList<String> preconds=op.getPreconds();
    boolean preres=true; //set to false if we fail to match a precond
    preState=initState; //state having met some preconds
    pCon=context; //context after having matched some preconds
    prePlan=new ArrayList<String>(); //plan for preconds, initially empty

    Iterator pit=preconds.iterator();
    String p; //will be next precond

    while (pit.hasNext()&& preres){ //iterate over preconds

      p=(String)pit.next(); //next precond
      //System.out.println("precond "+p);
      //System.out.println("pCon "+pCon);
      boolean matchres=initState.match(p,pCon);//match precond in initState
      //System.out.println(matchres);
      //System.out.println("context  "+initState.getContext());
      if (matchres) { //this precond met
        pCon=initState.getContext(); //update context from match
      }
      else { //precond p not met - call Strips1 to deal with it

        MString pm=new MString(p); //substitute back in the precondn
        String ps=pm.mSubst(pCon); //to form the goal for Strips1

        System.out.println("precond not met-  "+p);
        System.out.println("calling Strips1 for goal "+ps);
        Strips1 strips1 =new Strips1(opLis,preState,new MStringVector(ps));
        boolean strips1res=strips1.run();

        if (strips1res){ //Strips1 succeeded - back for more preconds
          prePlan.addAll(strips1.getPlan()); //save the strips1 plan
          preState=strips1.getNewState();
        }
        else {
          preres=false; //Strips1 failed - give up
        }
      }
    }
    return preres;
  }

  // apply an operator
  // delete items from op's delete list from preState
  // then add items from add list

  private void applyOp(){
    aPlan=op.getActList().mSubst(pCon); //the plan - subst in action list
    System.out.println("Strips3: Applying op "+aPlan);
    ArrayList<String> dels=op.getDelList().mSubst(pCon); //get deletions and substitute context
    ArrayList<String> adds=op.getAddList().mSubst(pCon); //ditto for additions
    /* diagnostics
       System.out.println("Applying op: deleting "+dels+"adding "+adds);
       String del=(String) dels.elementAt(0);
       System.out.println(del.length());
       System.out.println(del);
       for (Iterator it=preState.iterator();it.hasNext();){
       String el= (String)it.next();
       System.out.println(el.length());
       System.out.println(el);
       boolean r=el.equals(del);
       }
    */
    //make the new state

    // get the preState as ArrayList(String)

    ArrayList<String> newStrings = new ArrayList<String>();
    for (MString ms: preState.getV()){newStrings.add(ms.getStr());}
    System.out.println(dels);
    System.out.println(adds);
    // remove the Strings we don't want
    newStrings.removeAll(dels);
    // add the new ones
    newStrings.addAll(adds);

    // make result into an ArrayList of MString

    ArrayList<MString> newms = new ArrayList<MString>();
    for (String ns : newStrings){newms.add(new MString(ns));}

    // & make an MStringVector from the results
    newState=new MStringVector(newms);
    System.out.println("New state "+newState.toString());

    /* Code replaced that doesn't do deletions properly - needs an equals method of MString
    ArrayList<MString> preV= preState.getV();
    ArrayList<MString> delms = new ArrayList<MString>();
    ArrayList<MString> addms = new ArrayList<MString>();

    for (String ds: dels){delms.add(new MString(ds));}
    for (String as: adds){addms.add(new MString(as));}
    preV.removeAll(delms);
    preV.addAll(addms);
    newState=new MStringVector(preV);
    System.out.println("New state "+newState.toString());
    */

  }

}

