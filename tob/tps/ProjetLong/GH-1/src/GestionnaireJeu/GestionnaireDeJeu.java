package GestionnaireJeu;

import Barre_Outils.ModeleInventaire;
import Carte.Modele.LieuCarte;
import Joueur.JoueurSimple;
import Zone.Modele.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class GestionnaireDeJeu implements PropertyChangeListener {
	private final HashMap<String, ZoneSimple> zones;
	private final JoueurSimple joueur;

	public GestionnaireDeJeu(){
		zones = new HashMap<>();

		ZoneSimple Grotte =  new ZoneSimple("grotte.jpg", "Grotte");
		ZoneSimple Village =  new ZoneSimple("village.jpg", "Village");
		ZoneSimple Foret = new ZoneSimple("foret.jpg", "Foret");
		ZoneSimple Crash = new ZoneSimple("avion.jpg", "Crash");
		ZoneSimple Plage = new ZoneSimple("plage.jpg", "Plage");
		ZoneSimple Falaise = new ZoneSimple("falaise.jpg", "Falaise");
		ZoneSimple GrotteSombre = new ZoneSimple("grotte-sombre.jpg", "Grotte Sombre");
		ZoneSimple GrotteFinale = new ZoneSimple("grottefinale.jpg", "Grotte Finale");
		ZoneSimple GrotteNoire = new ZoneSimple("grotte-noire.jpg", "Grotte Noire");
		zones.put(Foret.getNom(), Foret);
		zones.put(Village.getNom(), Village);
		zones.put(Crash.getNom(), Crash);
		zones.put(Plage.getNom(), Plage);
		zones.put(Falaise.getNom(), Falaise);
		zones.put(Grotte.getNom(), Grotte);
		zones.put(GrotteSombre.getNom(), GrotteSombre);
		zones.put(GrotteFinale.getNom(), GrotteFinale);
		zones.put(GrotteNoire.getNom(), GrotteNoire);

		joueur = new JoueurSimple(Crash);
		joueur.getCarte().ajouterLieuAccessible(new LieuCarte(Grotte, "Grotte", "croix.png", 100, 100, joueur));

		Plage.ajouterElementInteractif(new Naufrage(this));
		Grotte.ajouterElementInteractif(new Fleche(this));
		Falaise.ajouterElementInteractif(new Tronceon(this));
		GrotteSombre.ajouterElementInteractif(new Torche(this));
		GrotteFinale.ajouterElementInteractif(new Sorcier(this));
		Village.ajouterElementInteractif(new Indien(this));

		joueur.addPropertyChangeListener(this);

	}

	public ZoneSimple getZone(String nomZone) {
		return zones.get(nomZone);
	}

	public HashMap<String, ZoneSimple> getZones(){
		return zones;
	}

	public JoueurSimple getJoueur() {
		return joueur;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("Objet tenu") || evt.getPropertyName().equals("zone")){
			if (joueur.objetTenu() == ModeleInventaire.Contenu.TORCHE && joueur.getZoneCourante().getNom().equals("Grotte Noire")){
				joueur.setZoneCourante(zones.get("Grotte Finale"));
				joueur.setMessage("Vous allumez la torche et parvenez à avancer!");
			}
		}
	}
}
