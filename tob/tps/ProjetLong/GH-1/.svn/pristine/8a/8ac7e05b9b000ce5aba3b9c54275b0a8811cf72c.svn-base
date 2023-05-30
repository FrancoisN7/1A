package Carte.Modele;

import Affichable.Affichable;

import java.util.HashMap;
import java.util.Set;

/**
 *  Une carte est un élément affichable, qui possède des lieux accessibles et inaccessibles.
 */
public interface Carte extends Affichable {

    /**
     * Obtenir l'ensemble des lieux accessibles.
     * @return l'ensemble des lieux accessibles
     */
    HashMap<String, LieuCarte> getLieuxAccessibles();

    /**
     * Obtenir l'ensemble des lieux inaccessibles.
     * @return l'ensemble des lieux inaccessibles
     */
    HashMap<String, LieuCarte> getLieuxInaccessibles();

    /**
     * Ajouter un lieu accessible.
     * Si le lieu était précédemment inaccessible, il ne l'est plus.
     * @param accessible le lieu accessible à ajouter
     */
    void ajouterLieuAccessible(LieuCarte accessible);

    /**
     * Ajouter un lieu inaccessible.
     * Si le lieu était précédemment accessible, il ne l'est plus.
     * @param inaccessible le lieu inaccessible à ajouter
     */
    void ajouterLieuInaccessible(LieuCarte inaccessible);
}
