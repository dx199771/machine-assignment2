 /**
   * RunMapSearch.java
   *
   * @author <a href="mailto: "Phil Green</a>
   * 2013 version

   run a map traversal

 */

import sheffield.*;
import java.util.*;

public class RunMapSearch {

  public static void main(String[] arg) {

    // create an EasyWriter

    EasyWriter screen = new EasyWriter();

    Carta map1= new Carta();
    map1.mapFromFile("map1.txt");
    // screen.println(map1.toString());
    //screen.println(map1.getLinks("Start"));

    MapSearch searcher = new MapSearch(map1,"Goal");
    SearchState initState = (SearchState) new MapState("Start",0);

    //change from search1 - specify strategy
    //String res_df = searcher.runSearch(initState, "breadthFirst");
    //screen.println(res_df);
    //String res_bf = searcher.runSearch(initState, "depthFirst");
    //screen.println(res_bf);
    String res_bb = searcher.runSearch(initState, "branchAndBound");
    screen.println(res_bb);
  }
}










