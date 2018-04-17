/**
*	State in a state-space search
*	abstract class
*   must implement goalP, get_Successors, same_State, toString
*/

import java.util.*;

public abstract class Search_State {
    
    /** 
    * goalP takes a Search_Node & returns a boolean if it's a goal
    */
   
    abstract boolean goalP(Search searcher);
    
    /** get_Successors returns an ArrayList of states which are successors to the 
    * current state in a given search
    */
    
    abstract ArrayList get_Successors(Search searcher);
    
    /**
    * same_State: is this state identical to a given one?
    */
    
    abstract boolean same_State(Search_State n2);
      
}
