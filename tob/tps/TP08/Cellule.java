
public class Cellule {
	public int element;
	public Cellule suivant;
	
	public Cellule(int elem, Cellule suiv) {
		this.element = elem;
		this.suivant = suiv;
	}
	
	public Cellule(int elem) {
		this.element = elem;
		this.suivant = null;
	}
}
