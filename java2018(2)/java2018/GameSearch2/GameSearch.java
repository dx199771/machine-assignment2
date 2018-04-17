/**
*	GameSearch.java
*   - Game Tree Search avoiding unnecessary evaluations
*   abstract class
*	specialising for particular games
*   explores game tree until nodes
*	representing win or lose for player about to move are reached
*   a node is labelled win if one of its successors is loss, else the node is labelled loss
*   the same board state may occur at diffferent points, making different nodes
*   @author Phil Green
*   First version April 2004
*/

import java.util.*;
import sheffield.*;

public abstract class GameSearch {

    protected GameSearchNode initNode; //initial node
    protected GameSearchNode currentNode; // current node

    protected ArrayList<GameSearchNode> open; //open - list of Search_Nodes
    protected ArrayList<GameSearchNode> closed; //closed - .......
	protected ArrayList<GameSearchNode> successorNodes; //used in expand & vetSuccessors

    protected ArrayList<GameSearchNode> wins_found; //nodes found to be wins, to avoid repeated work
    protected ArrayList<GameSearchNode> losses_found; //nodes found to be losses

	protected EasyWriter scr;

    /**
    * run a search
    * @param initState initial state
    * @param strat - String specifying strategy
    * @return indication of success or failure, and next state to play if win
	*/


    public  String runGameSearch (GameSearchState initState) {

        String strat = "depth_first"; // must be depth_first search

    	initNode= new GameSearchNode(initState, this); // create initial node

		scr=new EasyWriter();

		scr.println("STARTING "+strat+" GAME SEARCH");
		if (initNode.resultP()) {
			scr.println("Trivial ");
			return initNode.getOutcome();
		}
		scr.println();
		scr.println("GROWING GAME TREE");
		open=new ArrayList<GameSearchNode>(); // initial open, closed
		open.add(initNode);

		closed=new ArrayList<GameSearchNode>();

		wins_found=new ArrayList();  //wins & losses found initially empty
		losses_found=new ArrayList();

		int cnum = 1;		// counts the iterations

		while ((!open.isEmpty())&&(!initNode.resultP())) { //stop when init node has known outcome
		    scr.println();
		    scr.println("--------------------");
		    scr.println("  iteration no "+cnum);
	    	selectNode(strat); // selectNode selects next node, given strategy
	                   // makes it currentNode & removes it from open
	    	scr.println("    Current node "+currentNode.toString()+ " outcome "+currentNode.getOutcome());

	    	closed.add(currentNode); //put current node on closed
	    	if (!currentNode.resultP()) expand(); // if this node is not known win/lose, expand it

	    	cnum=cnum+1;
          	if (cnum==1000){return ("open "+open.size()+
	    		                  " closed "+closed.size()+
	    		                  " wins found "+ wins_found.size()+
	    		                  " losses_found "+ losses_found.size());};

		};
        //out of the while - we have a solution

        scr.println("======================");
        scr.println(initNode.getOutcome());
		if (initNode.getOutcome() == "win"){ //win, find the move to play
			boolean movefound=false; //one of initNode's successors must be a loss
			Iterator k = initNode.getChildren().iterator();
			while (movefound==false){
				currentNode = (GameSearchNode) k.next();
				scr.println(currentNode.toString()+" outcome "+currentNode.getOutcome());
				if (currentNode.getOutcome()=="loss") movefound= true;
				} //end of while
			return("Win: next board is "+currentNode.toString());
			} //end of then clause
			else return("Loss"); //end of if
	}


    // expand current node

    private void expand () {
		// get all successor nodes - as ArrayList of Objects
		// they already have their outcomes filled in

		successorNodes = currentNode.getSuccessors(this); //pass search instance
		boolean allwins=true; //flag which will be true at end of loop through successors if they are all wins


		for (GameSearchNode snode: successorNodes){
			scr.println("successor "+snode.toString());
			if (!snode.resultP()) check_known_outcomes(snode); //check if we know the outcome
			scr.println(" outcome "+snode.getOutcome());
			snode.setParent(currentNode);
			currentNode.addChild(snode);
			snode.setLevel(currentNode.getLevel() + 1);

			if (snode.getOutcome()=="loss") { //successor is a loss, so currentNode is a win & we backtrack
				currentNode.setOutcome("win");
				backtrackWin(currentNode);
				allwins=false;
				break;
				}
			else if (snode.getOutcome() !="win") { //succesor with unknown outcome, goes on open
					allwins=false;
					open.add(snode);
				}
		}
		if (allwins) {
			currentNode.setOutcome("loss");
			backtrackLoss(currentNode);
		}
	}

	/*backtrack from node which has been labelled win
	  unless it's the initial node,
	    find all other nodes with same state to form a list of new wins
	    for each node m on this list.
	      set its outcome to win
	      remove its successors from open
	      check whether all its parent's children are now wins. If so, label the parent loss and backtrackLoss from there
	*/

	private void backtrackWin(GameSearchNode n){
		if (n!=initNode){
		  scr.println("       backtrackWin for "+n.toString());
		  wins_found.add(n); //add to known wins
		  ArrayList<GameSearchNode> newWins = sameState_nodes(n); //all nodes on open or closed with same state as n

		  for (GameSearchNode m: newWins){
			  m.setOutcome("win");
			  remove_successors(m); //removes successors from open

			  GameSearchNode p = m.getParent(); //checking parent for all wins
			  ArrayList clis = p.getChildren();
			  boolean allwins = true;
			  Iterator j = clis.iterator();
			  while (j.hasNext()&&allwins==true){
			      GameSearchNode c = (GameSearchNode)j.next();
				  if (c.getOutcome()!="win") allwins = false;
			  }
			  if (allwins) {
				  p.setOutcome("loss");
				  backtrackLoss(p);
			     }
		   }
		 }
	}


	/*backtrack from node which has been labelled loss
	  find all other nodes with same state to form a list of new losses
	  for each node on this list,
	    its succesors should be removed from open
	    its parent should be labelled win & we backtrackWin from there
	*/

	private void backtrackLoss(GameSearchNode n){
		if (n!=initNode){
		  scr.println("       backtrackLoss for "+n.toString());
		  losses_found.add(n);
		  ArrayList<GameSearchNode> newLosses = sameState_nodes(n); //all nodes on open or closed with same state as n
		  for (GameSearchNode m: newLosses){
			  m.setOutcome("loss");
			  remove_successors(m); //removes successors from open
			  GameSearchNode p = m.getParent();
			  p.setOutcome("win");
			  backtrackWin(p);
			  }
		}
	}

	// return a list of all nodes on open or closed with same state as node n

	private ArrayList<GameSearchNode> sameState_nodes(GameSearchNode n){
		ArrayList anslis=new ArrayList();
		for (GameSearchNode m: open){
			if (n.sameState(m)) anslis.add(m);
		}
		for (GameSearchNode m: closed){
			if (n.sameState(m)) anslis.add(m);
		}
		return anslis;
	}

	//remove children of a node which has been labelled from open

	private void remove_successors(GameSearchNode n) {
		for (GameSearchNode c: n.getChildren()){open.remove(c);}
	}


	//check whether a given node is known to be a win or a loss
	//if so, set the Outcome of this node

	private void check_known_outcomes(GameSearchNode n) {
		//scr.println("          cko "+n.toString());
		boolean found=false;
		Iterator i = wins_found.iterator();
		while ((!found)&&(i.hasNext())) {
			GameSearchNode m = (GameSearchNode)i.next();
			//scr.println("         against win "+m.toString()+" "+n.sameState(m));
			if (n.sameState(m)) found=true;
		} //end of while
		if (found) {scr.println("known win");
		                        n.setOutcome("win");}
		else {
			  Iterator j = losses_found.iterator();
		      while ((!found)&&(j.hasNext())) {
			     GameSearchNode m = (GameSearchNode)j.next();
			     //scr.println("         against loss "+m.toString()+" "+n.sameState(m));
			     if (n.sameState(m)) found=true;
		         } //end of while
		    if (found) {scr.println("known loss");
		                n.setOutcome("loss");}
		    } //end of else
	}




    private void selectNode(String strat) {
    	if (strat== "depth_first") depth_first(); else breadth_first();
	}

    private void depth_first () {
    	int osize=open.size();
		currentNode= open.get(osize-1); // last node added to open
		open.remove(osize-1); //remove it
	}

	private void breadth_first(){
		currentNode= open.get(0); //first node on open
		open.remove(0);
	}


}
