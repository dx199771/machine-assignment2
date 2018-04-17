
/**
 * StripsFn.java
 *
 * abstract class for routines in Strips planning system
 * Created: Sat Jan 13 21:42:24 2001
 *
 * @author
 * 2013 Version
 */


import java.util.*;
import pmatch.*;

public abstract class StripsFn{

  //variables
  protected ArrayList<StripsOp> opLis;  //operators
  protected MStringVector goalState; //goal state
  protected MStringVector initState; //initial state
  protected MStringVector newState;  //state after carrying out plan
  protected ArrayList<String> plan;  //final plan
  protected boolean result; //success or failure

  //accessors

  public ArrayList<StripsOp> getOpLis(){return opLis;}
  public MStringVector getGoalState(){return goalState;}
  public MStringVector getInitState(){return initState;}
  public MStringVector getNewState(){return newState;}
  public ArrayList<String> getPlan(){return plan;}

}


