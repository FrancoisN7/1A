package allumettes;
import org.junit.*;
import static org.junit.Assert.*;

public class RapideTest {

	Strategie Strategie = new Rapide();
	Jeu jeu = new ClassJeu(13);
	@Test
	public void testStrategie() {
		assertTrue(Strategie.toString().contains("Rapide"));
		int nbPrises = Strategie.getPrise(jeu);
		assertTrue(nbPrises==3);
		nbPrises = Strategie.getPrise(jeu);
		assertTrue(nbPrises==3);
		nbPrises = Strategie.getPrise(jeu);
		assertTrue(nbPrises==3);
		nbPrises = Strategie.getPrise(jeu);
		assertTrue(nbPrises==3);
		nbPrises = Strategie.getPrise(jeu);
		assertTrue(nbPrises==3);
	}
}
