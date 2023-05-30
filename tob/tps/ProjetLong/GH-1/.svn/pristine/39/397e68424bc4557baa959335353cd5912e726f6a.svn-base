package Carte.Modele;

import Affichable.Interactif;
import Joueur.Joueur;
import Zone.Modele.Zone;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.util.Objects;


public class LieuCarte implements Interactif {

    static final int TAILLE_IMAGE = 50;

    private final Zone zone;
    private final String nom;
    private final ImageIcon image;
    private final int x;
    private final int y;
    private final Joueur joueur;

    /**
     * Créer un lieu à afficher sur la carte.
     * @param zone la zone référencée par le lieu sur la carte
     * @param nomLieu le nom du lieu
     * @param nomImage le nom de l'image à afficher pour ce lieu
     * @param posX l'abscisse sur la carte
     * @param posY l'ordonnée sur la carte
     * @param joueur le joueur
     */
    public LieuCarte(Zone zone, String nomLieu, String nomImage, int posX, int posY, Joueur joueur){
       this.zone = zone;
       this.nom = nomLieu;
       this.image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("res/" + nomImage)));
       this.x = posX;
       this.y = posY;
       this.joueur = joueur;
    }

    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getLargeur() {
        return TAILLE_IMAGE;
    }

    @Override
    public int getHauteur() {
        return TAILLE_IMAGE;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String[] getOptions() {
        return new String[]{"S'y rendre", "Annuler"};
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case "S'y rendre": joueur.setZoneCourante(zone); break;
            case "Annuler": break;
        }
    }
}
