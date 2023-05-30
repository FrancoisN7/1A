package allumettes;

public class RapideTest {
	
	public static void main(String[] args) {
	Joueur j1 = new Joueur("Sarah", new Rapide());
	Joueur j2 = new Joueur("Lena", new Rapide());
	Arbitre arbitre = new Arbitre(j1, j2);
	Jeu jeu = new ClassJeu();
	arbitre.Jouons(jeu);
	}
}
