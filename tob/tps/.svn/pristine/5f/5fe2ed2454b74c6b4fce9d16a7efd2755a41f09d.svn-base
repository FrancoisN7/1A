import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Classe de test complémentaire de la classe Cercle.
  */
public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les points du sujet
	private Point A, B, C;

	// Les cercles du sujet
	private Cercle C1, C2, C3, C4, C5, C6, C7;

	@Before public void setUp() {
		// Construire les points
		A = new Point(1, 0);
		B = new Point(-1, 0);
		C = new Point(3, 3);

		// Construire les cercles
		C1 = new Cercle(A, B);
		C2 = new Cercle(B, C);
		C2.setCouleur(Color.yellow);
		C3 = new Cercle(A, B, Color.yellow);
		C4 = new Cercle(B, C, Color.blue);
		C5 = Cercle.creerCercle(A, B); //A est le centre du cercle et B est un point du cercle.
		C6 = Cercle.creerCercle(A, C);
		C7 = Cercle.creerCercle(B, C);
	}

    static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}
	
    @Test public void testerE12a() {
		assertEquals(1, C1.getRayon(), EPSILON);
		memesCoordonnees("E12a : erreur",
				new Point(0, 0), C1.getCentre());
	}
	
    @Test public void testerE12b() {
		assertEquals(2.5, C2.getRayon(), EPSILON);
		memesCoordonnees("E12b : erreur",
				new Point (1.0, 1.5), C2.getCentre()); 
    }
    
     @Test public void testerE13a() {
		assertEquals(1, C3.getRayon(), EPSILON);
		memesCoordonnees("E13a : erreur",
				new Point(0, 0), C3.getCentre());
		assert C3.getCouleur()==Color.yellow;
	}
	
	@Test public void testerE13b() {
		assertEquals(2.5, C4.getRayon(), EPSILON);
		memesCoordonnees("E13b : erreur",
				new Point(1.0, 1.5), C4.getCentre());
		assert C4.getCouleur()==Color.blue;
	}
	
	@Test public void testerE14a() {
		assertEquals(A.distance(B), C5.getRayon(), EPSILON);
		memesCoordonnees("E14a : erreur",
				A, C5.getCentre());
	}
	
	@Test public void testerE14b() {
		assertEquals(A.distance(C), C6.getRayon(), EPSILON);
		memesCoordonnees("E14b : erreur",
				A, C6.getCentre());
	}
	
	@Test public void testerE14c() {
		assertEquals(B.distance(C), C7.getRayon(), EPSILON);
		memesCoordonnees("E14b : erreur",
				B, C7.getCentre());
	}
}
