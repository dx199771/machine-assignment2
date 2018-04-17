/**
*	State in a state-space search
*	abstract class
*   must implement goalP, getSuccessors, sameState, toString
*   variable cost version - has localCost variable
*   2013 version
*/

import java.util.*;

public abstract class SearchState {

  // change from search2 - need to give local cost
  protected int localCost;

  /**
  * accessor for local cost
  */

  public int getLocalCost(){
    return localCost;
  }

  // must implement goalP, getSuccessors, sameState, toString

  // goalP takes a SearchNode & returns a boolean if it's a goal

  abstract boolean goalP(Search searcher);
  abstract ArrayList<SearchState> getSuccessors(Search searcher);
  abstract boolean sameState(SearchState n2);

}
