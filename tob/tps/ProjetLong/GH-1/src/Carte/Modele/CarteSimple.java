package Carte.Modele;

import Carte.VC.CarteVue;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CarteSimple implements Carte {

    private final HashMap<String, LieuCarte> accessibles;
    private final HashMap<String, LieuCarte> inaccessibles;
    private final ImageIcon image;
    static final String REL_PATH_TO_IMAGE = "res/carte.jpg";

    /**
     * Créer une carte.
     */
    public CarteSimple(){
        this.accessibles = new HashMap<>();
        this.inaccessibles = new HashMap<>();
        image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(REL_PATH_TO_IMAGE)));
    }


    @Override
    public ImageIcon getImage() {
        return image;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getLargeur() {
        return CarteVue.LARGEUR;
    }

    @Override
    public int getHauteur() {
        return CarteVue.HAUTEUR;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public HashMap<String, LieuCarte> getLieuxAccessibles() {
        return accessibles;
    }

    @Override
    public HashMap<String, LieuCarte> getLieuxInaccessibles() {
        return inaccessibles;
    }

    @Override
    public void ajouterLieuAccessible(LieuCarte accessible) {
        this.inaccessibles.remove(accessible.getNom());
        this.accessibles.put(accessible.getNom(), accessible);
    }

    @Override
    public void ajouterLieuInaccessible(LieuCarte inaccessible) {
        this.accessibles.remove(inaccessible.getNom());
        this.inaccessibles.put(inaccessible.getNom(), inaccessible);
    }
}
