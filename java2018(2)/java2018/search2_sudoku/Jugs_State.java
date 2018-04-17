/**
* Jugs_State.java
* State in a jugs problem
*/

import java.util.*;

public class Jugs_State extends Search_State{

  private int j1; //content of jug1
  private int j2; //content of jug2
	
  /** 
  * constructor
  * @param j1c content of jug 1
  * @param j2c content of jug2
  */
  
  public Jugs_State(int j1c, int j2c){
    j1=j1c;
    j2=j2c;
  }
  
  /**
  * accessor for content of jug1
  */
  
  public int get_j1() {return j1;};
  
  /**
  * accessor for content of jug2
  */
  
  public int get_j2() {return j2;};
      
  /**
  * goalP
  * @param searcher - the current search
  */
  
  public boolean goalP(Search searcher) { 
    Jugs_Search jsearcher = (Jugs_Search) searcher;
    int tar=jsearcher.get_Target(); // get target amount
    return ((j1 == tar) || (j2 == tar));
  }

   /**
  * Get_Successors
  * @param searcher - the current search
  */
  	
  public ArrayList get_Successors (Search searcher) {
    Jugs_Search jsearcher = (Jugs_Search) searcher;
    int cap1=jsearcher.get_cap1();
    int cap2=jsearcher.get_cap2();
    int j1_space=cap1-j1;
    int j2_space=cap2-j2;
    
    ArrayList slis=new ArrayList();
		
    if (j1_space > 0) slis.add(new Jugs_State(cap1,j2)); //fill jug1
    if (j2_space > 0) slis.add(new Jugs_State(j1,cap2)); //fill jug2
    if (j1 != 0) slis.add(new Jugs_State(0,j2));         //empty j1
    if (j2 != 0) slis.add(new Jugs_State(j1,0));         //empty j2
    if ((j1 != 0) && (j2_space != 0)) {                     //pour from j1 into j2
      if (j1 > j2_space) 
	     {slis.add(new Jugs_State(j1-j2_space, cap2));}
      else   
	     {slis.add(new Jugs_State(0, j1+j2));};
      };
    if ((j2 != 0) && (j1_space != 0)) {                     //pour from j2 into j1
      if (j2 > j1_space) 
	     {slis.add(new Jugs_State(cap1, j2-j1_space));}
      else    
	     {slis.add(new Jugs_State(j1+j2,0));};
      };
    return slis;
              
  }
  
  /** 
  * same_State - do 2 Jugs_Search_Nodes have the same state?
  * @param s2 second state
  */

  public boolean same_State (Search_State s2) {
    Jugs_State js2= (Jugs_State) s2;
	
    return ((j1==js2.get_j1())&& (j2==js2.get_j2()));
    }
  

  /** 
  * toString
  */
  public String toString () {
    return "Jug State: Jug1-> "+j1+" Jug2-> "+j2;
    }
    
	
    
}



