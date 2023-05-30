package Carte.VC;

import Carte.Modele.LieuCarte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

/**
 * Controleur servant à la carte.
 * Chaque lieu à un menu d'options
 */
public class CarteControleur extends JPanel {

    public CarteControleur(CarteVue carteVue, CarteFenetre carteFenetre) {
        super();
        Set<LabelLieu> labelLieux = carteVue.labelLieux;

        // Créer tous les menus sur les lieux
        for (LabelLieu labelLieu : labelLieux) {
            // Créer un menu
            LieuCarte lieu = labelLieu.getLieu();
            JPopupMenu popupMenu = new JPopupMenu();

            // Ajouter les différentes options possibles
            for (String option : lieu.getOptions()) {
                JMenuItem menuItem = new JMenuItem(option);
                menuItem.addActionListener(lieu);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getActionCommand().equals("S'y rendre")){
                            carteFenetre.dispose();
                        }
                    }
                });

                popupMenu.add(menuItem);
            }

            // Afficher le menu
            labelLieu.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    popupMenu.show(labelLieu, mouseEvent.getX(), mouseEvent.getY());
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
