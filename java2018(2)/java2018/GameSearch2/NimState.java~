/**
 * NimState
 * a state in Nim
 * subclass of GameSearchState
*/

import java.util.*;
import java.math.*;
import sheffield.*;

public class NimState extends GameSearchState{

  private int[] b; //state represented by an int array - keep in sorted order

  // constructor

  /**
   * create a new nim state with a number of rows
   *@param (int[]) pa - the no of tokens in each row as an array
   */
  public NimState(int[] pa){
  	b=pa;
  	Arrays.sort(b);
  	}


  //accessor - needed for same state
  /**
   * accessor for the nim array
   */
  public int[] getB() {return b;}

  /**
    * result takes a GameSearchNode & returns "win", "loss" or "unknown"
    * simplest version for Nim is [1] is loss else [n] is win else unknown
    * @param (GameSearch) the current searcher
    */

    String result(GameSearch searcher){

    	switch (b.length) {
    		case 1: if (b[0]==1) return "loss"; else return "win";
    		case 2: if (b[0]==b[1]) return "loss"; else return "win";
    		case 3: return three_line_res();
    		case 4: return four_line_res();
    		case 5: return five_line_res();
    		default: return "unknown";
    	}
    }

   private String three_line_res(){
   	if      ((b[0]==1)&&(b[1]==1)&&(b[2]==1)) return "loss"; //1,1,1
   	else if ((b[0]==1)&&(b[1]==2)&&(b[2]==3)) return "loss"; //1,2,3
   	else if ((b[0]==1)&&(b[1]==2)) return "win"; //1,2,n
   	else if ((b[0]==1)&&(b[1]==3)) return "win"; //1,3,n
   	else if ((b[0]==2)&&(b[1]==3)) return "win"; //2,3,n
   	else if ((b[0]==b[1])||(b[1]==b[2])||(b[0]==b[2])) return "win"; //a pair +
   	else return "unknown";
   }

   private String four_line_res(){
   	if ((b[0]==1)&&(b[1]==1)&&(b[2]==1)) return "win"; // 1,1,1,n
   	else if ((b[0]==1)&&(b[1]==2)&&(b[2]==3)) return "win"; //1,2,3,n
   	else if ((b[0]==b[1])&&(b[2]==b[3])) return "loss"; // 2 pairs
   	else return "unknown";
   }

   private String five_line_res(){
   	if ((b[0]==1)&&(b[1]==1)&&(b[2]==1)&&(b[3]==1)&&(b[4]==1)) return "loss"; //1,1,1,1,1
   	else if ((b[0]==b[1])&&(b[2]==b[3])) return "win"; // 2 pairs +
   	else if ((b[1]==b[2])&&(b[3]==b[4])) return "win"; //2 pairs +
   	else return "unknown";
   	}
  /*
    	if (b.length>1) {return "unknown";}
    	    else if (b[0]==1) {return "loss";}
    	         else {return "win";}

    }
 */
    /** getSuccessors returns an ArrayList of states which are successors to the
    * current state in a given search
    * because result deals with boards with a single row we know we've got at least 2
    * for each row p in b
    * 		remove it from b
    *       generate successors from rest of b  and rest of b + p-1, p-2..1
    *		then sort each successor
    * then remove duplicates from the succs (not done yet)
    * @param (GameSearch) the current searcher
    */

    ArrayList<GameSearchState> getSuccessors(GameSearch searcher){
    	EasyWriter scr = new EasyWriter();
		ArrayList<GameSearchState> succs = new ArrayList<GameSearchState>();
		int nrows = b.length;
		for (int i=0;i<nrows;i++){//indexing the rows
			int p=b[i]; //the row to reduce
			if (i==0||b[i]!=b[i-1]){ // don't repeat if this row same as last
			  int [] br=new int[nrows-1]; //the board with this complete row gone

			  System.arraycopy(b,0,br,0,i);
			  System.arraycopy(b,i+1,br,i,nrows-i-1);
			  Arrays.sort(br);
			  NimState s = new NimState(br);
			  succs.add((GameSearchState)s); //add it to succs

			  for (int j=1;j<p;j++){
				int [] nb;
				nb=new int[nrows];
				System.arraycopy(br,0,nb,0,nrows-1);
				nb[nrows-1]=j;
				Arrays.sort(nb);
				NimState ss = new NimState(nb);
				succs.add(ss);
			  }
		}
		}
    	return succs;
    }


    /**
    * sameState: is this state identical to a given one?
    * @param (GameSearchState) the other searcher
    */

    boolean sameState(GameSearchState n2){
    	NimState p2 = (NimState) n2;
    	int[] b2=p2.getB();
    	if (b.length!=b2.length) {return false;}
    	else {
    		  boolean res = true;
    	      for (int i=0;i<b.length;i++) {
    		       if (b[i]!=b2[i]) res = false;
    		    }
    		  return res;
              }
    }


  /**
   *  toString
   * returns the Nim array as a string
   */
  	public String toString () {
  		EasyWriter scr=new EasyWriter();
  		String str = "Nim State: ";
  		for (int i=0;i<b.length;i++){
  			str=str.concat(" "+b[i]);
  		}
  		return str;
  	}


}



