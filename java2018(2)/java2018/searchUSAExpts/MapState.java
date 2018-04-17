/**
	State in a map search
	Version for A*
	changes indicated by //A*
	2013 version
*/

import java.util.*;

public class MapState extends SearchState{

  //city for this state
  private String city;


  //constructor
  //A* - has estRemCost now
  public MapState(String cname, int lc, int rc){
    city=cname;
    localCost=lc;
    estRemCost=rc;
  }
  //accessors
  public String getCity(){
    return city;
  }


  // goalP
  public boolean goalP(Search searcher) {
    MapSearch msearcher = (MapSearch) searcher;
    String tar=msearcher.getGoal(); // get target city
    return (city.compareTo(tar)== 0);
  }

  // getSuccessors
  public ArrayList<SearchState> getSuccessors (Search searcher) {
    MapSearch msearcher = (MapSearch) searcher;
    Carta map=msearcher.getMap();
    ArrayList<MapLink> links=map.getLinks(city);
    ArrayList<SearchState> succs=new ArrayList();

    for (MapLink l: links){
    	String scity;
        if (city.compareTo(l.getCity1())==0) {
        scity=l.getCity2();
        }
        else {
        scity=l.getCity1();};
        succs.add(new MapState(scity,l.getCost(),map.estbetween(scity,msearcher.getGoal())));
        }
    return succs;
    }



  // sameState

  public boolean sameState(SearchState s2) {
    MapState ms2= (MapState) s2;
    return (city.compareTo(ms2.getCity())==0);
  }


// toString
    public String toString () {
      return ("Map State: "+city);
    }



}



