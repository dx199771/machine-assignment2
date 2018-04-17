/**
 * RunNim.java
 * Run GameTree search for Nim
 * @author Phil Green
 * First Version 12/1/2004
 * */

import sheffield.*;
import java.util.*;
import java.math.*;

public class RunNim
{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();
   //EasyWriter scrf = new EasyWriter("Nim_results.txt");

   int[] nim123={1,2,3,4,5};

   NimSearch nsearch = new NimSearch(); //the search engine
   GameSearch gsearch = (GameSearch)nsearch;
   NimState ns = new NimState(nim123);
   GameSearchState initState = (GameSearchState) ns;

   /*ArrayList slis = ns.getSuccessors(gsearch);
   for (Iterator k = slis.iterator();k.hasNext();){
   	NimState s = (NimState)k.next();
   	scr.println(s.toString());
   }
   */
   String ans = nsearch.runGameSearch(initState); //run the search
   scr.println(ans);
  }
}
