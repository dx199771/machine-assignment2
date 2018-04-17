/**
*	Node in a state-space search
*   Must implement goalP, get_Successors, same_State, node_toString
*   node has parent slot now
*/


import java.util.*;

public  class Search_Node {
  
  private Search_State state;
  //change from search1
  private Search_Node parent; // the parent node
  
   /**
  * constructor
  * @param s a Search_State
  */

  public Search_Node(Search_State s){
    state= (Search_State) s;
  }

  /**
  * accessor for state
  */

  public Search_State get_State(){
    return state;
  }
  
  /**
  * accessor for parent
  */
  public Search_Node get_Parent(){
    return parent;
  }
  
  /**
  * mutator for parent
  */
  
  public void set_Parent(Search_Node n){
    parent=n;
  }  
  // must implement goalP, get_Successors, same_State, node_toString
    
  /** 
  * goalP takes a Search_Node & returns true if it's a goal
  * @param searcher the current search
  */
   
  public  boolean goalP(Search searcher){
    return state.goalP(searcher);
  }

  /**
  * get_Successors for this node
  * @param searcher the current search
  * @return ArrayList of successor nodes
  */
  
  public ArrayList get_Successors(Search searcher){
    ArrayList slis = state.get_Successors(searcher);
    ArrayList nlis= new ArrayList();
     
    for (Iterator it = slis.iterator();it.hasNext();){
      Search_State suc_state = (Search_State) it.next();
      Search_Node n = new Search_Node(suc_state);
      nlis.add(n);
    }
    return nlis;
  }
  
  /**
  * same_State - does another node have same state as this one?
  * @param n2 the other node
  */
      
  public boolean same_State(Search_Node n2){
    return state.same_State(n2.get_State());
  }
    
  public  String toString(){
    return "has state " + state.toString();
  }
    
    
}
