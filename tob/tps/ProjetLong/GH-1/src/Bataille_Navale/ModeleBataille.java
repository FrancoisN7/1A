package Bataille_Navale;

public interface ModeleBataille {

	// Modéliser (coder !) le contenu d'une case
	enum Etat { VIDE, CROIX, ROND };

	int TAILLE = 6;	// taille du jeu de Morpion

// Définition des événements qui vont influencer le modèle.

	/** Quitter le jeu. */
	void quitter();

	/** Recommencer une nouvelle partie.*/
	void recommencer();

	/** Cocher la case (x,y). */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	void cocher(int x, int y) throws CaseOccupeeException;

// Requêtes sur le modèle

	/** Est-ce que la partie est terminée ? */
	boolean estTerminee();

	/** Est-ce qu'il y a un gagnant ? */
	boolean estGagnee();

	/** Obtenir le contenu d'une case.
	 * @param x colonne de la case
	 * @param y ligne de la case
	 */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	Etat getValeur(int x, int y);
	
	/** A qui de jouer ? */
	boolean monTour(int nbCoups);
	
	/** Quel est le nombre de coups? */
	int getNbCoups();

}
