/**
*	Search.java - abstract class specialising to JugsSearch etc
*   2013 version
*/

import java.util.*;
import sheffield.*;



public abstract class Search {

    protected SearchNode initNode; //initial node
    protected SearchNode currentNode; // current node

    protected ArrayList<SearchNode> open; //open - list of SearchNodes
    protected ArrayList<SearchNode> closed; //closed - .......
	protected ArrayList<SearchNode> successorNodes; //used in expand & vetSuccessors

	protected EasyWriter scr;

    /**
    * run a search
    * @param initState initial state
    * @return indication of success or failure
	*/

    public  String runSearch (SearchState initState) {


    	initNode= new SearchNode(initState); // create initial node

		scr=new EasyWriter();

		scr.println("Starting Search");

		open=new ArrayList<SearchNode>(); // initial open, closed
		open.add(initNode);

		closed=new ArrayList<SearchNode>();

		int cnum = 1;		// counts the iterations

		while (!open.isEmpty()) {

	    	// print contents of open
	    	scr.println("-------------------------");
	    	scr.println("iteration no "+cnum);
	    	scr.println("open is");
	    	for (SearchNode nn: open) {
	          String nodestr=nn.toString();
		      scr.println(nodestr);
	    	}

	    	selectNode(); // selectNode selects next node,
	                   // makes it currentNode & removes it from open
	    	scr.println("Current node "+currentNode.toString());

	    	if (currentNode.goalP(this)) return "Search Succeeds";  //success
	                                     //- must pass search instance to goalP
	    	expand(); // go again

	    	closed.add(currentNode); // put current node on closed

	    	cnum=cnum+1;
		};

		return "Search Fails";  // out of the while loop - failure

	}

    // expand current node

    private void expand () {
		// get all successor nodes - ArrayList SearchNodes

		successorNodes = currentNode.getSuccessors(this); //pass search instance

		vetSuccessors(); //filter out unwanted - DP check

		//add surviving nodes to open

		for (SearchNode snode:successorNodes) open.add(snode);

	}

    // vet the successors - reject any whose states are on open or closed

	private void vetSuccessors() {

	    ArrayList<SearchNode> vslis = new ArrayList<SearchNode>();

	    for (SearchNode snode: successorNodes){
			if (!(onClosed(snode)) && !(onOpen(snode))) vslis.add(snode);
	    };
	    successorNodes=vslis;
	}

    //onClosed - is the state for a node the same as one on closed?

    private boolean onClosed(SearchNode newNode){
		boolean ans = false;
		for (SearchNode closedNode: closed){
		    if (newNode.sameState(closedNode)) ans=true;
        }
		return ans;
    }
	  //onOpen - is the state for a node the same as one on closed?

    private boolean onOpen(SearchNode newNode){
		boolean ans = false;
		for (SearchNode openNode: open){
			if (newNode.sameState(openNode)) ans=true;
		}
		return ans;
    }

    // select the next node - depth-first for now - remove it from open

    private void selectNode() {
    	int osize=open.size();
		currentNode= (SearchNode) open.get(osize-1); // last node added to open
		open.remove(osize-1); //remove it
	}







}
