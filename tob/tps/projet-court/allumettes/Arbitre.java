package allumettes;

import allumettes.Strategie;

public class Arbitre {

	private Joueur j1; // Joueur1
	private Joueur j2; // Joueur2
	private boolean confiant; // mode confiant

	/**
	 * Construire un arbitre à partir de son nom.
	 * 
	 * @param Joueur1 le joueur 1
	 * @param Joueur2 le joueur 2
	 */
	public Arbitre(Joueur Joueur1, Joueur Joueur2) {
		this.j1 = Joueur1;
		this.j2 = Joueur2;
	}

	public Arbitre(Joueur Joueur1, Joueur Joueur2, boolean confiant) {
		this.j1 = Joueur1;
		this.j2 = Joueur2;
		this.confiant = confiant;

	}

	/**
	 * Faire jouer un joueur et afficher le déroulement du jeu.
	 */
	private boolean FaireJouer(Joueur joueur, Jeu jeu) {
		boolean finBoucle = false;
		// Nombre d'Allumettes restantes
		int NombreAllu = jeu.getNombreAllumettes();
		System.out.println("Allumettes restantes : " + NombreAllu);
		try {
			Strategie Strategie = joueur.getStrategie();
			String StrStrat = Strategie.toString();
			if (StrStrat.contains("Tricheur") && confiant) {
				System.out.println("[Je triche...]");
				System.out.println("[Allumettes restantes : 2]");
			}
			FaireJouerInterne(joueur, jeu);
		} catch (OperationInterditeException e) {
			System.out.println("[Je triche...]");
			System.out.println("Abandon de la partie car " + joueur.getNom() + " triche !");
			finBoucle = true;
		}
		return (finBoucle);

	}

	private void FaireJouerInterne(Joueur joueur, Jeu jeu) {
		int NombreAllu = jeu.getNombreAllumettes();

		// FaireJouer un joueur
		Strategie Strategie = joueur.getStrategie();
		String NomJ = joueur.getNom();
		String StrStrat = Strategie.toString();
//		if (StrStrat.contains("Tricheur") && !confiant)  {
//			throw new OperationImpossibleException();
//		}

		// Affichage dans le cas d'un humain et d'un tricheur
		if (StrStrat.contains("Humain")) {
			System.out.print(NomJ + ", combien d'allumettes ? ");
		}

		try {
			int prise;
			if (confiant) {
				prise = Strategie.getPrise(jeu);
			} else {
				prise = Strategie.getPrise(new Proxy(jeu));
			}

			// Récapitulatif du coup du joueur
			// Disjonction de cas pour l'accord de "allumette(s)"
			if (prise == -1 || prise == 1 || prise == 0) {
				AfficherRecap(NomJ, prise);
				System.out.println(".");
				if (prise == 1) {
				System.out.print("\n");
				}
			} else if ((prise == 2 || prise == jeu.PRISE_MAX) && prise <= NombreAllu) {
				AfficherRecap(NomJ, prise);
				System.out.println("s.\n");
			} else {
				AfficherRecap(NomJ, prise);
				System.out.println("s.");
			}

			// Retirer les allumettes demandées du jeu
			try {
				jeu.retirer(prise);
			} catch (CoupInvalideException e) {
				int coup = e.getCoup();
				String prob = e.getProbleme();
				String str_imp = "Impossible ! Nombre invalide : ";
				if (prob == "(< 1)") {
					System.out.println(str_imp + prise + " " + prob + "\n");
					FaireJouer(joueur, jeu);
				}

				if (prob == "NbAl") {
					System.out.println(str_imp + coup + " (> " + NombreAllu + ")\n");
					FaireJouer(joueur, jeu);
				}

				if (prob == "NbMax") {
					System.out.println(str_imp + coup + " (> " + Jeu.PRISE_MAX + ")\n");
					FaireJouer(joueur, jeu);
				}

			}

		} catch (

		ConfigurationException e) {
			System.out.println("Vous devez donner un entier.");
			FaireJouerInterne(joueur, jeu);
		}
	}

	// Récapitulatif du coup du joueur
	// Disjonction de cas pour l'accord de "allumette(s)"
	private void AfficherRecap(String NomJ, int prise) {
		System.out.print(NomJ + " prend " + prise + " allumette");
	}

	/**
	 * Faire jouer les 2 joueurs l'un après l'autre et afficher le déroulement du
	 * jeu jusqu'à la défaite de l'un des 2 joueurs.
	 */
	public void arbitrer(Jeu jeu) {
		boolean finBoucle = false;
		String vainqueur;
		String perdant;
		int NombreAllu = jeu.getNombreAllumettes();
		int tour = 1; // définit le tour du joueur

		while (NombreAllu != 0 && !finBoucle) {

			if (tour == 1) {
				finBoucle = FaireJouer(j1, jeu);
				tour = 2;
			} else {
				finBoucle = FaireJouer(j2, jeu);
				tour = 1;
			}
			NombreAllu = jeu.getNombreAllumettes();
		}
		if (tour == 2) {
			vainqueur = j2.getNom();
			perdant = j1.getNom();

		} else {
			vainqueur = j1.getNom();
			perdant = j2.getNom();
		}

		// Pas d'affichage si il y a un tricheur
		// et que le mode est confiant
		Strategie Strategie1 = j1.getStrategie();
		Strategie Strategie2 = j2.getStrategie();
		boolean trich1 = Strategie1.toString().contains("Tricheur");
		boolean trich2 = Strategie2.toString().contains("Tricheur");
		boolean trich_pres = trich1 || trich2;
		if (!trich_pres ||  (confiant && trich_pres)) {
			System.out.println(perdant + " perd !");
			System.out.println(vainqueur + " gagne !");
		}
	}
}