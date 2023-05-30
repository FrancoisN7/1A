package editeur.commande;
import editeur.Ligne;
import menu.Menu;
import menu.Commande;
public class CommandeSousMenu 
	extends CommandeLigne
{
	String nom;
	Menu sousMenu;
	
	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeSousMenu(Ligne l, String nom) {
		super(l);
		sousMenu = new Menu(nom);
	}
	
	public void executer() {
		do {
			sousMenu = new Menu(nom) ;
			// Afficher la ligne
			System.out.println();
			ligne.afficher();
			System.out.println();

			// Afficher le menu
			sousMenu.afficher();

			// Sélectionner une entrée dans le menu
			sousMenu.selectionner();

			// Valider l'entrée sélectionnée
			sousMenu.valider();

		} while (! sousMenu.estQuitte());
	}
	
	public boolean estExecutable() {
		return(ligne.getLongueur() > 0);	// ligne non vide et curseur sur la première position
		
	}
	
	public void ajoutersousmenu(String texte, Commande cmd) {
		sousMenu.ajouter(texte, cmd);
		
		
	}

	
}
