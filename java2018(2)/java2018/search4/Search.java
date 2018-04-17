/*
	Search.java - abstract class specialising to MapSearch etc
	This version supports A*
	See //A* for changes
	2013 version
*/

import java.util.*;
import sheffield.*;

public abstract class Search {

  protected SearchNode initNode; //initial node
  protected SearchNode currentNode; // current node
  protected SearchNode old_node; //node found on open with same state as new one
  protected ArrayList<SearchNode> open;  //open - list of SearchNodes
  protected ArrayList<SearchNode> closed; //closed - .......
  protected ArrayList<SearchNode> successorNodes; //used in expand & vetSuccessors

  protected EasyWriter scr;




  /**
  * run a search
  * @param initState initial state
  * @param strat - String specifying strategy
  * @return indication of success or failure
  */

   public  String runSearch (SearchState initState, String strat) {

    initNode= new SearchNode(initState,0,0); // create initial node
    initNode.setGlobalCost(0); //change from search2

    scr=new EasyWriter();

	//change from search1 - print strategy
	scr.println("Starting "+strat+" Search");

	open=new ArrayList<SearchNode>(); // initial open, closed
	open.add(initNode);
	closed=new ArrayList<SearchNode>();

	int cnum = 1;

	while (!open.isEmpty()) {

	    // print contents of open
	    scr.println("-------------------------");
	    scr.println("iteration no "+cnum);
	    scr.println("open is");
	    for (SearchNode nn: open) {
	          String nodestr=nn.toString();
		      scr.println(nodestr);
	    }

	    selectNode(strat); // change from search1 -selectNode selects next node given strategy,
	    // makes it currentNode & removes it from open
	    scr.println("Current node: "+currentNode.toString());

	    if (currentNode.goalP(this)) return reportSuccess();  //success
	    //change from search1 - call reportSuccess

	    expand(); // go again
	    closed.add(currentNode); // put current node on closed
	    cnum=cnum+1;
	};

	return "Search Fails";  // out of the while loop - failure

	}

  /**
  * runSearchE
  * runSearch without printout
  * returns efficiency - 0 for failure
  * @param initState initial state
  * @param strat - String specifying strategy
  * @return indication of success or failure
  */

   public  float runSearchE (SearchState initState, String strat) {

    initNode= new SearchNode(initState,0,0); // create initial node
    initNode.setGlobalCost(0); //change from search2

    scr=new EasyWriter();

	open=new ArrayList<SearchNode>(); // initial open, closed
	open.add(initNode);
	closed=new ArrayList<SearchNode>();

	int cnum = 1;

	while (!open.isEmpty()) {

	    selectNode(strat); // change from search1 -selectNode selects next node given strategy,

	    if (currentNode.goalP(this)) return reportSuccessE();  //success
	    //change from search1 - call reportSuccess

	    expand(); // go again
	    closed.add(currentNode); // put current node on closed
	    cnum=cnum+1;
	};

	return 0;  // out of the while loop - failure

	}


    // expand current node

    private void expand () {
	// get all successor nodes - as ArrayList of Objects

	successorNodes = currentNode.getSuccessors(this); //pass search instance
	// change from search2
    // set global costs and parents for successors
    // A* - set estTotalCost

    for (SearchNode snode: successorNodes){
        snode.setGlobalCost(currentNode.getGlobalCost()+ snode.getLocalCost());
        snode.setParent(currentNode);
        snode.setestTotalCost(snode.getGlobalCost()+snode.getestRemCost()); //A*
        }

	vetSuccessors(); //filter out unwanted - DP check

	//add surviving nodes to open
	for (SearchNode snode: successorNodes) open.add(snode);
    }


  // vet the successors
  // A* - can't ignore nodes already closed

	private void vetSuccessors() {
	    ArrayList<SearchNode> vslis = new ArrayList<SearchNode>();

	    for (SearchNode snode: successorNodes){
          if (onOpen(snode)) { //on open - usual DP check
          	if (snode.getGlobalCost()<=old_node.getGlobalCost()) {
          		old_node.setParent(snode.getParent()); //better route, modify node
          		old_node.setGlobalCost(snode.getGlobalCost());
          		old_node.setLocalCost(snode.getLocalCost());
                old_node.setestTotalCost(snode.getestTotalCost());
                };
            }
            else {
            	if (onClosed(snode)) { //A* - on closed - DP check again
            		if (snode.getGlobalCost()<=old_node.getGlobalCost()) {
            			old_node.setParent(snode.getParent()); //better route, modify node
                        old_node.setGlobalCost(snode.getGlobalCost());
                        old_node.setLocalCost(snode.getLocalCost());
                        old_node.setestTotalCost(snode.getestTotalCost());
                        open.add(old_node); //A* - add the node back to open
                        closed.remove(old_node); //A* - & remove it from closed
                    };
                }
            	else vslis.add(snode); //not seen before
            };
        };
        successorNodes=vslis;
    }

    //onClosed - is the state for a node the same as one on closed?
    //A* - if node found, remember it in old_node

    private boolean onClosed(SearchNode newNode){
	boolean ans = false;
	Iterator ic = closed.iterator();
    while ((ic.hasNext())&& !ans){ //there can only be one node on open with same state
      SearchNode closedNode = (SearchNode) ic.next();
      if (newNode.sameState(closedNode)) {
        ans=true;
        old_node=closedNode;
      }
    }
	return ans;
  }

  //onOpen - is the state for a node the same as one on open?
  // if node found, remember it in old_node

  private boolean onOpen(SearchNode newNode){
	boolean ans = false;
    Iterator ic = open.iterator();
    while ((ic.hasNext())&& !ans){ //there can only be one node on open with same state
      SearchNode openNode = (SearchNode) ic.next();
      if (newNode.sameState(openNode)) {
        ans=true;
        old_node=openNode;
      }
    }
	return ans;
  }


   //Selection Strategies


    private void selectNode(String strat) {
	if (strat== "depthFirst")
      depthFirst();
    else
      if(strat=="breadthFirst")
        breadthFirst();
      else
       if(strat=="branchAndBound")
        branchAndBound();
      else AStar();
    }

    private void depthFirst () {
    	int osize=open.size();
		currentNode= (SearchNode) open.get(osize-1); // last node added to open
		open.remove(osize-1); //remove it
	}

	private void breadthFirst(){
		currentNode= (SearchNode) open.get(0); //first node on open
		open.remove(0);
	}

    //change from search2
    private void branchAndBound(){

      Iterator i = open.iterator();
      SearchNode minCostNode=(SearchNode) i.next();
      for (;i.hasNext();){
        SearchNode n=(SearchNode) i.next();
        if (n.getGlobalCost()<minCostNode.getGlobalCost()){
          minCostNode=n;};
      };

      currentNode=minCostNode;
      open.remove(minCostNode);
    }

    //A* - select node according to estTotalCost

	private void AStar(){

      Iterator i = open.iterator();
      SearchNode minCostNode=(SearchNode) i.next();
      for (;i.hasNext();){
        SearchNode n=(SearchNode) i.next();
        if (n.getestTotalCost()<minCostNode.getestTotalCost()){
          minCostNode=n;};
      };

      currentNode=minCostNode;
      open.remove(minCostNode);
    }

		//change from search1

	// report success - reconstruct path, convert to string & return

    private String reportSuccess(){

	SearchNode n = currentNode;
	StringBuffer buf = new StringBuffer(n.toString());
	int plen=1;

	while (n.getParent() != null){
	    buf.insert(0,"\n");
	    n=n.getParent();
	    buf.insert(0,n.toString());
	    plen=plen+1;
		}

	scr.println("=========================== \n");
	scr.println("Search Succeeds");

	scr.println("Efficiency "+ ((float) plen/(closed.size()+1)));
	scr.println("Solution Path\n");
	return buf.toString();
    }

   // reportSuccess for runSearcE

    private float reportSuccessE(){

	SearchNode n = currentNode;
	int plen=1;

	while (n.getParent() != null){
	    n=n.getParent();
	    plen=plen+1;
		}

	return (float) plen/(closed.size()+1);
    }
}
