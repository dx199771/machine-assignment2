 /**
   * Run_Equaliser_Search.java
   *
   *
   * Created: Fri Dec  1 12:59:33 2000
   *
   * @author <a href="mailto: "Phil Green</a>
   * @version
   
   run an equaliser search

 */

import sheffield.*;
import java.util.*;
import Search;
import Search_Node;
import Search_State;
import Equaliser_Search;
import Equaliser_State;

public class Run_Equaliser_Search {

public static void main(String[] arg) {
    
    // create an EasyWriter
    
    EasyWriter screen = new EasyWriter();
    
    Equaliser_Search searcher = new Equaliser_Search(8);
    Search_State init_state = (Search_State) new Equaliser_State(6,7,11);
    
    
    //Search_State s2 = (Search_State) new Equaliser_State(4,8,12);
    //screen.println(init_state.same_State(s2));
    
    
    
    
    // ArrayList al=init_state.get_Successors(searcher);
    
    
	//change from search1 - specify strategy
   //String resb = searcher.run_Search(init_state, "breadth_first");
   //screen.println(resb);
   String resd = searcher.run_Search(init_state, "depth_first");
   screen.println(resd);
}
}   


