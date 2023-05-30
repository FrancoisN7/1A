package Introduction;

import IU.IU;
import Joueur.Joueur;
import GestionnaireJeu.GestionnaireDeJeu;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Introduction implements ActionListener {

    private String[] textes;
    private int indiceTexte;
    private Joueur joueur;
    private GestionnaireDeJeu gestionnaireDeJeu;
    private IU fenetre;

    public Introduction(String...textes){
        gestionnaireDeJeu = new GestionnaireDeJeu();
        joueur = gestionnaireDeJeu.getJoueur();
        indiceTexte = 1;
        this.textes = textes;

        JButton continuer = new JButton("Continuer");
        continuer.addActionListener(this);
        
        fenetre = new IU(gestionnaireDeJeu);
        joueur.setMessage(textes[0]);
        fenetre.getBarreUtilisateur().add(continuer);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (indiceTexte < textes.length) {
            joueur.setMessage(textes[indiceTexte++]);
        }
        else {
            joueur.setZoneCourante(gestionnaireDeJeu.getZone("Plage"));
            fenetre.initialiser();
        }
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        EventQueue.invokeLater(() -> new Introduction("Le crash de votre avion vous laisse seul sur une île déserte.", "L'objectif? S'enfuir vivant de l'île.", "Et attention, chaque mauvaise décision met votre vie en péril."));
    }
}