/**
* bagger 1 prodns
* put an item in the current bag
* 2013 Version
*/

import java.util.*;
import java.io.*;
import sheffield.*;

public class b1BagInCurrent extends Prodn {

	final static String name = "BAG-IN-CURRENT";
	final static String[] antes = {"step is bag item",
	                               "item to bag ?I space ?S",
	                               "current bag no ?N space ?BS"};
	final static String[] adds = {"step is get next item",
							      "bag ?N contains ?I",
							      "current bag no ?N space ?RS"};
	final static String[] dels = {"step is bag item",
	                              "item to bag ?I space ?S",
	                              "current bag no ?N space ?BS"};
	final static String[] remarks = {"?I in bag no ?N"};

	public String getName(){return name;}
	public String[] getAntes() {return antes;}
	public String[] getAdds() {return adds;}
	public String[] getDels() {return dels;}
	public String[] getRemarks(){return remarks;}


	public boolean pred(HashMap c){
		Integer spaceLeft = Integer.valueOf((String) c.get("?BS"));
		Integer spaceNeeded = Integer.valueOf((String) c.get("?S"));
		return (spaceLeft.intValue()>=
		        spaceNeeded.intValue());}

	public HashMap modifyContext(HashMap c){
		Integer spaceLeft = Integer.valueOf((String) c.get("?BS"));
		Integer spaceNeeded = Integer.valueOf((String) c.get("?S"));
		c.put("?RS", String.valueOf(spaceLeft.intValue()-spaceNeeded.intValue()));
		return c;}

}