/**
* TestPS
* testing prodn systems
* 2013 Version
*/

import sheffield.*;
import java.util.*;
import java.math.*;
import pmatch.*;

public class TestPS
{
 public static void main(String[] arg) {
   // create objects for input and output
   EasyWriter scr = new EasyWriter();
   ArrayList<Prodn> bagger1 = new ArrayList<Prodn>(); //make the bagger1 rules
   bagger1.add(new b1Start());
   bagger1.add(new b1GetNextItem());
   bagger1.add(new b1BagInCurrent());
   bagger1.add(new b1StartNewBag());

   //initial facts

   ArrayList<String> stm = new ArrayList<String>();
   stm.add("step is start bagging");
   stm.add("trolley contains bread space 30");
   stm.add("trolley contains spuds space 50");
   stm.add("trolley contains cornflakes space 40");

   //prod sys engine

   ProdSys ps=new ProdSys(bagger1);

   ArrayList<String> res=ps.RunPS(stm);
   scr.println("final STM ");
   scr.println(res);
  }
}