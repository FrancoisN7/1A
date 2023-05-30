/** Exception qui indique qu'un créneau demandé est occupé.
 */
public class OccupeException extends Exception{

	/** Le créneau demandé. */
	private int creneau;

	/** Rendez_vous existant. */
	private String rdv;

	/** Initialiser CreneauInvalideException à partir du créneau demandé
	 * et du rendez-vous existant.  Par exemple, on peut avoir creneau
	 * qui vaut 14 et le rdv "Examen".
	 * @param creneau le créneau demandé
	 * @param rdv le rendez-vous existant
	 */
	public OccupeException(int creneau, String rdv) {
		System.out.println("Creneau impossible car " + rdv + " le " + creneau);
		this.creneau = creneau;
		this.rdv = rdv;
	}

	/** Retourner le creneau.
	  * @return le creneau */
	public int getCreneau() {
		return this.creneau;
	}

	/** Indiquer le rdv existant.
	  * @return le rdv */
	public String getRdv() {
		return this.rdv;
	}

}
