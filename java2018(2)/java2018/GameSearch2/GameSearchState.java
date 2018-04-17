/**
*	GameSearch_State
*	abstract class
*   stat in a game tree
*   must implement result, get_Successors, same_State, toString
*   @author Phil Green
*   First vesrion 11/1/2004
*/

import java.util.*;

public abstract class GameSearchState {

    /**
    * result takes a GameSearch_Node & returns "win", "loss" or "unknown"
    * @param searcher, the current search
    */

    abstract String result(GameSearch searcher);

    /**
     *get_Successors returns an ArrayList of states which are successors to the
     * current state in a given search
     *@param searcher, the current search
    */

    abstract ArrayList<GameSearchState> getSuccessors(GameSearch searcher);

    /**
    * same_State: is this state identical to a given one?
    * @param n2, the other state
    */

    abstract boolean sameState(GameSearchState n2);

}
