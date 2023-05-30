package Dialogue;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;

public class DialogueSimple implements Dialogue {

	private ArbreDialogue arbreDialogue;
	private PropertyChangeSupport support;
	private String nom;
	private String nomImage;

	public DialogueSimple(String nomPersonnage, String nomImage, ArbreDialogue arbreDialogue){
		this.nom = nomPersonnage;
		support = new PropertyChangeSupport(this);
		this.arbreDialogue = arbreDialogue;
		this.nomImage = nomImage;
	}

	@Override
	public void setChoix(int choix){
		List<String> ancienneValeur = this.getOptions();
		if (!arbreDialogue.estTermine()) {
			arbreDialogue.descendreSurEnfant(choix);
			support.firePropertyChange("options", ancienneValeur, this.getOptions());
			support.firePropertyChange("phrase", true, this.getPhrase());
		}
		else {
			arbreDialogue.executerConsequences();
			support.firePropertyChange("Terminé", false, true);
		}


	}
	@Override
	public List<String> getOptions() {
		return arbreDialogue.getOptions();
	}

	@Override
	public String getPhrase() {
		return arbreDialogue.getPhrase();
	}

	@Override
	public ImageIcon getImage() {
		return new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + nomImage)));
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return 640;
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return 480;
	}
	
	@Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	@Override
	public String getNom() {
		return nom;
	}

	
	
}
