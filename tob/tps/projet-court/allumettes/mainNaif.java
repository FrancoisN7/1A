package allumettes;

public class mainNaif {
	public static void main(String[] args) {
		Joueur j1 = new Joueur("Sarah", new Expert());
		Joueur j2 = new Joueur("Lena", new Tricheur());
		Arbitre arbitre = new Arbitre(j1, j2, true);
		Jeu jeu = new ClassJeu(13);
		arbitre.arbitrer(jeu);
	}
}
