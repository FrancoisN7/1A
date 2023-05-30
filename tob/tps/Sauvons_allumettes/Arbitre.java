package allumettes;
import allumettes.Strategie;
import allumettes.Joueur;
import allumettes.CoupInvalideException;

public class Arbitre {
	
	private Joueur j1 ; //Joueur1	
	private Joueur j2 ; //Joueur2

	/** Construire un arbitre à partir de son nom.
	 * @param Joueur1 le joueur 1
	 * @param Joueur2 le joueur 2
	 */
	public Arbitre(Joueur Joueur1, Joueur Joueur2) {
		this.j1 = Joueur1;
		this.j2 = Joueur2;
	}
	
	
	/** Faire jouer un joueur 
	 * et afficher le déroulement du jeu.
	 */
	private void FaireJouer(Joueur joueur, Jeu jeu) {
		//Nombre d'Allumettes restantes
		int NombreAllu = jeu.getNombreAllumettes();
		System.out.println("Allumettes restantes : " + NombreAllu);
	
		
		//FaireJouer un joueur
		Strategie Strategie = joueur.getStrategie();
		String NomJ = joueur.getNom();
        
        //Affichage dans le cas d'un humain et d'un tricheur
        String StrStrat = Strategie.toString();
        boolean estHumain = false;
		if (StrStrat.contains("Humain")) {
	        System.out.print(NomJ + ", combien d'allumettes ? ");
	        estHumain = true;
		}
        int prise = Strategie.getPrise(NombreAllu);
        if (estHumain) {
        	 System.out.println(prise);
        }
        
        
        
        //Récapitulatif du coup du joueur
        //Disjonction de cas pour l'accord de "allumette(s)"
        if (prise == 1) {
        System.out.println(NomJ + " prend 1 allumette.\n");
        }
        else {
        System.out.println(NomJ + " prend " + prise + " allumettes.\n");
        }
        
        //Retirer les allumettes demandées du jeu
        try {
			jeu.retirer(prise);
		} catch (CoupInvalideException e) {
			int coup = e.getCoup();
			String prob = e.getProbleme();
			String str_imp = "Impossible ! Nombre invalide : ";
			if (prob == "(< 1)") {
				System.out.println(str_imp + prise 
						+ " " + prob +"\n");
				FaireJouer(joueur, jeu);
			}

			if (prob == "NbMax") {
				System.out.println(str_imp + coup 
						+ " (> " + Jeu.PRISE_MAX + ")\n");
				FaireJouer(joueur, jeu);
			}
			
			if (prob == "NbAl") {
				System.out.println(str_imp + coup 
						+ " (> " + NombreAllu +")\n");
				FaireJouer(joueur, jeu);
			}
		}

	}

	
	
	/** Faire jouer les 2 joueurs l'un après l'autre 
	 * et afficher le déroulement du jeu jusqu'à la 
	 * défaite de l'un des 2 joueurs.
	 */
	public void Jouons(Jeu jeu) {
		String vainqueur;
		String perdant;
		int NombreAllu = jeu.getNombreAllumettes();
		int tour = 1; //définit le tour du joueur
		
		while (NombreAllu != 0) {
			
			if (tour == 1) {
				FaireJouer(j1, jeu);
				tour = 2;
			}
			else {
				FaireJouer(j2, jeu);
				tour = 1;
			}
			NombreAllu = jeu.getNombreAllumettes();				
		}
		if (tour == 2) {
			vainqueur = j2.getNom();
			perdant = j1.getNom();
			
		}
		else {
			vainqueur = j1.getNom();
			perdant = j2.getNom();
		}
		System.out.println(perdant + " perd !");
		System.out.println(vainqueur + " gagne !");
	}
	
    
}

