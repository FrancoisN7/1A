package Zone.Modele;

import Affichable.Interactif;
import Bataille_Navale.BatailleSwing;
import Bataille_Navale.ModeleGrillePersoSimple;
import Dialogue.ArbreDialogue;
import Dialogue.DialogueIU;
import Dialogue.DialogueSimple;
import Dialogue.Executable;
import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Indien implements Interactif {

    private final ImageIcon image;
    private final Joueur joueur;
    private final GestionnaireDeJeu gestionnaireDeJeu;

    public enum Tentative {PREMIERE, DEJAREUSSI, DEJARATE};

    private Tentative tentative = Tentative.PREMIERE;

    public Indien(GestionnaireDeJeu gestionnaireDeJeu){
        this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + "indien.png")));
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.joueur = gestionnaireDeJeu.getJoueur();
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 900;
    }

    @Override
    public int getY() {
        return 300;
    }

    @Override
    public int getLargeur() {
        return 100;
    }

    @Override
    public int getHauteur() {
        return 140;
    }

    @Override
    public String getNom() {
        return "Indien";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Parler", "Annuler"};
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Parler":
                switch (tentative){
                    case PREMIERE: new DialogueIU(new DialogueSimple("Indien", "indien.png", genererArbreDialogueIndien())); break;
                    case DEJAREUSSI: new DialogueIU(new DialogueSimple("Indien", "indien.png", genererArbreDialogueIndienReussite()));
                                    joueur.setMessage("Vous êtes maintenant le chef du village! Victoire!");break;
                    case DEJARATE: new DialogueIU(new DialogueSimple("Indien", "indien.png", genererArbreDialogueIndienRate())); break;
                }
            case "Annuler": break;
        }
    }

    public ArbreDialogue genererArbreDialogueIndien() {
        Indien indien = this;
        ArbreDialogue arbreDialogue = new ArbreDialogue(null, "Indien: Comment-es tu arrivé là?", "1. Grâce à un sorcier");
        arbreDialogue.ajouterEnfant(null, "Indien: Nous ne pouvons pas accepter des étrangers.", "1. Que puis-je faire pour être accepté?");
        arbreDialogue.descendreSurEnfant(1);
        arbreDialogue.ajouterEnfant(new Executable() {
            @Override
            public void executer() {
               new BatailleSwing(new ModeleGrillePersoSimple(), indien, gestionnaireDeJeu);
            }
        }, "Indien: Te battre contre moi... A la bataille navale", "1.Je relève le défi");
        arbreDialogue.retourARacine();
        return arbreDialogue;
    }

    public void setTentative(Tentative tentative) {
        this.tentative = tentative;
    }

    public ArbreDialogue genererArbreDialogueIndienReussite() {
        ArbreDialogue arbreDialogue = new ArbreDialogue(null, "Indien: Tu es vraiment fort. Plus fort que notre chef!", "1. Facile");
        arbreDialogue.ajouterEnfant(null, "Indien: Je te nomme maintenant nouveau chef du village", "1. Je ne peux pas rester, un volcan va exploser");
        arbreDialogue.descendreSurEnfant(1);
        arbreDialogue.ajouterEnfant(null,
        "Indien: C'est une personne de notre clan qui raconte cette histoire pour qu'on nous laisse en paix", "1. Devenir le chef du clan");
        arbreDialogue.retourARacine();
        return arbreDialogue;
    }

    public ArbreDialogue genererArbreDialogueIndienRate() {
        Indien indien = this;
        ArbreDialogue arbreDialogue = new ArbreDialogue(new Executable() {
            @Override
            public void executer() {
                new BatailleSwing(new ModeleGrillePersoSimple(), indien, gestionnaireDeJeu);
            }
            }, "Indien: Une nouvelle tentative?", "1. C'est parti");
        return arbreDialogue;
    }
}
