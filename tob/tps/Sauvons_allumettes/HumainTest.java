package allumettes;
import allumettes.Joueur;

public class HumainTest {
	
	public static void main(String[] args) {
	Joueur j1 = new Joueur("Sarah", new Humain());
	Joueur j2 = new Joueur("Lena", new Humain());
	Arbitre arbitre = new Arbitre(j1, j2);
	Jeu jeu = new ClassJeu();
	arbitre.Jouons(jeu);
	}
}
