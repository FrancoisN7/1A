package Zone.Modele;

import Affichable.Interactif;
import Carte.Modele.Carte;
import Carte.Modele.LieuCarte;
import Dialogue.*;
import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Sorcier implements Interactif {

    private final ImageIcon image;
    private final Joueur joueur;
    private final GestionnaireDeJeu gestionnaireDeJeu;

    private boolean reussi;

    public Sorcier(GestionnaireDeJeu gestionnaireDeJeu){
        this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + "sorcier.png")));
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.joueur = gestionnaireDeJeu.getJoueur();
        reussi = false;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 200;
    }

    @Override
    public int getY() {
        return 150;
    }

    @Override
    public int getLargeur() {
        return 100;
    }

    @Override
    public int getHauteur() {
        return 250;
    }

    @Override
    public String getNom() {
        return "Sorcier";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Parler", "Annuler"};
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Parler":
                if (!reussi) {
                    new DialogueIU(new DialogueSimple("Sorcier", "sorcier.png", genererArbreDialogueSorcier()));
                }
                else {
                    joueur.setMessage("Le sorcier semble occuper à s'amuser avec sa barbe");
                }
                break;
            case "Annuler": break;
        }
    }

    private ArbreDialogue genererArbreDialogueSorcier(){
        ArbreDialogue arbreDialogue = new ArbreDialogue(null, "Sorcier: Si tu veux passer tu dois réussir mon énigme!", "1.Ok", "2.Non");
        arbreDialogue.ajouterEnfant(null, "Sorcier: QELP, quelle est la prochaine lettre?", "1.Q", "2.S", "3.L", "4.A");
        arbreDialogue.descendreSurEnfant(1);
        arbreDialogue.ajouterEnfant(null, "Sorcier: Non!", "1.Quitter");
        arbreDialogue.ajouterEnfant(null, "Sorcier: Non!", "1.Quitter");
        arbreDialogue.ajouterEnfant(new Executable() {
            @Override
            public void executer() {
                Zone village = gestionnaireDeJeu.getZone("Village");
                Joueur joueur = gestionnaireDeJeu.getJoueur();
                Carte carte = joueur.getCarte();
                carte.ajouterLieuAccessible(new LieuCarte(village, "Village", "croix.png", 300, 300, gestionnaireDeJeu.getJoueur()));
                carte.ajouterLieuInaccessible(carte.getLieuxAccessibles().get("Grotte"));
                joueur.donnerCartePourModification();
                reussi = true;
            }
        }, "Sorcier: Oui! Je vais écrire sur ta carte la position d'un village qui pourra t'aider", "1.Le remercier");
        arbreDialogue.ajouterEnfant(null, "Sorcier: Non!", "1.Quitter");
        return arbreDialogue;
    }
}
