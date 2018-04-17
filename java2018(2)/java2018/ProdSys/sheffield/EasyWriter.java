/**
* EasyWriter (with apologies to Dennis Hopper)
* Provides a simple interface for text stream output 
* @author Guy J Brown
* First version 16/2/2000
* Modification history:
*/

package sheffield;

import java.io.*;

public class EasyWriter extends PrintWriter {

	// Constructors
	
	/**
	* Create a new EasyWriter that writes to standard output
	*/
	public EasyWriter() {
		super(System.out,true);
		}
		
	/**
	* Create a new EasyWriter that writes to a text file
	* @param s the name of the text file
	*/
	public EasyWriter(String s) {
		super(getFileWriter(s),true);
		}
		
	// Private methods
	
	private static FileWriter getFileWriter(String s) {
		FileWriter f = null;
		try {
			f = new FileWriter(s);
			}
		catch (IOException e) {
			System.err.println("Error in EasyWriter - couldn't create output file");
			System.exit(0);
			}
		return f;
		}
		
	private double rounded(double x, int ndp) {
		return Math.round(x*Math.pow(10,ndp))/Math.pow(10,ndp);
		}
		
	private String padfield(double x, int n, int w) {
		String s = padfrac(x,n);
		while (s.length()<w) s=" "+s;
		return s;
		}

	private String padfrac(double x, int n) {
		String s = (new Double(rounded(x,n))).toString();
		int ndp = n-(s.length()-s.indexOf('.')-1);
		for (int i=0; i<ndp; i++) s+="0";
		return s;
		}
			
	private String padwhole(long x, int n) {
		String s = (new Long(x)).toString();
		while (s.length()<n) s=" "+s;
		return s;
		}
		
	// give a prompt
	
	public void prompt(String p) {
		System.err.print(p);
		System.err.flush();
		}
				
	// formatted int
	
	/**
	* Println an int within a specified field width
	* @param x the number to println
	* @param n the field width
	*/
	public void println(int x, int n) {
		println(padwhole(x,n));
		}
		
	/**
	* Print an int within a specified field width
	* @param x the number to print
	* @param n the field width
	*/
	public void print(int x, int n) {
		print(padwhole(x,n));
		}
	
	// formatted long
	
	/**
	* Println a long within a specified field width
	* @param x the number to println
	* @param n the field width
	*/
	public void println(long x, int n) {
		println(padwhole(x,n));
		}

	/**
	* Print a long within a specified field width
	* @param x the number to print
	* @param n the field width
	*/		
	public void print(long x, int n) {
		print(padwhole(x,n));
		}
	
	// formatted short
	
	/**
	* Println a short within a specified field width
	* @param x the number to println
	* @param n the field width
	*/
	public void println(short x, int n) {
		println(padwhole(x,n));
		}
		
	/**
	* Print a short within a specified field width
	* @param x the number to print
	* @param n the field width
	*/
	public void print(short x, int n) {
		print(padwhole(x,n));
		}
	
	// formatted double
	
	/**
	* Print a double with a specified precision
	* @param x the number to print
	* @param n the number of decimal places
	*/
	public void print(double x, int n) {
		print(padfrac(x,n));
		}

	/**
	* Println a double with a specified precision
	* @param x the number to println
	* @param n the number of decimal places
	*/
	public void println(double x, int n) {
		println(padfrac(x,n));
		}

	/**
	* Print a double with a specified precision and field width
	* @param x the number to print
	* @param n the number of decimal places
	* @param w the field width
	*/
	public void print(double x, int n, int w) {
		print(padfield(x,n,w));
		}

	/**
	* Println a double with a specified precision and field width
	* @param x the number to println
	* @param n the number of decimal places
	* @param w the field width
	*/
	public void println(double x, int n, int w) {
		println(padfield(x,n,w));
		}

	// formatted float 

	/**
	* Print a float with a specified precision
	* @param x the number to print
	* @param n the number of decimal places
	*/
	public void print(float x, int n) {
		print(padfrac(x,n));
		}

	/**
	* Println a float with a specified precision
	* @param x the number to println
	* @param n the number of decimal places
	*/
	public void println(float x, int n) {
		println(padfrac(x,n));
		}
	
	/**
	* Print a float with a specified precision and field width
	* @param x the number to print
	* @param n the number of decimal places
	* @param w the field width
	*/
	public void print(float x, int n, int w) {
		print(padfield(x,n,w));
		}

	/**
	* Println a float with a specified precision and field width
	* @param x the number to println
	* @param n the number of decimal places
	* @param w the field width
	*/
	public void println(float x, int n, int w) {
		println(padfield(x,n,w));
		}
	
	}