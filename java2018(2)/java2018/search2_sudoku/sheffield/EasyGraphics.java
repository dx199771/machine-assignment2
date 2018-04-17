package sheffield;

import java.awt.*;
import java.awt.event.*;

public class EasyGraphics extends Frame implements WindowListener {

	private EasyCanvas myCanvas;
	private int currentX=0;
	private int currentY=0;
	
	public EasyGraphics() {
		this(200,200,0,0);
		}
		
	public EasyGraphics(int w, int h) {
		this(w,h,0,0);
		}
		
	public EasyGraphics(int w, int h, int x0, int y0) {
		setTitle("Graphics Window");
		setSize(w,h);
		myCanvas = new EasyCanvas(w,h,x0,y0);
		setMenuBar(new MenuBar());
		add(myCanvas);
		addWindowListener(this);
		setVisible(true);
		setResizable(false);
		}
		
	public void clear() {
		myCanvas.clear();
		}
		
	public void moveTo(int x0, int y0) {
		currentX=x0;
		currentY=y0;
		}
		
	public void moveTo(double x0, double y0) {
		currentX=(int)x0;
		currentY=(int)y0;
		}
		
	public void plot(int x0, int y0) {
		lineBetween(x0,y0,x0,y0);
		}

	public void plot(double x0, double y0) {
		lineBetween((int)x0,(int)y0,(int)x0,(int)y0);
		}

	public void lineTo(int x0, int y0) {
		lineBetween(currentX,currentY,x0,y0);
		}
		
	public void lineTo(double x0, double y0) {
		lineBetween(currentX,currentY,(int)x0,(int)y0);
		}
		
	public void lineBetween(int x0, int y0, int x1, int y1) {
		myCanvas.lineBetween(x0,y0,x1,y1);
		currentX=x1;
		currentY=y1;
		}
		
	public void lineBetween(double x0, double y0, double x1, double y1) {
		lineBetween((int)x0,(int)y0,(int)x1,(int)y1);
		}
		
	public void debug() { 
		myCanvas.debug(); 
		}
								
	public void windowClosing(WindowEvent event) {
		dispose();
		System.exit(0);
		}
		
	public void windowIconified(WindowEvent event) {}

	public void windowOpened(WindowEvent event) {}

	public void windowClosed(WindowEvent event) {}
		
	public void windowDeiconified(WindowEvent event) {}

	public void windowActivated(WindowEvent event) {}

	public void windowDeactivated(WindowEvent event) {}

	}
	
class EasyCanvas extends Canvas {

	private Coordinates head=null;
	private Coordinates tail=null;
	private int originX;
	private int originY;
	private int width;
	private int height;
	private int maxx;
	private int maxy;
		
	public EasyCanvas(int w, int h, int x, int y) {
		originX=x;
		originY=y;
		width=w;
		height=h;
		maxx=w;
		maxy=h;
		setSize(w,h);
		}
				
	public void paint(Graphics g) {
		Dimension d = getSize();
		maxx=d.width;
		maxy=d.height;
		Coordinates h = head;
		while (h!=null) {
			g.drawLine(mapx(h.x0),mapy(h.y0),mapx(h.x1),mapy(h.y1));
			h=h.next;
			}	
		}
		
	public void clear() {
		repaint();
		head=tail=null;
		}
				
	public void debug() {
		Coordinates h = head;
		while (h!=null) {
			System.err.println(h);
			h=h.next;
			}
		}
		
	public void lineBetween(int x0, int y0, int x1, int y1) {
		if (head==null) {
			head=tail=new Coordinates(x0,y0,x1,y1);
			}
		else {
			tail.next=new Coordinates(x0,y0,x1,y1);
			tail=tail.next;
			}
		Graphics g = getGraphics();
		g.drawLine(mapx(x0),mapy(y0),mapx(x1),mapy(y1));
		g.dispose();
		}
		
	private int mapy(int y) {
		return maxy*(height-(originY+y))/height;
		}
		
	private int mapx(int x) {
		return maxx*(originX+x)/width;
		}
	
	private class Coordinates {
	
		public int x0;
		public int y0;
		public int x1;
		public int y1;
		public Coordinates next;
		
		public Coordinates(int x0, int y0, int x1, int y1) {
			this.x0=x0;
			this.y0=y0;
			this.x1=x1;
			this.y1=y1;
			next=null;
			}
		
		public String toString() {
			return("("+x0+","+y0+") to ("+x1+","+y1+")");
			}
			
		}		
	}
	
