package allumettes;

public interface Strategie {
	/**
	 * Obtenir le nombre d'allumettes que le joueur veut prendre.
	 * 
	 * @return le nombre d'allumettes que le joueur veut prendre
	 */
	/* @ pure helper @ */ int getPrise(Jeu jeu);
}