package picksTheorem;

public class Coordinate {
    int x;
    int y;
    boolean bit1 = false; // left bit
    boolean bit2 = false; // right bit

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(double x, int y) {
    	this.x = (int) x;
    	this.y = y;
	}
    
    public Coordinate(double x, int y, boolean bit1, boolean bit2) {
    this(x,y);
    this.bit1 = bit1;
    this.bit2 = bit2;
	}
    
    

	public String toString() {
    return x + ", " + y;	
    }

}

