package Dialogue;

import java.beans.PropertyChangeListener;
import java.util.List;

import Affichable.Affichable;

public interface Dialogue extends Affichable {

	List<String> getOptions();

	String getPhrase();


	void setChoix(int choix);

		/**
	 * Ajouter un Listener sur le dialogue.
	 * @param pcl le listener à ajouter
	 */
	void addPropertyChangeListener(PropertyChangeListener pcl);

	/**
	 * Enlever un Listerner sur dialogue.
	 * @param pcl le listener à enlever
	 */
	void removePropertyChangeListener(PropertyChangeListener pcl);
	
}
