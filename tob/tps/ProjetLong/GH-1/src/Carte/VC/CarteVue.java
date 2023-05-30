package Carte.VC;

import Carte.Modele.Carte;
import Carte.Modele.LieuCarte;
import Joueur.Joueur;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class CarteVue extends JPanel {

    public static final int HAUTEUR = 600;
    public static final int LARGEUR = 464;

    protected final Set<LabelLieu> labelLieux;

    public CarteVue(Joueur joueur){
        super();

        labelLieux = new HashSet<>();
        //this.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));

        // Afficher la carte
        Carte carte = joueur.getCarte();
        JLabel labelCarte = new JLabel((carte.getImage()));
        this.add(labelCarte);

        // Afficher les zones accessibles

        for(LieuCarte accessible : carte.getLieuxAccessibles().values()){
            LabelLieu labelLieu = new LabelLieu(accessible);
            labelLieu.setText(accessible.getNom());
            labelLieu.setHorizontalTextPosition(JLabel.CENTER);
            labelLieu.setVerticalTextPosition(JLabel.TOP);
            labelLieu.setFont(new Font("Verdana", Font.PLAIN, 18));
            labelLieu.setForeground(Color.BLACK);
            labelLieu.setBounds(accessible.getX(), accessible.getY(), 2*accessible.getLargeur(), 2*accessible.getHauteur());
            labelLieux.add(labelLieu);
            labelCarte.add(labelLieu);
        }
    }
}
