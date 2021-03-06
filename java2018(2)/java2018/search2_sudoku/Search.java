/**
*	Search.java - 
*   abstract class specialising to Jugs_Search etc
*   includes depth-first, breadth first strategies
*   reconstructs solution path
*/

import java.util.*;
import Search_Node;
import Search_State;
import sheffield.*;

public abstract class Search {

    protected Search_Node init_node; //initial node
    protected Search_Node current_node; // current node
    
    protected ArrayList open; //open - list of Search_Nodes
    protected ArrayList closed; //closed - .......
	protected ArrayList successor_nodes; //used in expand & vet_successors
    
	protected EasyWriter scr;
      	
    /** 
    * run a search 
    * @param init_state initial state
    * @param strat - String specifying strategy
    * @return indication of success or failure
	*/
	
	
    public  String run_Search (Search_State init_state, String strat) {

      
    	init_node= new Search_Node(init_state); // create initial node
    
		scr=new EasyWriter();
	
		scr.println("Starting "+strat+" Search"); //change from search1 - print strategy
		
		open=new ArrayList(); // initial open, closed
		open.add(init_node);
		
		closed=new ArrayList();
	
		int cnum = 1;		// counts the iterations
		
		while (!open.isEmpty()) {
		    scr.println("-------------------------");
		    scr.println("iteration no "+cnum);
		    
		    /*
	    	//print contents of open
	    	//scr.println("-------------------------");
	    	//int ct=cnum%1000;
	    	//if (ct==0) scr.println("iteration no "+cnum);
	    	//scr.println("iteration no "+cnum);
	    	//scr.println("open is");
	    	for (Iterator it = open.iterator();it.hasNext(); ){
		
				Search_Node nn = (Search_Node) it.next();
		
				String nodestr=nn.toString();
		
				scr.println(nodestr);
	    	}
	        */
	        
	        
	    	select_Node(strat); // change from search1 - select_Node selects next node, given strategy
	                   // makes it current_node & removes it from open
	    	scr.println("Current node "+current_node.toString());
	    	
	    	if (current_node.goalP(this)) return report_Success();  //success
	    	                             //change from search1 - call report_Success 
	                                     //- must pass search instance to goalP
	    	expand(); // go again
	    	
	    	closed.add(current_node); // put current node on closed
	    	
	    	cnum=cnum+1;
		};

		return "Search Fails";  // out of the while loop - failure

	}
	
    // expand current node
			
    private void expand () {
		// get all successor nodes - as ArrayList of Objects
	
		successor_nodes = current_node.get_Successors(this); //pass search instance
	
		vet_Successors(); //filter out unwanted - DP check

		//add surviving nodes to open
		// change from search1 - set their parents to current_node      
		for (Iterator i = successor_nodes.iterator(); i.hasNext();){
			Search_Node snode = (Search_Node) i.next();
			snode.set_Parent(current_node);
			open.add(snode);
		}
	    
	}

    // vet the successors - reject any whose states are on open or closed
		
	private void vet_Successors() {
	    
	    ArrayList vslis = new ArrayList();
	    
	    for (Iterator i = successor_nodes.iterator(); i.hasNext();){
			Search_Node snode = (Search_Node) i.next();
			if (!(on_Closed(snode)) && !(on_Open(snode))) vslis.add(snode);
	    };
	    successor_nodes=vslis;
	}

    //on_Closed - is the state for a node the same as one on closed?

    private boolean on_Closed(Search_Node new_node){
		boolean ans = false;
		Iterator ic = closed.iterator();
		while (ic.hasNext()&&(ans==false)){
		 Search_Node closed_node = (Search_Node) ic.next();
	     if (new_node.same_State(closed_node)) ans=true;
	     }	
		return ans;
    }
	  //on_Closed - is the state for a node the same as one on closed?

    private boolean on_Open(Search_Node new_node){
		boolean ans = false;
		Iterator ic = open.iterator();
		while (ic.hasNext()&&(ans==false)){
		 Search_Node open_node = (Search_Node) ic.next();
	     if (new_node.same_State(open_node)) ans=true;
		}
		return ans;
    }   
		
    // select the next node - change from search1
    // call depth_first or breadth_first dependent on strat
    // defaults to breadth_first
	
    private void select_Node(String strat) {
    	if (strat== "depth_first") depth_first(); else breadth_first();
	}
    	
    private void depth_first () {
    	int osize=open.size();
		current_node= (Search_Node) open.get(osize-1); // last node added to open
		open.remove(osize-1); //remove it
	}
	
	private void breadth_first(){
		current_node= (Search_Node) open.get(0); //first node on open
		open.remove(0);
	}
	
	//change from search1
	
	// report success - reconstruct path, convert to string & return
		
    private String report_Success(){

	Search_Node n = current_node;
	StringBuffer buf = new StringBuffer(n.toString());
	int plen=1;
	
	while (n.get_Parent() != null){
	    buf.insert(0,"\n");
	    n=n.get_Parent();
	    buf.insert(0,n.toString());
	    plen=plen+1;	    
		}
	
	scr.println("=========================== \n");
	scr.println("Search Succeeds");

	scr.println("Efficiency "+ ((float) plen/(closed.size()+1)));
	scr.println("Solution Path\n");
	return buf.toString();
    }    
	
}
