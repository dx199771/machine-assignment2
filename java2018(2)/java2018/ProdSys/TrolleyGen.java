/**
 * @(#)TrolleyGen.java
 * Generate shopping trolleys for Bagger problems
 * Usage - supply random seed, number of shopping items
 * max space for an item is 100
 * TrolleyGen tg = new TrolleyGen(12345,10); 
 * String[]trolley= tg.fillTrolley();
 * Returns a String Array of 10 items
 *  trolley contains item1 space 51
 *  trolley contains item2 space 80
 *  trolley contains item3 space 41
 *  trolley contains item4 space 28
 *  trolley contains item5 space 55
 *  trolley contains item6 space 84
 *  trolley contains item7 space 75
 *  trolley contains item8 space 2
 *  trolley contains item9 space 1
 *  trolley contains item10 space 89
 * @author pdg
 * @version 1.00 2017/3/22
 */
 
 
import sheffield.*;
import java.util.*;

public class TrolleyGen {
 EasyWriter screen = new EasyWriter();
 Random gen; // random number generator
 int nItems; //number of items in trolley
 String stString = "trolley contains item";
 String finString = " space ";
 String[] contents; //build up answer in here
 
 
public TrolleyGen(int seed, int items ) { //constructor given seed, number of items
    gen=new Random(seed);
    nItems= items; 
    contents = new String[nItems];
    }
    
public String[] fillTrolley ()  { //generate a trolley
 screen.println(nItems);
 for (int i=1;i<=nItems;i++ ){
  String itemString = stString+i+finString+gen.nextInt(100);
  contents[i-1]=itemString;
 }
 return contents;
}
}
 	 
