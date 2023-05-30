package Zone.Modele;

import Affichable.Interactif;
import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Fleche implements Interactif {

    private final ImageIcon image;
    private final Joueur joueur;
    private final GestionnaireDeJeu gestionnaireDeJeu;

    public Fleche(GestionnaireDeJeu gestionnaireDeJeu){
        this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + "thrust.png")));
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.joueur = gestionnaireDeJeu.getJoueur();
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 610;
    }

    @Override
    public int getY() {
        return 480;
    }

    @Override
    public int getLargeur() {
        return 75;
    }

    @Override
    public int getHauteur() {
        return 75;
    }

    @Override
    public String getNom() {
        return "Fleche";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Entrer dans la grotte", "Annuler"};
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Entrer dans la grotte": joueur.setZoneCourante(gestionnaireDeJeu.getZone("Grotte Sombre")); break;
            case "Annuler": break;
        }
    }
}
