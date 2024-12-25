package picksTheorem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class SVGFileConverter {

	//default scale = 50
	public static void getSVGofAShape(Shape shape, String directoryPath, String filename) {
	getSVGofAShape(shape, 50, directoryPath, filename);	
	}
	
	public static void getSVGofAShape(Shape shape, int scale, String directoryPath, String filename) {
	
	PickTheoremApp app = new PickTheoremApp();
	
	Grid grid = app.signPoints(shape);
	
	String svgCombined = ConvertVector.combineShapeAndGridToSVG(shape, grid, scale);
	
	saveSVGToFile(svgCombined, directoryPath, filename);
		
	}
	
	public static void saveSVGToFile(String svgContent, String directoryPath, String fileName) {
        File directory = new File(directoryPath);

        // Create directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
        
        // Write SVG content to file
        File svgFile = new File(directory, fileName);
        try (FileWriter writer = new FileWriter(svgFile)) {
            writer.write(svgContent);
            System.out.println("SVG file saved to: " + svgFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save SVG file: " + e.getMessage());
        }
    }
}
