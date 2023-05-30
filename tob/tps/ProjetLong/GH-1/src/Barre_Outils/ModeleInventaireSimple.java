package Barre_Outils;

import GestionnaireJeu.GestionnaireDeJeu;
import Joueur.Joueur;

import javax.swing.JFrame;
import java.util.Arrays;

public class ModeleInventaireSimple implements ModeleInventaire {

    /** La zone de l'inventaire */
	private Contenu[][] cases;

    private Joueur joueur;

    public ModeleInventaireSimple(Joueur joueur){

        // Créer les cases de l'inventaire
        this.cases = new Contenu[ModeleInventaire.COLONNES][ModeleInventaire.LIGNES];

        this.joueur = joueur;

        initialiser();

    }

    /** Initialiser l'inventaire */
	private void initialiser() {

        for (Contenu[] ligne : this.cases) {
            Arrays.fill(ligne, Contenu.VIDE);
        }
    }

   
    public boolean estVide(int x, int y){
        return getValeur(x,y) == Contenu.VIDE;
    }

    public void ajouter(Contenu objet) throws InventairePleinException{
        
        int i =0;
        int j = 0;
        while ( this.cases[i][j] != Contenu.VIDE ) {
            i++;
            j++;

            if ( i == this.cases.length  && j== this.cases[i].length ){
                throw new InventairePleinException("Impossible, l'inventaire est plein !");
            }

            if ( i == this.cases.length ){
                i = 0;
            }
            if ( j == this.cases[i].length ){
                j = 0;
            }
             

        }

        this.cases[i][j] = objet;

    }


    public ModeleInventaire.Contenu prendre(int x, int y) throws CaseVideException {

        if ( estVide(x,y)){

            throw new CaseVideException("La case est vide");
        }

        Contenu cont = this.cases[x][y];
        this.cases[x][y] = Contenu.VIDE;
        joueur.setMessage("Vous avez pris de votre inventaire:" + cont);
        joueur.tenirObjet(cont);
        return cont;
    }


    public ModeleInventaire.Contenu getValeur(int x, int y){
        return this.cases[x][y];
    }
    
    public String getNom(int x, int y){
        ModeleInventaire.Contenu val = this.getValeur(x, y);
        
        if (val == Contenu.TORCHE) {
        	return ("rond_torche2.png");
        }
        else {
        	return ("rondInventaire3.PNG");
        }
    }


}