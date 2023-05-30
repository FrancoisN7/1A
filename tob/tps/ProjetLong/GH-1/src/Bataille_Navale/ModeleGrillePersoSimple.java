package Bataille_Navale;
import java.lang.*;
import Bataille_Navale.ModeleBataille.Etat;

public class ModeleGrillePersoSimple implements ModeleGrillePerso {

	/** La zone de jeu */
	private Etat[][] cases;

	private Etat joueur = Etat.CROIX;

	
	/** Le nombres de cases cochées */
	private int nbCoups;
	private int nbcasestrouves;
	/** Coup précédent*/
	private int posi_lig;
	private int posi_col;
	/** Est-ce que la partie est gagnée ? */
	private boolean gagnee;
	private boolean grilleFinie;

	public ModeleGrillePersoSimple() {
		// Créer le damier
		this.cases = new Etat[ModeleBataille.TAILLE][ModeleBataille.TAILLE];

		// Commencer une partie
		initialiser();
	}

	/** Est-ce que la partie est terminée ? */
	public boolean estTerminee() {
		return false;
	}

	/** Est-ce qu'il y a un gagnant ? */
	public boolean estGagnee() {
		gagnee= false;
		if (this.nbcasestrouves == 6) {
			gagnee = true;
		}
		return gagnee;
	}

	/** Est-ce que la case (i,j) est vide ? */
	public boolean estVide(int i, int j) {
		return getValeur(i,j) == Etat.VIDE;
	}

	public Etat getValeur(int x, int y) {
		return this.cases[x][y];
	}

	/** Initialiser le jeu pour faire une nouvelle partie */
	private void initialiser() {
		// Initialiser les cases
		for (int i = 0; i < this.cases.length; i++) {
			for (int j = 0; j < this.cases[i].length; j++) {
				this.cases[i][j] = Etat.VIDE;
				posi_lig = -1;
				posi_col = -1;
			}
		}	

		// Initialiser le nb de coups
		this.nbCoups = 0;

		// Initialiser gagnée
		gagnee = false;
	}

	/** Jouer en (i,j) pour le joueur */
	//@ requires estVide(i,j);
	//@ ensures getValeur(i,j) == joueur;
	private void jouer(int i, int j) {
		this.cases[i][j] = this.joueur;
		this.nbCoups++;
		posi_lig = i;
		posi_col = j;
		
		if(nbCoups ==6) {
			grilleFinie=true;
		}


		gagnee = this.estGagnee();
	}


	public int getNbCoups() {
		return this.nbCoups;
	}
	
	public void quitter() {
	}

	public void recommencer() {
		this.initialiser();
	}

	public boolean estVoisin(int x, int y, int i, int j) {
		return (Math.abs(x-i)==0) && (Math.abs(y-j)==1) || (Math.abs(x-i)==1) && (Math.abs(y-j)==0);
	}
	
	public void cocherCreer(int x, int y) throws CaseOccupeeException {
		if (nbCoups<6) {
			if (!this.estTerminee()) {	// La partie est en cours
				if (this.estVide(x, y)) {
					if (this.estVoisin(x,y,posi_lig,posi_col) || posi_lig == -1) {
						// Jouer la case
						this.jouer(x, y);
						if (nbCoups % 2 == 0) {
							posi_lig = -1;
						}
					}
					}
				} else {
					throw new CaseOccupeeException("Impossible, la case est occupée !");
				}
			}
		}
	public void cocherRandom(int x, int y) throws CaseOccupeeException {
		if (nbCoups>=6) {
			if (!this.grilleFinie) {	// La partie est en cours
				if (this.estVide(x, y)) {
					this.jouer(x, y);
				}
				} else {
					throw new CaseOccupeeException("Impossible, la case est occupée !");
				}
			}
		}
	}


