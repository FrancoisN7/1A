	import java.awt.Color;

/** Point modélise un point géométrique dans un plan équipé d'un
 * repère cartésien.  Un point peut être affiché et translaté.
 * Sa distance par rapport à un autre point peut être obtenue.
 * Le point peut être dessiné sur un afficheur.
 *
 * @author  Testu François <Francois.Testu@enseeiht.fr>
 */
public abstract class ObjetGeometrique {

	//@ private invariant getCouleur() != null;
	//@ private invariant getCouleur() == couleur;	// invariant de liaison
	private Color couleur;	// couleur du point

	/** Construire un point à partir de son abscisse et de son ordonnée.
	 * @param vx abscisse
	 * @param vy ordonnée
	 */
	
	public ObjetGeometrique() {
		this.couleur = Color.green;
	}
	

	/** Afficher le point. */
	public abstract void afficher();

   /** Translater le point.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public abstract void translater(double dx, double dy);
	
	/** Dessiner le point sur l'afficheur.
	 * @param afficheur l'afficheur à utiliser
	 */
	public abstract void dessiner(afficheur.Afficheur afficheur);

//  Gestion de la couleur

	/** Obtenir la couleur du point.
	 * @return la couleur du point
	 */
	//@ pure
	public Color getCouleur() {
		return this.couleur;
	}

	/** Changer la couleur du point.
	  * @param nouvelleCouleur nouvelle couleur
	  */
	public void setCouleur(Color nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

}
