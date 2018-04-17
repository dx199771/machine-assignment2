/**
*	GameSearch.java
*   - Game Tree Search by minimaxing * abstract class
*	specialising for particular games * explores game tree until nodes
*	representing win or lose for player about to move are reached
*   @author Phil Green
*   First version 11/1/2004
*/

import java.util.*;
import sheffield.*;

public abstract class GameSearch {

    protected GameSearchNode initNode; //initial node
    protected GameSearchNode currentNode; // current node

    protected ArrayList<GameSearchNode> open; //open - list of Search_Nodes
    protected ArrayList<GameSearchNode> closed; //closed - .......
	protected ArrayList<GameSearchNode> successorNodes; //used in expand & vetSuccessors

	protected EasyWriter scr;

    /**
    * run a search
    * @param initState initial state
    * @param strat - String specifying strategy
    * @return indication of success or failure, and next state to play if win
	*/


    public  String runGameSearch (GameSearchState initState, String strat) {


    	initNode= new GameSearchNode(initState); // create initial node

		scr=new EasyWriter();

		scr.println("STARTING "+strat+" GAME SEARCH");
		scr.println();
		scr.println("GROWING GAME TREE");
		open=new ArrayList<GameSearchNode>(); // initial open, closed
		open.add(initNode);

		closed=new ArrayList<GameSearchNode>();

		int cnum = 1;		// counts the iterations

		while (!open.isEmpty()) {
		    scr.println("  iteration no "+cnum);
	    	selectNode(strat); // selectNode selects next node, given strategy
	                   // makes it currentNode & removes it from open
	    	scr.println("    Current node "+currentNode.toString()+ " res "+currentNode.resultP(this));

	    	closed.add(currentNode); //put current node on closed
	    	if (!currentNode.resultP(this)) expand(); // if this node is not known win/lose, expand it

	    	cnum=cnum+1;
		};

		return minimax();  // ** completed search - do the minimaxing

	}

    // expand current node

    private void expand () {
		// get all successor nodes - as ArrayList of Objects

		successorNodes = currentNode.getSuccessors(this); //pass search instance

		// vetSuccessors(); //filter out unwanted - DP check - not needed?

		//add surviving nodes to open

		for (GameSearchNode snode: successorNodes){
			snode.set_Parent(currentNode);
			snode.setLevel(currentNode.getLevel() + 1);
			open.add(snode);
		}

	}

    // vet the successors - reject any whose states are on open or closed
    // state must be the same and level must be the same

	private void vetSuccessors() {

	    ArrayList<GameSearchNode> vslis = new ArrayList();

	    for (GameSearchNode snode: successorNodes){
	        if (!(onClosed(snode)) && !(onOpen(snode))) vslis.add(snode);
	    };
	    successorNodes=vslis;
	}

    //onClosed - is the state for a node the same as one on closed?

    private boolean onClosed(GameSearchNode newNode){
		boolean ans = false;
		Iterator ic = closed.iterator();
		while (ic.hasNext()&&(ans==false)){
		 GameSearchNode closedNode = (GameSearchNode) ic.next();
	     if (newNode.sameState(closedNode)) ans=true;
	     }
		return ans;
    }
	  //onOpen - is the state for a node the same as one on open?

    private boolean onOpen(GameSearchNode newNode){
		boolean ans = false;
		Iterator ic = open.iterator();
		while (ic.hasNext()&&(ans==false)){
		 GameSearchNode openNode = (GameSearchNode) ic.next();
	     if (newNode.sameState(openNode)) ans=true;
		}
		return ans;
    }

    // select the next node
    // call depth_first or breadth_first dependent on strat
    // defaults to breadth_first

    private void selectNode(String strat) {
    	if (strat== "depth_first") depth_first(); else breadth_first();
	}

    private void depth_first () {
    	int osize=open.size();
		currentNode= (GameSearchNode) open.get(osize-1); // last node added to open
		open.remove(osize-1); //remove it
	}

	private void breadth_first(){
		currentNode= (GameSearchNode) open.get(0); //first node on open
		open.remove(0);
	}

	//the minimax method

	private String minimax() {
		//set up the children for each node & find max level
		int maxlevel=1;

		for (GameSearchNode currentNode: closed){
			if (currentNode.getLevel()>maxlevel) maxlevel = currentNode.getLevel();
			if (currentNode.getLevel()>1){//add this child to cn's parent unless cn is root
				GameSearchNode p = currentNode.get_Parent();
				p.addChild(currentNode);
			}
		} // end of for

		// work back up from max level
		// if all a nodes successors are "win" its outcome is "loss"
		// otherwise it's "win"
		scr.println("-------------------");
		scr.println();
		scr.println("BACKTRACKING: tree has "+closed.size()+" nodes, max depth "+maxlevel);
		for (int lev = (maxlevel-1);lev>0;lev--) {

			for (GameSearchNode currentNode: closed){
				if ((currentNode.getLevel()==lev)&&
				    (currentNode.getOutcome()=="unknown")){ //here is one
					scr.println("  labelling "+currentNode.toString());
					String o="loss";

					for (Iterator j=currentNode.getChildren().iterator(); //look at its successors
					     (j.hasNext()&&(o=="loss"));){
					     	GameSearchNode s=(GameSearchNode)j.next();
					     	scr.println("    child "+s.toString()+" label "+s.getOutcome());
							if (s.getOutcome()=="loss") o="win"; //if one is a loss, label this node as win & stop
							} //end of if Iterator j
					currentNode.setOutcome(o);
					scr.println("  labelled "+o);
					} // end of if currentNode.getLevel
				}//end of the for i
			}//end of the for lev

		// the root of the tree must be the first node on closed
		scr.println("------------------");
		scr.println("DONE BACKTRACKING");
		GameSearchNode root = (GameSearchNode)closed.get(0);
		if (root.getOutcome() == "win"){ //win, find the move to play
			boolean movefound=false;
			Iterator k = root.getChildren().iterator();
			while (movefound==false){
				currentNode = (GameSearchNode) k.next();
				if (currentNode.getOutcome()=="loss") movefound= true;
				} //end of while
			return("  Win: next board is "+currentNode.toString());
			} //end of then clause
			else return("  Loss"); //end of if
		} //end of minimax
}
