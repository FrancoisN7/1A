package allumettes;

import java.util.Random;

public class Naif implements Strategie {

	/**
	 * Obtenir le nombre d'allumettes que le joueur veut prendre.
	 * 
	 * @return le nombre d'allumettes que le joueur veut prendre
	 */
	@Override
	public int getPrise(Jeu jeu) {
		int alRestantes = jeu.getNombreAllumettes();
		int prise;
		Random aleatoire = new Random();
		if (alRestantes >= Jeu.PRISE_MAX) {
			prise = aleatoire.nextInt(Jeu.PRISE_MAX) + 1; // Générer un nombre entre 1 et PRISE_MAX
		} else {
			prise = aleatoire.nextInt(alRestantes) + 1;
		}
		return prise;
	}

}
