
/**
 * Carta.java
 *
 * a map for searching
 * called Carta to avoid confusion with Interface map
 *
 *
 * @author <a href="mailto: "Phil Green</a>
 * 2013 version
 */

import sheffield.*;
import java.util.*;

public class Carta{
  private ArrayList<MapLink> links; // all the links
  private HashSet cities;  // all the cities

  //accessors
  public ArrayList<MapLink> getAllLinks() {return links;}
  public HashSet getCities() {return cities;}

  //constructor - empty map

  public Carta(){
  links = new ArrayList<MapLink>();
  }

  /**
  * addLink adds a link to a map
  * @param c1 city 1
  * @param c2 city 2
  * @param c cost of the link
  */
  public void addLink(String c1, String c2, int c){
    MapLink ml=new MapLink(c1,c2,c);
    links.add(ml);
  }

  public String toString(){
    StringBuffer buf = new StringBuffer("MAP WITH LINKS\n");
    for (MapLink lnk: links){
    	String lstr = lnk.toString();
    	buf.append(lstr+"\n");
    }
    return buf.toString();
  }

  /**
  * getLinks
  * returns all links to/from a given city
  * @param city - the city
  * @return ArrayList of links
  */
  public ArrayList<MapLink> getLinks(String city){
    ArrayList<MapLink> clinks = new ArrayList<MapLink>();
     for (MapLink l: links){
         if ((city.compareTo(l.getCity1())==0)||(city.compareTo(l.getCity2())==0))
         clinks.add(l);
     }

     return clinks;
  }

  /**
  * costbetween
  * returns cost between 2 cities
  * @param c1 city 1
  * @param c2 city 2
  */

  public int costbetween(String c1,String c2){
    ArrayList<MapLink> c1links=getLinks(c1);
    int ans=-1;
    Iterator i =c1links.iterator();
    while (i.hasNext()&&(ans<0)){
      MapLink l= (MapLink) i.next();
      if (c2.equals(l.getCity1()) || c2.equals(l.getCity2())){
        ans= l.getCost();
      }
    }
    return ans;
  }


  /**
  * mapFromFile
  * reads a map from file
  * @param fname - the file name
  */

  public void  mapFromFile(String fname){
    EasyReader rdr = new EasyReader(fname);
    String c1 = rdr.readString();
    while (c1.compareTo("done")!=0){
      links.add(new MapLink(c1, rdr.readString(), rdr.readInt()));
      c1 = rdr.readString();
      }
    findcities(); // uses the links to find the cities
  }

  // find all cities on the map

  private void findcities(){
    cities= new HashSet();
    for (MapLink l: links){
    	cities.add(l.getCity1());
      cities.add(l.getCity2());
    }
  }
}









