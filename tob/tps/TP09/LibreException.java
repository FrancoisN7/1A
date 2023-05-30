public class LibreException extends Exception{
	/** Le créneau à supprimer. */
	private int creneau;

	/** Initialiser LibreException à partir du créneau à supprimer.
	 * Par exemple, on peut avoir creneau
	 * qui vaut 324.
	 * @param creneau le créneau demandé
	 */
	
	public LibreException(int creneau) {
		super("Creneau non supprimé car " + creneau + ": créneau libre");
		this.creneau = creneau;
	}

	/** Retourner le creneau.
	  * @return le creneau */
	public int getCreneau() {
		return this.creneau;
	}
}
