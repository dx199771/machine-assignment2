 /**
   * Run_Sudoku_Search.java
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

public class Run_Sudoku_Search {

public static void main(String[] arg) {
    
    // create an EasyWriter
    
    EasyWriter screen = new EasyWriter();
    
   //an 'easy' sudoku puzzle
   int[][] s_easy = {{9,4,0,1,0,2,0,5,8},
                     {6,0,0,0,5,0,0,0,4},
   	                 {0,0,2,4,0,3,1,0,0},
   	                 {0,2,0,0,0,0,0,6,0},
   	                 {5,0,8,0,2,0,4,0,1},
   	                 {0,6,0,0,0,0,0,8,0},
   	                 {0,0,1,6,0,8,7,0,0},
   	                 {7,0,0,0,4,0,0,0,3},
   	                 {4,3,0,5,0,9,0,1,2}};
   	
   	// a 'medium' puzzle
   	int[][] s_mid = {{0,3,0,2,0,6,5,0,0},
                     {0,0,0,3,0,0,0,0,8},
   	                 {0,0,4,0,0,9,0,0,7},
   	                 {0,0,5,0,0,8,0,6,0},
   	                 {0,6,0,0,7,0,0,5,0},
   	                 {0,7,0,6,0,0,4,0,0},
   	                 {8,0,0,5,0,0,3,0,0},
   	                 {9,0,0,0,0,2,0,0,0},
   	                 {0,0,6,7,0,1,0,4,0}};
   	                 
   	// problem state
   	
   	int[][] prob = {{7,3,9,2,8,6,5,1,4},
                    {0,0,1,3,4,7,0,9,8},
   	                {0,0,4,1,5,9,0,3,7},
   	                {0,9,5,4,2,8,0,6,0},
   	                {0,6,8,0,7,3,0,5,0},
   	                {3,7,2,6,1,5,4,8,3},
   	                {8,1,7,5,9,4,3,2,6},
   	                {9,4,3,8,6,2,1,7,5},
   	                {0,0,6,7,3,1,8,4,9}};
   	                 
   	//search state for it
   	Search_State init_state1 = (Search_State) new Sudoku_State(s_mid);
   	
   	//Sudoku Search
   	Sudoku_Search searcher = new Sudoku_Search();
    
    /*
    Sudoku_State s2=new Sudoku_State(prob);
   	screen.println(s2.valid_State());
   	
   	
   	
   	ArrayList ans=init_state1.get_Successors(searcher);
   	screen.println(ans.size());
   	screen.println(ans.get(0));
   	*/
   	
   	
   	
   	String resd = searcher.run_Search(init_state1, "depth_first");
   	screen.println(resd);
   	
   	
}
}   


