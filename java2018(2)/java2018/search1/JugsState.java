/**
* JugsState.java
* State in a jugs problem
* 2013 version
*/

import java.util.*;

public class JugsState extends SearchState{

  private int j1; //content of jug1
  private int j2; //content of jug2

  /**
  * constructor
  * @param j1c content of jug 1
  * @param j2c content of jug2
  */

  public JugsState(int j1c, int j2c){
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
    JugsSearch jsearcher = (JugsSearch) searcher;
    int tar=jsearcher.getTarget(); // get target amount
    return ((j1 == tar) || (j2 == tar));
  }

  /**
  * getSuccessors
  * @param searcher - the current search
  */


  public ArrayList<SearchState> getSuccessors (Search searcher) {
    JugsSearch jsearcher = (JugsSearch) searcher;
    int cap1=jsearcher.getCap1();
    int cap2=jsearcher.getCap2();
    int j1Space=cap1-j1;
    int j2Space=cap2-j2;

    ArrayList<JugsState> jslis=new ArrayList<JugsState>(); // the list of jugs states
    ArrayList<SearchState> slis=new ArrayList<SearchState>();

    if (j1Space > 0) jslis.add(new JugsState(cap1,j2)); //fill jug1
    if (j2Space > 0) jslis.add(new JugsState(j1,cap2)); //fill jug2
    if (j1 != 0) jslis.add(new JugsState(0,j2));         //empty j1
    if (j2 != 0) jslis.add(new JugsState(j1,0));         //empty j2
    if ((j1 != 0) && (j2Space != 0)) {                     //pour from j1 into j2
      if (j1 > j2Space)
	     {jslis.add(new JugsState(j1-j2Space, cap2));}
      else
	     {jslis.add(new JugsState(0, j1+j2));};
      };
    if ((j2 != 0) && (j1Space != 0)) {                     //pour from j2 into j1
      if (j2 > j1Space)
	     {jslis.add(new JugsState(cap1, j2-j1Space));}
      else
	     {jslis.add(new JugsState(j1+j2,0));};
      };

    // cast the jugs states as search states in slis

    for (JugsState js:jslis)slis.add((SearchState)js);


    return slis;

  }

  /**
  * sameState - do 2 JugsSearchNodes have the same state?
  * @param s2 second state
  */

  public boolean sameState (SearchState s2) {
    JugsState js2= (JugsState) s2;

    return ((j1==js2.get_j1())&& (j2==js2.get_j2()));
    }


  /**
  * toString
  */

  public String toString () {
    return "Jug State: Jug1-> "+j1+" Jug2-> "+j2;
    }



}



