
public class EnsembleChaine implements Ensemble {
	//@ public invariant estVide() <==> cardinal() == 0;
	//@ public invariant 0 <= cardinal();
	
	private Cellule cellule;
	
	public EnsembleChaine() {
		this.cellule = null;
	}
	
	
	/** Obtenir le nombre d'éléments dans l'ensemble.
	 * @return nombre d'éléments dans l'ensemble.  */
	public int cardinal() {
		int card = 0;
		Cellule courant = cellule;
		while (courant != null) {
			courant = courant.suivant;
			card += 1;
		}
		return card;
	}
	
	

	/** Savoir si l'ensemble est vide.
	 * @return Est-ce que l'ensemble est vide ? */
	public boolean estVide() {
		boolean vide = true;
		if (this.cellule != null) {
			vide = false;
		}
		return vide;
	}
	
	
	/** Savoir si un élément est présent dans l'ensemble.
	 * @param x l'élément cherché
	 * @return x est dans l'ensemble */
	public boolean contient(int x) {
		boolean cont = false;
		Cellule courant = cellule;
		while (courant != null && !cont) {
			if (courant.element == x) {
				cont = true;
			}
			courant = courant.suivant;
		}
		return cont;
	}

	/** Ajouter un élément dans l'ensemble.
	 * @param x l'élément à ajouter */
	//@ ensures contient(x);        // élément ajouté
	
	public void ajouter(int x) {
		Cellule Tete = new Cellule(x,null);
		this.cellule.suivant = Tete;
	}
	
	/** Enlever un élément de l'ensemble.
	  * @param x l'élément à supprimer */
	//@ ensures ! contient(x);      // élément supprimé
	public void supprimer(int x) {
		Cellule courant = cellule;
		while (courant.element!=x) {
			courant = courant.suivant;
		}
		courant.element=courant.suivant.element;
		courant.suivant=courant.suivant.suivant;
	}
}
