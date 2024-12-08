package picksTheorem;

public class Grid {

    Point [][] points;
    boolean signed;

    public Grid(int width, int height) {
        boolean signed = false;
        this.points = new Point[width][height];
        
        for (int x = 0; x < points.length; x++) {
            for (int y = 0; y < points[x].length; y++) {
            points[x][y] = new Point(false, false); // işaretlenmemiş pointlerle doldur
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = points.length - 1; row >= 0; row--) { // Satırları ters sırayla yazdır
            for (int col = 0; col < points[row].length; col++) { // Sütunları soldan sağa yazdır
                sb.append(points[row][col]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

	
    
}
    


