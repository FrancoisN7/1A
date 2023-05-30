package allumettes;

public class Rapide implements Strategie {
	
	/** Obtenir le nombre d'allumettes que le joueur veut prendre.
	 * @return le nombre d'allumettes que le joueur veut prendre */
	@Override
    public int getPrise(int AlRestantes) {
    	int prise;
    	if (AlRestantes >= Jeu.PRISE_MAX) {
        prise = Jeu.PRISE_MAX; //utiliser le parametre jeu pour savoir si c'est possible
    	}
    	else {
    	prise = AlRestantes;
    	}
        return prise;
    }

}
