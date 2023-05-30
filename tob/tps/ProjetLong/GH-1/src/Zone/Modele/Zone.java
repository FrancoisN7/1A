package Zone.Modele;

import Affichable.Affichable;
import Affichable.Interactif;

import java.beans.PropertyChangeListener;

/**
 * Une zone est un élément affichable qui possède des éléments interactifs
 */
public interface Zone extends Affichable, Iterable<Interactif> {
	
	/**
	 * Ajouter un élément interactif dans la zone.
	 * @param element l'élément interactif à ajouter
	 */
	void ajouterElementInteractif(Interactif element);

	/**
	 * Supprimer un élément interactif de la zone.
	 * @param element l'élément interactif à supprimer
	 */
	void supprimerElementInteractif(Interactif element);

	/**
	 * Ajouter un Listener sur une zone.
	 * @param pcl le listener à ajouter
	 */
	void addPropertyChangeListener(PropertyChangeListener pcl);

	/**
	 * Enlever un Listener sur une zone.
	 * @param pcl le listener à enlever
	 */
	void removePropertyChangeListener(PropertyChangeListener pcl);
}
