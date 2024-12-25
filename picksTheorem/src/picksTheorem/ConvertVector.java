package picksTheorem;

import java.util.ArrayList;
import java.util.List;

public class ConvertVector {

    // Converts a grid to vector graphics in SVG format
    public static String convertToSVG(Grid grid) {
        StringBuilder svgBuilder = new StringBuilder();

        int width = grid.points[0].length; // Use grid width as is
        int height = grid.points.length; // Use grid height as is

        svgBuilder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 " + width + " " + height + "\" stroke=\"black\" stroke-width=\"0.05\">\n");

        // Draw grid intersections as empty squares
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String color = "white"; // Default color for empty points
                if (x < grid.points[0].length && y < grid.points.length) {
                    if (grid.points[y][x].isBoundary && !grid.points[y][x].isInterior) {
                        color = "red";
                    } else if (grid.points[y][x].isInterior && !grid.points[y][x].isBoundary) {
                        color = "blue";
                    }
                }

                svgBuilder.append("  <rect x=\"" + (x - 0.5) + "\" y=\"" + (y - 0.5) + "\" width=\"1\" height=\"1\" fill=\"none\" stroke=\"black\" />\n");
                svgBuilder.append("  <circle cx=\"" + x + "\" cy=\"" + y + "\" r=\"0.3\" fill=\"" + color + "\" />\n");
            }
        }

        svgBuilder.append("</svg>");
        return svgBuilder.toString();
    }
        // Default scale = 50
    public static String combineShapeAndGridToSVG(Shape shape, Grid grid) {
        return combineShapeAndGridToSVG(shape, grid, 50);    
    }
    
    
    // Combines Shape and Grid into a single SVG
    public static String combineShapeAndGridToSVG(Shape shape, Grid grid, int scale) {
        StringBuilder svgBuilder = new StringBuilder();

        // Adjust canvas size to fit the grid
        int width = grid.points[0].length * scale;
        int height = grid.points.length * scale;

        // Adjust viewBox for proper alignment
        int viewBoxX = 0;
        int viewBoxY = 0;
        int viewBoxWidth = width;
        int viewBoxHeight = height + scale / 4; // Extend height for additional text

        svgBuilder.append("<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"")
                  .append(viewBoxX).append(" ")
                  .append(viewBoxY).append(" ")
                  .append(viewBoxWidth).append(" ")
                  .append(viewBoxHeight)
                  .append("\" stroke=\"black\" stroke-width=\"" + scale * 0.005 + "\">\n");


        // Add shape to SVG
        List<Coordinate> coordinates = shape.getCoordinates();
        svgBuilder.append("  <polygon points=\"");
        for (Coordinate coord : coordinates) {
            svgBuilder.append((coord.x * scale + (0.5 * scale)) + "," + (coord.y * scale + (0.5 * scale)) + " ");
        }
        svgBuilder.append("\" fill=\"#90EE90\" stroke=\"black\" stroke-width=\"" + scale * 0.05 + "\" />\n");

        for (int i = 0; i < coordinates.size() - 1; i++) {
            Coordinate start = coordinates.get(i);
            Coordinate end = coordinates.get(i + 1);

            svgBuilder.append("  <line x1=\"").append(start.x * scale + (0.5 * scale))
                      .append("\" y1=\"").append(start.y * scale + (0.5 * scale))
                      .append("\" x2=\"").append(end.x * scale + (0.5 * scale))
                      .append("\" y2=\"").append(end.y * scale + (0.5 * scale))
                      .append("\" stroke=\"black\" stroke-width=\"" + scale * 0.05 + "\" />\n");

        }
        
        // Add grid points to SVG as empty squares
        for (int x = 1; x < grid.points[0].length; x++) {
            for (int y = 1; y < grid.points.length; y++) {
                int scaledX = x * scale;
                int scaledY = y * scale;

                // Draw rectangles
                svgBuilder.append("  <rect x=\"")
                          .append(scaledX - scale / 2).append("\" y=\"")
                          .append(scaledY - scale / 2).append("\" width=\"")
                          .append(scale).append("\" height=\"")
                          .append(scale).append("\" fill=\"none\" stroke=\"black\" />\n");
            }
        }

        for (int x = 0; x < grid.points[0].length; x++) {
            for (int y = 0; y < grid.points.length; y++) {
                int scaledX = x * scale;
                int scaledY = y * scale;

                // Determine circle color
                String color = "white"; // Default color for empty points
                if (x < grid.points[0].length && y < grid.points.length) {
                    if (grid.points[y][x].isBoundary && !grid.points[y][x].isInterior) {
                        color = "red";
                    } else if (grid.points[y][x].isInterior && !grid.points[y][x].isBoundary) {
                        color = "blue";
                    }
                }

                // Draw circles
                svgBuilder.append("  <circle cx=\"")
                          .append(scaledX + (0.5 * scale)).append("\" cy=\"")
                          .append(scaledY + (0.5 * scale)).append("\" r=\"")
                          .append(scale / 6.0).append("\" fill=\"")
                          .append(color).append("\" />\n");
            }
        }

        // Calculate area and add text
        PickTheoremApp app = new PickTheoremApp();
        double area = app.calculateArea(shape);
        svgBuilder.append("  <text x=\"" + (width / 2) + "\" y=\"" + (height + (scale / 6)) + "\" font-size=\"" + (scale * 0.4) + "\" text-anchor=\"middle\">Area: " + area + "</text>\n");

        svgBuilder.append("</svg>");
        return svgBuilder.toString();
    }

    public static void main(String[] args) {
        
        // Available shapes: [square, diamond, trapezoid, octagon, parallelogram, rectangle, hexagon, triangle, pentagon, heart]
        
        // Load shape
        Shape shape = Shape.getDefaultShape("trapezoid");

        // Combine Shape and Grid into a single SVG with expanded grid
        SVGFileConverter.getSVGofAShape(shape, 35, "output", "trapezoid.svg");
    }
}
