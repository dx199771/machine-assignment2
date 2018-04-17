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
  * @param s a Search_State
  */

  public GameSearchNode(GameSearchState s){state= (GameSearchState) s;}


  //accessors and modifiers

  /**
  * return the state associated with this node
  */

  public GameSearchState get_State(){return state;}

  /**
  * return the parent of this node
  */
  public GameSearchNode get_Parent(){return parent;}

  /**
  * set the parent of this node
  * @param n the parent node
  */
  public void set_Parent(GameSearchNode n){parent=n;}

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
  * resultP takes a GameSearchNode & returns true if
  * it's known to be win or loss for player to move
  * state.result must return "win","loss" or "unknown"
  * @param searcher the current search
  */

  public  boolean resultP(GameSearch searcher){
  	String res=state.result(searcher);
  	outcome=res;
  	if (outcome == "unknown") {return false;} else {return true;}
  }

  /**
  * getSuccessors for this node
  * @param searcher the current search
  * @return ArrayList of successor nodes
  */

  public ArrayList getSuccessors(GameSearch searcher){
    ArrayList<GameSearchState> slis = state.getSuccessors(searcher);
    ArrayList<GameSearchNode> nlis= new ArrayList();

    for (GameSearchState suc_state: slis) {
      GameSearchNode n = new GameSearchNode(suc_state);
      nlis.add(n);
    }
    return nlis;
  }

  /**
  * sameState - does another node have same state as this one?
  * & is it at the same level?
  * @param n2 the other node
  */

  public boolean sameState(GameSearchNode n2){
    return (state.sameState(n2.get_State())&&
    		(this.getLevel()==n2.getLevel()));
  }

 /**
  * toString
  * prints the state & level
  */
  public  String toString(){
    return "Node at level "+level+" has state " + state.toString();
  }


}
