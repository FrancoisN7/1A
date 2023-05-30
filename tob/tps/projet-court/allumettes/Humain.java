package allumettes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Humain implements Strategie {
	static Scanner saisieUtilisateur = new Scanner(System.in);
	/**
	 * Obtenir le nombre d'allumettes que le joueur veut prendre.
	 * 
	 * @return le nombre d'allumettes que le joueur veut prendre
	 */
	@Override
	public int getPrise(Jeu jeu) throws ConfigurationException {
		int prise = 0;
		try {
			prise = saisieUtilisateur.nextInt();
		} catch (InputMismatchException e) {
			saisieUtilisateur.nextLine();
			throw new ConfigurationException("char");
		}
		return prise;
	}
}
