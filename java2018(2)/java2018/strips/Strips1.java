
/**
 * Strips1.java
 * find difference, call strips2
 * if that succeeds, call strips1 to deal with any remaining diffs
 * Created: Sun Jan 14 21:06:02 2001
 *
 * @author
 * 2013 Version
 */

import java.util.*;
import pmatch.*;

public class Strips1 extends StripsFn{

  /** NOT NEEDED? 10/1/10
  //constructor called from outside at startup, reads ops from file

  //ops is vector of operator names

  public Strips1 (MStringVector ops, MStringVector is, MStringVector gs){
    initState=is;
    goalState=gs;
    opLis=new ArrayList<StripsOp>();

    //get the operators from file

    for (String str: ops){
      StripsOp op=new StripsOp(str);
      opLis.add(op);
    }
  }
  */

  //constructor called internally, passed opLis directly

  public Strips1 (ArrayList<StripsOp> opl,MStringVector is, MStringVector gs){
    opLis=opl;
    initState=is;
    goalState=gs;
  }

  //run problem solver

  public boolean run(){
    //commentary
    System.out.println("-----------------");
    System.out.println("Strips1");
    System.out.println("currrent state "+initState.toString());
    System.out.println("goal state "+goalState.toString());

    //look for a difference
    boolean diffound=false; //set to true when diff found
    String g=new String(); //where the diff found will go
    Iterator git=goalState.getV().iterator();
    while(git.hasNext()&& !diffound){ //iterate over goals
      MString gnext = (MString)git.next();
      g=gnext.getStr();
      //try to match goal g within state - initState is an MStringVector
      diffound=!initState.match(g);
    }

    if (!diffound){
      result=true; //no difference found - trivial success
      newState=initState; //end state same as initState
      System.out.println("all goals met");
      System.out.println("-----------------");
      plan=new ArrayList<String>(); //empty plan
      return result;
    }
    else{ //found a diff, use Strips2
      System.out.println("working on goal "+g);
      Strips2 strips2=new Strips2(opLis,initState, goalState,g); //make instance
      boolean s2res=strips2.run(); //run it
      if (!s2res){  //did Strips2 succeed?
        result=false; //no, failure
        return result;
      }
      else { //strips2 succeeded - call Strips1 again with state returned
        //must be a new instance - state will have changed
        Strips1 nstrips1=new Strips1(opLis, strips2.getNewState(),goalState);
        boolean nstrips1res=nstrips1.run();
        if (!nstrips1res){ //did strips1 succeed?
          result=false; //no, failure
          return result;
        }
        else {  //success
          result=true;
          //plan is strips2 plan + strips1 plan
          plan=strips2.getPlan();
          plan.addAll(nstrips1.getPlan());
          newState=nstrips1.getNewState();//final state is that returned by strips1
          return result;
        }
      }
    }
  }
}



