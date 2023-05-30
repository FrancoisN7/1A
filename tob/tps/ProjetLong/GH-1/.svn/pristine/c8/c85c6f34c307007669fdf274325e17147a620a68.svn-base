package IU;

import javax.swing.*;

import GestionnaireJeu.GestionnaireDeJeu;
import Zone.VC.ZoneControleur;
import Zone.VC.ZoneVue;

import java.awt.BorderLayout;

public class IU extends JFrame {
	

	BarreUtilisateur barreUtilisateur;
	public IU(GestionnaireDeJeu gestionnaireDeJeu) {

		// Affichage de la Zone
		ZoneVue zoneVue = new ZoneVue(gestionnaireDeJeu);
		ZoneControleur zoneControleur = new ZoneControleur(zoneVue);
		this.add(zoneVue, BorderLayout.NORTH);

		// Affichage de la barre utilisateur
		barreUtilisateur = new BarreUtilisateur(gestionnaireDeJeu.getJoueur());
		this.add(barreUtilisateur, BorderLayout.SOUTH);

		// Paramètres de la fenêtre
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public BarreUtilisateur getBarreUtilisateur(){
		return barreUtilisateur;
	}

	public void initialiser() {
		barreUtilisateur.initialiser();
		this.pack();
	}
}
