 /**
   * Run_Jugs_Search.java
   *
   *
   * Created: Fri Dec  1 12:59:33 2000
   *
   * @author <a href="mailto: "Phil Green</a>
   * @version 1
   
   run a jugs search

 */

import sheffield.*;
import java.util.*;
import Search;
import Search_Node;
import Search_State;
import Jugs_Search;
import Jugs_State;

public class Run_Jugs_Search {

public static void main(String[] arg) {
    
    // create an EasyWriter
    
    EasyWriter screen = new EasyWriter();
    
    Jugs_Search searcher = new Jugs_Search(7,4,2);
    Search_State init_state = (Search_State) new Jugs_State(0,0);

	//change from search1 - specify strategy
    //String resb = searcher.run_Search(init_state, "breadth_first");
    //screen.println(resb);
    String resd = searcher.run_Search(init_state, "depth_first");
    screen.println(resd);
    
}
}   