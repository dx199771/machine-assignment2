
/**
 * beerStrips.java
 * Strips on the beer problem
 *
 * Created: Jan 2005
 * STRIPS for the beer problem
 * @author pdg
 * 2013 Version
 */

//import pmatch.MStringVector;
//import pmatch.MString;
//import pmatch.MatchDetails;

import pmatch.*;
import sheffield.*;
import java.io.*;
import java.util.*;


public class beerStrips {
  public static void main(String[] args) {
    EasyWriter scr=new EasyWriter();

    //create the operators

    StripsOp open = new StripsOp("open door from ?r1 to ?r2",
                                   "door open ?r1 ?r2",
                                   "door closed ?r1 ?r2",
                                   "Robbie in ?r1|door closed ?r1 ?r2");
    StripsOp closed = new StripsOp("close door from ?r1 to ?r2",
                                     "door closed ?r1 ?r2",
                                     "door open ?r1 ?r2",
                                     "Robbie in ?r1|door open ?r1 ?r2");
    StripsOp move = new StripsOp("move from ?r1 to ?r2",
                                   "Robbie in ?r2",
                                   "Robbie in ?r1",
                                   "Robbie in ?r1|door open ?r1 ?r2");
    StripsOp carry = new StripsOp("carry ?obj from ?r1 to ?r2",
                                    "Robbie in ?r2|?obj in ?r2",
                                    "?obj in ?r1|Robbie in ?r1",
                                    "?obj in ?r1|Robbie in ?r1|door open ?r2 ?r1");

    //form them into a vector

    ArrayList<StripsOp> beerOps = new ArrayList<StripsOp>();
    beerOps.add(open);
    beerOps.add(closed);
    beerOps.add(move);
    beerOps.add(carry);

    //create instance of Strips1, give it the operators, init state & goal state

    Strips1 str=new Strips1(beerOps,
                            new MStringVector("Robbie in living_room|beer in kitchen|door closed living_room kitchen"),
                            new MStringVector("beer in living_room"));


    //run Strips
    boolean res=str.run();
    scr.println("Result is "+res); //result
    scr.println("Plan is   "+str.getPlan()); //plan

  }
}

