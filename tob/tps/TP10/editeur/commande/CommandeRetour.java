package editeur.commande;
import editeur.Ligne;

/** Retourner au début de la ligne.
 */
public class CommandeRetour 
	extends CommandeLigne
{

	/** Initialiser la ligne sur laquelle travaille
	 * cette commande.
	 * @param l la ligne
	 */
	//@ requires l != null;	// la ligne doit être définie
	public CommandeRetour(Ligne l) {
		super(l);
	}
	
	public void executer() {
		ligne.raz();
	}

	public boolean estExecutable() {
		return(ligne.getLongueur() > 0);	// ligne non vide et curseur sur la première position
		
	}

}
