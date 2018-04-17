/**
*	Node in a state-space search
*   variable costs case
*   Must implement goalP, getSuccessors, sameState, node_toString
*   node has local cost & global cost now
*   This version for A*
*   Mods indicated by //A*
*   2013 version
*/


import java.util.*;

public  class SearchNode {

  private SearchState state;
  //change from search2
  private int Cost;
  private int globalCost;
  private int localCost;
  private int estRemCost; //A*
  private int estTotalCost; //A*


  private SearchNode parent; // the parent node


  /**
  * constructor
  * @param s a SearchState
  * @param lc local cost of getting to this node from its predecessor
  * @param erc estimated remaining cost
  */

  public SearchNode(SearchState s, int lc, int erc){
    state= (SearchState) s;
    localCost=lc;  //change from search2
    estRemCost=erc;
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

  /**
  * mutator for localcost
  *
  */

  public void setLocalCost(int lc){
    localCost=lc;
  }

  /**
  * accessor for localcost
  *
  */

  public int getLocalCost(){
    return localCost;
  }

  /**
  * mutator for globalcost
  *
  */
  public void setGlobalCost(int lc){
    globalCost=lc;
  }

  /**
  * acccessor for globalcost
  *
  */
  public int getGlobalCost(){
    return globalCost;
  }


  /**
  * mutator for estremcost
  * for A*
  */
  public void setestRemCost(int erc){
    estRemCost=erc;
  }

  /**
  * accessor for estremcost
  * for A*
  */
  public int getestRemCost(){
    return estRemCost;
  }

  /**
  * mutator for esttotalcost
  * for A*
  */
  public void setestTotalCost(int erc){
    estTotalCost=erc;
  }

  /**
  * accessor for esttotalcost
  * for A*
  */
  public int getestTotalCost(){
    return estTotalCost;
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
  * A* - adds estremcost to node
  * @param searcher the current search
  * @return ArrayList of successor nodes
  */

  public ArrayList getSuccessors(Search searcher){
    ArrayList<SearchState> slis = state.getSuccessors(searcher);
    ArrayList<SearchNode> nlis= new ArrayList<SearchNode>();

    for (SearchState suc_state:slis){
         SearchNode n = new SearchNode(suc_state, suc_state.getLocalCost(), suc_state.getestRemCost());
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
  	String parent_state;
  	if (parent==null) parent_state="null"; else parent_state=parent.get_State().toString();
    return "\n++++++node+++++\nstate "+state.toString()+"\nparent "+parent_state+" lc "+localCost+" gc "+globalCost+" erc "+estRemCost+" etc "+estTotalCost+"\n++++end node++++";
  }


}
