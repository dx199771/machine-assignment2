/*
	State in a Sudoku problem
*/

import java.util.*;
import sheffield.*;

public class Sudoku_State extends Search_State{

  private int p[][]; //9x9 matrix represents state. 0 represents unoccupied square
  private Vector v[][]; //9*9 matrix of ArrayLists, to contain poss fillers for each square
  private Vector one2nine; // Vector of Integers, 1..9, starting point for v
  
  EasyWriter scr = new EasyWriter();
  
  // constructor supplying the Sudoku array directly
  public Sudoku_State(int[][] q){
  	p=q; 
  }
  
  //accessor - needed for same state
  public int[][] get_p() {return p;};

      
  // goalP - contains no zeroes
  
  public boolean goalP(Search searcher) { 
    
    boolean res=true;
    for (int i=0;i<=8;i++){
     for (int j=0;j<=8;j++){
     	if (p[i][j]==0) res=false;
     }
  	}
    return res;
  }

  // same_State - do 2 Sudoku_Search_Nodes have the same state?

  public boolean same_State (Search_State s2) {
    Sudoku_State js2= (Sudoku_State) s2;
    int[][] tar=js2.get_p();
    boolean result=true;
    for (int i=0;i<=8;i++){
     for (int j=0;j<=8;j++){
     	if(p[i][j]!=tar[i][j]) result=false;
     }
    }
    return result;
  }
  
  // toString 
  public String toString () {
    return "\nSudoku State:\n"+
     p[0][0]+" "+p[0][1]+" "+p[0][2]+" "+p[0][3]+" "+p[0][4]+" "+p[0][5]+" "+p[0][6]+" "+p[0][7]+" "+p[0][8]+"\n"+
     p[1][0]+" "+p[1][1]+" "+p[1][2]+" "+p[1][3]+" "+p[1][4]+" "+p[1][5]+" "+p[1][6]+" "+p[1][7]+" "+p[1][8]+"\n"+
     p[2][0]+" "+p[2][1]+" "+p[2][2]+" "+p[2][3]+" "+p[2][4]+" "+p[2][5]+" "+p[2][6]+" "+p[2][7]+" "+p[2][8]+"\n"+
     p[3][0]+" "+p[3][1]+" "+p[3][2]+" "+p[3][3]+" "+p[3][4]+" "+p[3][5]+" "+p[3][6]+" "+p[3][7]+" "+p[3][8]+"\n"+
     p[4][0]+" "+p[4][1]+" "+p[4][2]+" "+p[4][3]+" "+p[4][4]+" "+p[4][5]+" "+p[4][6]+" "+p[4][7]+" "+p[4][8]+"\n"+
     p[5][0]+" "+p[5][1]+" "+p[5][2]+" "+p[5][3]+" "+p[5][4]+" "+p[5][5]+" "+p[5][6]+" "+p[5][7]+" "+p[5][8]+"\n"+
     p[6][0]+" "+p[6][1]+" "+p[6][2]+" "+p[6][3]+" "+p[6][4]+" "+p[6][5]+" "+p[6][6]+" "+p[6][7]+" "+p[6][8]+"\n"+
     p[7][0]+" "+p[7][1]+" "+p[7][2]+" "+p[7][3]+" "+p[7][4]+" "+p[7][5]+" "+p[7][6]+" "+p[7][7]+" "+p[7][8]+"\n"+
     p[8][0]+" "+p[8][1]+" "+p[8][2]+" "+p[8][3]+" "+p[8][4]+" "+p[8][5]+" "+p[8][6]+" "+p[8][7]+" "+p[8][8]+"\n";
    }
    
  //valid_State
  //is a given array valid, i.e. no repetitions in rows, columns or squares
  
  public boolean valid_State () {
  	boolean result=true;
  	for (int i=0;i<=8;i++){
     int[] row ={1,2,3,4,5,6,7,8,9}; //set elem to 0 when number found 
     int[] col={1,2,3,4,5,6,7,8,9};
     for (int j=0;j<=8;j++){
     	if (p[i][j]!=0){ //check row i,j
     		if (row[p[i][j]-1]==0){ //crossed this out already
     		   //scr.println("fr "+i+" "+j);	
     		   result=false;}
     		   else {
     		   row[p[i][j]-1]=0;}
     		}//if p

        if (p[j][i]!=0){ //check col j,i
     		if (col[p[j][i]-1]==0){
     		   //scr.println("fc "+i+" "+j);	
     		   result=false;}
     		   else {
     		   col[p[j][i]-1]=0;}
     		}//if p
     	};//for j
     };//for i
     //scr.println(result);
     //check squares
     for (int i=0;i<=6;i=i+3){
     	for (int j=0;j<=6;j=j+3){
     		int[] sq = {1,2,3,4,5,6,7,8,9};
     		for (int k=i;k<=i+2;k++){
     			for (int l=j;l<=j+2;l++){
     				//scr.println("i="+i+" j="+j+" k="+k+" l="+l);
     				if (p[k][l]!=0){
     				    if (sq[p[k][l]-1]==0){
     				    	result=false;}
     				    	else {
     				    		  sq[p[k][l]-1]=0;}
     				    }//if p
     				 }//for l
     			}//for k
     		}//for j
     	}//for i
     	
     	return result;
     }				    		 

     	
  
  //form up the one2nine Vector
  private void make_one2nine(){
  	one2nine=new Vector();
  	one2nine.add(new Integer(1));
    one2nine.add(new Integer(2));
    one2nine.add(new Integer(3));
    one2nine.add(new Integer(4));
    one2nine.add(new Integer(5));
    one2nine.add(new Integer(6));
    one2nine.add(new Integer(7));
    one2nine.add(new Integer(8));
    one2nine.add(new Integer(9));
  }
    
    

  // Get_Successors	
  	
  public ArrayList get_Successors (Search searcher) {
    Sudoku_Search ssearcher = (Sudoku_Search) searcher;
    EasyWriter scr = new EasyWriter();
    ArrayList slis=new ArrayList(); //for the successors
    make_one2nine(); //form up one2nine
    Integer vint;
    
    // find possible fillers for each position:
    //   set up a 9*9 array v of Vectors, each starting as  {1,2,3,4,5,6,7,8,9}
    
    v=new Vector[9][9]; 
    for (int i=0;i<=8;i++){
     for (int j=0;j<=8;j++){
     	v[i][j]=(Vector)one2nine.clone();
     }
  	}
  	
    //   iterate through p:
    //   if p[i,j]=n & n!=0, remove n from v[i,*], v[*,j] 
    //                              and the square in v defined by imod3*3->imod3*3+2, jmod3*3, jmod3*3+2
    //   set v[i,j] to {n}
    //
    
    
    for (int i=0;i<=8;i++){
     for (int j=0;j<=8;j++){
     	if (p[i][j]!=0){ //got a filled square
     		//scr.println("square "+i+","+j+" = "+p[i][j]);
     		Integer x = new Integer(p[i][j]);
     		for (int k=0;k<=8;k++){ //remove from rows and columns in v
     			v[i][k].remove(x); 
     			v[k][j].remove(x);
     		}
     		//scr.println("rows/cols "+v[0][2]);
     		for (int k=(i/3)*3;k<=(i/3)*3+2;k++){
     			for (int l=(j/3)*3;l<=(j/3)*3+2;l++){
     				v[k][l].remove(x);  //remove from 3*3 neighbourhood
     			}
     		}
     		//scr.println("local "+v[0][2]);

     	    v[i][j]=new Vector(); // the square itself
     	    v[i][j].add(x);
     	
     	} //dealt with filled square
     } //close j
  } //close i
    
    // find the cardinality c of the smallest (new) filler subset in v
    // if c=0, no successors
    // if c=1, return 1 successor state with all squares for which c=1 filled with n where v[i,j]=[n]
    
    // find the new singletons in v, if any
    // at same time, identify index with smallest subset
    
    int nsfound=0;
    int c=9; //smallest cardinality
    int ci=0; //& its indices
    int cj=0;
    
    int[][] s1 = new int[9][9]; //the single successor if we find any new singletons
    
    for (int i=0;i<=8;i++){
     for (int j=0;j<=8;j++){
     	s1[i][j]=p[i][j]; //copy to s1 from p
     	if ((p[i][j]==0)&&(v[i][j].size()<c)){ //smaller subset
     		c=v[i][j].size();
     		ci=i;
     		cj=j;
     	}
     		
     	if ((v[i][j].size()==1)&&(p[i][j]==0)) { //a new singleton
     	    vint = (Integer)v[i][j].get(0);
     	    scr.println("singleton at "+i+" "+j+"->"+vint.intValue());
     		s1[i][j]=vint.intValue(); //overwrite with the new singleton
     		nsfound++;
     		}
     	}
     }
     if (nsfound > 0) { // found some new singletons
        Sudoku_State sing = new Sudoku_State(s1); //check if state valid
     	if (sing.valid_State()) {
     		scr.println("state is valid");
     		slis.add(sing);}
     		else scr.println("invalid state");
     	return slis;
     }
     
     if (c==0) {  //there are squares with no fillers, hence no successors
     	scr.println("no successors");
     	return slis;
     	} 
      
     // if c>1, pick one of the squares with c successors
     //         return c states corresponding to the alternatives for this square
     
     scr.println("returning "+c+" poss fillers for ("+ci+","+cj+")");
     scr.println(v[ci][cj]);
     for (int k=0;k<=c-1;k++){
     	
     	int[][] ss = new int[9][9]; //make new array - clone doesn't copy
     	for (int l=0;l<=8;l++){
     		for (int m=0;m<=8;m++){
     			ss[l][m]=s1[l][m];
     		}
     	}
     	
     	vint=(Integer)v[ci][cj].get(k);
     	scr.println(k+" "+vint.intValue());
     	ss[ci][cj]=vint.intValue();
     	slis.add(new Sudoku_State(ss));
     }
     //scr.println("slis");
     //scr.println(slis.get(0).toString());
     //scr.println(slis.get(1).toString());
     return slis;
  }
}



