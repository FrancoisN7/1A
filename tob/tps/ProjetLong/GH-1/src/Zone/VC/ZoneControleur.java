package Zone.VC;

import Affichable.Interactif;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class ZoneControleur extends JPanel {

    public ZoneControleur(ZoneVue zoneVue){
        super();
        HashSet<PanelZone> zonesPanel = zoneVue.zonesPanels;
        for (PanelZone zonePanel : zonesPanel){
            for (LabelElementInteractif labelElement : zonePanel.labelsElementsInteractifs.values()){

                // Ajouter les différentes options possibles
                Interactif element = labelElement.element;
                JPopupMenu popupMenu = new JPopupMenu();

                for (String option : element.getOptions()) {
                    JMenuItem menuItem = new JMenuItem(option);
                    menuItem.addActionListener(element);
                    popupMenu.add(menuItem);
                }

                // Afficher le menu
                labelElement.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        popupMenu.show(labelElement, mouseEvent.getX(), mouseEvent.getY());
                    }

                    @Override
                    public void mousePressed(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent mouseEvent) {

                    }

                    @Override
                    public void mouseExited(MouseEvent mouseEvent) {

                    }
                });
            }
        }
    }
}
