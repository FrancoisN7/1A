package Zone.VC;

import Affichable.Interactif;
import Zone.Modele.ZoneSimple;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.HashSet;

public class PanelZone extends JPanel implements PropertyChangeListener {

    protected final HashMap<Interactif, LabelElementInteractif> labelsElementsInteractifs;
    protected final JLabel labelZone;

    public PanelZone(ZoneSimple zone){
        super();
        labelsElementsInteractifs = new HashMap<>();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(ZoneVue.LARGEUR, ZoneVue.LONGUEUR));
        labelZone = new JLabel(zone.getImage());

        for (Interactif element: zone) {
            LabelElementInteractif labelElementInteractif = new LabelElementInteractif(element);
            labelsElementsInteractifs.put(element, labelElementInteractif);
            labelZone.add(labelElementInteractif);
        }
        labelZone.setSize(new Dimension(ZoneVue.LARGEUR, ZoneVue.LONGUEUR));
        this.add(labelZone);
        zone.addPropertyChangeListener(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("ajout element")){
            labelZone.remove(labelsElementsInteractifs.get((Interactif) evt.getNewValue()));
        }
        else if(evt.getPropertyName().equals("suppression element")){
            labelZone.remove(labelsElementsInteractifs.get((Interactif) evt.getOldValue()));
            this.repaint();
        }
    }
}
