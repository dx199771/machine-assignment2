/**
* prod1
* 2013 version
*/

import java.util.*;
import java.io.*;
import sheffield.*;

public class b1Start extends Prodn {
	final static String name = "START-BAGGING";
	final static String[] antes = {"step is start bagging"};
	final static String[] adds = {"step is get next item",
							      "current bag no 1 space 100"};
	final static String[] dels = {"step is start bagging"};
	final static String[] remarks = {"starting to bag"};


	public String getName(){return name;}
	public String[] getAntes() {return antes;}
	public String[] getAdds() {return adds;}
	public String[] getDels() {return dels;}
	public String[] getRemarks(){return remarks;}


	public boolean pred(HashMap c){return true;}
	public HashMap modifyContext(HashMap c){return c;}

}