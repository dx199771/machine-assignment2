/*
	State in an 8 puzzle problem
*/

import java.util.*;
import sheffield.*;

public class Epuzz_State extends Search_State{

  private int p[][]; //3x3 matrix represents state
  
	
  // constructor supplying each element in row order
  public Epuzz_State(int n00, int n01, int n02,int n10, int n11, int n12,int n20, int n21, int n22){
  	p=new int[3][3];
    p[0][0]=n00;p[0][1]=n01;p[0][2]=n02;
    p[1][0]=n10;p[1][1]=n11;p[1][2]=n12;
    p[2][0]=n20;p[2][1]=n21;p[2][2]=n22;
   
  }
  
  // constructor supplying the array directly
  public Epuzz_State(int[][] q){
  	p=q; 
  }
  
  //accessor - needed for same state
  public int[][] get_p() {return p;};

      
  // goalP
  public boolean goalP(Search searcher) { 
    Epuzz_Search esearcher = (Epuzz_Search) searcher;
    int[][] tar=esearcher.get_Target(); // get target arrangement
    boolean res=true;
    for (int i=0;i<=2;i++){
     for (int j=0;j<=2;j++){
     	if (tar[i][j]!=p[i][j]) res=false;
     }
  }

    return res;
  }



  // Get_Successors	
  	
  public ArrayList get_Successors (Search searcher) {
    Epuzz_Search esearcher = (Epuzz_Search) searcher;
    EasyWriter scr = new EasyWriter();
    ArrayList slis=new ArrayList();
    
    // find the space - the zero
    int spacei=0; //initialised to prevent warnings
    int spacej=0;
    for (int i=0;i<=2;i++){
     for (int j=0;j<=2;j++){
     	if (p[i][j]==0) {
     		spacei=i;
     		spacej=j;
     	}
     }
    }
    
    // move the space left
    if (spacej!=0) {
    	int[][]nl=(int[][])puzz_copy(p);
    	nl[spacei][spacej]=nl[spacei][spacej-1];
    	nl[spacei][spacej-1]=0;
    	Epuzz_State sl=new Epuzz_State(nl);
    	slis.add(sl);
    }
    	
    // move the space right
    if (spacej!=2) {
    	int[][]nr=(int[][])puzz_copy(p);
    	nr[spacei][spacej]=nr[spacei][spacej+1];
    	nr[spacei][spacej+1]=0;
    	Epuzz_State sr=new Epuzz_State(nr);
    	slis.add(sr);
    }
    	// move the space up
    if (spacei!=0) {
    	int[][]nu=puzz_copy(p);
    	nu[spacei][spacej]=nu[spacei-1][spacej];
    	nu[spacei-1][spacej]=0;
    	Epuzz_State su=new Epuzz_State(nu);
    	slis.add(su);
    }
    	// move the space down
    if (spacei!=2) {
    	int[][]nd=(int[][])puzz_copy(p);
    	nd[spacei][spacej]=nd[spacei+1][spacej];
    	nd[spacei+1][spacej]=0;
    	Epuzz_State sd=new Epuzz_State(nd);
    	slis.add(sd);
    }
    	return slis;
    }
  
  // copy the board
  private int[][] puzz_copy (int[][] b) {
  	int[][]bc={{0,0,0},{0,0,0},{0,0,0}};
  	for (int i=0;i<=2;i++){
     for (int j=0;j<=2;j++) {
     	bc[i][j]=b[i][j];
     }
  }
  return bc;
}
  	
  // same_State - do 2 Epuzz_Search_Nodes have the same state?

  public boolean same_State (Search_State s2) {
    Epuzz_State js2= (Epuzz_State) s2;
    int[][] tar=js2.get_p();
    boolean res=true;
    for (int i=0;i<=2;i++){
     for (int j=0;j<=2;j++)
      if (tar[i][j]!=p[i][j]) res=false;
    }
    return res;
  }
  

// toString 
  public String toString () {
    return "\n8 Puzzle State:\n"+p[0][0]+" "+p[0][1]+" "+p[0][2]+"\n"+
                                p[1][0]+" "+p[1][1]+" "+p[1][2]+"\n"+
                                p[2][0]+" "+p[2][1]+" "+p[2][2]+"\n";
    }

}



