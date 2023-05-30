package Zone.Modele;

import Affichable.Interactif;
import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;
import minijeux.minijeu_falaise.Ecran;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Tronceon implements Interactif {

    private final ImageIcon image;
    private final Joueur joueur;
    private final GestionnaireDeJeu gestionnaireDeJeu;

    private Boolean estPasse;

    public Tronceon(GestionnaireDeJeu gestionnaireDeJeu){
        this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + "transparent.png")));
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.joueur = gestionnaireDeJeu.getJoueur();
        this.estPasse = false;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 480;
    }

    @Override
    public int getY() {
        return 295;
    }

    @Override
    public int getLargeur() {
        return 150;
    }

    @Override
    public int getHauteur() {
        return 75;
    }

    @Override
    public String getNom() {
        return "Tronceon";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Traverser", "Annuler"};
    }

    public void setEstPasse(Boolean estPasse){
        this.estPasse = estPasse;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Traverser":   if (!estPasse){new Ecran(joueur, this);}
                                else {joueur.setMessage("Le tronçon semble plus fragile après votre passage réussi!");}
                                break;
            case "Annuler": break;
        }
    }
}
