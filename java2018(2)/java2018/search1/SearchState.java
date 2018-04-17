/**
*	State in a state-space search
*	abstract class
*   must implement goalP, getSuccessors, sameState, toString
*   2013 version
*/

import java.util.*;

public abstract class SearchState {

    /**
    * goalP takes a SearchNode & returns a boolean if it's a goal
    */
    abstract boolean goalP(Search searcher);

    /** getSuccessors returns an ArrayList of states which are successors to the
    * current state in a given search
    */

    abstract ArrayList<SearchState> getSuccessors(Search searcher);

    /**
    * sameState: is this state identical to a given one?
    */

    abstract boolean sameState(SearchState n2);

}
