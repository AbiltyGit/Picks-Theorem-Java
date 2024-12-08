package picksTheorem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PickTheoremApp {

    public Grid findMinCoords(Shape shape) {
    	int xMax = 1;
        int yMax = 1;
        	
        for(Coordinate c : shape.getCoordinates()) { // find required x,y for our shape
        xMax = Math.max(c.x, xMax);
        yMax = Math.max(c.y, yMax);
        }	
        
        return new Grid(yMax + 1, xMax + 1);
        
    }
    
    
    public Grid signPoints(Shape shape) {
    
    if(shape == null)
    return null;
    	
    Grid grid = findMinCoords(shape);
    
    Coordinate prevCoordinate = shape.getCoordinates().get(0);
    
    LinearEquation linear = new LinearEquation(1,1,1);
    
    List<Coordinate> excludedCoordinates = new ArrayList<>();
    
    for(Coordinate c : shape.getCoordinates()) { // sign shapes coordinates as boundary and not interior
    	
    	
    if(c.x == prevCoordinate.x && c.y == prevCoordinate.y)
    continue;
    
    linear = linear.fromCoordinates(prevCoordinate, c);
    
    for(Coordinate pc : linear.findIntegerCoordinates(prevCoordinate, c))
    grid.points[pc.y][pc.x] = new Point(true, false); // sign as boundary

    int m = LinearEquation.findM(prevCoordinate, c);
    
    for(Coordinate pc : linear.findIntegerCoordinatesY(prevCoordinate, c)) {
    
    if(!c.bit1 && !c.bit2) { // intuistonic tangent algorithm
        	if(m < 0)
            grid.points[pc.y][pc.x] = new Point(true, true); // psuedo boundary for negative m
            
            if(m > 0)
            grid.points[pc.y][pc.x + 1] = new Point(true, true); // psuedo boundary for positive m	
            
            continue;
    }
    
    if(c.bit1 && !c.bit2)
    grid.points[pc.y][pc.x] = new Point(true, true);
    
    if(c.bit2 && !c.bit1)
    grid.points[pc.y][pc.x + 1] = new Point(true, true);	
    

    }
    prevCoordinate = c;

    
    
    if(c.bit1 && c.bit2)
    excludedCoordinates.add(c);
    	
    }
    

    
    for (int x = 0; x < grid.points.length; x++) { 
        Stack<Integer> stack = new Stack<>(); // Her satır için yeni bir stack
        for (int y = 0; y < grid.points[x].length; y++) { 
            if (grid.points[x][y].isBoundary) { // Boundary hücresi bulundu
                if (stack.isEmpty()) {
                    stack.push(y); // İlk B'yi stack'e ekle
                } else { 
                    // İkinci B bulundu, stack patladı
                    int start = stack.pop(); // İlk B'nin indexini al
                    int end = y; // Şimdiki B'nin indexi
                    // İki B arasını I ile doldur
                    for (int i = start + 1; i < end; i++) {
                        grid.points[x][i].isInterior = true;
                    }
                }
            }
        }
        // Satır tamamlandı, stack sıfırlanır
        stack.clear();
    }

    for(Coordinate lastCoord: excludedCoordinates)
    grid.points[lastCoord.y][lastCoord.x] = new Point(true, false);	
    	
    	grid.signed = true;
    	return grid;
    }
  
    //calculate area
    double calculateArea(Shape shape) {
    
    Grid grid = signPoints(shape);
    
    double area = 0;
    
    for(Point[] row : grid.points) {
    for(Point point : row ) {
    if(point.isBoundary && !point.isInterior)
    area += 0.5;	
  
    if(point.isInterior && !point.isBoundary)
    area++;
    }   
    }
    
    return area - 1;
    
    }
    
}

