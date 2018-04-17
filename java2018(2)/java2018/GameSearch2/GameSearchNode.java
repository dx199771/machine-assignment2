/**
*	GameSearchNode
*   Node in a Game search
*   Must implement goalP, getSuccessors, sameState, node_toString
*   node has parent & successors slots
*   node has level slot -1+level of parent
*   node has outcome slot - win/lose if known
*   @author Phil Green
*   First version 11/1/2004
*/


import java.util.*;

public  class GameSearchNode {

  private GameSearchState state;
  private GameSearchNode parent; // the parent node
  private ArrayList<GameSearchNode> children = new ArrayList<GameSearchNode>(); //successor nodes in the game tree
  private int level=1; // the level of this node in the game tree - defaults to 1
  private String outcome = "unknown"; //the outcome of the game

   /**
  * constructor
  * @param s a Search_State, searcher a GameSearch
  * sets the outcome on creation
  */

  public GameSearchNode (GameSearchState s, GameSearch searcher){
  	state= (GameSearchState) s;
  	outcome=state.result(searcher);} //win, loss or unknown

  /**
  * resultP takes a GameSearchNode & returns true if
  * it's known to be win or loss for player to move
  */

  public  boolean resultP(){if (outcome == "unknown") {return false;} else {return true;}}


  //accessors and modifiers

  /**
  * return the state associated with this node
  */

  public GameSearchState getState(){return state;}

  /**
  * return the parent of this node
  */
  public GameSearchNode getParent(){return parent;}

  /**
  * set the parent of this node
  * @param n the parent node
  */
  public void setParent(GameSearchNode n){parent=n;}

  /**
  * return the children of this node
  */

  public ArrayList<GameSearchNode> getChildren(){return children;}

  /**
  * add a node to this node's children
  * @param n, the new child node
  */
  public void addChild(GameSearchNode n){children.add(n);}

  /**
  * return the level of this node in the tree
  */

  public int getLevel(){return level;}

  /**
  * set the level of this node in the tree
  * @param l, the level
  */

  public void setLevel(int l){level = l;}

  /**
  * return the outcome for this node (win, loss, unknown)
  */

  public String getOutcome(){return outcome;}

  /**
   * set this node's outcome (win,loss,unknown)
   * @ param s, the outcome
   */

  public void setOutcome(String s){outcome =s;}

  // must implement resultP, getSuccessors, sameState, node_toString


  /**
  * getSuccessors for this node
  * @param searcher the current search
  * @return ArrayList of successor nodes
  */

  public ArrayList getSuccessors(GameSearch searcher){
    ArrayList<GameSearchState> slis = state.getSuccessors(searcher);
    ArrayList<GameSearchNode> nlis= new ArrayList();

    for (GameSearchState sucState: slis) {
      GameSearchNode n = new GameSearchNode(sucState, searcher);
      nlis.add(n);
    }
    return nlis;
  }

  /**
  * sameState - does another node have same state as this one?
  * @param n2 the other node
  */

  public boolean sameState(GameSearchNode n2){
    return state.sameState(n2.getState());
  }

 /**
  * toString
  * prints the state & level
  */
  public  String toString(){
    return "Node at level "+level+" has state " + state.toString();
  }


}
