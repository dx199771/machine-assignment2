/*
	BChainSearch.java

	search for backward chaining

	2013 version

*/


//import Search;
//import SearchNode;

import java.util.*;

public class BChainSearch extends Search {

    private RuleSet rules; //target arrangement

    // constructor  takes initial arrangement

    public  BChainSearch (RuleSet rs) {rules=rs;}

    //accessors

    public RuleSet getRules(){
	    return rules;
    }
}










