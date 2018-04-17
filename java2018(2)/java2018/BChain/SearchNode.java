/**
*	Node in a state-space search
*   Must implement goalP, getSuccessors, sameState, node_toString
*   node has parent slot now
*   2013 version
*/


import java.util.*;

public  class SearchNode {

  private SearchState state;
  //change from search1
  private SearchNode parent; // the parent node

   /**
  * constructor
  * @param s a SearchState
  */

  public SearchNode(SearchState s){
    state= (SearchState) s;
  }

  /**
  * accessor for state
  */

  public SearchState get_State(){
    return state;
  }

  /**
  * accessor for parent
  */
  public SearchNode getParent(){
    return parent;
  }

  /**
  * mutator for parent
  */

  public void setParent(SearchNode n){
    parent=n;
  }
  // must implement goalP, getSuccessors, sameState, node_toString

  /**
  * goalP takes a SearchNode & returns true if it's a goal
  * @param searcher the current search
  */

  public  boolean goalP(Search searcher){
    return state.goalP(searcher);
  }

  /**
  * getSuccessors for this node
  * @param searcher the current search
  * @return ArrayList of successor nodes
  */

  public ArrayList<SearchNode> getSuccessors(Search searcher){
    ArrayList<SearchState>slis = state.getSuccessors(searcher);
    ArrayList<SearchNode>nlis = new ArrayList<SearchNode>();

    for (SearchState suc_state:slis){
    	 SearchNode n = new SearchNode(suc_state);
    	 nlis.add(n);
    }
    return nlis;
  }

  /**
  * sameState - does another node have same state as this one?
  * @param n2 the other node
  */

  public boolean sameState(SearchNode n2){
    return state.sameState(n2.get_State());
  }

  public  String toString(){
    return "has state " + state.toString();
  }


}
