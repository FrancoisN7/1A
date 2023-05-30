public class TestSchema {

	public static void main(String[] args) {
		// Créer deux points et un segment à partir de ces points
		Point p1 = new Point(3, 2);
		Point p2 = new Point(6, 9);
		Point p3 = new Point(11, 4);
    
    Schema s = new Schema(p1,p2,p3);
    
    }
}
