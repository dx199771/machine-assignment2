 /**
   * Run_Epuzz_Search.java
   *
   *
   * Created: Fri Dec  1 12:59:33 2000
   *
   * @author <a href="mailto: "Phil Green</a>
   * @version
   
   run an 8-puzzle search

 */

import sheffield.*;
import java.util.*;
import Search;
import Search_Node;
import Search_State;
import Epuzz_Search;
import Epuzz_State;

public class Run_Epuzz_Search {

public static void main(String[] arg) {
    
    // create an EasyWriter
    
    EasyWriter screen = new EasyWriter();
    
    int[][] tar = {{1,2,3},{4,5,6},{7,8,0}};
    //int[][] tar = {{1,0,3},{4,2,6},{7,5,8}};
    Epuzz_Search searcher = new Epuzz_Search(tar);
    //Search_State init_state = (Search_State) new Epuzz_State(1,2,3,4,5,6,7,0,8);
    Search_State init_state1 = (Search_State) new Epuzz_State(1,0,3,4,2,6,7,5,8);
    Search_State init_state2 = (Search_State) new Epuzz_State(4,1,3,7,2,5,0,8,6);
    Search_State init_state3 = (Search_State) new Epuzz_State(2,3,6,1,5,8,4,7,0);
    
    /*
    ArrayList al=init_state.get_Successors(searcher);
    for (Iterator it = al.iterator();it.hasNext(); ){
    	Epuzz_State s = (Epuzz_State)it.next();
    	screen.println(s.toString());
    }
    */
    
	//change from search1 - specify strategy
   //String resb = searcher.run_Search(init_state1, "breadth_first");
   //screen.println(resb);
   String resd = searcher.run_Search(init_state3, "depth_first");
   screen.println(resd);
}
}   


