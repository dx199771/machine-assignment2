/**
*	Jugs_Search.java
*	
*	search for jugs problems
*/


import Search;
import Search_Node;

import java.util.*;

public class Jugs_Search extends Search {  

    private int cap1; //capacity of jug1
    private int cap2; //........... jug2
    private int target; //target
    
    /** constructor  takes jug capacities and target
    * @param c1 capacity of jug1
    * @param c2 capacity of jug2
    * @param tar target amount to be measured
    */

    public  Jugs_Search (int c1, int c2, int tar) {
	
	    cap1=c1;
	    cap2=c2;
	    target=tar;
	    
    }
    
    /**
    * accessor for jug1 capacity
    */

    public int get_cap1(){
	return cap1;
    }
    
    /**
    * accessor for jug2 capacity
    */
    
     public int get_cap2(){
	return cap2;
    }
    
    /**
    * accessor for target
    */
    
     public int get_Target(){
	return target;
    }
}
    









