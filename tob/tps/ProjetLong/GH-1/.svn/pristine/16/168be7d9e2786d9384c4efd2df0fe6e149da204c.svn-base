package Zone.Modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

import javax.swing.ImageIcon;

import Affichable.*;
import Zone.VC.ZoneVue;

public class ZoneSimple implements Zone {
	private final Set<Interactif> elements;
	private final ImageIcon image;
	private final  String nom;

	private final PropertyChangeSupport support;;

	public ZoneSimple(String image, String nom, Interactif... elements) {
		this.elements = new HashSet<>(Arrays.asList(elements));
		this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + image)));
		this.nom = nom;
		this.support = new PropertyChangeSupport(this);
	}

	@Override
	public void ajouterElementInteractif(Interactif element){
		support.firePropertyChange("ajout element", null, element);
		elements.add(element);
	}

	@Override
	public void supprimerElementInteractif(Interactif element){
		support.firePropertyChange("suppression element", element, null);
		elements.remove(element);
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
	public Iterator<Interactif> iterator() {
		return elements.iterator();
	}

	@Override
	public ImageIcon getImage() {
		return image;
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public int getLargeur() {
		return ZoneVue.LARGEUR;
	}

	@Override
	public int getHauteur() {
		return ZoneVue.LONGUEUR;
	}

	@Override
	public String getNom() {
		return nom;
	}


}
