package picksTheorem;

import java.util.ArrayList;
import java.util.List;

public class LinearEquation {
    int x; // x'in katsayısı
    int y; // y'nin katsayısı
    int independent; // bağımsız sayı (c)

    // Constructor
    public LinearEquation(int x, int y, int independent) {
        this.x = x;
        this.y = y;
        this.independent = independent;
    }

    public boolean isOnLine(int x, int y) {
        return this.x * x + this.y * y + this.independent == 0;
    }

    public static int findM(Coordinate p1, Coordinate p2) {
    
    	
    	int deltaY = p2.y - p1.y;
        int deltaX = p2.x - p1.x;
    
        if(deltaX == 0)
        return 0;
        	
        return deltaY / deltaX;
    	
    }
    
    public static LinearEquation fromCoordinates(Coordinate p1, Coordinate p2) {
        // Eğim hesaplama
        int deltaY = p2.y - p1.y;
        int deltaX = p2.x - p1.x;

        // Katsayılar
        int a = deltaY; // y katsayısı
        int b = -deltaX; // x katsayısı
        int c = deltaX * p1.y - deltaY * p1.x; // sabit terim

        // Yeni LinearEquation nesnesi döndürülür
        return new LinearEquation(a, b, c);
    }

 // Belirli iki koordinat arasında tam sayı koordinatlarını döndüren metot
    public List<Coordinate> findIntegerCoordinates(Coordinate start, Coordinate end) {
        List<Coordinate> coordinates = new ArrayList<>();

        // Başlangıç koordinatının denklemi sağlayıp sağlamadığını kontrol et
        boolean isStartOnLine = this.x * start.x + this.y * start.y + this.independent == 0;

        // Bitiş koordinatının denklemi sağlayıp sağlamadığını kontrol et
        boolean isEndOnLine = this.x * end.x + this.y * end.y + this.independent == 0;

        // Eğer başlangıç veya bitiş koordinatları denklemi sağlamıyorsa, hata fırlat
        if (!isStartOnLine || !isEndOnLine) {
            throw new IllegalArgumentException("Başlangıç veya bitiş koordinatları denklemin üzerinde değil!");
        }

        // Sınırları belirle
        int xStart = Math.min(start.x, end.x);
        int xEnd = Math.max(start.x, end.x);
        int yStart = Math.min(start.y, end.y);
        int yEnd = Math.max(start.y, end.y);

        // Belirli aralıktaki tam sayı koordinatlarını kontrol et
        for (int i = xStart; i <= xEnd; i++) {
            for (int j = yStart; j <= yEnd; j++) {
                if (this.x * i + this.y * j + this.independent == 0) {
                    coordinates.add(new Coordinate(i, j));
                }
            }
        }

        return coordinates;
    }

    public List<Coordinate> findIntegerCoordinatesY(Coordinate start, Coordinate end) {
        List<Coordinate> coordinates = new ArrayList<>();

        // Başlangıç koordinatının denklemi sağlayıp sağlamadığını kontrol et
        boolean isStartOnLine = this.x * start.x + this.y * start.y + this.independent == 0;

        // Bitiş koordinatının denklemi sağlayıp sağlamadığını kontrol et
        boolean isEndOnLine = this.x * end.x + this.y * end.y + this.independent == 0;

        // Eğer başlangıç veya bitiş koordinatları denklemi sağlamıyorsa, hata fırlat
        if (!isStartOnLine || !isEndOnLine) {
            throw new IllegalArgumentException("Başlangıç veya bitiş koordinatları denklemin üzerinde değil!");
        }

        // Sınırları belirle
        int yStart = Math.min(start.y, end.y);
        int yEnd = Math.max(start.y, end.y);

        // Belirli y değerleri için x'in tam sayı olmamasını kontrol et
        for (int j = yStart; j <= yEnd; j++) {
            // Denkleme göre x'i hesapla: x = (-b*y - c) / a
            double x = (-this.y * j - this.independent) / (double) this.x;

            // Eğer x tam sayı değilse, koordinatı ekle
            if (x % 1 != 0) {
                coordinates.add(new Coordinate(x, j));
            }
        }

        return coordinates;
    }





    
    
    
    // toString metodu
    @Override
    public String toString() {
        StringBuilder equation = new StringBuilder();

        // x'in katsayısı
        if (x != 0) {
            equation.append(x).append("x");
        }

        // y'nin katsayısı
        if (y > 0) {
            equation.append("+").append(y).append("y");
        } else if (y < 0) {
            equation.append(y).append("y");
        }

        // bağımsız sayı
        if (independent > 0) {
            equation.append("+").append(independent);
        } else if (independent < 0) {
            equation.append(independent);
        }

        // Eşittir sıfır
        equation.append("=0");

        return equation.toString();
    }

}
