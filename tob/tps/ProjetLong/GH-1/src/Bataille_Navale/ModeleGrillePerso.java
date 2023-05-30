package Bataille_Navale;

import Bataille_Navale.ModeleGrillePerso.Etat;

public interface ModeleGrillePerso {

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
	void cocherCreer(int x, int y) throws CaseOccupeeException;
	
	/** Cocher la case (x,y). */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	void cocherRandom(int x, int y) throws CaseOccupeeException;
// Requêtes sur le modèle

	/** Est-ce qu'il y a un gagnant ? */
	boolean estGagnee();

	/** Obtenir le contenu d'une case.
	 * @param x colonne de la case
	 * @param y ligne de la case
	 */
	//@ requires x >= 0 && x < TAILLE;
	//@ requires y >= 0 && y < TAILLE;
	Etat getValeur(int x, int y);
	
	/** Quel est le nombre de coups? */
	int getNbCoups();
	
	/** Est-ce que la case est vide ? */
	boolean estVide(int x, int y);

	
}

