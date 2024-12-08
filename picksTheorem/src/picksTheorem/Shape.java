package picksTheorem;

import java.util.*;

public class Shape {
    List<Coordinate> coordinates;

 // Default shapes map
    private static final Map<String, Shape> defaultShapes = new HashMap<>();

    static {
        // Initialize default shapes
        defaultShapes.put("square", new Shape(Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(2, 0),
                new Coordinate(2, 2),
                new Coordinate(0, 2),
                new Coordinate(0, 0) // Closing the shape
        )));

        defaultShapes.put("triangle", new Shape(Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(3, 0),
                new Coordinate(0, 3),
                new Coordinate(0, 0) // Closing the shape
        )));

        defaultShapes.put("rectangle", new Shape(Arrays.asList(
                new Coordinate(0, 0),
                new Coordinate(4, 0),
                new Coordinate(4, 2),
                new Coordinate(0, 2),
                new Coordinate(0, 0) // Closing the shape
        )));

        defaultShapes.put("pentagon", new Shape(Arrays.asList(
                new Coordinate(1, 0),
                new Coordinate(3, 0),
                new Coordinate(4, 2),
                new Coordinate(2, 4),
                new Coordinate(0, 2),
                new Coordinate(1, 0) // Closing the shape
        )));

        defaultShapes.put("star", new Shape(Arrays.asList(
        		new Coordinate(4, 6),
        		new Coordinate(5, 4),
        		new Coordinate(7, 4),
        		new Coordinate(6, 2),
        		new Coordinate(8, 0),
        		new Coordinate(4, 1),
        		new Coordinate(0, 0),
        		new Coordinate(2, 2),
        		new Coordinate(1, 4),
        		new Coordinate(3, 4),
        		new Coordinate(4, 6) // Closing the shape

        )));

        defaultShapes.put("hexagon", new Shape(Arrays.asList(
                new Coordinate(2, 0),
                new Coordinate(4, 1),
                new Coordinate(4, 3),
                new Coordinate(2, 4),
                new Coordinate(0, 3),
                new Coordinate(0, 1),
                new Coordinate(2, 0) // Closing the shape
        )));

        defaultShapes.put("octagon", new Shape(Arrays.asList(
                new Coordinate(1, 0),
                new Coordinate(3, 0),
                new Coordinate(4, 1),
                new Coordinate(4, 3),
                new Coordinate(3, 4),
                new Coordinate(1, 4),
                new Coordinate(0, 3),
                new Coordinate(0, 1),
                new Coordinate(1, 0) // Closing the shape
        )));

        defaultShapes.put("diamond", new Shape(Arrays.asList(
                new Coordinate(0, 2),
                new Coordinate(2, 0),
                new Coordinate(4, 2),
                new Coordinate(2, 4),
                new Coordinate(0, 2) // Closing the shape
        )));

        defaultShapes.put("parallelogram", new Shape(Arrays.asList(
                new Coordinate(1, 0),
                new Coordinate(4, 0),
                new Coordinate(3, 2, false, true),
                new Coordinate(0, 2),
                new Coordinate(1, 0) // Closing the shape
        )));

        defaultShapes.put("trapezoid", new Shape(Arrays.asList(
                new Coordinate(1, 0),
                new Coordinate(3, 0),
                new Coordinate(4, 2),
                new Coordinate(0, 2),
                new Coordinate(1, 0) // Closing the shape
        )));

        defaultShapes.put("heart", new Shape(Arrays.asList(
                new Coordinate(2, 0),
                new Coordinate(4, 2),
                new Coordinate(3, 4),
                new Coordinate(2, 3),
                new Coordinate(1, 4),
                new Coordinate(0, 2),
                new Coordinate(2, 0) // Closing the shape
        )));
    }

    // Default constructor for empty shapes
    public Shape() {
        this.coordinates = new ArrayList<>();
    }

    // Constructor with coordinates
    public Shape(List<Coordinate> coordinates) {
        this.coordinates = new ArrayList<>(coordinates);
    }

    // Constructor with shape name
    public Shape(String name) {
        Shape shape = defaultShapes.get(name);
        if (shape != null) {
            this.coordinates = new ArrayList<>(shape.coordinates);
        } else {
            System.out.println("Warning: Shape \"" + name + "\" not found. Creating an empty shape.");
            this.coordinates = new ArrayList<>();
        }
    }

    public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	// Add a new coordinate to the shape
    public void addCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }

    // Scale all coordinates of the shape
    public void scale(double factor) {
        for (int i = 0; i < coordinates.size(); i++) {
            Coordinate coord = coordinates.get(i);
            coordinates.set(i, new Coordinate((int) (coord.x * factor), (int) (coord.y * factor)));
        }
    }


    private boolean isLatticePoint(Coordinate point) {
        return point.x % 1 == 0 && point.y % 1 == 0;
    }

    private int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

    public static Shape getDefaultShape(String name) {
        return defaultShapes.get(name);
    }

    public static Set<String> getDefaultShapeNames() {
        return defaultShapes.keySet();
    }
    
    public void showGrid() {
    
    	
    	
    }

    @Override
    public String toString() {
        if (coordinates.isEmpty()) {
            return "Empty shape.";
        }
        StringBuilder sb = new StringBuilder();
        for (Coordinate c : coordinates) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }
}
