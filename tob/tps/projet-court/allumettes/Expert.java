package allumettes;

import allumettes.Jeu;

public class Expert implements Strategie {

	/**
	 * Obtenir le nombre d'allumettes que le joueur veut prendre.
	 * 
	 * @return le nombre d'allumettes que le joueur veut prendre
	 */
	@Override
	public int getPrise(Jeu jeu) {
		// Le mode expert a pour but de laisser un multiple de (PRISE_MAX allumettes +
		// 1) +1;
		// ex pour PRISE_MAX = trois ,
		// il faut qu'il reste après notre tour : 1, 5, 9, 13, 17, ... allumettes.
		int nbAl = 1;
		int meilleurCoup;
		int testAlRest = jeu.getNombreAllumettes() - 1;
		boolean coup_interessant = false; // Ce booleen indique si un coup est bon à faire

		while (nbAl <= Jeu.PRISE_MAX && !coup_interessant) {
			if (testAlRest % (Jeu.PRISE_MAX + 1) == 1) {
				coup_interessant = true;
			} else {
				nbAl++;
				testAlRest -= 1;
			}
		}

		if (coup_interessant) {
			meilleurCoup = nbAl;
		} else {
			meilleurCoup = 1; // Dans le cas ou il n'y a pas de bon coup à faire
								// On choisit d'enlever une allumette.
		}

		return meilleurCoup;
	}

}
