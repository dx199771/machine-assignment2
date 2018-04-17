/**
* prod1
* 2013 version
*/

import java.util.*;
import java.io.*;
import sheffield.*;

public class b1GetNextItem extends Prodn {
	final static String name = "GET-NEXT-ITEM";
	final static String[] antes = {"step is get next item",
	                               "trolley contains ?I space ?S"};
	final static String[] adds = {"step is bag item",
							      "item to bag ?I space ?S"};
	final static String[] dels = {"step is get next item",
	                              "trolley contains ?I space ?S"};
	final static String[] remarks = {"bagging ?I"};


	public String getName(){return name;}
	public String[] getAntes() {return antes;}
	public String[] getAdds() {return adds;}
	public String[] getDels() {return dels;}
	public String[] getRemarks(){return remarks;}


	public boolean pred(HashMap c){return true;}
	public HashMap modifyContext(HashMap c){return c;}

}