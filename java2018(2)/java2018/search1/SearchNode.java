/**
*	Node in a state-space search
*   Must implement goalP, getSuccessors, sameState, toString
*   2013 version
*/

import java.util.*;

public  class SearchNode {

  private SearchState state;

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

  public SearchState getState(){
    return state;
  }

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
    ArrayList<SearchNode>nlis= new ArrayList<SearchNode>();

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
    return state.sameState(n2.getState());
  }

  /**
  * toString returns String describing node
  */

  public  String toString(){
    return "Node with state " + state.toString();
  }


}
