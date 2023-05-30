/**
 * CreneauInvalideException indique qu'une date n'est pas valide.
 */
public class CreneauInvalideException extends RuntimeException{
	
	/** Le créneau demandé. */
	private int creneau;

	/** Initialiser CreneauInvalideException à partir du créneau demandé.
	 * Par exemple, on peut avoir creneau
	 * qui vaut 369.
	 * @param creneau le créneau demandé
	 */
	
	public CreneauInvalideException(int creneau) {
		super("Creneau impossible car " + creneau + "entré : la date n'existe pas");
		this.creneau = creneau;
	}

	/** Retourner le creneau.
	  * @return le creneau */
	public int getCreneau() {
		return this.creneau;
	}
}
