/**
 * Strips2.java
 * in Strips, given a difference
 * looks for an operator to deal with it
 * calls Strips3 to apply the op
 *
 * Created: Sun Jan 14 21:58:54 2001
 *
 * @author
 * 2013 Version
 */

import java.util.*;
import pmatch.*;

public class Strips2 extends StripsFn{

  String diff;  //difference to reduce

  //constructor
  public Strips2 (ArrayList<StripsOp> opl,MStringVector is, MStringVector gs, String d){
    opLis=opl;
    initState=is;
    goalState=gs;
    diff=d;
  }

  //run problem solver

  public boolean run(){
    //commentary
    System.out.println("------------------");
    System.out.println("Strips2");
    System.out.println("working on "+diff);

    result=false; //set to true when diff dealt with
    Iterator opit=opLis.iterator();

    while (opit.hasNext()&& !result){ //try ops till one succeeds or none left
      StripsOp op= (StripsOp)opit.next();

      boolean matchres=op.getAddList().match(diff); //match diff against addlist
      if (matchres){ //op can deal with diff
        HashMap con=op.getAddList().getContext(); //in this context
        System.out.println("calling Strips3 to apply operator "+op.getActList());
        //call strips3 to attempt to apply op
        Strips3 strips3 = new Strips3(opLis, initState, goalState,op,con);
        System.out.println("back from Strips3");
        boolean strips3res=strips3.run();
        if (strips3res){ //strips3 succeeded
          plan=strips3.getPlan(); //Strips2 plan is Strips3 plan
          newState=strips3.getNewState(); //new state is Strips3 new state
          result=true;
        }
      }
    }
    return result;
  }
}
