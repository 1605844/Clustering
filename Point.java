import java.lang.Math;

public class Point{
	
	
	private double x,y;
	private int label;
	
	//-----------------------------------------------------------------------------------
	//constructors
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.label = 0;
	}
	
	public Point(double x, double y, int l) {
		this.x = x;
		this.y = y;
		this.label = l;
	}
	
	public Point(double size) {
		this.x = (Math.random() - 0.5) * size;
		this.y = (Math.random() - 0.5) * size;
		this.label = 0;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
		this.label = 0;
	}
	
	//---------------------------------------------------------------------------------------------
	//getters
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public int getLabel() {
		return this.label;
	}
	
	//---------------------------------------------------------------------------------------------
	//setters
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setLabel(int l) {
		this.label = l;
	}
	
	//---------------------------------------------------------------------------------------------
	//to String method
	
	public String toString() {
		String s = "";
		s += "(" + this.x +","+ this.y + "," + this.label + ")";
		return s;
	}
	
	//---------------------------------------------------------------------------------------------
	//point addition
	
	public Point add(Point p) {
		double X = getX() + p.getX();
		double Y = getY() + p.getY();
		
		return new Point(X,Y);
	}
	
	public void addOn(Point p) {
		this.x += p.getX();
		this.y += p.getY();
		
	}
	
	//---------------------------------------------------------------------------------------------
	//scalar multiplication
	
	public Point scale(double a) {
		double X = getX()*a;
		double Y = getY()*a;
		
		return new Point(X,Y,this.label);
	}
	
	//---------------------------------------------------------------------------------------------
	//Euclidean distance
	
	public double dist(Point p) {
		double X = getX() - p.getX();
		double Y = getY() - p.getY();
		
		double dist = Math.sqrt(X*X + Y*Y);
		return dist;
	}
}




















