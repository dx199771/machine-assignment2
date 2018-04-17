/*
	Epuzz_Search.java
	
	search for 8 puzzle problems
*/


import Search;
import Search_Node;

import java.util.*;

public class Epuzz_Search extends Search {  

    private int[][] target; //target arrangement
    
    // constructor  takes initial arrangement

    public  Epuzz_Search (int[][] tar) {
	
	    target=tar;
	    
    }
    //accessors
  
    public int[][] get_Target(){
	    return target;
    }
}
    









