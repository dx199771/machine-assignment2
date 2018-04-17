/**
 * Prodn.java
 * Abstract class for productions
 * which have antecedants, a predicate method, a modifyContext method, add actions, delete actions & remark actions
 * the antecedants are matched against the STM. If they match a context is established
 * if the pred succeeds for the context, modifyContext runs,
 * then the actions are caarried out using the resulting context
 * @author Phil Green
 * 2013 version
 */

 import java.util.*;
 import java.io.*;
 import sheffield.*;
 import pmatch.*;

 public abstract class  Prodn {

 	String name;	//production name
	String[] antes;	//antecedants
	String[] adds;	//additions to stm
	String[] dels;	//deletions from stm
	String[] remarks; //printouts on firing

	//can't give the accessor code here, otherwise don't get val from concrete class
 	abstract String getName();
 	abstract String[] getAntes();
 	abstract String[] getAdds();
 	abstract String[] getDels();
 	abstract String[] getRemarks();

 	abstract boolean pred(HashMap context);
 	abstract HashMap modifyContext(HashMap context);
}