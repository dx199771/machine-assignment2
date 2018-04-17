/*
 * Equaliser_State.java
 *
 * Created on 17 March 2002, 09:46
 */



/**
 *
 * @author  Philip Prudom
 * @version 
 */
import java.util.*;

public class Equaliser_State extends Search_State {

    private int t1; //total number of tokens in stack one.
    private int t2; //total number of tokens in stack two.
    private int t3; //total number of tokens in stack three.
    
    
    public Equaliser_State(int t1c, int t2c, int t3c) {
        t1=t1c;
        t2=t2c;
        t3=t3c;
        
    }

    public int get_t1() {
        return t1;
    }
    
    public int get_t2() {
        return t2;
    }
    
    public int get_t3() {
        return t3;
    }
    
    public boolean goalP(Search searcher) { 
      Equaliser_Search esearcher = (Equaliser_Search) searcher;
      int tar=esearcher.get_Target(); 
      return ((t1 == tar) || (t2 == tar) || (t3 == tar));
    } //*** you'd missed this off!
    
    public ArrayList get_Successors (Search searcher) {
     Equaliser_Search esearcher = (Equaliser_Search) searcher;
    
     int t1 = esearcher.get_t1; //*** not needed - you already have t1,t2,t3
     int t2 = esearcher.get_t2; //*** they are variables in this instance
     int t3 = esearcher.get_t3; //*** not in esearcher
    
     ArrayList slis=new ArrayList();
    
     if (t3>t2) slis.add(new Equaliser_State(t1,2*t2,t3-t2)); //move tokens from t3 to t2
     if (t3>t1) slis.add(new Equaliser_State(2*t1,t2,t3-t1)); //move tokens from t3 to t1
     if (t2>t1) slis.add(new Equaliser_State(2*t1,t2-t1,t3)); //move tokens from t2 to t1
     if (t2>t3) slis.add(new Equaliser_State(t1,t2-t3,2*t3)); //move tokens from t2 to t3
     if (t1>t2) slis.add(new Equaliser_State(t1-t2,2*t2,t3)); //move tokens from t1 to t2
     if (t1>t3) slis.add(new Equaliser_State(t1-t3,t2,2*t3)); //move tokens from t1 to t3 
    
     return slis;
    
    }
    
    public boolean same_State (Search_State s2) {
     Equaliser_State es2= (Equaliser_State) s2;
	
     return ((t1==es2.get_t1())&& (t2==es2.get_j2())&&(t3==es2.get_t3()));
    }
    
    /** 
  * toString
  */
  public String toString () {
    return "Stack State: Stack1-> "+t1+" Stack2-> "+t2+" Stack3-> "+t3;
    }
}
// }
