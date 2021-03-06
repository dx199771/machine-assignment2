 /**
   * RunJugsSearch.java
   *
   *
   * 2013 version
   *
   * @author <a href="mailto: "Phil Green</a>
   * @version 1

   run a jugs search

 */

import sheffield.*;
import java.util.*;

public class RunJugsSearch {

public static void main(String[] arg) {

    // create an EasyWriter

    EasyWriter screen = new EasyWriter();

    JugsSearch searcher = new JugsSearch(7,4,2);
    SearchState init_state = (SearchState) new JugsState(0,0);

    String res = searcher.runSearch(init_state);
    screen.println(res);
}
}


