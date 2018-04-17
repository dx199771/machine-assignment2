/**
 * Piles_State
 * a state in Piles (Grundy's game)
 * subclass of GameSearchState
*/

import java.util.*;
import java.math.*;
import sheffield.*;

public class PilesState extends GameSearchState{

  private int[] b; //state represented by an int array - keep in sorted order


  // constructors

  /**
   * create a new piles state with a single pile
   *@param (int) p - the pile
   */

  public PilesState(int initBoard) {
  	b=new int[1];
  	b[0]=initBoard;
 	}

  /**
   * create a new piles state with a number of piles
   *@param (int[]) pa - the piles as an array
   */
  public PilesState(int[] pa){b=pa;}


  //accessor - needed for same state
  /**
   * accessor for the pile array
   */
  public int[] getB() {return b;}

  /**
    * result takes a GameSearchNode & returns "win", "loss" or "unknown"
    * in Piles, if no successors then loss else unknown
    * @param (GameSearch) the current searcher
    */

    String result(GameSearch searcher){
    	ArrayList<GameSearchState> succs = this.getSuccessors(searcher);
    	if (succs.isEmpty()) return "loss"; else return "unknown";
    }

    /** getSuccessors returns an ArrayList of states which are successors to the
    * current state in a given search
    * for each pile p in b
    * 		remove it from b
    *		if p > 2 split into 1, p-1, 2,p-2... n, p-n while n<p/2
    *		for each split, add in the other piles
    *		then sort the piles
    * then remove duplicates from the succs (not done yet)
    * @param (GameSearch) the current searcher
    */

    ArrayList<GameSearchState> getSuccessors(GameSearch searcher){
    	EasyWriter scr = new EasyWriter();
		ArrayList<PilesState> succs = new ArrayList<PilesState>();
		ArrayList<GameSearchState> slis=new ArrayList<GameSearchState>();

		for (int i=0;i<b.length;i++){//indexing the piles
			int p=b[i]; //the pile to split
			if (p>2){ //only split if its 3 or more
				float pf = (float)p;
				int plim=(int)Math.ceil(pf/2);
				for (int j=1;j<plim;j++){ //all poss splits
					int[] nb= new int[b.length+1]; //the new board
					System.arraycopy(b,0,nb,0,i); //copy piles before i
   					System.arraycopy(b,i+1,nb,i,b.length-i-1); //copy piles after i
   					nb[nb.length-2]=j;
   					nb[nb.length-1]=p-j; //add the split for i to the end of the array
   					Arrays.sort(nb); //sort the result
					PilesState s = new PilesState(nb); //make new state
    				succs.add(s); //add it to succs
    			} //end of j
    		}; //end of if
    	}//end of for
    	for (PilesState js:succs){slis.add((GameSearchState)js);}//cast successors as GameSearchStates
    	return slis;
    }


    /**
    * sameState: is this state identical to a given one?
    * @param (GameSearchState) the other searcher
    */

    boolean sameState(GameSearchState n2){
    	PilesState p2 = (PilesState) n2;
    	return b.equals(p2.getB());}


  /**
   *  toString
   * returns the pile array as a string
   */
  	public String toString () {
  		EasyWriter scr=new EasyWriter();
  		String str = "Piles State: ";
  		for (int i=0;i<b.length;i++){
  			str=str.concat(" "+b[i]);
  		}
  		return str;
  	}


}



