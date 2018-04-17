/**
 * MatchDetails.java
 * result from a successful match against an element of an MStringVector
 * context, element that matched, rest of the vector
 */

package pmatch;

import java.util.*;

public class MatchDetails {
		private HashMap context;
		private String matcher;
		private MStringVector therest;

		// accessors
  		public HashMap getContext() {return context;}
		public String getMatcher() {return matcher;}
		public MStringVector getRest() {return therest;}

		//constructor

		public MatchDetails (HashMap c, String m, MStringVector r){
			context = (HashMap)c.clone();
			matcher = m;
			therest=new MStringVector(r.getV());
		}

		/**
		* setRest
		* reset the rest
		* @param MStringVector r
		*/

		public void setRest(MStringVector r){therest=new MStringVector(r.getV());}

	}