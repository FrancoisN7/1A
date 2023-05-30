package Zone.Modele;

import Affichable.Interactif;
import Barre_Outils.InventairePleinException;
import Barre_Outils.ModeleInventaire.Contenu;
import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Torche implements Interactif {

    private final ImageIcon image;
    private final Joueur joueur;
    private final GestionnaireDeJeu gestionnaireDeJeu;

    public Torche(GestionnaireDeJeu gestionnaireDeJeu){
        this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + "torche_eteinte_petite.png")));
        this.gestionnaireDeJeu = gestionnaireDeJeu;
        this.joueur = gestionnaireDeJeu.getJoueur();
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 370;
    }

    @Override
    public int getY() {
        return 350;
    }

    @Override
    public int getLargeur() {
        return 75;
    }

    @Override
    public int getHauteur() {
        return 131;
    }

    @Override
    public String getNom() {
        return "Torche";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"Ramasser la torche", "Annuler"};
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "Ramasser la torche":
                try {
                    joueur.getInventaire().ajouter(Contenu.TORCHE);
                    joueur.getZoneCourante().supprimerElementInteractif(this);
                    joueur.setMessage("Vous avez ramassé une torche et continuez à avancer!");
                    joueur.setZoneCourante(gestionnaireDeJeu.getZone("Grotte Noire"));

                } catch (InventairePleinException e) {
                    joueur.setMessage(e.getMessage());
                }
                break;
            case "Annuler": break;
        }
    }
}
