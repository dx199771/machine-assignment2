
/**
 * StripsOp.java
 * an operator in strips
 * with add list, delete list, preconditions, act list
 * all these are MstringVectors, for matching
 * Created: Wed Jan 10 20:21:50 2001
 *
 * @author
 * 2013 Version
 */

import java.util.*;
import java.io.*;
import pmatch.*;

public class StripsOp {

  private MStringVector addList;
  private MStringVector delList;
  private ArrayList<String> preconds;
  private MStringVector actList;

  //accessors
  public MStringVector getAddList() {return addList;}
  public MStringVector getDelList() {return delList;}
  public ArrayList<String> getPreconds() {return preconds;}
  public MStringVector getActList() {return actList;}

  //constructor given addList as a string,
  //addList, delList & preconds as strings

  public StripsOp(String actlis, String addlis, String delis, String prelis){
    actList=new MStringVector(actlis);
    addList=new MStringVector(addlis);
    delList=new MStringVector(delis);
    preconds=new ArrayList<String>();
    int i=0;
    int j=prelis.indexOf('|');
    while (j != -1){
      preconds.add(prelis.substring(i,j));
      i=j+1;
      j=prelis.indexOf('|',j+1);
    }
    preconds.add(prelis.substring(i,prelis.length()));
  }


  //this constructor reads the operator from file given op name as a string
  //looks for 4 files: name_addList.txt etc

  public StripsOp(String oname){
    addList=readPatts(oname+"_addList.txt");
    delList=readPatts(oname+"_delList.txt");
    actList=readPatts(oname+"_actList.txt");
    preconds=readStrs(oname+"_preconds.txt");
  }

  // read op component from file as vector of strings

  private MStringVector readPatts(String fname){
    File f = new File(fname);
    MStringVector res = new MStringVector();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String s;
      while ((s = reader.readLine()) != null) res.addMString(new MString(s));
    } catch (IOException ex) { ex.printStackTrace(); }
    return(res);
  }

  private ArrayList<String> readStrs(String fname){
    File f = new File(fname);
    ArrayList<String> res = new ArrayList<String>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(f));
      String s;
      while ((s = reader.readLine()) != null) res.add(s);
    } catch (IOException ex) { ex.printStackTrace(); }
    return(res);
  }
}
