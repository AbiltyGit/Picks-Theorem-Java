package picksTheorem;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
   
    	PickTheoremApp app = new PickTheoremApp();
    	
    	// Default şekli isimle oluştur
       
    	// Available shapes: [square, diamond, trapezoid, octagon, parallelogram, rectangle, hexagon, triangle, pentagon, heart]
    	
    	Shape testShape = new Shape("parallelogram");
    	
    	Grid test2Grid = app.signPoints(testShape);
        System.out.println(test2Grid);

        // Boş şekil oluştur ve koordinat ekle
        Shape customShape = new Shape();
        customShape.addCoordinate(new Coordinate(0, 0));
        customShape.addCoordinate(new Coordinate(1, 1));
        customShape.addCoordinate(new Coordinate(2, 0));
        System.out.println("Custom Shape:\n" + customShape);

        // Tüm varsayılan şekil isimlerini al
        System.out.println("Available shapes: " + Shape.getDefaultShapeNames());
    	
    	// 2D Grid oluştur
        int gridWidth = 10;
        int gridHeight = 10;
        Grid grid = new Grid(gridWidth, gridHeight);
        System.out.println(grid);

        // 1. Test: 2x2 Kare
        List<Coordinate> rectCoordinates = new ArrayList<>();
        
        rectCoordinates.add(new Coordinate(0, 0));
        rectCoordinates.add(new Coordinate(2, 0));
        rectCoordinates.add(new Coordinate(2, 2));
        rectCoordinates.add(new Coordinate(0, 2));
        rectCoordinates.add(new Coordinate(0, 0));
        



        Shape rectangle = new Shape(rectCoordinates);

        
        
        Grid testGrid = app.signPoints(rectangle);
        
        System.out.println(testGrid);
  
        double rectArea = app.calculateArea(rectangle);
        System.out.println("Kare Alanı (2x2): " + rectArea);

        // 2. Test: 3x3 Dik Üçgen
        List<Coordinate> triangleCoordinates = new ArrayList<>();
        triangleCoordinates.add(new Coordinate(0, 0)); // (0,0)
        triangleCoordinates.add(new Coordinate(3, 0)); // (3,0)
        triangleCoordinates.add(new Coordinate(0, 3)); // (0,3)
        triangleCoordinates.add(new Coordinate(0, 0)); // (0,0)

        Shape triangle = new Shape(triangleCoordinates);

        Grid testGrid2 = app.signPoints(triangle);
        
        System.out.println(testGrid2);
        
        double triangleArea = app.calculateArea(triangle);
        System.out.println("Dik Üçgen Alanı (3x3): " + triangleArea);

}
}
