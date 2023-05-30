package Zone.VC;

import javax.swing.JPanel;

import GestionnaireJeu.GestionnaireDeJeu;
import Zone.Modele.Zone;
import Zone.Modele.ZoneSimple;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;

public class ZoneVue extends JPanel implements PropertyChangeListener {
	

	public static final int LARGEUR = 1280;
	public static final int LONGUEUR = 600;


	protected final HashSet<PanelZone> zonesPanels;
	public ZoneVue(GestionnaireDeJeu gestionnaireDeJeu){
		super();
		this.setLayout(new CardLayout());

		zonesPanels = new HashSet<>();

		// Paramètres de la vue sur la zone
		this.setPreferredSize(new Dimension(ZoneVue.LARGEUR, ZoneVue.LONGUEUR));
		this.setBackground(Color.BLUE);

		for (ZoneSimple zone: gestionnaireDeJeu.getZones().values()){
			PanelZone zonePanel = new PanelZone(zone);
			this.add(zonePanel, zone.getNom());
			zonesPanels.add(zonePanel);
		}
		CardLayout cl = (CardLayout) this.getLayout();
		cl.show(this, gestionnaireDeJeu.getJoueur().getZoneCourante().getNom());

		gestionnaireDeJeu.getJoueur().addPropertyChangeListener(this);
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("zone")){
			// Afficher la nouvelle zone
			CardLayout cl = (CardLayout) this.getLayout();
			cl.show(this, ((Zone) evt.getNewValue()).getNom());
		}
	}
		
}

