/*
	State in an equaliser problem
*/

import java.util.*;

public class Equaliser_State extends Search_State{

  private int p[]; //content of piles in ascending order
  
	
  // constructor
  public Equaliser_State(int j1c, int j2c, int j3c){
  	p=new int[3];
    p[0]=j1c;
    p[1]=j2c;
    p[2]=j3c;
    
    Arrays.sort(p); // keep in ascending order
  }
  
  //accessor - needed for same state
  public int[] get_p() {return p;};

      
  // goalP
  public boolean goalP(Search searcher) { 
    Equaliser_Search esearcher = (Equaliser_Search) searcher;
    int tar=esearcher.get_Target(); // get target amount
    return ((p[0] == tar) && (p[1] == tar) && (p[2]==tar));
  }

  // Get_Successors	
  	
  public ArrayList get_Successors (Search searcher) {
    Equaliser_Search esearcher = (Equaliser_Search) searcher;
    
    ArrayList slis=new ArrayList();
    
    // since p is sorted in ascending order, only poss moves are p1->p0, p1->p1 & p2->p0
    // check that same successor is not being produced twice
    
    //move from pile 1 to pile 0
    
    if (p[1]>p[0]) slis.add(new Equaliser_State(2*p[0],p[1]-p[0], p[2]));
    
    //move from pile 2 to pile 0
    
    if (p[2]>p[0]) {
    	Equaliser_State s2= new Equaliser_State(2*p[0],p[1], p[2]-p[0]);
    	if (new_state(s2,slis)) slis.add(s2);
    }
    
    //move from pile 2 to pile 1
    
    if (p[2]>p[1]) {
    	Equaliser_State s3= new Equaliser_State(p[0],2*p[1], p[2]-p[1]);
    	if (new_state(s3,slis)) slis.add(s3);
    }
    
    return slis;
              
  }
  
  // same_State - do 2 Equaliser_Search_Nodes have the same state?

  public boolean same_State (Search_State s2) {
    Equaliser_State js2= (Equaliser_State) s2;
    return Arrays.equals(p,js2.get_p());
    }
  

// toString 
  public String toString () {
    return "Equaliser State: "+p[0]+" "+p[1]+" "+p[2];
    }
    
// new_state - returns True if given state not already in slis

  private boolean new_state(Equaliser_State s, ArrayList slis) {
    boolean res=true;
    for (Iterator it = slis.iterator();it.hasNext();){
    	Search_State s2=(Search_State) it.next();
    	if (s.same_State(s2)) res=false;
    }
    return res;
}

}



