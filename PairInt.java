package Maze;

public class PairInt {

	private int x;
	private int y;
	
	//Constructs a new PairInt object
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Returns X
	public int getX() {
		return x;
	}
	//Returns y
	public int getY() {
		return y;
	}
	
	// Sets a new value for X
	public void setX(int x) {
		this.x = x;
	}
	
	//Sets a new value for Y
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
	if(((PairInt)p).getX() == this.x && ((PairInt)p).getY() == this.y) {
		return true;
	}
		return false;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public PairInt copy() {
		return  new PairInt(this.x,this.y);
	}
}
