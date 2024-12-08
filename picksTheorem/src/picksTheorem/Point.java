package picksTheorem;

public class Point {

	boolean isBoundary = false;
	boolean isInterior = false;
	
	Point(boolean isBoundary, boolean isInterior) {
		super();
		this.isBoundary = isBoundary;
		this.isInterior = isInterior;
	}

	@Override
	public String toString() {
	if(isBoundary && isInterior) // fake boundary
	return "E";
	else if(isBoundary)
	return "B";	
	else if(isInterior)
	return "I";
	else
	return "E";	
	}
	
	
	
}
