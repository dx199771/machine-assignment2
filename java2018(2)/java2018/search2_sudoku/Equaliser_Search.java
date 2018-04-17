/*
	Equaliser_Search.java
	
	search for jugs problems
*/


import Search;
import Search_Node;

import java.util.*;

public class Equaliser_Search extends Search {  

    private int target; //target number in each pile
    
    // constructor  takes initial arrangement

    public  Equaliser_Search (int tar) {
	
	    target=tar;
	    
    }
    //accessors
  
    public int get_Target(){
	return target;
    }
}
    









