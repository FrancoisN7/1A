package Joueur;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import Barre_Outils.ModeleInventaire;
import Carte.Modele.Carte;
import Carte.Modele.CarteSimple;
import Zone.Modele.Zone;
import Barre_Outils.ModeleInventaireSimple;

public class JoueurSimple implements Joueur {

	private Zone zoneCourante;
	private final PropertyChangeSupport support;
	private String message;
	private final ModeleInventaireSimple inventaire;
	private final Carte carte;

	private ModeleInventaire.Contenu objetTenu;

	/**
	 * Créer un joueur dans une zone de départ choisie.
	 * @param zoneDepart la zone de départ
	 */
	public JoueurSimple(Zone zoneDepart) {
		this.zoneCourante = zoneDepart;
		this.message = null;
		carte = new CarteSimple();

		this.inventaire = new ModeleInventaireSimple(this);
		support = new PropertyChangeSupport(this);
		objetTenu = null;
	}

	@Override
	public ModeleInventaireSimple getInventaire() {
		return this.inventaire;
	}

	@Override
	public Zone getZoneCourante() {
		return this.zoneCourante;
	}

	@Override
	public void obtenirCarte(){
		support.firePropertyChange("carte obtenue", null, carte);
		this.setMessage("Vous traversez avec succès et vous trouvez une carte oubliée sur le sol");
	}


	@Override
	public void donnerCartePourModification(){
		support.firePropertyChange("carte modifiée", false, true);
		this.setMessage("Votre carte a été modifiée");
	}
	public ModeleInventaire.Contenu objetTenu() {
		return objetTenu;
	}

	@Override
	public void tenirObjet(ModeleInventaire.Contenu objet) {
		objetTenu = objet;
		support.firePropertyChange("Objet tenu", null, objet);
	}

	@Override
	public void setZoneCourante(Zone nouvelleZone) {
		Zone ancienneZone = this.zoneCourante;
		this.zoneCourante = nouvelleZone;
		support.firePropertyChange("zone", ancienneZone, nouvelleZone);
	}

	@Override
	public Carte getCarte(){
		return carte;
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
	public void setMessage(String message) {
		support.firePropertyChange("message", this.message, message);
		this.message = message;
	}

}
